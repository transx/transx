package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.service.CompanyManager;

public class CompanyList extends BasePage implements Serializable {
	private CompanyManager companyManager;

	public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
	}

	public CompanyList() {
		setSortColumn("id"); // sets the default sort column
	}

	public List getCompanies() {
		return sort(companyManager.getAll());
	}
}
