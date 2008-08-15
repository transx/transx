/* *Class created on [ Jun 21, 2008 | 9:22:42 AM ] */
package com.asta.app2.tutorial.reservecontainer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.render.Renderer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.asta.app2.tutorial.selectchair.SelectChair;
import com.asta.app2.tutorial.util.Renderers;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ReserveContainerRenderer extends Renderer {

	public boolean getRendersChildren() {
		return true;
	}

	@Override
	public void decode(FacesContext context, UIComponent component) {
		Map<String, String> requestParams = context.getExternalContext()
				.getRequestParameterMap();
		String clientId = component.getClientId(context);

		String content = (String) requestParams.get(clientId);
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
		
//		UISelectMany selectManyCheckbox = Renderers.getSelectMany(context, component);
//		selectManyCheckbox.encodeBegin(context);
//		selectManyCheckbox.encodeChildren(context);
//		selectManyCheckbox.encodeEnd(context);

		List<SelectChair> selectChairs = Renderers.getSelectChairs(component);
		
		
		writer.startElement("thead", component);
		writer.startElement("tr", component);
		writer.startElement("th", component);
		writer.startElement("table", component);
		writer.startElement("tbody", component);
		writer.startElement("tr", component);
		for (SelectChair item : selectChairs) {
			if (item.getY().equals("1")) {
				encodeChairTD(context, writer, item, component);
			}
		}
		writer.endElement("tr");
		writer.startElement("tr", component);
		for (SelectChair item : selectChairs) {
			if (item.getY().equals("1")) {
				encodeNameTD(context, writer, item, component);
			}
		}
		writer.endElement("tr");
		writer.endElement("tbody");
		writer.endElement("table");
		writer.endElement("th");
		writer.endElement("tr");
		writer.endElement("thead");
		writer.startElement("thead", component);
		writer.startElement("tr", component);
		writer.startElement("th", component);
		writer.startElement("table", component);
		writer.startElement("tbody", component);
		writer.startElement("tr", component);
		for (SelectChair item : selectChairs) {
			if (item.getY().equals("2")) {
				encodeChairTD(context, writer, item, component);
			}
		}
		writer.endElement("tr");
		writer.startElement("tr", component);
		for (SelectChair item : selectChairs) {
			if (item.getY().equals("2")) {
				encodeNameTD(context, writer, item, component);
			}
		}
		writer.endElement("tr");
		writer.endElement("tbody");
		writer.endElement("table");
		writer.endElement("th");
		writer.endElement("tr");
		writer.endElement("thead");
		writer.startElement("thead", component);
		writer.startElement("tr", component);
		writer.startElement("th", component);
		writer.startElement("table", component);
		writer.startElement("tbody", component);
		writer.startElement("tr", component);
		for (SelectChair item : selectChairs) {
			if (item.getY().equals("3")) {
				encodeChairTD(context, writer, item, component);
			}
		}
		writer.endElement("tr");
		writer.startElement("tr", component);
		for (SelectChair item : selectChairs) {
			if (item.getY().equals("3")) {
				encodeNameTD(context, writer, item, component);
			}
		}
		writer.endElement("tr");
		writer.endElement("tbody");
		writer.endElement("table");
		writer.endElement("th");
		writer.endElement("tr");
		writer.endElement("thead");
		writer.startElement("thead", component);
		writer.startElement("tr", component);
		writer.startElement("th", component);
		writer.startElement("table", component);
		writer.startElement("tbody", component);
		writer.startElement("tr", component);
		for (SelectChair item : selectChairs) {
			if (item.getY().equals("4")) {
				encodeChairTD(context, writer, item, component);
			}
		}
		writer.endElement("tr");
		writer.startElement("tr", component);
		for (SelectChair item : selectChairs) {
			if (item.getY().equals("4")) {
				encodeNameTD(context, writer, item, component);
			}
		}
		writer.endElement("tr");
		writer.endElement("tbody");
		writer.endElement("table");
		writer.endElement("th");
		writer.endElement("tr");
		writer.endElement("thead");

	}

	private void encodeChairTD(FacesContext context, ResponseWriter writer,
			SelectChair item, UIComponent component) throws IOException {
//		String chairText = item.getLabel();
		String content = (String) item.getValue();
//		String clientId = selectManyCheckbox.getClientId(context);
		String formId = Renderers.getFormId(context, component);
//		<input id="reserveForm:selectedChairs:0" type="checkbox" value="1" name="reserveForm:selectedChairs"/>
//		<label for="reserveForm:selectedChairs:0"/>
		
		writer.startElement("td", component);
		writer.writeAttribute("class", item.getCssClass(), null);
		
		if (content != null) {
			encodeFacet(component,content,context);
		}
		
//		UISelectItem selectItem = new UISelectItem();
//		selectItem.setItemValue(item.getX());
//		selectItem.setItemLabel(item.getLabel());
//		selectItem.setItemDisabled(item.isDisabled());
//		selectItem.setParent(selectManyCheckbox);
//		selectItem.encodeAll(context);
		writer.startElement("input", component);
		writer.writeAttribute("id",formId+":"+"selectedChairs"+":"+item.getX(), "id");
		writer.writeAttribute("type", "checkbox", "type");
		writer.writeAttribute("value", item.getX(), "value");
		writer.writeAttribute("name", formId+":"+"selectedChairs", "name");
		
		if (item.isDisabled())
			writer.writeAttribute("disabled", "disabled", "disabled");
		
		writer.endElement("input");
		writer.endElement("td");
	}

	private void encodeNameTD(FacesContext context, ResponseWriter writer,
			SelectChair item, UIComponent component) throws IOException {
		writer.startElement("td", component);
		writer.startElement("input", component);
		writer.writeAttribute("type", "text", "type");
		writer.writeAttribute("class", item.getCssClass(), "class");
		writer.writeAttribute("value", item.getPassengerName(), "vlaue");
		writer.writeAttribute("disabled", "disabled", "disabled");
		writer.endElement("input");
		writer.endElement("td");
	}

	private void encodeFacet(UIComponent component, String content,
			FacesContext context) throws IOException {
		UIComponent facet = component.getFacet(content);
		if (facet != null){
			if (facet.isRendered()){
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
//		UIReserveContainer container = (UIReserveContainer) component;
//		String content = container.getContent();
//		UIComponent parent = component;
//		while (!(parent instanceof UIForm))
//			parent = parent.getParent();
//		
		
/*		writer.startElement("tbody", component);
		writer.startElement("tr", component);
		writer.startElement("td", component);

		if (content != null) {
			
			UIComponent facet = component.getFacet(content);
			if (facet != null) {
				if (facet.isRendered()) {
					facet.encodeBegin(context);
					if (facet.getRendersChildren())
						facet.encodeChildren(context);
					facet.encodeEnd(context);
				}
			} else
				includePage(context, component);
		}

		writer.endElement("td");
		writer.endElement("tr");
		writer.endElement("tbody");

		// Close off the column, row, and table elements
		writer.endElement("table");
*/
//		encodeHiddenField(context, writer, component);
	}

	private void encodeHiddenField(FacesContext context, ResponseWriter writer,
			UIComponent component) throws IOException {
		// write hidden field whose name is the reserveContainer's client Id
		writer.startElement("input", component);
		writer.writeAttribute("type", "hidden", null);
		writer.writeAttribute("name", component.getClientId(context), null);
		writer.endElement("input");
	}

	private void includePage(FacesContext fc, UIComponent component) {
		ExternalContext ec = fc.getExternalContext();
		ServletContext sc = (ServletContext) ec.getContext();
		UIReserveContainer container = (UIReserveContainer) component;
		String content = container.getContent();

		ServletRequest request = (ServletRequest) ec.getRequest();
		ServletResponse response = (ServletResponse) ec.getResponse();

		try {
			sc.getRequestDispatcher(content).include(request, response);
		} catch (ServletException ex) {
//			logger.log(Level.WARNING, "Couldn't load page: " + content, ex);
		} catch (IOException ex) {
//			logger.log(Level.WARNING, "Couldn't load page:" + content, ex);
		}
	}
}
