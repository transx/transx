package com.asta.app2.webapp.action;

import com.asta.app2.model.Lodger;
import com.asta.app2.service.LodgerManager;

public class LodgerFormTestOff extends BasePageTestCase {
    private LodgerForm bean;
    private LodgerManager lodgerManager;
        
    public void setLodgerManager(LodgerManager lodgerManager) {
        this.lodgerManager = lodgerManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new LodgerForm();
        bean.setLodgerManager(lodgerManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

 /*   public void testAdd() throws Exception {
        Lodger lodger = new Lodger();

        // enter all required fields
        bean.setLodger(lodger);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getLodger());
        assertFalse(bean.hasErrors());
    }
  */

   /* public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getLodger());
        Lodger lodger = bean.getLodger();

        // update required fields
        bean.setLodger(lodger);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }
*/
    public void testRemove() throws Exception {
        log.debug("testing remove...");
        Lodger lodger = new Lodger();
        lodger.setId(-2L);
        bean.setLodger(lodger);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}