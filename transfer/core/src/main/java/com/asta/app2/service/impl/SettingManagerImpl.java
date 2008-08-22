/* *Class created on [ Jul 22, 2008 | 11:03:28 PM ] */
package com.asta.app2.service.impl;

import com.asta.app2.dao.SettingDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.Setting;
import com.asta.app2.service.SettingManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SettingManagerImpl extends GenericManagerImpl<Setting, Long>
		implements SettingManager {
	private SettingDao settingDao;

	public SettingManagerImpl(SettingDao settingDao) {
		super(settingDao);
		this.settingDao = settingDao;
	}

	public Setting getSettingByCompany(Company company) {
		return settingDao.getSettingByCompany(company);
	}

}
