/* *Class created on [ Jul 15, 2008 | 6:53:46 PM ] */
package com.asta.app2.dao;

import com.asta.app2.model.Service;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SooratDaoTestOff extends BaseDaoTestCase {
	private SooratDao sooratDao;
	private ServiceDao serviceDao;

	public void setServiceDao(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	public void setSooratDao(SooratDao sooratDao) {
		this.sooratDao = sooratDao;
	}

	public void testFindSooratsByService() {
		log.debug("testing findSooratsByService.......");
		Service service = serviceDao.get(-1L);
		assertTrue(sooratDao.findSooratsByService(service).size() > 0);
		log.debug("size of list :"+sooratDao.findSooratsByService(service).size());
	}
	
/*	public void testGetAllSoorat(){
		log.debug("testing getAllSoorat......");
		assertTrue(sooratDao.getAllSoorat().size() >0);
		log.debug("the size of list is :"+sooratDao.getAllSoorat().size());
	}*/
}
