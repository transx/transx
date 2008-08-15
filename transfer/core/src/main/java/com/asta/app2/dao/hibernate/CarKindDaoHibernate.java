/* *Class created on [ Jun 1, 2008 | 6:45:14 PM ] */ 
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.CarKindDao;
import com.asta.app2.model.CarKind;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CarKindDaoHibernate extends GenericDaoHibernate<CarKind, Long> implements CarKindDao{

	public CarKindDaoHibernate() {
		super(CarKind.class);
	}

	public List<CarKind> findByName(String name) {
		return getHibernateTemplate().find("from CarKind where name=?",name);
	}

	public CarKind load(CarKind carKind) {
		//return getHibernateTemplate().load(carKind, carKind.getId());
		return null;
	}	

}


