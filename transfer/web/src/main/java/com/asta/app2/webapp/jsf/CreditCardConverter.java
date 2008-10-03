/* *Class created on [ Oct 2, 2008 | 11:17:38 AM ] */ 
package com.asta.app2.webapp.jsf;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.asta.app2.tutorial.numberbean.CreditCard;
	
/**
 * the source of this class exist on Core JavaServer Faces 2nd Edition May 2007 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CreditCardConverter implements Converter, Serializable {
	   private String separator;

	   // PROPERTY: separator
	   public void setSeparator(String newValue) { separator = newValue; }

	   public Object getAsObject(
	      FacesContext context,
	      UIComponent component,
	      String newValue)
	      throws ConverterException {
	      StringBuilder builder = new StringBuilder(newValue);
	      int i = 0;
	      while (i < builder.length()) {
	         if (Character.isDigit(builder.charAt(i)))
	            i++;
	         else
	            builder.deleteCharAt(i);
	      }
	      return new CreditCard(builder.toString());
	   }

	   public String getAsString(
	      FacesContext context,
	      UIComponent component,
	      Object value)
	      throws ConverterException {
	      // length 13: xxxx xxx xxx xxx
	      // length 14: xxxxx xxxx xxxxx
	      // length 15: xxxx xxxxxx xxxxx
	      // length 16: xxxx xxxx xxxx xxxx
	      // length 22: xxxxxx xxxxxxxx xxxxxxxx
	      if (!(value instanceof CreditCard))
	         throw new ConverterException();      
	      String v = ((CreditCard) value).toString();
	      String sep = separator;
	      if (sep == null) sep = " ";
	      int[] boundaries = null;
	      int length = v.length();
	      if (length == 13)
	         boundaries = new int[] { 4, 7, 10 };
	      else if (length == 14)
	         boundaries = new int[] { 5, 9 };
	      else if (length == 15)
	         boundaries = new int[] { 4, 10 };
	      else if (length == 16)
	         boundaries = new int[] { 4, 8, 12 };
	      else if (length == 22)
	         boundaries = new int[] { 6, 14 };
	      else
	         return v;
	      StringBuilder result = new StringBuilder();
	      int start = 0;
	      for (int i = 0; i < boundaries.length; i++) {
	         int end = boundaries[i];
	         result.append(v.substring(start, end));
	         result.append(sep);
	         start = end;
	      }
	      result.append(v.substring(start));
	      return result.toString();
	   }
	}
