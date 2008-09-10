/* *Class created on [ Jun 7, 2008 | 10:06:06 AM ] */
package com.asta.app2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.asta.app2.model.enums.Weekday;
import com.asta.app2.util.DateUtil;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
@Table(name = "service")
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Service extends BaseObject implements Serializable,Comparable<Service> {
	private static final long serialVersionUID = 772935845822949036L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 10, nullable = false)
	private String timed;
	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = true)
	private Weekday weekday;
	@Column(nullable = false)
	private Date datebook;

	@Column(name = "service_enabled")
	private Boolean enabled;
	@Column(name = "service_locked")
	private Boolean serviceLocked;
	@Column(name = "service_opened")
	private Boolean opened;
	@Column(name = "service_expired")
	private Boolean serviceExpired;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;
	@ManyToOne
	@JoinColumn(name = "car_kind_id", nullable = false)
	private CarKind carKind;
	@ManyToOne
	@JoinColumn(name = "path_id", nullable = false)
	private Path path;
	@ManyToOne
	@JoinColumn(name = "car_id", nullable = true)
	private Car car;

	@ManyToOne
	@JoinColumn(name = "template_id", nullable = false)
	private ServiceTemplate template;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "service_driver", joinColumns = { @JoinColumn(name = "service_id") }, inverseJoinColumns = @JoinColumn(name = "driver_id"))
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Driver> drivers = new HashSet<Driver>();
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "service_lodger", joinColumns = { @JoinColumn(name = "service_id") }, inverseJoinColumns = @JoinColumn(name = "lodger_id"))
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Lodger> lodgers = new HashSet<Lodger>();
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "service_path", joinColumns = { @JoinColumn(name = "service_id") }, inverseJoinColumns = @JoinColumn(name = "path_id"))
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Path> paths = new HashSet<Path>();


	public Service() {
	}

	public Service(long id) {
		this.id = id;
	}

	public Service(ServiceTemplate template,Company company,CarKind carKind,Path path, String timed, Date datebook) {
		this.template = template;
		this.company = company; 
		this.carKind = carKind;
		this.path = path;
		this.timed = timed;
		this.datebook = datebook;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Service))
			return false;

		final Service service = (Service) o;

		return this.getDatebook().equals(service.getDatebook());
	}

	@Override
	public int hashCode() {
		return (datebook != null ? datebook.hashCode() : 0);
	}

	@Override
	public String toString() {
		return getPath() + "-" + getTimed() + "-" + getWeekday().getLabel()
				+ "-" + getDatebookFormatted();
	}

	public int compareTo(Service service) {
		String otherService = service.toString();
		return this.toString().compareTo(otherService);
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

	public Weekday getWeekday() {
		return weekday;
	}

	public void setWeekday(Weekday weekday) {
		this.weekday = weekday;
	}

	public Date getDatebook() {
		return datebook;
	}

	public String getDatebookFormatted() {
		return DateUtil.getLocaleFormattedDate(datebook);
	}

	public void setDatebook(Date datebook) {
		this.datebook = datebook;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getServiceLocked() {
		return serviceLocked;
	}

	public void setServiceLocked(Boolean serviceLocked) {
		this.serviceLocked = serviceLocked;
	}

	public Boolean getOpened() {
		return opened;
	}

	public void setOpened(Boolean opened) {
		this.opened = opened;
	}

	public Boolean getServiceExpired() {
		return serviceExpired;
	}

	public void setServiceExpired(Boolean serviceExpired) {
		this.serviceExpired = serviceExpired;
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

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Set<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(Set<Driver> drivers) {
		this.drivers = drivers;
	}

	public Set<Lodger> getLodgers() {
		return lodgers;
	}

	public void setLodgers(Set<Lodger> lodgers) {
		this.lodgers = lodgers;
	}

	public Set<Path> getPaths() {
		return paths;
	}

	public void setPaths(Set<Path> paths) {
		this.paths = paths;
	}

	/**
	 * adds a driver into service
	 * 
	 * @param driver
	 */
	public void addDriver(Driver driver) {
		getDrivers().add(driver);
	}

	/**
	 * adds a lodger into service
	 * 
	 * @param lodger
	 */
	public void addLodger(Lodger lodger) {
		getLodgers().add(lodger);
	}

	/**
	 * adds a sub path into service
	 * 
	 * @param path
	 */
	public void addPath(Path path) {
		getPaths().add(path);
	}

	public Driver getDriver1() {
		if (drivers.size() > 0){
			Driver driver1 = new Driver();
			driver1 = getDriverList().get(0);
			return driver1;
		}
		return null;
	}

	public Driver getDriver2() {
		if (drivers.size() > 1){
			Driver driver2 = new Driver();
			driver2 = getDriverList().get(1);
			return driver2;
		}
		return null;
	}

	public Driver getDriver3() {
		if (drivers.size() > 2){
			Driver driver3 = new Driver();
			driver3 = getDriverList().get(2);
			return driver3;
		}
		return null;
	}

	public List<Driver> getDriverList() {
		List<Driver> driverList = new ArrayList<Driver>();
		if (drivers.size() > 0) {
			for (Driver driver : drivers) {
				driverList.add(driver);
			}
		}
		return driverList;
	}

	public ServiceTemplate getTemplate() {
		return template;
	}

	public void setTemplate(ServiceTemplate template) {
		this.template = template;
	}
}
