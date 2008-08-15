package com.asta.app2.webapp.action;

import com.asta.app2.model.Soorat;
import com.asta.app2.service.SooratManager;

public class SooratListTestOff extends BasePageTestCase {
    private SooratList bean;
    private SooratManager sooratManager;

    public void setSooratManager(SooratManager sooratManager) {
        this.sooratManager = sooratManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new SooratList();
        bean.setSooratManager(sooratManager);
        
        // add a test soorat to the database
        Soorat soorat = new Soorat();

        // enter all required fields
        soorat.setSeri("EmXiUzGdLzUdJhMsSaIn");
        soorat.setSerial("HfAdBsOxPo");
        soorat.setIssued(Boolean.FALSE);
        soorat.setDestroyed(Boolean.FALSE);

        sooratManager.save(soorat);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getSoorats().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}