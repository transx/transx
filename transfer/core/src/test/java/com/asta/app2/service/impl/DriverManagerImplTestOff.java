/* *Class created on [ Jun 3, 2008 | 6:42:52 PM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.DriverDao;
import com.asta.app2.model.Driver;
import com.asta.app2.service.BaseManagerMockTestCase;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class DriverManagerImplTestOff extends BaseManagerMockTestCase {
	private DriverManagerImpl manager;
	private DriverDao dao;

	@Before
	public void setUp() {
		dao = context.mock(DriverDao.class);
		manager = new DriverManagerImpl(dao);
	}

	@After
	public void tearDown() {
		manager = null;
	}

/*	@Test
	public void testFindDriverByCode() {
		log.debug("testing findDriverByCode");
		final List<Driver> drivers = new ArrayList<Driver>();
		context.checking(new Expectations() {
			{
				one(dao).findDriverByCode("111111111");
				will(returnValue(drivers));
			}
		});
		List<Driver> result = manager.findDriverByCode("111111111");
		assertSame(drivers, result);
	}
*/
/*	@Test
	public void testGetMap() {

		final List<Driver> drivers = new ArrayList<Driver>();
		context.checking(new Expectations() {
			{
				one(dao).getAll();
				will(returnValue(drivers));
			}
		});
		List<Driver> result = manager.getAll();
		assertSame(drivers, result);

	}*/

}
