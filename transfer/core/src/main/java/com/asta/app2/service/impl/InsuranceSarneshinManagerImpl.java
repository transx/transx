/* *Class created on [ Jul 26, 2008 | 3:49:12 PM ] */
package com.asta.app2.service.impl;

import java.util.List;

import com.asta.app2.dao.InsuranceSarneshinDao;
import com.asta.app2.model.InsuranceSarneshin;
import com.asta.app2.model.enums.Distance;
import com.asta.app2.service.InsuranceSarneshinManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class InsuranceSarneshinManagerImpl extends
		GenericManagerImpl<InsuranceSarneshin, Long> implements
		InsuranceSarneshinManager {
	private InsuranceSarneshinDao insuranceSarneshinDao;

	public InsuranceSarneshinManagerImpl(
			InsuranceSarneshinDao insuranceSarneshinDao) {
		super(insuranceSarneshinDao);
		this.insuranceSarneshinDao = insuranceSarneshinDao;
	}

	public List<InsuranceSarneshin> findISbyDistanceCapacity(Distance distance,
			Integer capacity) {
		return insuranceSarneshinDao.findISbyDistanceCapacity(distance,
				capacity);
	}

}
