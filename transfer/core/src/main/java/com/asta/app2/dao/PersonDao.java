/* *Class created on [ Jun 3, 2008 | 4:32:48 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Company;
import com.asta.app2.model.Person;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface PersonDao extends GenericDao<Person, Long>{
	List<Person> getAllPersonByCompany(Company company); 
	void saveOrUpdate(Person person);
	List<Person> findPersonById(Company company, long id);
	Person savePerson(Person person);
}


