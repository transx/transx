/* *Class created on [ Jun 19, 2008 | 2:05:18 PM ] */
package com.asta.app2.tutorial.sayhello;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class SayHelloRenderer extends Renderer {

	@Override
	public void decode(FacesContext context, UIComponent component) {
		super.decode(context, component);
	}

	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		super.encodeBegin(context, component);
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {
		super.encodeChildren(context, component);
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		super.encodeEnd(context, component);
		if (!component.isRendered())
			return;

		ResponseWriter writer = context.getResponseWriter();
		SayHello sayHello = (SayHello) component;
		String firstName = sayHello.getFirstName();
		String lastName = sayHello.getLastName();
		writer.write("Hello ");

		if (firstName != null)
			writer.write("" + firstName);

		if (lastName != null)
			writer.write("" + lastName);
		
		writer.write("!");
	}

}
