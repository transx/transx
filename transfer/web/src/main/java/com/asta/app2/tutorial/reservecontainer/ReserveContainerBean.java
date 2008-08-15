/* *Class created on [ Jun 24, 2008 | 4:44:26 PM ] */ 
package com.asta.app2.tutorial.reservecontainer;

import com.asta.app2.webapp.action.BasePage;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ReserveContainerBean extends BasePage {
	private String chairCode = "0";
	private String chairDisabled = "true";
	
	public String changeChair() {
		return null;
	}

	public String getChairCode() {
		return chairCode;
	}

	public String getChairDisabled() {
		return chairDisabled;
	}

	public void setChairCode(String chairCode) {
		this.chairCode = chairCode;
	}

	public void setChairDisabled(String chairDisabled) {
		this.chairDisabled = chairDisabled;
	}
	
	
}


