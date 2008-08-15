/* *Class created on [ Jun 3, 2008 | 6:29:44 PM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.PersonDao;
import com.asta.app2.model.Person;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class PersonManagerImplTestOff extends BaseManagerMockTestCase {
	private PersonManagerImpl manager;
	private PersonDao dao;

	@Before
	public void setUp() {
		dao = context.mock(PersonDao.class);
		manager = new PersonManagerImpl(dao);
	}

	@After
	public void tearDown() {
		manager = null;
	}

/*	@Test
	public void testFindPersonByFieldName() {
		log.debug("testing findPersonFieldValue......");
		final List<Person> people = new ArrayList<Person>();
		context.checking(new Expectations() {
			{
				one(dao).findPersonByFieldValue();
				will(returnValue(people));
			}
		});
		List<Person> result = manager.findPersonByFieldValue();
		assertSame(people, result);
	}*/
}
