/* *Class created on [ Jul 18, 2008 | 9:05:45 AM ] */
package com.asta.app2.webapp.jsf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.render.Renderer;

import com.asta.app2.webapp.jsf.util.Renderers;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ReserveContainerVerticalRenderer extends Renderer {

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	@Override
	public void decode(FacesContext context, UIComponent component) {
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		String clientId = component.getClientId(context);

		String content = (String) resquestParams.get(clientId);
		if (content != null && !content.equals("")) {
			UIReserveContainer container = (UIReserveContainer) component;
			container.setContent(content);
		}
		component.queueEvent(new ActionEvent(component));
	}

	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("table", component);

		String styleClass = (String) component.getAttributes()
				.get("styleClass");
		if (styleClass != null)
			writer.writeAttribute("class", styleClass, null);
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {
		if (component.getChildCount() == 0)
			return;
		ResponseWriter writer = context.getResponseWriter();

		List<SelectChairItem> selectChairItems = Renderers
				.getSelectChairs(component);

		int x = 1;
		for (SelectChairItem item : selectChairItems) {
			if (x == 5)
				x = 1;
			if (x == 1) {
				writer.startElement("thead", component);
				writer.startElement("tr", component);
			}

			encodeChairTD(context, writer, item, component);

			if (x == 4) {
				writer.endElement("tr");
				writer.endElement("thead");
			}
			x++;
		}

	}

	private void encodeChairTD(FacesContext context, ResponseWriter writer,
			SelectChairItem item, UIComponent component) throws IOException {
		String content = (String) item.getValue();
		String formId = Renderers.getFormId(context, component);

		writer.startElement("td", component);
		writer.writeAttribute("class", item.getCssClass(), null);

		if (content != null) {
			encodeFacet(component, content, context);
		}

		if (!item.isDisabled()){
			writer.startElement("input", component);
			writer.writeAttribute("id", formId + ":" + "selectedChairs" + ":"
					+ item.getLabel(), "id");
			writer.writeAttribute("type", "checkbox", "type");
			writer.writeAttribute("value", item.getLabel(), "value");
			writer.writeAttribute("class", "checkbox2", null);
			writer.writeAttribute("name", formId + ":" + "selectedChairs", "name");
			if (item.isDisabled())
				writer.writeAttribute("disabled", "disabled", "disabled");
			writer.endElement("input");
		}
		
		writer.startElement("br", component);
		writer.endElement("br");
		writer.startElement("input", component);
		writer.writeAttribute("type", "text", "type");
		if (item.isFemale())
			writer.writeAttribute("class", item.getCssClass()+"LabelFemale", "class");
		else
			writer.writeAttribute("class", item.getCssClass()+"Label", "class");
			
		writer.writeAttribute("value", item.getPassengerName(), "vlaue");
		writer.writeAttribute("disabled", "disabled", "disabled");
		writer.endElement("input");
		writer.endElement("td");
	}

	private void encodeFacet(UIComponent component, String content,
			FacesContext context) throws IOException {
		UIComponent facet = component.getFacet(content);
		if (facet != null) {
			if (facet.isRendered()) {
				facet.encodeBegin(context);
				if (facet.getRendersChildren())
					facet.encodeChildren(context);
				facet.encodeEnd(context);
			}
		}
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.endElement("table");
	}
}
