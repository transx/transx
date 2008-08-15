package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.model.Cash;
import com.asta.app2.model.Ticket;
import com.asta.app2.service.CashManager;
import com.asta.app2.service.TicketManager;

public class TicketList extends BasePage implements Serializable {
    private TicketManager ticketManager;
    private Long cashId;
    private CashManager cashManager;
    private Cash cash = null;
    


    public TicketList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getTickets() {
        return sort(ticketManager.getAllTickets(getCurrentUser().getCompany()));
    }
    
    public List<Ticket> getSoldTicketsByCashId(){
    	if (cashId != null)
    		cash  = cashManager.get(cashId);
    	return sort(ticketManager.getAllTicketsByCashId(getCurrentUser().getCompany(),cash));
    }
	public Cash getCash() {
		return cash;
	}

	public void setCash(Cash cash) {
		this.cash = cash;
	}

	public void setCashManager(CashManager cashManager) {
		this.cashManager = cashManager;
	}

	public void setTicketManager(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
    }
    public void setCashId(Long cashId) {
		this.cashId = cashId;
	}
	public Long getCashId() {
		return cashId;
	}
	
	public String viewSoldTicketsByCash(){
		return "viewSoldTicket";
	}
	
}

