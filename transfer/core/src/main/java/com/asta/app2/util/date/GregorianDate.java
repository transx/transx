package com.asta.app2.util.date;

import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: sehsanm
 * Date: Jan 9, 2005
 * Time: 5:58:59 PM
 * To change this template use Options | File Templates.
 */
public class GregorianDate extends Date{

    public static int getDaysInMonthStatic(int m) {
        return g_days_in_month[m] ;
    }

    public GregorianDate() {
        setCurrentDate();
    }
    
    
    public GregorianDate(String date) {
    	this(java.sql.Date.valueOf(date));
//    	if(date!=null&&date.equals("")&& date.indexOf("-")>0){
//	        StringTokenizer st = new StringTokenizer(date,"-");
//	        year = Integer.parseInt(st.nextToken()) ;
//	        month = Integer.parseInt(st.nextToken()) ;
//	        day = Integer.parseInt(st.nextToken()) ;
//    	}
    }

    public GregorianDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public GregorianDate(java.sql.Date date) {
        setDate(date);
    }

    public GregorianDate(long millisecond) {
        setDate(new java.sql.Date(millisecond));
    }

    /**
     * @return current gregorian date with format: yyyy-mm-dd.
     */
    public static String getCurrentDate() {
        String date = new java.sql.Date(System.currentTimeMillis()).toString();
        return date;
    }

    /**
     * This method is used for setting value of current
     * gregorian date.
     */
    public void setCurrentDate() {
        String date = new java.sql.Date(System.currentTimeMillis()).toString();
        StringTokenizer tokenizer = new StringTokenizer(date,"-");
        year = Integer.parseInt(tokenizer.nextToken());
        month = Integer.parseInt(tokenizer.nextToken());
        day = Integer.parseInt(tokenizer.nextToken());
    }


    /**
     * This method is used for setting the date with object of
     * java.sql.Date class.
     * @param sqlDate is an object of java.sql.Date.
     */
    public void setDate(java.sql.Date sqlDate) {
        String date = sqlDate.toString();
        StringTokenizer tokenizer = new StringTokenizer(date,"-");
        year = Integer.parseInt(tokenizer.nextToken());
        month = Integer.parseInt(tokenizer.nextToken());
        day = Integer.parseInt(tokenizer.nextToken());
    }

    /**
     * This method returns the date as instance of java.sql.Date.
     * @return the date as instance of java.sql.Date.
     */
    public java.sql.Date getSQLDate() {
        return java.sql.Date.valueOf(toString());
    }

	public static GregorianDate parseYearMonth(String requestDate) {
        StringTokenizer st = new StringTokenizer(requestDate,"-");
        try {
            int y = Integer.parseInt(st.nextToken()) ;
            int m = Integer.parseInt(st.nextToken()) ;
            return new GregorianDate(y , m , 1);
        }catch(Exception e) {
            return new GregorianDate() ;
        }
	}

	public Object nextMonth() {
		if (month == 12)
			return new GregorianDate(year + 1 , 1 , 1);
		return new GregorianDate(year , month + 1 , 1); 
	}

	public Object previousMonth() {
		if (month == 1)
			return new GregorianDate(year - 1 , 12 ,1) ; 
		return new GregorianDate(year , month - 1 , 1); 
	}

}
