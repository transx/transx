/* *Class created on [ Jun 1, 2008 | 9:54:28 AM ] */
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Company;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface CompanyDao extends GenericDao<Company, Long> {
	List<Company> findByCode(String code);

}
