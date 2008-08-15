/* *Class created on [ Jul 22, 2008 | 10:12:15 AM ] */
package com.asta.app2.webapp.action;

import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import com.asta.app2.model.LabelValue;
import com.asta.app2.model.enums.CarType;
import com.asta.app2.model.enums.Gender;
import com.asta.app2.model.enums.Quality;
import com.asta.app2.model.enums.RateType;
import com.asta.app2.model.enums.Weekday;
import com.asta.app2.service.CarKindManager;
import com.asta.app2.service.CarManager;
import com.asta.app2.service.CompanyManager;
import com.asta.app2.service.DriverManager;
import com.asta.app2.service.LodgerManager;
import com.asta.app2.service.LookupManager;
import com.asta.app2.service.PathManager;
import com.asta.app2.service.PersonManager;
import com.asta.app2.util.ConvertUtil;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class LookupProvider extends BasePage {
	private LookupManager lookupManager;
	private PathManager pathManager;
	private CarKindManager carKindManager;
	private CarManager carManager;
	private DriverManager driverManager;
	private LodgerManager lodgerManager;
	private CompanyManager companyManager;
	private PersonManager personManager;
	private Map<String, String> pathMap;
	private Map<String, String> parentPathMap;
	private Map<String, String> carKindMap;
	private Map<String, String> carMap;
	private Map<String, String> driverMap;
	private Map<String, String> lodgerMap;
	private Map<String, String> companyMap;
	private Map<String, String> personMap;
	
	private static SelectItem[] genderItems = {
			new SelectItem(Gender.MALE, Gender.MALE.getLabel()),
			new SelectItem(Gender.FEMALE, Gender.FEMALE.getLabel()) };

	private static SelectItem[] rateTypeItems = {
			new SelectItem(RateType.ORDINARY, RateType.ORDINARY.getLabel()),
			new SelectItem(RateType.SPRING, RateType.SPRING.getLabel()) };

	private static SelectItem[] weekdayItems = {
			new SelectItem(Weekday.SATURDAY, Weekday.SATURDAY.getLabel()),
			new SelectItem(Weekday.SUNDAY, Weekday.SUNDAY.getLabel()),
			new SelectItem(Weekday.MONDAY, Weekday.MONDAY.getLabel()),
			new SelectItem(Weekday.TUESDAY, Weekday.TUESDAY.getLabel()),
			new SelectItem(Weekday.WEDNESDAY, Weekday.WEDNESDAY.getLabel()),
			new SelectItem(Weekday.THURSDAY, Weekday.THURSDAY.getLabel()),
			new SelectItem(Weekday.FRIDAY, Weekday.FRIDAY.getLabel()) };

	private static SelectItem[] qualityItems = {
			new SelectItem(Quality.UP, Quality.UP.getLabel()),
			new SelectItem(Quality.NORMAL, Quality.NORMAL.getLabel()) };

	private static SelectItem[] carTypeItems = {
			new SelectItem(CarType.TYPE1, CarType.TYPE1.getLabel()),
			new SelectItem(CarType.TYPE2, CarType.TYPE2.getLabel()) };

	public SelectItem[] getCarTypeItems() {
		return carTypeItems;
	}

	public SelectItem[] getQualityItems() {
		return qualityItems;
	}

	public SelectItem[] getGenderItems() {
		return genderItems;
	}

	public SelectItem[] getRateTypeItems() {
		return rateTypeItems;
	}

	public SelectItem[] getWeekdayItems() {
		return weekdayItems;
	}

	public Map<String, String> getCarKindMap() {
		if (carKindMap == null) {
			List<LabelValue> list = carKindManager.getAllCarKindLableValue();
			carKindMap = ConvertUtil.convertListToMap(list);
		}
		return carKindMap;
	}

	public Map<String, String> getPathMap() {
		if (pathMap == null)
			pathMap = pathManager.getPathMap(false);
		return pathMap;
	}

	public Map<String, String> getParentPathMap() {
		if (parentPathMap == null)
			parentPathMap = pathManager.getParentPathMap(false);
		return parentPathMap;
	}

	public Map<String, String> getCarMap() {
		if (carMap == null)
			carMap = carManager.getMap(getCurrentUser().getCompany(),false);
		return carMap;
	}

	public Map<String, String> getDriverMap() {
		if (driverMap == null)
			driverMap = driverManager.getMap(getCurrentUser().getCompany(),false);
		return driverMap;
	}

	public Map<String, String> getLodgerMap() {
		if (lodgerMap == null)
			lodgerMap = lodgerManager.getMap(getCurrentUser().getCompany(),false);
		return lodgerMap;
	}

	public Map<String, String> getCompanyMap() {
		if (companyMap == null)
			companyMap = companyManager.getMap(false);
		return companyMap;
	}
	
	public Map<String, String> getPersonMap() {
		if (personMap == null)
			personMap = personManager.getPersonMap(getCurrentUser().getCompany(), false);
		return personMap;
	}
	
	public void setLookupManager(LookupManager lookupManager) {
		this.lookupManager = lookupManager;
	}

	public void setPathManager(PathManager pathManager) {
		this.pathManager = pathManager;
	}

	public void setCarKindManager(CarKindManager carKindManager) {
		this.carKindManager = carKindManager;
	}

	public void setCarManager(CarManager carManager) {
		this.carManager = carManager;
	}

	public void setDriverManager(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	public void setLodgerManager(LodgerManager lodgerManager) {
		this.lodgerManager = lodgerManager;
	}

	public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
	}

	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}

}
