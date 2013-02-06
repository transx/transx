/* *Class created on [ Jun 7, 2008 | 6:18:40 PM ] */
package com.asta.app2.dao.hibernate;

import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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

	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public List<Service> getAllServices(Company company) {
		return getHibernateTemplate().find("from Service service where service.company=? and service.serviceExpired=?", new Object[]{ company, false });
	}
	
	public List<Service> getAllServicesReadyForReserve(Company company) {
		return getHibernateTemplate().find("from Service service where service.company=? and service.enabled=? and service.serviceExpired=? and service.opened=?", new Object[]{ company, true, false, true });
	}

	public List<Service> getAllServicesReadyForSoorat(Company company) {
		return getHibernateTemplate().find("from Service service where service.company=? and service.enabled=? and service.serviceExpired=?", new Object[]{ company, true, false });
	}
	
	public List<Service> findByExample(Service service) {
		return getHibernateTemplate().findByExample(service);
	}
}
