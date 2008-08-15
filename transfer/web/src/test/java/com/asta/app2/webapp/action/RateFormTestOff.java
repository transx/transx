package com.asta.app2.webapp.action;

import com.asta.app2.service.RateManager;
import com.asta.app2.model.Rate;
import com.asta.app2.webapp.action.BasePageTestCase;

public class RateFormTestOff extends BasePageTestCase {
    private RateForm bean;
    private RateManager rateManager;
        
    public void setRateManager(RateManager rateManager) {
        this.rateManager = rateManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new RateForm();
        bean.setRateManager(rateManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

/*    public void testAdd() throws Exception {
        Rate rate = new Rate();

        // enter all required fields
        rate.setPrice(23786L);
        bean.setRate(rate);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }*/

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getRate());
        assertFalse(bean.hasErrors());
    }

/*    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getRate());
        Rate rate = bean.getRate();

        // update required fields
        rate.setPrice(32439L);
        bean.setRate(rate);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }
    public void testRemove() throws Exception {
        log.debug("testing remove...");
        Rate rate = new Rate();
        rate.setId(-2L);
        bean.setRate(rate);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
 */
}