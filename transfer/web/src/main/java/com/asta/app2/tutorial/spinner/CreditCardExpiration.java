/* *Class created on [ Jun 16, 2008 | 3:14:13 PM ] */
package com.asta.app2.tutorial.spinner;

import javax.faces.event.ValueChangeEvent;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CreditCardExpiration {
	private int month = 1;
	private int year = 2000;
	private int changes = 0;

	// PROPERTY: month
	public int getMonth() {
		return month;
	}

	public void setMonth(int newValue) {
		month = newValue;
	}

	// PROPERTY: year
	public int getYear() {
		return year;
	}

	public void setYear(int newValue) {
		year = newValue;
	}

	// PROPERTY: changes
	public int getChanges() {
		return changes;
	}

	public void changeListener(ValueChangeEvent e) {
		// we convert to strings because oldValue is a String, newValue is an
		// Integer
		if (!e.getNewValue().toString().equals(e.getOldValue()))
			changes++;
	}
}
