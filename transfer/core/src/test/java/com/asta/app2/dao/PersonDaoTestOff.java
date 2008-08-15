/* *Class created on [ Jun 3, 2008 | 4:31:43 PM ] */
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Person;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class PersonDaoTestOff extends BaseDaoTestCase {
	private PersonDao personDao;

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

/*	public void testFindPersonByFieldValue() {
		log.debug("testing findPersonByFieldValue......");
		assertTrue(personDao.findPersonByFieldValue().size() >= 1);
	}

	public void testEditPersonEntity() {
		log.debug("testing editPersonEntitty.......");
		List<Person> people = personDao.findPersonById(-3L);
		Person person = people.get(0); 
		person.setFirstName("Arash");
		person = personDao.save(person);
		assertSame(person.getFirstName(), "Arash");
	}*/
}
