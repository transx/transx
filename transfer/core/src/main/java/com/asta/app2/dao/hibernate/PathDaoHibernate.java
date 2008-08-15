package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.PathDao;
import com.asta.app2.model.City;
import com.asta.app2.model.Path;

public class PathDaoHibernate extends GenericDaoHibernate<Path, Long> implements
		PathDao {

	public PathDaoHibernate() {
		super(Path.class);
	}

	public List<Path> findByStartCityId(long start_id) {
		return getHibernateTemplate().find("from Path where city_start_id=?",start_id);
	}

	public List<Path> findByEndCityId(long end_id) {
		return getHibernateTemplate().find("from Path where city_end_id=?",end_id);
	}

	public List<Path> getParentPath() {
		return getHibernateTemplate().find("from Path where parent_id is null");
	}

	public List<Path> getSubPath(long id) {
		return getHibernateTemplate().find("from Path where parent_id=?",id);
	}


}
