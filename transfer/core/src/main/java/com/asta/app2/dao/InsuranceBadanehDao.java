/* *Class created on [ Jul 26, 2008 | 7:08:49 PM ] */
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.InsuranceBadaneh;
import com.asta.app2.model.enums.Quality;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface InsuranceBadanehDao extends GenericDao<InsuranceBadaneh, Long> {
	List<InsuranceBadaneh> findIBbyQualityJodaganehSpace(Quality quality,
			Boolean jodaganeh, Long space);
}
