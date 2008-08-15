/* *Class created on [ Jun 21, 2008 | 12:42:25 PM ] */
package com.asta.app2.tutorial.selectchair;

import javax.el.ValueExpression;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class UISelectChair extends UIComponentBase {

	static public final String COMPONENT_FAMILY = "javax.faces.SelectItem";
	static public final String COMPONENT_TYPE = "javax.faces.SelectItem";

	/**
	 * Construct an instance of the UISelectItem.
	 */
	public UISelectChair() {
		setRendererType(null);
	}

	// Property: value
	private Object _value;

	/**
	 * Gets The initial value of this component.
	 * 
	 * @return the new value value
	 */
	public Object getValue() {
		if (_value != null) {
			return _value;
		}
		ValueExpression expression = getValueExpression("value");
		if (expression != null) {
			return expression.getValue(getFacesContext().getELContext());
		}
		return null;
	}

	/**
	 * Sets The initial value of this component.
	 * 
	 * @param value
	 *            the new value value
	 */
	public void setValue(Object value) {
		this._value = value;
	}

	// Property: itemDisabled
	private boolean _itemDisabled;
	private boolean _itemDisabledSet;

	/**
	 * Gets Determine whether this item can be chosen by the user. When true,
	 * this item cannot be chosen by the user. If this method is ever called,
	 * then any EL-binding for the disabled property will be ignored.
	 * 
	 * @return the new itemDisabled value
	 */
	public boolean isItemDisabled() {
		if (_itemDisabledSet) {
			return _itemDisabled;
		}
		ValueExpression expression = getValueExpression("itemDisabled");
		if (expression != null) {
			return (Boolean) expression.getValue(getFacesContext()
					.getELContext());
		}
		return false;
	}

	/**
	 * Sets Determine whether this item can be chosen by the user. When true,
	 * this item cannot be chosen by the user. If this method is ever called,
	 * then any EL-binding for the disabled property will be ignored.
	 * 
	 * @param itemDisabled
	 *            the new itemDisabled value
	 */
	public void setItemDisabled(boolean itemDisabled) {
		this._itemDisabled = itemDisabled;
		this._itemDisabledSet = true;
	}

	// Property: itemLabel
	private String _itemLabel;

	/**
	 * Gets The string which will be presented to the user for this option.
	 * 
	 * @return the new itemLabel value
	 */
	public String getItemLabel() {
		if (_itemLabel != null) {
			return _itemLabel;
		}
		ValueExpression expression = getValueExpression("itemLabel");
		if (expression != null) {
			return (String) expression.getValue(getFacesContext()
					.getELContext());
		}
		return null;
	}

	/**
	 * Sets The string which will be presented to the user for this option.
	 * 
	 * @param itemLabel
	 *            the new itemLabel value
	 */
	public void setItemLabel(String itemLabel) {
		this._itemLabel = itemLabel;
	}

	// Property: itemValue
	private Object _itemValue;

	/**
	 * Gets The value for this Item.
	 * 
	 * @return the new itemValue value
	 */
	public Object getItemValue() {
		if (_itemValue != null) {
			return _itemValue;
		}
		ValueExpression expression = getValueExpression("itemValue");
		if (expression != null) {
			return expression.getValue(getFacesContext().getELContext());
		}
		return null;
	}

	/**
	 * Sets The value for this Item.
	 * 
	 * @param itemValue
	 *            the new itemValue value
	 */
	public void setItemValue(Object itemValue) {
		this._itemValue = itemValue;
	}

	// Property : cssClass
	private String _cssClass;

	public String getCssClass() {
		if (_cssClass != null)
			return _cssClass;

		ValueExpression expression = getValueExpression("cssClass");
		if (expression != null) {
			return (String) expression.getValue(getFacesContext()
					.getELContext());
		}
		return null;
	}

	public void setCssClass(String cssClass) {
		this._cssClass = cssClass;
	}

	// Property : x
	private String _x;

	public String getX() {
		if (_x != null)
			return _x;
		ValueExpression expression = getValueExpression("x");
		if (expression != null) {
			return (String) expression.getValue(getFacesContext()
					.getELContext());
		}
		return null;
	}

	public void setX(String x) {
		this._x = x;
	}

	// Property : y
	private String _y;
	
	public String getY() {
		if (_y != null)
			return _y;
		ValueExpression expression = getValueExpression("y");
		if (expression != null) {
			return (String) expression.getValue(getFacesContext()
					.getELContext());
		}
		return null;
	}
	
	public void setY(String y) {
		this._y = y;
	}
	
	//property : passengerName
	private String _passengerName;

	public String getPassengerName() {
		if (_passengerName != null)
			return _passengerName;
		ValueExpression expression = getValueExpression("passengerName");
		if (expression !=null){
			return (String) expression.getValue(getFacesContext().getELContext());
		}
		return null;
	}

	public void setPassengerName(String passengerName) {
		this._passengerName = passengerName;
	}

	@Override
	public Object saveState(FacesContext facesContext) {
		Object[] values = new Object[10];
		values[0] = super.saveState(facesContext);
		values[1] = _value;
		values[2] = _itemDisabled;
		values[3] = _itemDisabledSet;
		values[4] = _itemLabel;
		values[5] = _itemValue;
		values[6] = _cssClass;
		values[7] = _x;
		values[8] = _y;
		values[9] = _passengerName;

		return values;
	}

	@Override
	public void restoreState(FacesContext facesContext, Object state) {
		Object[] values = (Object[]) state;
		super.restoreState(facesContext, values[0]);
		_value = values[1];
		_itemDisabled = (Boolean) values[2];
		_itemDisabledSet = (Boolean) values[3];
		_itemLabel = (String) values[4];
		_itemValue = values[5];
		_cssClass = (String) values[6];
		_x = (String) values[7];
		_y = (String) values[8];
		_passengerName = (String) values[9];
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

}
