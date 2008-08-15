package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.CityDao;
import com.asta.app2.dao.PathDao;
import com.asta.app2.model.City;
import com.asta.app2.model.Path;
import com.asta.app2.service.CityManager;

public class PathManagerImplTest extends BaseManagerMockTestCase {
	private PathManagerImpl manager = null;
	private PathDao dao = null;
	private CityDao cityDao;
	private CityManager cityManager;

	@Before
	public void setUP() {
		dao = context.mock(PathDao.class);
		manager = new PathManagerImpl(dao);
		cityDao = context.mock(CityDao.class);
		cityManager = new CityManagerImpl(cityDao);
	}

	@After
	public void tearDown() {
		manager = null;
	}

	@Test
	public void testFindByStartCityId() {
		log.debug("testing findByStartCityId......");
		final List<Path> paths = new ArrayList<Path>();
		context.checking(new Expectations() {
			{
				one(dao).findByStartCityId(-2L);
				will(returnValue(paths));
			}
		});
		List<Path> result = manager.findByStartCityId(-2L);
		assertSame(paths, result);
		log.debug("size of paths = "+result.size());
	}

	@Test
	public void testFindByEndCityId() {
		log.debug("testing findByEndCityId......");

		final List<Path> paths = new ArrayList<Path>();
		context.checking(new Expectations() {
			{
				one(dao).findByEndCityId(-3L);
				will(returnValue(paths));
			}
		});
		List<Path> result = manager.findByEndCityId(-3L);
		assertSame(paths, result);
		log.debug("size of paths = "+result.size());
	}
}
