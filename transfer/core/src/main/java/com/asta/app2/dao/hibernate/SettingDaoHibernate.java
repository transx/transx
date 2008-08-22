/* *Class created on [ Jul 22, 2008 | 10:58:54 PM ] */
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.SettingDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.Setting;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SettingDaoHibernate extends GenericDaoHibernate<Setting, Long>
		implements SettingDao {

	public SettingDaoHibernate() {
		super(Setting.class);
	}

	public Setting getSettingByCompany(Company company) {
		List<Setting> settings = getHibernateTemplate().find("from Setting where company=?",company);
		return settings.get(0);
	}

}
