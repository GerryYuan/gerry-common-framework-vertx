package com.gerry.common.framework.vertx.exception;

import io.vertx.core.VertxException;

public class DataAccessException extends VertxException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1121991555218687445L;

	/**
	 * Create an instance given a message
	 *
	 * @param message
	 *            the message
	 */
	public DataAccessException(String message) {
		super(message);
	}

	/**
	 * Create an instance given a message and a cause
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Create an instance given a cause
	 *
	 * @param cause
	 *            the cause
	 */
	public DataAccessException(Throwable cause) {
		super(cause);
	}

}
