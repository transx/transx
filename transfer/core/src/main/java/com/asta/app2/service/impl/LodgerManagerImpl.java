/* *Class created on [ Jun 4, 2008 | 9:57:57 PM ] */
package com.asta.app2.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.asta.app2.dao.LodgerDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.Lodger;
import com.asta.app2.service.LodgerManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class LodgerManagerImpl extends GenericManagerImpl<Lodger, Long>
		implements LodgerManager {
	private LodgerDao lodgerDao;

	public LodgerManagerImpl(LodgerDao lodgerDao) {
		super(lodgerDao);
		this.lodgerDao = lodgerDao;
	}

	public List<Lodger> findLodgerByPresenter(Company company,String presenter) {
		return lodgerDao.findLodgerByPresenter(company,presenter);
	}

	public Map<String, String> getMap(Company company,boolean withEmpty) {
		List<Lodger> lodgers = lodgerDao.getAllLodgerByCompany(company);
		Map<String, String> map = new TreeMap<String, String>();
		map.clear();
		if (withEmpty)
			map.put("", "");
		for (Lodger lodger : lodgers) {
			map.put(lodger.toString() == null ? "" : lodger.toString(), lodger
					.getId().toString());
		}

		return map;
	}

}
