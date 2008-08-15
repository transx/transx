/* *Class created on [ Jun 7, 2008 | 6:18:40 PM ] */
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.ServiceDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.Service;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ServiceDaoHibernate extends GenericDaoHibernate<Service, Long>
		implements ServiceDao {

	public ServiceDaoHibernate() {
		super(Service.class);
	}

	public Service saveService(Service service) {
		log.debug("service's id : " + service.getId());
		getHibernateTemplate().saveOrUpdate(service);
		// necessary to throw a DataIntegrityViolation and catch it in
		// ServiceManager
		getHibernateTemplate().flush();
		return service;
	}

	public List<Service> getAllServices(Company company) {
		return getHibernateTemplate().find("from Service service where service.company=? and service.serviceExpired=0",company);
	}
	
	public List<Service> getAllServicesReadyForReserve(Company company) {
		return getHibernateTemplate().find("from Service service where service.company=? and service.enabled=1 and service.serviceExpired=0 and service.opened=1",company);
	}

	public List<Service> getAllServicesReadyForSoorat(Company company) {
		return getHibernateTemplate().find("from Service service where service.company=? and service.enabled=1 and service.serviceExpired=0",company);
	}
	
	public List<Service> findByExample(Service service) {
		return getHibernateTemplate().findByExample(service);
	}
}
