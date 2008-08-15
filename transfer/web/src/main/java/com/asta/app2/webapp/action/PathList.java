package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.service.PathManager;

public class PathList extends BasePage implements Serializable {
    private PathManager pathManager;

    public void setPathManager(PathManager pathManager) {
        this.pathManager = pathManager;
    }

    public PathList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getPaths() {
        return sort(pathManager.getAll());
    }
}

