package com.asta.app2.webapp.action;

import java.io.Serializable;


import com.asta.app2.model.Person;
import com.asta.app2.service.PersonManager;

public class PersonForm extends BasePage implements Serializable {
	private PersonManager personManager;
	private Person person = new Person();
	private Long id;

	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String delete() {
		
		try{
			personManager.remove(person.getId());
			addMessage("person.deleted");
		}catch(Exception e){
			addError("errors.delete.exception");
			return "edit";
		}

		return "list";
	}

	public String edit() {
		if (id != null) {
			person = personManager.get(id);
		} else {
			person = new Person();
		}

		return "edit";
	}

	public String save() {
		boolean isNew = (person.getId() == null);
		person.setCompany(getCurrentUser().getCompany());
		personManager.save(person);

		String key = (isNew) ? "person.added" : "person.updated";
		addMessage(key);

		if (isNew) {
			return "list";
		} else {
			return "edit";
		}
	}
}