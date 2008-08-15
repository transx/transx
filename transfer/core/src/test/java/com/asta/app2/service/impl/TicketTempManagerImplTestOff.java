/* *Class created on [ Jun 24, 2008 | 12:51:14 PM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.ServiceDao;
import com.asta.app2.dao.TicketTempDao;
import com.asta.app2.model.Service;
import com.asta.app2.model.TicketTemp;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class TicketTempManagerImplTestOff extends BaseManagerMockTestCase {
	private TicketTempManagerImpl manager;
	private TicketTempDao dao;
	private ServiceDao serviceDao;
	private ServiceManagerImpl serviceManager;
	
	@Before
	public void setUp() {
		dao = context.mock(TicketTempDao.class);
		manager = new TicketTempManagerImpl(dao);
		serviceDao = context.mock(ServiceDao.class);
		serviceManager = new ServiceManagerImpl(serviceDao);
	}

/*	@Test
	public void testFindTicketTempsByService() {
		final List<TicketTemp> tts = new ArrayList<TicketTemp>();
		final Service service = new Service();

		context.checking(new Expectations() {
			{
				one(serviceDao).get(-1L);
				will(returnValue(service));
			}
		});
		Service serviceResult = serviceManager.get(-1L);
		assertSame(service,serviceResult);
		context.checking(new Expectations() {
			{
				one(dao).findTicketTempsByService(service);
				will(returnValue(tts));
			}
		});
		List<TicketTemp> result = manager.findTicketTempsByService(service);
		log.debug("ticketTemp founded by manager : "+result.size());
		assertSame(tts,result);
	}*/

}
