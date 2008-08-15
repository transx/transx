/* *Class created on [ Jun 17, 2008 | 9:03:12 AM ] */ 
package com.asta.app2.tutorial.spinnerjs;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.ConverterException;
import javax.faces.render.Renderer;

import com.asta.app2.tutorial.util.Renderers;

/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class JSSpinnerRenderer extends Renderer {
   public Object getConvertedValue(FacesContext context, UIComponent component,
         Object submittedValue) throws ConverterException {
      return Renderers.getConvertedValue(context, component,
         submittedValue);
   }
   
   public void encodeBegin(FacesContext context, UIComponent component) 
         throws IOException {
      ResponseWriter writer = context.getResponseWriter();
      String clientId = component.getClientId(context);
      String formId = Renderers.getFormId(context, component);
           
      UIInput spinner = (UIInput)component;
      String min = (String) component.getAttributes().get("minimum");
      String max = (String) component.getAttributes().get("maximum");
      String size = (String) component.getAttributes().get("size");
     
      writer.startElement("input", spinner);
      writer.writeAttribute("type", "text", null);
      writer.writeAttribute("name", clientId , null);
      writer.writeAttribute("value", spinner.getValue().toString(), "value");
      if (size != null) 
          writer.writeAttribute("size", size , null);
      writer.endElement("input");

      writer.write(MessageFormat.format(
         "<script language=\"JavaScript\">"
         + "document.forms[''{0}''][''{1}''].spin = function (increment) '{'"
         + "var v = parseInt(this.value) + increment;"
         + "if (isNaN(v)) return;"
         + "if (\"min\" in this && v < this.min) return;"
         + "if (\"max\" in this && v > this.max) return;"
         + "this.value = v;"
         + "};",
         new Object[] { formId, clientId } ));

      if (min != null) {
         writer.write(MessageFormat.format(
            "document.forms[''{0}''][''{1}''].min = {2};",
            new Object[] { formId, clientId, min }));
      }
      if (max != null) {
         writer.write(MessageFormat.format(
            "document.forms[''{0}''][''{1}''].max = {2};",
            new Object[] { formId, clientId, max }));
      }
      writer.write("</script>");

      writer.startElement("input", spinner);
      writer.writeAttribute("type", "button", null);
      writer.writeAttribute("value", "<", null);
      writer.writeAttribute("onclick", 
    		  MessageFormat.format(
    				  "document.forms[''{0}''][''{1}''].spin(-1);", 
    				  new Object[] { formId, clientId }), 
    		  null);
      writer.endElement("input");

      writer.startElement("input", spinner);
      writer.writeAttribute("type", "button", null);
      writer.writeAttribute("value", ">", null);
      writer.writeAttribute("onclick", 
    		  MessageFormat.format(
    				  "document.forms[''{0}''][''{1}''].spin(1);", 
    				  new Object[] { formId, clientId }), 
    		  null);
      writer.endElement("input");
   }
   
   public void decode(FacesContext context, UIComponent component) {
      EditableValueHolder spinner = (EditableValueHolder) component;
      Map<String, String> requestMap 
         = context.getExternalContext().getRequestParameterMap();
      String clientId = component.getClientId(context);
      spinner.setSubmittedValue((String) requestMap.get(clientId));
      spinner.setValid(true);
   }
}


