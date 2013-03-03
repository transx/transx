package com.asta.app2.webapp.action;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;

import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.asta.app2.model.CarKind;
import com.asta.app2.model.Driver;
import com.asta.app2.model.Service;
import com.asta.app2.model.Setting;
import com.asta.app2.model.Soorat;
import com.asta.app2.model.enums.SooratType;
import com.asta.app2.service.CarKindManager;
import com.asta.app2.service.DriverManager;
import com.asta.app2.service.ServiceManager;
import com.asta.app2.service.SettingManager;
import com.asta.app2.service.SooratManager;
import com.asta.app2.util.DateUtil;

/**
 * This class used for calculate sooratPrivate
 * @author  <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SooratPrivateForm extends BasePage implements Serializable {
	private static final long serialVersionUID = 3752111698477620274L;
	private SooratManager sooratManager;
	private ServiceManager serviceManager;
	private SettingManager settingManager;
	private CarKindManager carKindManager;
	private DriverManager driverManager;
	private Soorat soorat = new Soorat();
	private Service service = new Service();
	private Setting setting;
	private Long snack;
	private CarKind carKind;
	private String[] driverSelected; 
	
	
	public String editPrivate() {
		setting = settingManager.getSettingByCompany(getCurrentUser().getCompany());
		if (soorat.getId() != null) {

			service = soorat.getService();
			service.setOpened(Boolean.FALSE);

		} else {
			soorat = new Soorat();
			soorat.setSeri(setting.getSeriPrivate());
			soorat.setSerial(setting.getSerialPrivate().toString());
			soorat.setStamp(setting.getStamp());
			if (service.getId() == null){
				service = new Service();
				// this part is about set default CarKind in add method + need for carKindConverter
				service.setCarKind(carKindManager.findByName("ولوو").get(0));
			}
		}
		return "editPrivate";
	}

	public String calcPrivate(){
		long totalIsTA = soorat.getTotal() - (soorat.getGovernmentToll()+soorat.getInsuranceSarneshin()+soorat.getStamp());
		double totalIsTADouble = Long.valueOf(totalIsTA).doubleValue();
		soorat.setTotalIsTA(Math.round(totalIsTADouble));
		soorat.setCommission((soorat.getTotalIsTA()* setting.getCommission())/100);
		soorat.setDriverPay(soorat.getTotalIsTA() - (soorat.getCommission()+soorat.getInsuranceBadaneh()+soorat.getSnack()));
		return "edit";
	}	

	@Transactional
	public String savePrivate() {
		try{
			boolean isNew = (soorat.getId() == null);
			service.setCompany(getCurrentUser().getCompany());
			service.setSooratType(SooratType.PRIVATE);
			service.setDatebook(DateUtil.getDateZone(service.getDatebook()));
			service.setWeekday(DateUtil.getWeekday(service.getDatebook()));
			service.setDrivers(new HashSet<Driver>());
			for (int i = 0; (driverSelected != null) && (i < driverSelected.length); i++) {
				long id = Long.valueOf(driverSelected[i]).longValue();
				service.addDriver(driverManager.get(id));
			}
			service = serviceManager.saveService(service);
			soorat.setService(service);
			soorat.setSooratType(SooratType.PRIVATE);
			soorat.setCompany(getCurrentUser().getCompany());
			soorat.setTotalIsTA(soorat.getTotal() - (soorat.getGovernmentToll()+soorat.getInsuranceSarneshin()+soorat.getStamp()));
			soorat.setCommission((soorat.getTotalIsTA()* setting.getCommission())/100);
			soorat.setDriverPay(soorat.getTotalIsTA() - (soorat.getCommission()+soorat.getInsuranceBadaneh()+soorat.getSnack()));
			if (soorat.getIssued() == null)
				soorat.setIssued(Boolean.TRUE);
			if (soorat.getDriverPaid() == null)
				soorat.setDriverPaid(Boolean.FALSE);

			sooratManager.save(soorat);
			setting.setSerialPrivate(setting.getSerialPrivate()+1);
			settingManager.save(setting);
			if (isNew) {
				addMessage("soorat.added");
	            return "list";
	        } else {
	        	addMessage("soorat.updated");
	            return "edit";
	        }

		}catch(Exception e){
			addError("soorat.notAadded");
			return "edit";
		}	
	}
	
	@Transactional
	public String savePrintPrivate() {
		try{
			boolean isNew = (soorat.getId() == null);
			service.setCompany(getCurrentUser().getCompany());
			service.setSooratType(SooratType.PRIVATE);
			service.setDatebook(DateUtil.getDateZone(service.getDatebook()));
			service.setWeekday(DateUtil.getWeekday(service.getDatebook()));
			service.setDrivers(new HashSet<Driver>());
			for (int i = 0; (driverSelected != null) && (i < driverSelected.length); i++) {
				long id = Long.valueOf(driverSelected[i]).longValue();
				service.addDriver(driverManager.get(id));
			}
			service = serviceManager.saveService(service);
			soorat.setService(service);
			soorat.setSooratType(SooratType.PRIVATE);
			soorat.setCompany(getCurrentUser().getCompany());
			soorat.setTotalIsTA(soorat.getTotal() - (soorat.getGovernmentToll()+soorat.getInsuranceSarneshin()+soorat.getStamp()));
			soorat.setCommission((soorat.getTotalIsTA()* setting.getCommission())/100);
			soorat.setDriverPay(soorat.getTotalIsTA() - (soorat.getCommission()+soorat.getInsuranceBadaneh()+soorat.getSnack()));
			if (soorat.getIssued() == null)
				soorat.setIssued(Boolean.TRUE);
			if (soorat.getDriverPaid() == null)
				soorat.setDriverPaid(Boolean.FALSE);

			sooratManager.save(soorat);
			setting.setSerialPrivate(setting.getSerialPrivate()+1);
			settingManager.save(setting);
			try{
				
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("SOORAT_DATEBOOK", service.getDatebookFormatted());
				parameters.put("SOORAT_STATE", getCurrentUser().getCompany().getCity().getName());
				parameters.put("SOORAT_TIMED", service.getTimed());
				
				parameters.put("SOORAT_DRIVER1",service.getDriver1().getPerson().getFullName());
				parameters.put("SOORAT_DRIVER1_STATE", service.getDriver1().getLiveState());
				parameters.put("SOORAT_DRIVER1_LICENCE",service.getDriver1().getLicenceNumber()+service.getDriver1().getLicenceIssue());
				parameters.put("SOORAT_DRIVER1_IDENTITYNUMBER", service.getDriver1().getPerson().getIdentityNumber());
				parameters.put("SOORAT_DRIVER1_CODE", service.getDriver1().getCode());
				
				parameters.put("SOORAT_DRIVER2",service.getDriver2().getPerson().getFullName());
				parameters.put("SOORAT_DRIVER2_STATE", service.getDriver2().getLiveState());
				parameters.put("SOORAT_DRIVER2_LICENCE",service.getDriver2().getLicenceNumber()+service.getDriver2().getLicenceIssue());
				parameters.put("SOORAT_DRIVER2_IDENTITYNUMBER", service.getDriver2().getPerson().getIdentityNumber());
				parameters.put("SOORAT_DRIVER2_CODE", service.getDriver2().getCode());
								
				parameters.put("SOORAT_CAR_PLAQUENUMBER", service.getCar().getPlaqueNumber());
				parameters.put("SOORAT_CAR_PLAQUESERIAL", service.getCar().getPlaqueSerial());
				parameters.put("SOORAT_CAR_PLAQUEISSUE", service.getCar().getPlaqueIssue());
				parameters.put("SOORAT_CAR_KIND",service.getCarKind().getName() );
				parameters.put("SOORAT_CAR_CAPACITY", service.getCarKind().getCapacity().toString());
				
				parameters.put("SOORAT_CODE_CO",getCurrentUser().getCompany().getCode());
				parameters.put("SOORAT_PASSENGERCOUNT",soorat.getPassengerCount().toString());
				parameters.put("SOORAT_TOTAL",soorat.getTotal().toString());
				parameters.put("SOORAT_TOOSHEEZAFI",soorat.getSnack().toString());
				parameters.put("SOORAT_GOVERNMENTTOLL",soorat.getGovernmentToll().toString());
				parameters.put("SOORAT_INSURANCESARNESHIN",soorat.getInsuranceSarneshin().toString());
				parameters.put("SOORAT_COMPANY_NAME",getCurrentUser().getCompany().getName());
				parameters.put("SOORAT_COMPANYPLACE", setting.getCompanyPlace());
				parameters.put("SOORAT_COMPANY_CITY", getCurrentUser().getCompany().getCity().getName());
				parameters.put("SOORAT_COMPANY_PHONE", setting.getCompanyPhone());
				parameters.put("SOORAT_DRIVER1_SIGN", service.getDriver1().getPerson().getFullName());
				parameters.put("SOORAT_DRIVER2_SIGN", service.getDriver2().getPerson().getFullName());
				parameters.put("SOORAT_COMPANYSIGN", getCurrentUser().getCompany().getName()+" - "+setting.getCompanyPlace());
				
//				parameters.put("SOORAT_END", service.getPath().getEnd().getName());
//				parameters.put("SOORAT_START",service.getPath().getStart().getName());
				parameters.put("SOORAT_INSURANCEBADANEH", soorat.getInsuranceBadaneh().toString());
				parameters.put("SOORAT_COMMISSION", soorat.getCommission().toString());
				parameters.put("SOORAT_DRIVERPAY", soorat.getDriverPay().toString());
				
				getResponse().setContentType("application/pdf");
				getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");
				
				InputStream is = getServletContext().getResourceAsStream("/WEB-INF/reports/sooratPrivate.jrxml");
				JasperDesign jasperDesign = JRXmlLoader.load(is);
				JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JREmptyDataSource());
				
				JasperExportManager.exportReportToPdfStream(jasperPrint, getResponse().getOutputStream());
				getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
				getFacesContext().responseComplete();

			}catch(Exception e){
				addError("soorat.notPrinted");
				log.debug(e);
				return "edit";
			}
			if (isNew) {
				addMessage("soorat.added");
	            return "list";
	        } else {
	        	addMessage("soorat.updated");
	            return "edit";
	        }
		}catch(Exception e){
			addError("soorat.notAadded");
			return "edit";
		}	
	}

	public void driverChanged(ValueChangeEvent evt){
    	driverSelected = (String[]) evt.getNewValue(); 
    }    
	
	public Setting getSetting() {
		return setting;
	}
	
	public void setSettingManager(SettingManager settingManager) {
		this.settingManager = settingManager;
	}

	public void setSooratManager(SooratManager sooratManager) {
		this.sooratManager = sooratManager;
	}

	public Soorat getSoorat() {
		return soorat;
	}

	public void setSoorat(Soorat soorat) {
		this.soorat = soorat;
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

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public Long getSnack() {
		return snack;
	}

	public void setSnack(Long snack) {
		this.snack = snack;
	}

	public CarKind getCarKind() {
		return carKind;
	}

	public void setCarKind(CarKind carKind) {
		this.carKind = carKind;
	}

	public void setCarKindManager(CarKindManager carKindManager) {
		this.carKindManager = carKindManager;
	}

	public void setDriverManager(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	public String[] getDriverSelected() {
		if (driverSelected == null){
			driverSelected = new String[service.getDrivers().size()];
		
			int i = 0;
			if (driverSelected.length > 0) {
				for (Driver driver : service.getDrivers()) {
					driverSelected[i] = driver.getId().toString();
					i++;
				}
			}
		}
		return driverSelected;
	}

	public void setDriverSelected(String[] driverSelected) {
		this.driverSelected = driverSelected;
	}
}