package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.Date;

import com.asta.app2.model.Cash;
import com.asta.app2.webapp.action.BasePage;
import com.asta.app2.service.CashManager;

public class CashForm extends BasePage implements Serializable {
    private CashManager cashManager;
    private Cash cash = new Cash();
    private Long id;

    public void setCashManager(CashManager cashManager) {
        this.cashManager = cashManager;
    }

    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
    	try{
	        cashManager.remove(cash.getId());
	        addMessage("cash.deleted");
		}catch(Exception e){
			addError("errors.delete.exception");
			return "edit";
		}

        return "list";
    }

    public String edit() {
        if (id != null) {
            cash = cashManager.get(id);
        } else {
            cash = new Cash();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (cash.getId() == null);
        
        cash.setCompany(getCurrentUser().getCompany());
        cashManager.save(cash);

        String key = (isNew) ? "cash.added" : "cash.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
    
    public String enableCash(){
    	if (id != null){
    		Cash cash = cashManager.get(id);
    		cash.setEnabled(true);
    		cash.setTimeOpen(new Date());
    		cashManager.save(cash);
    	}
    	return "list";
    }

    public String expireCash(){
    	if (id != null){
    		Cash cash = cashManager.get(id);
    		cash.setExpired(true);
    		cash.setTimeClose(new Date());
    		cashManager.save(cash);
    	}
    	return "list";
    }
    
	public Long getId() {
		return id;
	}
} 