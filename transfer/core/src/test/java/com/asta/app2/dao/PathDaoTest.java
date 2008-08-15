package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Path;

public class PathDaoTest extends BaseDaoTestCase {
	private PathDao pathDao = null;

	public void setPathDao(PathDao pathDao) {
		this.pathDao = pathDao;
	}

	public void testFindByStartCityId() {
		log.debug("testing findByStartCityId......");
		List<Path> paths = pathDao.findByStartCityId(-1L);
		assertTrue(paths.size() > 0);
	}
	
	public void testFindByEndCityId(){
		log.debug("testing findByEndCityId");
		List<Path> paths = pathDao.findByEndCityId(-2L);
		assertTrue(paths.size() > 0);
		log.debug("size of paths = "+paths.size());
	}

}
