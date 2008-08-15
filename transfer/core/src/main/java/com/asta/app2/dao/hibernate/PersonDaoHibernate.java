/* *Class created on [ Jun 3, 2008 | 4:36:54 PM ] */
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.PersonDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.Person;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class PersonDaoHibernate extends GenericDaoHibernate<Person, Long>
		implements PersonDao {

	PersonDao personDao;

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public PersonDaoHibernate() {
		super(Person.class);
	}

	public List<Person> getAllPersonByCompany(Company company) {
		return getHibernateTemplate().find("from Person as p where p.company=?",company);
	}

	public void saveOrUpdate(Person person) {
		getHibernateTemplate().saveOrUpdate(person);
	}

	public List<Person> findPersonById(Company company,long id) {
		return getHibernateTemplate().find("from Person as p where p.company=? and p.id=?", new Object[] { company ,id });
	}

	public Person savePerson(Person person) {
		getHibernateTemplate().saveOrUpdate(person);
		getHibernateTemplate().flush();
		return person;
	}

}
