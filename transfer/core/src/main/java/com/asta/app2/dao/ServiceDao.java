/* *Class created on [ Jun 7, 2008 | 6:17:54 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Company;
import com.asta.app2.model.Service;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface ServiceDao extends GenericDao<Service, Long>{
	Service saveService(Service service);
	List<Service> getAllServices(Company company);
	List<Service> getAllServicesReadyForReserve(Company company);
	List<Service> getAllServicesReadyForSoorat(Company company);
}


