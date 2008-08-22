/* *Class created on [ Jul 15, 2008 | 6:59:22 PM ] */ 
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.SooratDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.Service;
import com.asta.app2.model.Soorat;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SooratDaoHibernate extends GenericDaoHibernate<Soorat, Long> implements SooratDao{
	
	
	public SooratDaoHibernate() {
		super(Soorat.class);
	}

	public List<Soorat> findSooratsByService(Service service) {
		return getHibernateTemplate().find("from Soorat as soor where soor.service=?",service);
	}

	public List<Soorat> getAllSoorat(Company company) {
		return getHibernateTemplate().find("from Soorat as soor where soor.company=?",company);
	}

	public List<Soorat> getAllSooratActive(Company company) {
		return getHibernateTemplate().find("from Soorat as soor where soor.company=? and soor.issued=1 and soor.destroyed=0",company);
	}

}


