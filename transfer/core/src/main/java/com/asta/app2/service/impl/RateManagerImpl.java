/* *Class created on [ Jul 22, 2008 | 12:00:13 AM ] */ 
package com.asta.app2.service.impl;

import java.util.List;

import com.asta.app2.dao.RateDao;
import com.asta.app2.model.Rate;
import com.asta.app2.service.RateManager;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class RateManagerImpl extends GenericManagerImpl<Rate, Long> implements RateManager{
	private RateDao rateDao;
	public RateManagerImpl(RateDao rateDao) {
		super(rateDao);
		this.rateDao = rateDao;
	}
	public List<Rate> findRateForTicket(Rate rate) {
		return rateDao.findRateForTicket(rate);
	}
	
	

}


