package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.webapp.action.BasePage;
import com.asta.app2.service.CashTemplateManager;

public class CashTemplateList extends BasePage implements Serializable {
    private CashTemplateManager cashTemplateManager;

    public void setCashTemplateManager(CashTemplateManager cashTemplateManager) {
        this.cashTemplateManager = cashTemplateManager;
    }

    public CashTemplateList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getCashTemplates() {
        return sort(cashTemplateManager.getAllDistinct());
    }
}

