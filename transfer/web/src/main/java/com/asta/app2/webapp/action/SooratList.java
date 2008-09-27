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
    private ServiceManager serviceManager; 
    private Soorat sooratTemp = new Soorat();
    
    private List<Soorat> listSooratPrivate;
    
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

    public String searchSooratPrivate(){
    	sooratTemp.setCompany(new Company(getCurrentUser().getCompany().getId()));
    	sooratTemp.setSooratType(SooratType.PRIVATE);
    	setlistSooratPrivate(sooratManager.searchByExample(sooratTemp));
    	return Constants.NO_WHERE;
    }

    public List<Soorat> getlistSooratPrivate() {
		if (listSooratPrivate == null){
			sooratTemp = new Soorat();
			sooratTemp.setCompany(new Company(getCurrentUser().getCompany().getId()));
			sooratTemp.setSooratType(SooratType.PRIVATE);
			listSooratPrivate = sooratManager.searchByExample(sooratTemp);
		}
		return listSooratPrivate;
	}
	

    public void setSooratManager(SooratManager sooratManager) {
        this.sooratManager = sooratManager;
    }
    
	public void setlistSooratPrivate(List<Soorat> listSooratPrivate) {
		this.listSooratPrivate = listSooratPrivate;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public Soorat getSooratTemp() {
		return sooratTemp;
	}

	public void setSooratTemp(Soorat sooratTemp) {
		this.sooratTemp = sooratTemp;
	}
}

