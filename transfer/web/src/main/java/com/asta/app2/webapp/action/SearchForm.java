/* *Class created on [ Sep 6, 2008 | 7:37:13 PM ] */ 
package com.asta.app2.webapp.action;

import com.asta.app2.model.Service;
import com.asta.app2.service.ServiceManager;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SearchForm extends BasePage{
	ServiceManager serviceManager;
	private Service service = new Service();
	
	public String search(){
		
		
		return "result";
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
	
	
}


