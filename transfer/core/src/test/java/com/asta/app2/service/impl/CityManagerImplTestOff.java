package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.CityDao;
import com.asta.app2.model.City;
import com.asta.app2.service.BaseManagerMockTestCase;

public class CityManagerImplTestOff extends BaseManagerMockTestCase {
	private CityManagerImpl manager = null;
	private CityDao dao = null;

	@Before
	public void setUp() {
		dao = context.mock(CityDao.class);
		manager = new CityManagerImpl(dao);
	}

	@After
	public void tearDown() {
		manager = null;
	}

	@Test
	public void testFindByName() {
		log.debug("testing findByName......");
		final List<City> cities = new ArrayList<City>();
		context.checking(new Expectations() {
			{
				one(dao).findByName("Tehran");
				will(returnValue(cities));
			}
		});
		List<City> result = manager.findByName("Tehran");
		assertSame(cities,result);
	}

}
