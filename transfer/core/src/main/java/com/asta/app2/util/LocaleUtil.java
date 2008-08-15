package com.asta.app2.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocaleUtil {

	public static final String LOCALE_KEY = "locale_key";

	public static final String DIRECTION_KEY = "direction";

	public static final String BUNDLE_KEY = "msg";

	public static final String BUNDLE_NAME = "ApplicationResources";

	public static final String DEFAULT_ENCODING = "UTF-8";

	public static final String RIGHT_TEXTALIGN_KEY = "rightAlign";

	public static final String LEFT_TEXTALIGN_KEY = "leftAlign";

	public static final String IS_LOCALE_FARSI = "isLocaleFarsi";
	
	private static ThreadLocal<Locale> locale = new ThreadLocal<Locale>();

	private static Map<Locale, ResourceBundle> rbCache = new HashMap<Locale, ResourceBundle>();

	public static void setLocale(Locale newLocale) {
//		Log.debug("setting new locale: %s" ,newLocale);
		if (newLocale != null) {
			locale.set(newLocale);
		}
	}

	public static Locale getLocale() {
		if (locale.get() == null) {
			setLocale(new Locale("fa"));	
		}
		return locale.get();
	}

	public static boolean isLocaleFarsi() {
		return getLocale() == null ? false : getLocale().getLanguage()
				.toLowerCase().startsWith("fa");
	}

	public static ResourceBundle getBundle() {
		ResourceBundle rb = rbCache.get(getLocale());
		if (rb == null) {
			rbCache.put(getLocale(), rb = ResourceBundle.getBundle(
					getBundleName(), getLocale()));
//			Log.debug("Bundle loaded, locale:%s",rb.getLocale());
		}
		return rb;
	}

	public static String getBundleName() {
		return BUNDLE_NAME;
	}

	public static String getText(String key) {
		String res = getTextWithDefault(key, null);
		if (res == null)
			return malformedKey(key);
		else
			return res;
	}
	
	public static String getTextWithDefault(String key, String defaultValue) {
		String message;
		try {
			message = getBundle().getString(key);
			return message;
		} catch (java.util.MissingResourceException mre) {
			return defaultValue;
		}
	}


	private static String malformedKey(String key) {
		return "" + key + "";
	}

	public static String getText(String key, Object... arg) {
		try {
			if (arg == null || arg.length == 0) {
				return getText(key);
			}
		} catch (MissingResourceException e) {
			return malformedKey(key);
		}

		String pattern = null;
		try {
			pattern = getText(key);
		} catch (MissingResourceException e) {
			return malformedKey(key);
		}
		return new MessageFormat(pattern, getLocale()).format(arg,
				new StringBuffer(), null).toString();
	}

	public static String getDirection() {
		return isLocaleFarsi() ? "rtl" : "ltr";
	}
	
	public static String getLeftTextAlign() {
		return isLocaleFarsi() ? "right" : "left";
	}

	public static String getRightTextAlign() {
		return isLocaleFarsi() ? "left" : "right";
	}

}
