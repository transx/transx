/* *Class created on [ Jun 10, 2008 | 9:14:47 AM ] */
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Cash;
import com.asta.app2.model.Service;
import com.asta.app2.model.Ticket;
import com.asta.app2.service.CashManager;
import com.asta.app2.service.ChairManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class TicketDaoTestOff extends BaseDaoTestCase {
	private TicketDao ticketDao;
	private ChairManager chairManager;
	private ServiceDao serviceDao;
	private CashManager cashManager;
	
	public void setCashManager(CashManager cashManager) {
		this.cashManager = cashManager;
	}

	public void setServiceDao(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	public void setChairManager(ChairManager chairManager) {
		this.chairManager = chairManager;
	}

/*	public void testFindTicketsByService() {
		log.debug("testing findTicketsByService.......");
		Service service = serviceDao.get(-3L);
		assertTrue(ticketDao.getTicketsByService(service).size() >= 1);
		log.debug(ticketDao.getTicketsByService(service).size());
	}

	public void testGetAllTicketsByCashId(){
		log.debug("testing getAllTicketsByCashId......");
		Cash cash = cashManager.get(-3L);
		List<Ticket> list = ticketDao.getAllTicketsByCashId(cash);
		assertTrue(list.size() >= 0);
		log.debug("size of list :"+list.size());
	}*/
	
/*	public void testAddDeleteUpdateTicketChair() {
		Ticket ticket = ticketDao.get(-1L);
		assertEquals(5, ticket.getChairs().size());
		ticket.setIssueDate(new Date());
		ticketDao.save(ticket);
		flush();
		assertEquals(5, ticket.getChairs().size());
		
		Chair chair = chairManager.get(11L);
		ticket.getChairs().add(chair);
		ticketDao.save(ticket);
		flush();
		assertEquals(6, ticket.getChairs().size());
		
		ticket.addChair(chair);
		ticketDao.save(ticket);
		flush();
		assertEquals(6, ticket.getChairs().size());
		
		ticket.getChairs().remove(chair);
		ticketDao.save(ticket);
		flush();
		assertEquals(5, ticket.getChairs().size());
		
	}*/

}
