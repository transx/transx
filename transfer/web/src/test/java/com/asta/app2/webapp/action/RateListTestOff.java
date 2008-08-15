package com.asta.app2.webapp.action;

import com.asta.app2.webapp.action.BasePageTestCase;
import com.asta.app2.service.RateManager;
import com.asta.app2.model.Rate;

public class RateListTestOff extends BasePageTestCase {
    private RateList bean;
    private RateManager rateManager;

    public void setRateManager(RateManager rateManager) {
        this.rateManager = rateManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new RateList();
        bean.setRateManager(rateManager);
        
        // add a test rate to the database
//        Rate rate = new Rate();
//
//        // enter all required fields
//        rate.setPrice(16740L);
//        rate.setToll(6789L);
//
//        rateManager.save(rate);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getRates().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}