package com.asta.app2.webapp.util.selectitems;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import com.asta.app2.model.LabelValueGeneric;
import com.asta.app2.webapp.action.BasePage;

/**
 * 
 * @author Rene Guenther rene.guenther@innflow.com
 * 
 */
public class BaseSI extends BasePage {
	/**
	 * Class to compare LabelValues using their labels with locale-sensitive
	 * behaviour.
	 */
	public class LabelValueComparator implements Comparator<Object> {
		private Comparator<Object> c;

		/**
		 * Creates a new LabelValueComparator object.
		 * 
		 * @param locale
		 *            The Locale used for localized String comparison.
		 */
		public LabelValueComparator(Locale locale) {
			c = Collator.getInstance(locale);
		}

		/**
		 * Compares the localized labels of two LabelValues.
		 * 
		 * @param o1
		 *            The first LabelValue to compare.
		 * @param o2
		 *            The second LabelValue to compare.
		 * 
		 * @return The value returned by comparing the localized labels.
		 */
		public final int compare(Object o1, Object o2) {
			LabelValueGeneric lhs = (LabelValueGeneric) o1;
			LabelValueGeneric rhs = (LabelValueGeneric) o2;

			return c.compare(lhs.getLabel(), rhs.getLabel());
		}
	}
}
