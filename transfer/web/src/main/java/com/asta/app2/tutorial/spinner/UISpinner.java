/* *Class created on [ Jun 16, 2008 | 2:38:14 PM ] */
package com.asta.app2.tutorial.spinner;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.IntegerConverter;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class UISpinner extends UIInput {
	private static final String MORE = ".more";
	private static final String LESS = ".less";

	public UISpinner() {
		setConverter(new IntegerConverter()); // to convert the submitted value
		setRendererType(null); // this component renders itself
	}

	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String clientId = getClientId(context);

		encodeInputField(writer, clientId);
		encodeDecrementButton(writer, clientId);
		encodeIncrementButton(writer, clientId);
	}

	public void decode(FacesContext context) {
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
		String clientId = getClientId(context);

		int increment;
		if (requestMap.containsKey(clientId + MORE))
			increment = 1;
		else if (requestMap.containsKey(clientId + LESS))
			increment = -1;
		else
			increment = 0;

		try {
			int submittedValue = Integer.parseInt((String) requestMap.get(clientId));

			int newValue = getIncrementedValue(submittedValue, increment);
			setSubmittedValue("" + newValue);
			setValid(true);
		} catch (NumberFormatException ex) {
			// let the converter take care of bad input, but we still have
			// to set the submitted value, or the converter won't have
			// any input to deal with
			setSubmittedValue((String) requestMap.get(clientId));
		}
	}

	private void encodeInputField(ResponseWriter writer, String clientId)
			throws IOException {
		writer.startElement("input", this);
		writer.writeAttribute("name", clientId, "clientId");

		Object v = getValue();
		if (v != null)
			writer.writeAttribute("value", v.toString(), "value");

		String size = (String) getAttributes().get("size");
		if (size != null)
			writer.writeAttribute("size",size, "size");

		writer.endElement("input");
	}

	private void encodeDecrementButton(ResponseWriter writer, String clientId)
			throws IOException {
		writer.startElement("input", this);
		writer.writeAttribute("type", "submit", null);
		writer.writeAttribute("name", clientId + LESS, null);
		writer.writeAttribute("value", "<", "value");
		writer.endElement("input");
	}

	private void encodeIncrementButton(ResponseWriter writer, String clientId)
			throws IOException {
		writer.startElement("input", this);
		writer.writeAttribute("type", "submit", null);
		writer.writeAttribute("name", clientId + MORE, null);
		writer.writeAttribute("value", ">", "value");
		writer.endElement("input");
	}

	private int getIncrementedValue(int submittedValue, int increment) {
		String minimum = (String) getAttributes().get("minimum");
		String maximum = (String) getAttributes().get("maximum");
		int newValue = submittedValue + increment;

		if ((minimum == null || newValue >= Integer.valueOf(minimum))
				&& (maximum == null || newValue <= Integer.valueOf(maximum)))
			return newValue;
		else
			return submittedValue;
	}
}
