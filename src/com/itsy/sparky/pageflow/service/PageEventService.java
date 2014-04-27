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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsy.sparky.common.utils.Log;
import com.itsy.sparky.pageflow.dao.EventsDAO;
import com.itsy.sparky.pageflow.events.PageContext;
import com.itsy.sparky.pageflow.events.PageEvent;
import com.itsy.sparky.pageflow.model.MetaEvent;
import com.itsy.sparky.pageflow.model.Step;

/**
 * PageEvent Service responsible for executing events (by page, name, and id).
 */

@Service
@SuppressWarnings({ "rawtypes" })
public class PageEventService extends AbstractService {

	@Autowired
	EventsDAO eventsDAO;
	
	public List<MetaEvent> getEventsList() {
		return eventsDAO.getEventsList();
	}

	/*
	 * public void executePageEvents(Step step) { }
	 */

	public PageResults executeEventByName(String eventName)
			throws PageEventException {
		//MetaEvent event = eventsDAO.getEventByName(name);
		Log.info(this, String.format("Executing event: [%s]: ", eventName));
		return executePageEvent(eventName);
	}

/*	public PageResults executeEventById(Integer id)
			throws PageEventException {
		MetaEvent event = eventsDAO.getEventById(id);
		Log.info(this, String.format("Executing event: [%s]: ", event));
		return executePageEvent(event.getEventClassName());
	}*/
	
	private PageResults executePageEvent(String eventName)
			throws PageEventException {

		/* conventions: IInterface, no checking for nulls, handle exceptions,
		   page services manage flow and handle forwards */
		
		Log.info(this, String.format("Instantiating event: [%s]: ", eventName));

		try {

			PageEvent pageEvent = (PageEvent) instantiateEvent(eventName);
			
			Log.info(this, String.format("PageEvent: [%s]: ", pageEvent));
			
			return pageEvent.doExecute(getPageContext(), this.httpSession, this.httpRequest);

		} catch (java.lang.ClassNotFoundException e) {
			Log.info(this, String.format("PageEvent not found"));
		} catch (InstantiationException e) {
			Log.info(this, String.format("PageEvent instantiation error."));
		} catch (IllegalAccessException e) {
			Log.info(this, String.format("PageEvent illegal access error."));
		}

		throw new PageEventException(
				String.format("Error processing PageEvent: [%s]", eventName));
	}
	
	public PageContext getPageContext() {
		return pageContext;
	}

	public void setPageContext(Step step) {
		this.pageContext = new PageContext();
		this.pageContext.generatePageContext(step);
	}
		
	protected PageContext pageContext;

}
