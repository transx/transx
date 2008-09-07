package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.asta.app2.Constants;
import com.asta.app2.model.CarKind;
import com.asta.app2.model.Path;
import com.asta.app2.model.Service;
import com.asta.app2.service.ServiceManager;

public class ServiceList extends BasePage implements Serializable {
    private ServiceManager serviceManager;
    private List<Service> listService;
    private Service service = new Service();
    private String pathID;
    private String carKindID;

	public ServiceList() {
        setSortColumn("datebook"); // sets the default sort column
        setAscending(true);
    }

    public List<Service> getServices() {
        return sort(getListService());
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
    
    public String search(){
    	if (!getPathID().equals(Constants.EMPTY))
    		service.setPath(new Path(Long.valueOf(getPathID()).longValue()));
    	
    	if (!getCarKindID().equals(Constants.EMPTY))
    		service.setCarKind(new CarKind(Long.valueOf(getCarKindID()).longValue()));
    	
    	setListService(serviceManager.searchByExample(service));
    	return Constants.NO_WHERE;
    }

	public List<Service> getListService() {
		if (listService == null)
			listService = serviceManager.getAllServices(getCurrentUser().getCompany());
		return listService;
	}

	public void setListService(List<Service> listService) {
		this.listService = listService;
	}
	
    public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public void setServiceManager(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }
    
    public String getPathID() {
		return pathID;
	}

	public void setPathID(String pathID) {
		this.pathID = pathID;
	}

	public String getCarKindID() {
		return carKindID;
	}

	public void setCarKindID(String carKindID) {
		this.carKindID = carKindID;
	}
}


