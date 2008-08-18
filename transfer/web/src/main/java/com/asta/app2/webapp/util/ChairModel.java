/* *Class created on [ Jun 15, 2008 | 11:19:07 AM ] */
package com.asta.app2.webapp.util;

import java.io.Serializable;

import com.asta.app2.model.Passenger;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ChairModel implements Serializable {
	private static final long serialVersionUID = -8841138605259338661L;
	private Long id;
	private String[] chairmate;
	private String chairType;
	private String reserverID;
	private Passenger passenger;
	private String pathID;
	private Long ticketNumber;
	private Long ticketPrice;
	private Long ticketToll;
	private Long ticketSnack;
	
	public Long getTicketSnack() {
		return ticketSnack;
	}

	public void setTicketSnack(Long ticketSnack) {
		this.ticketSnack = ticketSnack;
	}

	public Long getTicketToll() {
		return ticketToll;
	}

	public void setTicketToll(Long ticketToll) {
		this.ticketToll = ticketToll;
	}

	public ChairModel() {
	}

	public ChairModel(Long id, String[] chairmate, String chairType,
			String reserverID, Passenger passenger, String pathID) {
		this.id = id;
		this.chairmate = chairmate;
		this.chairType = chairType;
		this.reserverID = reserverID;
		this.passenger = passenger;
		this.pathID = pathID;
	}

	public ChairModel(Long id, Passenger passenger, Long ticketNumber, Long ticketPrice,Long ticketToll,Long ticketSnack) {
		this.id = id;
		this.passenger = passenger;
		this.ticketNumber = ticketNumber;
		this.ticketPrice = ticketPrice;
		this.ticketToll = ticketToll;
		this.ticketSnack = ticketSnack;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String[] getChairmate() {
		return chairmate;
	}

	public void setChairmate(String[] chairmate) {
		this.chairmate = chairmate;
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

	public Long getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(Long ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public Long getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Long ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
}
