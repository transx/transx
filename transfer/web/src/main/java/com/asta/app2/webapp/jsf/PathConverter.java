package com.asta.app2.webapp.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.model.SelectItem;

import com.asta.app2.model.Path;
import com.asta.app2.webapp.jsf.util.Renderers;

public class PathConverter implements Converter,Serializable{

	public Object getAsObject(FacesContext context, UIComponent component,
			String newValue) throws ConverterException {
		Path path = null;
		List<SelectItem> children = Renderers.getSelectItems(component);
		for(SelectItem element : children){
			if (element.getLabel().equalsIgnoreCase(newValue))
				path = (Path) element.getValue();
		}
		return path;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (!(value instanceof Path))
			throw new ConverterException();
		
		Path path = (Path) value;
		return path.toString();
	}

}
