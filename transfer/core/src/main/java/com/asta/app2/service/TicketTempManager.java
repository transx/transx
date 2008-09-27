/* *Class created on [ Jun 24, 2008 | 12:52:37 PM ] */ 
package com.asta.app2.service;

import java.util.List;

import com.asta.app2.exception.ChairReservedException;
import com.asta.app2.model.Company;
import com.asta.app2.model.Service;
import com.asta.app2.model.TicketTemp;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface TicketTempManager extends GenericManager<TicketTemp, Long>{
	List<TicketTemp> findTicketTempsByService(Company company,Service service);

	List<TicketTemp> getAllTicketTemp(Company company);

	TicketTemp saveTicketTemp(Company company,TicketTemp ticketTemp) throws ChairReservedException;

	List<TicketTemp> getAllTicketTempReadyForSell(Company company);
}


