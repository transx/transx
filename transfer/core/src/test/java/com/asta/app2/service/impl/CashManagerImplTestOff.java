/* *Class created on [ Jul 28, 2008 | 8:35:35 PM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.CashDao;
import com.asta.app2.model.Cash;
import com.asta.app2.service.BaseManagerMockTestCase;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CashManagerImplTestOff extends BaseManagerMockTestCase {
	private CashDao dao;
	private CashManagerImpl manager;

	@Before
	public void OnSetUp() {
		dao = context.mock(CashDao.class);
		manager = new CashManagerImpl(dao);
	}

/*	@Test
	public void testGetAllEnabledCash() {
		log.debug("testing getAllEnabledCash......");
		final List<Cash> cashes = new ArrayList<Cash>();
		context.checking(new Expectations() {
			{
				one(dao).getAllEnabledCash();
				will(returnValue(cashes));
			}
		});
		List<Cash> result = manager.getAllEnabledCash();
		assertSame(cashes, result);
		log.debug("size of list is :" + result.size());
	}
*/}
