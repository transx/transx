package com.asta.app2.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country extends BaseObject implements Comparable<Country>{
	private Long id;
	private String name;
	private Set<City> cities;

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

	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	@Column(name = "city_id")
	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Country)) {
			return false;
		}

		final Country user = (Country) o;

		return !(name != null ? !name.equals(user.getName())
				: user.getName() != null);

	}

	@Override
	public int hashCode() {
		return (name != null ? name.hashCode() : 0);
	}

	@Override
	public String toString() {
		return getName();
	}

	public int compareTo(Country country) {
		String otherCountry = country.toString();
		return this.toString().compareTo(otherCountry);
	}

}
