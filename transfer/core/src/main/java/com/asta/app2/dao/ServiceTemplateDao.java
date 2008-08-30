/* *Class created on [ Jun 2, 2008 | 10:11:48 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Company;
import com.asta.app2.model.ServiceTemplate;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface ServiceTemplateDao extends GenericDao<ServiceTemplate, Long>{
	List<ServiceTemplate> findServiceTemplateByPath(long path_id);

	List<ServiceTemplate> getAllByCompany(Company company);

	ServiceTemplate findServiceTemplateByTemplate(ServiceTemplate template);

	ServiceTemplate findServiceTemplateByTemplateWithTime(ServiceTemplate template);
}


