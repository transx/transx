/* *Class created on [ Jun 19, 2008 | 12:03:15 PM ] */
package com.asta.app2.tutorial.sayhello;

import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SayHello extends UIOutput {
	public static final String COMPONENT_TYPE = "com.asta.app2.tutorial.SayHello";
	public static final String DEFAULT_RENDERER_TYPE = "com.asta.app2.tutorial.SayHelloRenderer";
	public static final String COMPONENT_FAMILY = "javax.faces.Output";

	private String firstName;
	private String lastName;

	public SayHello() {
	}
	
	public String getFamily(){
		return COMPONENT_FAMILY;
	}
	
	@Override
	public void restoreState(FacesContext facesContext, Object state) {
		Object values[] =(Object[]) state;
		super.restoreState(facesContext, values[0]);
		this.firstName = (String) values[1];
		this.lastName = (String) values[2];
	}
	
	@Override
	public Object saveState(FacesContext facesContext) {
		Object values[] = new Object[3];
		values[0] = super.saveState(facesContext);
		values[1] = firstName;
		values[2] = lastName;
		return values;
	}

	public String getFirstName() {
		if (firstName != null)
			return firstName;
		
		ValueBinding vb = getValueBinding("firstName");
		return (vb == null)? null :(String) vb.getValue(FacesContext.getCurrentInstance());
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		if (lastName != null)
			return lastName;
		
		ValueBinding vb = getValueBinding("lastName");
		return (vb == null)? null :(String) vb.getValue(FacesContext.getCurrentInstance());
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
