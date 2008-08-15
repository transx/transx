package com.asta.app2.webapp.action;

import java.io.Serializable;

import com.asta.app2.model.Country;
import com.asta.app2.service.CountryManager;

public class CountryForm extends BasePage implements Serializable {
    private CountryManager countryManager;
    private Country country = new Country();
    private Long id;

    public void setCountryManager(CountryManager countryManager) {
        this.countryManager = countryManager;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
        countryManager.remove(country.getId());
        addMessage("country.deleted");

        return "list";
    }

    public String edit() {
        if (id != null) {
            country = countryManager.get(id);
        } else {
            country = new Country();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (country.getId() == null);
        countryManager.save(country);

        String key = (isNew) ? "country.added" : "country.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
} 