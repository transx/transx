/* *Class created on [ Jun 3, 2008 | 6:46:00 PM ] */
package com.asta.app2.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.asta.app2.dao.DriverDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.Driver;
import com.asta.app2.service.DriverManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class DriverManagerImpl extends GenericManagerImpl<Driver, Long>
		implements DriverManager {
	private DriverDao driverDao;

	public DriverManagerImpl(DriverDao driverDao) {
		super(driverDao);
		this.driverDao = driverDao;
	}

	public List<Driver> findDriverByCode(Company company,String code) {
		return driverDao.findDriverByCode(company,code);
	}

	public Map<String, String> getMap(Company company, boolean withEmpty) {
		List<Driver> drivers = driverDao.getAllDriverByCompany(company);
		Map<String, String> map = new TreeMap<String, String>();
		map.clear();
		if (withEmpty)
			map.put("", "");

		for (Driver driver : drivers) {
			map.put(driver.toString() == null ? "" : driver.toString(), driver
					.getId().toString());
		}
		return map;
	}
}
