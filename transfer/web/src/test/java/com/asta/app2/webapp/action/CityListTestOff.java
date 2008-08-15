package com.asta.app2.webapp.action;

import com.asta.app2.model.City;
import com.asta.app2.service.CityManager;

public class CityListTestOff extends BasePageTestCase {
    private CityList bean;
    private CityManager cityManager;

    public void setCityManager(CityManager cityManager) {
        this.cityManager = cityManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new CityList();
        bean.setCityManager(cityManager);
        
        City city = new City();
        city.setName("BnExSbRuXqJcThNl");

        cityManager.save(city);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getCities().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}