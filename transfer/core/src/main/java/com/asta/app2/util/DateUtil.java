package com.asta.app2.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;

import com.asta.app2.Constants;
import com.asta.app2.model.enums.Weekday;
import com.asta.app2.util.date.GregorianDate;
import com.asta.app2.util.date.JalaliDate;
import com.asta.app2.util.date.JalaliDateUtil;


/**
 * Date Utility Class used to convert Strings to Dates and Timestamps
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *  Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a> 
 *  to correct time pattern. Minutes should be mm not MM (MM is month). 
 */
public class DateUtil {
    private static Log log = LogFactory.getLog(DateUtil.class);
    private static final String TIME_PATTERN = "HH:mm";

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private DateUtil() {
    }

    /**
     * Return default datePattern (MM/dd/yyyy)
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        Locale locale = LocaleContextHolder.getLocale();
        String defaultDatePattern;
        try {
            defaultDatePattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY, locale)
                .getString("date.format");
        } catch (MissingResourceException mse) {
            defaultDatePattern = "MM/dd/yyyy";
        }

        return defaultDatePattern;
    }

    public static String getDateTimePattern() {
    	return DateUtil.getDatePattern() + " HH:mm:ss.S";
    }

	private static String getDefaultDateSystem() {
		return BundleUtil.getMessageBundle("date.system.default");
	}
	
    /**
     * This method attempts to convert an Oracle-formatted date
     * in the form dd-MMM-yyyy to mm/dd/yyyy.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static String getDate(Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @see java.text.SimpleDateFormat
     * @throws ParseException when String doesn't match the expected format
     */
    public static Date convertStringToDate(String aMask, String strDate)
      throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format:
     * MM/dd/yyyy HH:MM a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(TIME_PATTERN, theTime);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     * 
     * @return the current date
     * @throws ParseException when String doesn't match the expected format
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * This method generates a string representation of a date's date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * 
     * @see java.text.SimpleDateFormat
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     * 
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    /**
     * This method converts a String to a date using the datePattern
     * 
     * @param strDate the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * @throws ParseException when String doesn't match the expected format
     */
    public static Date convertStringToDate(String strDate)
      throws ParseException {
        Date aDate = null;

        try {
            if (log.isDebugEnabled()) {
                log.debug("converting date with pattern: " + getDatePattern());
            }

            aDate = convertStringToDate(getDatePattern(), strDate);
        } catch (ParseException pe) {
            log.error("Could not convert '" + strDate + "' to a date, throwing exception");
            pe.printStackTrace();
            throw new ParseException(pe.getMessage(),
                                     pe.getErrorOffset());
        }

        return aDate;
    }

	private static String getUserCalenderType() {
		//String type = (String) SecurityContextUtils.getCurrentUser().getProfile().get(Constants.DATE_SYSTEM);
		String type = null;
		if (type == null) {
			type = getDefaultDateSystem();
		}
		return type;
	}
	
	public static boolean isUserCalendarJalali() {
		return Constants.JALALI_DATE.equals(getUserCalenderType());
	}

	public static String getLocaleFormattedDateTime(Date date) {
		if (date == null)
		return null;
		String system = getUserCalenderType();
		if (Constants.GERIGORIAN_DATE.equals(system)){
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern(getDateTimePattern());
			return sdf.format(date);
		}else{
//			SimpleDateFormat sdf = new SimpleDateFormat();
//			sdf.applyPattern(getDateTimePattern());
//			return sdf.format(getJalaliDate(date));
			
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern("HH:mm:ss");
			String s = sdf.format(date);
			
			GregorianDate gd = new GregorianDate(new java.sql.Date(date.getTime()));
			JalaliDate localDate = JalaliDateUtil.convertToJalali(gd);
			
			return localDate.getYear() + "/" + localDate.getMonth() + "/" + localDate.getDay() + " " + s;
		}
	}
	
	public static String getLocaleFormattedDate(Date date) {
		if (date == null)
			return null;
		String system = getUserCalenderType();
		if (Constants.GERIGORIAN_DATE.equals(system)){
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern(getDateTimePattern());
			return sdf.format(date);
		}else{
//			SimpleDateFormat sdf = new SimpleDateFormat();
//			sdf.applyPattern(getDateTimePattern());
//			return sdf.format(getJalaliDate(date));
			
//			SimpleDateFormat sdf = new SimpleDateFormat();
//			sdf.applyPattern("HH:mm:ss");
//			String s = sdf.format(date);
			
			GregorianDate gd = new GregorianDate(new java.sql.Date(date.getTime()));
			JalaliDate localDate = JalaliDateUtil.convertToJalali(gd);
			
			return localDate.getYear() + "/" + localDate.getMonth() + "/" + localDate.getDay();// + " " + s;
		}
	}

	public static Date convertJalaliDate(String date, String separator) {
		if (!StringUtils.hasText(date))
			return null;
		if (!StringUtils.hasText(separator)) {
			separator = "-";
		}
    	JalaliDate startFromJd = new JalaliDate(date.replace(separator, "-"));
    	GregorianDate gd = JalaliDateUtil.convertToGregorian(startFromJd);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    	try {
			return sdf.parse(gd.getYear() + "/" + gd.getMonth() + "/" + gd.getDay());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getHourMinute(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("HH:mm");
		return sdf.format(date);
	}
	
	//calculate the days between to Date
	public static int getDays(GregorianCalendar g1, GregorianCalendar g2) {
		int elapsed = 0;
		GregorianCalendar gc1, gc2;
		if (g2.after(g1)) {
			gc1 = (GregorianCalendar) g1.clone();
			gc2 = (GregorianCalendar) g2.clone();
		} else {
			gc1 = (GregorianCalendar) g2.clone();
			gc2 = (GregorianCalendar) g1.clone();
		}
		gc1.clear(Calendar.MILLISECOND);
		gc1.clear(Calendar.SECOND);
		gc1.clear(Calendar.MINUTE);
		gc1.clear(Calendar.HOUR_OF_DAY);
		gc2.clear(Calendar.MILLISECOND);
		gc2.clear(Calendar.SECOND);
		gc2.clear(Calendar.MINUTE);
		gc2.clear(Calendar.HOUR_OF_DAY);
		while (gc1.before(gc2)) {
			gc1.add(Calendar.DATE, 1);
			elapsed++;
		}
		return elapsed;
	}
	
	public static Weekday getWeekday(Date date){
		GregorianCalendar gcalender = new GregorianCalendar();
		gcalender.setTime(date);
		Weekday weekday;
		switch(gcalender.get(Calendar.DAY_OF_WEEK)){
			case Calendar.SUNDAY :
				weekday = Weekday.SUNDAY;
				break;
			case Calendar.MONDAY :
				weekday = Weekday.MONDAY;
				break;
			case Calendar.TUESDAY : 
				weekday = Weekday.TUESDAY;
				break;
			case Calendar.WEDNESDAY : 
				weekday = Weekday.WEDNESDAY;
				break;
			case Calendar.THURSDAY :
				weekday = Weekday.THURSDAY;
				break;
			case Calendar.FRIDAY : 
				weekday = Weekday.FRIDAY;
				break;
			case Calendar.SATURDAY :
				weekday = Weekday.SATURDAY;
				break;
			default :
				weekday = Weekday.SATURDAY;
		}
		return weekday;
	}
	
	public static Date getDateZone(Date date){
		GregorianCalendar gCalender = new GregorianCalendar();
		gCalender.setTime(date);
		TimeZone zone = TimeZone.getTimeZone("Asia/Tehran");
		gCalender.setTimeZone(zone);
		System.out.println(gCalender.getTime().toGMTString());
		System.out.println(gCalender.getTime());
		return gCalender.getTime();
	}
	
}
