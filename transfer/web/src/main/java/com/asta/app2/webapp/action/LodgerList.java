package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.service.LodgerManager;

public class LodgerList extends BasePage implements Serializable {
    private LodgerManager lodgerManager;

    public void setLodgerManager(LodgerManager lodgerManager) {
        this.lodgerManager = lodgerManager;
    }

    public LodgerList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getLodgers() {
        return sort(lodgerManager.getAll());
    }
}

