package com.techaccent.reviews.service.impl;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.techaccent.reviews.exception.ADEventSubscriptionException;
import com.techaccent.reviews.model.ADEvent;
import com.techaccent.reviews.model.ADEventResponse;
import com.techaccent.reviews.service.IADSubscriptionService;

/**
 * ADSubscriptionService class implements functionality to parse the
 * subscription message in order to identify subscription event type and
 * generate well formatted xml response to appDirect.
 * 
 * @author Oleksandr Slobodyan
 * @version 1.0
 * @since 2015-02-18
 */

@Service
public class ADSubscriptionService implements IADSubscriptionService {
	private static Logger log = Logger.getLogger(ADSubscriptionService.class
			.getName());

	/**
	 * EventType enumeration exposes all appDirect subscription event types.
	 * 
	 */
	enum EventType {
		SUBSCRIPTION_ORDER, SUBSCRIPTION_CANCEL, SUBSCRIPTION_CHANGE, SUBSCRIPTION_NOTICE
	};

	@Override
	public void processAdEvent(final ADEvent event) {
		// TODO Auto-generated method stub
		EventType type = EventType.valueOf(event.getType());
		switch (type) {
		case SUBSCRIPTION_ORDER:
			logAdEvent(event);
			break;
		case SUBSCRIPTION_CANCEL:
			logAdEvent(event);
			break;
		case SUBSCRIPTION_CHANGE:
			logAdEvent(event);
			break;
		case SUBSCRIPTION_NOTICE:
			logAdEvent(event);
			break;
		default:
			throw new ADEventSubscriptionException("Unknown subscription type.");
		}
	}

	private void logAdEvent(final ADEvent event) {
		log.info("Event processed. Event type : " + event.getType());
	}

	public ADEventResponse buildResponseSuccess(final ADEvent event) {
		ADEventResponse result = new ADEventResponse();

		result.setSuccess(true);
		result.setAccountIdentifier("Dummy Account Identifier");
		result.setMessage("Subscription of event type " + event.getType()
				+ " : processed.");
		return result;
	}

	public ADEventResponse buildResponseFailure(final String errorCode,
			final String errorMsg) {
		ADEventResponse result = new ADEventResponse();

		result.setSuccess(false);
		result.setErrorCode(errorCode);
		result.setMessage(errorMsg);
		return result;
	}

}
