package com.asta.app2.webapp.action;

import com.asta.app2.webapp.action.BasePageTestCase;
import com.asta.app2.service.CashManager;
import com.asta.app2.service.GenericManager;
import com.asta.app2.model.Cash;

public class CashListTestOff extends BasePageTestCase {
    private CashList bean;
    private CashManager cashManager;

    public void setCashManager(CashManager cashManager) {
        this.cashManager = cashManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new CashList();
        bean.setCashManager(cashManager);
        
        // add a test cash to the database
        Cash cash = new Cash();

        // enter all required fields

        cashManager.save(cash);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getCashes().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}