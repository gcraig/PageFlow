package com.itsy.sparky.pageflow.events;

import com.itsy.sparky.pageflow.service.PageEventException;
import com.itsy.sparky.pageflow.service.PageResults;

/*
 * Add distinction of generic-page-type events, specific-x-type events
 */
public interface IPageEvent {

	public PageResults execute(PageContext context) throws PageEventException;
	
	public void resultsForward(String pageName);
	
	public void resultsAddErrorMessage(String key, String message);
	
	public void resultsAddWarningMessage(String key, String message);
	
	public void resultsAddValidationMessage(String key, String message);
	
	public final static String EVENT_OK = "OkContinue";
	
	public final static String EVENT_STOP = "OkContinue";
	
		
}
