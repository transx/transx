/* *Class created on [ Oct 2, 2008 | 5:55:11 PM ] */ 
package com.asta.app2.webapp.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.model.SelectItem;

import com.asta.app2.Constants;
import com.asta.app2.model.Car;
import com.asta.app2.webapp.jsf.util.Renderers;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CarConverter implements Converter,Serializable{

	public Object getAsObject(FacesContext context, UIComponent component,
			String newValue) throws ConverterException {
		Car car = null;
		List<SelectItem> children = Renderers.getSelectItems(component);
		for(SelectItem element: children){
			if (element.getLabel().equalsIgnoreCase(newValue)){
				car = (Car) element.getValue();
			}
		}			
		return car;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null)
			return Constants.EMPTY;
		
		else if (value.toString().equalsIgnoreCase(Constants.EMPTY))
			return Constants.EMPTY;
		
		if (!(value instanceof Car))
			throw new ConverterException();
		
		Car car = (Car) value;
		if (car.getId() == null)
			return Constants.EMPTY;
		return car.toString();
	}

}


