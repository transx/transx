/* *Class created on [ Jun 5, 2008 | 5:26:30 PM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.CarDao;
import com.asta.app2.model.Car;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CarManagerImplTestOff extends BaseManagerMockTestCase {
	private CarManagerImpl manager;
	private CarDao dao;

	@Before
	public void setUp() {
		dao = context.mock(CarDao.class);
		manager = new CarManagerImpl(dao);
	}

	@After
	public void tearDown() {
		manager = null;
	}

/*	@Test
	public void testFindCarByCode() {
		log.debug("testing findCarByCode......");
		final List<Car> cars = new ArrayList<Car>();
		context.checking(new Expectations() {
			{
				one(dao).findCarByCode("C0001");
				will(returnValue(cars));
			}
		});
		List<Car> result = manager.findCarByCode("C0001");
		assertSame(cars, result);
	}
*/}
