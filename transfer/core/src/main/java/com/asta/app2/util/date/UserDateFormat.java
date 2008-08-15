package com.asta.app2.util.date;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import com.asta.app2.util.DateUtil;
public class UserDateFormat extends SimpleDateFormat {
	private static final long serialVersionUID = 1L;
	
	public UserDateFormat() {
		super();
		pattern = null;
	}
	
	private final String pattern;
	
	public UserDateFormat(String pattern) {
		super(pattern);
		this.pattern = pattern;
	}
	
	@Override
	public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {
		if (DateUtil.isUserCalendarJalali()) {
			if (toAppendTo == null)
				toAppendTo = new StringBuffer();
			toAppendTo.append(jalaliFormat(date, toAppendTo, pos));
			return toAppendTo;
		} else
			return super.format(date, toAppendTo, pos);
	}
	

	String[] monthNames = {"Farvardin", "Ordibehesht", "Khordad", 
			"Tir", "Mordad", "Shahrivar", 
			"Mehr", "Aban", "Azar", 
			"Dey", "Bahman", "Esfand"};
	
	
	private String jalaliFormat(Date d, StringBuffer toAppendTo, FieldPosition pos) {
		if (d == null)
			return "null";
		
		if ("HH:mm:ss.SSS".equals(pattern) || "HH:mm:ss".equals(pattern) || "HH:mm".equals(pattern) ) {
			return super.format(d, toAppendTo , pos).toString();
		} else { 
			JalaliDate jalali = new JalaliDate(d.getTime());
			int day = jalali.getDay();
			int month = jalali.getMonth() - 1;
			int year = jalali.getYear();
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			int min = calendar.get(Calendar.MINUTE);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			if (pattern == "d-MMM, HH:mm") {
				return day + "-" + monthNames[month] + ", " + hour + ":" + min;
			} else if (pattern == "d-MMM") {
				return day + "-" + monthNames[month];
			} else if (pattern == "MMM-yyyy") {
				return monthNames[month] + "-" + year;
			} else if (pattern == "yyyy") {
				return String.valueOf(year);
			} else {
				return super.format(d, toAppendTo , pos).toString();
			}
		}
	}
	
	
}
