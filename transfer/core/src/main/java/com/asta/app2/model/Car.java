/* *Class created on [ Jun 5, 2008 | 10:09:21 AM ] */
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
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
@Table(name = "car")
public class Car extends BaseObject implements Comparable<Car> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 20, nullable = false, unique = true)
	private String code;
	@Column(length = 10, name = "plaque_serial", nullable = false)
	private String plaqueSerial;
	@Column(length = 20, name = "plaque_number", nullable = false)
	private String plaqueNumber;
	@Column(length = 50, name = "plaque_issue", nullable = false)
	private String plaqueIssue;
	@Column(length = 50, nullable = false)
	private String motor;
	@Column(length = 50, nullable = false)
	private String chassis;
	@Column(length = 50, nullable = false)
	private String transit;
	@Column(name="company_build", length = 50, nullable = false)
	private String companyBuild;
	@Column(length = 10, name = "build_year", nullable = false)
	private String buildYear;
	@Column(length = 20, name = "statistic_card")
	private String statisticCard;
	@Column(length = 20, name = "smart_card")
	private String smartCard;
	@Column(name = "insurance_exam_deadline", nullable = false)
	private Date insuranceExamDeadline;
	@Column(name = "insurance_third_deadline", nullable = false)
	private Date insuranceThirdDeadline;
	@Column(name = "insurance_body_apart_deadline", nullable = false)
	private Date insuranceBodyApartDeadline;
	@ManyToOne
	@JoinColumn(name = "car_kind_id", nullable = true)
	private CarKind carKind;
	@ManyToOne
	@JoinColumn(name = "person_id", nullable = true)
	private Person person;

	@Column(name = "insurance_badaneh_jodaganeh")
	private Boolean insuranceBadanehJodaganeh;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (!(o instanceof Car))
			return false;

		final Car car1 = (Car) o;
		if (!car1.getId().equals(getId()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return (code != null ? code.hashCode() : 0);
	}

	public int compareTo(Car o) {
		String otherCar = o.toString();
		return this.toString().compareTo(otherCar);
	}

	@Override
	public String toString() {
		return getPlaqueNumber() + "-" + getPlaqueSerial() + getPlaqueIssue();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPlaqueSerial() {
		return plaqueSerial;
	}

	public void setPlaqueSerial(String plaqueSerial) {
		this.plaqueSerial = plaqueSerial;
	}

	public String getPlaqueNumber() {
		return plaqueNumber;
	}

	public void setPlaqueNumber(String plaqueNumber) {
		this.plaqueNumber = plaqueNumber;
	}

	public String getPlaqueIssue() {
		return plaqueIssue;
	}

	public void setPlaqueIssue(String plaqueIssue) {
		this.plaqueIssue = plaqueIssue;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public String getChassis() {
		return chassis;
	}

	public void setChassis(String chassis) {
		this.chassis = chassis;
	}

	public String getTransit() {
		return transit;
	}

	public void setTransit(String transit) {
		this.transit = transit;
	}

	public String getCompanyBuild() {
		return companyBuild;
	}

	public void setCompanyBuild(String companyBuild) {
		this.companyBuild = companyBuild;
	}

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public String getStatisticCard() {
		return statisticCard;
	}

	public void setStatisticCard(String statisticCard) {
		this.statisticCard = statisticCard;
	}

	public String getSmartCard() {
		return smartCard;
	}

	public void setSmartCard(String smartCard) {
		this.smartCard = smartCard;
	}

	public Date getInsuranceExamDeadline() {
		return insuranceExamDeadline;
	}

	public String getInsuranceExamDeadlineFormatted() {
		return DateUtil.getLocaleFormattedDate(insuranceExamDeadline);
	}

	public void setInsuranceExamDeadline(Date insuranceExamDeadline) {
		this.insuranceExamDeadline = insuranceExamDeadline;
	}

	public Date getInsuranceThirdDeadline() {
		return insuranceThirdDeadline;
	}
	
	public String getInsuranceThirdDeadlineFormatted() {
		return DateUtil.getLocaleFormattedDate(insuranceThirdDeadline);
	}

	public void setInsuranceThirdDeadline(Date insuranceThirdDeadline) {
		this.insuranceThirdDeadline = insuranceThirdDeadline;
	}

	public Date getInsuranceBodyApartDeadline() {
		return insuranceBodyApartDeadline;
	}

	public String getInsuranceBodyApartDeadlineFormatted() {
		return DateUtil.getLocaleFormattedDate(insuranceBodyApartDeadline);
	}

	public void setInsuranceBodyApartDeadline(Date insuranceBodyApartDeadline) {
		this.insuranceBodyApartDeadline = insuranceBodyApartDeadline;
	}

	public CarKind getCarKind() {
		return carKind;
	}

	public void setCarKind(CarKind carKind) {
		this.carKind = carKind;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Boolean getInsuranceBadanehJodaganeh() {
		return insuranceBadanehJodaganeh;
	}

	public void setInsuranceBadanehJodaganeh(Boolean insuranceBadanehJodaganeh) {
		this.insuranceBadanehJodaganeh = insuranceBadanehJodaganeh;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
