/* *Class created on [ Jul 26, 2008 | 3:46:44 PM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.InsuranceSarneshinDao;
import com.asta.app2.model.InsuranceSarneshin;
import com.asta.app2.model.enums.Distance;
import com.asta.app2.service.BaseManagerMockTestCase;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class InsuranceSarneshinManagerImplTestOff extends BaseManagerMockTestCase {
	private InsuranceSarneshinDao dao;
	private InsuranceSarneshinManagerImpl manager;

	@Before
	public void onSetUp() {
		dao = context.mock(InsuranceSarneshinDao.class);
		manager = new InsuranceSarneshinManagerImpl(dao);
	}

	@Test
	public void testFindISbyDistanceCapacity() {
		log.debug("testing findISbyDistanceCapacity......");
		final List<InsuranceSarneshin> iss = new ArrayList<InsuranceSarneshin>();

		context.checking(new Expectations() {
			{
				one(dao).findISbyDistanceCapacity(Distance.UP300, 40);
				will(returnValue(iss));
			}
		});
		List<InsuranceSarneshin> result = manager.findISbyDistanceCapacity(
				Distance.UP300, 40);
		assertSame(iss, result);
	}
}
