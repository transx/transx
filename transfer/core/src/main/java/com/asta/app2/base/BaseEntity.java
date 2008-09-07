package com.asta.app2.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.LazyInitializationException;

public abstract class BaseEntity<U extends Serializable> extends
		BasePersistentObject implements Serializable, Cloneable {

	private boolean isRefined = false;

	private void refineMetaData() {
		if (isRefined)
			return;
		isRefined = true;
		try {
			HashMap<String, String> temp = new HashMap<String, String>();
			for (String col : col2prop.keySet())
				temp.put(col.toLowerCase(), col2prop.get(col));
			col2prop = temp;
			temp = new HashMap<String, String>();
			for (String prop : prop2col.keySet())
				temp.put(prop, prop2col.get(prop).toLowerCase());
			prop2col = temp;
		} catch (Throwable e) {
		}
	}

	protected Map<String, String> col2prop = new HashMap<String, String>();

	protected Map<String, String> prop2col = new HashMap<String, String>();

	protected String tableName;

	/**
	 * @return Returns the ID of this entity
	 */
	public abstract U getID();

	/**
	 * 
	 * @param id:
	 *            Specifies the new id of this entity which is to be replaced by
	 *            the entity id
	 */
	public void setID(U id) {
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Will prepare a {@link String} including all the values from all the
	 * fields of tne entity.
	 * 
	 * @return
	 */
	public String getFullDescription() {
		Object[] args = new Object[getEntityMetaData().getAttributes().size() * 2];
		int i = 0;
		for (AttributeSet.Attribute attr : getEntityMetaData().getAttributes()) {
			args[i++] = attr.name;
			args[i++] = attr.getValue(this);
		}
		return getFullDescription(args);
	}

	protected String getFullDescription(Object... arg) {
		StringBuffer msg = new StringBuffer("");
		if (arg == null || arg.length == 0)
			return "";
		for (int i = 1; i < arg.length; i += 2) {
			msg.append(arg[i - 1] + ":" + arg[i]);
			if (arg.length - i > 2) {
				msg.append(", ");
			}
		}
		return msg.toString();
	}

	/**
	 * This method creates a {@link Map} of names to values for an entity.
	 * 
	 * @return
	 */
	public Map<String, Object> getDataMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (getEntityMetaData() != null) {
			fillDataMap(map, "", getEntityMetaData(), this);
		}
		return map;
	}

	/**
	 * Will return the table name for persistent layer for the entity.
	 * 
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}

	private void fillDataMap(Map<String, Object> map, String prefix,
			AttributeSet as, BasePersistentObject entity) {
		if (entity == null)
			return;
		for (AttributeSet.Attribute attr : getEntityMetaData().getAttributes())
			map.put(prefix + attr.name, attr.getValue(entity));

		for (AttributeSet.Relation rel : getEntityMetaData()
				.getSome2OneRelations())
			map.put(prefix + rel.name, rel.getValue(entity));

		for (AttributeSet.ComponentMetaData comp : getEntityMetaData()
				.getComponents())
			fillDataMap(map, prefix + comp.name + '.', comp, comp
					.getValue(entity));
	}

	/**
	 * Given the column name for an entity in the persistence layer, will return
	 * the name of Java property regarding to that column.
	 * 
	 * @param column
	 * @return
	 */
	public String getPropertyName(String column) {
		refineMetaData();
		column = column.toLowerCase();
		// Log.debug("%s->%s", column, col2prop.get(column));
		return col2prop.get(column);
	}

	/**
	 * To set the name of the table for the entity in the database.
	 * 
	 * @param tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Will do as reverse of {@link BaseEntity#getPropertyName(String)} as with
	 * having Java property name, this method will give out the column name of
	 * that property in the persistent layer.
	 * 
	 * @param property
	 * @return
	 */
	public String getColumnName(String property) {
		refineMetaData();
		// Log.debug("%s->%s", property, prop2col.get(property));
		return prop2col.get(property);

	}

	/**
	 * Left empty here. If necessary, will be overrident in subclasses to load
	 * some special values into the entity.
	 */
	public void loadDefaultValues() {
	}

	public EntityMetaData<? extends BaseEntity> getEntityMetaData() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public void cleanNullRelations() {
		if (getEntityMetaData() != null) {
			Collection c = getEntityMetaData().getSome2OneRelations();
			for (AttributeSet<BaseEntity>.Relation rel : (Collection<AttributeSet<BaseEntity>.Relation>) c) {
				try {
					BaseEntity relatedObj = (BaseEntity) rel.getValue(this);
					if (relatedObj != null && relatedObj.getID() == null)
						rel.setValue(this, null);
				} catch (LazyInitializationException e) {
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void initRelations() {
		if (getEntityMetaData() != null) {
			Collection c = getEntityMetaData().getSome2OneRelations();
			for (AttributeSet<BaseEntity>.Relation rel : (Collection<AttributeSet<BaseEntity>.Relation>) c) {
				BaseEntity relatedObj = (BaseEntity) rel.getValue(this);
				if (relatedObj == null) {
					try {
						rel.setValue(this, rel.relatedType.newInstance());
					} catch (InstantiationException e) {
					} catch (IllegalAccessException e) {

					}
				} else
					rel.setValue(this, relatedObj.clone());
			}
		}
	}

}
