/* *Class Create on [ Jun 1, 2008 | 9:33:06 AM ] */
package com.asta.app2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
@Table(name = "company")
public class Company extends BaseObject implements Comparable<Company> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 100, nullable = false)
	private String name;
	@Column(length = 50, nullable = false, unique = true)
	private String code;
	@ManyToOne
	@JoinColumn(name = "city_id", nullable = true)
	private City city;

	public Company() {}
	
	public Company(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Company))
			return false;

		final Company company = (Company) o;
		return this.code.equals(company.getCode());
	}

	@Override
	public int hashCode() {
		return (code != null ? code.hashCode() : 23);
	}

	@Override
	public String toString() {
		return getName();
	}

	public int compareTo(Company company) {
		String otherCompany = company.toString();
		return this.toString().compareTo(otherCompany);
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}



}
