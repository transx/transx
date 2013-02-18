/* *Class created on [ Jul 26, 2008 | 11:03:33 PM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.InsuranceBadanehDao;
import com.asta.app2.model.InsuranceBadaneh;
import com.asta.app2.model.enums.Quality;
import com.asta.app2.service.BaseManagerMockTestCase;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class InsuranceBadanehManagerImplTestOff extends BaseManagerMockTestCase {
	private InsuranceBadanehDao dao;
	private InsuranceBadanehManagerImpl manager;

	@Before
	public void onSetUp() {
		dao = context.mock(InsuranceBadanehDao.class);
		manager = new InsuranceBadanehManagerImpl(dao);
	}

	@Test
	public void testFindIBbyQualityJodaganehSpace() {
		log.debug("testing findIBbyQualityJodaganehSpace......");
		final List<InsuranceBadaneh> ibs = new ArrayList<InsuranceBadaneh>();
		context.checking(new Expectations() {
			{
				one(dao).findIBbyQualityJodaganehSpace(Quality.UP, true, 600L);
				will(returnValue(ibs));
			}
		});
		List<InsuranceBadaneh> result = manager.findISbyQualityJodaganehSpace(Quality.UP, true, 600L);
		assertSame(ibs,result);
	}
}
