/* *Class created on [ Jul 21, 2008 | 11:32:03 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Rate;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface RateDao extends GenericDao<Rate, Long>{
	List<Rate> findRateForTicket(Rate rate);
}


