/*
 * Sparky Dynamic Workflow Engine
 * IT Synergistics, Inc.
 * Copyright (c) 2012, 2013
 */

package com.itsy.sparky.pageflow.validation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class ValidationResults implements Serializable {
	
	private Boolean successful = Boolean.FALSE;

	private Boolean errored = Boolean.FALSE;

	private Map<String, String> validationMessages = new HashMap<String, String>();

	public Map<String, String> getValidationMessages() {
		return validationMessages;
	}

	public void setValidationMessages(Map<String, String> validationMessages) {
		this.validationMessages = validationMessages;
	}

	public Boolean hasValidationMessages() {
		return validationMessages.size() > 0;
	}
	
	public void addValidationMessage(String key, String message) {
		this.validationMessages.put(key, message);
	}

	public void setSuccessful() {
		this.successful = true;
	}

	public void setErrored() {
		this.errored = true;
	}	
	
	public Boolean hasError() {
		return this.errored;
	}
	
	public Boolean isSuccessful() {
		return this.successful;
	}
}
