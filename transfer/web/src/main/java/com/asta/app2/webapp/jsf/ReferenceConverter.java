package com.asta.app2.webapp.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * 
 * 
 * <p>
 * Based on http://wiki.jboss.org/wiki/Wiki.jsp?page=SelectItems
 * 
 * @param <T>
 */
public class ReferenceConverter<T> implements Serializable, Converter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1100627491163130596L;

	private static final String EMPTY = "__EMPTY__";

	private List<T> values;

	public ReferenceConverter(List<T> vals) {
		this.values = vals;
	}

	public String getAsString(FacesContext facesContext, UIComponent component,
			Object obj) {
		if (obj == null)
			return EMPTY;
		if ("0".equals(obj))
			return EMPTY;

		String val = String.valueOf(obj.hashCode());
		return val;
	}

	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String str) throws ConverterException {
		if (str == null || str.length() == 0 || EMPTY.equals(str)) {
			return null;
		}

		int hash = Integer.valueOf(str).intValue();
		for (T val : values) {
			if (val != null && val.hashCode() == hash) {
				return val;
			}
		}

		return null;
	}

}