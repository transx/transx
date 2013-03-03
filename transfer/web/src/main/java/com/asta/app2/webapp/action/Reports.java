/* *Class created on [ Aug 2, 2008 | 9:10:22 AM ] */
package com.asta.app2.webapp.action;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asta.app2.model.Ticket;
import com.asta.app2.service.TicketManager;
import com.asta.app2.service.UserManager;
import com.asta.app2.util.BundleUtil;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class Reports extends BasePage implements Serializable {

	private String testString;
	private UserManager userManager;
	private TicketManager ticketManager;
	
	public void setTicketManager(TicketManager ticketManager) {
		this.ticketManager = ticketManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public String getTestString() {
		if (testString == null)
			testString = "HelloWorld";
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}

	public String hello() {
		List results = userManager.getUsers(null);
		
		Map parameters = new HashMap();
		parameters.put("forrmat", "pdf");
		parameters.put("WEBDIR", getServletContext().getRealPath("/"));
		try{
			InputStream is = getServletContext().getResourceAsStream("/WEB-INF/reports/userList.jrxml");
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(results);
			getResponse().setContentType("application/pdf");
			getResponse().addHeader("Content-Disposition", "attachment; filename=hello.pdf");
			JasperDesign jasperDesign = JRXmlLoader.load(is);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,ds);
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, getResponse().getOutputStream());
			
			getFacesContext().getApplication().getStateManager().saveSerializedView(getFacesContext());
			getFacesContext().responseComplete();
			
		}catch(Exception e){
			log.debug(e);
		}
		
		return null;
	}
	
	// the below lines is about print directly without preview.
	/* getReportViewer(jasperPrint, reportTitle); */
	// JasperPrintManager jasperPrintManager = new JasperPrintManager();
	// jasperPrintManager.printReport(jasperPrint,true);
	
	public String helloPrint() {
		List results = userManager.getUsers(null);
		
		Map parameters = new HashMap();
		parameters.put("forrmat", "pdf");
		parameters.put("WEBDIR", getServletContext().getRealPath("/"));
		try{
			InputStream is = getServletContext().getResourceAsStream("/WEB-INF/reports/userList.jrxml");
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(results);
//			getResponse().setContentType("application/pdf");
//			getResponse().addHeader("Content-Disposition", "attachment; filename=hello.pdf");
			JasperDesign jasperDesign = JRXmlLoader.load(is);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,ds);
			
//			JasperPrintManager jasperPrintManager = new JasperPrintManager();
//			jasperPrintManager.printReport(jasperPrint, false);
			
		}catch(Exception e){
			log.debug(e);
		}
		
		return null;
	}

	public String helloTikcet() {
		Ticket  ticket = ticketManager.get(-1L);
		
		Map parameters = new HashMap();

		parameters.put("TICKET_NUMBER", "101214");
		parameters.put("TICKET_CAR_KIND", ticket.getService().getCarKind().toString());
		parameters.put("TICKET_PASSENGER_NAME", ticket.getPassenger().getGender().getLabel()+" "+ticket.getPassenger().getFirstName()+" "+ticket.getPassenger().getLastName());
		parameters.put("TICKET_CAHIRS", ticket.getChairs().toString());
		parameters.put("TICKET_DATEBOOK", ticket.getService().getDatebookFormatted());
		parameters.put("TICKET_TIMED", "00:00");
		parameters.put("TICKET_START", BundleUtil.getMessageBundle("soorat.defaultPlace"));
		parameters.put("TICKET_END", ticket.getPath().getEnd().toString());
		parameters.put("TICKET_PRICE_WORD", ticket.getPrice().toString());
		parameters.put("TICKET_CAR", ticket.getService().getCar().getPlaqueSerial()+" "+ticket.getService().getCar().getPlaqueNumber());
		parameters.put("TICKET_ISSUE_DATE", ticket.getIssueDateFormatted());
		parameters.put("TICKET_ISSUE_TIME", "12:00");
		parameters.put("TICKET_PRICE", ticket.getPrice().toString());
		parameters.put("TICKET_SNACK", "1900");
		int count = ticket.getChairs().size();
		parameters.put("TICKET_COUNT", "2");
		parameters.put("TICKET_TOTAL", "1000 toman!");

		try{
			InputStream is = getServletContext().getResourceAsStream("/WEB-INF/reports/belit.jrxml");
			JasperDesign jasperDesign = JRXmlLoader.load(is);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			
//			JasperPrintManager jasperPrintManager = new JasperPrintManager();
//			jasperPrintManager.printReport(jasperPrint, true);
			
		}catch(Exception e){
			log.debug(e);
		}
		
		return null;
	}
}
