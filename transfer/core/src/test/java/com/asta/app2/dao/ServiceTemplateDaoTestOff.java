/* *Class created on [ Jun 2, 2008 | 10:11:02 PM ] */ 
package com.asta.app2.dao;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ServiceTemplateDaoTestOff extends BaseDaoTestCase{
	private ServiceTemplateDao serviceTemplateDao;

	public void setServiceTemplateDao(ServiceTemplateDao serviceTemplateDao) {
		this.serviceTemplateDao = serviceTemplateDao;
	}
	
	public void testFindServiceTemplateByPath(){
		log.debug("testing findServiceTemplateByPath......");
		assertTrue(serviceTemplateDao.findServiceTemplateByPath(-1L).size() >=1);
	}
}


