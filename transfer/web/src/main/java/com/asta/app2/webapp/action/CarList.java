package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.service.CarManager;

public class CarList extends BasePage implements Serializable {
    private CarManager carManager;

    public void setCarManager(CarManager carManager) {
        this.carManager = carManager;
    }

    public CarList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getCars() {
        return sort(carManager.getAllCarByCompany(getCurrentUser().getCompany()));
    }
}

