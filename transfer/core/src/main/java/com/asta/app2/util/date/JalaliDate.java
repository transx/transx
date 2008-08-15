package com.asta.app2.util.date;

import java.util.StringTokenizer;


/**
 * Created by IntelliJ IDEA.
 * User: sehsanm
 * Date: Jan 9, 2005
 * Time: 5:59:28 PM
 * To change this template use Options | File Templates.
 */
public class JalaliDate extends Date{
    static int getDaysInMonthStatic(int m) {
        return j_days_in_month[m] ;
    }
    public JalaliDate() {
        GregorianDate gDate = new GregorianDate() ;
        JalaliDate tmp = JalaliDateUtil.convertToJalali(gDate)  ;
        setDate(tmp);

    }


    public JalaliDate(int year, int month, int day) {
        this.year = year ;
        this.month = month ;
        this.day = day ;
    }

    public JalaliDate(java.sql.Date date) {
        GregorianDate gDate = new GregorianDate(date) ;
        JalaliDate tmp = JalaliDateUtil.convertToJalali(gDate)  ;
        setDate(tmp);
    }

    public JalaliDate(long millisecond) {
        GregorianDate gDate = new GregorianDate(millisecond) ;
        JalaliDate tmp = JalaliDateUtil.convertToJalali(gDate)  ;
        setDate(tmp);
    }
    
    public JalaliDate(String date) {
        StringTokenizer st = new StringTokenizer(date,"-");
        year = Integer.parseInt(st.nextToken()) ;
        month = Integer.parseInt(st.nextToken()) ;
        if(st.hasMoreElements())
        	day = Integer.parseInt(st.nextToken()) ;
        else
        	day=1;
    	
    }

    /**
     * @return current jalali date with format: yyyy-mm-dd.
     */
    public static String getCurrentDate() {
        JalaliDate date = new JalaliDate();
        return date.toString();
    }

    public java.sql.Date getSQLDate() {
        return JalaliDateUtil.convertToGregorian(this).getSQLDate() ;
    }

    public static JalaliDate parseYearMonth(String requestDate) {
        StringTokenizer st = new StringTokenizer(requestDate,"-");
        try {
            int y = Integer.parseInt(st.nextToken()) ;
            int m = Integer.parseInt(st.nextToken()) ;
            return new JalaliDate(y , m , 1);
        }catch(Exception e) {
            return new JalaliDate() ;
        }

    }

    public JalaliDate previousMonth() {
        if (month == 1) {
            return new JalaliDate(year - 1 , 12 , 1);
        } else
            return new JalaliDate(year , month - 1 , 1);
    }

    public JalaliDate nextMonth() {
        if (month == 12) {
            return new JalaliDate(year + 1 , 1 , 1);
        } else
            return new JalaliDate(year , month + 1 , 1);
    }

    public int getDaysInMonth() {
    	return getDaysInMonth(year , month);  
    }
    
    public static  int getDaysInMonth(int year , int month) {
        if (isLeapYear(year) && month == 12)
            return 30 ;
        else
            return j_days_in_month[month - 1 ]  ;
    }

    private boolean isLeapYear() {
    	return isLeapYear(year) ; 
    }
    
    private static boolean isLeapYear(int year) {
        //TODO COnsider 5 years leaps
        //75 was a 5 years leap yae
        int yearDistance = year - 75 ;

        yearDistance = yearDistance % 4 ;

        if (yearDistance == 0)
            return true ;
        return false ;
    	
    }
    
    public JalaliDate nextDay() {
    	return seek(1); 
    }
    
    public JalaliDate previousDay() {
    	return seek(-1)  ; 
    }
    
    public JalaliDate seek(int dayCount) { 
 
    	int tmpDay = day ; 
    	int tmpMonth = month ;
    	int tmpYear = year ;
 
    	if (dayCount >= 0) {
	    	while(true) {
		    	if (dayCount + tmpDay <= getDaysInMonth(tmpYear , tmpMonth)) {
		    		return new JalaliDate(tmpYear , tmpMonth , dayCount + tmpDay) ; 
		    	}else {
					dayCount -= (getDaysInMonth(tmpYear , tmpMonth) - tmpDay + 1 ) ;
					tmpDay = 1 ; 
					if  (tmpMonth < 12)
						tmpMonth++ ;
					else {
						tmpMonth = 1 ; 
						tmpYear++ ; 
					}
		    	}	
	    	}
    	} else {
    		
    		dayCount = -dayCount ;
    		
    		while(true){
	    		if (tmpDay > dayCount) 
	    			return new JalaliDate(tmpYear , tmpMonth , tmpDay - dayCount) ; 
	    		else {
	    			dayCount -= tmpDay ; 
	    			if (tmpMonth > 1)
	    				tmpMonth-- ; 
	    			else {
	    				tmpMonth = 12 ; 
	    				tmpYear-- ; 
	    			}
	    			tmpDay = getDaysInMonth(tmpYear , tmpMonth) ; 
	    		}
    		}
    	}
    }
    
    public boolean equals(Object o) {
    	JalaliDate date = (JalaliDate) o ;
    	return (date.day == day && 
    			date.year == year && 
				date.month == month) ; 
    	
    }
    
    public int hashCode(){
    	return (year - 1000) * 400 + month * 30 + day ; 
    }

}
