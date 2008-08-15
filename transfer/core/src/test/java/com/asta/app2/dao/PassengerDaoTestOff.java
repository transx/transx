/* *Class created on [ Jun 7, 2008 | 12:17:24 AM ] */
package com.asta.app2.dao;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class PassengerDaoTestOff extends BaseDaoTestCase{
	private PassengerDao passengerDao;

	public void setPassengerDao(PassengerDao passengerDao) {
		this.passengerDao = passengerDao;
	}

	public void testFindPassengerByLastName() {
		log.debug("testing findPassengerByLastName......");
		assertTrue(passengerDao.findPassengerByLastName("محسنی").size() >= 1);
	}
}
