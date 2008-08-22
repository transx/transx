/* *Class created on [ Jul 15, 2008 | 7:20:38 PM ] */
package com.asta.app2.service.impl;

import java.util.List;

import com.asta.app2.dao.SooratDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.Service;
import com.asta.app2.model.Soorat;
import com.asta.app2.service.SooratManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SooratManagerImpl extends GenericManagerImpl<Soorat, Long> implements SooratManager{
	SooratDao sooratDao;
	
	public SooratManagerImpl(SooratDao sooratDao) {
		super(sooratDao);
		this.sooratDao = sooratDao;
	}

	public List<Soorat> findSooratsByService(Service service) {
		return sooratDao.findSooratsByService(service);
	}

	public List<Soorat> getAllSoorat(Company company) {
		return sooratDao.getAllSoorat(company);
	}

	public List<Soorat> getAllSooratActive(Company company) {
		return sooratDao.getAllSooratActive(company);
	}

}
