package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.CityDao;
import com.asta.app2.model.City;

public class CityDaoHibernate extends GenericDaoHibernate<City, Long> implements
		CityDao {

	public CityDaoHibernate() {
		super(City.class);
	}

	public List<City> findByName(String name) {
		return getHibernateTemplate().find("from City where name=?",name);
	}

}
