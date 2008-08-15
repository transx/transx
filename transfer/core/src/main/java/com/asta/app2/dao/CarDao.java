/* *Class created on [ Jun 5, 2008 | 3:08:37 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Car;
import com.asta.app2.model.CarKind;
import com.asta.app2.model.Company;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface CarDao extends GenericDao<Car, Long>{
	
	List<Car> getAllCarByCompany(Company company);
	List<Car> findCarByCode(Company company,String code);
	List<Car> getAllCarByKind(Company company,CarKind carKind);
}


