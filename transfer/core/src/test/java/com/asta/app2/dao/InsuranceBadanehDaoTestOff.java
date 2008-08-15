/* *Class created on [ Jul 26, 2008 | 7:08:12 PM ] */
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.InsuranceBadaneh;
import com.asta.app2.model.enums.Quality;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class InsuranceBadanehDaoTestOff extends BaseDaoTestCase {
	private InsuranceBadanehDao insuranceBadanehDao;

	public void setInsuranceBadanehDao(InsuranceBadanehDao insuranceBadanehDao) {
		this.insuranceBadanehDao = insuranceBadanehDao;
	}

	public void testFindIBbyQualityJodaganehSpace() {
		log.debug("testing findIBbyQualityJodaganehSpace......");
		List<InsuranceBadaneh> list = insuranceBadanehDao
				.findIBbyQualityJodaganehSpace(Quality.NORMAL, false, 300L);
		log.debug("size of list:"+list.size());
		log.debug("price:"+list.get(0).getPrice());
	}
}
