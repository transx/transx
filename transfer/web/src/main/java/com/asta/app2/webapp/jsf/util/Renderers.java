/* *Class created on [ Jun 16, 2008 | 10:11:34 PM ] */

package com.asta.app2.webapp.jsf.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.component.UISelectMany;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.model.SelectItem;

import com.asta.app2.webapp.jsf.SelectChairItem;
import com.asta.app2.webapp.jsf.UISelectChairItem;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class Renderers {
	public static Object getConvertedValue(FacesContext context,
			UIComponent component, Object submittedValue)
			throws ConverterException {
		if (submittedValue instanceof String) {
			Converter converter = getConverter(context, component);
			if (converter != null) {
				return converter.getAsObject(context, component,
						(String) submittedValue);
			}
		}
		return submittedValue;
	}

	public static Converter getConverter(FacesContext context,
			UIComponent component) {
		if (!(component instanceof ValueHolder))
			return null;
		ValueHolder holder = (ValueHolder) component;

		Converter converter = holder.getConverter();
		if (converter != null)
			return converter;

		ValueExpression expr = component.getValueExpression("value");
		if (expr == null)
			return null;

		Class targetType = expr.getType(context.getELContext());
		if (targetType == null)
			return null;
		// Version 1.0 of the reference implementation will not apply a
		// converter
		// if the target type is String or Object, but that is a bug.

		Application app = context.getApplication();
		return app.createConverter(targetType);
	}

	public static String getFormId(FacesContext context, UIComponent component) {
		UIComponent parent = component;
		while (!(parent instanceof UIForm))
			parent = parent.getParent();
		return parent.getClientId(context);
	}

	@SuppressWarnings("unchecked")
	public static List<SelectItem> getSelectItems(UIComponent component) {
		ArrayList<SelectItem> list = new ArrayList<SelectItem>();
		for (UIComponent child : component.getChildren()) {
			if (child instanceof UISelectItem) {
				Object value = ((UISelectItem) child).getValue();
				if (value == null) {
					UISelectItem item = (UISelectItem) child;
					list.add(new SelectItem(item.getItemValue(), item
							.getItemLabel(), item.getItemDescription(), item
							.isItemDisabled()));
				} else if (value instanceof SelectItem) {
					list.add((SelectItem) value);
				}
			} else if (child instanceof UISelectItems) {
				Object value = ((UISelectItems) child).getValue();
				if (value instanceof SelectItem)
					list.add((SelectItem) value);
				else if (value instanceof SelectItem[])
					list.addAll(Arrays.asList((SelectItem[]) value));
				else if (value instanceof Collection)
					list.addAll((Collection<SelectItem>) value); // unavoidable
				// warning
				else if (value instanceof Map) {
					for (Map.Entry<?, ?> entry : ((Map<?, ?>) value).entrySet())
						list.add(new SelectItem(entry.getKey(), ""
								+ entry.getValue()));
				}
			}
		}
		return list;
	}

	public static List<SelectChairItem> getSelectChairs(UIComponent component) {
		ArrayList<SelectChairItem> list = new ArrayList<SelectChairItem>();
		for (UIComponent child : component.getChildren()) {
			if (child instanceof UISelectChairItem) {
				Object value = ((UISelectChairItem) child).getValue();
				if (value == null) {
					UISelectChairItem item = (UISelectChairItem) child;
					list.add(new SelectChairItem(item.getItemValue(), item
							.getItemLabel(), item.isItemDisabled(),item.isItemFemale(), item
							.getCssClass(), item.getX(), item.getY(), item.getPassengerName()));
				} else if (value instanceof SelectChairItem) {
					list.add((SelectChairItem) value);
				}
			}
		}
		return list;
	}

	public static UISelectMany getSelectMany(FacesContext context,
			UIComponent component) {
		UISelectMany smc = null;
		for (UIComponent child : component.getChildren()){
			if (child instanceof UISelectMany){
				smc = (UISelectMany) child;
			}
		}
		return smc;
	}
}