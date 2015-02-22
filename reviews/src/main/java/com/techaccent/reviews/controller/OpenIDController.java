package com.techaccent.reviews.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techaccent.reviews.service.IOpenIdConsumerService;
import com.techaccent.reviews.util.CommonConst;

/**
* OpenIDController class enables 
* openId authentication for the DEMO.
*
*
* @author  Oleksandr Slobodyan
* @version 1.0
* @since   2015-02-18 
*/

@RestController
@RequestMapping("/login")
public class OpenIDController {
	@Autowired
	private ServletContext context;
	
	@Autowired
	private IOpenIdConsumerService consumerService;
	
	private static Logger log = Logger.getLogger(OpenIDController.class
			.getName());
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public void redirectOpenID(@RequestParam final String openId, final HttpServletRequest request,
			final HttpServletResponse response) {
		log.info("Received request from AppDirect to log in :" + openId);
		try {
			consumerService.authRequest(openId, request, response);
		} catch (DiscoveryException | MessageException | ConsumerException
				| IOException e) {
			log.log(Level.SEVERE, "Exception when authenticating via controller from AppDirect");
		}
	}

	@RequestMapping(value = "/redirect/verify", method = RequestMethod.GET)
	public void verifyResponse(final HttpServletRequest request, final HttpServletResponse response) {
		try {
			VerificationResult verification = consumerService.verifyResponse(request);

			// examine the verification result and extract the verified
			// identifier
			Identifier verified = verification.getVerifiedId();
			if (verified != null) {
				request.getSession().setAttribute(CommonConst.AUTHANTICATED, true);
				response.sendRedirect("/reviews.html");
			} else {
				request.getSession().setAttribute(CommonConst.AUTHANTICATED, false);
				response.sendRedirect("/index.html");
			}

		} catch (OpenIDException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
