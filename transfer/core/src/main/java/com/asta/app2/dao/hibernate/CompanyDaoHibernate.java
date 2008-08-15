/* *Class created on [ Jun 1, 2008 | 9:58:42 AM ] */
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.CompanyDao;
import com.asta.app2.model.Company;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CompanyDaoHibernate extends GenericDaoHibernate<Company, Long> implements CompanyDao{
	
	public CompanyDaoHibernate() {
		super(Company.class);
	}

	public List<Company> findByCode(String code) {
		return getHibernateTemplate().find("from Company c where c.code=?",code);
	}

}
