package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.Constants;
import com.asta.app2.model.CarKind;
import com.asta.app2.model.Company;
import com.asta.app2.model.Path;
import com.asta.app2.model.Service;
import com.asta.app2.service.ServiceManager;

public class ServiceList extends BasePage implements Serializable {
    private ServiceManager serviceManager;
    private List<Service> listService;
    private List<Service> listServiceForReserve;
    private Service service = new Service();
    private Service serviceTemp = new Service();
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
    	return sort(getListServiceForReserve());
    }
    
    public List<Service> getServicesReadyForSoorat(){
    	return sort(serviceManager.getAllServicesReadyForSoorat(getCurrentUser().getCompany()));
    }
    
    public String goForReservePage(){
    	return "edit";
    }
    
    public String search(){
    	service.setCompany(new Company(getCurrentUser().getCompany().getId()));
    	
    	if (getPathID().equals(Constants.EMPTY))
    		service.setPath(null);
    	else
    		service.setPath(new Path(Long.valueOf(getPathID()).longValue()));
    	
    	if (getCarKindID().equals(Constants.EMPTY))
    		service.setCarKind(null);
    	else
    		service.setCarKind(new CarKind(Long.valueOf(getCarKindID()).longValue()));
    	
    	setListService(serviceManager.searchByExample(service));
    	return Constants.NO_WHERE;
    }
    
    public String searchForReserve(){
    	service.setCompany(new Company(getCurrentUser().getCompany().getId()));
    	service.setEnabled(Boolean.TRUE);
    	service.setOpened(Boolean.TRUE);
    	service.setServiceExpired(Boolean.FALSE);
    	
    	if (getPathID().equals(Constants.EMPTY))
    		service.setPath(null);
    	else
    		service.setPath(new Path(Long.valueOf(getPathID()).longValue()));
    	
    	if (getCarKindID().equals(Constants.EMPTY))
    		service.setCarKind(null);
    	else
    		service.setCarKind(new CarKind(Long.valueOf(getCarKindID()).longValue()));
    	
    	setListServiceForReserve(serviceManager.searchByExample(service));
    	return Constants.NO_WHERE;
    }
	public List<Service> getListService() {
		if (listService == null){
			service.setCompany(new Company(getCurrentUser().getCompany().getId()));
			listService = serviceManager.searchByExample(service);
		}
		return listService;
	}
	
	public List<Service> getListServiceForReserve() {
		if (listServiceForReserve == null){
			service.setCompany(new Company(getCurrentUser().getCompany().getId()));
			service.setEnabled(Boolean.TRUE);
			service.setOpened(Boolean.TRUE);
			service.setServiceExpired(Boolean.FALSE);
			listServiceForReserve = serviceManager.searchByExample(service);
		}
		return listServiceForReserve;
	}
	
	public String closeService() {
		serviceTemp.setOpened(Boolean.FALSE);
		serviceManager.save(serviceTemp);
		return Constants.NO_WHERE;
	}

	public String openService() {
		serviceTemp.setOpened(Boolean.TRUE);
		serviceManager.save(serviceTemp);
		return Constants.NO_WHERE;
	}
	public String enableService() {
		serviceTemp.setEnabled(Boolean.TRUE);
		serviceTemp.setOpened(Boolean.TRUE);
		serviceManager.save(serviceTemp);
//		return "return";
		return Constants.NO_WHERE;
	}

	public String expireService() {
		serviceTemp.setOpened(Boolean.FALSE);
		serviceTemp.setServiceExpired(Boolean.TRUE);
		serviceManager.save(serviceTemp);
		return Constants.NO_WHERE;
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

	public Service getServiceTemp() {
		return serviceTemp;
	}

	public void setServiceTemp(Service serviceTemp) {
		this.serviceTemp = serviceTemp;
	}

	public void setListServiceForReserve(List<Service> listServiceForReserve) {
		this.listServiceForReserve = listServiceForReserve;
	}
}


