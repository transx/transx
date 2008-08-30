/* *Class created on [ Jun 2, 2008 | 8:14:48 PM ] */
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
@Table(name = "service_template")
public class ServiceTemplate extends BaseObject implements
		Comparable<ServiceTemplate> {
	private static final long serialVersionUID = 3539733334372171218L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 10, nullable = true)
	private String timed;
	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;
	@ManyToOne
	@JoinColumn(name = "car_kind_id", nullable = false)
	private CarKind carKind;
	@ManyToOne
	@JoinColumn(name = "path_id", nullable = false)
	private Path path;

	public ServiceTemplate() {}
	
	public ServiceTemplate(Company company,Path path,CarKind carKind) {
		this.company=company;
		this.path=path;
		this.carKind=carKind;
	}
	public ServiceTemplate(Company company,Path path,CarKind carKind,String timed) {
		this.company=company;
		this.path=path;
		this.carKind=carKind;
		this.timed=timed;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof ServiceTemplate))
			return false;
		final ServiceTemplate st = (ServiceTemplate) o;
		return (id != null ? id.equals(st.getId()) : false);
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public String toString() {
		return getCarKind() + ":" + getPath();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTimed() {
		return timed;
	}

	public void setTimed(String timed) {
		this.timed = timed;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public CarKind getCarKind() {
		return carKind;
	}

	public void setCarKind(CarKind carKind) {
		this.carKind = carKind;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public int compareTo(ServiceTemplate serviceTemplate) {
		String otherServiceTemplate = serviceTemplate.toString();
		return this.toString().compareTo(otherServiceTemplate);
	}

}
