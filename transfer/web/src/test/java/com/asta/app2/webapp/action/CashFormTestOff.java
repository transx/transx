package com.asta.app2.webapp.action;

import com.asta.app2.service.CashManager;
import com.asta.app2.service.GenericManager;
import com.asta.app2.model.Cash;
import com.asta.app2.webapp.action.BasePageTestCase;

public class CashFormTestOff extends BasePageTestCase {
    private CashForm bean;
    private CashManager cashManager;
        
    public void setCashManager(CashManager cashManager) {
        this.cashManager = cashManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new CashForm();
        bean.setCashManager(cashManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testAdd() throws Exception {
        Cash cash = new Cash();

        // enter all required fields
        bean.setCash(cash);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getCash());
        assertFalse(bean.hasErrors());
    }

    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getCash());
        Cash cash = bean.getCash();

        // update required fields
        bean.setCash(cash);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testRemove() throws Exception {
        log.debug("testing remove...");
        Cash cash = new Cash();
        cash.setId(-2L);
        bean.setCash(cash);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}