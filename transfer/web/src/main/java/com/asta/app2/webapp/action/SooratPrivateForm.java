package com.asta.app2.webapp.action;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.asta.app2.model.Passenger;
import com.asta.app2.model.Service;
import com.asta.app2.model.Setting;
import com.asta.app2.model.Soorat;
import com.asta.app2.service.InsuranceBadanehManager;
import com.asta.app2.service.InsuranceSarneshinManager;
import com.asta.app2.service.ServiceManager;
import com.asta.app2.service.SettingManager;
import com.asta.app2.service.SooratManager;
import com.asta.app2.service.TicketManager;

/**
 * This class used for calculate sooratPrivate
 * @author  <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SooratPrivateForm extends BasePage implements Serializable {
	private SooratManager sooratManager;
	private ServiceManager serviceManager;
	private SettingManager settingManager;
	private InsuranceSarneshinManager insuranceSarneshinManager;
	private InsuranceBadanehManager insuranceBadanehManager;
	private Soorat soorat = new Soorat();
	private Service service = new Service();
	private Setting setting;
	private Long id;
	private int capacity = 0;
	private Passenger newPassenger;
	private Long snack;

	@Transactional
	public String editPrivate() {
		if (soorat.getId() != null) {
			//soorat = sooratManager.get(id);
			//service = serviceManager.save(service);
			//soorat = new Soorat();
			//soorat.setService(service);
			service = soorat.getService();
			service.setOpened(false);
			//Distance distance;
			//if (service.getPath().getSpace() >= 300)
			//	distance = Distance.UP300;
			//else
			//	distance = Distance.DOWN300;
			//List<InsuranceSarneshin> iss = insuranceSarneshinManager.findISbyDistanceCapacity(distance, getCapacity());
			//List<InsuranceBadaneh> ibs = insuranceBadanehManager.findISbyQualityJodaganehSpace(service.getCarKind().getQuality(), service.getCar().getInsuranceBadanehJodaganeh(), service.getPath().getSpace());

			//soorat.setInsuranceSarneshin(iss.get(0).getPrice());
			//soorat.setInsuranceBadaneh(ibs.get(0).getPrice());
		} else {
//			addError("soorat.notFound");
			soorat = new Soorat();
			service = new Service();
			soorat.setSeri(getSetting().getSeriPrivate());
			soorat.setSerial(getSetting().getSerialPrivate());
		}
		return "edit";
	}

	public String calcPrivate(){
		long totalIsTA = soorat.getTotal() - (soorat.getGovernmentToll()+soorat.getInsuranceSarneshin()+setting.getStamp());
		double totalIsTADouble = Long.valueOf(totalIsTA).doubleValue();
		soorat.setTotalIsTA(Math.round(totalIsTADouble));
		soorat.setCommission((soorat.getTotalIsTA()* setting.getCommission())/100);
		soorat.setDriverPay(soorat.getTotalIsTA() - (soorat.getCommission()+soorat.getInsuranceBadaneh()+soorat.getSnack()));
		return "edit";
	}	

	public String savePrivate() {
		try{
			serviceManager.save(service);
			soorat.setService(getService());
			soorat.setCompany(getCurrentUser().getCompany());
			soorat.setTotalIsTA(soorat.getTotal() - (soorat.getGovernmentToll()+soorat.getInsuranceSarneshin()+setting.getStamp()));
			soorat.setCommission((soorat.getTotalIsTA()* setting.getCommission())/100);
			soorat.setDriverPay(soorat.getTotalIsTA() - (soorat.getCommission()+soorat.getInsuranceBadaneh()+soorat.getSnack()));
			soorat.setIssued(true);
			sooratManager.save(soorat);
			setting.setSerialInner(setting.getSerialInner()+1);
			settingManager.save(setting);
			addMessage("soorat.added");
			return "list";
		}catch(Exception e){
			addError("soorat.notAadded");
			return "edit";
		}	
	}

	public String savePrintPrivate() {
		try{
			soorat.setService(getService());
			soorat.setCompany(getCurrentUser().getCompany());
			soorat.setTotalIsTA(soorat.getTotal() - (soorat.getGovernmentToll()+soorat.getInsuranceSarneshin()+setting.getStamp()));
			soorat.setCommission((soorat.getTotalIsTA()* setting.getCommission())/100);
			soorat.setDriverPay(soorat.getTotalIsTA() - (soorat.getCommission()+soorat.getInsuranceBadaneh()+soorat.getSnack()));
			soorat.setIssued(true);
			sooratManager.save(soorat);
			
			setting.setSerialInner(setting.getSerialInner()+1);
			settingManager.save(setting);
			try{
				
				Map parameters = new HashMap();
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
				
				parameters.put("SOORAT_END", service.getPath().getEnd().getName());
				parameters.put("SOORAT_START",service.getPath().getStart().getName());
				parameters.put("SOORAT_INSURANCEBADANEH", soorat.getInsuranceBadaneh().toString());
				parameters.put("SOORAT_COMMISSION", soorat.getCommission().toString());
				parameters.put("SOORAT_DRIVERPAY", soorat.getDriverPay().toString());
				
				InputStream is = getServletContext().getResourceAsStream("/WEB-INF/reports/sooratPrivate.jrxml");
				JasperDesign jasperDesign = JRXmlLoader.load(is);
				JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JREmptyDataSource());
				
				JasperPrintManager jasperPrintManager = new JasperPrintManager();
				jasperPrintManager.printReport(jasperPrint, false);
			}catch(Exception e){
				addError("soorat.notPrinted");
				log.debug(e);
				return "list";
			}
			addMessage("soorat.added");
			return "list";
		}catch(Exception e){
			addError("soorat.notAadded");
			return "edit";
		}	
	}

	public Setting getSetting() {
		if (setting == null)
			setting = settingManager.getSettingByCompany(getCurrentUser().getCompany());
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

	public void setId(Long id) {
		this.id = id;
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

	public void setInsuranceBadanehManager(
			InsuranceBadanehManager insuranceBadanehManager) {
		this.insuranceBadanehManager = insuranceBadanehManager;
	}

	public Passenger getNewPassenger() {
		newPassenger = new Passenger();
		return newPassenger;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public void setInsuranceSarneshinManager(
			InsuranceSarneshinManager insuranceSarneshinManager) {
		this.insuranceSarneshinManager = insuranceSarneshinManager;
	}

	public Long getSnack() {
		return snack;
	}

	public void setSnack(Long snack) {
		this.snack = snack;
	}
}