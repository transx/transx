package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.service.CarKindManager;

/**
 * 
 * @author  <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CarKindList extends BasePage implements Serializable {
    private CarKindManager carKindManager;

    public void setCarKindManager(CarKindManager carKindManager) {
        this.carKindManager = carKindManager;
    }

    public CarKindList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getCarKinds() {
        return sort(carKindManager.getAll());
    }
}

