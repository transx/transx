/* *Class created on [ Oct 12, 2008 | 11:57:29 AM ] */ 
package com.asta.app2.webapp.action;

import javax.faces.model.SelectItem;

import com.asta.app2.model.enums.TicketTempType;

	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class LookupSessionProvider {

	private static SelectItem[] ticketTempTypeItems = {
		new SelectItem(TicketTempType.SELL, TicketTempType.SELL.getLabel()),
		new SelectItem(TicketTempType.RESERVE, TicketTempType.RESERVE.getLabel()) };

	public SelectItem[] getTicketTempTypeItems() {
		return ticketTempTypeItems;
	}
	
}


