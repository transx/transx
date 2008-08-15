package com.asta.app2.webapp.action;

import com.asta.app2.model.Country;
import com.asta.app2.service.CountryManager;

public class CountryFormTestOff extends BasePageTestCase {
    private CountryForm bean;
    private CountryManager countryManager;
        
    public void setCountryManager(CountryManager countryManager) {
        this.countryManager = countryManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new CountryForm();
        bean.setCountryManager(countryManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

/*    public void testAdd() throws Exception {
        Country country = new Country();

        // enter all required fields
        country.setName("qBdBpLcZkUwAyMw");
        bean.setCountry(country);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getCountry());
        assertFalse(bean.hasErrors());
    }

    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getCountry());
        Country country = bean.getCountry();

        // update required fields
        country.setName("NxFqXsDeOnXoXoVlGmWs");
        bean.setCountry(country);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }*/

    public void testRemove() throws Exception {
        log.debug("testing remove...");
        Country country = new Country();
        country.setId(-2L);
        bean.setCountry(country);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}