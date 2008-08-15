/* *Class created on [ Jun 14, 2008 | 5:19:53 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Chair;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface ChairDao extends GenericDao<Chair, Long>{
	List<Chair> findChairsByServiceId(long service_id);
	List<Chair> getAllSorted(int lastIs);
	
}


