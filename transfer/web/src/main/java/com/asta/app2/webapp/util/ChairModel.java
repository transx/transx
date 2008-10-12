/* *Class created on [ Jun 15, 2008 | 11:19:07 AM ] */
package com.asta.app2.webapp.util;

import java.io.Serializable;

import com.asta.app2.model.Passenger;
import com.asta.app2.model.enums.TicketTempType;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ChairModel implements Serializable {
	private static final long serialVersionUID = -8841138605259338661L;
	private Long id;
	private String chairType;
	private String reserverID;
	private String pathID;
	private Passenger passenger;
	private TicketTempType ticketTempType;

	public ChairModel() {
	}

	public ChairModel(Long id, String chairType,String reserverID, Passenger passenger, String pathID) {
		this.id = id;
		this.chairType = chairType;
		this.reserverID = reserverID;
		this.passenger = passenger;
		this.pathID = pathID;
	}

	public ChairModel(Long id, String chairType,String reserverID, Passenger passenger, String pathID,TicketTempType ticketTempType) {
		this.id = id;
		this.chairType = chairType;
		this.reserverID = reserverID;
		this.passenger = passenger;
		this.pathID = pathID;
		this.ticketTempType = ticketTempType;
	}


	public TicketTempType getTicketTempType() {
		return ticketTempType;
	}

	public void setTicketTempType(TicketTempType ticketTempType) {
		this.ticketTempType = ticketTempType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChairType() {
		return chairType;
	}

	public void setChairType(String chairType) {
		this.chairType = chairType;
	}

	public String getReserverID() {
		return reserverID;
	}

	public void setReserverID(String reserverID) {
		this.reserverID = reserverID;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public String getPathID() {
		return pathID;
	}

	public void setPathID(String pathID) {
		this.pathID = pathID;
	}
}
