/* *Class created on [ Jul 26, 2008 | 11:06:40 PM ] */
package com.asta.app2.service.impl;

import java.util.List;

import com.asta.app2.dao.InsuranceBadanehDao;
import com.asta.app2.model.InsuranceBadaneh;
import com.asta.app2.model.enums.Quality;
import com.asta.app2.service.InsuranceBadanehManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class InsuranceBadanehManagerImpl extends
		GenericManagerImpl<InsuranceBadaneh, Long> implements
		InsuranceBadanehManager {
	private InsuranceBadanehDao insuranceBadanehDao;

	public InsuranceBadanehManagerImpl(InsuranceBadanehDao insuranceBadanehDao) {
		super(insuranceBadanehDao);
		this.insuranceBadanehDao = insuranceBadanehDao;
	}

	public List<InsuranceBadaneh> findISbyQualityJodaganehSpace(
			Quality quality, Boolean jodaganeh, Long space) {
		return insuranceBadanehDao.findIBbyQualityJodaganehSpace(quality,
				jodaganeh, space);
	}

}
