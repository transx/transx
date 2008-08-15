package com.asta.app2.webapp.action;

import com.asta.app2.model.ServiceTemplate;
import com.asta.app2.service.ServiceTemplateManager;

public class ServiceTemplateListTestOff extends BasePageTestCase {
    private ServiceTemplateList bean;
    private ServiceTemplateManager serviceTemplateManager;

    public void setServiceTemplateManager(ServiceTemplateManager serviceTemplateManager) {
        this.serviceTemplateManager = serviceTemplateManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new ServiceTemplateList();
        bean.setServiceTemplateManager(serviceTemplateManager);
        
        // add a test serviceTemplate to the database
        ServiceTemplate serviceTemplate = new ServiceTemplate();

        // enter all required fields

        serviceTemplateManager.save(serviceTemplate);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getServiceTemplates().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}