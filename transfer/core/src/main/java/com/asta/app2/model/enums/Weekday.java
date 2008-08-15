/* *Class created on [ Jun 7, 2008 | 10:14:58 AM ] */
package com.asta.app2.model.enums;

import com.asta.app2.util.BundleUtil;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public enum Weekday {
	SATURDAY, SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;

	public String getLabel() {
		switch (this) {
		case SATURDAY:
			return BundleUtil.getMessageBundle("weekday.saturday");
		case SUNDAY:
			return BundleUtil.getMessageBundle("weekday.sunday");
		case MONDAY:
			return BundleUtil.getMessageBundle("weekday.monday");
		case TUESDAY:
			return BundleUtil.getMessageBundle("weekday.tuesday");
		case WEDNESDAY:
			return BundleUtil.getMessageBundle("weekday.wednesday");
		case THURSDAY:
			return BundleUtil.getMessageBundle("weekday.thursday");
		case FRIDAY:
			return BundleUtil.getMessageBundle("weekday.friday");
		}
		return "Unknown";
	}

}
