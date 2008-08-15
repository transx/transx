/* *Class created on [ Jul 28, 2008 | 8:21:25 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Cash;
import com.asta.app2.model.Company;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CashDaoTestOff extends BaseDaoTestCase{
	private CashDao cashDao;

	public void setCashDao(CashDao cashDao) {
		this.cashDao = cashDao;
	}
	
	public void testGetAllEnabledCash(){
		log.debug("testing getAllEnabledCash......");
		// THIS TEST WILL FAIL BECAUSE OF OBJECT COMPANY
		List<Cash> cashes = cashDao.getAllEnabledCash(new Company());
		assertTrue(cashes.size() > 0);
		log.debug("size of the list :"+cashes.size());
	}
}


