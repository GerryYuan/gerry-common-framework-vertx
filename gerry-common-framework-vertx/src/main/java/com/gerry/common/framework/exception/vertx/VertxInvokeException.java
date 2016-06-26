package com.gerry.common.framework.exception.vertx;

import io.vertx.core.VertxException;

public class VertxInvokeException extends VertxException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7890039123516527486L;

	/**
	 * Create an instance given a message
	 *
	 * @param message
	 *            the message
	 */
	public VertxInvokeException(String message) {
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
	public VertxInvokeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Create an instance given a cause
	 *
	 * @param cause
	 *            the cause
	 */
	public VertxInvokeException(Throwable cause) {
		super(cause);
	}

}
