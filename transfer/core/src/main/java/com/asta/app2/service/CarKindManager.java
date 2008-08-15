/* *Class created on [ Jun 1, 2008 | 7:51:32 PM ] */ 
package com.asta.app2.service;

import java.util.List;
import java.util.Map;

import com.asta.app2.model.CarKind;
import com.asta.app2.model.LabelValue;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface CarKindManager extends GenericManager<CarKind, Long>{
	List<CarKind> findByName(String name);

	Map<String, String> getMap(boolean withEmpty);

	CarKind load(CarKind carKind);

	List<LabelValue> getAllCarKindLableValue();
}


