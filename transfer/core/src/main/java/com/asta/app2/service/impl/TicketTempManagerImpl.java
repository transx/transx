/* *Class created on [ Jun 24, 2008 | 1:16:21 PM ] */
package com.asta.app2.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.asta.app2.dao.TicketTempDao;
import com.asta.app2.exception.ChairReservedException;
import com.asta.app2.model.Chair;
import com.asta.app2.model.Company;
import com.asta.app2.model.Service;
import com.asta.app2.model.Ticket;
import com.asta.app2.model.TicketTemp;
import com.asta.app2.service.TicketManager;
import com.asta.app2.service.TicketTempManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class TicketTempManagerImpl extends GenericManagerImpl<TicketTemp, Long>
		implements TicketTempManager {
	private TicketTempDao dao;
	private TicketManager ticketManager;

	public void setTicketManager(TicketManager ticketManager) {
		this.ticketManager = ticketManager;
	}

	public TicketTempManagerImpl(TicketTempDao ticketTempDao) {
		super(ticketTempDao);
		dao = ticketTempDao;
	}

	public List<TicketTemp> findTicketTempsByService(Company company,Service service) {
		return dao.findTicketTempsByService(company,service);
	}

	public List<TicketTemp> getAllTicketTemp(Company company) {
		return dao.getAllTicketTemp(company);
	}
	
	@Transactional
	public TicketTemp saveTicketTemp(Company company,TicketTemp ticketTemp) throws ChairReservedException {
		TicketTemp oldTt = null;
		List<TicketTemp> tts = findTicketTempByServiceAndPassenger(company,ticketTemp);
		if (tts.size() > 0)
			oldTt = tts.get(0);

		List<TicketTemp> allTts = findTicketTempsByService(company,ticketTemp.getService());
		List<Ticket> allTs = ticketManager.getTicketsByService(company,ticketTemp.getService());

		List<Chair> allReservedChairs = new ArrayList<Chair>();

		for (TicketTemp element : allTts) {
			for (Chair chair : element.getChairs()) {
				allReservedChairs.add(chair);
			}
		}
		if (oldTt != null) {
			for (Chair chair : oldTt.getChairs()) {
				allReservedChairs.remove(chair);
			}
		}
		for (Ticket element : allTs) {
			for (Chair chair : element.getChairs()) {
				allReservedChairs.add(chair);
			}
		}
		boolean confirm = true;
		Set<Chair> notValidChairs = new HashSet<Chair>();
		for (Chair chairReserved : allReservedChairs) {
			for (Chair chairForReserve : ticketTemp.getChairs()) {
				if (chairForReserve.equals(chairReserved)) {
					notValidChairs.add(chairForReserve);
					confirm = false;
				}
			}
		}

		if (!confirm) {
			for(Chair element: notValidChairs){
//				log.debug("not valid chair:"+element.getId().toString());
				ticketTemp.getChairs().remove(element);
			}
			throw new ChairReservedException("Chairs '" + notValidChairs.toString() + "' already reserved!");
		} else {
			if (oldTt != null) {
				oldTt.setChairs(ticketTemp.getChairs());
				oldTt.setReserverId(ticketTemp.getReserverId());
				oldTt.setPath(ticketTemp.getPath());
				oldTt.setReserveDate(ticketTemp.getReserveDate());
				oldTt.setCount(ticketTemp.getChairs().size());
				log.debug("still in old entity:"+oldTt.getId());
				return dao.saveTicketTemp(company,oldTt);
			} else {
				log.debug("New Save Accoured!");
				return dao.saveTicketTemp(company,ticketTemp);
			}
		}

	}

	public List<TicketTemp> findTicketTempByServiceAndPassenger(Company company,TicketTemp ticketTemp) {
		return dao.findTicketTempsByServiceAndPassenger(company,ticketTemp);
	}

	public List<TicketTemp> getAllTicketTempReadyForSell(Company company) {
		return dao.getAllTicketTempReadyForSell(company);
	}

}
