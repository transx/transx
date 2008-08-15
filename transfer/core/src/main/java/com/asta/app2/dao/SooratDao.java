/* *Class created on [ Jul 15, 2008 | 6:55:24 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Company;
import com.asta.app2.model.Service;
import com.asta.app2.model.Soorat;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface SooratDao extends GenericDao<Soorat, Long>{
	List<Soorat> findSooratsByService(Service service);

	List<Soorat> getAllSoorat(Company company);
}


