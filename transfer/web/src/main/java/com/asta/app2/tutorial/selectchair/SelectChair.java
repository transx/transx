/* *Class created on [ Jun 21, 2008 | 6:40:50 PM ] */
package com.asta.app2.tutorial.selectchair;

import java.io.Serializable;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SelectChair implements Serializable {

	private static final long serialVersionUID = 2555045722731866958L;

	// FIELDS
	private Object _value;
	private String _label;
	private String _cssClass;
	private boolean _disabled;
	private String _x;
	private String _y;
	private String _passengerName;

	// CONSTRUCTORS
	public SelectChair() {
	}

	public SelectChair(Object value) {
		_value = value;
		_label = value == null ? null : value.toString();
		_cssClass = null;
		_disabled = false;
		_x = null;
		_y = null;
		_passengerName = null;
	}

	public SelectChair(Object value, String label) {
		_value = value;
		_label = label;
		_cssClass = null;
		_disabled = false;
		_x = null;
		_y = null;
		_passengerName = null;
	}

	public SelectChair(Object value, String label, String cssClass) {
		_value = value;
		_label = label;
		_cssClass = cssClass;
		_disabled = false;
		_x = null;
		_y = null;
		_passengerName = null;
	}

	public SelectChair(Object value, String label, String cssClass,
			boolean disabled) {
		_value = value;
		_label = label;
		_cssClass = cssClass;
		_disabled = disabled;
		_x = null;
		_y = null;
		_passengerName = null;
	}

	public SelectChair(Object value, String label, boolean disabled,
			String cssClass, String x, String y) {
		_value = value;
		_label = label;
		_cssClass = cssClass;
		_disabled = disabled;
		_x = x;
		_y = y;
		_passengerName = null;
	}

	public SelectChair(Object value, String label, boolean disabled,
			String cssClass, String x, String y, String passengerName) {
		_value = value;
		_label = label;
		_cssClass = cssClass;
		_disabled = disabled;
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
