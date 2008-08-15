/* *Class created on [ Jun 4, 2008 | 9:12:48 PM ] */
package com.asta.app2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
public class Lodger extends BaseObject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 100)
	private String presenter;
	@OneToOne
	@JoinColumn(name = "person_id", unique = true)
	private Person person;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = true)
	private Company company;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Lodger)) {
			return false;
		}
		final Lodger lodger1 = (Lodger) o;
		if (!lodger1.getId().equals(getId()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return getPerson().getFirstName() + "_" + getPerson().getLastName()
				+ "_" + getPerson().getIdentityNumber();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPresenter() {
		return presenter;
	}

	public void setPresenter(String presenter) {
		this.presenter = presenter;
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
