/* *Class created on [ Jun 12, 2008 | 3:25:36 PM ] */
package com.asta.app2.webapp.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.transaction.annotation.Transactional;

import com.asta.app2.Constants;
import com.asta.app2.model.Chair;
import com.asta.app2.model.Passenger;
import com.asta.app2.model.Path;
import com.asta.app2.model.Service;
import com.asta.app2.model.Ticket;
import com.asta.app2.model.TicketTemp;
import com.asta.app2.model.enums.Gender;
import com.asta.app2.model.enums.TicketTempType;
import com.asta.app2.service.ChairManager;
import com.asta.app2.exception.ChairReservedException;
import com.asta.app2.service.PassengerManager;
import com.asta.app2.service.PathManager;
import com.asta.app2.service.ServiceManager;
import com.asta.app2.service.TicketManager;
import com.asta.app2.service.TicketTempManager;
import com.asta.app2.webapp.util.ChairModel;

/**
 * this class is all about reservation page (reserveForm.xhtml)
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ReserveForm extends BasePage implements Serializable {
	private static final long serialVersionUID = 2555716245986788007L;
	private Service service = new Service();
	private Passenger passenger = new Passenger();
	private Passenger newPassenger = new Passenger();
	private TicketTemp ticketTemp = new TicketTemp();
	private Long id;

	private ServiceManager serviceManager;
	private TicketManager ticketManager;
	private TicketTempManager ticketTempManager;
	private ChairManager chairManager;
	private PathManager pathManager;
	private PassengerManager passengerManager;

	private List<Ticket> tickets;
	private List<TicketTemp> tts;
	private List<ChairModel> chairModels;

	private int capacity = 0;

	private String reserverID = "?";
	private String pathID;
	private Map<String, String> pathMap;

	private String isChairDisabled = "true";
	private String isChairReserved = "false";
	private String isChairRegisted = "true";
	private boolean disabled = true;
	// this field is hard coded inside of reserveContainer component.
	private String[] selectedChairs;

	private TicketTempType ticketTempType;

	public String changeChair() {

		if (isChairRegisted.equals("true"))
			setDisabled(true);
		else
			setDisabled(false);
		if (isChairReserved.equals("true")) {
			if (reserverID.equals(getRequest().getRemoteUser()))
				setDisabled(false);
			else
				setDisabled(true);
		}
		return null;
	}

	public String edit() {
		if (id != 0) {
			service = serviceManager.get(id);
		}
		return "edit";
	}
	
	public String enter() {
		return "edit";
	}
	
	@Transactional
	public String save() throws IOException {
		//service should be update for check is Opened.
		//service = serviceManager.get(service.getId());
		if (service.getOpened()) {
			boolean isNew = (ticketTemp.getId() == null);
			String lastName = null;
			String genderString = null;
			String pathString = null;

			lastName = getRequest().getParameter("reserveForm:lastName");
			genderString = getRequest().getParameter("reserveForm:gender");
			pathString = getRequest().getParameter("reserveForm:path");

			if (lastName == null || lastName == "" || genderString == null || pathString == null) {
				addError("reserve.required");
			} else {
				Gender gender = null;
				if (genderString.equals(Gender.MALE.toString()))
					gender = Gender.MALE;
				else
					gender = Gender.FEMALE;
				passenger.setGender(gender);
				passenger.setLastName(lastName);
				passenger.setFirstName(getRequest().getParameter("reserveForm:firstName"));
				passenger.setPassportNumber(getRequest().getParameter("reserveForm:passportNumber"));
				Path path = pathManager.get(Long.valueOf(pathString).longValue());
				passenger = passengerManager.save(passenger);
				ticketTemp.setPassenger(passenger);
				ticketTemp.setPath(path);
				ticketTemp.setService(service);
				ticketTemp.setCommitted(false);
				ticketTemp.setReserveDate(new Date());
				ticketTemp.setReserverId(getRequest().getRemoteUser());
				ticketTemp.setTicketTempType(ticketTempType);
				
				String key;
				setSelectedChairs(null);
				ticketTemp.getChairs().clear();
				setSelectedChairs(getRequest().getParameterValues("reserveForm:selectedChairs"));
				if (selectedChairs != null && selectedChairs.length > 0){
					for (int i = 0; (selectedChairs != null)&& (i < selectedChairs.length); i++) {
						long id = Long.valueOf(selectedChairs[i]).longValue();
						ticketTemp.addChair(chairManager.get(id));
					}
					key = (isNew) ? "reserve.added" : "reserve.updated";
				}else{
					key = "reserve.empty";
				}
				try {
					ticketTemp.setCount(ticketTemp.getChairs().size());
					ticketTempManager.saveTicketTemp(getCurrentUser().getCompany(),ticketTemp);
					if (isNew)
						tts.add(ticketTemp);
					if (key.equals("reserve.empty")){
						addError(key);
					}else{
						addMessage(key, ticketTemp.getChairs().toString());
					}
				} catch (ChairReservedException cre) {
					addError("reserve.chair", ticketTemp.getChairs().toString());
				}
			}
		}else{
			addError("reserve.closed");
			return "list";
		}
		return "edit";
	}

	@Transactional
	public String refresh() {
		tickets.clear();
		tts.clear();
		setTickets(ticketManager.getTicketsByService(getCurrentUser().getCompany(), service));
		setTts(ticketTempManager.findTicketTempsByService(getCurrentUser().getCompany(), service)); 
		setDisabled(false);
		return "edit";
	}
	@Transactional
	public String newTT() {
//		service = serviceManager.get(service.getId());
		passenger = new Passenger();
		ticketTemp = new TicketTemp();
		setDisabled(false);
		return "edit";
	}
	public List<ChairModel> getChairModels() {
		chairModels = new ArrayList<ChairModel>();
		chairModels.clear();
		for (Ticket ticket : getTickets()) {
			for (Chair chair : ticket.getChairs()) {
				ChairModel model = new ChairModel(chair.getId(),Constants.CHAIR_REGISTERED, ticket.getReserverId(),ticket.getPassenger(), ticket.getPath().getId().toString());
				chairModels.add(model);
			}
		}
		for (TicketTemp tt : getTts()) {
			for (Chair chair : tt.getChairs()) {
				ChairModel model = new ChairModel(chair.getId(),Constants.CHAIR_RESERVED, tt.getReserverId(), tt.getPassenger(), tt.getPath().getId().toString(),tt.getTicketTempType());
				chairModels.add(model);
			}
		}
		return chairModels;
	}

	@Transactional
	public List<Ticket> getTickets() {
		if (tickets == null)
			tickets = ticketManager.getTicketsByService(getCurrentUser().getCompany(),service);
		return tickets;
	}
	@Transactional
	public List<TicketTemp> getTts() {
		if (tts == null)
			tts = ticketTempManager.findTicketTempsByService(getCurrentUser().getCompany(),service);
		return tts;
	}

	
	public TicketTempType getTicketTempType() {
		return ticketTempType;
	}

	public void setTicketTempType(TicketTempType ticketTempType) {
		this.ticketTempType = ticketTempType;
	}

	public void setPassengerManager(PassengerManager passengerManager) {
		this.passengerManager = passengerManager;
	}

	public TicketTemp getTicketTemp() {
		return ticketTemp;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}


	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public void setTicketManager(TicketManager ticketManager) {
		this.ticketManager = ticketManager;
	}

	public void setChairManager(ChairManager chairManager) {
		this.chairManager = chairManager;
	}

	public int getCapacity() {
		if (service.getCarKind() != null)
			capacity = service.getCarKind().getCapacity();
		return capacity;
	}

	public Map<String, String> getPathMap() {
		pathMap = new TreeMap<String, String>();
		pathMap.clear();
		if (service != null)
			pathMap.put(service.getPath().getEnd().getName(), service.getPath().getId()
					.toString());
		for (Path path : service.getPaths()) {
			pathMap.put(path.toString() == null ? "" : path.getEnd().getName(), path
					.getId().toString());
		}
		return pathMap;
	}

	public String getIsChairRegisted() {
		return isChairRegisted;
	}

	public void setIsChairRegisted(String isChairRegisted) {
		this.isChairRegisted = isChairRegisted;
	}

	public String getIsChairReserved() {
		return isChairReserved;
	}

	public void setIsChairReserved(String isChairReserved) {
		this.isChairReserved = isChairReserved;
	}

	public void setTicketTempManager(TicketTempManager ticketTempManager) {
		this.ticketTempManager = ticketTempManager;
	}

	public String getReserverID() {
		return reserverID;
	}

	public void setReserverID(String reserverID) {
		this.reserverID = reserverID;
	}

	public String getPathID() {
	 if (pathID == null)
		 pathID = service.getPath().getId().toString();
		return pathID;
	}

	public void setPathID(String pathID) {
		this.pathID = pathID;
	}

	public String getIsChairDisabled() {
		return isChairDisabled;
	}

	public void setIsChairDisabled(String isChairDisabled) {
		this.isChairDisabled = isChairDisabled;
	}

	public String[] getSelectedChairs() {
		if (selectedChairs == null) {
			selectedChairs = new String[getCapacity()];
			selectedChairs[0] = "0";
		}
		return selectedChairs;
	}

	public void setSelectedChairs(String[] selectedChairs) {
		this.selectedChairs = selectedChairs;
	}

	public Passenger getPassenger() {
		if (passenger == null) {
			passenger = new Passenger();
		}
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Passenger getNewPassenger() {
		newPassenger = new Passenger();
		return newPassenger;
	}

	public void setNewPassenger(Passenger newPassenger) {
		this.newPassenger = newPassenger;
	}

	public void setPathManager(PathManager pathManager) {
		this.pathManager = pathManager;
	}

	public void setTts(List<TicketTemp> tts) {
		this.tts = tts;
	}

	public boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

}
