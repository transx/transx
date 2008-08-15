package com.asta.app2.util.date;

//import ir.asta.mofa.helpers.Constants;
//import ir.asta.mofa.util.log.Log;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.asta.app2.util.LocaleUtil;

/**
 * @author sehsanm A utility class for working with date objects.
 */

public class JalaliDateUtil {

	public static final int SATURDAY = 1;

	public static final int SUNDAY = 2;

	public static final int MONDAY = 3;

	public static final int TUESDAY = 4;

	public static final int WEDNESDAY = 5;

	public static final int THURDAY = 6;

	public static final int FRIDAY = 7;

	public static final String SEP = "-";

	public static final String JALALI_SEP = "/";

	public static String correctDateView(String date) {
		if (date == null || date.length() <= 0 || !LocaleUtil.isLocaleFarsi()) {
			return date;
		} else {
			return correctJalaliDateView(date);
		}
	}

	public static String correctJalaliDateView(String date) {
		if (date == null || date.length() <= 0) {
			return date;
		}
		String s1, s2, s3;
		int sep1Index = date.indexOf(SEP);
		int sep2Index = date.lastIndexOf(SEP);
		s1 = date.substring(0, sep1Index);
		s2 = date.substring(sep1Index + 1, sep2Index);
		s3 = date.substring(sep2Index + 1, date.length());
		// Log.debug(date);
		// Log.debug(s1+ " " + s2+ " " + s3+ " ");
		return (s3 + JALALI_SEP + s2 + JALALI_SEP + s1);
		// return date;
	}

	/**
	 * Finds the joulian date of a specified date.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return joulin date of specified gregorian date.
	 */
	public static double getJulianDate(int year, int month, int day) {

		int y = year;
		int m = month;
		if (m <= 2) {
			m += 12;
			y--;
		}
		int a = (y / 100);
		int b = (2 - a) + (a / 4);

		double JD = ((int) (365.25 * y)) + ((int) (30.6001 * (m + 1))) + day
				+ 0.5 + 1720994.5 + b;

		return JD;
	}

	public static double getJulianDate(GregorianDate date) {
		return getJulianDate(date.getYear(), date.getMonth(), date.getDay());

	}

	/**
	 * Finds number of days between two dates.
	 * 
	 * @param start
	 * @param end
	 * @return Number of days between start and end.
	 */
	public static long getDistance(GregorianDate start, GregorianDate end) {
		double sJD = getJulianDate(start);
		double eJD = getJulianDate(end);

		return ((long) (sJD - eJD));
	}

	/**
	 * Return day of week of specified date. Note that staurday is 1 and friday
	 * is 7.
	 * 
	 * @param date
	 * @return number between 1..7 which 1 is saturday and 7 is friday.
	 */
	public static int getDayofWeek(GregorianDate date) {
		// 2005-05-07 is saturday
		GregorianDate base = new GregorianDate(2005, 5, 7);
		long distance = getDistance(date, base);

		// Log.info("Distance is " + distance);
		distance = distance % 7;
		if (distance < 0)
			distance += 7;

		distance++;

		return (int) distance;
	}

	public static int getDayofWeek(JalaliDate date) {
		return getDayofWeek(convertToGregorian(date));
	}

	/**
	 * Return day of week of specified date. Note that staurday is 1 and friday
	 * is 7.
	 * 
	 * @return number between 1..7 which 1 is saturday and 7 is friday.
	 * @param year
	 * @param month
	 * @param day
	 */
	public static int getDayofWeek(int year, int month, int day) {
		return getDayofWeek(new GregorianDate(year, month, day));
	}

	/**
	 * This method is used for converting gregorian date to jalali.
	 */

	public static JalaliDate convertToJalali(GregorianDate date) {
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDay();

		int g_y = year;
		int g_m = month;
		int g_d = day;

		int gy, gm, gd;
		int jy, jm, jd;
		long g_day_no, j_day_no;
		int j_np;

		int i;

		gy = g_y - 1600;
		gm = g_m - 1;
		gd = g_d - 1;

		g_day_no = 365 * gy + (gy + 3) / 4 - (gy + 99) / 100 + (gy + 399) / 400;
		for (i = 0; i < gm; ++i)
			g_day_no += GregorianDate.getDaysInMonthStatic(i);
		if (gm > 1 && ((gy % 4 == 0 && gy % 100 != 0) || (gy % 400 == 0)))
			/* leap and after Feb */
			++g_day_no;

		g_day_no += gd;

		j_day_no = g_day_no - 79;

		j_np = (int) (j_day_no / 12053);
		j_day_no %= 12053;

		jy = (int) (979 + 33 * j_np + 4 * (j_day_no / 1461));
		j_day_no %= 1461;

		if (j_day_no >= 366) {
			jy += (j_day_no - 1) / 365;
			j_day_no = (j_day_no - 1) % 365;
		}

		for (i = 0; i < 11 && j_day_no >= JalaliDate.getDaysInMonthStatic(i); ++i) {
			j_day_no -= JalaliDate.getDaysInMonthStatic(i);
		}

		jm = i + 1;
		jd = (int) (j_day_no + 1);
		year = jy;
		month = jm;
		day = jd;

		return new JalaliDate(year, month, day);
	}

	/**
	 * This method converts JalaliDate to gregorian date.
	 * 
	 * @param date
	 */
	public static GregorianDate convertToGregorian(JalaliDate date) {
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDay();

		int j_y = year;
		int j_m = month;
		int j_d = day;

		int gy, gm, gd;
		int jy, jm, jd;
		long g_day_no, j_day_no;
		int leap;

		int i;

		jy = j_y - 979;
		jm = j_m - 1;
		jd = j_d - 1;

		j_day_no = 365 * jy + (jy / 33) * 8 + (jy % 33 + 3) / 4;
		for (i = 0; i < jm; ++i)
			j_day_no += JalaliDate.getDaysInMonthStatic(i);

		j_day_no += jd;

		g_day_no = j_day_no + 79;

		gy = (int) (1600 + 400 * (g_day_no / 146097));
		g_day_no = g_day_no % 146097;

		leap = 1;
		if (g_day_no >= 36525) {
			g_day_no--;
			gy += 100 * (g_day_no / 36524);
			g_day_no = g_day_no % 36524;

			if (g_day_no >= 365)
				g_day_no++;
			else
				leap = 0;
		}

		gy += 4 * (g_day_no / 1461);
		g_day_no %= 1461;

		if (g_day_no >= 366) {
			leap = 0;

			g_day_no--;
			gy += g_day_no / 365;
			g_day_no = g_day_no % 365;
		}

		int append = 0;
		for (i = 0; g_day_no >= GregorianDate.getDaysInMonthStatic(i) + append; i++) {
			if ((i == 1) && (leap != 0))
				append = 1;
			else
				append = 0;

			g_day_no -= GregorianDate.getDaysInMonthStatic(i) + append;
		}

		gm = i + 1;
		gd = (int) (g_day_no + 1);

		year = gy;
		month = gm;
		day = gd;

		return new GregorianDate(year, month, day);
	}

	public static boolean isHoliday(JalaliDate jDate) {
		if (getDayofWeek(convertToGregorian(jDate)) == 7)
			return true;
		return false;

	}

	/**
	 * Finds the dats between two dates.
	 * 
	 * @param start
	 *            Start date.
	 * @param end
	 *            End date.
	 * @return A List containng days between start and end. Including start and
	 *         end. If end is before start It will return an empty list.
	 */
	public static List getDaysInInterval(JalaliDate start, JalaliDate end) {
		// check if it is before.
		GregorianDate gStart = convertToGregorian(start);
		GregorianDate gEnd =  convertToGregorian(end);
		long dist =  getDistance(gEnd, gStart);

		if (dist < 0)
			return new ArrayList();
		List ret = new ArrayList();
		JalaliDate tmp = start;
		while (!tmp.equals(end)) {
			// TODO check holidays.
			ret.add(tmp);
			tmp = tmp.nextDay();
		}

		ret.add(tmp);
		return ret;

	}

	public static JalaliDate startOfWeek(JalaliDate date) {
		JalaliDate tmp = new JalaliDate(date.getYear(), date.getMonth(), date
				.getDay());
		while (getDayofWeek(tmp) != SATURDAY)
			tmp = tmp.previousDay();
		return tmp;
	}

	public static JalaliDate[][] getDays(JalaliDate startDate, int weekCount) {
		JalaliDate[][] date = new JalaliDate[6][weekCount];

		startDate = startOfWeek(startDate);

		for (int i = 0; i < 6; i++) {

			for (int d = 0; d < weekCount; d++) {
				date[i][d] = startDate.seek(d * 7);
			}

			startDate = startDate.nextDay();
		}

		return date;
	}

	public static JalaliDate endOfWeek(JalaliDate date) {
		JalaliDate tmp = new JalaliDate(date.getYear(), date.getMonth(), date
				.getDay());
		if (getDayofWeek(tmp) == FRIDAY)
			tmp = tmp.previousDay();
		while (getDayofWeek(tmp) != THURDAY)
			tmp = tmp.nextDay();
		return tmp;

	}

	public static boolean validateBefor(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			return false;
		return d1.compareTo(d2) >= 0;
	}

}
