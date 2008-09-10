package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


import com.asta.app2.Constants;
import com.asta.app2.model.Service;
import com.asta.app2.model.ServiceTemplate;
import com.asta.app2.model.enums.Weekday;
import com.asta.app2.service.CarKindManager;
import com.asta.app2.service.PathManager;
import com.asta.app2.service.ServiceManager;
import com.asta.app2.service.ServiceTemplateManager;
import com.asta.app2.util.DateUtil;

public class ServiceTemplateForm extends BasePage implements Serializable {
	private ServiceTemplateManager serviceTemplateManager;
	private ServiceTemplate serviceTemplate = new ServiceTemplate();
	private Long id;

	private String carKindID;
	private String pathID;
	private CarKindManager carKindManager;
	private ServiceManager serviceManager;
	private PathManager pathManager;

	private Date dateStart;
	private Date dateEnd;
	private int counter = 0;

	public int getCounter() {
		if (counter == 0)
			counter = 1;
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String delete() {
		try{
			serviceTemplateManager.remove(serviceTemplate.getId());
			addMessage("serviceTemplate.deleted");
		}catch(Exception e){
			addError("errors.delete.exception");
			return "edit";
		}

		return "list";
	}

	public String edit() {
		if (id != null) {
			serviceTemplate = serviceTemplateManager.get(id);
			setCarKindID(serviceTemplate.getCarKind().getId().toString());
			setPathID(serviceTemplate.getPath().getId().toString());
		} else {
			serviceTemplate = new ServiceTemplate();
		}

		return "edit";
	}

	public String save() {
		boolean isNew = (serviceTemplate.getId() == null);
		
		serviceTemplate.setCompany(getCurrentUser().getCompany());
		serviceTemplate.setCarKind(carKindManager.get(Long.valueOf(getCarKindID()).longValue()));
		serviceTemplate.setPath(pathManager.get(Long.valueOf(getPathID()).longValue()));
		
		serviceTemplateManager.save(serviceTemplate);

		String key = (isNew) ? "serviceTemplate.added" : "serviceTemplate.updated";
		addMessage(key);

		if (isNew) {
			return "list";
		} else {
			return "edit";
		}
	}

	public String goForGenerateForm() {
		if (id != null) {
			serviceTemplate = serviceTemplateManager.get(id);
			setCarKindID(serviceTemplate.getCarKind().getId().toString());
			setPathID(serviceTemplate.getPath().getId().toString());
		} else {
			serviceTemplate = null;
		}

		return "generate";
	}
	
	public String generate(){
		Date aDayBefore = new Date();
		aDayBefore.setDate(aDayBefore.getDate()-1);
		
		if (dateStart.before(aDayBefore)){
			addError("errors.serviceTemplate.startBeforeToday");
			return Constants.NO_WHERE;

		}else if (dateStart.after(dateEnd)){
			addError("errors.serviceTemplate.startAfterEnd");
			return Constants.NO_WHERE;

		}else{
			GregorianCalendar start = new GregorianCalendar();
			GregorianCalendar end = new GregorianCalendar();
			start.setTime(dateStart);
			end.setTime(dateEnd);
			int count = DateUtil.getDays(start,end);
			serviceTemplate.setCompany(getCurrentUser().getCompany());
			serviceTemplate.setCarKind(carKindManager.get(Long.valueOf(getCarKindID()).longValue()));
			for (int i = 0; i < count+1; i = i+getCounter()) {
				
				Service service = new Service(serviceTemplate,serviceTemplate.getCompany(),serviceTemplate.getCarKind(),serviceTemplate.getPath(),serviceTemplate.getTimed(),start.getTime());
				
				Weekday weekday;
				switch(start.get(Calendar.DAY_OF_WEEK)){
					case Calendar.SUNDAY :
						weekday = Weekday.SUNDAY;
						break;
					case Calendar.MONDAY :
						weekday = Weekday.MONDAY;
						break;
					case Calendar.TUESDAY : 
						weekday = Weekday.TUESDAY;
						break;
					case Calendar.WEDNESDAY : 
						weekday = Weekday.WEDNESDAY;
						break;
					case Calendar.THURSDAY :
						weekday = Weekday.THURSDAY;
						break;
					case Calendar.FRIDAY : 
						weekday = Weekday.FRIDAY;
						break;
					case Calendar.SATURDAY :
						weekday = Weekday.SATURDAY;
						break;
					default :
						weekday = Weekday.SATURDAY;
				}
				service.setWeekday(weekday);
				service.setServiceExpired(Boolean.FALSE);
				serviceManager.save(service);
				start.add(Calendar.DATE, getCounter());
			}
			
			addMessage("serviceTemplate.generated");
			return "successServices";
		}
		
	}

	public void setCarKindManager(CarKindManager carKindManager) {
		this.carKindManager = carKindManager;
	}

	public void setPathManager(PathManager pathManager) {
		this.pathManager = pathManager;
	}

	public void setServiceTemplateManager(
			ServiceTemplateManager serviceTemplateManager) {
		this.serviceTemplateManager = serviceTemplateManager;
	}

	public ServiceTemplate getServiceTemplate() {
		return serviceTemplate;
	}

	public void setServiceTemplate(ServiceTemplate serviceTemplate) {
		this.serviceTemplate = serviceTemplate;
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
		return pathID;
	}

	public void setPathID(String pathID) {
		this.pathID = pathID;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
}