package com.asta.app2.webapp.action;

import com.asta.app2.service.CashTemplateManager;
import com.asta.app2.service.CompanyManager;
import com.asta.app2.model.CashTemplate;
import com.asta.app2.model.Company;
import com.asta.app2.webapp.action.BasePageTestCase;

public class CashTemplateFormTest extends BasePageTestCase {
    private CashTemplateForm bean;
    private CashTemplateManager cashTemplateManager;
    private CompanyManager companyManager;    
    
    public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
	}

	public void setCashTemplateManager(CashTemplateManager cashTemplateManager) {
        this.cashTemplateManager = cashTemplateManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new CashTemplateForm();
        bean.setCashTemplateManager(cashTemplateManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testAdd() throws Exception {
        CashTemplate cashTemplate = new CashTemplate();

        // enter all required fields
        cashTemplate.setNumber("۴");
        cashTemplate.setEnabled(Boolean.FALSE);
        cashTemplate.setCompany(companyManager.get(-1L));
        bean.setCashTemplate(cashTemplate);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getCashTemplate());
        assertFalse(bean.hasErrors());
    }

    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getCashTemplate());
        CashTemplate cashTemplate = bean.getCashTemplate();

        // update required fields
        cashTemplate.setNumber("HuK");
        cashTemplate.setEnabled(Boolean.FALSE);
        bean.setCashTemplate(cashTemplate);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testRemove() throws Exception {
        log.debug("testing remove...");
        CashTemplate cashTemplate = new CashTemplate();
        cashTemplate.setId(-2L);
        bean.setCashTemplate(cashTemplate);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}