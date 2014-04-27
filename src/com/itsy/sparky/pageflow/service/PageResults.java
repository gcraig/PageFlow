/*******************************************************************************
 * Sparky Dynamic Workflow Engine
 * 
 * NOTICE:
 * This software is the confidential and proprietary information 
 * ("CONFIDENTIAL INFORMATION") of IT Synergistics, LLC. You shall not 
 * disclose such Confidential Information and shall use it only in 
 * accordance with the terms of any license, employment, or other 
 * agreement(s) you entered into with IT Synergistics, LLC.
 * 
 * Copyright (c) 2011-2013
 * IT Synergistics, LLC
 * 115 Tree St., Flowood, MS 39232 USA
 * All Rights Reserved
 ******************************************************************************/
package com.itsy.sparky.pageflow.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.itsy.sparky.pageflow.validation.ValidationResults;

/**
 * Page results returned after executing a PageEvent; responsible for 
 * displaying messages and managing page flow.
 */

@SuppressWarnings("serial")
public class PageResults implements Serializable {

	private String forward;

	private Boolean successful = Boolean.FALSE;

	private Boolean errored = Boolean.FALSE;

	private String successMessage;

	private Map<String, String> errorMessages = new HashMap<String, String>();

	private Map<String, String> warningMessages = new HashMap<String, String>();

	private String pageContent;

	private String gotoPage;
	
	private ValidationResults validationResults;

	public String getForward() {
		return forward;
	}

	public PageResults setForward(String pageName) {
		this.forward = pageName;
		return this;
	}

	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public Map<String, String> getWarningMessages() {
		return warningMessages;
	}

	public void setWarningMessages(Map<String, String> warningMessages) {
		this.warningMessages = warningMessages;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public Boolean hasErrors() {
		return errorMessages.size() > 0;
	}

	public Boolean hasWarnings() {
		return warningMessages.size() > 0;
	}

	public Boolean isSuccessful() {
		return (successful || null != successMessage);
	}

	public Boolean hasErrored() {
		return (errored || errorMessages.size() > 0);
	}

	public Boolean hasForward() {
		return (null != forward);
	}

	public Boolean hasGotoPage() {
		return (null != this.gotoPage);
	}
	
	public void setSuccessful() {
		this.successful = true;
	}

	public void setErrored() {
		this.errored = true;
	}

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	public String getGotoPage() {
		return gotoPage;
	}

	public PageResults setGotoPage(String gotoPage) {
		this.gotoPage = gotoPage;
		return this;
	}

	public void setValidationResults(ValidationResults validationResults) {
		this.validationResults = validationResults;		
	}
	
	public ValidationResults getValidationResults() {
		return this.validationResults;
	}
	
	@Override
	public String toString() {
		return "PageResults [forward=" + forward + ", successful=" + successful
				+ ", errored=" + errored + "]";
	}	
}
