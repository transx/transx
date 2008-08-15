/* *Class created on [ Jul 31, 2008 | 7:27:42 PM ] */
package org.apache.myfaces.custom.avandate2;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.ConverterException;

import org.apache.myfaces.shared_impl.renderkit.html.HTML;
import org.apache.myfaces.shared_impl.util._ComponentUtils;
import org.apache.myfaces.shared_tomahawk.renderkit.html.HtmlRendererUtils;

import org.apache.myfaces.renderkit.html.ext.HtmlTextRenderer;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.asta.app2.util.DateUtil;
import com.asta.app2.util.date.GregorianDate;
import com.asta.app2.util.date.JalaliDate;
import com.asta.app2.util.date.JalaliDateUtil;
import com.asta.app2.util.date.UserDateFormat;

/**
 * the original class of this renderer exist on
 * ir.asta.core(org.apache.myfaces.custom.avandate2.InputDateRenderer2)
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class InputDate2Renderer extends HtmlTextRenderer{
	private static final String SECOND_TYPE = "second";
	private static final String MINUTE_TYPE = "minute";
	private static final String HOUR_TYPE = "hour";
	private static final String MONTH_TYPE = "month";
	private static final String DAY_TYPE = "day";
	private static final String TIME_TYPE = "time";
	private static final String TIME_MINUTE_TYPE = "time-minute";

	private static final String DATE_TYPE = "date";
	private static final String FULL_TYPE = "full";
	private static final String BOTH_TYPE = "both";
	
	private static final String ID_YEAR_POSTFIX = "_y";
	private static final String ID_MONTH_POSTFIX = "_m";
	private static final String ID_DAY_POSTFIX = "_d";

	private static final String ID_HOUR_POSTFIX = "_h";
	private static final String ID_MINUTE_POSTFIX = "_n";
	private static final String ID_SECOND_POSTFIX = "_s";
	
	private static final String SECOND_PATTERN = "yyyy/MM/dd HH:mm:ss";
	private static final String MINUTE_PATTERN = "yyyy/MM/dd HH:mm";
	private static final String HOUR_PATTERN = "yyyy/MM/dd HH";
	private static final String DAY_PATTERN = "yyyy/MM/dd";
	private static final String MONTH_PATTERN = "yyyy/MM";
	private static final String TIME_PATTERN = "HH:mm:ss";
	private static final String TIME_MINUTE_PATTERN = "HH:mm";
	private static final String JAVASCRIPT_ENCODED = "org.apache.myfaces.calendar.avan.date2.JAVASCRIPT_ENCODED";
	
	@Override
	public void decode(FacesContext context, UIComponent component) {
		UIInputDate2 input = (UIInputDate2) component;
		Map requestMap = context.getExternalContext().getRequestParameterMap();
		String stringValue= (String) requestMap.get(component.getClientId(context));
		input.setSubmittedValue(stringValue);
	}
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		addScriptAndCSSResources(context,component);
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		UIInputDate2 input = (UIInputDate2) component;
		ResponseWriter writer = context.getResponseWriter();
		
		Date value = (Date) input.getValue();
		String type = input.getType();
		
		if (input.isDisplayValueOnly()){
			if (value!=null) {
				if(TIME_TYPE.equals(type))
					writer.write(new SimpleDateFormat(TIME_PATTERN).format(value));
				else if( type.equals(TIME_MINUTE_TYPE.equals(type)))
					writer.write(new SimpleDateFormat(TIME_MINUTE_PATTERN).format(value));
				else
					writer.write(DateUtil.getLocaleFormattedDateTime(value));
			}
			return;
		}
		
		boolean disabled = input.isDisabled();
		boolean readonly = input.isReadonly();
		Calendar c=Calendar.getInstance();
		
		String year="";
		String month="";
		String day="";
		String hour="";
		String minute="";
		String second="";
		if(value!=null){
			if(DateUtil.isUserCalendarJalali()) {
				JalaliDate jalaliValue = JalaliDateUtil.convertToJalali(new GregorianDate(value.getTime()));
				year=padLeft(""+jalaliValue.getYear());
				month=padLeft(""+jalaliValue.getMonth());
				day=padLeft(""+jalaliValue.getDay());
			}else {
				c.setTime(value);
				year = padLeft(year+ c.get(Calendar.YEAR));
				month = padLeft(month+ (c.get(Calendar.MONTH)+1));
				day = padLeft(day+ c.get(Calendar.DAY_OF_MONTH));
			}
			hour = padLeft(hour+ c.get(Calendar.HOUR_OF_DAY));
			minute = padLeft(minute+ c.get(Calendar.MINUTE));
			second = padLeft(second+ c.get(Calendar.SECOND));
		}

		String id=input.getClientId(context);
		
		writer.startElement(HTML.TABLE_ELEM, component);
		writer.writeAttribute(HTML.ID_ATTR, id+"_Table", null);
		writer.startElement(HTML.TR_ELEM, component);

		writer.startElement(HTML.TD_ELEM, component);
		encodeInputHidden(component, writer, id, year+"/"+month+"/"+day+ " "+hour+":"+minute+":"+second);
		writer.endElement(HTML.TD_ELEM);
		
		if(!TIME_TYPE.equals(type) && !TIME_MINUTE_TYPE.equals(type)){
			writer.startElement(HTML.TD_ELEM, component);
			writer.startElement(HTML.TABLE_ELEM, component);
			writer.writeAttribute(HTML.CELLPADDING_ATTR, "0", null);
			writer.writeAttribute(HTML.CELLSPACING_ATTR, "0", null);
			writer.writeAttribute(HTML.STYLE_ATTR, "background:#ffffff;border:1px solid #bbccdd;direction:ltr",	null);
			writer.startElement(HTML.TR_ELEM, component);
	
			writer.startElement(HTML.TD_ELEM, component);
			encodeInputYear(component,writer,id, year,disabled,readonly);
			writer.endElement(HTML.TD_ELEM);
		
			writer.startElement(HTML.TD_ELEM, component);
			writer.write("/");
			writer.endElement(HTML.TD_ELEM);
	
			writer.startElement(HTML.TD_ELEM, component);
			encodeInputMonth(component,writer,id, month,disabled,readonly);
			writer.endElement(HTML.TD_ELEM);
			if(!MONTH_TYPE.equals(type)){
				writer.startElement(HTML.TD_ELEM, component);
				writer.write("/");
				writer.endElement(HTML.TD_ELEM);
				
				writer.startElement(HTML.TD_ELEM, component);
				encodeInputDay(component,writer,id, day,disabled,readonly);
				writer.endElement(HTML.TD_ELEM);
			}
			writer.endElement(HTML.TD_ELEM);
			writer.endElement(HTML.TR_ELEM);
			writer.endElement(HTML.TABLE_ELEM);
			writer.endElement(HTML.TD_ELEM);
		}
		if(!DATE_TYPE.equals(type) && !DAY_TYPE.equals(type) && !MONTH_TYPE.equals(type)&& type!=null && type.length()!=0){
			writer.startElement(HTML.TD_ELEM, component);
			writer.startElement(HTML.TABLE_ELEM, component);
			writer.writeAttribute(HTML.CELLPADDING_ATTR, "0", null);
			writer.writeAttribute(HTML.CELLSPACING_ATTR, "0", null);
			writer.writeAttribute(HTML.STYLE_ATTR, "background:#ffffff;border:1px solid #bbccdd;direction:ltr",	null);
			writer.startElement(HTML.TR_ELEM, component);
	
			writer.startElement(HTML.TD_ELEM, component);
			encodeInputHour(component,writer,id, hour,disabled,readonly);
			writer.endElement(HTML.TD_ELEM);
		
			if(!HOUR_TYPE.equals(type)){
				writer.startElement(HTML.TD_ELEM, component);
				writer.write(":");
				writer.endElement(HTML.TD_ELEM);

				writer.startElement(HTML.TD_ELEM, component);
				encodeInputMinute(component,writer,id, minute,disabled,readonly);
				writer.endElement(HTML.TD_ELEM);
			}
			
			if(!HOUR_TYPE.equals(type) && !MINUTE_TYPE.equals(type)&& !TIME_MINUTE_TYPE.equals(type)){
				writer.startElement(HTML.TD_ELEM, component);
				writer.write(":");
				writer.endElement(HTML.TD_ELEM);
				
				writer.startElement(HTML.TD_ELEM, component);
				encodeInputSecond(component,writer,id, second,disabled,readonly);
				writer.endElement(HTML.TD_ELEM);
			}
			writer.endElement(HTML.TD_ELEM);
			writer.endElement(HTML.TR_ELEM);
			writer.endElement(HTML.TABLE_ELEM);
			writer.endElement(HTML.TD_ELEM);

		}
		if(!TIME_TYPE.equals(type) && !TIME_MINUTE_TYPE.equals(type) && !disabled){
			writer.startElement(HTML.TD_ELEM, component);
			encodeSelectDate(context, input, component.getClientId(context));
			writer.endElement(HTML.TD_ELEM);
			writer.startElement(HTML.TD_ELEM, component);
			encodeClearDate(context, component, component.getClientId(context));
			writer.endElement(HTML.TD_ELEM);
		}
		writer.endElement(HTML.TR_ELEM);
		writer.endElement(HTML.TABLE_ELEM);
	}

	@Override
	public Object getConvertedValue(FacesContext facesContext,
			UIComponent component, Object submittedValue)
			throws ConverterException {
		UIInputDate2 input = (UIInputDate2) component;
		String type = input.getType();
		Date dateValue = null;
		SimpleDateFormat format = getDateFormat(type);
		try {
			String refinedTimeString = refineTimeString((String)submittedValue);
			if (DateUtil.isUserCalendarJalali()){
				String timePart="";
				String datePart=refinedTimeString;
				int timeStartIndex = refinedTimeString.indexOf(" ");
				if(timeStartIndex>0) {
					datePart=refinedTimeString.substring(0,timeStartIndex);
					timePart=refinedTimeString.substring(timeStartIndex);
				}
				refinedTimeString=new SimpleDateFormat("yyyy/MM/dd").format(DateUtil.convertJalaliDate(datePart, "/"))+timePart;
				dateValue=new SimpleDateFormat(getDateFormatString(type)).parse(refinedTimeString);
			}
			else
				dateValue = format.parse(refinedTimeString);
			input.setValid(true);
		}catch(Exception e){
			
		}
		return dateValue;
	}
	
	private String refineTimeString(String time) {
		String refinedTime=time;
		String[] dateTime=time.split(" ");
		String datePart="";
		String timePart="";
		refinedTime="";
		if(dateTime.length==2){
			datePart = dateTime[0];
			timePart = " "+dateTime[1];
		}else{
			if(dateTime[0].indexOf(":")>0) {
				datePart="";
				timePart=" "+dateTime[0];
			}else {
				datePart=dateTime[0];
				timePart="";
			}
		}
		if(datePart.equals("//"))
			datePart="";
		for(int i=0;i<datePart.length();i++){
			if(isSeperator(datePart, i) && isSeperator(datePart, i+1) ){
				refinedTime=refinedTime + datePart.charAt(i)+"01";
			}else
				refinedTime=refinedTime+ datePart.charAt(i);
		}
		for(int i=0;i<timePart.length();i++){
			if(isSeperator(timePart, i) && isSeperator(timePart, i+1) ){
				refinedTime=refinedTime + timePart.charAt(i)+"00";
			}else
				refinedTime=refinedTime+ timePart.charAt(i);
		}
		if(refinedTime.endsWith(" "))
			refinedTime=refinedTime+"00";
		return refinedTime;
	}

	private boolean isSeperator(String time, int i) {
		if(i>=time.length())return true;
		return time.charAt(i)==':'||time.charAt(i)==' '||time.charAt(i)=='/';
	}

	private void encodeClearDate(FacesContext context, UIComponent component,
			String clientId) throws IOException{
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement(HTML.ANCHOR_ELEM, component);
		writer.writeAttribute(HTML.HREF_ATTR, "javascript:void(0)", null);
		String onclick = "javascript:clearInput('" + clientId+ "');" +
				"clearInput('" + clientId+ID_YEAR_POSTFIX +"');" +
				"clearInput('" + clientId+ID_MONTH_POSTFIX +"');" +
				"clearInput('" + clientId+ID_DAY_POSTFIX +"');" +
				"clearInput('" + clientId+ID_HOUR_POSTFIX +"');" +
				"clearInput('" + clientId+ID_MINUTE_POSTFIX+"');" +
				"clearInput('" + clientId+ID_SECOND_POSTFIX +"');" +
				"return false;";

		writer.writeAttribute(HTML.ONCLICK_ATTR, onclick, null);
		writer.startElement(HTML.IMG_ELEM, component);
		writer.writeAttribute(HTML.NAME_ATTR, clientId + "_select", null);
		writer.writeAttribute(HTML.SRC_ATTR, getUrl(context,
				"/images/btn_lovclear.gif"), "value");
		writer.writeAttribute(HTML.BORDER_ATTR, "0", null);
		writer.endElement(HTML.IMG_ELEM);
		writer.endElement(HTML.ANCHOR_ELEM);
		
	}

	private void encodeSelectDate(FacesContext context, UIInputDate2 component,
			String clientId) throws IOException{
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement(HTML.ANCHOR_ELEM, component);
		writer.writeAttribute(HTML.HREF_ATTR, "javascript:void(0)", null);
		String parentForm=_ComponentUtils.findNestingForm(component, context).getFormName();
		String onclick = "jscalendarSetImageDirectory('"+context.getExternalContext().getRequestContextPath()+"/images/');" +
				"addCalendar('calc" + clientId + "', 'Select Date', '"
				+ clientId + "', '" + parentForm + "');enableSwitch="+component.getEnableSwitch()+";showCal('calc"
				+ clientId + "'); return false;";
		writer.writeAttribute(HTML.ONCLICK_ATTR, onclick, null);
		writer.startElement(HTML.IMG_ELEM, component);
		writer.writeAttribute(HTML.NAME_ATTR, clientId + "_select", null);
		writer.writeAttribute(HTML.SRC_ATTR, getUrl(context,
				"/images/btn_calendar.gif"), "value");
		writer.writeAttribute(HTML.BORDER_ATTR, "0", null);
		writer.endElement(HTML.IMG_ELEM);
		writer.endElement(HTML.ANCHOR_ELEM);
		
	}

	private void encodeInputSecond(UIComponent component,
			ResponseWriter writer, String mainId, String second, boolean disabled,
			boolean readonly) throws IOException{
		encodeInputField(component,writer,mainId,ID_SECOND_POSTFIX,second,2,disabled,readonly,"width:15px;border:0px;background:#ffffff");
		
	}

	private void encodeInputMinute(UIComponent component,
			ResponseWriter writer, String mainId, String minute, boolean disabled,
			boolean readonly) throws IOException{
    	encodeInputField(component,writer,mainId,ID_MINUTE_POSTFIX,minute,2,disabled,readonly,"width:15px;border:0px;background:#ffffff");
		
	}

	private void encodeInputHour(UIComponent component, ResponseWriter writer,
			String mainId, String hour, boolean disabled, boolean readonly) throws IOException{
		encodeInputField(component,writer,mainId,ID_HOUR_POSTFIX,hour,2,disabled,readonly,"width:15px;border:0px;background:#ffffff");
		
	}

	private void encodeInputDay(UIComponent component, ResponseWriter writer,
			String mainId, String day, boolean disabled, boolean readonly) throws IOException{
		encodeInputField(component,writer,mainId,ID_DAY_POSTFIX,day,2,disabled,readonly,"width:15px;border:0px;background:#ffffff");
		
	}

	private void encodeInputMonth(UIComponent component, ResponseWriter writer,
			String mainId, String month, boolean disabled, boolean readonly) throws IOException{
		encodeInputField(component,writer,mainId,ID_MONTH_POSTFIX,month,2,disabled,readonly,"width:15px;border:0px;background:#ffffff");
	}

	private void encodeInputYear(UIComponent component, ResponseWriter writer,
			String mainId, String year, boolean disabled, boolean readonly) throws IOException{
		encodeInputField(component,writer,mainId,ID_YEAR_POSTFIX,year,4,disabled,readonly,"width:30px;border:0px;background:#ffffff");
	}

	private void encodeInputField(UIComponent component, ResponseWriter writer,
			String mainId, String postFix, String value, int size,
			boolean disabled, boolean readonly, String style) throws IOException{
    	String id=mainId+postFix;
    	String onKeyDown="keyEnter(event.keyCode,this,"+size+")";
    	String onKeyPress="keyFilter(event,this)";
    	String onBlur="updateHiddenDateValue('"+mainId+"')";
    	writer.startElement(HTML.INPUT_ELEM, component);
        
    	writer.writeAttribute(HTML.STYLE_ATTR, style, null); 
    	writer.writeAttribute(HTML.ONKEYDOWN_ATTR, onKeyDown, null);
    	writer.writeAttribute(HTML.ONKEYPRESS_ATTR, onKeyPress, null);
    	writer.writeAttribute(HTML.ONBLUR_ATTR, onBlur, null);

    	HtmlRendererUtils.renderHTMLAttributes(writer, component, HTML.UNIVERSAL_ATTRIBUTES);
        HtmlRendererUtils.renderHTMLAttributes(writer, component, HTML.EVENT_HANDLER_ATTRIBUTES);
        HtmlRendererUtils.renderHTMLAttributes(writer, component, HTML.INPUT_ATTRIBUTES);
        HtmlRendererUtils.renderHTMLAttributes(writer, component, HTML.COMMON_FIELD_EVENT_ATTRIBUTES);

		if (disabled) {
		    writer.writeAttribute(HTML.DISABLED_ATTR, Boolean.TRUE, null);
		}
		if( readonly ) {
			writer.writeAttribute(HTML.READONLY_ATTR, Boolean.TRUE, null);
		}

		writer.writeAttribute(HTML.ID_ATTR, id, null);
		writer.writeAttribute(HTML.NAME_ATTR, id, null);
		writer.writeAttribute(HTML.SIZE_ATTR, Integer.toString(size), null);
		writer.writeAttribute(HTML.MAXLENGTH_ATTR, Integer.toString(size), null);
		if (value != null) {
		    writer.writeAttribute(HTML.VALUE_ATTR, value, null);
		}
		writer.endElement(HTML.INPUT_ELEM);
		
	}

	private void encodeInputHidden(UIComponent component,
			ResponseWriter writer, String id, String value) throws IOException {
		writer.startElement(HTML.INPUT_ELEM, component);
		writer.writeAttribute(HTML.TYPE_ATTR,HTML.INPUT_TYPE_HIDDEN,null);
		writer.writeAttribute(HTML.ID_ATTR, id, null);
		writer.writeAttribute(HTML.NAME_ATTR, id, null);
		if (value != null) {
 
//			UIInputDate2 input = (UIInputDate2) component;
//			String type = input.getType();
//			SimpleDateFormat format = getDateFormat(type);
			writer.writeAttribute(HTML.VALUE_ATTR, value, null);
		}
		writer.endElement(HTML.INPUT_ELEM);
	}

	static public void addScriptAndCSSResources(FacesContext context,
			UIComponent component) {
		if (context.getExternalContext().getRequestMap().containsKey(JAVASCRIPT_ENCODED)){
			return;
		}
		AddResource addResource = AddResourceFactory.getInstance(context);
		
        String javascriptLocation = HtmlRendererUtils.getJavascriptLocation(component);

        if(javascriptLocation==null){
		
			addResource.addJavaScriptAtPosition(context, AddResource.HEADER_BEGIN, InputDate2Renderer.class,"dateTagScript.js");
			addResource.addJavaScriptAtPosition(context, AddResource.HEADER_BEGIN, InputDate2Renderer.class,"dateScript.js");
			addResource.addJavaScriptAtPosition(context, AddResource.HEADER_BEGIN, InputDate2Renderer.class,"persianPopupCalendar.js");
			addResource.addJavaScriptAtPosition(context, AddResource.HEADER_BEGIN, InputDate2Renderer.class,"dFilter.js");
	        
        }else{
        	
        	addResource.addJavaScriptAtPosition(context, AddResource.HEADER_BEGIN, javascriptLocation + "/dateTagScript.js");
        	addResource.addJavaScriptAtPosition(context, AddResource.HEADER_BEGIN, javascriptLocation + "/dateScript.js");
        	addResource.addJavaScriptAtPosition(context, AddResource.HEADER_BEGIN, javascriptLocation + "/persianPopupCalendar.js");
        	addResource.addJavaScriptAtPosition(context, AddResource.HEADER_BEGIN, javascriptLocation + "/dFilter.js");
        	
        }
		
		String calendarTypeScript = "calendarType=";
		if(com.asta.app2.util.DateUtil.isUserCalendarJalali())
			calendarTypeScript=calendarTypeScript+"'hijri'";
		else
			calendarTypeScript=calendarTypeScript+"'gregorian'";
		
		addResource.addInlineScriptAtPosition(context,AddResource.HEADER_BEGIN, calendarTypeScript);
		
		context.getExternalContext().getRequestMap().put(JAVASCRIPT_ENCODED, Boolean.TRUE);
	}
	
	private String getUrl(FacesContext facesContext, String url) {
		return facesContext.getExternalContext().getRequestContextPath() + url;
	}
	
	private SimpleDateFormat getDateFormat(String type) {
		SimpleDateFormat formatter=null;
		String formatString = getDateFormatString(type);
		if(DateUtil.isUserCalendarJalali())
			formatter=new UserDateFormat(formatString);
		else
			formatter=new SimpleDateFormat(formatString);
		return formatter;
	}

	private String getDateFormatString(String type) {
		String formatString=null;
		if(SECOND_TYPE.equals(type) || FULL_TYPE.equals(type)|| BOTH_TYPE.equals(type))
			formatString= SECOND_PATTERN;
		else if(MINUTE_TYPE.equals(type))
			formatString= MINUTE_PATTERN;
		else if(HOUR_TYPE.equals(type))
			formatString= HOUR_PATTERN;
		else if(DAY_TYPE.equals(type) || DATE_TYPE.equals(type)|| type==null || type.length()==0)
			formatString= DAY_PATTERN;
		else if(MONTH_TYPE.equals(type))
			formatString= MONTH_PATTERN;
		else if(TIME_MINUTE_TYPE.equals(type))
			formatString= TIME_MINUTE_PATTERN;
		else if(TIME_TYPE.equals(type))
			formatString= TIME_PATTERN;
		else formatString= DAY_PATTERN;
		return formatString;
	}

	private String padLeft(String s){
		String str=s;
		if(str!=null && str.length()==1)
			str="0"+str;
		return str;
	}


}
