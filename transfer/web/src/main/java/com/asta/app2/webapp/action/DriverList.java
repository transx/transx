package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.service.DriverManager;

public class DriverList extends BasePage implements Serializable {
    private DriverManager driverManager;

    public void setDriverManager(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public DriverList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getDrivers() {
        return sort(driverManager.getAll());
    }
}

