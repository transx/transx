/* *Class created on [ Jul 31, 2008 | 11:27:56 PM ] */ 
package com.asta.app2.util;

import com.asta.app2.util.date.JalaliCalendar;

import junit.framework.TestCase;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
/**
 * @author  <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class JalaliCalenderTest extends TestCase{
	public final static int ERA = 0;
    public final static int YEAR = 1;
    public final static int MONTH = 2;
    public final static int WEEK_OF_YEAR = 3;
    public final static int WEEK_OF_MONTH = 4;
    public final static int DATE = 5;
    public final static int DAY_OF_MONTH = 5;
    public final static int DAY_OF_YEAR = 6;
    public final static int DAY_OF_WEEK = 7;
    public final static int DAY_OF_WEEK_IN_MONTH = 8;
    public final static int AM_PM = 9;
    public final static int HOUR = 10;
    public final static int HOUR_OF_DAY = 11;
    public final static int MINUTE = 12;
    public final static int SECOND = 13;
    public final static int MILLISECOND = 14;
    public final static int ZONE_OFFSET = 15;
    public final static int DST_OFFSET = 16;
    public final static int FIELD_COUNT = 17;

    
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGet() {
		JalaliCalendar jc = new JalaliCalendar(1383,11,30);
		assertEquals(jc.get(YEAR), 1383);
		assertEquals(jc.get(MONTH), 11);
		assertEquals(jc.get(DAY_OF_MONTH), 30);
		assertEquals(jc.get(DATE), 30);
		assertEquals(jc.get(DAY_OF_YEAR), 366);
		assertEquals(jc.get(DAY_OF_WEEK), 1);
		assertEquals(jc.get(WEEK_OF_YEAR), 53);
		assertEquals(jc.get(WEEK_OF_MONTH), 5);
		assertEquals(jc.get(DAY_OF_WEEK_IN_MONTH), 5);
		
		jc.set(DAY_OF_MONTH, 31);
		assertEquals(jc.get(YEAR), 1384);
		assertEquals(jc.get(MONTH), 0);
		assertEquals(jc.get(DAY_OF_MONTH), 1);
		assertEquals(jc.get(DATE), 1);
		assertEquals(jc.get(DAY_OF_YEAR), 1);
		assertEquals(jc.get(DAY_OF_WEEK), 2);
		assertEquals(jc.get(WEEK_OF_YEAR), 1);
		assertEquals(jc.get(WEEK_OF_MONTH), 1);
		assertEquals(jc.get(DAY_OF_WEEK_IN_MONTH), 1);
		
		jc = new JalaliCalendar(1386,2,1);
		assertEquals(jc.get(WEEK_OF_YEAR), 10);
	}

	public void testSetIntInt() {
		//Year
		JalaliCalendar jc = new JalaliCalendar(1383,11,30);
		jc.set(YEAR, 1384);
		assertEquals(jc.get(YEAR), 1385);
		assertEquals(jc.get(MONTH),0);
		assertEquals(jc.get(DAY_OF_MONTH),1);
		//Month
		jc = new JalaliCalendar(1383,5,31);
		jc.set(MONTH, 6);
		assertEquals(jc.get(YEAR), 1383);
		assertEquals(jc.get(MONTH),7);
		assertEquals(jc.get(DAY_OF_MONTH),1);
		//Day_Of_Month
		jc = new JalaliCalendar(1384,11,29);
		jc.set(DAY_OF_MONTH, 30);
		assertEquals(jc.get(YEAR), 1385);
		assertEquals(jc.get(MONTH),0);
		assertEquals(jc.get(DAY_OF_MONTH),1);
		//Day_Of_Year
		jc = new JalaliCalendar(1383,11,29);
		jc.set(DAY_OF_YEAR, 366);
		assertEquals(jc.get(YEAR), 1383);
		assertEquals(jc.get(MONTH),11);
		assertEquals(jc.get(DAY_OF_MONTH),30);
		assertEquals(jc.get(DAY_OF_YEAR),366);
		
		jc.set(DAY_OF_YEAR,367);
		assertEquals(jc.get(YEAR), 1384);
		assertEquals(jc.get(MONTH),0);
		assertEquals(jc.get(DAY_OF_MONTH),1);
		assertEquals(jc.get(DAY_OF_YEAR),1);
		//Day_Of_Week
		
		jc = new JalaliCalendar(1383,11,30);
		jc.set(DAY_OF_WEEK, 2);
		assertEquals(jc.get(YEAR), 1384);
		assertEquals(jc.get(MONTH),0);
		assertEquals(jc.get(DAY_OF_MONTH),1);
		assertEquals(jc.get(DAY_OF_YEAR),1);
		assertEquals(jc.get(DAY_OF_WEEK),2);
		
		jc = new JalaliCalendar(1384,5,31);//Panjshanbeh
		jc.set(DAY_OF_WEEK, 7);
		assertEquals(jc.get(YEAR), 1384);
		assertEquals(jc.get(MONTH),5);
		assertEquals(jc.get(DAY_OF_MONTH),26);
		assertEquals(jc.get(DAY_OF_YEAR),181);
		assertEquals(DAY_OF_WEEK, 7);
		
		jc = new JalaliCalendar(1386,0,7);
		jc.set(DAY_OF_WEEK, 10);
		assertEquals(jc.get(WEEK_OF_MONTH), 2);
		assertEquals(jc.get(DAY_OF_WEEK), 3);
		
		//Week_Of_Year
		jc = new JalaliCalendar(1384,0,1);
		jc.set(WEEK_OF_YEAR, 2);
		assertEquals(jc.get(YEAR), 1384);
		assertEquals(jc.get(MONTH),0);
		assertEquals(jc.get(DAY_OF_MONTH),8);
		assertEquals(jc.get(DAY_OF_YEAR),8);
		assertEquals(jc.get(DAY_OF_WEEK),2);
		assertEquals(jc.get(WEEK_OF_YEAR),2);
		
		jc = new JalaliCalendar(1383,0,31);
		assertEquals(jc.get(WEEK_OF_YEAR),5);
		jc.set(WEEK_OF_YEAR,6);
		assertEquals(jc.get(YEAR), 1383);
		assertEquals(jc.get(MONTH),1);
		assertEquals(jc.get(DAY_OF_MONTH),7);
		assertEquals(jc.get(DAY_OF_YEAR),38);
		assertEquals(jc.get(WEEK_OF_YEAR), 6);
		
		jc = new JalaliCalendar(1383,11,23);
		assertEquals(jc.get(WEEK_OF_YEAR),52);
		jc.set(WEEK_OF_YEAR, 53);
		assertEquals(jc.get(WEEK_OF_YEAR),53);
		assertEquals(jc.get(DAY_OF_WEEK),1);
		
		jc = new JalaliCalendar(1383,11,24);
		assertEquals(jc.get(WEEK_OF_YEAR), 52);
		jc.set(WEEK_OF_YEAR, 53);
		assertEquals(jc.get(WEEK_OF_YEAR), 1);
		assertEquals(jc.get(DAY_OF_WEEK), 2);
		
		jc = new JalaliCalendar(1383,11,30);
		assertEquals(jc.get(WEEK_OF_YEAR),53);
		jc.set(WEEK_OF_YEAR,54);
		assertEquals(jc.get(YEAR), 1384);
		assertEquals(jc.get(MONTH),0);
		assertEquals(jc.get(DAY_OF_MONTH),7);
		assertEquals(jc.get(DAY_OF_YEAR),7);
		assertEquals(jc.get(WEEK_OF_YEAR), 2);
		//Week_Of_Month
		jc = new JalaliCalendar(1383,0,1);
		assertEquals(jc.get(WEEK_OF_MONTH), 1);
		jc.set(WEEK_OF_MONTH, 2);
		assertEquals(jc.get(YEAR), 1383);
		assertEquals(jc.get(MONTH),0);
		assertEquals(jc.get(DAY_OF_MONTH),8);
		assertEquals(jc.get(DAY_OF_YEAR),8);
		assertEquals(jc.get(WEEK_OF_YEAR), 2);
		assertEquals(jc.get(WEEK_OF_MONTH), 2);
		
		jc = new JalaliCalendar(1383,0,29);
		assertEquals(jc.get(WEEK_OF_MONTH), 5);
		jc.set(WEEK_OF_MONTH, 6);
		assertEquals(jc.get(YEAR), 1383);
		assertEquals(jc.get(MONTH),1);
		assertEquals(jc.get(DAY_OF_MONTH), 5);
		assertEquals(jc.get(DAY_OF_YEAR), 36);
		assertEquals(jc.get(WEEK_OF_YEAR), 6);
		assertEquals(jc.get(WEEK_OF_MONTH), 2);
		
		jc = new JalaliCalendar(1383,11,30);
		assertEquals(jc.get(WEEK_OF_MONTH), 5);
		jc.set(WEEK_OF_MONTH, 6);
		assertEquals(jc.get(YEAR), 1384);
		assertEquals(jc.get(MONTH),0);
		assertEquals(jc.get(DAY_OF_MONTH),7);
		assertEquals(jc.get(DAY_OF_YEAR),7);
		assertEquals(jc.get(WEEK_OF_YEAR), 2);
		assertEquals(jc.get(WEEK_OF_MONTH), 2);
		
		jc = new JalaliCalendar(1383,11,24);
		assertEquals(WEEK_OF_MONTH, 4);
		jc.set(WEEK_OF_MONTH, 5);
		assertEquals(jc.get(YEAR), 1384);
		assertEquals(jc.get(MONTH),0);
		assertEquals(jc.get(DAY_OF_MONTH),1);
		assertEquals(jc.get(DAY_OF_YEAR),1);
		assertEquals(jc.get(WEEK_OF_YEAR), 1);
		assertEquals(jc.get(WEEK_OF_MONTH), 1);
		//Day_Of_Week_In_Month
		jc = new JalaliCalendar(1386,0,1);
		assertEquals(jc.get(DAY_OF_WEEK_IN_MONTH), 1);
		jc.set(DAY_OF_WEEK_IN_MONTH, 2);
		assertEquals(jc.get(YEAR), 1386);
		assertEquals(jc.get(MONTH),0);
		assertEquals(jc.get(DAY_OF_MONTH),8);
		assertEquals(jc.get(DAY_OF_YEAR),8);
		assertEquals(jc.get(WEEK_OF_YEAR), 2);
		assertEquals(jc.get(WEEK_OF_MONTH), 2);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		assertEquals(jc.get(DAY_OF_WEEK_IN_MONTH), 2);
		
		jc = new JalaliCalendar(1383,11,24);
		assertEquals(jc.get(DAY_OF_WEEK_IN_MONTH), 4);
		jc.set(DAY_OF_WEEK_IN_MONTH, 5);
		assertEquals(jc.get(YEAR), 1384);
		assertEquals(jc.get(MONTH),0);
		assertEquals(jc.get(DAY_OF_MONTH),1);
		assertEquals(jc.get(DAY_OF_YEAR),1);
		assertEquals(jc.get(WEEK_OF_YEAR), 1);
		assertEquals(jc.get(WEEK_OF_MONTH), 1);
		assertEquals(jc.get(DAY_OF_WEEK_IN_MONTH), 1);
		
		jc = new JalaliCalendar(1383,5,28);
		assertEquals(jc.get(DAY_OF_WEEK_IN_MONTH), 4);
		jc.set(DAY_OF_WEEK_IN_MONTH, 5);
		assertEquals(jc.get(YEAR), 1383);
		assertEquals(jc.get(MONTH),6);
		assertEquals(jc.get(DAY_OF_MONTH),4);
		assertEquals(jc.get(DAY_OF_YEAR),190);
		assertEquals(jc.get(WEEK_OF_YEAR), 28);
		assertEquals(jc.get(WEEK_OF_MONTH), 2);
		assertEquals(jc.get(DAY_OF_WEEK_IN_MONTH), 1);
		
	}

	public void testAdd() {
		JalaliCalendar jc = new JalaliCalendar(1386,0,7);
		assertEquals(jc.get(DAY_OF_WEEK), 3);
		jc.add(WEEK_OF_MONTH, -1);
		assertEquals(jc.get(MONTH), 11);
//		assertEquals(jc.get(DAY_OF_MONTH),28);
	}

	public void testRollIntBoolean() {
	}

	public void testRollIntInt() {
		
		//MONTH
		JalaliCalendar jc = new JalaliCalendar(1384,10,30);
		jc.roll(MONTH, 1);
		assertEquals(jc.get(YEAR), 1384);
		assertEquals(jc.get(MONTH), 11);
		assertEquals(jc.get(DAY_OF_MONTH), 29);
		
		jc = new JalaliCalendar(1384,0,31);
		jc.roll(MONTH, -1);
		assertEquals(jc.get(YEAR), 1384);
		assertEquals(jc.get(MONTH), 11);
		assertEquals(jc.get(DAY_OF_MONTH), 29);
		
		jc = new JalaliCalendar(1386,0,1);
		jc.roll(MONTH, -12);
		assertEquals(jc.get(YEAR), 1386);
		assertEquals(jc.get(MONTH), 0);
		assertEquals(jc.get(DAY_OF_MONTH), 1);
		
		//WEEK_OF_YEAR
		jc = new JalaliCalendar(1387,11,28);
		assertEquals(jc.get(DAY_OF_WEEK),4);
		jc.roll(WEEK_OF_YEAR, 1);
		assertEquals(jc.get(DAY_OF_MONTH), 7);
		assertEquals(jc.get(MONTH), 0);
		assertEquals(jc.get(WEEK_OF_MONTH), 2);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		
		jc = new JalaliCalendar(1386,11,24);
		assertEquals(jc.get(DAY_OF_WEEK), 6);
		jc.roll(WEEK_OF_YEAR, 1);
		assertEquals(jc.get(YEAR), 1386);
		assertEquals(jc.get(MONTH), 0);
		assertEquals(jc.get(DAY_OF_MONTH), 3);
		assertEquals(jc.get(DAY_OF_WEEK), 6);
			
		jc = new JalaliCalendar(1386,0,1);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		jc.roll(WEEK_OF_YEAR, -1);
		assertEquals(jc.get(YEAR), 1386);
		assertEquals(jc.get(MONTH), 11);
		assertEquals(jc.get(DAY_OF_MONTH), 22);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		
		//WEEK_OF_MONTH
		jc = new JalaliCalendar(1386,0,29);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		jc.roll(WEEK_OF_MONTH, 1);
		assertEquals(jc.get(YEAR), 1386);
		assertEquals(jc.get(MONTH), 0);
		assertEquals(jc.get(DAY_OF_MONTH), 1);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		
		jc = new JalaliCalendar(1386,0,28);
		assertEquals(jc.get(DAY_OF_WEEK), 3);
		jc.roll(WEEK_OF_MONTH, 1);
		assertEquals(jc.get(YEAR), 1386);
		assertEquals(jc.get(MONTH), 0);
		assertEquals(jc.get(DAY_OF_MONTH), 1);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		
		jc = new JalaliCalendar(1386,0,20);
		assertEquals(jc.get(DAY_OF_WEEK), 2);
		jc.roll(WEEK_OF_MONTH, -8);
		assertEquals(jc.get(YEAR), 1386);
		assertEquals(jc.get(MONTH), 0);
		assertEquals(jc.get(DAY_OF_MONTH), 1);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		
		//DAY_OF_MONTH
		jc = new JalaliCalendar(1383,11,1);
		jc.roll(DAY_OF_MONTH, 29);
		assertEquals(jc.get(YEAR), 1383);
		assertEquals(jc.get(MONTH), 11);
		assertEquals(jc.get(DAY_OF_MONTH), 30);
		assertEquals(jc.get(DAY_OF_WEEK), 1);
		
		jc = new JalaliCalendar(1386,6,1);
		assertEquals(jc.get(DAY_OF_WEEK), 1);
		jc.roll(DAY_OF_MONTH, -60);
		assertEquals(jc.get(YEAR), 1386);
		assertEquals(jc.get(MONTH), 6);
		assertEquals(jc.get(DAY_OF_MONTH), 1);
		assertEquals(jc.get(DAY_OF_WEEK), 1);
		
		//DAY_OF_YEAR
		jc =  new JalaliCalendar(1383,0,1);
		assertEquals(jc.get(DAY_OF_WEEK), 7);
		jc.roll(DAY_OF_YEAR, 365);
		assertEquals(jc.get(YEAR), 1383);
		assertEquals(jc.get(MONTH), 11);
		assertEquals(jc.get(DAY_OF_MONTH), 30);
		assertEquals(jc.get(DAY_OF_WEEK), 1);
		
		jc =  new JalaliCalendar(1383,0,1);
		assertEquals(jc.get(DAY_OF_WEEK), 7);
		jc.roll(DAY_OF_YEAR, 366);
		assertEquals(jc.get(YEAR), 1383);
		assertEquals(jc.get(MONTH), 0);
		assertEquals(jc.get(DAY_OF_MONTH), 1);
		assertEquals(jc.get(DAY_OF_WEEK), 7);
		
		jc =  new JalaliCalendar(1386,0,31);
		assertEquals(jc.get(DAY_OF_WEEK), 6);
		jc.roll(DAY_OF_YEAR, -30);
		assertEquals(jc.get(YEAR), 1386);
		assertEquals(jc.get(MONTH), 0);
		assertEquals(jc.get(DAY_OF_MONTH), 1);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		
		//DAY_OF_WEEK
		jc = new JalaliCalendar(1386,0,3);
		assertEquals(jc.get(DAY_OF_WEEK), 6);
		jc.roll(DAY_OF_WEEK, -2);
		assertEquals(jc.get(YEAR), 1386);
		assertEquals(jc.get(MONTH), 0);
		assertEquals(jc.get(DAY_OF_MONTH), 1);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		
		jc = new JalaliCalendar(1386,0,10);
		assertEquals(jc.get(DAY_OF_WEEK), 6);
		jc.roll(DAY_OF_WEEK, -6);
		assertEquals(jc.get(YEAR), 1386);
		assertEquals(jc.get(MONTH), 0);
		assertEquals(jc.get(DAY_OF_MONTH), 4);
		assertEquals(jc.get(DAY_OF_WEEK), 7);
		
		jc = new JalaliCalendar(1386,0,1);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		jc.roll(DAY_OF_WEEK, -1);
		assertEquals(jc.get(YEAR), 1385);
		assertEquals(jc.get(MONTH), 11);
		assertEquals(jc.get(DAY_OF_MONTH), 29);
		assertEquals(jc.get(DAY_OF_WEEK), 3);
		
		jc = new JalaliCalendar(1384,11,29);
		assertEquals(jc.get(DAY_OF_WEEK), 2);
		jc.roll(DAY_OF_WEEK, 1);
		assertEquals(jc.get(YEAR), 1385);
		assertEquals(jc.get(MONTH), 0);
		assertEquals(jc.get(DAY_OF_MONTH), 1);
		assertEquals(jc.get(DAY_OF_WEEK), 3);
		
		//DAY_OF_WEEK_IN_MONTH
		jc = new JalaliCalendar(1386,0,8);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		jc.roll(DAY_OF_WEEK_IN_MONTH, -1);
		assertEquals(jc.get(YEAR), 1386);
		assertEquals(jc.get(MONTH), 0);
		assertEquals(jc.get(DAY_OF_MONTH), 1);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		
		jc = new JalaliCalendar(1386,0,1);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		jc.roll(DAY_OF_WEEK_IN_MONTH, 4);
		assertEquals(jc.get(YEAR), 1386);
		assertEquals(jc.get(MONTH), 0);
		assertEquals(jc.get(DAY_OF_MONTH), 29);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		
		jc = new JalaliCalendar(1385,11,22);
		assertEquals(jc.get(DAY_OF_WEEK), 3);
		jc.roll(DAY_OF_WEEK_IN_MONTH, 1);
		assertEquals(jc.get(YEAR), 1385);
		assertEquals(jc.get(MONTH), 11);
		assertEquals(jc.get(DAY_OF_MONTH), 29);
		assertEquals(jc.get(DAY_OF_WEEK), 3);
		
		jc = new JalaliCalendar(1385,11,23);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		jc.roll(DAY_OF_WEEK_IN_MONTH, 1);
		assertEquals(jc.get(YEAR), 1385);
		assertEquals(jc.get(MONTH), 11);
		assertEquals(jc.get(DAY_OF_MONTH), 2);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		
		jc = new JalaliCalendar(1386,3,27);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		jc.roll(DAY_OF_WEEK_IN_MONTH, 1);
		assertEquals(jc.get(YEAR), 1386);
		assertEquals(jc.get(MONTH), 3);
		assertEquals(jc.get(DAY_OF_MONTH), 6);
		assertEquals(jc.get(DAY_OF_WEEK), 4);
		
		
		
		////////??????????????? 50,51
//		GregorianCalendar gc = new GregorianCalendar(2007,11,16);
//		int old = gc.get(WEEK_OF_YEAR);
//		gc.roll(WEEK_OF_YEAR, gc.getActualMaximum(WEEK_OF_YEAR));
//		assertEquals(gc.get(WEEK_OF_YEAR), old);
//		assertEquals(gc.get(MONTH),11);
//		assertEquals(gc.get(DAY_OF_MONTH), 16);
		
//		GregorianCalendar gc = new GregorianCalendar(2007,11,31);
//		assertEquals(gc.get(WEEK_OF_YEAR), 52); // failed !!
//		assertEquals(gc.get(WEEK_OF_YEAR), 1); // success !!
		
		//??????????????
//		GregorianCalendar gc = new GregorianCalendar();
//		gc.roll(DAY_OF_WEEK, -1);
//		assertEquals(gc.get(YEAR), 2004);
//		assertEquals(gc.get(MONTH),0);
//		assertEquals(gc.get(DAY_OF_MONTH), 2);
	}
	
	public void testsetTimeInMillisLong() {
		
		JalaliCalendar jc = new JalaliCalendar(1386,0,20);
		jc.setTimeInMillis(jc.getTimeInMillis() +  24 * 60 * 60 * 1000);
		assertEquals(jc.get(YEAR), 1386);
		assertEquals(jc.get(MONTH), 0);
		assertEquals(jc.get(DAY_OF_MONTH), 21);
		assertEquals(jc.get(DAY_OF_WEEK), 3);
	}

	
	
}



