/* *Class created on [ Jun 5, 2008 | 5:28:02 PM ] */ 
package com.asta.app2.service;

import java.util.List;
import java.util.Map;

import com.asta.app2.model.Car;
import com.asta.app2.model.CarKind;
import com.asta.app2.model.Company;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface CarManager extends GenericManager<Car, Long>{
	
	List<Car> getAllCarByCompany(Company company);
	List<Car> findCarByCode(Company company,String code);
	Map<String, String> getMap(Company company,boolean withEmpty);
	Map<String, String> getCarKindMap(Company company,CarKind carKind);
	
}


