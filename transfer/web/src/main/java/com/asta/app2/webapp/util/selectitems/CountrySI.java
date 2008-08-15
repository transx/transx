package com.asta.app2.webapp.util.selectitems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.convert.Converter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.asta.app2.model.Country;
import com.asta.app2.model.LabelValueGeneric;
import com.asta.app2.service.GenericManager;
import com.asta.app2.webapp.jsf.ReferenceConverter;

/**
 * @author Saeid Moradi saeid3@gmail.com
 */
@Controller("countrySI")
@Scope("session")
public class CountrySI extends BaseSI implements BaseSIIntf<Country> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5816369678066099890L;
	private GenericManager<Country, Long> countryManager;
	private Map<String, Country> selectItems;
	private List<Country> allObjects;

//	@Resource(name = "countryManager")
	public void setCountryManager(GenericManager<Country, Long> countryManager) {
		this.countryManager = countryManager;
	}

	public Map<String, Country> getSelectItems() {
		return getSelectItems(getRequest().getLocale());
	}

	public Map<String, Country> getSelectItems(Locale locale) {
		if (this.selectItems == null) {
			allObjects = countryManager.getAll();
			List<LabelValueGeneric<Country>> objects = new ArrayList<LabelValueGeneric<Country>>();
			for (Country country : allObjects) {
				final String label = country.getName();
				objects.add(new LabelValueGeneric<Country>(label, country));
			}
			if (null == locale)
				Collections.sort(objects);
			else
				Collections.sort(objects, new LabelValueComparator(locale));

			Map<String, Country> options = new LinkedHashMap<String, Country>();
			for (LabelValueGeneric<Country> lv : objects) {
				options.put(lv.getLabel(), lv.getValue());
			}
			this.selectItems = options;
		}
		return this.selectItems;
	}

	public List<Country> getAllObjects() {
		if (allObjects == null) {
			allObjects = countryManager.getAll();
		}
		return allObjects;
	}

	public Converter getConverter() {
		return new ReferenceConverter<Country>(getAllObjects());
	}
	
	public String getFoo(){
		return "Country Title";
	}
}
