package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.Map;

import com.asta.app2.model.Path;
import com.asta.app2.service.CityManager;
import com.asta.app2.service.PathManager;
import com.asta.app2.util.BundleUtil;

public class PathForm extends BasePage implements Serializable {
	private PathManager pathManager;
	private Path path = new Path();
	private Long id;
	private CityManager cityManager;
	private String parentID;
	private String startID;
	private String endID;
	private Map<String, String> cityMap;
	private Map<String, String> pathParentMap;
	
	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}

	public void setPathManager(PathManager pathManager) {
		this.pathManager = pathManager;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String delete() {
		pathManager.remove(path.getId());
		addMessage("path.deleted");

		return "list";
	}

	public String edit() {
		if (id != null) {
			path = pathManager.get(id);
			setStartID(path.getStart().getId().toString());
			setEndID(path.getEnd().getId().toString());
			if (path.getParent() != null){
				setParentID(path.getParent().getId().toString());
			}
		} else {
			path = new Path();
		}

		return "edit";
	}

	public String save() {
		boolean isNew = (path.getId() == null);
		path.setStart(cityManager.get(Long.valueOf(getStartID()).longValue()));
		path.setEnd(cityManager.get(Long.valueOf(getEndID()).longValue()));
		if (getParentID() != null){
			path.setParent(pathManager.get(Long.valueOf(getParentID()).longValue()));
		}
		pathManager.save(path);

		String key = (isNew) ? "path.added" : "path.updated";
		addMessage(key);

		if (isNew) {
			return "list";
		} else {
			return "edit";
		}
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getStartID() {
		if (startID == null){
			startID = cityManager.findByName(BundleUtil.getMessageBundle("default.path.city.start")).get(0).getId().toString();
		}
		return startID;
	}

	public void setStartID(String startID) {
		this.startID = startID;
	}

	public String getEndID() {
		return endID;
	}

	public void setEndID(String endID) {
		this.endID = endID;

	}
	
	public Map<String, String> getCityMap() {
		if(cityMap == null){
			cityMap = cityManager.getCityMap(false);
		}
		return cityMap;
	}
	
	public Map<String,String> getParentPathMap(){
		if(pathParentMap == null){
			pathParentMap = pathManager.getParentPathMap(true);
		}
		return pathParentMap;
	}


}