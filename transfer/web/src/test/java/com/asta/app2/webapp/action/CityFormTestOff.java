package com.asta.app2.webapp.action;

import com.asta.app2.model.City;
import com.asta.app2.service.CityManager;
import com.asta.app2.service.CountryManager;

public class CityFormTestOff extends BasePageTestCase {
	private CityForm bean;
	private CityManager cityManager;
	private CountryManager countryManager;

	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}

	public void setCountryManager(CountryManager countryManager) {
		this.countryManager = countryManager;
	}

	@Override
	protected void onSetUp() throws Exception {
		super.onSetUp();
		bean = new CityForm();
		bean.setCityManager(cityManager);
		bean.setCountryManager(countryManager);
	}

	@Override
	protected void onTearDown() throws Exception {
		super.onTearDown();
		bean = null;
	}
/*
	public void testAdd() throws Exception {
		City city = new City();

		// enter all required fields
		city.setName("wOiFtYsWuHtCqFf");
		bean.setCity(city);

		assertEquals("list", bean.save());
		assertFalse(bean.hasErrors());
	}

	public void testEdit() throws Exception {
		log.debug("testing edit...");
		bean.setId(-1L);

		assertEquals("edit", bean.edit());
		assertNotNull(bean.getCity());
		assertFalse(bean.hasErrors());
	}

	public void testSave() {
		log.debug("testing save...");
		bean.setId(-1L);

		assertEquals("edit", bean.edit());
		assertNotNull(bean.getCity());
		City city = bean.getCity();

		// update required fields
		city.setName("SnXsEbNaXaDlDdVbUtEbMbXy");
		bean.setCity(city);

		assertEquals("edit", bean.save());
		assertFalse(bean.hasErrors());
	}*/

	public void testRemove() throws Exception {
		log.debug("testing remove...");
		City city = new City();
		city.setId(-2L);
		bean.setCity(city);

		assertEquals("list", bean.delete());
		assertFalse(bean.hasErrors());
	}

	public void testGetModel() throws Exception {
		log.debug("testing getCountryMap......");
		assertTrue(countryManager.getMap().size() >= 1);
	}
}