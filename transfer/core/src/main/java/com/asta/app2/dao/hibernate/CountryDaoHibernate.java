package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.CountryDao;
import com.asta.app2.model.Country;

public class CountryDaoHibernate extends GenericDaoHibernate<Country, Long> implements CountryDao{

	public CountryDaoHibernate() {
		super(Country.class);
	}

	public List<Country> getCountrys(Country country) {
		 return getHibernateTemplate().find("from Country c order by upper(c.name)");
	}

	public List<Country> findByName(String name) {
		return getHibernateTemplate().find("from Country where name=?",name);
	}
	
}
