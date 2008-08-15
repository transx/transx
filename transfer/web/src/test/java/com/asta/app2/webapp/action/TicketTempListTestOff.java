package com.asta.app2.webapp.action;

import com.asta.app2.model.TicketTemp;
import com.asta.app2.service.TicketTempManager;

public class TicketTempListTestOff extends BasePageTestCase {
    private TicketTempList bean;
    private TicketTempManager ticketTempManager;

    public void setTicketTempManager(TicketTempManager ticketTempManager) {
        this.ticketTempManager = ticketTempManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new TicketTempList();
        bean.setTicketTempManager(ticketTempManager);
        
        // add a test ticketTemp to the database
        TicketTemp ticketTemp = new TicketTemp();

        // enter all required fields
        ticketTemp.setCommitted(Boolean.FALSE);

        ticketTempManager.save(ticketTemp);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getTicketTemps().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}