/* *Class created on [ Jul 9, 2008 | 10:13:21 AM ] */
package com.asta.app2.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.asta.app2.model.enums.SooratType;

/**
 * This entity is for gathering and saving the information of each
 * serviceEntity.
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
@Table(name = "soorat")
public class Soorat extends BaseObject implements Serializable, Comparable<Soorat> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	private Service service;
	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;
	@ManyToOne
	@JoinColumn(name = "cash_id", nullable = true)
	private Cash cash;
	@ManyToOne
	@JoinColumn(name = "cashier_id", nullable = true)
	private User cashier;

	@Column(length = 20, nullable = false)
	private String seri;

	@Column(length = 20, nullable = false)
	private String serial;

	@Column(nullable = true)
	private Boolean issued;
	@Column(nullable = true)
	private Boolean destroyed;
	@Column(name = "driver_paid", nullable = false)
	private Boolean driverPaid;
	
	@Column(nullable = false)
	private Long total;
	@Column(name = "government_toll", nullable = false)
	private Long governmentToll;
	@Column(name = "insurance_sarneshin", nullable = false)
	private Long insuranceSarneshin;
	@Column(name = "insurance_badaneh", nullable = false)
	private Long insuranceBadaneh;
	@Column(nullable = false)
	private Long snack;
	@Column(name = "total_is_t_a")
	private Long totalIsTA;
	@Column(nullable = false)
	private Long commission;
	@Column(name = "driver_pay", nullable = false)
	private Long driverPay;


	@Column(name = "passenger_count", length = 3, nullable = false)
	private Integer passengerCount;
	@Column(nullable = true) 
	private Long days;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "soorat_type", length = 10, nullable = false)
	private SooratType sooratType;

	@Column(nullable = true)
	private String pathway;
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o instanceof Soorat)
			return true;
		
		final Soorat soorat = (Soorat) o;
		return this.toString().equals(soorat.toString());
	}

	@Override
	public int hashCode() {
		return (seri != null ? seri.hashCode() :23)+
			   (serial != null ? serial.hashCode():23);
	}

	@Override
	public String toString() {
		return (seri != null ? getSeri():"")+" - "+ 
				(serial != null ? getSerial():"");
	}

	public int compareTo(Soorat soorat) {
		final String anotherString = soorat.toString();
		return this.toString().compareTo(anotherString);
	}
	
	public Long getId() {
		return id;
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

	public String getSeri() {
		return seri;
	}

	public void setSeri(String seri) {
		this.seri = seri;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Boolean getDestroyed() {
		return destroyed;
	}

	public void setDestroyed(Boolean destroyed) {
		this.destroyed = destroyed;
	}

	public Boolean getIssued() {
		return issued;
	}

	public void setIssued(Boolean issued) {
		this.issued = issued;
	}

	public Boolean getDriverPaid() {
		return driverPaid;
	}

	public void setDriverPaid(Boolean driverPaid) {
		this.driverPaid = driverPaid;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getGovernmentToll() {
		return governmentToll;
	}

	public void setGovernmentToll(Long governmentToll) {
		this.governmentToll = governmentToll;
	}

	public Long getInsuranceSarneshin() {
		return insuranceSarneshin;
	}

	public void setInsuranceSarneshin(Long insuranceSarneshin) {
		this.insuranceSarneshin = insuranceSarneshin;
	}

	public Long getInsuranceBadaneh() {
		return insuranceBadaneh;
	}

	public void setInsuranceBadaneh(Long insuranceBadaneh) {
		this.insuranceBadaneh = insuranceBadaneh;
	}

	public Long getSnack() {
		return snack;
	}

	public void setSnack(Long snack) {
		this.snack = snack;
	}

	public Long getCommission() {
		return commission;
	}

	public void setCommission(Long commission) {
		this.commission = commission;
	}

	public Long getDriverPay() {
		return driverPay;
	}

	public void setDriverPay(Long driverPay) {
		this.driverPay = driverPay;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Cash getCash() {
		return cash;
	}

	public void setCash(Cash cash) {
		this.cash = cash;
	}

	public User getCashier() {
		return cashier;
	}

	public void setCashier(User cashier) {
		this.cashier = cashier;
	}

	public Integer getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(Integer passengerCount) {
		this.passengerCount = passengerCount;
	}

	public Long getTotalIsTA() {
		return totalIsTA;
	}

	public void setTotalIsTA(Long totalIsTA) {
		this.totalIsTA = totalIsTA;
	}

	public SooratType getSooratType() {
		return sooratType;
	}

	public void setSooratType(SooratType sooratType) {
		this.sooratType = sooratType;
	}

	public Long getDays() {
		return days;
	}

	public void setDays(Long days) {
		this.days = days;
	}

	public String getPathway() {
		return pathway;
	}

	public void setPathway(String pathway) {
		this.pathway = pathway;
	}
}