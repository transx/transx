/* *Class created on [ Jun 3, 2008 | 6:15:11 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Company;
import com.asta.app2.model.Driver;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface DriverDao extends GenericDao<Driver, Long>{

	List<Driver> getAllDriverByCompany(Company company);
	List<Driver> findDriverByCode(Company company,String code);

}


