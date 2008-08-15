/* *Class created on [ Jun 16, 2008 | 10:02:41 PM ] */
package com.asta.app2.tutorial.spinner2;

import javax.faces.component.UIInput;
import javax.faces.convert.IntegerConverter;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class UISpinner2 extends UIInput {
	public UISpinner2() {
		setConverter(new IntegerConverter()); // to convert the submitted value
	}
}
