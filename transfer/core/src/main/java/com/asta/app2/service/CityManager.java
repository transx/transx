package com.asta.app2.service;

import java.util.List;
import java.util.Map;

import com.asta.app2.model.City;

public interface CityManager extends GenericManager<City, Long> {
	List<City> findByName(String name);
	Map<String, String> getCityMap(boolean withEmpty);
}
