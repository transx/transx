package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Country;

public interface CountryDao extends GenericDao<Country, Long>{
	public List<Country> getCountrys(Country country); 
	public List<Country> findByName(String name);
}
