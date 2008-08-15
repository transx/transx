/* *Class created on [ Jun 3, 2008 | 10:42:11 AM ] */
package com.asta.app2.model;

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
import javax.persistence.OneToOne;

import com.asta.app2.model.enums.Gender;
import com.asta.app2.util.DateUtil;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
public class Person extends BaseObject implements Comparable<Person> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;
	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;
	@Column(name = "father_name", length = 50, nullable = false)
	private String fatherName;
	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private Gender gender;
	@Column(nullable = true)
	private Date birthday;
	@Column(name = "identity_number", length = 50, nullable = false)
	private String identityNumber;
	@Column(name = "identity_issue", length = 50, nullable = false)
	private String identityIssue;
	@Column(name = "passport_number", length = 50)
	private String passportNumber;
	@Column(length = 200)
	private String address;
	@Column(length = 50)
	private String phone;
	@OneToOne(mappedBy = "person")
	private Driver driver;
	@OneToOne(mappedBy = "person")
	private Lodger lodger;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = true)
	private Company company;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Person)) {
			return false;
		}
		final Person person1 = (Person) o;

		if (!person1.getIdentityNumber().equals(getIdentityNumber()))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (identityNumber != null ? identityNumber.hashCode() : 0);
	}

	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBirthdayFormatted() {
		return DateUtil.getLocaleFormattedDate(getBirthday());
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getIdentityIssue() {
		return identityIssue;
	}

	public void setIdentityIssue(String identityIssue) {
		this.identityIssue = identityIssue;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int compareTo(Person person) {
		String otherPerson = person.toString();
		return this.toString().compareTo(otherPerson);
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Lodger getLodger() {
		return lodger;
	}

	public void setLodger(Lodger lodger) {
		this.lodger = lodger;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
