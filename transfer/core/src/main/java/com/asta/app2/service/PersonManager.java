/* *Class created on [ Jun 3, 2008 | 6:31:09 PM ] */ 
package com.asta.app2.service;

import java.util.List;
import java.util.Map;

import com.asta.app2.model.Company;
import com.asta.app2.model.Person;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface PersonManager extends GenericManager<Person, Long>{
	List<Person> getAllPersonByCompany(Company company);
	void saveOrUpdate(Person person);
	Person findPersonById(Company company,long id);
	Map<String, String> getPersonMap(Company company,boolean withEmpty);
	Person savePerson(Person person);
}


