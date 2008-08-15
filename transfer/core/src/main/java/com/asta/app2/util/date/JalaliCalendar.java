package com.asta.app2.util.date;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;


public class JalaliCalendar extends Calendar {
	
	public final static int LONG = 2;
	
    public final static int FARVARDIN = 0;
    public final static int ORDIBEHESHT = 1;
    public final static int KHORDAD = 2;
    public final static int TIR = 3;
    public final static int MORDAD = 4;
    public final static int SHAHRIVAR = 5;
    public final static int MEHR = 6;
    public final static int ABAN = 7;
    public final static int AZAR = 8; 
    public final static int DEY = 9;
    public final static int BAHMAN = 10;
    public final static int ESFAND = 11;
    
    private static final int  ONE_SECOND = 1000;
    private static final int  ONE_MINUTE = 60*ONE_SECOND;
    private static final int  ONE_HOUR   = 60*ONE_MINUTE;
    private static final long ONE_DAY    = 24*ONE_HOUR;
    private static final long ONE_WEEK   = 7*ONE_DAY;
    
    static final int MIN_VALUES[] = {
    0,	// ERA
	1,		// YEAR
	FARVARDIN,	// MONTH
	1,		// WEEK_OF_YEAR
	0,		// WEEK_OF_MONTH
	1,		// DAY_OF_MONTH
	1,		// DAY_OF_YEAR
	SATURDAY,		// DAY_OF_WEEK
	1,		// DAY_OF_WEEK_IN_MONTH
	AM,		// AM_PM
	0,		// HOUR
	0,		// HOUR_OF_DAY
	0,		// MINUTE
	0,		// SECOND
	0,		// MILLISECOND
	-13*ONE_HOUR,	// ZONE_OFFSET (UNIX compatibility)
	0		// DST_OFFSET
    };
    
    static final int MAX_VALUES[] = {
    1,		// ERA
	292278994,	// YEAR
	ESFAND,	// MONTH
	53,		// WEEK_OF_YEAR
	6,		// WEEK_OF_MONTH
	31,		// DAY_OF_MONTH
	366,	// DAY_OF_YEAR
	FRIDAY,		// DAY_OF_WEEK
	6,		// DAY_OF_WEEK_IN_MONTH
	
	PM,		// AM_PM
	11,		// HOUR
	23,		// HOUR_OF_DAY
	59,		// MINUTE
	59,		// SECOND
	999,	// MILLISECOND
	14*ONE_HOUR,	// ZONE_OFFSET
	2*ONE_HOUR	// DST_OFFSET (double summer time)
    };
    
    static final int LEAST_MAX_VALUES[] = {
    1,		// ERA
	292269054,	// YEAR
	ESFAND,	// MONTH
	52,		// WEEK_OF_YEAR
	4,		// WEEK_OF_MONTH
	29,		// DAY_OF_MONTH
	365,		// DAY_OF_YEAR
	FRIDAY,	// DAY_OF_WEEK
	4,		// DAY_OF_WEEK_IN_MONTH
	PM,		// AM_PM
	11,		// HOUR
	23,		// HOUR_OF_DAY
	59,		// MINUTE
	59,		// SECOND
	999,		// MILLISECOND
	14*ONE_HOUR,	// ZONE_OFFSET
	20*ONE_MINUTE	// DST_OFFSET (historical least maximum)
    };
    
    
    
    private  int era;
    private  int year;
    private  int month;
    private  int date;// date = dayOfMonth
    private  int dayOfMonth;
    private  int dayOfYear;
    private  int dayOfWeek;
    private  int weekOfYear;
    private  int weekOfMonth;
    private  int dayOfWeekInMonth;
    
    public GregorianCalendar thisGc;
	public GregorianCalendar gc;
	public GregorianDate gd;
	public JalaliDate jd;

	
	public JalaliCalendar() {
		thisGc = new GregorianCalendar();
		thisGc.setFirstDayOfWeek(7);
		thisGc.setMinimalDaysInFirstWeek(1);
		gd = new GregorianDate(thisGc.get(YEAR),thisGc.get(MONTH)+1,thisGc.get(DAY_OF_MONTH));
		jd=convertToJalali(gd);
		computeJalaliCalendarFields(jd.getYear(), jd.getMonth()-1, jd.getDay());
	}
	//The first day of month is 1
	 // The first month of the year is 0 
	 //
	public JalaliCalendar(int year, int month, int dayOfMonth){
		jd = new JalaliDate(year,month+1,dayOfMonth);
		gd = convertToGregorian(jd);
		thisGc = new GregorianCalendar(gd.getYear(),gd.getMonth()-1,gd.getDay());
		thisGc.setFirstDayOfWeek(7);
		thisGc.setMinimalDaysInFirstWeek(1);
		gd = new GregorianDate(thisGc.get(YEAR),thisGc.get(MONTH)+1,thisGc.get(DAY_OF_MONTH));
		jd=convertToJalali(gd);
		computeJalaliCalendarFields(jd.getYear(), jd.getMonth()-1, jd.getDay());
	}
	
	@Override
	public int get(int field) {
		switch (field) {
		case ERA:
			return this.getEra();
		case YEAR:
			return this.getYear();
		case MONTH:
			return this.getMonth();
		case DATE | DAY_OF_MONTH :
			return this.getDayOfMonth();
		case DAY_OF_YEAR :
			return this.getDayOfYear();
		case DAY_OF_WEEK:
			return this.getDayOfWeek();
		case WEEK_OF_YEAR:
			return this.getWeekOfYear();
		case WEEK_OF_MONTH:
			return this.getWeekOfMonth();
		case DAY_OF_WEEK_IN_MONTH:
			return this.getDayOfWeekInMonth();
		case AM_PM:
			return thisGc.get(AM_PM);
		case HOUR:
			return thisGc.get(HOUR);
		case HOUR_OF_DAY:
			return thisGc.get(HOUR_OF_DAY);
		case MINUTE:
			return thisGc.get(MINUTE);
		case SECOND:
			return thisGc.get(SECOND);
		case MILLISECOND:
			return thisGc.get(MILLISECOND);
		default:
			throw new IllegalArgumentException();
		}

	}

	@Override
	public void set(int field, int value) {
		switch (field) {
		case ERA:
			this.setEra(value);
			break;
		case YEAR:
			gd = convertToGregorian(new JalaliDate(value,month+1,dayOfMonth));
			thisGc.set(gd.getYear(), gd.getMonth()-1, gd.getDay());
			gd = new GregorianDate(thisGc.get(YEAR),thisGc.get(MONTH)+1,thisGc.get(DAY_OF_MONTH));
			jd = convertToJalali(gd);
			computeJalaliCalendarFields(jd.getYear(), jd.getMonth()-1, jd.getDay());
			break;
		case MONTH:
			gd = convertToGregorian(new JalaliDate(year,value+1,dayOfMonth));
			thisGc.set(gd.getYear(), gd.getMonth()-1, gd.getDay());
			gd = new GregorianDate(thisGc.get(YEAR),thisGc.get(MONTH)+1,thisGc.get(DAY_OF_MONTH));
			jd = convertToJalali(gd);
			computeJalaliCalendarFields(jd.getYear(), jd.getMonth()-1, jd.getDay());
			break;
		case DATE | DAY_OF_MONTH :
			gd = convertToGregorian(new JalaliDate(year,month+1,dayOfMonth));
			int disDOM = value - this.get(DAY_OF_MONTH);//distance between current dayOfMonth and new day
			thisGc.set(gd.getYear(),gd.getMonth()-1,gd.getDay() + disDOM);
			gd = new GregorianDate(thisGc.get(YEAR),thisGc.get(MONTH)+1,thisGc.get(DAY_OF_MONTH));
			jd = convertToJalali(gd);
			computeJalaliCalendarFields(jd.getYear(), jd.getMonth()-1, jd.getDay());
			break;
		case DAY_OF_YEAR :
			gd = convertToGregorian(new JalaliDate(year,month+1,dayOfMonth));
			int disDOY = value - this.get(DAY_OF_YEAR);//distance between current dayOfYear and new day
			thisGc.set(gd.getYear(),gd.getMonth()-1,gd.getDay() + disDOY);
			gd = new GregorianDate(thisGc.get(YEAR),thisGc.get(MONTH)+1,thisGc.get(DAY_OF_MONTH));
			jd = convertToJalali(gd);
			computeJalaliCalendarFields(jd.getYear(), jd.getMonth()-1, jd.getDay());
			break;
		case DAY_OF_WEEK:
			gd = convertToGregorian(new JalaliDate(year,month+1,dayOfMonth));
			int disDOW;//distance between current dayOfWeek and new day
			
			value = value % 7;
			if(value == 0)
				value = 7;
			
			switch (this.get(DAY_OF_WEEK)) {
			case 7:
				if (value == 7) {
					disDOW = 0;
				}else {
					disDOW = value; 
				}
				break;
			default:
				if(value == 7)
					disDOW = - dayOfWeek;
				else
					disDOW = value - dayOfWeek;
				break;
			}
			thisGc.set(gd.getYear(),gd.getMonth()-1,gd.getDay() + disDOW);
			gd = new GregorianDate(thisGc.get(YEAR),thisGc.get(MONTH)+1,thisGc.get(DAY_OF_MONTH));
			jd = convertToJalali(gd);
			computeJalaliCalendarFields(jd.getYear(), jd.getMonth()-1, jd.getDay());
			break;
		case WEEK_OF_YEAR:
			gd = convertToGregorian(new JalaliDate(year,month+1,dayOfMonth));
			int disWOY = value - weekOfYear;//distance between current weekOfYear and new week
			thisGc.set(gd.getYear(),gd.getMonth()-1,gd.getDay() + disWOY * 7);
			gd = new GregorianDate(thisGc.get(YEAR),thisGc.get(MONTH)+1,thisGc.get(DAY_OF_MONTH));
			jd = convertToJalali(gd);
			computeJalaliCalendarFields(jd.getYear(), jd.getMonth()-1, jd.getDay());
			break;
		case WEEK_OF_MONTH:
			gd = convertToGregorian(new JalaliDate(year,month+1,dayOfMonth));
			int disWOM = value - weekOfMonth;//distance between current weekOfMonth and new week
			thisGc.set(gd.getYear(),gd.getMonth()-1,gd.getDay() + disWOM * 7);
			gd = new GregorianDate(thisGc.get(YEAR),thisGc.get(MONTH)+1,thisGc.get(DAY_OF_MONTH));
			jd = convertToJalali(gd);
			computeJalaliCalendarFields(jd.getYear(), jd.getMonth()-1, jd.getDay());
			break;
		//only for positive set programmed
		case DAY_OF_WEEK_IN_MONTH:
			//max(dayOfWeekInMonth) for all months = 5
			gd = convertToGregorian(new JalaliDate(year,month+1,dayOfMonth));
			
			int dOY = this.getDayOfYear();
			int value_mod5 = value % 5;
			int dweek = this.getDayOfWeek();
			
			if(value_mod5 != 0){
				if (value > 0 && value < 5) {
					dOY = dOY + (value - dayOfWeekInMonth) * 7;
				}else if (value > 0 && value > 5) {
					dOY =  dOY + j_days_in_month[this.getMonth()] - this.getDayOfMonth();
					
					dweek = (dweek + (j_days_in_month[this.getMonth()] - this.getDayOfMonth()) % 7) % 7;
					if (dweek == 0) dweek = 7;
					for(int i = 1 ; i < value/5; i++){
						dOY = dOY + j_days_in_month[this.getMonth()+i];
						dweek = (dweek + j_days_in_month[this.getMonth()+i] % 7) % 7;
						if (dweek == 0) dweek = 7;
					}
					for(int i = 1; i < (value % 5); i++){
						dOY = dOY + 7;
					}
					dweek = (dweek +1) % 7;
					if (dweek == 0) dweek = 7;
					dOY++;
					switch (dweek) {
					case 7:
						if (this.getDayOfWeek() == 7) {
							dOY = dOY + 7;
						}else {
							dOY = dOY + this.getDayOfWeek(); 
						}
						break;
					default:
						if(this.getDayOfWeek() == 7)
							dOY = dOY + 7 - dweek;
						else
							if(this.getDayOfWeek() >= dweek )
								dOY = dOY + this.getDayOfWeek() - dweek;
							else
								dOY = dOY + 7 - dweek + this.getDayOfWeek();
						break;
					}
					
				}
			}else{
				for(int i = 1; i< value/5; i++){
					if(i == 1){
						dOY = dOY + j_days_in_month[this.getMonth()+ i-1] - this.getDayOfMonth();
						dweek =  (dweek + (j_days_in_month[this.getMonth()+ i-1] - this.getDayOfMonth()) % 7) % 7;
						if (dweek == 0) dweek = 7;
					}else{
						dOY = dOY + j_days_in_month[this.getMonth()+ i-1];
						dweek = (dweek + (j_days_in_month[this.getMonth()+ i-1]) % 7) % 7;
						if (dweek == 0) dweek = 7;
					}
				}
				//for setting zero or negative value
				if(value == 0){
					
				}
				if (value != 5){
					//go to first day_of_month
					dweek = (dweek +1) % 7;
					if (dweek == 0) dweek = 7;
					dOY++;

					//go to first of 5'th week_in_month
					dOY = dOY + 28;
					switch (dweek) {
					case 7:
						if (this.getDayOfWeek() == 7) {
							dOY = dOY + 7;
						}else {
							dOY = dOY + this.getDayOfWeek(); 
						}
						break;
					default:
						if(this.getDayOfWeek() == 7)
							dOY = dOY + 7 - dweek;
						else
							if(this.getDayOfWeek() >= dweek )
								dOY = dOY + this.getDayOfWeek() - dweek;
							else
								dOY = dOY + 7 - dweek + this.getDayOfWeek();
						break;
					}
				
				}else{
					if(this.getDayOfMonth() < 29){
						//go to first of 5'th week_in_month
						dOY = dOY + 28 - this.getDayOfMonth() + 1;	
						dweek = (dweek +28 - this.getDayOfMonth() + 1) % 7;
						if(dweek == 0) dweek = 7;
						
						switch (dweek) {
						case 7:
							if (this.getDayOfWeek() == 7) {
								dOY = dOY + 7;
							}else {
								dOY = dOY + this.getDayOfWeek(); 
							}
							break;
						default:
							if(this.getDayOfWeek() == 7)
								dOY = dOY + 7 - dweek;
							else
								if(this.getDayOfWeek() >= dweek )
									dOY = dOY + this.getDayOfWeek() - dweek;
								else
									dOY = dOY + 7 - dweek + this.getDayOfWeek();
							break;
						}
					}
				}
				
			}
			this.set(DAY_OF_YEAR, dOY);
			break;
		case AM_PM:
			thisGc.set(AM_PM, value);
			break;
		case HOUR:
			thisGc.set(HOUR, value);
			break;
		case HOUR_OF_DAY:
			thisGc.set(HOUR_OF_DAY, value);
			break;
		case MINUTE:
			thisGc.set(MINUTE, value);
			break;
		case SECOND:
			thisGc.set(SECOND, value);
			break;
		case MILLISECOND:
			thisGc.set(MILLISECOND, value);
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setTimeInMillis(long millis) {
		thisGc.setTimeInMillis(millis);
		JalaliDate jd = convertToJalali(new GregorianDate(thisGc.get(YEAR),thisGc.get(MONTH)+1,thisGc.get(DAY_OF_MONTH)));
		this.set(YEAR, jd.getYear());
		this.set(MONTH, jd.getMonth()-1);
		this.set(DAY_OF_MONTH, jd.getDay());
	}

	@Override
	protected void computeFields() {
	}

	@Override
	protected void computeTime() {
	}

	protected final static int[] j_days_in_month = new int[12];
    static {

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

	protected int computeDayOfYear(JalaliCalendar jc){
		int dOY = 0; //dayOfYear
		for (int i = 0; i < jc.getMonth(); i++){
			dOY = dOY + j_days_in_month[i];
		}
		dOY = dOY + jc.getDayOfMonth();
		return dOY;
	}

	protected boolean isLeap(JalaliCalendar jc){
		gd = convertToGregorian(new JalaliDate(jc.get(YEAR),12,30));
		gc.set(gd.getYear(), gd.getMonth()-1, gd.getDay());
		gd = new GregorianDate(gc.get(YEAR),gc.get(MONTH)+1,gc.get(DAY_OF_MONTH));
		jd = convertToJalali(gd);
		if(jc.get(YEAR) != jd.getYear())
			return false;
		else
			return true;
	}
	
	protected int computeMonthLen(JalaliCalendar jc){
		int monthLen;
		
		if(jc.get(MONTH) != 11)
			monthLen = j_days_in_month[jc.get(MONTH)];
		else
			if(isLeap(jc))
				monthLen = 30;
			else
				monthLen = 29;
		
		return monthLen;
		
	}
	
	protected int computeYearLen(JalaliCalendar jc){
		int yearLen;
		
		if(isLeap(jc))
			yearLen = 366;
		else
			yearLen = 365;
		
		return yearLen;
	}
	
	protected void computeJalaliCalendarFields(int year , int month, int day){
		gc = new GregorianCalendar();//for using conversion
		
		if(year > 0)
			this.setEra(1);
		else
			this.setEra(0);
		
		this.setYear(year);
		this.setMonth(month);
		this.setDate(day);
		this.setDayOfMonth(day);
		this.setDayOfYear(computeDayOfYear(this));
		//compute dayOfWeek
		gd = convertToGregorian(new JalaliDate(year,month+1,day));
		gc.set(gd.getYear(), gd.getMonth()-1, gd.getDay());
		this.setDayOfWeek(gc.get(DAY_OF_WEEK));
		//compute weekOfYear
		gd = convertToGregorian(new JalaliDate(year,1,1));
		gc.set(gd.getYear(),gd.getMonth()-1,gd.getDay());
		int dOW = gc.get(DAY_OF_WEEK);//day_of_week
		int wOY = 1; //week_of_year
		if (dOW == 7){
			dOW = 0;
			wOY = 0;
			wOY = wOY + (int)Math.ceil((double)(this.dayOfYear) / 7);
			if(wOY == 0)
				wOY = 1;
		}else
			wOY = wOY + (int)Math.ceil(((double)(this.dayOfYear - (7 - dOW)) / 7));
		
		this.setWeekOfYear(wOY);
		//compute weekOfMonth
		int wOM = 1;//week_of_month
		gd = convertToGregorian(new JalaliDate(year,month + 1,1));
		gc.set(gd.getYear(),gd.getMonth()-1,gd.getDay());
		dOW = gc.get(DAY_OF_WEEK);//day_of_week
		if(dOW == 7){
			dOW = 0;
			wOM = 0;
			wOM = wOM + (int)Math.ceil((double)(this.dayOfMonth) / 7);
			if(wOM == 0)
				wOM = 1;
		}else
			wOM = wOM + (int)Math.ceil(((double)(this.dayOfMonth - (7 - dOW)) / 7));
		
		this.setWeekOfMonth(wOM);
		//compute dayOfWeekInMonth
		int dOWIM = 0;//day_of_week_in_month
		dOWIM = (int)Math.ceil(((double)(this.dayOfMonth)) / 7);
		this.setDayOfWeekInMonth(dOWIM);
		
	}
	
	
	
	
	@Override
	public int getActualMaximum(int field) {
		switch (field) {
		case ERA:
		case DAY_OF_WEEK:
		case HOUR:
		case AM_PM:
		case HOUR_OF_DAY:
		case MINUTE:
		case SECOND:
		case MILLISECOND:
		case ZONE_OFFSET:
		case DST_OFFSET:
		case MONTH:
		case YEAR:
			return getMaximum(field);
		case DAY_OF_MONTH:
			return computeMonthLen(this);
		case DAY_OF_YEAR:
			return computeYearLen(this);
		case WEEK_OF_YEAR:
			JalaliCalendar testJc = new JalaliCalendar(this.get(YEAR),0,1);
			int result = 1,value;
			
			value = computeYearLen(this);
			
			if(testJc.get(DAY_OF_WEEK) != 7)
				value = value - (7 - testJc.get(DAY_OF_WEEK));
			else
				value = value - 7;
			result += (int)Math.ceil(((double)value) / 7);
			return result;
		case WEEK_OF_MONTH:
			testJc = new JalaliCalendar(this.get(YEAR),this.get(MONTH),1);
			
			result = 1;
			
			if(this.get(MONTH) != 11)
				value = j_days_in_month[this.get(MONTH)];
			else
				value = computeMonthLen(this);
			
			if(testJc.get(DAY_OF_WEEK) != 7)
				value = value - (7 - testJc.get(DAY_OF_WEEK));
			else
				value = value - 7;
			
			result += (int)Math.ceil(((double)value)/7);
			
			return result;
		case DAY_OF_WEEK_IN_MONTH:
			if(this.get(MONTH) != 11)
				value = j_days_in_month[this.get(MONTH)];
			else
				value = computeMonthLen(this);
			
			result = (int)Math.ceil(((double)value)/7);
			return result;
			
		default:
			throw new ArrayIndexOutOfBoundsException(field);
		}
	}

	@Override
	public int getActualMinimum(int field) {
		return getMinimum(field);
	}


	@Override
	public void add(int field, int amount) {
		this.set(field, this.get(field) + amount);
	}

	@Override
	public boolean after(Object when) {
		return thisGc.after(when);
	}

	@Override
	public boolean before(Object when) {
		return thisGc.before(when);
	}

	@Override
	public int compareTo(Calendar anotherCalendar) {
		return thisGc.compareTo(anotherCalendar);
	}

	@Override
	public boolean equals(Object obj) {
		return thisGc.equals(obj);
	}

	@Override
	public void roll(int field, boolean up) {
		roll(field, up ? +1 : -1);
	}

	@Override
	public void roll(int field, int amount) {
		if(amount == 0){
			return;
		}
		
		if (field < 0 || field >= ZONE_OFFSET) {
		    throw new IllegalArgumentException();
		}
		
		switch (field) {
		case AM_PM:
			thisGc.roll(AM_PM, amount);
			break;
		case HOUR:
			thisGc.roll(HOUR, amount);
			break;
		case HOUR_OF_DAY:
			thisGc.roll(HOUR_OF_DAY, amount);
			break;
		case MINUTE:
			thisGc.roll(MINUTE, amount);
			break;
		case SECOND:
			thisGc.roll(SECOND, amount);
			break;
		case MILLISECOND:
			thisGc.roll(MILLISECOND, amount);
			break;
		case MONTH:
			int mon = (this.get(MONTH) + amount) % 12;
		    while (mon < 0) {
			mon += 12;
		    }
		    this.setMonth(mon);
		    
		    // Keep the day of month in the range.
		    int monthLen = computeMonthLen(this);
		    
		    if (this.get(DAY_OF_MONTH) > monthLen) {
		    	set(DAY_OF_MONTH, monthLen);
		    }
		    //synchronized with thisGc
			gd = convertToGregorian(new JalaliDate(this.get(YEAR),this.get(MONTH),this.get(DAY_OF_MONTH)));
			thisGc.set(gd.getYear(), gd.getMonth(), gd.getDay());
		    break;
		    
		case WEEK_OF_YEAR:
			
			int max = this.getActualMaximum(WEEK_OF_YEAR);
			int min = this.getActualMinimum(WEEK_OF_YEAR);
			int value = this.get(WEEK_OF_YEAR);
			value = value + amount;
			if(value > min && value < max){
				this.set(WEEK_OF_YEAR, value);
				//synchronized with thisGc
				gd = convertToGregorian(new JalaliDate(this.get(YEAR),this.get(MONTH),this.get(DAY_OF_MONTH)));
				thisGc.set(gd.getYear(), gd.getMonth(), gd.getDay());
				return;
			}
			//check fulling end weekOfYear
			JalaliCalendar testJc = new JalaliCalendar(this.get(YEAR),11,isLeap(this) ? 30 : 29);
			boolean checkFull = testJc.get(DAY_OF_WEEK)== 6 ? true : false;//if dayOfWeek == jomeh : checkFull=true
			if(!checkFull){
				max--;
			}	
			
			//end week not full and weekOfYear = end week
			if(!checkFull && this.get(WEEK_OF_YEAR) == this.getActualMaximum(WEEK_OF_YEAR)){
			//////////////????????????
			//////////////???????????
			//////////////????????????
			}
			
			value = value % max;
			if(value == 0)
				value = max;
			
			//Make sure that the min week has the current DAY_OF_WEEK
			if(value == 1){
				testJc = new JalaliCalendar(this.get(YEAR),0,1);
				if(testJc.get(DAY_OF_WEEK) == 7)
					this.set(WEEK_OF_YEAR, value);
				else
					if(this.get(DAY_OF_WEEK) == 7 || this.get(DAY_OF_WEEK) < testJc.get(DAY_OF_WEEK))
						this.set(WEEK_OF_YEAR, value + 1);
					else
						this.set(WEEK_OF_YEAR, value);
			}else{
				this.set(WEEK_OF_YEAR, value);
			}
			//synchronized with thisGc
			gd = convertToGregorian(new JalaliDate(this.get(YEAR),this.get(MONTH),this.get(DAY_OF_MONTH)));
			thisGc.set(gd.getYear(), gd.getMonth(), gd.getDay());
			
			break;

		case WEEK_OF_MONTH:
			monthLen = computeMonthLen(this);
			
			gd = convertToGregorian(new JalaliDate(this.get(YEAR),this.get(MONTH)+1,1));
			gc.set(gd.getYear(), gd.getMonth()-1, gd.getDay());
			jd = convertToJalali(new GregorianDate(gc.get(YEAR),gc.get(MONTH)+1,gc.get(DAY_OF_MONTH)));
			int firstDayOfFirstWeekOfMonth = new JalaliCalendar(jd.getYear(),jd.getMonth()-1,jd.getDay()).get(DAY_OF_WEEK);
			
			gd = convertToGregorian(new JalaliDate(this.get(YEAR),this.get(MONTH)+1,monthLen));
			gc.set(gd.getYear(), gd.getMonth()-1, gd.getDay());
			jd = convertToJalali(new GregorianDate(gc.get(YEAR),gc.get(MONTH)+1,gc.get(DAY_OF_MONTH)));
			int lastDayOfLastWeekOfMonth = new JalaliCalendar(jd.getYear(),jd.getMonth()-1,jd.getDay()).get(DAY_OF_WEEK);
			
			int numberWeeksInMonth;
			if(firstDayOfFirstWeekOfMonth == 7)
				numberWeeksInMonth = 5;
			else
				numberWeeksInMonth = 1 + (int)Math.ceil( ((double)(monthLen - (7 - firstDayOfFirstWeekOfMonth))) / 7 );
			
			amount = (this.get(WEEK_OF_MONTH) + amount) % numberWeeksInMonth;
			if (amount <= 0)
				amount = amount + numberWeeksInMonth;
			
			int disDOM = 0;//distance between current dayOfMonth and new day
			
			if(amount == this.get(WEEK_OF_MONTH))
				return;
			else{
				//movement backward
				if(amount < this.get(WEEK_OF_MONTH)){
					if(this.get(DAY_OF_WEEK) == 7)
						disDOM += 0;
					else
						disDOM += this.get(DAY_OF_WEEK);
					disDOM = disDOM + 7 * (this.get(WEEK_OF_MONTH) - amount - 1);
					if(amount != 1){
						if(this.get(DAY_OF_WEEK) == 7)
							disDOM = disDOM + 7;
						else
							disDOM = disDOM + 7 - this.get(DAY_OF_WEEK);
					}else{
						if(this.get(DAY_OF_WEEK) == 7){
							if(firstDayOfFirstWeekOfMonth != 7)
								disDOM += 7 - firstDayOfFirstWeekOfMonth;
							else
								disDOM += 7;
						}else{
							if(firstDayOfFirstWeekOfMonth == 7){
								disDOM += 7 - this.get(DAY_OF_WEEK);
							}else{
								if(firstDayOfFirstWeekOfMonth <= this.get(DAY_OF_WEEK))
									disDOM += 7 - this.get(DAY_OF_WEEK);
								else
									disDOM += 7 - firstDayOfFirstWeekOfMonth;
							}
						}
					}
					disDOM = - disDOM;
				//movement forward
				}else{
					if(this.get(DAY_OF_WEEK) == 7)
						disDOM += 6;
					else
						disDOM += 6 - this.get(DAY_OF_WEEK);
					
					disDOM += 7 * (amount - this.get(WEEK_OF_MONTH) -1);
					
					if(amount != numberWeeksInMonth){
						if(this.get(DAY_OF_WEEK) == 7)
							disDOM += 1;
						else
							disDOM += this.get(DAY_OF_WEEK) + 1;
					}else{
						if(lastDayOfLastWeekOfMonth == 7){
							disDOM += 1;
						}else{
							if(this.get(DAY_OF_WEEK) <= lastDayOfLastWeekOfMonth)
								disDOM += this.get(DAY_OF_WEEK) + 1;
							else
								disDOM += lastDayOfLastWeekOfMonth + 1;
						}
					}
				}
										
			}
			
			this.set(DAY_OF_MONTH, this.get(DAY_OF_MONTH) + disDOM);
			//synchronized with thisGc
			gd = convertToGregorian(new JalaliDate(this.get(YEAR),this.get(MONTH),this.get(DAY_OF_MONTH)));
			thisGc.set(gd.getYear(), gd.getMonth(), gd.getDay());
			
			break;
			
		case DAY_OF_MONTH:
		    if (this.get(MONTH) != 11){
		    	monthLen = j_days_in_month[this.get(MONTH)];
		    }else{
		    	monthLen = computeMonthLen(this);
		    }
		    value = this.get(DAY_OF_MONTH) + amount;
		    value = value % monthLen;
		    if(value <= 0)
		    	value += monthLen;
		    this.set(DAY_OF_MONTH, value);
		    
		    //synchronized with thisGc
			gd = convertToGregorian(new JalaliDate(this.get(YEAR),this.get(MONTH),this.get(DAY_OF_MONTH)));
			thisGc.set(gd.getYear(), gd.getMonth(), gd.getDay());
			
			break;
		
		case DAY_OF_YEAR:
			int yearLen = computeYearLen(this);
			value = this.get(DAY_OF_YEAR) + amount;
			
			value = value % yearLen;
			if(value <= 0)
				value += yearLen;
			this.set(DAY_OF_YEAR, value);
			
			//synchronized with thisGc
			gd = convertToGregorian(new JalaliDate(this.get(YEAR),this.get(MONTH),this.get(DAY_OF_MONTH)));
			thisGc.set(gd.getYear(), gd.getMonth(), gd.getDay());
			
			break;

		case DAY_OF_WEEK:
			gd = convertToGregorian(new JalaliDate(year,month+1,dayOfMonth));
			int disDOW;//distance between current dayOfWeek and new day
			
			amount = amount % 7;
			if(amount == 0)
				return;
			
			switch (dayOfWeek) {
			case 7:
				if (amount < 0) {
					disDOW = 7 - amount;
				}else {
					disDOW = amount; 
				}
				break;
			default:
				if(amount > 0)
					disDOW = ((dayOfWeek + amount) % 7 ) - dayOfWeek ;
				else
					if(amount >= -dayOfWeek)
						disDOW = amount;
					else
						disDOW = amount + 7;
				break;
			}
			thisGc.set(gd.getYear(),gd.getMonth()-1,gd.getDay() + disDOW);
			gd = new GregorianDate(thisGc.get(YEAR),thisGc.get(MONTH)+1,thisGc.get(DAY_OF_MONTH));
			jd = convertToJalali(gd);
			computeJalaliCalendarFields(jd.getYear(), jd.getMonth()-1, jd.getDay());
			break;
			
		case DAY_OF_WEEK_IN_MONTH:
			int dowim = this.get(DAY_OF_WEEK_IN_MONTH);
			
			if(this.get(month) != 11)
				monthLen = j_days_in_month[this.get(MONTH)];
			else
				monthLen = computeMonthLen(this);
			
			dowim += amount;
			dowim = dowim % 5;
			if(dowim == 0)
				dowim = 5;
			
			//Make sure that the min or max week has the current DAY_OF_WEEK
			if(dowim == 1){
				testJc = new JalaliCalendar(this.get(YEAR),this.get(MONTH),1);
				if(testJc.get(DAY_OF_WEEK) == 7)
					this.set(WEEK_OF_MONTH, dowim);
				else
					if(this.get(DAY_OF_WEEK) == 7 || this.get(DAY_OF_WEEK) < testJc.get(DAY_OF_WEEK))
						this.set(WEEK_OF_MONTH, dowim + 1);
					else
						this.set(WEEK_OF_MONTH, dowim);
				
			}else if(dowim == 5){
				testJc = new JalaliCalendar(this.get(YEAR),this.get(MONTH),28);
				do {
					testJc.roll(DAY_OF_MONTH, 1);
					if(this.get(DAY_OF_WEEK) == testJc.get(DAY_OF_WEEK)){
						this.set(DAY_OF_MONTH, testJc.get(DAY_OF_MONTH));
						
						//synchronized with thisGc
						gd = convertToGregorian(new JalaliDate(this.get(YEAR),this.get(MONTH),this.get(DAY_OF_MONTH)));
						thisGc.set(gd.getYear(), gd.getMonth(), gd.getDay());
						
						return;
					}					
				} while (monthLen != testJc.get(DAY_OF_MONTH));
				this.set(DAY_OF_WEEK_IN_MONTH, 1);			
				
			}else{
				this.set(DAY_OF_WEEK_IN_MONTH, dowim);
			}
			
			//synchronized with thisGc
			gd = convertToGregorian(new JalaliDate(this.get(YEAR),this.get(MONTH),this.get(DAY_OF_MONTH)));
			thisGc.set(gd.getYear(), gd.getMonth(), gd.getDay());
			
			break;

		default:
			throw new ArrayIndexOutOfBoundsException(field);
		}
		
	}

	public String toString() {
		return super.toString();
	}

	public String getDisplayName(int field, int style, Locale locale) {
		ResourceBundle rb = ResourceBundle.getBundle("ir.asta.core.util.date.jalaliFieldNames");
		String result,sub;
		switch (field) {
		case ERA:
			sub = "ERA";
			break;
		case MONTH:
			sub = "MONTH";
			break;
		case DAY_OF_WEEK:
			sub = "DAY";
			break;
		case AM_PM:
			sub = "AM_PM";
			break;
		default:
			sub = null;
			break;
		}
		if(style == LONG){	
			result = "L_" + sub + "_" + this.get(field);
 		}else{
			result = "S_" + sub + "_" + this.get(field);
		}
		try {
			return rb.getString(result);
		} catch (MissingResourceException e) {
			return null;
		}
	}

	public Map<String, Integer> getDisplayNames(int field, int style, Locale locale) {
		
		ResourceBundle rb = ResourceBundle.getBundle("ir.asta.core.util.date.jalaliFieldNames");
		Map<String,Integer> names = new HashMap<String,Integer>();
		int num;
		String sub,result;
		switch (field) {
		case ERA:
			num = 2;
			sub = "ERA";
			break;
		case MONTH:
			num = 12; 
			sub = "MONTH";
			break;
		case DAY_OF_WEEK:
			num = 7;
			sub = "DAY_OF_WEEK";
			break;
		case AM_PM:
			num = 2;
			sub = "AM_PM";
			break;
		default:
			return null;
		}
		
		if(style == LONG)
			result = "L_" + sub;
		else
			result = "S_" + sub;
		
		if(field != DAY_OF_WEEK){
			for(int i = 0; i < num; i++){
				names.put(rb.getString(result + "_" + i),i);
			}
		}else{
			for(int i = 1; i <= num; i++){
				names.put(rb.getString(result + "_" + i),i);
			}
		}
			
		return names;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public int getGreatestMinimum(int field) {
		return MIN_VALUES[field];
	}

	@Override
	public int getLeastMaximum(int field) {
		return LEAST_MAX_VALUES[field];
	}

	@Override
	public int getMaximum(int field) {
		return MAX_VALUES[field];
	}
	@Override
	public int getMinimum(int field) {
		return MIN_VALUES[field];
	}
	
	@Override
	public void setTimeZone(TimeZone value) {
		thisGc.setTimeZone(value);
	}
	
	@Override
	public TimeZone getTimeZone() {
		return thisGc.getTimeZone();
	}

	@Override
	public long getTimeInMillis() {
		return thisGc.getTimeInMillis();
	}

	@Override
	public void setLenient(boolean lenient) {
		thisGc.setLenient(lenient);
	}

	@Override
	public boolean isLenient() {
		return thisGc.isLenient();
	}
	
	@Override
	public int getFirstDayOfWeek() {
		return thisGc.getFirstDayOfWeek();
	}

	@Override
	public int getMinimalDaysInFirstWeek() {
		return thisGc.getMinimalDaysInFirstWeek();
	}

	@Override
	public void setFirstDayOfWeek(int value) {
		thisGc.setFirstDayOfWeek(value);
	}

	@Override
	public void setMinimalDaysInFirstWeek(int value) {
		thisGc.setMinimalDaysInFirstWeek(value);
	}
	
	
	
	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getDayOfWeekInMonth() {
		return dayOfWeekInMonth;
	}

	public void setDayOfWeekInMonth(int dayOfWeekInMonth) {
		this.dayOfWeekInMonth = dayOfWeekInMonth;
	}

	public int getDayOfYear() {
		return dayOfYear;
	}

	public void setDayOfYear(int dayOfYear) {
		this.dayOfYear = dayOfYear;
	}

	public int getEra() {
		return era;
	}

	public void setEra(int era) {
		this.era = era;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getWeekOfMonth() {
		return weekOfMonth;
	}

	public void setWeekOfMonth(int weekOfMonth) {
		this.weekOfMonth = weekOfMonth;
	}

	public int getWeekOfYear() {
		return weekOfYear;
	}

	public void setWeekOfYear(int weekOfYear) {
		this.weekOfYear = weekOfYear;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}	
	
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
	
}
