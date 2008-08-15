package com.asta.app2.webapp.action;

import com.asta.app2.model.Driver;
import com.asta.app2.service.DriverManager;

public class DriverListTestOff extends BasePageTestCase {
    private DriverList bean;
    private DriverManager driverManager;

    public void setDriverManager(DriverManager driverManager) {
        this.driverManager = driverManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new DriverList();
        bean.setDriverManager(driverManager);
        
        // add a test driver to the database
        Driver driver = new Driver();

        // enter all required fields
        driver.setLicenceNumber("111ZiIaYjNlPa");
        driver.setLicenceIssue("1111NaGbBq1XyZjEsQx");
        driver.setInsuranceNumber("111IwHe");
        driver.setInsuranceDeadline(new java.util.Date());

        driverManager.save(driver);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getDrivers().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}