/* *Class created on [ Jun 1, 2008 | 10:25:37 AM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.CompanyDao;
import com.asta.app2.model.Company;
import com.asta.app2.service.BaseManagerMockTestCase;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CompanyManagerImplTestOff extends BaseManagerMockTestCase {
	private CompanyManagerImpl manager;
	private CompanyDao dao;

	@Before
	public void setUp() {
		dao = context.mock(CompanyDao.class);
		manager = new CompanyManagerImpl(dao);
	}

	@After
	public void tearDown() {
		manager = null;
	}

	@Test
	public void testFindByCode() {
		log.debug("testing findByCode......");
		final List<Company> companies = new ArrayList<Company>();
		context.checking(new Expectations() {
			{
				one(dao).findByCode("11222");
				will(returnValue(companies));
			}
		});
		List<Company> result = manager.findByCode("11222");
		assertSame(companies,result);
	}

}
