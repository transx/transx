/* *Class created on [ Jul 24, 2008 | 10:37:46 AM ] */
package com.asta.app2.service;

/**
 * An exception that is thrown by classes wanting to register on ticket twice .
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class TicketDidNotIssuedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for TicketIssuedException.
	 * 
	 * @param message exception message
	 */
	public TicketDidNotIssuedException(final String message) {
		super(message);
	}
}
