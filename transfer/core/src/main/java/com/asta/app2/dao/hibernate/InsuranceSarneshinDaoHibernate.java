/* *Class created on [ Jul 26, 2008 | 12:27:20 PM ] */
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.InsuranceSarneshinDao;
import com.asta.app2.model.InsuranceSarneshin;
import com.asta.app2.model.enums.Distance;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class InsuranceSarneshinDaoHibernate extends
		GenericDaoHibernate<InsuranceSarneshin, Long> implements
		InsuranceSarneshinDao {

	public InsuranceSarneshinDaoHibernate() {
		super(InsuranceSarneshin.class);
	}

	public List<InsuranceSarneshin> findISbyDistanceCapacity(Distance distance,
			Integer capacity) {
		return getHibernateTemplate().find("from InsuranceSarneshin as insur where insur.distance=? and insur.max>=? and insur.min<=?",new Object[]{distance,capacity,capacity});
	}

}
