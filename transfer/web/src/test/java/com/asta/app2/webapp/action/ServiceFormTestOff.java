package com.asta.app2.webapp.action;

import com.asta.app2.model.Service;
import com.asta.app2.service.ServiceManager;

public class ServiceFormTestOff extends BasePageTestCase {
    private ServiceForm bean;
    private ServiceManager serviceManager;
        
    public void setServiceManager(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new ServiceForm();
        bean.setServiceManager(serviceManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

/*    public void testAdd() throws Exception {
        Service service = new Service();

        // enter all required fields
        service.setTimed("UfBsJeNqGh");
        service.setDatebook(new java.util.Date());
        bean.setService(service);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }*/
    /*

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getService());
        assertFalse(bean.hasErrors());
    }
    public void testSave() { 
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getService());
        Service service = bean.getService();

        // update required fields
        service.setTimed("DpWlQkTrQt");
        service.setDatebook(new java.util.Date());
        bean.setService(service);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }*/

    public void testRemove() throws Exception {
        log.debug("testing remove...");
        Service service = new Service();
        service.setId(-2L);
        bean.setService(service);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}