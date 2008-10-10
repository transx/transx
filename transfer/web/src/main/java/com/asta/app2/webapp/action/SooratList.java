package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.Constants;
import com.asta.app2.model.Company;
import com.asta.app2.model.Soorat;
import com.asta.app2.model.enums.SooratType;
import com.asta.app2.service.ServiceManager;
import com.asta.app2.service.SooratManager;

public class SooratList extends BasePage implements Serializable {
    private SooratManager sooratManager;
    private Soorat soorat = new Soorat();
    
    private List<Soorat> listSooratPrivate;
    private List<Soorat> listSooratInner;
    
    public SooratList() {
        setSortColumn("id"); // sets the default sort column
    }

    @SuppressWarnings("unchecked")
	public List<Soorat> getSoorats() {
        return sort(sooratManager.getAllSoorat(getCurrentUser().getCompany()));
    }

    public List<Soorat> getSooratsActive() {
    	return sort(sooratManager.getAllSooratActive(getCurrentUser().getCompany()));
    }

    public List<Soorat> getSooratPrivates() {
    	return sort(getlistSooratPrivate());
    }
    public List<Soorat> getSooratInners() {
    	return sort(getListSooratInner());
    }

    public String searchSooratPrivate(){
    	soorat.setCompany(new Company(getCurrentUser().getCompany().getId()));
    	soorat.setSooratType(SooratType.PRIVATE);
    	setlistSooratPrivate(sooratManager.searchByExample(soorat));
    	return Constants.NO_WHERE;
    }

    public String searchSooratInner(){
    	soorat.setCompany(new Company(getCurrentUser().getCompany().getId()));
    	soorat.setSooratType(SooratType.INNER);
    	setListSooratInner(sooratManager.searchByExample(soorat));
    	return Constants.NO_WHERE;
    }
    

	public List<Soorat> getlistSooratPrivate() {
		if (listSooratPrivate == null){
			soorat = new Soorat();
			soorat.setCompany(new Company(getCurrentUser().getCompany().getId()));
			soorat.setSooratType(SooratType.PRIVATE);
			listSooratPrivate = sooratManager.searchByExample(soorat);
		}
		return listSooratPrivate;
	}

    public List<Soorat> getListSooratInner() {
		if (listSooratInner == null){
			soorat = new Soorat();
			soorat.setCompany(new Company(getCurrentUser().getCompany().getId()));
			soorat.setSooratType(SooratType.INNER);
			listSooratInner = sooratManager.searchByExample(soorat);
		}
    	return listSooratInner;
	}

	public void setSooratManager(SooratManager sooratManager) {
        this.sooratManager = sooratManager;
    }
	public void setListSooratInner(List<Soorat> listSooratInner) {
		this.listSooratInner = listSooratInner;
	}
	public void setlistSooratPrivate(List<Soorat> listSooratPrivate) {
		this.listSooratPrivate = listSooratPrivate;
	}
	public Soorat getSoorat() {
		return soorat;
	}
	public void setSoorat(Soorat soorat) {
		this.soorat = soorat;
	}
}

