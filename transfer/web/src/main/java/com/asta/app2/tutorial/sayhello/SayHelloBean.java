/* *Class created on [ Jun 19, 2008 | 2:14:05 PM ] */ 
package com.asta.app2.tutorial.sayhello;

import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SayHelloBean {
	
    protected final Log log = LogFactory.getLog(SayHelloBean.class);
	
	private String firstName;
	private String lastName;
	private boolean renderGreeting;
	
	public SayHelloBean() {
		this.renderGreeting = false;
	}
	
	public void sayIt(ActionEvent evt){
		renderGreeting = true;
		if (log.isInfoEnabled())
			log.info("The name to say hello is '"+firstName+"' and the last name is '"+lastName+"'.");
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isRenderGreeting() {
		return renderGreeting;
	}

	public void setRenderGreeting(boolean renderGreeting) {
		this.renderGreeting = renderGreeting;
	}
}


