package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.CountryDao;
import com.asta.app2.model.Country;
import com.asta.app2.service.BaseManagerMockTestCase;

public class CountryManagerImplTestOff extends BaseManagerMockTestCase {
	private CountryManagerImpl manager = null;
	private CountryDao dao = null;

	@Before
	public void setUp() {
		dao = context.mock(CountryDao.class);
		manager = new CountryManagerImpl(dao);
	}

	@After
	public void tearDown() {
		manager = null;
	}

	@Test
	public void testGetCountrys() {
		log.debug("test getCountrys......");
		final List<Country> countries = new ArrayList<Country>();
		// set expected behavior on dao
		context.checking(new Expectations() {
			{
				one(dao).getCountrys(null);
				will(returnValue(countries));
			}
		});

		List<Country> result = manager.getCountrys(null);
		assertSame(countries, result);
	}

	@Test
	public void testFindByName() {
		log.debug("testing findByName......");
		final List<Country> countries = new ArrayList<Country>();
		// set expected behavior on dao
		context.checking(new Expectations() {
			{
				one(dao).findByName("Iran");
				will(returnValue(countries));
			}
		});
		List<Country> result = manager.findByName("Iran");
		assertSame(countries, result);
	}
}
