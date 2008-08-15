package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.model.Country;
import com.asta.app2.service.CountryManager;

public class CountryList extends BasePage implements Serializable {
	private CountryManager countryManager;

	public void setCountryManager(CountryManager countryManagerer) {
		this.countryManager = countryManagerer;
	}

	public CountryList() {
		setSortColumn("id"); // sets the default sort column
	}

	public List getCountrys() {
		return sort(countryManager.getCountrys(null));
	}
	
	public List getCountrys(Country country) {
		return sort(countryManager.getCountrys(country));
	}
	
	public List findByName(String name) {
		return countryManager.findByName(name);
	}
}
