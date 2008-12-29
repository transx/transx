/* *Class created on [ Oct 15, 2008 | 11:06:38 AM ] */ 
package com.asta.app2.webapp.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.model.SelectItem;

import com.asta.app2.model.Company;
import com.asta.app2.webapp.jsf.util.Renderers;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CompanyConverter implements Converter,Serializable{

	public Object getAsObject(FacesContext context, UIComponent component,
			String newValue) throws ConverterException {
		Company company = new Company();
		List<SelectItem> companyItems = Renderers.getSelectItems(component);
		for (SelectItem element: companyItems){
			if (element.getLabel().equalsIgnoreCase(newValue))
				company = (Company) element.getValue();
		}
		
		return company;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (!(value instanceof Company))
			throw new ConverterException();
		
		Company company = (Company) value;
		return company.toString();
	}

}


