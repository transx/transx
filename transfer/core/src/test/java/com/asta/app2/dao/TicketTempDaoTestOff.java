/* *Class created on [ Jun 24, 2008 | 12:31:01 PM ] */
package com.asta.app2.dao;

import com.asta.app2.model.Service;
import com.asta.app2.model.TicketTemp;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class TicketTempDaoTestOff extends BaseDaoTestCase {
	private TicketTempDao ticketTempDao;
	private ServiceDao serviceDao;

/*	public void testFindTTbyService() {
		log.debug("testing findTicketTempsByService......");
		Service service = serviceDao.get(-1L);
		assertTrue(ticketTempDao.findTicketTempsByService(service).size() >= 1);
		log.debug("the size of list is :"
				+ ticketTempDao.findTicketTempsByService(service).size());

	}*/

/*	public void testFindTTbyTicketTemp(){
		log.debug("testing findTicketTempByServiceAndPassenger");
		TicketTemp ticketTemp = ticketTempDao.get(-1L);
		assertTrue(ticketTempDao.findTicketTempsByServiceAndPassenger(ticketTemp).size() >= 1);
		log.debug("the size of list is :"
				+ ticketTempDao.findTicketTempsByServiceAndPassenger(ticketTemp).size());

	}*/
	
	public void setTicketTempDao(TicketTempDao ttDao) {
		this.ticketTempDao = ttDao;
	}

	public void setServiceDao(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

}
