package com.techaccent.reviews.endpoint;

import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.scribe.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techaccent.reviews.exception.ADEventSubscriptionException;
import com.techaccent.reviews.model.ADEvent;
import com.techaccent.reviews.model.ADEventResponse;
import com.techaccent.reviews.service.IADSubscriptionService;
import com.techaccent.reviews.util.ADErrorCodes;

/**
 * ADSubscriptionManagementEndpoint class implements RESRFull web service
 * endpoint for subscription management requests.
 * 
 * @author Oleksandr Slobodyan
 * @version 1.0
 * @since 2015-02-18
 */

@RestController
@RequestMapping("/adendpoint")
public class ADSubscriptionManagementEndpoint extends ADBaseEndpoint {
	@Autowired
	private IADSubscriptionService adSubscriptionService;
	
	private static final Integer RESPONSE_CODE_SUCCESS = 200;
	
	private static Logger log = Logger
			.getLogger(ADBaseEndpoint.class.getName());

	@RequestMapping(value = "/subscribe", method = RequestMethod.GET, produces = "application/xml")
	@ResponseBody public ADEventResponse handleADEvent(
			@RequestParam final String eventURL,
			final HttpServletRequest request) {
		log.warning("EVENT URL: " + eventURL);

		// Verify event request against oauth1.0
		if (verifyAdRequest(request)) {
			Response adResponse = signADEventURL(eventURL).send();
			if (adResponse.getCode() != RESPONSE_CODE_SUCCESS) {
				log.warning("Unauthorized to access resource for EVENT URL: "
						+ eventURL);
				return adSubscriptionService.buildResponseFailure(
						ADErrorCodes.UNAUTHORIZED, "Authorization failure.");
			}
			Reader reader = new StringReader(adResponse.getBody());
			log.info(adResponse.getBody());
			ADEvent event = buildEvent(reader);
			try {
				adSubscriptionService.processAdEvent(event);
				return adSubscriptionService.buildResponseSuccess(event);
			} catch (ADEventSubscriptionException e) {
				log.warning("Unsupported subscription type : "
						+ event.getType());
				return adSubscriptionService.buildResponseFailure(
						ADErrorCodes.CONFIGURATION_ERROR, "Unsupported subscription type : "
								+ event.getType());
			}

		} else {
			log.warning("Unauthorized to access resource for EVENT URL "
					+ eventURL);
			return adSubscriptionService.buildResponseFailure(
					ADErrorCodes.UNAUTHORIZED, "Authorization failure.");
		}
	}
}
