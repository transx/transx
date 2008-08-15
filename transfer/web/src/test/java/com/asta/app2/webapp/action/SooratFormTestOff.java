package com.asta.app2.webapp.action;

import com.asta.app2.model.Soorat;
import com.asta.app2.service.SooratManager;

public class SooratFormTestOff extends BasePageTestCase {
    private SooratForm bean;
    private SooratManager sooratManager;
        
    public void setSooratManager(SooratManager sooratManager) {
        this.sooratManager = sooratManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new SooratForm();
        bean.setSooratManager(sooratManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

//    public void testAdd() throws Exception {
//        Soorat soorat = new Soorat();
//
//        // enter all required fields
//        soorat.setSeri("MrXlUxAcOeJxGmEvLxBr");
//        soorat.setSerial("SuWiVrYeSp");
//        soorat.setIssued(Boolean.FALSE);
//        soorat.setDestroyed(Boolean.FALSE);
//        bean.setSoorat(soorat);
//
//        assertEquals("list", bean.save());
//        assertFalse(bean.hasErrors());
//    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getSoorat());
        assertFalse(bean.hasErrors());
    }

    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getSoorat());
        Soorat soorat = bean.getSoorat();

        // update required fields
        soorat.setSeri("LpJoDiBsIgBzUyWeHyHr");
        soorat.setSerial("TuDvOzMaRj");
        soorat.setIssued(Boolean.FALSE);
        soorat.setDestroyed(Boolean.FALSE);
        bean.setSoorat(soorat);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testRemove() throws Exception {
        log.debug("testing remove...");
        Soorat soorat = new Soorat();
        soorat.setId(-2L);
        bean.setSoorat(soorat);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}