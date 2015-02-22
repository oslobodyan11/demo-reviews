package com.techaccent.reviews.endpoint;

import java.io.Reader;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.techaccent.reviews.exception.ADEventBuildException;
import com.techaccent.reviews.model.ADEvent;
import com.techaccent.reviews.util.DummyScribe;

/**
 * OauthService class implements base functionality for subscription endpoints
 * including 2 legged OAuth support and subscription event response generation.
 * 
 * Since appDirect doesn't sign the request to subscription endpoint (doesn't
 * include secret headers oauth_consumer_key and oauth_signature to the request
 * header) method verifyAdRequest always returns true.
 * 
 * @author Oleksandr Slobodyan
 * @version 1.0
 * @since 2015-02-18
 */

public class ADBaseEndpoint {
	private static Logger log = Logger
			.getLogger(ADBaseEndpoint.class.getName());

	@Value("${oauth.key}")
	private String customerKey;

	@Value("${oauth.secret}")
	private String customerSecret;

	@Autowired
	private JAXBContext jaxbContext;
	/**
	 * This method checks the incoming http request for secret headers
	 * oauth_consumer_key and oauth_signature.
	 * 
	 * @param incoming
	 *            http request
	 * 
	 * @return boolean true if request comply with openID, false otherwise.
	 */
	public boolean verifyAdRequest(final HttpServletRequest request) {
		String headerConsumerKey = request.getHeader("oauth_consumer_key");
		String headerConsumerSecret = request.getHeader("oauth_signature");

		return true;

		/*
		 * if (headerConsumerKey != null && headerConsumerSecret != null &&
		 * headerConsumerKey.equals(CONSUMER_KEY) &&
		 * headerConsumerSecret.equals(CONSUMER_SECRET)) { return true; } else {
		 * return false; }
		 */
	}

	/**
	 * This method signs eventURL received from appDirect engine.
	 * 
	 * @param event
	 *            URL
	 * 
	 * @return signed request.
	 */
	public OAuthRequest signADEventURL(final String url) {
		log.info("Signing request with " + customerKey + ":" + customerSecret
				+ "to url " + url);
		OAuthService service = new ServiceBuilder().provider(DummyScribe.class)
				.apiKey(customerKey).apiSecret(customerSecret).build();
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		return request;
	}

	public ADEvent buildEvent(final Reader reader) {
		ADEvent event = null;
		try {
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			event = (ADEvent) jaxbUnmarshaller.unmarshal(reader);
		} catch (JAXBException ex) {
			throw new ADEventBuildException(ex);
		}
		return event;
	}

}
