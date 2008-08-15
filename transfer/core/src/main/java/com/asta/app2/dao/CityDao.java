package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.City;

public interface CityDao extends GenericDao<City, Long> {

	List<City> findByName(String name);

}
