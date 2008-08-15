/* *Class created on [ Jun 2, 2008 | 10:28:03 PM ] */
package com.asta.app2.service.impl;

import java.util.List;

import com.asta.app2.dao.ServiceTemplateDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.ServiceTemplate;
import com.asta.app2.service.ServiceTemplateManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ServiceTemplateManagerImpl extends
		GenericManagerImpl<ServiceTemplate, Long> implements ServiceTemplateManager{
	private ServiceTemplateDao serviceTemplateDao;
	
	public ServiceTemplateManagerImpl(ServiceTemplateDao serviceTemplateDao) {
		super(serviceTemplateDao);
		this.serviceTemplateDao = serviceTemplateDao;
	}

	public List<ServiceTemplate> findServiceTemplateByPath(long path_id) {
		return serviceTemplateDao.findServiceTemplateByPath(path_id);
	}

	public List<ServiceTemplate> getAllByCompany(Company company) {
		return serviceTemplateDao.getAllByCompany(company);
	}

}
