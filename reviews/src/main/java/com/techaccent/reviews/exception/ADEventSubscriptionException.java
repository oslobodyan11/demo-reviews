package com.techaccent.reviews.exception;

/**
 * ADEventSubscriptionException class is an exception for 
 * processing of subscription types. 
 * 
 * @author Oleksandr Slobodyan
 * @version 1.0
 * @since 2015-02-18
 */

public class ADEventSubscriptionException extends RuntimeException {

	private static final long serialVersionUID = -3104316657915337780L;

	public ADEventSubscriptionException(final String message) {
		super(message);
	}

	public ADEventSubscriptionException(final Throwable cause) {
		super(cause);
	}
}
