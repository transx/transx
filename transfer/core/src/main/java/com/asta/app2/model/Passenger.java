/* *Class created on [ Jun 6, 2008 | 11:59:15 PM ] */
package com.asta.app2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.asta.app2.model.enums.Gender;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
public class Passenger extends BaseObject implements Comparable<Passenger>,
		Serializable {
	private static final long serialVersionUID = -159521127793117616L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "first_name", length = 50, nullable = true)
	private String firstName;
	@Column(name = "last_name", length = 100, nullable = false)
	private String lastName;
	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private Gender gender;
	@Column(name = "passport_number", length = 20, nullable = true)
	private String passportNumber;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Passenger))
			return false;
		final Passenger passenger1 = (Passenger) o;

		if (!this.getId().equals(passenger1.getId()))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public String toString() {
		return getGender().getLabelShort() + "_"
				+ (getFirstName() != null ? getFirstName() : "") + " "
				+ getLastName();
	}

	public int compareTo(Passenger passenger) {
		String otherPassenger = passenger.toString();
		return this.toString().compareTo(otherPassenger);
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

}
