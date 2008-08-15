/* *Class created on [ Jun 24, 2008 | 12:32:05 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Company;
import com.asta.app2.model.Service;
import com.asta.app2.model.TicketTemp;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface TicketTempDao extends GenericDao<TicketTemp, Long>{
	
	List<TicketTemp> getAllTicketTemp(Company company);

	List<TicketTemp> findTicketTempsByService(Company company,Service service);

	List<TicketTemp> findTicketTempsByServiceAndPassenger(Company company,TicketTemp ticketTemp);

	List<TicketTemp> getAllTicketTempReadyForSell(Company company);

	TicketTemp saveTicketTemp(Company company,TicketTemp ticketTemp);
}


