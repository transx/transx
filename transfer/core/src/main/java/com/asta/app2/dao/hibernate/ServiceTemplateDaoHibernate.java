/* *Class created on [ Jun 2, 2008 | 10:15:08 PM ] */ 
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.ServiceTemplateDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.ServiceTemplate;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ServiceTemplateDaoHibernate extends GenericDaoHibernate<ServiceTemplate, Long> implements ServiceTemplateDao{

	public ServiceTemplateDaoHibernate() {
		super(ServiceTemplate.class);
	}

	public List<ServiceTemplate> findServiceTemplateByPath(long path_id) {
		return getHibernateTemplate().find("from ServiceTemplate where path_id=?",path_id);
	}

	public List<ServiceTemplate> getAllByCompany(Company company) {
		return getHibernateTemplate().find("from ServiceTemplate as sertmp where sertmp.company=?",company);
	}

}


