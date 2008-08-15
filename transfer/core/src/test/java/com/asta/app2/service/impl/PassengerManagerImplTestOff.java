/* *Class created on [ Jun 7, 2008 | 12:32:24 AM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.PassengerDao;
import com.asta.app2.model.Passenger;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class PassengerManagerImplTestOff extends BaseManagerMockTestCase {
	private PassengerManagerImpl manager;
	private PassengerDao dao;

	@Before
	public void setUp() {
		dao = context.mock(PassengerDao.class);
		manager = new PassengerManagerImpl(dao);
	}

	@After
	public void tearDown() {
		manager = null;
	}

	@Test
	public void testFindPassengerByLastName() {
		log.debug("testing findPassengerByLastName......");
		final List<Passenger> passengers = new ArrayList<Passenger>();
		context.checking(new Expectations() {
			{
				one(dao).findPassengerByLastName("محسنی");
				will(returnValue(passengers));
			}
		});
		List<Passenger> result = manager.findPassengerByLastName("محسنی");
		assertSame(passengers, result);
	}
}
