/* *Class created on [ Jun 3, 2008 | 6:16:32 PM ] */
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.DriverDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.Driver;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class DriverDaoHibernate extends GenericDaoHibernate<Driver, Long>
		implements DriverDao {
	private DriverDao driverDao;

	public DriverDaoHibernate() {
		super(Driver.class);
	}

	public void setDriverDao(DriverDao driverDao) {
		this.driverDao = driverDao;
	}

	public List<Driver> findDriverByCode(Company company,String code) {
		return getHibernateTemplate().find("from Driver where company=? and code=?",new Object[]{company,code});
	}

	public List<Driver> getAllDriverByCompany(Company company) {
		return getHibernateTemplate().find("from Driver where company=?",company);
	}

}
