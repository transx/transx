package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.service.PassengerManager;

public class PassengerList extends BasePage implements Serializable {
    private PassengerManager passengerManager;

    public void setPassengerManager(PassengerManager passengerManager) {
        this.passengerManager = passengerManager;
    }

    public PassengerList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getPassengers() {
        return sort(passengerManager.getAll());
    }
}

