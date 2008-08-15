/* *Class created on [ Jul 22, 2008 | 10:54:01 PM ] */ 
package com.asta.app2.dao;

import com.asta.app2.model.Setting;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SettingDaoTestOff extends BaseDaoTestCase{
	SettingDao settingDao;
	
	public void testGetSetting(){
		log.debug("testing getSetting......");
		Setting setting =  settingDao.get(1L);
		assertNotNull(setting);
	}

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
	}
}


