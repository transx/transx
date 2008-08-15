package com.asta.app2.webapp.action;

import com.asta.app2.model.Driver;
import com.asta.app2.service.DriverManager;

public class DriverFormTestOff extends BasePageTestCase {
    private DriverForm bean;
    private DriverManager driverManager;
        
    public void setDriverManager(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new DriverForm();
        bean.setDriverManager(driverManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    /*public void testAdd() throws Exception {
        Driver driver = new Driver();

        // enter all required fields
        driver.setLicenceNumber("" + Math.random());
        driver.setLicenceIssue("4444lDmAxWwLj");
        driver.setInsuranceNumber("4444lXkBwFl");
        driver.setInsuranceDeadline(new java.util.Date());
        bean.setPersonID("-1");
        bean.setDriver(driver);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getDriver());
        assertFalse(bean.hasErrors());
    }

    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getDriver());
        Driver driver = bean.getDriver();

        // update required fields
        driver.setLicenceNumber("555SvCrQq");
        driver.setLicenceIssue("555OtVbQi");
        driver.setInsuranceNumber("555Xq");
        driver.setInsuranceDeadline(new java.util.Date());
        bean.setDriver(driver);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }*/

    public void testRemove() throws Exception {
        log.debug("testing remove...");
        Driver driver = new Driver();
        driver.setId(-2L);
        bean.setDriver(driver);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}