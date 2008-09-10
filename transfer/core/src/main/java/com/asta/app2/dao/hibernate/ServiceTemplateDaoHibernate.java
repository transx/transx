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

	public ServiceTemplate findServiceTemplateByTemplate(ServiceTemplate template) {
		List<ServiceTemplate> sts =getHibernateTemplate().find("from ServiceTemplate as sertmp where sertmp.company=? and sertmp.path=?",new Object[]{template.getCompany(),template.getPath()}); 
		if (sts.size()>0)
			return sts.get(0);
		else
			return save(template);
	}

	public ServiceTemplate findServiceTemplateByTemplateWithTime(ServiceTemplate template) {
		List<ServiceTemplate> sts =getHibernateTemplate().find("from ServiceTemplate as sertmp where sertmp.company=? and sertmp.path=? and sertmp.carKind=? and sertmp.timed=?",new Object[]{template.getCompany(),template.getPath(),template.getCarKind(),template.getTimed()}); 
		if (sts.size()>0)
			return sts.get(0);
		else
			return save(template);
	}

}


