package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.webapp.action.BasePage;
import com.asta.app2.service.RateManager;

public class RateList extends BasePage implements Serializable {
    private RateManager rateManager;

    public void setRateManager(RateManager rateManager) {
        this.rateManager = rateManager;
    }

    public RateList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getRates() {
        return sort(rateManager.getAll());
    }
}

