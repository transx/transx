/* *Class created on [ Jul 21, 2008 | 11:58:42 PM ] */ 
package com.asta.app2.service;

import java.util.List;

import com.asta.app2.model.Rate;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface RateManager extends GenericManager<Rate, Long>{
	List<Rate> findRateForTicket(Rate rate);
}


