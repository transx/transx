package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.asta.app2.model.User;
import com.asta.app2.service.ServiceTemplateManager;

public class ServiceTemplateList extends BasePage implements Serializable {
    private ServiceTemplateManager serviceTemplateManager;

    public void setServiceTemplateManager(ServiceTemplateManager serviceTemplateManager) {
        this.serviceTemplateManager = serviceTemplateManager;
    }

    public ServiceTemplateList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getServiceTemplates() {
    	SecurityContext ctx = SecurityContextHolder.getContext();
		User currentUser = (User) ctx.getAuthentication().getPrincipal();
        return sort(serviceTemplateManager.getAllByCompany(currentUser.getCompany()));
    }
}

