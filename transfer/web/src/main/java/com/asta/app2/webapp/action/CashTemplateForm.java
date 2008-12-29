package com.asta.app2.webapp.action;

import java.io.Serializable;

import javax.faces.event.ValueChangeEvent;

import com.asta.app2.model.CashTemplate;
import com.asta.app2.model.User;
import com.asta.app2.webapp.action.BasePage;
import com.asta.app2.service.CashTemplateManager;
import com.asta.app2.service.CompanyManager;

public class CashTemplateForm extends BasePage implements Serializable {
	private static final long serialVersionUID = 1L;
	private CashTemplateManager cashTemplateManager;
    private CompanyManager companyManager;
    private CashTemplate cashTemplate = new CashTemplate();
    private Long id;
    private String[] userSelected;
    
    public String delete() {
        cashTemplateManager.remove(cashTemplate.getId());
        addMessage("cashTemplate.deleted");

        return "list";
    }

    public String edit() {
        if (id != null) {
            cashTemplate = cashTemplateManager.get(id);
        } else {
            cashTemplate = new CashTemplate();
            cashTemplate.setCompany(companyManager.get(-1L));
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (cashTemplate.getId() == null);
        
        for (int i = 0; (userSelected != null) && (i < userSelected.length); i++) {
			cashTemplate.addUser(userManager.getUser(userSelected[i]));
		}
        cashTemplateManager.save(cashTemplate);
        
        String key = (isNew) ? "cashTemplate.added" : "cashTemplate.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
    
    public void userChanged(ValueChangeEvent event){
    	userSelected = (String[]) event.getNewValue();
    }
    
    public void setCashTemplateManager(CashTemplateManager cashTemplateManager) {
        this.cashTemplateManager = cashTemplateManager;
    }

    public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
	}

	public CashTemplate getCashTemplate() {
        return cashTemplate;
    }

    public void setCashTemplate(CashTemplate cashTemplate) {
        this.cashTemplate = cashTemplate;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String[] getUserSelected() {
		if (userSelected == null)
			userSelected = new String[cashTemplate.getUsers().size()];
		
		int i = 0;
		if (cashTemplate.getUsers().size() > 0){
			for (User user : cashTemplate.getUsers() ){
				userSelected[i] = user.getId().toString();
				i++;
			}
		}
			
		
		return userSelected;
	}

	public void setUserSelected(String[] userSelected) {
		this.userSelected = userSelected;
	}
    
} 