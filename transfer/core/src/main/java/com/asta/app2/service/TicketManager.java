/* *Class created on [ Jun 10, 2008 | 10:02:12 AM ] */ 
package com.asta.app2.service;

import java.util.List;

import com.asta.app2.exception.TicketDidNotIssuedException;
import com.asta.app2.exception.TicketIssuedException;
import com.asta.app2.model.Cash;
import com.asta.app2.model.Company;
import com.asta.app2.model.Service;
import com.asta.app2.model.Ticket;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface TicketManager extends GenericManager<Ticket, Long>{
	
	List<Ticket> getAllTickets(Company company);
	List<Ticket> getTicketsByService(Company company,Service service);
	Ticket saveTicket(Company company,Ticket ticket, Long id) throws TicketIssuedException,TicketDidNotIssuedException;
	List<Ticket> getAllTicketsByCashId(Company company,Cash cash);
}


