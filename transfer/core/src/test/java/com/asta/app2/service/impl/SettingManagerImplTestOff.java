/* *Class created on [ Jul 22, 2008 | 11:02:01 PM ] */
package com.asta.app2.service.impl;

import static org.junit.Assert.assertSame;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.asta.app2.dao.SettingDao;
import com.asta.app2.model.Setting;
import com.asta.app2.service.BaseManagerMockTestCase;
import com.asta.app2.service.SettingManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SettingManagerImplTestOff extends BaseManagerMockTestCase {
	private SettingDao dao;
	private SettingManager manager;

	@Before
	public void onSetUp() {
		dao = context.mock(SettingDao.class);
		manager = new SettingManagerImpl(dao);
	}

	@Test
	public void testGetSetting() {
		log.debug("testing getSetting......");
		final Setting setting = new Setting();
		context.checking(new Expectations() {
			{
				one(dao).get(1L);
				will(returnValue(setting));
			}
		});
		Setting result = manager.get(1L);
		assertSame(setting,result);
	}

}
