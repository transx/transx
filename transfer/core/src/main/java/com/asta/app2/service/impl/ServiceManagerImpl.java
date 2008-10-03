/* *Class created on [ Jun 7, 2008 | 9:49:50 PM ] */
package com.asta.app2.service.impl;

import java.util.List;

import com.asta.app2.dao.ServiceDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.Service;
import com.asta.app2.service.ServiceManager;

/**
 * ServiceManagerImpl extends UniversalManagerImpl implements ServiceManager
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ServiceManagerImpl extends GenericManagerImpl<Service, Long> implements
		ServiceManager {
	private ServiceDao serviceDao;
	
	public ServiceManagerImpl(ServiceDao serviceDao) {
		super(serviceDao);
		this.serviceDao = serviceDao;
	}

	public Service getService(String serviceId) {
		return serviceDao.get(new Long(serviceId));
	}

	public List<Service> getAllServices(Company company) {
		return serviceDao.getAllServices(company);
	}

	public List<Service> getAllServicesReadyForReserve(Company company) {
		return serviceDao.getAllServicesReadyForReserve(company);
	}

/*	public Map<String, String> getMap(boolean withEmpty) {
		List<Service> services = serviceDao.getAll();
		Map<String, String> map = new TreeMap<String, String>();
		map.clear();
		if (withEmpty)
			map.put("", "");
		for (Service service : services) {
			map.put(service.toString() == null ? "" : service.toString(), service
					.getId().toString());
		}
		return map;
	}
*/
	public List<Service> getAllServicesReadyForSoorat(Company company) {
		return serviceDao.getAllServicesReadyForSoorat(company);
	}

	public Service saveService(Service service) {
		return serviceDao.saveService(service);
	}

}
