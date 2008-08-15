package com.asta.app2.service;

import java.util.List;
import java.util.Map;

import com.asta.app2.model.Path;

public interface PathManager extends GenericManager<Path, Long>{
	List<Path> findByStartCityId(long start_id);
	List<Path> findByEndCityId(long end_id);
	Map<String, String> getPathMap(boolean withEmpty);
	Map<String, String> getParentPathMap(boolean withEmpty);
	Map<String, String> getSubPathMap(long id);
	
}
