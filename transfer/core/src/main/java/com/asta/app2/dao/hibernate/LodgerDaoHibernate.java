/* *Class created on [ Jun 4, 2008 | 9:36:13 PM ] */
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.LodgerDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.Lodger;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class LodgerDaoHibernate extends GenericDaoHibernate<Lodger, Long>
		implements LodgerDao {
	public LodgerDaoHibernate() {
		super(Lodger.class);
	}

	public List<Lodger> findLodgerByPresenter(Company company,String presenter) {
		return getHibernateTemplate().find("from Lodger where company=? and presenter=?",new Object[]{company,presenter});
	}

	public List<Lodger> getAllLodgerByCompany(Company company) {
		return getHibernateTemplate().find("from Lodger where company=?",company);
	}

}
