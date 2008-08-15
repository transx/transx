package com.asta.app2.service;

import java.util.List;
import java.util.Map;

import com.asta.app2.model.Country;

public interface CountryManager extends GenericManager<Country, Long> {
	
	List<Country> getCountrys(Country country);
	List<Country> findByName(String name);
	Map<String, String> getMap();
}
