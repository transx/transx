/* *Class created on [ Jun 1, 2008 | 7:51:17 PM ] */
package com.asta.app2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.asta.app2.dao.CarKindDao;
import com.asta.app2.model.CarKind;
import com.asta.app2.model.LabelValue;
import com.asta.app2.service.CarKindManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CarKindManagerImpl extends GenericManagerImpl<CarKind, Long>
		implements CarKindManager {
	private CarKindDao carKindDao;

	public CarKindManagerImpl(CarKindDao carKindDao) {
		super(carKindDao);
		this.carKindDao = carKindDao;
	}

	public List<CarKind> findByName(String name) {
		return carKindDao.findByName(name);
	}

	public Map<String, String> getMap(boolean withEmpty) {
		List<CarKind> carKinds = carKindDao.getAll();
		Map<String, String> map = new TreeMap<String, String>();
		map.clear();
		if (withEmpty) {
			map.put("", null);
		}
		for (CarKind carKind : carKinds) {
			map.put(carKind.toString() == null ? "" : carKind.toString(),
					carKind.getId().toString());
		}
		return map;
	}

	public CarKind load(CarKind carKind) {
		return carKindDao.load(carKind);
	}

	public List<LabelValue> getAllCarKindLableValue() {
		List<CarKind> carKinds = carKindDao.getAll();
		List<LabelValue> list = new ArrayList<LabelValue>();

		for (CarKind ck : carKinds) {
			list.add(new LabelValue(ck.toString(), ck.getId().toString()));
		}

		return list;
	}
}
