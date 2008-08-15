package com.asta.app2.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.asta.app2.dao.CountryDao;
import com.asta.app2.model.Country;
import com.asta.app2.service.CountryManager;

public class CountryManagerImpl extends GenericManagerImpl<Country, Long>
		implements CountryManager {
	CountryDao countryDao;

	public CountryManagerImpl(CountryDao countryDao) {
		super(countryDao);
		this.countryDao = countryDao;
	}

	public List<Country> getCountrys(Country country) {
		return countryDao.getCountrys(null);
	}

	public List<Country> findByName(String name) {
		return countryDao.findByName(name);
	}

	public Map<String, String> getMap() {
		List<Country> countries = countryDao.getAll();
			Map<String , String> map = new TreeMap<String, String>();
			map.clear();
			// model.put("(Empty)", "0"); // Add EMPTY element if needed
			for (Country o : countries) {
				map.put(o.toString() == null ? "" : o.toString(), o.getId()
						.toString());
			}
		return map;
	}
}
