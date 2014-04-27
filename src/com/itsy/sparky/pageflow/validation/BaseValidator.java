/*
 * Sparky Dynamic Workflow Engine
 * IT Synergistics, Inc.
 * Copyright (c) 2012, 2013
 */

package com.itsy.sparky.pageflow.validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.itsy.sparky.pageflow.controller.HashMapCommand;
import com.itsy.sparky.pageflow.events.PageContext;

// FIXME: Validators and Events should collaborate and share behavior
public class BaseValidator implements IValidator {

	protected final ValidationResults VALIDATE_SUCCESS = new ValidationResults();
	
	protected final ValidationResults VALIDATE_ERROR = new ValidationResults();

	public BaseValidator() {
		VALIDATE_SUCCESS.setSuccessful();
		VALIDATE_ERROR.setErrored();
	}
	
	public ValidationResults doValidate(
			PageContext pageContext, 
			HttpSession httpSession,
			HttpServletRequest httpRequest) {

		/* inject the http session and request parameters */

		pageContext.setHttpSession(httpSession);
		pageContext.setHttpRequest(httpRequest);

		return validate(pageContext);
	}
	
	/*
	 * Convenience methods for data property manipulation; retrieving from page,
	 * and storing/retrieving from session.
	 */
	
	/**
	 * Retrieve the Http Session associated with the PageContext
	 * @param context
	 * @return
	 */
	protected HttpSession getHttpSession(PageContext context) {
		Map<String, Object> httpValues = context.getHttpValues();
		HttpSession httpSession = (HttpSession) httpValues.get("httpSession");
		return httpSession;
	}
	
	/**
	 * Save a value in the http session.
	 * @param context
	 * @param key
	 * @param value
	 */
	protected final void saveSessionValue(PageContext context, String key, Object value) {
		Map<String, Object> httpValues = context.getHttpValues();
		HttpSession httpSession = (HttpSession) httpValues.get("httpSession");
		httpSession.setAttribute(key, value);
	}
	
	/**
	 * Retrieve a value on the page, via the http request.
	 * @param context
	 * @param key
	 * @return
	 */
	protected final String getPropertyValue(PageContext context, String key) {
		HashMapCommand command = (HashMapCommand) context.getCommand();
		return command.getProperties().get(key).toString();
	}
	
	/**
	 * Retrieve a value in the http session.
	 * @param context
	 * @param key
	 * @return
	 */
	protected final String getSessionValue(PageContext context, String key) {
		Map<String, Object> httpValues = context.getHttpValues();
		HttpSession httpSession = (HttpSession) httpValues.get("httpSession");
		return httpSession.getAttribute(key).toString();
	}	

	/*
	 * Convenience hooks, to allow subclasses to override only necessary
	 * functionality.
	 */


	@Override
	public ValidationResults validate(PageContext context) {
		return null;
	}

	@Override
	public void resultsAddValidationMessage(String key, String message) {		
	}

	/*
	protected DataType dataType;

	public ValidationResults validate() {
		Object value = this.getDataType().getValue();
		ValidationResults results = new ValidationResults();
		results.setIsSatisfied(isEmpty(value) == false);
		return results;
	}
	*/
}
