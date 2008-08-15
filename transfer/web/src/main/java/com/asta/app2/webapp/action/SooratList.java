package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.model.Soorat;
import com.asta.app2.service.SooratManager;

public class SooratList extends BasePage implements Serializable {
    private SooratManager sooratManager;

    public void setSooratManager(SooratManager sooratManager) {
        this.sooratManager = sooratManager;
    }

    public SooratList() {
        setSortColumn("id"); // sets the default sort column
    }

    @SuppressWarnings("unchecked")
	public List<Soorat> getSoorats() {
        return sort(sooratManager.getAllSoorat(getCurrentUser().getCompany()));
    }
}

