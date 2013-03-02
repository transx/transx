package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.asta.app2.Constants;
import com.asta.app2.model.Chair;
import com.asta.app2.model.Role;
import com.asta.app2.model.Ticket;
import com.asta.app2.model.User;
import com.asta.app2.service.ChairManager;
import com.asta.app2.service.PassengerManager;
import com.asta.app2.service.ServiceManager;
import com.asta.app2.service.TicketManager;
import com.asta.app2.service.UserManager;

// This class need to be edited !  
public class TicketForm extends BasePage implements Serializable {

	private static final long serialVersionUID = -8195243967765393312L;
	private TicketManager ticketManager;
	private Ticket ticket = new Ticket();
	private Long id;
	private UserManager userManager;
	private PassengerManager passengerManager;
	private ServiceManager serviceManager;
	private ChairManager chairManager;
	private String cashierID;
	private String passengerID;
	private String serviceID;
	private Map<String, String> cashierMap;
	private Map<String, String> passengerMap;
//	private Map<String, String> serviceMap;
	private Map<String, String> availableChairs;
	private String[] ticketChairs;

	
	public String delete() {
		ticketManager.remove(ticket.getId());
		addMessage("ticket.deleted");

		return "list";
	}

	public String edit() {
		if (id != null) {
			ticket = ticketManager.get(id);
			setCashierID(ticket.getCashier() != null ? ticket.getCashier()
					.getId().toString() : "");
			setPassengerID(ticket.getPassenger().getId().toString());
			setServiceID(ticket.getService().getId().toString());
		} else {
			ticket = new Ticket();
		}

		return "edit";
	}

	public String save() {
		boolean isNew = (ticket.getId() == null);
		ticket.setCount(ticketChairs.length);
		// ticket.setReserveDate(new Date());
		// e.g. User user = (User) getSession().getAttribute("user");
		ticket.setReserverId(ticket.getReserverId());
		if (ticket.isIssued())
			ticket.setIssueDate(new Date());
		ticket.setPassenger(passengerManager.get(Long.valueOf(getPassengerID())
				.longValue()));
		ticket.setService(serviceManager.get(Long.valueOf(getServiceID())
				.longValue()));

		setTicketChairs(getRequest().getParameterValues(
				"ticketForm:ticketChairs"));
		for (int i = 0; (ticketChairs != null) && (i < ticketChairs.length); i++) {
			long id = Long.valueOf(ticketChairs[i]).longValue();
			ticket.addChair(chairManager.get(id));
		}

		ticketManager.save(ticket);

		String key = (isNew) ? "ticket.added" : "ticket.updated";
		addMessage(key);

		if (isNew) {
			return "list";
		} else {
			return "edit";
		}
	}
	
	public String return0(){
		if (id != null) {
			Ticket ticket = ticketManager.get(id);
			if (ticket.isIssued()){
				SecurityContext ctx = SecurityContextHolder.getContext();
				User returner = (User) ctx.getAuthentication().getPrincipal();
				ticket.setReturner(returner);
				ticket.setIssued(false);
				ticket.setReturnDate(new Date());
				ticket.setSnack(0L);
				ticket.setPrice(0L);
				ticket.setTotal(ticket.getTotal()* -1);
				try{
					ticketManager.saveTicket(getCurrentUser().getCompany(),ticket,new Long(0L));
				}catch(Exception e){
					
				}
			}
		}	
		return "noWhereSoThisWillBackToTheCurrentList";
	}

	public String return10(){
		if (id != null) {
			Ticket ticket = ticketManager.get(id);
			if (ticket.isIssued()){
				SecurityContext ctx = SecurityContextHolder.getContext();
				User returner = (User) ctx.getAuthentication().getPrincipal();
				ticket.setReturner(returner);
				ticket.setIssued(false);
				ticket.setReturnDate(new Date());
				ticket.setSnack((ticket.getSnack()*10)/100);
				ticket.setPrice((ticket.getPrice()*10)/100);
				ticket.setTotal(((ticket.getSnack()+ticket.getPrice())*ticket.getCount())-ticket.getTotal());
				try{
					ticketManager.saveTicket(getCurrentUser().getCompany(),ticket,new Long(0L));
				}catch(Exception e){
					
				}
			}
		}	
		return "noWhereSoThisWillBackToTheCurrentList";
	}

	public String return50(){
		if (id != null) {
			Ticket ticket = ticketManager.get(id);
			if (ticket.isIssued()){
				SecurityContext ctx = SecurityContextHolder.getContext();
				User returner = (User) ctx.getAuthentication().getPrincipal();
				ticket.setReturner(returner);
				ticket.setIssued(false);
				ticket.setReturnDate(new Date());
				ticket.setSnack((ticket.getSnack()*50)/100);
				ticket.setPrice((ticket.getPrice()*50)/100);
				ticket.setTotal(((ticket.getSnack()+ticket.getPrice())*ticket.getCount())-ticket.getTotal());
				
				try{
					ticketManager.saveTicket(getCurrentUser().getCompany(),ticket,new Long(0L));
				}catch(Exception e){
					
				}
			}
		}	
		return "noWhereSoThisWillBackToTheCurrentList";
	}

	public void setTicketManager(TicketManager ticketManager) {
		this.ticketManager = ticketManager;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setChairManager(ChairManager chairManager) {
		this.chairManager = chairManager;
	}

	public String getCashierID() {
		return cashierID;
	}

	public void setCashierID(String cashierID) {
		this.cashierID = cashierID;
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

	public Map<String, String> getCashierMap() {
		if (cashierMap == null)
			cashierMap = userManager.getSpecificMap(getCurrentUser().getCompany(),true,Constants.CASHIER_ROLE);
		return cashierMap;
	}

	public Map<String, String> getPassengerMap() {
		if (passengerMap == null)
			passengerMap = passengerManager.getMap(false, serviceManager
					.get(-1L));// -1L!!!CORRECT ME!!!
								// Long.valueOf(getServiceID()).longValue())
		return passengerMap;
	}

/*	public Map<String, String> getServiceMap() {
		if (serviceMap == null)
			serviceMap = serviceManager.getMap(false);
		return serviceMap;
	}*/

	public Map<String, String> getAvailableChairs() {
		if (availableChairs == null) {
			// !!! CORRECT ME , HARD CODE!!!
			availableChairs = chairManager.getMap(32);
		}
		return availableChairs;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void setPassengerManager(PassengerManager passengerManager) {
		this.passengerManager = passengerManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public String[] getTicketChairs() {
		ticketChairs = new String[ticket.getChairs().size()];

		int i = 0;
		if (ticketChairs.length > 0) {
			for (Chair chair : ticket.getChairs()) {
				ticketChairs[i] = chair.getId().toString();
				i++;
			}

		}

		return ticketChairs;
	}

	public void setTicketChairs(String[] ticketChairs) {
		this.ticketChairs = ticketChairs;
	}

}