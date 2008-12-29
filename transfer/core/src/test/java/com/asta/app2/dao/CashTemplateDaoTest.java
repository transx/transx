/* *Class created on [ Oct 14, 2008 | 12:36:05 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.CashTemplate;
import com.asta.app2.model.Company;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CashTemplateDaoTest extends BaseDaoTestCase{

	private CashTemplateDao cashTemplateDao;

	public void setCashTemplateDao(CashTemplateDao cashTemplateDao) {
		this.cashTemplateDao = cashTemplateDao;
	}
	
	public void testSearch(){
		log.debug("we are going to test search on CashTemplate!");
		CashTemplate cashTemplate = new CashTemplate();
		cashTemplate.setCompany(new Company(-1L));
		cashTemplate.setEnabled(Boolean.TRUE);
		List<CashTemplate> cts = cashTemplateDao.searchByExample(cashTemplate);
		log.debug("size of list : "+cts.size());
		assertTrue(cts.size() == 2);
	}
}


