package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.service.PersonManager;

public class PersonList extends BasePage implements Serializable {
    private PersonManager personManager;

    public void setPersonManager(PersonManager personManager) {
        this.personManager = personManager;
    }

    public PersonList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getPersons() {
        return sort(personManager.getAll());
    }
}

