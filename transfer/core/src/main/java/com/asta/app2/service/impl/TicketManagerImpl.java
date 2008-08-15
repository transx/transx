/* *Class created on [ Jun 10, 2008 | 10:03:18 AM ] */
package com.asta.app2.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.asta.app2.dao.TicketDao;
import com.asta.app2.model.Cash;
import com.asta.app2.model.Company;
import com.asta.app2.model.Service;
import com.asta.app2.model.Setting;
import com.asta.app2.model.Ticket;
import com.asta.app2.model.TicketTemp;
import com.asta.app2.service.CashManager;
import com.asta.app2.service.SettingManager;
import com.asta.app2.service.TicketDidNotIssuedException;
import com.asta.app2.service.TicketIssuedException;
import com.asta.app2.service.TicketManager;
import com.asta.app2.service.TicketTempManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class TicketManagerImpl extends GenericManagerImpl<Ticket, Long>
		implements TicketManager {
	private TicketDao ticketDao;
	private TicketTempManager ticketTempManager;
	private SettingManager settingManager;
	private CashManager cashManager;
	
	public void setCashManager(CashManager cashManager) {
		this.cashManager = cashManager;
	}

	public void setSettingManager(SettingManager settingManager) {
		this.settingManager = settingManager;
	}

	public TicketManagerImpl(TicketDao ticketDao) {
		super(ticketDao);
		this.ticketDao = ticketDao;
	}

	public List<Ticket> getAllTickets(Company company) {
		return ticketDao.getAllTickets(company);
	}

	public List<Ticket> getTicketsByService(Company company,Service service) {
		return ticketDao.getTicketsByService(company,service);
	}

	@Transactional
	public Ticket saveTicket(Company company,Ticket ticket, Long id)
			throws TicketIssuedException,TicketDidNotIssuedException {
			Cash cash = cashManager.get(ticket.getCash().getId());
		if (id.equals(new Long(0L))){
			try {
				ticket.setCompany(company);
				ticket = ticketDao.save(ticket);
				cash.setTotalPrice(cash.getTotalPrice() + ticket.getTotal());
				cash = cashManager.save(cash);
				ticket.setCash(cash);
				ticketDao.save(ticket);
			}catch(Exception e){
				throw new TicketDidNotIssuedException("This message will never shown.");
			}
		}else{
			Setting setting = settingManager.get(1L); 
			TicketTemp tt = ticketTempManager.get(id);
			if (tt.isCommitted()) {
				throw new TicketIssuedException("This message will be hidden .");
			}else {
				try{
					ticket.setCompany(company);
					ticket.setNumber(setting.getTicketNumber());
					ticket.setIssued(true);
					ticket = ticketDao.save(ticket);
					cash.setCount(cash.getCount()+1);
					cash.setTotalPrice(cash.getTotalPrice() + ticket.getTotal());
					cash = cashManager.save(cash);
					ticket.setCash(cash);
					ticketDao.save(ticket);
					tt.setCommitted(true);
					tt.setCommitDate(new Date());
					ticketTempManager.save(tt);
					setting.setTicketNumber(setting.getTicketNumber() + 1);
					settingManager.save(setting);
				}catch(Exception e){
					throw new TicketDidNotIssuedException("This message will be hidden .");
				}
			}
		}
		return ticket;
	}

	public void setTicketTempManager(TicketTempManager ticketTempManager) {
		this.ticketTempManager = ticketTempManager;
	}

	public List<Ticket> getAllTicketsByCashId(Company company,Cash cash) {
		return ticketDao.getAllTicketsByCashId(company,cash);
	}

}
