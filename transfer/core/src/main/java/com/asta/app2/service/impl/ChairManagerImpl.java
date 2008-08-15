/* *Class created on [ Jun 14, 2008 | 5:41:34 PM ] */
package com.asta.app2.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.asta.app2.dao.ChairDao;
import com.asta.app2.model.Chair;
import com.asta.app2.service.ChairManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ChairManagerImpl extends GenericManagerImpl<Chair, Long> implements
		ChairManager {
	private ChairDao chairDao;

	public ChairManagerImpl(ChairDao chairDao) {
		super(chairDao);
		this.chairDao = chairDao;
	}

	public List<Chair> findChairsByServiceId(long service_id) {
		return chairDao.findChairsByServiceId(service_id);
	}

	/**
	 * 
	 * @param capecity determine to have how many chairs to be loaded !
	 * @return map of chair ids 
	 */
	public Map<String, String> getMap(int capacity) {
		
		List<Chair> chairs = chairDao.getAllSorted(capacity);
		Map<String, String> map = new TreeMap<String, String>();
		for (Chair chair : chairs) {
			map.put(chair.getId().toString(), chair.getId().toString());
		}
		return map;
	}

	public List<Chair> getAllSorted(int lastId) {
		return chairDao.getAllSorted(lastId);
	}

}
