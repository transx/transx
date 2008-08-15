package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.Map;

import com.asta.app2.model.Company;
import com.asta.app2.service.CityManager;
import com.asta.app2.service.CompanyManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CompanyForm extends BasePage implements Serializable {
	private CompanyManager companyManager;
	private Company company = new Company();
	private Long id;
	private CityManager cityManager;
	private String cityID;
	private Map<String, String> cityMap;

	public String getCityID() {
		return cityID;
	}

	public void setCityID(String cityID) {
		this.cityID = cityID;
	}

	public Map<String, String> getCityMap() {
		if (cityMap == null) 
			cityMap = cityManager.getCityMap(false);
		
		return cityMap;
	}

	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}

	public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String delete() {
		companyManager.remove(company.getId());
		addMessage("company.deleted");

		return "list";
	}

	public String edit() {
		if (id != null) {
			company = companyManager.get(id);
			setCityID(company.getCity().getId().toString());
		} else {
			company = new Company();
		}

		return "edit";
	}

	public String save() {
		boolean isNew = (company.getId() == null);
		company.setCity(cityManager.get(Long.valueOf(getCityID()).longValue()));
		companyManager.save(company);

		String key = (isNew) ? "company.added" : "company.updated";
		addMessage(key);

		if (isNew) {
			return "list";
		} else {
			return "edit";
		}
	}
}