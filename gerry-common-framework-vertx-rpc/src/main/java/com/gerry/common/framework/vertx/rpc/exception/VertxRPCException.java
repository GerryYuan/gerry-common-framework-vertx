package com.gerry.common.framework.vertx.rpc.exception;

import io.vertx.core.VertxException;

public class VertxRPCException extends VertxException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3076086443718918427L;

	/**
	 * Create an instance given a message
	 *
	 * @param message
	 *            the message
	 */
	public VertxRPCException(String message) {
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
	public VertxRPCException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Create an instance given a cause
	 *
	 * @param cause
	 *            the cause
	 */
	public VertxRPCException(Throwable cause) {
		super(cause);
	}

}
