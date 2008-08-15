package com.asta.app2.webapp.action;

import com.asta.app2.model.Ticket;
import com.asta.app2.service.TicketManager;

public class TicketFormTestOff extends BasePageTestCase {
    private TicketForm bean;
    private TicketManager ticketManager;
        
    public void setTicketManager(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new TicketForm();
        bean.setTicketManager(ticketManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

/*    public void testAdd() throws Exception {
        Ticket ticket = new Ticket();

        // enter all required fields
        bean.setTicket(ticket);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getTicket());
        assertFalse(bean.hasErrors());
    }
 */

/*    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getTicket());
        Ticket ticket = bean.getTicket();

        // update required fields
        bean.setTicket(ticket);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }*/

    public void testRemove() throws Exception {
        log.debug("testing remove...");
        Ticket ticket = new Ticket();
        ticket.setId(-2L);
        bean.setTicket(ticket);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}