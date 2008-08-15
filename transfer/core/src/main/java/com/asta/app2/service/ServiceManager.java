/* *Class created on [ Jun 7, 2008 | 9:48:44 PM ] */ 
package com.asta.app2.service;

import java.util.List;
import java.util.Map;

import com.asta.app2.model.Company;
import com.asta.app2.model.Service;
	
/**
 * ServiceManager extends UniversalManager
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface ServiceManager extends GenericManager<Service, Long>{

	Service getService(String serviceId);

	List<Service> getAllServices(Company company);

	List<Service> getAllServicesReadyForReserve(Company company);

	List<Service> getAllServicesReadyForSoorat(Company company);

//	Map<String, String> getMap(boolean withEmpty);
	
}


