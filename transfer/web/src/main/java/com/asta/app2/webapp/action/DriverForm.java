package com.asta.app2.webapp.action;

import java.io.Serializable;

import com.asta.app2.model.Driver;
import com.asta.app2.model.Person;
import com.asta.app2.service.DriverManager;
import com.asta.app2.service.PersonManager;

public class DriverForm extends BasePage implements Serializable {
	private DriverManager driverManager;
	private Driver driver = new Driver();
	private Long id;
	private Person person = new Person();
	private PersonManager personManager;

	public String delete() {
		try {
			driverManager.remove(driver.getId());
			addMessage("driver.deleted");
		} catch (Exception e) {
			addError("errors.delete.exception");
			return "edit";
		}

		return "list";
	}

	public String edit() {
		if (id != null) {
			driver = driverManager.get(id);
			person = driver.getPerson();
		} else {
			driver = new Driver();
			person = new Person();
		}

		return "edit";
	}

	public String save() {
		boolean isNew = (driver.getId() == null);
		
		person.setCompany(getCurrentUser().getCompany());
		person = personManager.savePerson(person);
		driver.setCompany(getCurrentUser().getCompany());
		driver.setPerson(person);
		driverManager.save(driver);

		String key = (isNew) ? "driver.added" : "driver.updated";
		addMessage(key);

		if (isNew) {
			return "list";
		} else {
			return "edit";
		}
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}

	public void setDriverManager(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public void setId(Long id) {
		this.id = id;
	}

}