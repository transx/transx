/* *Class created on [ Oct 2, 2008 | 12:46:59 PM ] */ 
package com.asta.app2.webapp.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.model.SelectItem;

import com.asta.app2.model.CarKind;
import com.asta.app2.webapp.jsf.util.Renderers;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CarKindConverter implements Converter, Serializable{

	public Object getAsObject(FacesContext context, UIComponent component,
			String newValue) throws ConverterException {
		CarKind carKind = new CarKind();
		List<SelectItem> children = Renderers.getSelectItems(component); 
		for (SelectItem element : children) {
			if (element.getLabel().equalsIgnoreCase(newValue)){
				carKind = (CarKind) element.getValue();
			}
		}
		return carKind;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (!(value instanceof CarKind))
			throw new ConverterException();
		CarKind ck = (CarKind) value;
		
		return ck.toString();
	}

}


