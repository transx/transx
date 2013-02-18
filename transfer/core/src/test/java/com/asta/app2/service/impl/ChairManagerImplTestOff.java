/* *Class created on [ Jun 14, 2008 | 5:36:03 PM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.ChairDao;
import com.asta.app2.model.Chair;
import com.asta.app2.service.BaseManagerMockTestCase;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ChairManagerImplTestOff extends BaseManagerMockTestCase {
	private ChairManagerImpl manager;
	private ChairDao dao;

	@Before
	public void setUp() {
		dao = context.mock(ChairDao.class);
		manager = new ChairManagerImpl(dao);
	}

	@After
	public void tearDown() {
		manager = null;
	}

	@Test
	public void testFindChairsByServiceId() {
		log.debug("testing findChairsByService......");
		final List<Chair> chairs = new ArrayList<Chair>();

		context.checking(new Expectations() {
			{
				one(dao).findChairsByServiceId(-1L);
				will(returnValue(chairs));
			}
		});
		List<Chair> result = manager.findChairsByServiceId(-1L);
		assertSame(chairs,result);
	}
}
