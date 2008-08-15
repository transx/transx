/* *Class created on [ Jul 26, 2008 | 12:23:41 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.InsuranceSarneshin;
import com.asta.app2.model.enums.Distance;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class InsuranceSarneshinDaoTestOff extends BaseDaoTestCase{
	private InsuranceSarneshinDao insuranceSarneshinDao;

	public void setInsuranceSarneshinDao(InsuranceSarneshinDao insuranceSarneshinDao) {
		this.insuranceSarneshinDao = insuranceSarneshinDao;
	}
	
	public void testFindISbyDistanceCapacity(){
		log.debug("testing findInsuranceSarneshinByDistanceCapacity......");
		List<InsuranceSarneshin> list =  insuranceSarneshinDao.findISbyDistanceCapacity(Distance.UP300, 40);
		log.debug("size of list is :" +list.size());
		log.debug("price="+list.get(0).getPrice());
	}
}


