/* *Class created on [ Jun 3, 2008 | 1:23:16 PM ] */
package com.asta.app2.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.asta.app2.util.DateUtil;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
public class Driver extends BaseObject implements Serializable,
		Comparable<Driver> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8722268066593745206L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 50, unique = true)
	private String code;
	@Column(name = "code_note", length = 50, unique = true)
	private String codeNote;
	@Column(name = "licence_number", length = 50, nullable = false, unique = true)
	private String licenceNumber;
	@Column(name = "licence_issue", length = 100, nullable = false)
	private String licenceIssue;
	@Column(name = "insurance_number", length = 50, nullable = false)
	private String insuranceNumber;
	@Column(name = "insurance_deadline", nullable = false)
	private Date insuranceDeadline;
	@Column(name = "contract_deadline")
	private Date contractDeadline;
	@OneToOne
	@JoinColumn(name = "person_id", unique = true)
	private Person person;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = true)
	private Company company;

	public Driver() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Person)) {
			return false;
		}
		final Driver driver1 = (Driver) o;

		if (!driver1.getLicenceNumber().equals(getLicenceNumber()))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (licenceNumber != null ? licenceNumber.hashCode() : 0);
	}

	@Override
	public String toString() {
		return getCode();
	}

	public int compareTo(Driver driver) {
		String otherDriver = driver.toString();
		return this.toString().compareTo(otherDriver);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeNote() {
		return codeNote;
	}

	public void setCodeNote(String codeNote) {
		this.codeNote = codeNote;
	}

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	public String getLicenceIssue() {
		return licenceIssue;
	}

	public void setLicenceIssue(String licenceIssue) {
		this.licenceIssue = licenceIssue;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public Date getInsuranceDeadline() {
		return insuranceDeadline;
	}

	public String getInsuranceDeadlineFormatted() {
		return DateUtil.getLocaleFormattedDate(insuranceDeadline);
	}

	public void setInsuranceDeadline(Date insuranceDeadline) {
		this.insuranceDeadline = insuranceDeadline;
	}

	public Date getContractDeadline() {
		return contractDeadline;
	}

	public String getContractDeadlineFormatted() {
		return DateUtil.getLocaleFormattedDate(contractDeadline);
	}

	public void setContractDeadline(Date contractDeadline) {
		this.contractDeadline = contractDeadline;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
