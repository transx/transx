/* *Class created on [ Jul 15, 2008 | 7:19:38 PM ] */ 
package com.asta.app2.service;

import java.util.List;

import com.asta.app2.model.Company;
import com.asta.app2.model.Service;
import com.asta.app2.model.Soorat;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface SooratManager extends GenericManager<Soorat, Long>{
	List<Soorat> findSooratsByService(Service service);
	
	List<Soorat> getAllSoorat(Company company);
	List<Soorat> getAllSooratActive(Company company);
}


