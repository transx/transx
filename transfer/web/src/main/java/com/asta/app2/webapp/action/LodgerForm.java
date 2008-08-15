package com.asta.app2.webapp.action;

import java.io.Serializable;

import com.asta.app2.model.Lodger;
import com.asta.app2.model.Person;
import com.asta.app2.service.LodgerManager;
import com.asta.app2.service.PersonManager;

public class LodgerForm extends BasePage implements Serializable {
	private Long id;
    private LodgerManager lodgerManager;
    private Lodger lodger = new Lodger();
    private PersonManager personManager;
    private Person person = new Person();
    


    public String delete() {
    	try{
	        lodgerManager.remove(lodger.getId());
	        addMessage("lodger.deleted");
		}catch(Exception e){
			addError("errors.delete.exception");
			return "edit";
		}

        return "list";
    }

    public String edit() {
        if (id != null) {
            lodger = lodgerManager.get(id);
            person = lodger.getPerson();
        } else {
            lodger = new Lodger();
            person = new Person();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (lodger.getId() == null);
        
        person = personManager.savePerson(person);
        lodger.setPerson(person);
        lodgerManager.save(lodger);

        String key = (isNew) ? "lodger.added" : "lodger.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }

	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}
    public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setLodgerManager(LodgerManager lodgerManager) {
        this.lodgerManager = lodgerManager;
    }

    public Lodger getLodger() {
        return lodger;
    }

    public void setLodger(Lodger lodger) {
        this.lodger = lodger;
    }

    public void setId(Long id) {
        this.id = id;
    }
} 