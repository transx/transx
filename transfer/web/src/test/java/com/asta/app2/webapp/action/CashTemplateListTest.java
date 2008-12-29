package com.asta.app2.webapp.action;

import com.asta.app2.webapp.action.BasePageTestCase;
import com.asta.app2.service.CashTemplateManager;
import com.asta.app2.service.GenericManager;
import com.asta.app2.model.CashTemplate;
import com.asta.app2.model.Company;

public class CashTemplateListTest extends BasePageTestCase {
    private CashTemplateList bean;
    private CashTemplateManager cashTemplateManager;

    public void setCashTemplateManager(CashTemplateManager cashTemplateManager) {
        this.cashTemplateManager = cashTemplateManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new CashTemplateList();
        bean.setCashTemplateManager(cashTemplateManager);
        
        // add a test cashTemplate to the database
        CashTemplate cashTemplate = new CashTemplate();

        // enter all required fields
        cashTemplate.setNumber("PqL");
        cashTemplate.setEnabled(Boolean.FALSE);
        cashTemplate.setCompany(new Company(-2L));
        cashTemplateManager.save(cashTemplate);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getCashTemplates().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}