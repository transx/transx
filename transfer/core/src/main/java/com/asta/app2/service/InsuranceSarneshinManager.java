/* *Class created on [ Jul 26, 2008 | 3:48:05 PM ] */ 
package com.asta.app2.service;

import java.util.List;

import com.asta.app2.model.InsuranceSarneshin;
import com.asta.app2.model.enums.Distance;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface InsuranceSarneshinManager extends GenericManager<InsuranceSarneshin, Long>{
	List<InsuranceSarneshin> findISbyDistanceCapacity(Distance distance, Integer capacity);
}


