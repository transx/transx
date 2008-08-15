package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.service.CityManager;

public class CityList extends BasePage implements Serializable {
	private CityManager cityManager;

	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}

	public CityList() {
		setSortColumn("id"); // sets the default sort column
	}

	public List getCities() {
		return sort(cityManager.getAll());
	}
}
