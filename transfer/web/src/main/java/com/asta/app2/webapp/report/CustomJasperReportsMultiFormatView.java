package com.asta.app2.webapp.report;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

import com.asta.app2.webapp.controller.BaseController;

/**
 * This class replace report name and wanted rendering formats(pdf, cvs, xls, html) in runtime.
 *
 */
public class CustomJasperReportsMultiFormatView extends JasperReportsMultiFormatView {

	@Override
	protected void renderReport(JasperPrint populatedReport,
			Map<String, Object> model, HttpServletResponse response) throws Exception {
		setReportDataKey(BaseController.REPORT_DATA);

		String currentReportName = populatedReport.getName();
		Object specifiedReportName = model.get(BaseController.REPORT_FILE_NAME);
		String reportName = ( specifiedReportName == null ? currentReportName : (String) specifiedReportName);
		String format = model.get(BaseController.REPORT_FORMAT).toString();

		response.addHeader("Content-Disposition", "attachment; filename=\"" + reportName + "." + format + "\"");
		super.renderReport(populatedReport, model, response);
	}

}
