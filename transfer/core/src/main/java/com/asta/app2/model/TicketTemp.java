/* *Class created on [ Jun 24, 2008 | 11:37:26 AM ] */
package com.asta.app2.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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

import com.asta.app2.model.enums.TicketTempType;
import com.asta.app2.util.DateUtil;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
@Table(name = "ticket_temp")
public class TicketTemp extends BaseObject implements Serializable,
		Comparable<TicketTemp> {
	private static final long serialVersionUID = -6283953728270207934L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer count;
	@Column(name = "reserve_date")
	private Date reserveDate;
	@Column(name = "commit_date")
	private Date commitDate;

	private boolean committed;
	@Column(name = "reserver_id")
	private String reserverId;
	@ManyToOne
	@JoinColumn(name = "passenger_id", nullable = false)
	private Passenger passenger;
	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	private Service service;
	@ManyToOne
	@JoinColumn(name = "path_id", nullable = false)
	private Path path;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ticket_temp_chair", joinColumns = { @JoinColumn(name = "ticket_temp_id") }, inverseJoinColumns = @JoinColumn(name = "chair_id"))
	private Set<Chair> chairs = new HashSet<Chair>();
	
	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@Enumerated(EnumType.STRING)
	@Column(name = "ticket_temp_type", length = 20 , nullable = false)
	private TicketTempType ticketTempType;

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof TicketTemp))
			return false;

		final TicketTemp ticketTemp = (TicketTemp) o;
		return (reserveDate != null ? reserveDate.equals(ticketTemp
				.getReserveDate()) : false);
	}

	@Override
	public int hashCode() {
		return (reserveDate != null ? reserveDate.hashCode() : 0);
	}

	@Override
	public String toString() {
		return getId() + "_" + getService();
	}

	public int compareTo(TicketTemp ticketTemp) {
		String otherTicketTemp = ticketTemp.toString();
		return this.toString().compareTo(otherTicketTemp);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getReserveDate() {
		return reserveDate;
	}

	public String getReserveDateFormatted() {
		return DateUtil.getLocaleFormattedDate(reserveDate);
	}

	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}

	public Date getCommitDate() {
		return commitDate;
	}

	public String getCommitDateFormatted() {
		return DateUtil.getLocaleFormattedDate(commitDate);
	}

	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}

	public boolean isCommitted() {
		return committed;
	}

	public void setCommitted(boolean committed) {
		this.committed = committed;
	}

	public String getReserverId() {
		return reserverId;
	}

	public void setReserverId(String reserverId) {
		this.reserverId = reserverId;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Set<Chair> getChairs() {
		return chairs;
	}

	public void setChairs(Set<Chair> chairs) {
		this.chairs = chairs;
	}

	public void addChair(Chair chair) {
		this.getChairs().add(chair);
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public TicketTempType getTicketTempType() {
		return ticketTempType;
	}

	public void setTicketTempType(TicketTempType ticketTempType) {
		this.ticketTempType = ticketTempType;
	}
}
