/* *Class created on [ Jun 10, 2008 | 9:20:52 AM ] */ 
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.TicketDao;
import com.asta.app2.model.Cash;
import com.asta.app2.model.Company;
import com.asta.app2.model.Service;
import com.asta.app2.model.Ticket;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class TicketDaoHibernate extends GenericDaoHibernate<Ticket, Long> implements TicketDao{

	public TicketDaoHibernate() {
		super(Ticket.class);
	}

	public List<Ticket> getAllTickets(Company company) {
		return getHibernateTemplate().find("from Ticket as tic where tic.company=?",company);
	}
	
	/**
	 * @param service
	 * @param company		//this parameter exist on service
	 * @return List<Ticket>
	 */
	public List<Ticket> getTicketsByService(Company company,Service service) {
		return getHibernateTemplate().find("from Ticket as tic where tic.service=? and tic.issued=?", new Object[]{ service, true });
	}

	/**
	 * @param cash
	 * @param company		//this parameter exist on service
	 * @return List<Ticket>
	 */
	public List<Ticket> getAllTicketsByCashId(Company company,Cash cash) {
		return getHibernateTemplate().find("from Ticket as t where t.cash=?",cash);
	}

}
