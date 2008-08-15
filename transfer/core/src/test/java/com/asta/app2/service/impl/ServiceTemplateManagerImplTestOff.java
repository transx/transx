/* *Class created on [ Jun 2, 2008 | 10:24:23 PM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.ServiceTemplateDao;
import com.asta.app2.model.ServiceTemplate;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ServiceTemplateManagerImplTestOff extends BaseManagerMockTestCase {
	private ServiceTemplateManagerImpl manager;
	private ServiceTemplateDao dao;

	@Before
	public void setUp() {
		dao = context.mock(ServiceTemplateDao.class);
		manager = new ServiceTemplateManagerImpl(dao);
	}

	@After
	public void tearDown() {
		manager = null;
	}

	@Test
	public void testFindServiceTemplateByPath() {
		log.debug("testing findServiceTemplateByPath");
		final List<ServiceTemplate> sts = new ArrayList<ServiceTemplate>();
		context.checking(new Expectations() {
			{
				one(dao).findServiceTemplateByPath(-1L);
				will(returnValue(sts));
			}
		});
		List<ServiceTemplate> result = manager.findServiceTemplateByPath(-1L);
		assertSame(sts, result);
	}
}
