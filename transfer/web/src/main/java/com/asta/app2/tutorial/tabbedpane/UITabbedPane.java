/* *Class created on [ Jun 17, 2008 | 11:05:27 AM ] */
package com.asta.app2.tutorial.tabbedpane;

import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class UITabbedPane extends UICommand {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Comment out these two methods to see what happens when a component does
	 * not properly save its state.
	 */
	@Override
	public Object saveState(FacesContext context) {
		Object values[] = new Object[3];
		values[0] = super.saveState(context);
		values[1] = content;
		return values;
	}
	
	@Override
	public void restoreState(FacesContext facesContext, Object state) {
		Object values[] = (Object[]) state;
		super.restoreState(facesContext, values[0]);
		content = (String) values[1];
	}
}
