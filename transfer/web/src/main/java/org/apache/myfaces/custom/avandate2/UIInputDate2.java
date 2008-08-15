/* *Class created on [ Jul 31, 2008 | 7:20:01 PM ] */
package org.apache.myfaces.custom.avandate2;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import org.apache.myfaces.component.html.ext.HtmlInputText;
import org.apache.myfaces.shared_tomahawk.util._ComponentUtils;

/**
 * the original class of this component exist on ir.asta.core(org.apache.myfaces.custom.avandate2.InputDate2) and is
 * base on JSP tag. the only task here is customize this component for using by
 * Facelet.
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class UIInputDate2 extends HtmlInputText {
	private String type;
	private Boolean enableSwitch;

	public UIInputDate2() {
		super();
		this.type = null;
	}

	public String getType() {
		if (type != null)
			return type;
		ValueBinding vb = getValueBinding("type");
		return vb !=null ? _ComponentUtils.getStringValue(getFacesContext(), vb) : null;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getEnableSwitch() {
		if (enableSwitch != null)
		return enableSwitch;
		
		ValueBinding vb = getValueBinding("enableSwitch");
		return vb!=null ? Boolean.valueOf(_ComponentUtils.getStringValue(getFacesContext(), vb)) : null;
	}

	public void setEnableSwitch(Boolean enableSwitch) {
		this.enableSwitch = enableSwitch;
	}
	
	@Override
	public Object saveState(FacesContext context) {
		Object[] values = new Object[2];
		values[0] = super.saveState(context);
		values[1] = type;
		return values;
	}
	
	@Override
	public void restoreState(FacesContext context, Object state) {
		Object[] values = (Object[]) state; 
		super.restoreState(context, values[0]);
		type = (String) values[1];
	}
}
