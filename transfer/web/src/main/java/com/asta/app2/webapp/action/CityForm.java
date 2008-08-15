package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.Map;

import com.asta.app2.model.City;
import com.asta.app2.service.CityManager;
import com.asta.app2.service.CountryManager;
import com.asta.app2.util.BundleUtil;

public class CityForm extends BasePage implements Serializable {
	private Long id;
	private City city = new City();
	private CityManager cityManager;
	private String countryID;
	private CountryManager countryManager;
	private Map<String, String> model;

	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}

	public void setCountryManager(CountryManager countryManager) {
		this.countryManager = countryManager;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String delete() {
		cityManager.remove(city.getId());
		addMessage("city.deleted",city.getId().toString());

		return "list";
	}

	public String edit() {
		if (id != null) {
			city = cityManager.get(id);
			setCountryID(city.getCountry().getId().toString());
		} else {
			city = new City();
		}

		return "edit";
	}

	public String save() {
		boolean isNew = (city.getId() == null);
		
		city.setCountry(countryManager.get(Long.valueOf(getCountryID()).longValue()));
		cityManager.save(city);

		String key = (isNew) ? "city.added" : "city.updated";
		addMessage(key);

		if (isNew) {
			return "list";
		} else {
			return "edit";
		}
	}

	public String getCountryID() {
		if (countryID == null) {
			countryID = countryManager.findByName(BundleUtil.getMessageBundle("default.city.country")).get(0).getId().toString();
		}
		return countryID;
	}

	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}

	public Map<String, String> getModel() { // f:selectItems value
		if (model == null) {
			model = countryManager.getMap();
		}
		return model;
	}
}