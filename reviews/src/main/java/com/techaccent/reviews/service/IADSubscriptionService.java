package com.techaccent.reviews.service;

import com.techaccent.reviews.model.ADEvent;
import com.techaccent.reviews.model.ADEventResponse;

/**
* IADSubscriptionService interface defines 
* a contract for SubscriptionService implementation.
*
*
* @author  Oleksandr Slobodyan
* @version 1.0
* @since   2015-02-18 
*/
public interface IADSubscriptionService {
	void processAdEvent(ADEvent event);
	ADEventResponse buildResponseSuccess(ADEvent event);
	ADEventResponse buildResponseFailure(String errorCode, String errorMsg);
}
