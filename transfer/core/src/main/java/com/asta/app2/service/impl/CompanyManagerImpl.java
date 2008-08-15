/* *Class created on [ Jun 1, 2008 | 10:28:19 AM ] */
package com.asta.app2.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.asta.app2.dao.CompanyDao;
import com.asta.app2.model.Company;
import com.asta.app2.service.CompanyManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CompanyManagerImpl extends GenericManagerImpl<Company, Long>
		implements CompanyManager {
	private CompanyDao companyDao;

	public CompanyManagerImpl(CompanyDao companyDao) {
		super(companyDao);
		this.companyDao = companyDao;
	}

	public List<Company> findByCode(String code) {
		return companyDao.findByCode(code);
	}

	public Map<String, String> getMap(boolean withEmpty) {
		List<Company> companies = companyDao.getAll();
		Map<String, String> map = new TreeMap<String, String>();
		map.clear();
		if (withEmpty) {
			map.put("", null);
		}
		for (Company company : companies) {
			map.put(company.toString() == null ? "" : company.toString(), company
					.getId().toString());
		}
		return map;
	}

}
