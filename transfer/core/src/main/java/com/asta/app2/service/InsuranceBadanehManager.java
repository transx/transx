/* *Class created on [ Jul 26, 2008 | 11:04:31 PM ] */ 
package com.asta.app2.service;

import java.util.List;

import com.asta.app2.model.InsuranceBadaneh;
import com.asta.app2.model.enums.Quality;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface InsuranceBadanehManager extends GenericManager<InsuranceBadaneh, Long>{
	List<InsuranceBadaneh> findISbyQualityJodaganehSpace(Quality quality,Boolean jodaganeh,Long space);
}


