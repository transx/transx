/* *Class created on [ Jun 2, 2008 | 10:26:03 PM ] */ 
package com.asta.app2.service;

import java.util.List;

import com.asta.app2.model.Company;
import com.asta.app2.model.ServiceTemplate;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface ServiceTemplateManager extends GenericManager<ServiceTemplate, Long>{
	List<ServiceTemplate> findServiceTemplateByPath(long path_id);
	List<ServiceTemplate> getAllByCompany(Company company);
	ServiceTemplate findServiceTemplateByTemplate(ServiceTemplate template);
	ServiceTemplate findServiceTemplateByTemplateWithTime(ServiceTemplate serviceTemplate);
	
}


