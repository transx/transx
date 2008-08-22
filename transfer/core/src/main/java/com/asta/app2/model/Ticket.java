/* *Class created on [ Jun 9, 2008 | 7:07:04 PM ] */
package com.asta.app2.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.asta.app2.util.DateUtil;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
@Table(name = "ticket")
public class Ticket extends BaseObject implements Serializable,
		Comparable<Ticket> {
	private static final long serialVersionUID = 4048276008346340310L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private Long number;
	@Column(nullable = false)
	private Integer count;
	@Column(nullable = false)
	private Long price;
	@Column(nullable = false)
	private Long toll;
	@Column(nullable = false)
	private Long snack;
	@Column(nullable = false)
	private Long total;

	@Column(name = "reserve_date")
	private Date reserveDate;
	@Column(name = "issue_date")
	private Date issueDate;
	@Column(name = "return_date")
	private Date returnDate;

	private boolean issued;

	@Column(name = "reserver_id")
	private String reserverId;
	@ManyToOne
	@JoinColumn(name = "cashier_id", nullable = false)
	private User cashier;
	@ManyToOne
	@JoinColumn(name = "returner_id", nullable = true)
	private User returner;
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
	@JoinTable(name = "ticket_chair", joinColumns = { @JoinColumn(name = "ticket_id") }, inverseJoinColumns = @JoinColumn(name = "chair_id"))
	private Set<Chair> chairs = new HashSet<Chair>();

	@ManyToOne
	@JoinColumn(name = "cash_id", nullable = false)
	private Cash cash;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	public Ticket() {
	}

	public Ticket(Long number, Long price, Long toll, Long snack,
			Integer count, Date reserveDate, Date issueDate, String reserverId,
			User cashier, Passenger passenger, Service service, Path path) {
		this.number = number;
		this.price = price;
		this.toll = toll;
		this.snack = snack;
		this.count = count;
		this.reserveDate = reserveDate;
		this.issueDate = issueDate;
		this.reserverId = reserverId;
		this.cashier = cashier;
		this.passenger = passenger;
		this.service = service;
		this.path = path;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Ticket))
			return false;

		final Ticket ticket = (Ticket) o;
		return (reserveDate != null ? reserveDate.equals(ticket
				.getReserveDate()) : false);
	}

	@Override
	public int hashCode() {
		return (number != null ? number.hashCode() : 0);
	}

	@Override
	public String toString() {
		return getCash().getId().toString() + "_" + getNumber();
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

	public Date getIssueDate() {
		return issueDate;
	}

	public String getIssueDateFormatted() {
		return DateUtil.getLocaleFormattedDate(issueDate);
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public String getReturnDateFormatted() {
		return DateUtil.getLocaleFormattedDate(returnDate);
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isIssued() {
		return issued;
	}

	public void setIssued(boolean issued) {
		this.issued = issued;
	}

	public String getReserverId() {
		return reserverId;
	}

	public void setReserverId(String reserverId) {
		this.reserverId = reserverId;
	}

	public User getCashier() {
		return cashier;
	}

	public void setCashier(User cashier) {
		this.cashier = cashier;
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

	public int compareTo(Ticket ticket) {
		String otherTicket = ticket.toString();
		return this.toString().compareTo(otherTicket);
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

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Cash getCash() {
		return cash;
	}

	public void setCash(Cash cash) {
		this.cash = cash;
	}

	public Long getToll() {
		return toll;
	}

	public void setToll(Long toll) {
		this.toll = toll;
	}

	public Long getSnack() {
		return snack;
	}

	public void setSnack(Long snack) {
		this.snack = snack;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public User getReturner() {
		return returner;
	}

	public void setReturner(User returner) {
		this.returner = returner;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
