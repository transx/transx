/* *Class created on [ Jun 7, 2008 | 9:47:46 PM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.ServiceDao;
import com.asta.app2.model.Driver;
import com.asta.app2.model.Service;
import com.asta.app2.service.BaseManagerMockTestCase;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ServiceManagerImplTestOff extends BaseManagerMockTestCase {
	private ServiceManagerImpl manager = null;
	private ServiceDao dao = null;

	@Before
	public void setUp() {
		dao = context.mock(ServiceDao.class);
		manager = new ServiceManagerImpl(dao);
	}
	@Test
	public void testGetService() {
		final Service testData = new Service(1L);
		testData.addDriver(new Driver());

		context.checking(new Expectations() {
			{
				one(dao).get(with(equal(1L)));
				will(returnValue(testData));
			}
		});
		Service service = manager.getService("1");
		assertTrue(service != null);
	}
	
}
