/* *Class created on [ Jun 10, 2008 | 9:17:15 AM ] */
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Cash;
import com.asta.app2.model.Company;
import com.asta.app2.model.Service;
import com.asta.app2.model.Ticket;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface TicketDao extends GenericDao<Ticket, Long> {

	List<Ticket> getAllTickets(Company company);

	List<Ticket> getTicketsByService(Company company, Service service);

	List<Ticket> getAllTicketsByCashId(Company company, Cash cash);
}
