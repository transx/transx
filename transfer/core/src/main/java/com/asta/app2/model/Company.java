/* *Class Create on [ Jun 1, 2008 | 9:33:06 AM ] */
package com.asta.app2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
public class Company extends BaseObject implements Comparable<Company> {
	private Long id;
	private String name;
	private String code;
	private City city;

	public Company() {}
	
	public Company(Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = 100, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 50, nullable = false, unique = true)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@ManyToOne
	@JoinColumn(name = "city_id", nullable = true)
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Company))
			return false;

		final Company company = (Company) o;
		return this.getId().equals(company.getId());
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public String toString() {
		return getName();
	}

	public int compareTo(Company company) {
		String otherCompany = company.toString();
		return this.toString().compareTo(otherCompany);
	}

}
