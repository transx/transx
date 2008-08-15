package com.asta.app2.webapp.action;

import com.asta.app2.model.TicketTemp;
import com.asta.app2.service.TicketTempManager;

public class TicketTempFormTestOff extends BasePageTestCase {
    private TicketTempForm bean;
    private TicketTempManager ticketTempManager;
        
    public void setTicketTempManager(TicketTempManager ticketTempManager) {
        this.ticketTempManager = ticketTempManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new TicketTempForm();
        bean.setTicketTempManager(ticketTempManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

/*    public void testAdd() throws Exception {
        TicketTemp ticketTemp = new TicketTemp();

        // enter all required fields
        ticketTemp.setCommitted(Boolean.FALSE);
        bean.setTicketTemp(ticketTemp);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getTicketTemp());
        assertFalse(bean.hasErrors());
    }

    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getTicketTemp());
        TicketTemp ticketTemp = bean.getTicketTemp();

        // update required fields
        ticketTemp.setCommitted(Boolean.FALSE);
        bean.setTicketTemp(ticketTemp);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }
*/
    public void testRemove() throws Exception {
        log.debug("testing remove...");
        TicketTemp ticketTemp = new TicketTemp();
        ticketTemp.setId(-2L);
        bean.setTicketTemp(ticketTemp);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}