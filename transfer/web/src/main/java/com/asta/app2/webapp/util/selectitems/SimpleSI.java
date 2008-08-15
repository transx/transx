package com.asta.app2.webapp.util.selectitems;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SimpleSI<T> extends BaseSI implements SimpleSIIntf {
	private Map<String, String> selectItems;
	private List<T> allObjects;

	public Map<String, String> getSelectItems() {
		if (selectItems == null) {
			allObjects = getAllObjects();
			selectItems = new TreeMap<String, String>();
			fillMaps();
		}
		return selectItems;
	}

	private void fillMaps() {
		selectItems.clear();
		// model.put("(Empty)", "0"); // Add EMPTY element if needed
		for (T o : allObjects) {
			// selectItems.put(o.toString() == null ? "" : o.toString(),
			// o.getId().toString());
		}
	}

	public void setAllObjects(List<T> allObjects) {
		this.allObjects = allObjects;
	}

	public List<T> getAllObjects() {
		return allObjects;
	}

}
