/* *Class created on [ Jun 5, 2008 | 3:10:21 PM ] */ 
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.CarDao;
import com.asta.app2.model.Car;
import com.asta.app2.model.CarKind;
import com.asta.app2.model.Company;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CarDaoHibernate extends GenericDaoHibernate<Car, Long> implements CarDao{

	public CarDaoHibernate() {
		super(Car.class);
	}

	public List<Car> getAllCarByCompany(Company company) {
		return getHibernateTemplate().find("from Car where company=?",company);
	}

	public List<Car> findCarByCode(Company company,String code) {
		return getHibernateTemplate().find("from Car where company=? and code=?",new Object[]{company,code});
	}

	public List<Car> getAllCarByKind(Company company,CarKind carKind) {
		return getHibernateTemplate().find("from Car where company=? and carKind=?",new Object[]{company,carKind});
	}


}


