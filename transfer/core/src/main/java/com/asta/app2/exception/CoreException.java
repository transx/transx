/* *Class created on [ Sep 7, 2008 | 12:56:33 PM ] */
package com.asta.app2.exception;

public class CoreException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public CoreException(String message) {
		super(message);
	}

	public CoreException(Throwable cause) {
		super(cause);
	}

	public CoreException() {
		super();
	}

}
