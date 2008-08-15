/* *Class created on [ Jul 28, 2008 | 8:21:55 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Cash;
import com.asta.app2.model.Company;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface CashDao extends GenericDao<Cash, Long>{

	List<Cash> getAllCash(Company company);
	List<Cash> getAllEnabledCash(Company company);
	List<Cash> getAllEnabledCashNotExpired(Company company);

}


