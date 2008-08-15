package com.asta.app2.webapp.action;

import com.asta.app2.model.Lodger;
import com.asta.app2.service.LodgerManager;

public class LodgerListTestOff extends BasePageTestCase {
    private LodgerList bean;
    private LodgerManager lodgerManager;

    public void setLodgerManager(LodgerManager lodgerManager) {
        this.lodgerManager = lodgerManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new LodgerList();
        bean.setLodgerManager(lodgerManager);
        
        // add a test lodger to the database
        Lodger lodger = new Lodger();

        // enter all required fields

        lodgerManager.save(lodger);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getLodgers().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}