package com.asta.app2.webapp.action;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataSourceProvider;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRColorUtil;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.asta.app2.Constants;
import com.asta.app2.model.Chair;
import com.asta.app2.model.InsuranceBadaneh;
import com.asta.app2.model.InsuranceSarneshin;
import com.asta.app2.model.Passenger;
import com.asta.app2.model.Service;
import com.asta.app2.model.Setting;
import com.asta.app2.model.Soorat;
import com.asta.app2.model.Ticket;
import com.asta.app2.model.enums.Distance;
import com.asta.app2.service.InsuranceBadanehManager;
import com.asta.app2.service.InsuranceSarneshinManager;
import com.asta.app2.service.ServiceManager;
import com.asta.app2.service.SettingManager;
import com.asta.app2.service.SooratManager;
import com.asta.app2.service.TicketManager;
import com.asta.app2.webapp.util.ChairModel;
import com.asta.app2.webapp.util.ChairModelForPrint;

public class SooratSodorForm extends BasePage implements Serializable {
	private SooratManager sooratManager;
	private ServiceManager serviceManager;
	private TicketManager ticketManager;
	private Soorat soorat = new Soorat();
	private Service service = new Service();
	private Setting setting = new Setting();
	private Long id;
	private List<Ticket> tickets;
	private int capacity = 0;
	private List<ChairModel> chairModels;
	private Collection<ChairModelForPrint> chairModelForPrints;
	private Passenger newPassenger;
	private SettingManager settingManager;
	private Long snack;
	private InsuranceSarneshinManager insuranceSarneshinManager;
	private InsuranceBadanehManager insuranceBadanehManager;

	public String editInner() {
		if (id != null) {
			service = serviceManager.get(id);
			service.setOpened(false);
			service = serviceManager.save(service);
			soorat = new Soorat();
			soorat.setService(service);
			Distance distance;
			if (service.getPath().getSpace() >= 300)
				distance = Distance.UP300;
			else
				distance = Distance.DOWN300;
			List<InsuranceSarneshin> iss = insuranceSarneshinManager
					.findISbyDistanceCapacity(distance, getCapacity());
			List<InsuranceBadaneh> ibs = insuranceBadanehManager
					.findISbyQualityJodaganehSpace(service.getCarKind()
							.getQuality(), service.getCar()
							.getInsuranceBadanehJodaganeh(), service.getPath()
							.getSpace());

			soorat.setInsuranceSarneshin(iss.get(0).getPrice());
			soorat.setInsuranceBadaneh(ibs.get(0).getPrice());
		} else {
			//soorat = null;
		}
		setting = settingManager.getSettingByCompany(getCurrentUser().getCompany());
		soorat.setSeri(setting.getSeriInner());
		soorat.setSerial(setting.getSerialInner());
		return "issueInner";
	}

	public String calcInner(){
		long totalIsTA = soorat.getTotal() - (soorat.getGovernmentToll()+soorat.getInsuranceSarneshin()+setting.getStamp());
		double totalIsTADouble = Long.valueOf(totalIsTA).doubleValue();
		soorat.setTotalIsTA(Math.round(totalIsTADouble));
		soorat.setCommission((soorat.getTotalIsTA()* setting.getCommission())/100);
		soorat.setDriverPay(soorat.getTotalIsTA() - (soorat.getCommission()+soorat.getInsuranceBadaneh()+soorat.getSnack()));
		return Constants.NO_WHERE;
	}	

	public String saveInner() {
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
			addMessage("soorat.added");
			return "list";
		}catch(Exception e){
			addError("soorat.notAadded");
			return Constants.NO_WHERE;
		}	
	}

	public String savePrintInner() {
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
				
				parameters.put("SOORAT_DRIVER3",service.getDriver3().getPerson().getFullName());
				parameters.put("SOORAT_DRIVER3_STATE", service.getDriver3().getLiveState());
				parameters.put("SOORAT_DRIVER3_LICENCE",service.getDriver3().getLicenceNumber()+service.getDriver3().getLicenceIssue());
				parameters.put("SOORAT_DRIVER3_IDENTITYNUMBER", service.getDriver3().getPerson().getIdentityNumber());
				parameters.put("SOORAT_DRIVER3_CODE", service.getDriver3().getCode());
				
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
				parameters.put("SOORAT_DRIVER3_SIGN", service.getDriver3().getPerson().getFullName());
				parameters.put("SOORAT_COMPANYSIGN", getCurrentUser().getCompany().getName()+" - "+setting.getCompanyPlace());
				
				parameters.put("SOORAT_END", service.getPath().getEnd().getName());
				parameters.put("SOORAT_START",service.getPath().getStart().getName());
				parameters.put("SOORAT_INSURANCEBADANEH", soorat.getInsuranceBadaneh().toString());
				parameters.put("SOORAT_COMMISSION", soorat.getCommission().toString());
				parameters.put("SOORAT_DRIVERPAY", soorat.getDriverPay().toString());
				
				InputStream is = getServletContext().getResourceAsStream("/WEB-INF/reports/sooratInner.jrxml");
				JasperDesign jasperDesign = JRXmlLoader.load(is);
				JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JRBeanCollectionDataSource(getChairModelsForPrint()));
				
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
			return Constants.NO_WHERE;
		}	
	}

	public String previewInner(){
		if (id != null) {
			soorat = sooratManager.get(id);
			service = soorat.getService();
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
				
				parameters.put("SOORAT_DRIVER3",service.getDriver3().getPerson().getFullName());
				parameters.put("SOORAT_DRIVER3_STATE", service.getDriver3().getLiveState());
				parameters.put("SOORAT_DRIVER3_LICENCE",service.getDriver3().getLicenceNumber()+service.getDriver3().getLicenceIssue());
				parameters.put("SOORAT_DRIVER3_IDENTITYNUMBER", service.getDriver3().getPerson().getIdentityNumber());
				parameters.put("SOORAT_DRIVER3_CODE", service.getDriver3().getCode());
				
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
				parameters.put("SOORAT_DRIVER3_SIGN", service.getDriver3().getPerson().getFullName());
				parameters.put("SOORAT_COMPANYSIGN", getCurrentUser().getCompany().getName()+" - "+setting.getCompanyPlace());
				
				parameters.put("SOORAT_END", service.getPath().getEnd().getName());
				parameters.put("SOORAT_START",service.getPath().getStart().getName());
				parameters.put("SOORAT_INSURANCEBADANEH", soorat.getInsuranceBadaneh().toString());
				parameters.put("SOORAT_COMMISSION", soorat.getCommission().toString());
				parameters.put("SOORAT_DRIVERPAY", soorat.getDriverPay().toString());
				
				getResponse().setContentType("application/pdf");
				getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");
				
				InputStream is = getServletContext().getResourceAsStream("/WEB-INF/reports/sooratInner.jrxml");
				JasperDesign jasperDesign = JRXmlLoader.load(is);
				JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JRBeanCollectionDataSource(getChairModelsForPrint()));

				JasperExportManager.exportReportToPdfStream(jasperPrint, getResponse().getOutputStream());
				
				getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
				getFacesContext().responseComplete();
				
			}catch(Exception e){
				addError("soorat.notPreview");
				return "list";
			}	
		}else{
			addError("soorat.notPreview");
		}
		return "list";
	}
	
	
	public List<ChairModel> getChairModels() {
		chairModels = new ArrayList<ChairModel>();
		chairModels.clear();
		Integer passengerCount = 0;
		Long total =0L;
		Long snack = 0L;
		Long toll = 0L;
		for (Ticket ticket : getTickets()) {
			for (Chair chair : ticket.getChairs()) {
				ChairModel model = new ChairModel(chair.getId(), ticket
						.getPassenger(), ticket.getNumber(), ticket.getPrice(),
						ticket.getToll(), ticket.getSnack());
				chairModels.add(model);
			}
			passengerCount = passengerCount + ticket.getChairs().size();
			total = total + ticket.getTotal();
			snack = snack + (ticket.getCount()*ticket.getSnack());
			toll = toll + (ticket.getCount()*ticket.getToll());
		}
		soorat.setPassengerCount(passengerCount);
		soorat.setTotal(total);
		soorat.setSnack(snack);
		soorat.setGovernmentToll(toll);
		return chairModels;
	}

	public Collection<ChairModelForPrint> getChairModelsForPrint() {
		chairModelForPrints = new ArrayList<ChairModelForPrint>();
		chairModelForPrints.clear();
		for (long i = 1; i < 46; i++) {
			String passenger = "";
			String ticketNumber = "";
			String ticketPrice = "";
			
			for (Ticket ticket : getTickets()) {
				for (Chair chair : ticket.getChairs()) {
					if (chair.getId() == i){
						passenger = ticket.getPassenger().toString();
						ticketNumber = ticket.getNumber().toString();
						ticketPrice = ticket.getPrice().toString();
						break;
					}
				}
			}
			ChairModelForPrint model = new ChairModelForPrint(String.valueOf(i),passenger, ticketNumber, ticketPrice);
			chairModelForPrints.add(model);
		}
		
		
		
		return chairModelForPrints;
	}

	public void setSettingManager(SettingManager settingManager) {
		this.settingManager = settingManager;
	}

	public int getCapacity() {
		capacity = service.getCarKind().getCapacity();
		return capacity;
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

	public List<Ticket> getTickets() {
		if (tickets == null)
			tickets = ticketManager.getTicketsByService(getCurrentUser().getCompany(), service);
		return tickets;
	}
	
	public void setTicketManager(TicketManager ticketManager) {
		this.ticketManager = ticketManager;
	}

	public Passenger getNewPassenger() {
		newPassenger = new Passenger();
		return newPassenger;
	}

	public Setting getSetting() {
		return setting;
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