/* *Class created on [ Jun 1, 2008 | 6:42:08 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.CarKind;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface CarKindDao extends GenericDao<CarKind, Long>{
	List<CarKind> findByName(String name);

	CarKind load(CarKind carKind);
}


