package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.model.TicketTemp;
import com.asta.app2.service.TicketTempManager;

public class TicketTempList extends BasePage implements Serializable {
    private TicketTempManager ticketTempManager;

    public void setTicketTempManager(TicketTempManager ticketTempManager) {
        this.ticketTempManager = ticketTempManager;
    }

    public TicketTempList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getTicketTemps() {
        return sort(ticketTempManager.getAllTicketTemp(getCurrentUser().getCompany()));
    }
    
    public List<TicketTemp> getTicketTempReadyForSell(){
    	return sort(ticketTempManager.getAllTicketTempReadyForSell(getCurrentUser().getCompany()));
    }
}

