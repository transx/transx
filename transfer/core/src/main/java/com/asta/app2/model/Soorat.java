/* *Class created on [ Jul 9, 2008 | 10:13:21 AM ] */
package com.asta.app2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

	@Column(length = 20, nullable = false)
	private String seri;

	@Column(length = 20, nullable = false)
	private int serial;

	@Column(nullable = true)
	private boolean issued;

	@Column(nullable = true)
	private boolean destroyed;
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

	@Column(nullable = false)
	private Long commission;

	@Column(name = "driver_pay", nullable = false)
	private Long driverPay;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@ManyToOne
	@JoinColumn(name = "cash_id", nullable = true)
	private Cash cash;

	@ManyToOne
	@JoinColumn(name = "cashier_id", nullable = true)
	private User cashier;

	@Column(name = "driver_paid", nullable = false)
	private boolean driverPaid;

	@Column(name = "passenger_count", length = 3, nullable = false)
	private Integer passengerCount;

	@Column(name = "total_is_t_a")
	private Long totalIsTA;

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
			   (serial != 0 ? serial:23);
	}

	@Override
	public String toString() {
		return getSeri()+" - "+ getSerial();
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

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public boolean isIssued() {
		return issued;
	}

	public void setIssued(boolean issued) {
		this.issued = issued;
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

	public boolean isDriverPaid() {
		return driverPaid;
	}

	public void setDriverPaid(boolean driverPaid) {
		this.driverPaid = driverPaid;
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
}