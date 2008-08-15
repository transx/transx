package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.model.Service;
import com.asta.app2.service.ServiceManager;

public class ServiceList extends BasePage implements Serializable {
    private ServiceManager serviceManager;

    public void setServiceManager(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    public ServiceList() {
        setSortColumn("datebook"); // sets the default sort column
        setAscending(true);
    }

    public List<Service> getServices() {
        return sort(serviceManager.getAllServices(getCurrentUser().getCompany()));
    }
    
    public List<Service> getServicesReadyForReserve(){
    	return sort(serviceManager.getAllServicesReadyForReserve(getCurrentUser().getCompany()));
    }
    
    public List<Service> getServicesReadyForSoorat(){
    	return sort(serviceManager.getAllServicesReadyForSoorat(getCurrentUser().getCompany()));
    }
    
    public String goForReservePage(){
    	return "edit";
    }
}


