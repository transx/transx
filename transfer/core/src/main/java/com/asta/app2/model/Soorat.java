/* *Class created on [ Jul 9, 2008 | 10:13:21 AM ] */
package com.asta.app2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * This entity is for gathering and saving the information of each
 * serviceEntity.
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
public class Soorat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "service_id", nullable = true)
	private Service service;

	@Column(length = 20, nullable = false)
	private String seri;

	@Column(length = 20, nullable = false)
	private String serial;

	@Column(nullable = true)
	private boolean issued;

	@Column(nullable = true)
	private boolean destroyed;

	private Long total;
	@Column(name = "government_toll")
	private Long governmentToll;

	@Column(name = "insurance_sarneshin")
	private Long insuranceSarneshin;

	@Column(name = "insurance_badaneh")
	private Long insuranceBadaneh;

	private Long snack;

	private Long commission;

	@Column(name = "driver_pay")
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
	
	@Column(name = "driver_paid")
	private boolean driverPaid; 
	
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

}
