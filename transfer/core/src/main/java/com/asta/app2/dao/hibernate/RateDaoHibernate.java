/* *Class created on [ Jul 21, 2008 | 11:34:17 PM ] */ 
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.RateDao;
import com.asta.app2.model.Rate;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class RateDaoHibernate extends GenericDaoHibernate<Rate, Long> implements RateDao{

	public RateDaoHibernate() {
		super(Rate.class);
		// TODO Auto-generated constructor stub
	}

	public List<Rate> findRateForTicket(Rate rate) {
		return getHibernateTemplate().find("from Rate as rate where rate.rateType=? and rate.quality=? and rate.path=?", new Object[]{rate.getRateType(),rate.getQuality(),rate.getPath()});
	}

}


