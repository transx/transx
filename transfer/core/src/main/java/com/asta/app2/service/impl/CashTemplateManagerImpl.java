/* *Class created on [ Oct 14, 2008 | 8:18:42 PM ] */ 
package com.asta.app2.service.impl;

import com.asta.app2.dao.CashTemplateDao;
import com.asta.app2.dao.GenericDao;
import com.asta.app2.model.CashTemplate;
import com.asta.app2.service.CashManager;
import com.asta.app2.service.CashTemplateManager;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CashTemplateManagerImpl extends GenericManagerImpl<CashTemplate, Long> implements CashTemplateManager{
	private CashTemplateDao cashTemplateDao;
	
	public CashTemplateManagerImpl(CashTemplateDao cashTemplateDao) {
		super(cashTemplateDao);
		this.cashTemplateDao = cashTemplateDao;
	}

}

 
