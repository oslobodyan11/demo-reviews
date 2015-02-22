package com.techaccent.reviews.util;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
* DummyScribe is a dummy class to call 
* Scribe  OAuthServiceBuilder to construct 
* signed request.
* 
* @author  Oleksandr Slobodyan
* @version 1.0
* @since   2015-02-18 
*/
public class DummyScribe extends DefaultApi10a {

	@Override
	public String getAccessTokenEndpoint() {
		return null;
	}

	@Override
	public String getAuthorizationUrl(final Token arg0) {
		return null;
	}

	@Override
	public String getRequestTokenEndpoint() {
		return null;
	}

}
