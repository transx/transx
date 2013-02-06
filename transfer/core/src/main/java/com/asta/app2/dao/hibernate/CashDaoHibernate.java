/* *Class created on [ Jul 28, 2008 | 8:23:04 PM ] */
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.CashDao;
import com.asta.app2.model.Cash;
import com.asta.app2.model.Company;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CashDaoHibernate extends GenericDaoHibernate<Cash, Long> implements CashDao{

	public CashDaoHibernate() {
		super(Cash.class);
	}
	
	public List<Cash> getAllCash(Company company) {
		return getHibernateTemplate().find("from Cash as c where c.company=?",company);
	}

	public List<Cash> getAllEnabledCash(Company company) {
		return getHibernateTemplate().find("from Cash as c where c.company=? and c.enabled=?",new Object[]{ company, true });
	}

	public List<Cash> getAllEnabledCashNotExpired(Company company) {
		return getHibernateTemplate().find("from Cash as c where c.company=? and c.enabled=? and c.expired=?", new Object[]{ company, true, false });
	}

}
