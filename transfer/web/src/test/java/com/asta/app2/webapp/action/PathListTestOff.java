package com.asta.app2.webapp.action;

import com.asta.app2.service.PathManager;

public class PathListTestOff extends BasePageTestCase {
    private PathList bean;
    private PathManager pathManager;

    public void setPathManager(PathManager pathManager) {
        this.pathManager = pathManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new PathList();
        bean.setPathManager(pathManager);
        
        // add a test path to the database
//        Path path = new Path();

        // enter all required fields

//        pathManager.save(path);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getPaths().size() >= 1);
		assertFalse(bean.hasErrors());
    }
}