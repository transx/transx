package com.asta.app2.webapp.util.selectitems;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface SimpleSIIntf extends Serializable {

	public Map<String, String> getSelectItems();

	public List getAllObjects();

}
