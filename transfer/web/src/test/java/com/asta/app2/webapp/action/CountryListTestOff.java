package com.asta.app2.webapp.action;

import com.asta.app2.model.Country;
import com.asta.app2.service.CountryManager;

public class CountryListTestOff extends BasePageTestCase {
    private CountryList bean;
    private CountryManager countryManager;

    public void setCountryManager(CountryManager countryManager) {
        this.countryManager = countryManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new CountryList();
        bean.setCountryManager(countryManager);
        
        // add a test country to the database
        Country country = new Country();
        country.setName("Pakestan");
        countryManager.save(country);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testGetCountrys() throws Exception {
    	log.debug("testing getCountrys......");
        assertTrue(bean.getCountrys(null).size() >= 1);
        assertFalse(bean.hasErrors());
    }
    
/*    public void testFindByName() throws Exception {
    	log.debug("testing findByName......");
    	assertTrue(bean.findByName("ایران").size()>0);
    }*/
}