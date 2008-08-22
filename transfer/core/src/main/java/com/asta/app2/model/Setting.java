/* *Class created on [ Jul 22, 2008 | 8:34:33 PM ] */
package com.asta.app2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.asta.app2.model.enums.RateType;

/**
 * This entity is for handle [default value] of [setting] in diferent parts of
 * application
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
@Table(name = "setting")
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

	@Column(name = "seri_private", length = 20, nullable = false)
	private String seriPrivate;

	@Column(name = "serial_inner", length = 20, nullable = false)
	private int serialInner;

	@Column(name = "serial_outer", length = 20, nullable = false)
	private int serialOuter;

	@Column(name = "serial_private", length = 20, nullable = false)
	private int serialPrivate;

	@Column(nullable = false)
	private long snack;

	@Column(nullable = false)
	private long commission;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@Column(name = "company_phone", length = 20, nullable = false)
	private String companyPhone;

	@Column(name = "company_place", length = 100, nullable = false)
	private String companyPlace;

	@Column(nullable = true)
	private Long stamp;

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

	public String getSeriPrivate() {
		return seriPrivate;
	}

	public void setSeriSole(String seriPrivate) {
		this.seriPrivate = seriPrivate;
	}

	public int getSerialInner() {
		return serialInner;
	}

	public void setSerialInner(int serialInner) {
		this.serialInner = serialInner;
	}

	public int getSerialOuter() {
		return serialOuter;
	}

	public void setSerialOuter(int serialOuter) {
		this.serialOuter = serialOuter;
	}

	public int getSerialPrivate() {
		return serialPrivate;
	}

	public void setSerialPrivate(int serialPrivate) {
		this.serialPrivate = serialPrivate;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public Long getStamp() {
		return stamp;
	}

	public void setStamp(Long stamp) {
		this.stamp = stamp;
	}

	public String getCompanyPlace() {
		return companyPlace;
	}

	public void setCompanyPlace(String companyPlace) {
		this.companyPlace = companyPlace;
	}

}
