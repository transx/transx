/* *Class created on [ Jun 4, 2008 | 9:50:35 PM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.LodgerDao;
import com.asta.app2.model.Lodger;
import com.asta.app2.service.BaseManagerMockTestCase;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class LodgerManagerImplTestOff extends BaseManagerMockTestCase {
	private LodgerManagerImpl manager;
	private LodgerDao dao;

	@Before
	public void setUp() {
		dao = context.mock(LodgerDao.class);
		manager = new LodgerManagerImpl(dao);
	}

	@After
	public void tearDown() {
		manager = null;
	}

/*	@Test
	public void testFindLodgerByPresenter() {
		log.debug("testing findLodgerByPresenter......");
		final List<Lodger> lodgers = new ArrayList<Lodger>();
		context.checking(new Expectations() {
			{
				one(dao).findLodgerByPresenter("معرف1");
				will(returnValue(lodgers));
			}
		});
		List<Lodger> result = manager.findLodgerByPresenter("معرف1");
		assertSame(lodgers,result);
	}
*/}
