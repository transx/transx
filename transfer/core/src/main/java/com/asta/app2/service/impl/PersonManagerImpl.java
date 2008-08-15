/* *Class created on [ Jun 3, 2008 | 6:32:17 PM ] */
package com.asta.app2.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.asta.app2.dao.PersonDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.Person;
import com.asta.app2.service.PersonManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class PersonManagerImpl extends GenericManagerImpl<Person, Long> implements PersonManager{
	private PersonDao personDao;

	public PersonManagerImpl(PersonDao personDao) {
		super(personDao);
		this.personDao = personDao;
	}

	public List<Person> getAllPersonByCompany(Company company) {
		return personDao.getAllPersonByCompany(company);
	}

	public void saveOrUpdate(Person person) {
		personDao.saveOrUpdate(person);
	}

	public Person findPersonById(Company company,long id) {
		return personDao.findPersonById(company, id).get(0);
	}

	public Map<String, String> getPersonMap(Company company,boolean withEmpty) {
		List<Person> persons = personDao.getAllPersonByCompany(company);
		Map<String, String> map = new TreeMap<String, String>();
		map.clear();
		if (withEmpty) {
			map.put("", null);
		}
		for (Person person : persons) {
			map.put(person.toString() == null ? "" : person.toString(), person
					.getId().toString());
		}
		return map;
	}

	public Person savePerson(Person person) {
		return personDao.savePerson(person);
	}

}
