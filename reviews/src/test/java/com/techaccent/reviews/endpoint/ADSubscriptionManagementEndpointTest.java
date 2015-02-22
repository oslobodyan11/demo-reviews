package com.techaccent.reviews.endpoint;

import java.io.Reader;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;

import com.techaccent.reviews.exception.ADEventSubscriptionException;
import com.techaccent.reviews.model.ADEvent;
import com.techaccent.reviews.service.IADSubscriptionService;
import com.techaccent.reviews.util.ADErrorCodes;

@RunWith(MockitoJUnitRunner.class)
public class ADSubscriptionManagementEndpointTest {

	@Spy
	@InjectMocks
	private ADSubscriptionManagementEndpoint endpoint;

	@Mock
	private IADSubscriptionService adSubscriptionService;
	@Mock
	private HttpServletRequest request;
	@Mock
	private OAuthRequest oauthRequest;
	@Mock
	private Response adResponse;
	@Mock 
	ADEvent adEvent;

	@Test
	public void handleADEventTestValidationFalse() {
		Mockito.doReturn(false).when(endpoint).verifyAdRequest(Mockito.any(HttpServletRequest.class));

		endpoint.handleADEvent("", request);

		Mockito.verify(adSubscriptionService).buildResponseFailure(ADErrorCodes.UNAUTHORIZED, "Authorization failure.");
	}

	@Test
	public void handleADEventTestValidationTrueErrorCode400() {	
		Mockito.doReturn(true).when(endpoint).verifyAdRequest(Mockito.any(HttpServletRequest.class));
		Mockito.doReturn(oauthRequest).when(endpoint).signADEventURL(Mockito.anyString());
		Mockito.when(oauthRequest.send()).thenReturn(adResponse);
		Mockito.when(adResponse.getCode()).thenReturn(400);

		endpoint.handleADEvent("", request);

		Mockito.verify(adSubscriptionService).buildResponseFailure(
				ADErrorCodes.UNAUTHORIZED, "Authorization failure.");
	}

	@Test
	public void handleADEventTestValidationTrueErrorCode200() {
		Mockito.doReturn(true).when(endpoint)
				.verifyAdRequest(Mockito.any(HttpServletRequest.class));
		Mockito.doReturn(oauthRequest).when(endpoint).signADEventURL(Mockito.anyString());
		Mockito.when(oauthRequest.send()).thenReturn(adResponse);
		Mockito.when(adResponse.getCode()).thenReturn(200);
		Mockito.when(adResponse.getBody()).thenReturn("<event></event>");		
		Mockito.doReturn(adEvent).when(endpoint).buildEvent(Mockito.any(Reader.class));
	
		endpoint.handleADEvent("", request);

		Mockito.verify(adSubscriptionService).buildResponseSuccess(adEvent);
	}
	
	@Test
	public void handleADEventTestValidationTrueErrorCode200InvlalidSubstciption() {
		Mockito.doReturn(true).when(endpoint)
				.verifyAdRequest(Mockito.any(HttpServletRequest.class));
		Mockito.doReturn(oauthRequest).when(endpoint).signADEventURL(Mockito.anyString());
		Mockito.when(oauthRequest.send()).thenReturn(adResponse);
		Mockito.when(adResponse.getCode()).thenReturn(200);
		Mockito.when(adResponse.getBody()).thenReturn("<event></event>");		
		Mockito.doReturn(adEvent).when(endpoint).buildEvent(Mockito.any(Reader.class));
		Mockito.doThrow(ADEventSubscriptionException.class).
				when(adSubscriptionService).processAdEvent(Mockito.any(ADEvent.class));
		Mockito.when(adEvent.getType()).thenReturn("INVALID_TYPE");
		
		endpoint.handleADEvent("", request);

		Mockito.verify(adSubscriptionService).buildResponseFailure(ADErrorCodes.CONFIGURATION_ERROR, 
				"Unsupported subscription type : INVALID_TYPE");
	}
}
