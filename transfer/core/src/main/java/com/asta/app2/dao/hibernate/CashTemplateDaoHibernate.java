/* *Class created on [ Oct 14, 2008 | 1:55:59 PM ] */ 
package com.asta.app2.dao.hibernate;

import com.asta.app2.dao.CashTemplateDao;
import com.asta.app2.model.CashTemplate;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CashTemplateDaoHibernate extends GenericDaoHibernate<CashTemplate, Long> implements CashTemplateDao{

	public CashTemplateDaoHibernate() {
		super(CashTemplate.class);
	}

}


