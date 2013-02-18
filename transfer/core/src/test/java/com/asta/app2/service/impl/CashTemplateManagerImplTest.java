/* *Class created on [ Oct 14, 2008 | 8:16:27 PM ] */ 
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.CashTemplateDao;
import com.asta.app2.model.CashTemplate;
import com.asta.app2.model.Company;
import com.asta.app2.service.BaseManagerMockTestCase;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CashTemplateManagerImplTest extends BaseManagerMockTestCase{

	private CashTemplateDao dao;
	private CashTemplateManagerImpl manager;
	
	
	@Before
	public void onSetUp(){
		dao = context.mock(CashTemplateDao.class);
		manager = new CashTemplateManagerImpl(dao);
	}
	
	@Test
	public void testSearch(){
		log.debug("test search on CashTemplateManagerImpl");
		final CashTemplate ct = new CashTemplate();
		final List<CashTemplate> cts = new ArrayList<CashTemplate>();
		ct.setCompany(new Company(-1l));
		ct.setEnabled(Boolean.TRUE);
		context.checking(new Expectations(){
			{
				one(dao).searchByExample(ct);
				will(returnValue(cts));
			}
		});
		List<CashTemplate> result = manager.searchByExample(ct);
		log.debug("size of cashTemplateList:"+cts.size());
		assertSame(cts,result);
	}
}


