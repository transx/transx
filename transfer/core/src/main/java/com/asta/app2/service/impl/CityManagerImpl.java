package com.asta.app2.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.asta.app2.dao.CityDao;
import com.asta.app2.model.City;
import com.asta.app2.service.CityManager;

public class CityManagerImpl extends GenericManagerImpl<City, Long> implements
		CityManager {
	private CityDao dao;

	public CityManagerImpl(CityDao cityDao) {
		super(cityDao);
		this.dao = cityDao;
	}

	public List<City> findByName(String name) {
		return dao.findByName(name);
	}

	public Map<String, String> getCityMap(boolean withEmpty) {
		List<City> cities = dao.getAll();
		Map<String, String> map = new TreeMap<String, String>();
		map.clear();
		if (withEmpty) {
			map.put("", null);
		}
		for (City city : cities) {
			map.put(city.toString() == null ? "" : city.toString(), city
					.getId().toString());
		}
		return map;
	}

}
