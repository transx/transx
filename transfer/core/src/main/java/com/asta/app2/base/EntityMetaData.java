package com.asta.app2.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import com.asta.app2.util.ReflectionUtils;
	
public class EntityMetaData<T extends BaseEntity> extends AttributeSet<T>{
	public final String tableName;
	public EntityMetaData(Class type, String tableName) {
		super(type);
		this.tableName = tableName;
	}

}


@SuppressWarnings("unchecked")
class AttributeSet<T extends BasePersistentObject> {
	private Map<String, Attribute> attributes = new ListOrderedMap();
	private Map<String, EntityField> allAttributesByName = new HashMap<String, EntityField>();
	private Map<String, EntityField> allAttributesByColumn = new HashMap<String, EntityField>();
	private Map<String, ComponentMetaData> components = new ListOrderedMap();
	private Map<String, Relation> relations = new ListOrderedMap();
	private List<Relation> some2OneRelations = new ArrayList<Relation>();
	public Class type;

	public class Attribute extends EntityField {
		public final String column;
		public final Class type;
		public Attribute(String name, String column, Class type) {
			super(name);
			this.column = column;
			this.type = type;
			AttributeSet.this.addAttribute(this);
			AttributeSet.this.allAttributesByName.put(name, this);
			AttributeSet.this.allAttributesByColumn.put(column, this);
		}
		
	}
	
	public class ComponentMetaData extends AttributeSet {
		public final String name;
		public ComponentMetaData(String name, Class type) {
			super(type);
			this.name = name;
			AttributeSet.this.addComponent(this);
		}
		public BaseComponent getValue(BasePersistentObject entity) {
			return (BaseComponent) ReflectionUtils.invokeGetter(entity, name);
		}
	}
		
	
	public abstract class EntityField {
		public final String name;
		public EntityField(String name) {
			this.name = name;
		}
		public AttributeSet getEntity() {
			return AttributeSet.this;
		}
		public Object getValue(T entity) {
			return ReflectionUtils.invokeGetter(entity, name);
		}
		public void setValue(T entity, Object value) {
			ReflectionUtils.invokeSetter(entity, name, value);
		}
	}

	public class M2ORelation extends Relation {
		public final String column;

		public M2ORelation(String name, String column, Class relatedType) {
			super(name, relatedType);
			this.column = column;
			AttributeSet.this.allAttributesByName.put(name, this);
			AttributeSet.this.allAttributesByColumn.put(column, this);
		}
	}
	
	public class O2ORelation extends Relation {
		public final String column;

		public O2ORelation(String name, String column, Class relatedType) {
			super(name, relatedType);
			this.column = column;
			AttributeSet.this.allAttributesByName.put(name, this);
			AttributeSet.this.allAttributesByColumn.put(column, this);
		}
	}
	
	public abstract class Relation extends EntityField {
		public final Class relatedType;
		public Relation(String name, Class relatedType) {
			super(name);
			this.relatedType = relatedType;
			AttributeSet.this.addRelation(this);
		}
	}
	
	public AttributeSet(Class type) {
		this.type = type;
	}
	void addAttribute(Attribute attr) {
		attributes.put(attr.name, attr);
	}
	
	void addComponent(ComponentMetaData comp) {
		components.put(comp.name, comp);
	}
	
	void addRelation(Relation rel) {
		relations.put(rel.name, rel);
		
		if (M2ORelation.class.isInstance(rel) || O2ORelation.class.isInstance(rel))
			some2OneRelations.add(rel);
	}
	
	public Attribute getAttribute(String name) {
		return attributes.get(name);
	}
	
	public Collection<Attribute> getAttributes() {
		return attributes.values();
	}
	
	public ComponentMetaData getComponent(String name) {
		return components.get(name);
	}
	
	public Collection<ComponentMetaData> getComponents() {
		return components.values();
	}

	public Relation getRelation(String name) {
		return relations.get(name);
	}
	
	public Collection<Relation> getRelations() {
		return relations.values();
	}
	
	public Collection<Relation> getSome2OneRelations() {
		return some2OneRelations;
	}
	
	public EntityField getFieldByColumn(String column) {
		return allAttributesByColumn.get(column);
	}
	
	public EntityField getFieldByName(String name) {
		return allAttributesByName.get(name);
	}
	
	

}

