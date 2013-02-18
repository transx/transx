/* *Class created on [ Jul 21, 2008 | 11:56:40 PM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.CarKindDao;
import com.asta.app2.dao.PathDao;
import com.asta.app2.dao.RateDao;
import com.asta.app2.model.CarKind;
import com.asta.app2.model.Path;
import com.asta.app2.model.Rate;
import com.asta.app2.model.enums.Quality;
import com.asta.app2.model.enums.RateType;
import com.asta.app2.service.BaseManagerMockTestCase;
import com.asta.app2.service.CarKindManager;
import com.asta.app2.service.PathManager;
import com.asta.app2.service.RateManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class RateManagerImplTestOff extends BaseManagerMockTestCase {
	private RateDao dao;
	private RateManager manager;
	private PathDao pathDao;
	private PathManager pathManager;
	
	@Before
	public void onSetUp() {
		dao = context.mock(RateDao.class);
		manager = new RateManagerImpl(dao);
		pathDao = context.mock(PathDao.class);
		pathManager = new PathManagerImpl(pathDao);
	}

	@Test
	public void testFindRateForTicket() {
		log.debug("testing findRateForTicket");
		final List<Rate> rates = new ArrayList<Rate>();
		final Path path = new Path();
		context.checking(new Expectations() {
			{
				one(pathDao).get(-1L);
				will(returnValue(path));
			}
		});
		Path pathResult = pathManager.get(-1L);
		assertSame(path, pathResult);

		final Rate rate = new Rate(RateType.ORDINARY, path, Quality.UP);
		context.checking(new Expectations() {
			{
				one(dao).findRateForTicket(rate);
				will(returnValue(rates));
			}
		});
		List<Rate> result = manager.findRateForTicket(rate);
		assertSame(rates, result);
	}

	public void setManager(RateManager manager) {
		this.manager = manager;
	}
}
