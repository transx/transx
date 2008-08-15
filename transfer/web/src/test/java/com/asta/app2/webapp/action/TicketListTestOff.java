package com.asta.app2.webapp.action;

import com.asta.app2.model.Ticket;
import com.asta.app2.service.TicketManager;

public class TicketListTestOff extends BasePageTestCase {
    private TicketList bean;
    private TicketManager ticketManager;

    public void setTicketManager(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new TicketList();
        bean.setTicketManager(ticketManager);
        
        // add a test ticket to the database
    //    Ticket ticket = new Ticket();

        // enter all required fields

      //  ticketManager.save(ticket);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getTickets().size() >= 1);
        log.debug(bean.getTickets().size());
        assertFalse(bean.hasErrors());
    }
}