package com.techaccent.reviews.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.OpenIDException;
import org.openid4java.association.AssociationException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.MessageException;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.FetchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techaccent.reviews.service.IOpenIdConsumerService;

/**
 * OpenIdConsumerService class is the implementation of IOpenIdConsumerService
 * interface.
 * 
 * @author Oleksandr Slobodyan
 * @version 1.0
 * @since 2015-02-18
 */
@Service
public class OpenIdConsumerService implements IOpenIdConsumerService {

	private static Logger log = Logger.getLogger(OpenIdConsumerService.class
			.getName());

	@Autowired
	private ConsumerManager consumerManager;

	private static final String OPEN_ID_DISC = "openid-disc";

	@Override
	public String authRequest(final String openId,
			final HttpServletRequest request, final HttpServletResponse response)
			throws DiscoveryException, MessageException, ConsumerException,
			IOException {
		String returnToUrl = request.getRequestURL().append("/verify")
				.toString();
		@SuppressWarnings("rawtypes")
		List discoveries = consumerManager.discover(openId);
		DiscoveryInformation discovered = consumerManager
				.associate(discoveries);
		request.getSession().setAttribute(OPEN_ID_DISC, discovered);
		AuthRequest authRequest = consumerManager.authenticate(discovered,
				returnToUrl);
		FetchRequest fetch = FetchRequest.createFetchRequest();
		authRequest.addExtension(fetch);
		response.sendRedirect(authRequest.getDestinationUrl(true));
		return null;
	}

	@Override
	public VerificationResult verifyResponse(final HttpServletRequest request)
			throws OpenIDException {
		VerificationResult verification = null;
		ParameterList openIdResponse = new ParameterList(
				request.getParameterMap());
		DiscoveryInformation discovered = (DiscoveryInformation) request
				.getSession().getAttribute(OPEN_ID_DISC);
		StringBuffer recievingUrl = request.getRequestURL();
		if (request.getQueryString() != null) {
			recievingUrl.append("?").append(request.getQueryString());
		}
		try {
			verification = consumerManager.verify(recievingUrl.toString(),
					openIdResponse, discovered);

		} catch (MessageException | DiscoveryException | AssociationException e) {
			log.log(Level.WARNING, "Exception when verifying openId response",
					e);
			throw new OpenIDException("Unable to verify response.");
		}
		return verification;
	}
}
