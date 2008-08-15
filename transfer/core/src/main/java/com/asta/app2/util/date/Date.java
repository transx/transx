package com.asta.app2.util.date;

/**
 * This class is used for converting jalali date to
 * Gregorian date format and vice versa.
 */

public abstract class Date {

    protected final static int[] g_days_in_month = new int[12];
    protected final static int[] j_days_in_month = new int[12];

    static {

        //initializing date of gregorian months.
        g_days_in_month[0] = 31;
        g_days_in_month[1] = 28;
        g_days_in_month[2] = 31;
        g_days_in_month[3] = 30;
        g_days_in_month[4] = 31;
        g_days_in_month[5] = 30;
        g_days_in_month[6] = 31;
        g_days_in_month[7] = 31;
        g_days_in_month[8] = 30;
        g_days_in_month[9] = 31;
        g_days_in_month[10] = 30;
        g_days_in_month[11] = 31;

        //initializing date of jalali months.
        j_days_in_month[0] = 31;
        j_days_in_month[1] = 31;
        j_days_in_month[2] = 31;
        j_days_in_month[3] = 31;
        j_days_in_month[4] = 31;
        j_days_in_month[5] = 31;
        j_days_in_month[6] = 30;
        j_days_in_month[7] = 30;
        j_days_in_month[8] = 30;
        j_days_in_month[9] = 30;
        j_days_in_month[10] = 30;
        j_days_in_month[11] = 29;

    }



    protected int year = 0;
    protected int month = 0;
    protected int day = 0;




    /**
     *
     * @return year of the date.
     */
    public int getYear() {
        return year;
    }

    /**
     * This method is used for setting year of the date.
     * @param year is year value
     */
    public void setYear(int year) {
        this.year = year;
    }


    /**
     *
     * @return month of the date.
     */
    public int getMonth() {
        return month;
    }

    /**
     * This method is used for setting month of the date.
     * @param month is month value
     */

    public void setMonth(int month) {
        this.month = month;
    }

    /**
     *
     * @return day of the date.
     */

    public int getDay() {
        return day;
    }

    /**
     * This method is used for setting month of the date.
     * @param day is day value
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * This method is used for formating value of date as a String.
     * @return a string whith formnat : yyyy-mm-dd
     */
    public String toString() {
        String result = year+"-"+month+"-"+day;
        return result;
    }


    public void setDate(Date date) {
        setDate(date.getYear() , date.getMonth() , date.getDay())  ;
    }


    /**
     * This method is used for setting a new date.
     * @param year is year
     * @param month is month
     * @param day is day
     */
    public void setDate(int year,int month,int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * This method is used for formating value of date as a String.
     * @return a string whith formnat : yyyy-mm-dd
     */
    public String getDate() {
        return toString();
    }

    public abstract java.sql.Date getSQLDate() ;

}


