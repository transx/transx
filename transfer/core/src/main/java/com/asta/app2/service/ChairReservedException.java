/* *Class created on [ Jun 30, 2008 | 3:22:53 PM ] */
package com.asta.app2.service;

/**
 * An exception that is thrown by classes wanting to trap manual unique
 * constraint violations on chair reservation !
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ChairReservedException extends Exception {
	private static final long serialVersionUID = 8485844271078769128L;

	public ChairReservedException(final String message) {
		super(message);
	}
}
