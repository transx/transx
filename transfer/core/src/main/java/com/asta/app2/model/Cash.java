/* *Class created on [ Jul 28, 2008 | 12:23:28 PM ] */
package com.asta.app2.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.asta.app2.util.DateUtil;

/**
 * This class is for handles monetary transactions
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
@Table(name = "cash")
public class Cash extends BaseObject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "time_open")
	private Date timeOpen;

	@Column(name = "time_close")
	private Date timeClose;

	@Column(name = "cash_enabled")
	private boolean enabled;

	@Column(name = "cash_expired")
	private boolean expired;

	@Column(name = "count_ticket")
	private Integer count;

	@Column(name = "total_price")
	private Long totalPrice;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@ManyToOne
	@JoinColumn(name = "cash_template_id" , nullable = false)
	private CashTemplate cashTemplate;
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o instanceof Cash)
			return true;

		final Cash cash = (Cash) o;
		return (timeOpen != null ? timeOpen.equals(cash.getTimeOpen()) : false);
	}

	@Override
	public int hashCode() {
		return (timeOpen != null ? timeOpen.hashCode() : 23)
				+ (timeClose != null ? timeClose.hashCode() : 23);
	}

	@Override
	public String toString() {
		return getId().toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimeOpen() {
		return timeOpen;
	}
	
	public String getTimeOpenFormatted() {
		return DateUtil.getLocaleFormattedDateTime(timeOpen);
	}

	public void setTimeOpen(Date timeOpen) {
		this.timeOpen = timeOpen;
	}

	public Date getTimeClose() {
		return timeClose;
	}

	public String getTimeCloseFormatted() {
		return DateUtil.getLocaleFormattedDateTime(timeClose);
	}
	
	public void setTimeClose(Date timeClose) {
		this.timeClose = timeClose;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public CashTemplate getCashTemplate() {
		return cashTemplate;
	}

	public void setCashTemplate(CashTemplate cashTemplate) {
		this.cashTemplate = cashTemplate;
	}

	
}
