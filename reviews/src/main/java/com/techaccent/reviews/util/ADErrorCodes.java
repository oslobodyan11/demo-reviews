package com.techaccent.reviews.util;

/**
 * ADErrorCodes is a utility class to
 * expose error codes are supported by appDirect. 
 * 
 * @author Oleksandr Slobodyan
 * @version 1.0
 * @since 2015-02-18
 */
public final class ADErrorCodes {
	public static final String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
	public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
	public static final String ACCOUNT_NOT_FOUND = "ACCOUNT_NOT_FOUND";
	public static final String MAX_USERS_REACHED = "MAX_USERS_REACHED";
	public static final String UNAUTHORIZED = "UNAUTHORIZED";
	public static final String OPERATION_CANCELED = "OPERATION_CANCELED";
	public static final String CONFIGURATION_ERROR = "CONFIGURATION_ERROR";
	public static final String INVALID_RESPONSE = "INVALID_RESPONSE";
	public static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";
	
	private ADErrorCodes() {
		
	}
}
