package com.asta.app2.webapp.action;

import com.asta.app2.model.ServiceTemplate;
import com.asta.app2.service.CarKindManager;
import com.asta.app2.service.CompanyManager;
import com.asta.app2.service.PathManager;
import com.asta.app2.service.ServiceTemplateManager;

public class ServiceTemplateFormTestOff extends BasePageTestCase {
    private ServiceTemplateForm bean;
    private ServiceTemplateManager serviceTemplateManager;
    private CarKindManager carKindManager;
    private PathManager pathManager;

	public void setCarKindManager(CarKindManager carKindManager) {
		this.carKindManager = carKindManager;
	}

	public void setPathManager(PathManager pathManager) {
		this.pathManager = pathManager;
	}

	public void setServiceTemplateManager(ServiceTemplateManager serviceTemplateManager) {
        this.serviceTemplateManager = serviceTemplateManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new ServiceTemplateForm();
        bean.setServiceTemplateManager(serviceTemplateManager);
        bean.setCarKindManager(carKindManager);
        bean.setPathManager(pathManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

/*    public void testAdd() throws Exception {
        ServiceTemplate serviceTemplate = new ServiceTemplate();
        // enter all required fields
        bean.setCompanyID("-1");
        bean.setCarKindID("-1");
        bean.setPathID("-1");
        bean.setServiceTemplate(serviceTemplate);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getServiceTemplate());
        assertFalse(bean.hasErrors());
    }*/

/*    public void testSave() {
        log.debug("testing save...");
        bean.setId(-3L);
        bean.setCompanyID("-1");
        bean.setCarKindID("-1");
        bean.setPathID("-1");
        assertEquals("edit", bean.edit());
        assertNotNull(bean.getServiceTemplate());
        ServiceTemplate serviceTemplate = bean.getServiceTemplate();

        // update required fields
        bean.setServiceTemplate(serviceTemplate);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }*/

    public void testRemove() throws Exception {
        log.debug("testing remove...");
        ServiceTemplate serviceTemplate = new ServiceTemplate();
        serviceTemplate.setId(-2L);
        bean.setServiceTemplate(serviceTemplate);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}