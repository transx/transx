/* *Class created on [ Jul 26, 2008 | 12:24:31 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.InsuranceSarneshin;
import com.asta.app2.model.enums.Distance;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface InsuranceSarneshinDao extends GenericDao<InsuranceSarneshin, Long>{
	List<InsuranceSarneshin> findISbyDistanceCapacity(Distance distance, Integer capacity);
}


