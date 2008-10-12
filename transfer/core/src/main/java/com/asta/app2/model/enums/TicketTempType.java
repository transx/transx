/* *Class created on [ Oct 12, 2008 | 11:12:33 AM ] */ 
package com.asta.app2.model.enums;

import com.asta.app2.util.BundleUtil;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public enum TicketTempType {
	RESERVE,SELL;
	
	public String getLabel(){
		switch(this){
			case RESERVE:
				return BundleUtil.getMessageBundle("ticketTempType.reserve");
			case SELL:
				return BundleUtil.getMessageBundle("ticketTempType.sell");
		}
		return "Unknown";
	}
}


