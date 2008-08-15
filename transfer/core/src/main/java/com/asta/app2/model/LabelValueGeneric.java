package com.asta.app2.model;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A simple JavaBean to represent label-value pairs. This is most commonly used
 * when constructing user interface elements which have a label to be displayed
 * to the user, and a corresponding value to be returned to the server. One
 * example is the <code>&lt;html:options&gt;</code> tag.
 * 
 * <p>
 * Note: this class has a natural ordering that is inconsistent with equals.
 * <p>
 * This class supports values of generic type (labels are still strings
 * 
 * @see org.apache.struts.util.LabelValueBean
 */
public class LabelValueGeneric<S> implements Comparable<LabelValueGeneric<S>>,
		Serializable {

	private static final long serialVersionUID = 3689355407466181430L;

	/**
	 * Comparator that can be used for a case insensitive sort of
	 * <code>LabelValueGeneric</code> objects.
	 */
	public static final Comparator<Object> CASE_INSENSITIVE_ORDER = new Comparator<Object>() {
		@SuppressWarnings("unchecked")
		public int compare(Object o1, Object o2) {
			String label1 = ((LabelValueGeneric) o1).getLabel();
			String label2 = ((LabelValueGeneric) o2).getLabel();
			return label1.compareToIgnoreCase(label2);
		}
	};

	// ----------------------------------------------------------- Constructors

	/**
	 * Default constructor.
	 */
	public LabelValueGeneric() {
		super();
	}

	/**
	 * Construct an instance with the supplied property values.
	 * 
	 * @param label
	 *            The label to be displayed to the user.
	 * @param value
	 *            The value to be returned to the server.
	 */
	public LabelValueGeneric(final String label, final S value) {
		this.label = label;
		this.value = value;
	}

	// ------------------------------------------------------------- Properties

	/**
	 * The property which supplies the option label visible to the end user.
	 */
	private String label;

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * The property which supplies the value returned to the server.
	 */
	private S value;

	public S getValue() {
		return this.value;
	}

	public void setValue(S value) {
		this.value = value;
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Compare LabelValueBeans based on the label, because that's the human
	 * viewable part of the object.
	 * 
	 * @see Comparable
	 * @param o
	 *            LabelValue object to compare to
	 * @return 0 if labels match for compared objects
	 */
	public int compareTo(LabelValueGeneric<S> o) {
		// Implicitly tests for the correct type, throwing
		// ClassCastException as required by interface
		String otherLabel = o.getLabel();

		return this.getLabel().compareTo(otherLabel);
	}

	/**
	 * Return a string representation of this object.
	 * 
	 * @return object as a string
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("LabelValue[");
		sb.append(this.label);
		sb.append(", ");
		sb.append(this.value.toString());
		sb.append("]");
		return (sb.toString());
	}

	/**
	 * LabelValueBeans are equal if their values are both null or equal.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param obj
	 *            object to compare to
	 * @return true/false based on whether values match or not
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof LabelValueGeneric)) {
			return false;
		}

		LabelValueGeneric bean = (LabelValueGeneric) obj;
		int nil = (this.getValue() == null) ? 1 : 0;
		nil += (bean.getValue() == null) ? 1 : 0;

		if (nil == 2) {
			return true;
		} else if (nil == 1) {
			return false;
		} else {
			return this.getValue().equals(bean.getValue());
		}

	}

	/**
	 * The hash code is based on the object's value.
	 * 
	 * @see java.lang.Object#hashCode()
	 * @return hashCode
	 */
	public int hashCode() {
		return (this.getValue() == null) ? 17 : this.getValue().hashCode();
	}
}