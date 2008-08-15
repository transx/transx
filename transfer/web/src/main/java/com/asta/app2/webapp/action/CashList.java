package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.webapp.action.BasePage;
import com.asta.app2.model.Cash;
import com.asta.app2.model.Company;
import com.asta.app2.service.CashManager;
import com.asta.app2.service.GenericManager;

public class CashList extends BasePage implements Serializable {
    private CashManager cashManager;

    public void setCashManager(CashManager cashManager) {
        this.cashManager = cashManager;
    }

    public CashList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getCashes() {
        return sort(cashManager.getAllCash(getCurrentUser().getCompany()));
    }
    
    public List<Cash> getAllEnabledCashNotExpired(){
    	return sort(cashManager.getAllEnabledCashNotExpired(getCurrentUser().getCompany()));
    }

}

