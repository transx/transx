/* *Class created on [ Jun 10, 2008 | 10:01:17 AM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.ServiceDao;
import com.asta.app2.dao.TicketDao;
import com.asta.app2.model.Service;
import com.asta.app2.model.Ticket;
import com.asta.app2.service.BaseManagerMockTestCase;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class TicketManagerImplTestOff extends BaseManagerMockTestCase {
	private TicketManagerImpl manager;
	private TicketDao dao;
	private ServiceManagerImpl serviceManager;
	private ServiceDao serviceDao;

	@Before
	public void setUp() {
		dao = context.mock(TicketDao.class);
		manager = new TicketManagerImpl(dao);
		serviceDao = context.mock(ServiceDao.class);
		serviceManager = new ServiceManagerImpl(serviceDao);
	}

	@After
	public void tearDown() {
		manager = null;
		serviceManager = null;
	}

/*	@Test
	public void testGetTicketsByService() {
		log.debug("testing getTicketsByService......");
		final List<Ticket> tickets = new ArrayList<Ticket>();
		final Service service = new Service();

		context.checking(new Expectations() {
			{
				one(serviceDao).get(-3L);
				will(returnValue(service));
			}
		});
		Service serviceResult = serviceManager.get(-3L);
		assertSame(service, serviceResult);
		
		context.checking(new Expectations() {
			{
				one(dao).getTicketsByService(service);
				will(returnValue(tickets));
			}
		});
		List<Ticket> result = manager.getTicketsByService(service);
		assertSame(tickets, result);
	}*/
}
