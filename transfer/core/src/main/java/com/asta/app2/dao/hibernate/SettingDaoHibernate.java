/* *Class created on [ Jul 22, 2008 | 10:58:54 PM ] */
package com.asta.app2.dao.hibernate;

import com.asta.app2.dao.SettingDao;
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

}
