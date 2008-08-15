package com.asta.app2.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class City extends BaseObject implements Comparable<City> {
	private Long id;
	private String name;
	private String codeOrg;
	private String codeInr;
	private Country country;
	private Set<Path> paths;
	private Set<Path> paths2;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = 150, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 100, name = "organization_code")
	public String getCodeOrg() {
		return codeOrg;
	}

	public void setCodeOrg(String codeOrg) {
		this.codeOrg = codeOrg;
	}

	@Column(length = 100, name = "inner_code")
	public String getCodeInr() {
		return codeInr;
	}

	public void setCodeInr(String codeInr) {
		this.codeInr = codeInr;
	}

	@ManyToOne
	@JoinColumn(name = "country_id", nullable = true)
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@OneToMany(mappedBy = "start", cascade = CascadeType.ALL)
	@Column(name = "city_start")
	public Set<Path> getPaths() {
		return paths;
	}

	public void setPaths(Set<Path> paths) {
		this.paths = paths;
	}

	@OneToMany(mappedBy = "end", cascade = CascadeType.ALL)
	@Column(name = "city_end")
	public Set<Path> getPaths2() {
		return paths2;
	}

	public void setPaths2(Set<Path> paths2) {
		this.paths2 = paths2;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof City))
			return false;

		final City city = (City) o;
		return (id != null ? id.equals(city.getId()) : false);
	}

	@Override
	public int hashCode() {
		return (id != null? id.hashCode():0);
	}

	@Override
	public String toString() {
		return getName();
	}

	public int compareTo(City city) {
		// Implicitly tests for the correct type, throwing
		// ClassCastException as required by interface
		String otherCity = city.toString();

		return this.toString().compareTo(otherCity);
	}

}
