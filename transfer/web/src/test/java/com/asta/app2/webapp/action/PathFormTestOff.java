package com.asta.app2.webapp.action;

import com.asta.app2.model.Path;
import com.asta.app2.service.CityManager;
import com.asta.app2.service.PathManager;

public class PathFormTestOff extends BasePageTestCase {
	private PathForm bean;
	private PathManager pathManager;
	private CityManager cityManager;

	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}

	public void setPathManager(PathManager pathManager) {
		this.pathManager = pathManager;
	}

	@Override
	protected void onSetUp() throws Exception {
		super.onSetUp();
		bean = new PathForm();
		bean.setPathManager(pathManager);
		bean.setCityManager(cityManager);
	}

	@Override
	protected void onTearDown() throws Exception {
		super.onTearDown();
		bean = null;
	}

/*	public void testAdd() throws Exception {
		Path path = new Path();

		// enter all required fields
		bean.setPath(path);

		assertEquals("list", bean.save());
		assertFalse(bean.hasErrors());
	}

	public void testEdit() throws Exception {
		log.debug("testing edit...");
		bean.setId(-1L);

		assertEquals("edit", bean.edit());
		assertNotNull(bean.getPath());
		assertFalse(bean.hasErrors());
	}

	public void testSave() {
		log.debug("testing save...");
		bean.setId(-1L);

		assertEquals("edit", bean.edit());
		assertNotNull(bean.getPath());
		Path path = bean.getPath(); // update required fields
		// bean.setPath(path);

		assertEquals("edit", bean.save());
		assertFalse(bean.hasErrors());
	}*/

	public void testRemove() throws Exception {
		log.debug("testing remove...");
		Path path = new Path();
		path.setId(-2L);
		bean.setPath(path);

		assertEquals("list", bean.delete());
		assertFalse(bean.hasErrors());
	}
	
	public void testGetCityMap() throws Exception{
		log.debug("testing getCityMap......");
		assertTrue(cityManager.getCityMap(false).size() >=1);
		//assertFalse(bean.hasErrors());
	}
}