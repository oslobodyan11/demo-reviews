package com.techaccent.reviews.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techaccent.reviews.util.CommonConst;

import java.io.IOException;

/**
* OpenIdSecurityFilter class implements
* openId protection for DEMO application
* backend. Filter checks each request
* to the application backend for special session
* attribute CommonConst.AUTHANTICATED which is set up
* when openId authorization is done.
* 
* @author  Oleksandr Slobodyan
* @version 1.0
* @since   2015-02-18 
*/

public class OpenIdSecurityFilter implements Filter {

	public void doFilter(final ServletRequest request, final ServletResponse response,
			final FilterChain filterChain) throws IOException, ServletException {
		Object sessionAuth = ((HttpServletRequest) request).getSession()
				.getAttribute(CommonConst.AUTHANTICATED);
		if (sessionAuth != null && (Boolean) sessionAuth) {
			filterChain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect("/index.html");
		}
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(final FilterConfig arg0) throws ServletException {
		
	}

}
