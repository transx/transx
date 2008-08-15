package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.City;
import com.asta.app2.model.Path;

public interface PathDao extends GenericDao<Path, Long>{
	List<Path> findByStartCityId(long start_id);
	List<Path> findByEndCityId(long end_id);

	List<Path> getParentPath();
	List<Path> getSubPath(long id);

}
