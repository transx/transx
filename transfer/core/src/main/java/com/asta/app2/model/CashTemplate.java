/* *Class created on [ Oct 13, 2008 | 6:39:47 PM ] */ 
package com.asta.app2.model;

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
import javax.persistence.Transient;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
@Table(name = "cash_template")
public class CashTemplate extends BaseObject{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false,length = 3)
	private String number;
	
	@Column(nullable = false)
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "cash_template_user", joinColumns = { @JoinColumn(name = "cash_template_id") }, inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users = new HashSet<User>();
	
	@ManyToOne
	@JoinColumn (name ="company_id", nullable = false)
	private Company company;


	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o instanceof CashTemplate)
			return true;
		
		CashTemplate cashTemplate = (CashTemplate) o;
		return (number != null ? number.equals(cashTemplate.getNumber()): false);
	}

	@Override
	public int hashCode() {
		return number.hashCode() + company.hashCode();
	}

	@Override
	public String toString() {
		return company.toString()+ ":" + number;
	}

	public void addUser(User user){
		getUsers().add(user);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Set<User> getUsers() {
		return users;
	}

	@Transient
	public String getUsernames(){
	StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (getUsers().size() > 0){
			for(User user : getUsers()){
				sb.append(user.getUsername());
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}


