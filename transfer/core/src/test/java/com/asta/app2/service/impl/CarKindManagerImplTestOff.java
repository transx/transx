/* *Class created on [ Jun 1, 2008 | 7:49:40 PM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.CarKindDao;
import com.asta.app2.model.CarKind;
import com.asta.app2.service.BaseManagerMockTestCase;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CarKindManagerImplTestOff extends BaseManagerMockTestCase {
	private CarKindManagerImpl manager;
	private CarKindDao dao;

	@Before
	public void setUp() {
		dao = context.mock(CarKindDao.class);
		manager = new CarKindManagerImpl(dao);
	}

	@After
	public void tearDown() {
		manager = null;
	}

	@Test
	public void testFindByName() {
		log.debug("testing findByName......");
		final List<CarKind> carKinds = new ArrayList<CarKind>();
		context.checking(new Expectations() {
			{
				one(dao).findByName("Volvo60");
				will(returnValue(carKinds));
			}
		});
		List<CarKind> result = manager.findByName("Volvo60");
		assertSame(carKinds,result);
	}

}
