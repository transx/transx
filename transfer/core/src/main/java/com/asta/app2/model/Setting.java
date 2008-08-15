/* *Class created on [ Jul 22, 2008 | 8:34:33 PM ] */
package com.asta.app2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.asta.app2.model.enums.RateType;

/**
 * This entity is for handle [default value] of [setting] in diferent parts of
 * application
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
public class Setting extends BaseObject {

	@Id
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "rate_type", length = 20, nullable = false)
	private RateType rateType;

	@Column(name = "ticket_number", nullable = false)
	private Long ticketNumber;

	@Column(name = "seri_inner", length = 20, nullable = false)
	private String seriInner;

	@Column(name = "seri_outer", length = 20, nullable = false)
	private String seriOuter;

	@Column(name = "seri_sole", length = 20, nullable = false)
	private String seriSole;

	@Column(name = "serial_inner", length = 20, nullable = false)
	private String serialInner;

	@Column(name = "serial_outer", length = 20, nullable = false)
	private String serialOuter;

	@Column(name = "serial_sole", length = 20, nullable = false)
	private String serialSole;

	@Column(nullable = false)
	private long snack;
	
	@Column(nullable = false)
	private long commission;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RateType getRateType() {
		return rateType;
	}

	public void setRateType(RateType rateType) {
		this.rateType = rateType;
	}

	public Long getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(Long ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getSeriInner() {
		return seriInner;
	}

	public void setSeriInner(String seriInner) {
		this.seriInner = seriInner;
	}

	public String getSeriOuter() {
		return seriOuter;
	}

	public void setSeriOuter(String seriOuter) {
		this.seriOuter = seriOuter;
	}

	public String getSeriSole() {
		return seriSole;
	}

	public void setSeriSole(String seriSole) {
		this.seriSole = seriSole;
	}

	public String getSerialInner() {
		return serialInner;
	}

	public void setSerialInner(String serialInner) {
		this.serialInner = serialInner;
	}

	public String getSerialOuter() {
		return serialOuter;
	}

	public void setSerialOuter(String serialOuter) {
		this.serialOuter = serialOuter;
	}

	public String getSerialSole() {
		return serialSole;
	}

	public void setSerialSole(String serialSole) {
		this.serialSole = serialSole;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o instanceof Setting)
			return true;

		final Setting setting = (Setting) o;
		return (ticketNumber != null ? ticketNumber.equals(setting
				.getTicketNumber()) : false);
	}

	@Override
	public int hashCode() {
		return (ticketNumber != null ? ticketNumber.hashCode() : 0);
	}

	@Override
	public String toString() {
		return getRateType() + "_" + getTicketNumber();
	}

	public long getSnack() {
		return snack;
	}

	public void setSnack(long snack) {
		this.snack = snack;
	}

	public long getCommission() {
		return commission;
	}

	public void setCommission(long commission) {
		this.commission = commission;
	}

}
