/* *Class created on [ Jun 2, 2008 | 2:14:16 PM ] */ 
package com.asta.app2.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

import com.asta.app2.Constants;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class BundleUtil {

	public static String getMessageBundle(String key) {
		Locale locale = LocaleContextHolder.getLocale();
		String returnValue;
		try {
			returnValue = ResourceBundle
					.getBundle(Constants.BUNDLE_KEY, locale).getString(key);
		} catch (MissingResourceException e) {
			returnValue = key;
		}
		return returnValue;
	}
}


