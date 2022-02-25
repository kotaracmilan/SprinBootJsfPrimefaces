package com.dajo.proj.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class WebServerCustomization implements ErrorPageRegistrar{

	/**
	 * This class registers specific pages that are directed in certan HTTP responses
	 * Change if necessary, or create those pages inside /src/main/webapp directory
	 */
	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		registry.addErrorPages(
	    		  new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.xhtml"),
	    		  new ErrorPage(HttpStatus.BAD_REQUEST, "/error.xhtml"),
	    		  new ErrorPage(HttpStatus.UNAUTHORIZED, "/access.xhtml"),
	    		  new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error.xhtml")
	    		  );
		
	}

}
