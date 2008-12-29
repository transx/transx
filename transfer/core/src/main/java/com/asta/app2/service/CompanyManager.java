/* *Class created on [ Jun 1, 2008 | 10:26:46 AM ] */
package com.asta.app2.service;

import java.util.List;
import java.util.Map;

import com.asta.app2.model.Company;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface CompanyManager extends GenericManager<Company, Long> {
	List<Company> findByCode(String code);
	Map<String, String> getMap(boolean withEmpty);
	Map<Company, Company> getCompanyItems(boolean withEmpty);
}
