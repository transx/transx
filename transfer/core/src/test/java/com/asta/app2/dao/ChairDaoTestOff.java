/* *Class created on [ Jun 14, 2008 | 5:16:55 PM ] */
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Chair;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ChairDaoTestOff extends BaseDaoTestCase {
	private ChairDao chairDao;

	public void setChairDao(ChairDao chairDao) {
		this.chairDao = chairDao;
	}

	public void testFindChairsByServiceId() {
		assertTrue(chairDao.findChairsByServiceId(-1L).size() >= 1);
		log.debug(chairDao.findChairsByServiceId(-1L).size());
	}

	public void testGetAllSorted() {
		log.debug("testing getAllSorted.......");
		List<Chair> list = chairDao.getAllSorted(40);
		assertTrue(list.size() >= 1);
//		for (Chair chair : list) {
//			log.debug(chair.getId());
//		}	
	}

}
