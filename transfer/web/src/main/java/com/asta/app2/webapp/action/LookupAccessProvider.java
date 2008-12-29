/* *Class created on [ Oct 2, 2008 | 4:49:26 PM ] */
package com.asta.app2.webapp.action;

import java.util.Map;

import javax.faces.model.SelectItem;

import com.asta.app2.Constants;
import com.asta.app2.model.Company;
import com.asta.app2.model.enums.TicketTempType;
import com.asta.app2.service.CarKindManager;
import com.asta.app2.service.CarManager;
import com.asta.app2.service.CompanyManager;
import com.asta.app2.service.DriverManager;
import com.asta.app2.service.PathManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class LookupAccessProvider extends BasePage{

	private Map<Object, Object> carKindItems;
	private CarKindManager carKindManager;
	public void setCarKindManager(CarKindManager carKindManager) {
		this.carKindManager = carKindManager;
	}
	public Map<Object, Object> getCarKindItems() {
		if (carKindItems == null)
			carKindItems = carKindManager.getCarKindItems();
		return carKindItems;
	}

	private Map<Object, Object> carItems;
	private CarManager carManager;
	public void setCarManager(CarManager carManager) {
		this.carManager = carManager;
	}
	public Map<Object, Object> getCarItems() {
		if (carItems == null)
			carItems = carManager.getCarItems(getCurrentUser().getCompany(),false);
		return carItems;
	}
	
	private Map<String, String> driverItemsMapString;
	private DriverManager driverManager;
	public void setDriverManager(DriverManager driverManager) {
		this.driverManager = driverManager;
	}
	public Map<String, String> getDriverItemsMapString() {
		if (driverItemsMapString == null)
			driverItemsMapString = driverManager.getMap(getCurrentUser().getCompany(), false);
		return driverItemsMapString;
	}
	
	private Map<Object, Object> pathParentItems;
	private PathManager pathManager;
	public void setPathManager(PathManager pathManager) {
		this.pathManager = pathManager;
	}
	public Map<Object, Object> getPathParentItems() {
		if (pathParentItems == null)
			pathParentItems = pathManager.getPathParentItems(false);
		return pathParentItems;
	}
	
	private static SelectItem[] ticketTempTypeItems = {
		new SelectItem(TicketTempType.SELL, TicketTempType.SELL.getLabel()),
		new SelectItem(TicketTempType.RESERVE, TicketTempType.RESERVE.getLabel()) };

	public SelectItem[] getTicketTempTypeItems() {
		return ticketTempTypeItems;
	}
	
	private Map<Company, Company> companyItems;
	private CompanyManager companyManager;
	public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
	}
	public Map<Company, Company> getCompanyItems(){
		if (companyItems == null)
			companyItems = companyManager.getCompanyItems(false);
		return companyItems;
	} 
	
	private Map<String, String> notFilterReserverItems;
	public Map<String, String> getNotFilterReserverItems(){
		if(notFilterReserverItems == null)
			notFilterReserverItems = userManager.getSpecificMap(false, Constants.RESERVER_ROLE);
		return notFilterReserverItems;
	}
	private Map<String, String> reserverItems;
	public Map<String, String> getReserverItems(){
		if(reserverItems == null)
			reserverItems = userManager.getSpecificMap(getCurrentUser().getCompany(),false, Constants.RESERVER_ROLE);
		return reserverItems;
	}
}
