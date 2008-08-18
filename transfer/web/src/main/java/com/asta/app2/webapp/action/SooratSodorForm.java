package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private Passenger newPassenger;
	private SettingManager settingManager;
	private Long snack;
	private InsuranceSarneshinManager insuranceSarneshinManager;
	private InsuranceBadanehManager insuranceBadanehManager;

	public String delete() {
		sooratManager.remove(soorat.getId());
		addMessage("soorat.deleted");

		return "list";
	}

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
			soorat = new Soorat();
		}
		setting = settingManager.get(1L);
		soorat.setSeri(setting.getSeriInner());
		soorat.setSerial(setting.getSerialInner());
		soorat.setSnack(setting.getSnack());
		soorat.setCommission(setting.getCommission());
		return "issueInner";
	}

	public String save() {
		boolean isNew = (soorat.getId() == null);
		soorat.setService(getService());
		soorat.setCompany(getCurrentUser().getCompany());
		sooratManager.save(soorat);

		String key = (isNew) ? "soorat.added" : "soorat.updated";
		addMessage(key);

		if (isNew) {
			return "list";
		} else {
			return "edit";
		}
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
			snack = snack + ticket.getSnack();
			toll = toll + ticket.getToll();
		}
		soorat.setPassengerCount(passengerCount);
		soorat.setTotal(total);
		soorat.setSnack(snack);
		soorat.setGovernmentToll(toll);
		return chairModels;
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
			tickets = ticketManager.getTicketsByService(getCurrentUser()
					.getCompany(), service);
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