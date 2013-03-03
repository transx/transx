package com.asta.app2.webapp.controller;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asta.app2.service.UserManager;

/**
 * Report controller is a general purpose report controller
 */
@Controller
public class ReportController extends BaseController {

	@Autowired
	private UserManager userManager;
	
	@RequestMapping(value = "/admin/users/pdf", method = RequestMethod.GET)
	public String reportUserList (ModelMap modelMap) {
		JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(userManager.getUsers(null));
		modelMap.put(REPORT_DATA, jrDataSource);
		modelMap.put(REPORT_FORMAT, "pdf");
		modelMap.put(REPORT_FILE_NAME, "User-List");
		return "userListReport";
	}
	
}
