package com.techaccent.reviews.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.message.MessageException;

/**
 * IOpenIdConsumerService interface defines a contract for 
 * openId consumer implementation.
 *
 *
 * @author Oleksandr Slobodyan
 * @version 1.0
 * @since 2015-02-18
 */
public interface IOpenIdConsumerService {

	String authRequest(String openId, HttpServletRequest request,
			HttpServletResponse response) throws DiscoveryException,
			MessageException, ConsumerException, IOException;

	VerificationResult verifyResponse(HttpServletRequest request)
			throws OpenIDException;

}