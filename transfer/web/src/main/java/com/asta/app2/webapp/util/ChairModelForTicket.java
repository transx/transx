/* *Class created on [ Oct 12, 2008 | 12:56:43 PM ] */ 
package com.asta.app2.webapp.util;

import com.asta.app2.model.Passenger;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ChairModelForTicket {
	private Long id;
	private Passenger passenger;
	private Long ticketNumber;
	private Long ticketPrice;
	private Long ticketToll;
	private Long ticketSnack;

	public ChairModelForTicket(Long id, Passenger passenger, Long ticketNumber, Long ticketPrice,Long ticketToll,Long ticketSnack) {
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

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
}


