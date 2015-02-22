package com.techaccent.reviews.exception;

/**
 * ADEventBuildException class is an exception for 
 * unmarshalling of appDirect response. 
 * 
 * @author Oleksandr Slobodyan
 * @version 1.0
 * @since 2015-02-18
 */

public class ADEventBuildException extends RuntimeException {

	private static final long serialVersionUID = -7925782208805344987L;

	public ADEventBuildException(final Throwable cause) {
		super(cause);
	}
}
