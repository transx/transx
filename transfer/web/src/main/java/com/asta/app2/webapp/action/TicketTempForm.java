package com.asta.app2.webapp.action;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.asta.app2.model.Cash;
import com.asta.app2.model.Chair;
import com.asta.app2.model.Rate;
import com.asta.app2.model.Setting;
import com.asta.app2.model.Ticket;
import com.asta.app2.model.TicketTemp;
import com.asta.app2.model.User;
import com.asta.app2.service.CashManager;
import com.asta.app2.service.ChairManager;
import com.asta.app2.service.PassengerManager;
import com.asta.app2.service.RateManager;
import com.asta.app2.service.ServiceManager;
import com.asta.app2.service.SettingManager;
import com.asta.app2.exception.TicketDidNotIssuedException;
import com.asta.app2.exception.TicketIssuedException;
import com.asta.app2.service.TicketManager;
import com.asta.app2.service.TicketTempManager;
import com.asta.app2.service.UserManager;
import com.asta.app2.util.BundleUtil;
import com.asta.app2.util.DateUtil;

public class TicketTempForm extends BasePage implements Serializable {
	private TicketTempManager ticketTempManager;
	private TicketTemp ticketTemp = new TicketTemp();
	private Long id;
	private Long cashId;
	private PassengerManager passengerManager;
	private ServiceManager serviceManager;
	private ChairManager chairManager;
	private TicketManager ticketManager;
	private UserManager userManager;
	private String passengerID;
	private String serviceID;
	private Map<String, String> passengerMap;
	//private Map<String, String> serviceMap;
	private String[] ttChairs;
	private RateManager rateManager;
	private SettingManager settingManager;
	private Cash cash = new Cash();
	private CashManager cashManager;

	public String delete() {
		ticketTempManager.remove(ticketTemp.getId());
		addMessage("ticketTemp.deleted");

		return "list";
	}

	public String edit() {
		if (id != null) {
			ticketTemp = ticketTempManager.get(id);
			setPassengerID(ticketTemp.getPassenger().getId().toString());
			setServiceID(ticketTemp.getService().getId().toString());
		} else {
			ticketTemp = new TicketTemp();
		}

		return "edit";
	}

	/*
	 * public String save() { boolean isNew = (ticketTemp.getId() == null);
	 * 
	 * ticketTemp.setCount(ttChairs.length);
	 * ticketTemp.setService(serviceManager.get(Long.valueOf(getServiceID())
	 * .longValue()));
	 * ticketTemp.setPassenger(passengerManager.get(Long.valueOf(
	 * getPassengerID()).longValue())); if (ticketTemp.isCommitted())
	 * ticketTemp.setCommitDate(new Date());
	 * setTtChairs(getRequest().getParameterValues("ticketTempForm:ttChairs"));
	 * for (int i = 0; (ttChairs != null) && (i < ttChairs.length); i++) { long
	 * id = Long.valueOf(ttChairs[i]).longValue();
	 * ticketTemp.getChairs().add(chairManager.get(id)); }
	 * ticketTempManager.save(ticketTemp);
	 * 
	 * String key = (isNew) ? "ticketTemp.added" : "ticketTemp.updated";
	 * addMessage(key);
	 * 
	 * if (isNew) { return "list"; } else { return "edit"; } }
	 */
	@Transactional
	public String issueTicket() {
		if (id != null) {
			TicketTemp tt = ticketTempManager.get(id);

			if (tt.isCommitted()) {
				addError("errors.ticket.issued");
			} else {
				User cashier = getCurrentUser();
				Setting setting = settingManager.get(1L);
				List<Rate> rates = rateManager.findRateForTicket(new Rate(
						setting.getRateType(), tt.getPath(), tt.getService()
								.getCarKind().getQuality()));
				if (rates.size() <1){
					addError("errors.ticket.rate.notExist");
				}else{
					Rate rate = rates.get(0);
					
					Ticket ticket = new Ticket(setting.getTicketNumber(), 
							rate.getPrice(), rate.getToll(), rate.getSnack(), 
							tt.getCount(), tt.getReserveDate(),	new Date(), 
							tt.getReserverId(), cashier, tt.getPassenger(), 
							tt.getService(), tt.getPath());
					ticket.setChairs(tt.getChairs());
					ticket.setTotal(ticket.getCount()*(ticket.getPrice()+ticket.getSnack()));
					ticket.setCash(getCash());
					
					try {
						
						Map parameters = new HashMap();
						parameters.put("TICKET_NUMBER", ticket.getNumber().toString());
						parameters.put("TICKET_CAR_KIND", ticket.getService().getCarKind().toString());
						parameters.put("TICKET_PASSENGER_NAME", ticket.getPassenger().getGender().getLabel()+" "+(ticket.getPassenger().getFirstName()!=null ? ticket.getPassenger().getFirstName(): "")+" "+ticket.getPassenger().getLastName());
						parameters.put("TICKET_CHAIRS", ticket.getChairs().toString());
						parameters.put("TICKET_DATEBOOK", ticket.getService().getDatebookFormatted());
						parameters.put("TICKET_TIMED", ticket.getService().getTimed());
						parameters.put("TICKET_START", BundleUtil.getMessageBundle("soorat.defaultPlace"));
						parameters.put("TICKET_END", ticket.getPath().getEnd().toString());
						parameters.put("TICKET_CAR", ticket.getService().getCar().getPlaqueSerial()+" "+ticket.getService().getCar().getPlaqueNumber());
						parameters.put("TICKET_ISSUE_DATE", ticket.getIssueDateFormatted());
						parameters.put("TICKET_ISSUE_TIME", DateUtil.getHourMinute(ticket.getIssueDate()));
						parameters.put("TICKET_PRICE", ticket.getPrice()+"");
						parameters.put("TICKET_SNACK", ticket.getSnack()+"");
						parameters.put("TICKET_COUNT", ticket.getCount()+"");
						parameters.put("TICKET_TOTAL", ticket.getTotal()+"");
						parameters.put("TICKET_TOTAL_WORD", ticket.getTotal()+"");
						
						InputStream is = getServletContext().getResourceAsStream("/WEB-INF/reports/belit.jrxml");
						JasperDesign jasperDesign = JRXmlLoader.load(is);
						JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
						JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JREmptyDataSource());
						
						JasperPrintManager jasperPrintManager = new JasperPrintManager();
						jasperPrintManager.printReport(jasperPrint, false);
						
						ticket = ticketManager.saveTicket(getCurrentUser().getCompany(),ticket, id);
						setCash(ticket.getCash());
					} catch (TicketIssuedException tie) {
						addError("errors.ticket.issued");
					} catch (TicketDidNotIssuedException tdie) {
						addError("errors.ticket.issued.didNot");
					}catch (Exception e) {
						addError("errors.ticket.printed");
					}
				}
			}

		}
		return "list";
	}
	
	public String previewTicket() {
		if (id != null) {
			TicketTemp tt = ticketTempManager.get(id);

			if (tt.isCommitted()) {
				addError("errors.ticket.issued");
			} else {
				User cashier = getCurrentUser();
				Setting setting = settingManager.get(1L);
				List<Rate> rates = rateManager.findRateForTicket(new Rate(
						setting.getRateType(), tt.getPath(), tt.getService()
								.getCarKind().getQuality()));
				if (rates.size() <1){
					addError("errors.ticket.rate.notExist");
				}else{
					Rate rate = rates.get(0);
					
					Ticket ticket = new Ticket(setting.getTicketNumber(), 
							rate.getPrice(), rate.getToll(), rate.getSnack(), 
							tt.getCount(), tt.getReserveDate(),	new Date(), 
							tt.getReserverId(), cashier, tt.getPassenger(), 
							tt.getService(), tt.getPath());
					ticket.setChairs(tt.getChairs());
					ticket.setTotal(ticket.getCount()*(ticket.getPrice()+ticket.getSnack()));
					ticket.setCash(getCash());
					
					try {						
						Map parameters = new HashMap();
						parameters.put("format", "pdf");
						parameters.put("TICKET_NUMBER", ticket.getNumber().toString());
						parameters.put("TICKET_CAR_KIND", ticket.getService().getCarKind().toString());
						parameters.put("TICKET_PASSENGER_NAME", ticket.getPassenger().getGender().getLabel()+" "+(ticket.getPassenger().getFirstName()!=null ? ticket.getPassenger().getFirstName(): "")+" "+ticket.getPassenger().getLastName());
						parameters.put("TICKET_CHAIRS", ticket.getChairs().toString());
						parameters.put("TICKET_DATEBOOK", ticket.getService().getDatebookFormatted());
						parameters.put("TICKET_TIMED", ticket.getService().getTimed());
						parameters.put("TICKET_START", BundleUtil.getMessageBundle("soorat.defaultPlace"));
						parameters.put("TICKET_END", ticket.getPath().getEnd().toString());
						parameters.put("TICKET_CAR", ticket.getService().getCar().getPlaqueSerial()+" "+ticket.getService().getCar().getPlaqueNumber());
						parameters.put("TICKET_ISSUE_DATE", ticket.getIssueDateFormatted());
						parameters.put("TICKET_ISSUE_TIME", DateUtil.getHourMinute(ticket.getIssueDate()));
						parameters.put("TICKET_PRICE", ticket.getPrice()+"");
						parameters.put("TICKET_SNACK", ticket.getSnack()+"");
						parameters.put("TICKET_COUNT", ticket.getCount()+"");
						parameters.put("TICKET_TOTAL", ticket.getTotal()+"");
						parameters.put("TICKET_TOTAL_WORD", ticket.getTotal()+"");
						
						getResponse().setContentType("application/pdf");
						getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");
						
						InputStream is = getServletContext().getResourceAsStream("/WEB-INF/reports/belit.jrxml");
						JasperDesign jasperDesign = JRXmlLoader.load(is);
						JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
						JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JREmptyDataSource());
						
						JasperExportManager.exportReportToPdfStream(jasperPrint, getResponse().getOutputStream());
						
						getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
						getFacesContext().responseComplete();
					
					}catch (Exception e) {
						addError("errors.ticket.printed");
					}
				}
			}

		}
		return null;
	}

	public String sell() {
		if (cashId != null) {
			cash = cashManager.get(cashId);
		} else {
			cash = null;
			addError("errors.cash.notSelected");
		}

		return "sell";
	}

	public void setCashManager(CashManager cashManager) {
		this.cashManager = cashManager;
	}

	public void setSettingManager(SettingManager settingManager) {
		this.settingManager = settingManager;
	}

	public void setRateManager(RateManager rateManager) {
		this.rateManager = rateManager;
	}

	public void setTicketTempManager(TicketTempManager ticketTempManager) {
		this.ticketTempManager = ticketTempManager;
	}

	public TicketTemp getTicketTemp() {
		return ticketTemp;
	}

	public void setTicketTemp(TicketTemp ticketTemp) {
		this.ticketTemp = ticketTemp;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassengerID() {
		return passengerID;
	}

	public void setPassengerID(String passengerID) {
		this.passengerID = passengerID;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public String[] getTtChairs() {
		ttChairs = new String[ticketTemp.getChairs().size()];
		int i = 0;
		if (ttChairs.length > 0) {
			for (Chair chair : ticketTemp.getChairs()) {
				ttChairs[i] = chair.getId().toString();
				i++;
			}
		}
		return ttChairs;
	}

	public void setTtChairs(String[] ttChairs) {
		this.ttChairs = ttChairs;
	}

	public Map<String, String> getPassengerMap() {
		if (passengerMap == null)
			passengerMap = passengerManager.getMap(false, ticketTemp
					.getService());
		return passengerMap;
	}

/*	public Map<String, String> getServiceMap() {
		if (serviceMap == null)
			serviceMap = serviceManager.getMap(false);
		return serviceMap;
	}*/

	public void setPassengerManager(PassengerManager passengerManager) {
		this.passengerManager = passengerManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public void setChairManager(ChairManager chairManager) {
		this.chairManager = chairManager;
	}

	public void setTicketManager(TicketManager ticketManager) {
		this.ticketManager = ticketManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public Cash getCash() {
		return cash;
	}

	public void setCash(Cash cash) {
		this.cash = cash;
	}

	public Long getId() {
		return id;
	}

	public Long getCashId() {
		return cashId;
	}

	public void setCashId(Long cashId) {
		this.cashId = cashId;
	}
}