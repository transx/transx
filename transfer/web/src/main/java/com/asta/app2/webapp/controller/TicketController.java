package com.asta.app2.webapp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asta.app2.exception.TicketDidNotIssuedException;
import com.asta.app2.exception.TicketIssuedException;
import com.asta.app2.model.Rate;
import com.asta.app2.model.Setting;
import com.asta.app2.model.Ticket;
import com.asta.app2.model.TicketTemp;
import com.asta.app2.model.User;
import com.asta.app2.service.CashManager;
import com.asta.app2.service.RateManager;
import com.asta.app2.service.SettingManager;
import com.asta.app2.service.TicketManager;
import com.asta.app2.service.TicketTempManager;
import com.asta.app2.service.UserManager;
import com.asta.app2.util.BundleUtil;
import com.asta.app2.util.DateUtil;

@Controller
public class TicketController extends BaseController {

	@Autowired private TicketTempManager ticketTempManager;
	@Autowired private UserManager userManager;
	@Autowired private SettingManager settingManager;
	@Autowired private RateManager rateManager;
	@Autowired private CashManager cashManager;
	@Autowired private TicketManager ticketManager;
	@Autowired private ServletContext servletContext;

	@Transactional
	@RequestMapping(value = "/cashier/issue/ticket/{cashId}/{ticketTempId}", method = RequestMethod.GET)
	public String issueTicket(Model model, Principal principal,
			@PathVariable("cashId") Long cashId,
			@PathVariable("ticketTempId") Long ticketTempId,
			HttpServletRequest request, HttpServletResponse response) {
		if (cashId == null || ticketTempId == null) {
			//TODO
		}
		TicketTemp tt = ticketTempManager.get(ticketTempId);
		if (tt.isCommitted()) {
			//TODO
		}
		User cashier = userManager.getUserByUsername(principal.getName());
		Setting setting = settingManager.get(1L);
		Rate rateTemplate = new Rate(setting.getRateType(), tt.getPath(), tt.getService().getCarKind().getQuality());
		List<Rate> rates = rateManager.findRateForTicket(rateTemplate);
		if (rates.size() < 1) {
			//TODO
		}
		Rate rate = rates.get(0);
		Ticket ticket = new Ticket(setting.getTicketNumber(),
				rate.getPrice(), rate.getToll(), rate.getSnack(),
				tt.getCount(), tt.getReserveDate(), new Date(),
				tt.getReserverId(), cashier, tt.getPassenger(),
				tt.getService(), tt.getPath());
		ticket.setChairs(tt.getChairs());
		ticket.setTotal(ticket.getCount()*(ticket.getPrice()+ticket.getSnack()));
		ticket.setCash(cashManager.get(cashId));
		try {
			ticket = ticketManager.saveTicket(cashier.getCompany(), ticket, ticketTempId);
			
			Map parameters = new HashMap();
			parameters.put("TICKET_NUMBER", ticket.getNumber().toString());
			parameters.put("TICKET_CAR_KIND", ticket.getService().getCarKind().toString());
			parameters.put("TICKET_PASSENGER_NAME", ticket.getPassenger().getGender().getLabel()+" "+(ticket.getPassenger().getFirstName()!=null ? ticket.getPassenger().getFirstName(): "")+" "+ticket.getPassenger().getLastName());
			parameters.put("TICKET_CHAIRS", ticket.getChairs().toString());
			parameters.put("TICKET_DATEBOOK", ticket.getService().getDatebookFormatted());
			parameters.put("TICKET_TIMED", ticket.getService().getTimed());
			parameters.put("TICKET_START", BundleUtil.getMessageBundle("soorat.defaultPlace"));
			parameters.put("TICKET_END", ticket.getPath().getEnd().toString());
			parameters.put("TICKET_CAR", ticket.getService().getCar().getPlaqueSerial()+" "+ticket.getService().getCar().getPlaqueNumber());
			parameters.put("TICKET_ISSUE_DATE", ticket.getIssueDateFormatted());
			parameters.put("TICKET_ISSUE_TIME", DateUtil.getHourMinute(ticket.getIssueDate()));
			parameters.put("TICKET_PRICE", ticket.getPrice()+"");
			parameters.put("TICKET_SNACK", ticket.getSnack()+"");
			parameters.put("TICKET_COUNT", ticket.getCount()+"");
			parameters.put("TICKET_TOTAL", ticket.getTotal()+"");
			parameters.put("TICKET_TOTAL_WORD", ticket.getTotal()+"");
			
			InputStream is = servletContext.getResourceAsStream("/WEB-INF/reports/belit.jrxml");
			JasperDesign jasperDesign = JRXmlLoader.load(is);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JREmptyDataSource());
			JRHtmlExporter exporter = new JRHtmlExporter();

			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			exporter.setParameters(parameters);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRHtmlExporterParameter.OUTPUT_WRITER, out);
			exporter.setParameter(JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
			exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exporter.setParameter(JRHtmlExporterParameter.IS_WRAP_BREAK_WORD, Boolean.TRUE);

			request.setAttribute("exportIndetObject", exporter);

		return "cashier/print";
		} catch (TicketIssuedException e) {
			e.printStackTrace(); //TODO
		} catch (TicketDidNotIssuedException e) {
			e.printStackTrace(); //TODO
		} catch (IOException e) {
			e.printStackTrace(); //TODO
		} catch (JRException e) {
			e.printStackTrace(); //TODO
		}
		return "redirect:/cashier/cashes.html";
	}
}
