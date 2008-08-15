package com.asta.app2.webapp.action;

import java.io.Serializable;

import com.asta.app2.model.Service;
import com.asta.app2.model.Soorat;
import com.asta.app2.service.SooratManager;

public class SooratForm extends BasePage implements Serializable {
    private SooratManager sooratManager;
    private Soorat soorat = new Soorat();
    private Service service = new Service();
    private Long id;

    public void setSooratManager(SooratManager sooratManager) {
        this.sooratManager = sooratManager;
    }

    public Soorat getSoorat() {
        return soorat;
    }

    public void setSoorat(Soorat soorat) {
        this.soorat = soorat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
        sooratManager.remove(soorat.getId());
        addMessage("soorat.deleted");

        return "list";
    }

    public String edit() {
        if (id != null) {
            soorat = sooratManager.get(id);
            service = soorat.getService();
        } else {
            soorat = new Soorat();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (soorat.getId() == null);
        soorat.setService(getService());
        sooratManager.save(soorat);

        String key = (isNew) ? "soorat.added" : "soorat.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
} 