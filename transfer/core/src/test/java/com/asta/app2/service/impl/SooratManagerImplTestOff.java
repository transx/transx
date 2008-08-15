/* *Class created on [ Jul 15, 2008 | 7:18:36 PM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.SooratDao;
import com.asta.app2.model.Soorat;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SooratManagerImplTestOff extends BaseManagerMockTestCase {
	private SooratDao dao;
	private SooratManagerImpl manager;

	@Before
	public void onSetUp() {
		dao = context.mock(SooratDao.class);
		manager = new SooratManagerImpl(dao);
	}

	@After
	public void onTearDown() {
		manager = null;
	}

	@Test
	public void testFindSooratsByService() {
		final List<Soorat> soorats = new ArrayList<Soorat>();
		context.checking(new Expectations() {
			{
				one(dao).getAll();
				will(returnValue(soorats));
			}
		});
		List<Soorat> result = manager.getAll();
		assertSame(soorats,result);
	}
}
