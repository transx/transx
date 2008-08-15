/* *Class created on [ Jun 21, 2008 | 6:40:50 PM ] */
package com.asta.app2.webapp.jsf;

import java.io.Serializable;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SelectChairItem implements Serializable {

	private static final long serialVersionUID = 2555045722731866958L;

	// FIELDS
	private Object _value;
	private String _label;
	private String _cssClass;
	private boolean _disabled;
	private boolean _female;
	private String _x;
	private String _y;
	private String _passengerName;
	
	// CONSTRUCTORS
	public SelectChairItem() {
	}

	public SelectChairItem(Object value) {
		_value = value;
		_label = value == null ? null : value.toString();
		_cssClass = null;
		_disabled = false;
		_female = false;
		_x = null;
		_y = null;
		_passengerName = null;
	}

	public SelectChairItem(Object value, String label) {
		_value = value;
		_label = label;
		_cssClass = null;
		_disabled = false;
		_female = false;
		_x = null;
		_y = null;
		_passengerName = null;
	}

	public SelectChairItem(Object value, String label, String cssClass) {
		_value = value;
		_label = label;
		_cssClass = cssClass;
		_disabled = false;
		_female = false;
		_x = null;
		_y = null;
		_passengerName = null;
	}

	public SelectChairItem(Object value, String label, String cssClass,
			boolean disabled) {
		_value = value;
		_label = label;
		_cssClass = cssClass;
		_disabled = disabled;
		_female = false;
		_x = null;
		_y = null;
		_passengerName = null;
	}

	public SelectChairItem(Object value, String label, boolean disabled,
			String cssClass, String x, String y) {
		_value = value;
		_label = label;
		_cssClass = cssClass;
		_disabled = disabled;
		_female = false;
		_x = x;
		_y = y;
		_passengerName = null;
	}

	public SelectChairItem(Object value, String label, boolean disabled,
			String cssClass, String x, String y, String passengerName) {
		_value = value;
		_label = label;
		_cssClass = cssClass;
		_disabled = disabled;
		_female = false;
		_x = x;
		_y = y;
		_passengerName = passengerName;
	}

	public SelectChairItem(Object value, String label, boolean disabled,boolean female,
			String cssClass, String x, String y, String passengerName) {
		_value = value;
		_label = label;
		_cssClass = cssClass;
		_disabled = disabled;
		_female = female;
		_x = x;
		_y = y;
		_passengerName = passengerName;
	}

	// METHODS
	public boolean isDisabled() {
		return _disabled;
	}

	public void setDisabled(boolean disabled) {
		_disabled = disabled;
	}

	public boolean isFemale() {
		return _female;
	}
	
	public void setFemale(boolean female) {
		_female = female;
	}

	public String getLabel() {
		return _label;
	}

	public void setLabel(String label) {
		if (label == null)
			throw new NullPointerException("label");
		_label = label;
	}

	public Object getValue() {
		return _value;
	}

	public void setValue(Object value) {
		_value = value;
	}

	public String getCssClass() {
		return _cssClass;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public String getX() {
		return _x;
	}

	public void setX(String _x) {
		this._x = _x;
	}

	public String getY() {
		return _y;
	}

	public void setY(String _y) {
		this._y = _y;
	}

	public String getPassengerName() {
		return _passengerName;
	}

	public void setPassengerName(String passengerName) {
		_passengerName = passengerName;
	}
	

}
