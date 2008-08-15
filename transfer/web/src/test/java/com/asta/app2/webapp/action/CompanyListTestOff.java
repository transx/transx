package com.asta.app2.webapp.action;

import com.asta.app2.service.CompanyManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CompanyListTestOff extends BasePageTestCase {
    private CompanyList bean;
    private CompanyManager companyManager;

    public void setCompanyManager(CompanyManager companyManager) {
        this.companyManager = companyManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new CompanyList();
        bean.setCompanyManager(companyManager);
        
//        // add a test company to the database
//        Company company = new Company();
//        // enter all required fields
//        company.setName("aZjGqUrXtNm");
//        company.setCode("AxRvJrSoHkAaApFoHr");
//        companyManager.save(company);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getCompanies().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}