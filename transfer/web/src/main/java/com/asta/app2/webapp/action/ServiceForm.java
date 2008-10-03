package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;

import com.asta.app2.Constants;
import com.asta.app2.model.City;
import com.asta.app2.model.Driver;
import com.asta.app2.model.Lodger;
import com.asta.app2.model.Path;
import com.asta.app2.model.Service;
import com.asta.app2.model.ServiceTemplate;
import com.asta.app2.model.enums.SooratType;
import com.asta.app2.service.CarKindManager;
import com.asta.app2.service.CarManager;
import com.asta.app2.service.CityManager;
import com.asta.app2.service.DriverManager;
import com.asta.app2.service.LodgerManager;
import com.asta.app2.service.PathManager;
import com.asta.app2.service.ServiceManager;
import com.asta.app2.service.ServiceTemplateManager;
import com.asta.app2.util.BundleUtil;

public class ServiceForm extends BasePage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6475994219784037987L;
	private ServiceManager serviceManager;
	private Service service = new Service();
	private Long id;
	private CarKindManager carKindManager;
	private PathManager pathManager;
	private CarManager carManager;
	private DriverManager driverManager;
	private LodgerManager lodgerManager;
	private CityManager cityManager;
	private ServiceTemplateManager serviceTemplateManager;
	private String carKindID;
	private String pathID;
	private String carID;
	private Map<String, String> availablePaths;
	private Map<String, String> availableCars;
	private String[] serviceDrivers;
	private String[] serviceLodgers;
	private String[] servicePaths;

	public void carKindChanged(ValueChangeEvent event){
		String carKindID = (String) event.getNewValue();
		setAvailableCars(carManager.getCarKindMap(getCurrentUser().getCompany(),carKindManager.get(Long.valueOf(carKindID).longValue())));
		//setAvailablePaths(getAvailablePaths());
	}

	public void pathChanged(ValueChangeEvent pathEvent){
		String pathID = (String) pathEvent.getNewValue();
		setAvailablePaths(pathManager.getSubPathMap(pathManager.get(Long.valueOf(pathID).longValue()).getId()));
		//setAvailableCars(getAvailableCars());
	}

	public void driverChanged(ValueChangeEvent evt){
    	serviceDrivers = (String[]) evt.getNewValue(); 
    }    

	public void lodgerChanged(ValueChangeEvent evt){
		serviceLodgers = (String[]) evt.getNewValue(); 
	}    
	
	public String delete() {
		try{
			serviceManager.remove(service.getId());
			addMessage("service.deleted");
		}catch(Exception e){
			addError("errors.delete.exception");
			return "edit";
		}

		return "list";
	}

	public String edit() {
		if (id != null) {
			service = serviceManager.get(id);
			setCarKindID(service.getCarKind().getId().toString());
			setPathID(service.getPath().getId().toString());
			setAvailableCars(carManager.getCarKindMap(getCurrentUser().getCompany(),service.getCarKind()));
			if (service.getCar() != null)
			setCarID(service.getCar().getId().toString());
		} else {
			service = new Service();
			//make default carKind -2L volvo40
			setCarKindID(carKindManager.get(-2L).getId().toString());
		}

		return "edit";
	}

	public String save() {
		boolean isNew = (service.getId() == null);


		service.setCompany(getCurrentUser().getCompany());

		service.setCarKind(carKindManager.get(Long.valueOf(getCarKindID()).longValue()));
		service.setPath(pathManager.get(Long.valueOf(getPathID()).longValue()));
		service.setTemplate(serviceTemplateManager.findServiceTemplateByTemplate(new ServiceTemplate(getCurrentUser().getCompany(),service.getPath(),service.getCarKind())));
		service.setSooratType(SooratType.INNER);
		
		if (getCarID() != null && !getCarID().equals(Constants.EMPTY))
		service.setCar(carManager.get(Long.valueOf(getCarID()).longValue()));


		setServicePaths(getRequest().getParameterValues("serviceForm:servicePaths"));
		for (int i = 0; (servicePaths != null) && (i < servicePaths.length); i++) {
			long id = Long.valueOf(servicePaths[i]).longValue();
			service.addPath(pathManager.get(id));
		}
		for (int i = 0; (serviceLodgers != null) && (i < serviceLodgers.length); i++) {
			long id = Long.valueOf(serviceLodgers[i]).longValue();
			service.addLodger(lodgerManager.get(id));
			//log.debug("i="+i+", value="+serviceLodgers[i]+"\n");
		}
		for (int i = 0; (serviceDrivers != null) && (i < serviceDrivers.length); i++) {
			long id = Long.valueOf(serviceDrivers[i]).longValue();
			service.addDriver(driverManager.get(id));
		}

		if (isNew)
			service.setServiceExpired(Boolean.FALSE);
		serviceManager.save(service);

		String key = (isNew) ? "service.added" : "service.updated";
		addMessage(key);

		if (isNew) {
			return "list";
		} else {
			return "edit";
		}
	}

/*	public String closeService() {
		if (id != null) {
			Service service = serviceManager.get(id);
			service.setOpened(Boolean.FALSE);
			serviceManager.save(service);
		}
		return Constants.NO_WHERE;
	}

	public String openService() {
		if (id != null) {
			Service service = serviceManager.get(id);
			service.setOpened(Boolean.TRUE);
			serviceManager.save(service);
		}
		return Constants.NO_WHERE;
	}

	public String enableService() {
		if (id != null) {
			Service service = serviceManager.get(id);
			service.setEnabled(Boolean.TRUE);
			service.setOpened(Boolean.TRUE);
			serviceManager.save(service);
		}
		return "return";
	}*/

	public String expireService() {
		if (id != null) {
			Service service = serviceManager.get(id);
			service.setOpened(Boolean.FALSE);
			service.setServiceExpired(Boolean.TRUE);
			serviceManager.save(service);
		}
		return Constants.NO_WHERE;
	}

	public String[] getServiceDrivers() {
		if (serviceDrivers == null){
			serviceDrivers = new String[service.getDrivers().size()];
		
			int i = 0;
			if (serviceDrivers.length > 0) {
				for (Driver driver : service.getDrivers()) {
					serviceDrivers[i] = driver.getId().toString();
					i++;
				}
			}
		}
		return serviceDrivers;
	}

	public String[] getServiceLodgers() {
		if (serviceLodgers == null){
			serviceLodgers = new String[service.getLodgers().size()];
	
			int i = 0;
			if (serviceLodgers.length > 0) {
				for (Lodger lodger : service.getLodgers()) {
					serviceLodgers[i] = lodger.getId().toString();
					i++;
				}
			}
		}
		return serviceLodgers;
	}

	public String[] getServicePaths() {
		servicePaths = new String[service.getPaths().size()];

		int i = 0;
		if (servicePaths.length > 0) {
			for (Path path : service.getPaths()) {
				servicePaths[i] = path.getId().toString();
				i++;
			}
		}
		return servicePaths;
	}

	public void setCarKindManager(CarKindManager carKindManager) {
		this.carKindManager = carKindManager;
	}

	public void setPathManager(PathManager pathManager) {
		this.pathManager = pathManager;
	}

	public void setCarManager(CarManager carManager) {
		this.carManager = carManager;
	}

	public void setDriverManager(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarKindID() {
		return carKindID;
	}

	public void setCarKindID(String carKindID) {
		this.carKindID = carKindID;
	}

	public String getPathID() {
		if (pathID == null){
			List<City> defaultEndCitys = cityManager.findByName(BundleUtil.getMessageBundle("default.service.path.end"));
			pathID = pathManager.findByEndCityId(defaultEndCitys.get(0).getId()).get(0).getId().toString();
		}
		return pathID;
	}

	public void setPathID(String pathID) {
		this.pathID = pathID;
	}

	public String getCarID() {
		return carID;
	}

	public void setCarID(String carID) {
		this.carID = carID;
	}

	public Map<String, String> getAvailablePaths() {
		if (availablePaths == null)
			availablePaths = pathManager.getSubPathMap(service.getPath() == null ? Long.valueOf(getPathID()).longValue()
					: service.getPath().getId());
		return availablePaths;
	}

	public void setServiceDrivers(String[] serviceDrivers) {
		this.serviceDrivers = serviceDrivers;
	}

	public void setLodgerManager(LodgerManager lodgerManager) {
		this.lodgerManager = lodgerManager;
	}

	public void setServiceLodgers(String[] serviceLodgers) {
		this.serviceLodgers = serviceLodgers;
	}

	public void setServicePaths(String[] servicePaths) {
		this.servicePaths = servicePaths;
	}

	public void setAvailablePaths(Map<String, String> availablePaths) {
		this.availablePaths = availablePaths;
	}

	public Map<String, String> getAvailableCars() {
		if (availableCars == null){
			if (carKindID != null)
			availableCars = carManager.getCarKindMap(getCurrentUser().getCompany(),carKindManager.get(Long.valueOf(carKindID).longValue()));
			else{
				availableCars = new HashMap<String, String>();	
				availableCars.put("- - - - - - - - - -", Constants.EMPTY);
			}
		}
		return availableCars;
	}

	public void setAvailableCars(Map<String, String> availableCars) {
		this.availableCars = availableCars;
	}

	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}

	public void setServiceTemplateManager(
			ServiceTemplateManager serviceTemplateManager) {
		this.serviceTemplateManager = serviceTemplateManager;
	}

}