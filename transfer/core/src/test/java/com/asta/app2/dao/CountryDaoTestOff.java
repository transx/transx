package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Country;


public class CountryDaoTestOff extends BaseDaoTestCase{
	private CountryDao countryDao = null;
	
	public void setCountryDao(CountryDao countryDao){
		this.countryDao = countryDao;
	}
	
	public void testGetCountrys() throws Exception{
		log.debug("testing getCountrys......");
		List<Country> countrys = countryDao.getCountrys(null);
		assertTrue(countrys.size()>0);
	}
	
	public void testFindByName() throws Exception{
		log.debug("testing findByName......");
		List<Country> countries = countryDao.findByName("ایران");
		assertTrue(countries.size()>0);
	}
}
