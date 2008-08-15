package com.asta.app2.webapp.util.selectitems;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.convert.Converter;

/**
 * 
 * @author Rene Guenther rene.guenther@innflow.com
 * 
 * @param <T>
 */
public interface BaseSIIntf<T> extends Serializable {
	public Map<String, T> getSelectItems(Locale locale);

	public Map<String, T> getSelectItems();

	public List<T> getAllObjects();

	public Converter getConverter();
}