/* *Class created on [ Jul 21, 2008 | 6:11:09 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Rate;
import com.asta.app2.model.enums.Quality;
import com.asta.app2.model.enums.RateType;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class RateDaoTestOff extends BaseDaoTestCase{
	RateDao rateDao;
	PathDao pathDao;
	CarKindDao carKindDao;
	
	public void setPathDao(PathDao pathDao) {
		this.pathDao = pathDao;
	}

	public void setCarKindDao(CarKindDao carKindDao) {
		this.carKindDao = carKindDao;
	}

	public void setRateDao(RateDao rateDao) {
		this.rateDao = rateDao;
	}
	
	public void testFindRateForTicket(){
		log.debug("testing findRateForTicket......");
		Rate rate = new Rate();
		rate.setRateType(RateType.ORDINARY);
		rate.setQuality(Quality.UP);
		rate.setPath(pathDao.get(-1L));
		List<Rate> rates = rateDao.findRateForTicket(rate);
		log.debug("size of list :"+rates.size());
		log.debug("price :"+rates.get(0).getPrice());
		assertTrue(rates.size() == 1);
	}
}


