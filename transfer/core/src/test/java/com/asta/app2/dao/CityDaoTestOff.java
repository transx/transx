package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.City;
import com.asta.app2.util.BundleUtil;


public class CityDaoTestOff extends BaseDaoTestCase {
	private CityDao cityDao = null;

	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	public void testFindByName() throws Exception {
		log.debug("test findByName......");
		List<City> cities = cityDao.findByName("بندرعباس");
		assertTrue(cities.size() >= 1);
	}

}
