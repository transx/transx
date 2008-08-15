/* *Class created on [ Jul 28, 2008 | 8:37:48 PM ] */ 
package com.asta.app2.service.impl;

import java.util.List;

import com.asta.app2.dao.CashDao;
import com.asta.app2.model.Cash;
import com.asta.app2.model.Company;
import com.asta.app2.service.CashManager;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CashManagerImpl extends GenericManagerImpl<Cash, Long> implements CashManager{
	private CashDao cashDao;
	public CashManagerImpl(CashDao cashDao) {
		super(cashDao);
		this.cashDao = cashDao;
	}
	
	public List<Cash> getAllCash(Company company) {
		return cashDao.getAllCash(company);
	}
	public List<Cash> getAllEnabledCash(Company company) {
		return cashDao.getAllEnabledCash(company);
	}
	public List<Cash> getAllEnabledCashNotExpired(Company company) {
		return cashDao.getAllEnabledCashNotExpired(company);
	}

}


