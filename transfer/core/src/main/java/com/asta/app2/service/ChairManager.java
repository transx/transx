/* *Class created on [ Jun 14, 2008 | 5:40:25 PM ] */
package com.asta.app2.service;

import java.util.List;
import java.util.Map;

import com.asta.app2.model.Chair;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface ChairManager extends GenericManager<Chair, Long> {

	List<Chair> findChairsByServiceId(long service_id);
	Map<String, String> getMap(int capacity);
	List<Chair> getAllSorted(int lastId);
}
