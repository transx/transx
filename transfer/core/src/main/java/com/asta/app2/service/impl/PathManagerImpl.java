package com.asta.app2.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.asta.app2.Constants;
import com.asta.app2.dao.PathDao;
import com.asta.app2.model.Path;
import com.asta.app2.service.CityManager;
import com.asta.app2.service.PathManager;

public class PathManagerImpl extends GenericManagerImpl<Path, Long> implements
		PathManager {
	private PathDao pathDao;
	private CityManager cityManager;
	
	public PathManagerImpl(PathDao pathDao) {
		super(pathDao);
		this.pathDao = pathDao;
	}

	public List<Path> findByStartCityId(long start_id) {
		return pathDao.findByStartCityId(start_id);
	}

	public Map<String, String> getPathMap(boolean withEmpty) {
		List<Path> paths = pathDao.getAll();
		Map<String, String> map = new TreeMap<String, String>();
		map.clear();
		if (withEmpty) {
			map.put("- - - - - - - - - -", Constants.EMPTY);
		}
		for (Path path : paths) {
			map.put(path.toString() == null ? "" : path.toString(), path
					.getId().toString());
		}
		return map;
	}

	public Map<String, String> getParentPathMap(boolean withEmpty) {
		List<Path> paths = pathDao.getParentPath();
		Map<String, String> map = new TreeMap<String, String>();
		map.clear();
		if (withEmpty) {
			map.put("- - - - - - - - - -", Constants.EMPTY);
		}
		for (Path path : paths) {
			map.put(path.toString() == null ? "" : path.toString(), path
					.getId().toString());
		}
		return map;
	}

	public Map<String, String> getSubPathMap(long id) {
		List<Path> paths = pathDao.getSubPath(id);
		Map<String, String> map = new TreeMap<String, String>();
		map.clear();
		for (Path path : paths) {
			map.put(path == null ? "noWhere!" : path.getEnd().getName(), path
					.getId().toString());
		}
		return map;
	}

	public List<Path> findByEndCityId(long end_id) {
		return pathDao.findByEndCityId(end_id);
	}

	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}

	public Map<Object, Object> getPathParentItems(boolean withEmpty) {
		List<Path> paths = pathDao.getParentPath();
		Map<Object, Object> map = new TreeMap<Object, Object>();
		if (withEmpty) {
			map.put("- - - - - - - - - -", Constants.EMPTY);
		}
		for (Path path : paths) {
			map.put(path, path);
		}
		return map;
	}

}
