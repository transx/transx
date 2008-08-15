/* *Class created on [ Jun 3, 2008 | 6:43:51 PM ] */
package com.asta.app2.service;

import java.util.List;
import java.util.Map;

import com.asta.app2.model.Company;
import com.asta.app2.model.Driver;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface DriverManager extends GenericManager<Driver, Long> {
	
	List<Driver> findDriverByCode(Company company, String code);

	Map<String, String> getMap(Company company, boolean withEmpty);
}
