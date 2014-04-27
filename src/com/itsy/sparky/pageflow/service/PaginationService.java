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
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsy.sparky.pageflow.events.Event;
import com.itsy.sparky.pageflow.events.PageContext;
import com.itsy.sparky.pageflow.model.Sequence;
import com.itsy.sparky.pageflow.model.Step;
import com.itsy.sparky.pageflow.validation.ValidationResults;

/**
 * Pagination Service responsible for walking the Sequence, executing events, 
 * returning Pages, and presenting Page and Validation results.
 */

@Service
@SuppressWarnings({ "rawtypes" })
public class PaginationService extends AbstractService {

	static Logger log = Logger.getLogger(PaginationService.class);
	
	@Autowired
	EntityService entityService;

	@Autowired
	PageContentService contentService;

	@Autowired
	PageEventService eventService;

	@Autowired
	PageValidationService validationService;

	public PaginationService() {
	}

	// FIXME: thread-safe?
	private Sequence sequence;
	private Step currentStep;
	private List<Step> steps;

	public final void init(Integer sequenceId) {
		this.sequence = entityService.getSequenceById(sequenceId);
		this.steps = this.sequence.getSteps();
		this.currentStep = this.steps.get(0);
		this.isIntialized = Boolean.TRUE;
	}

	public final void init(String sequenceName) {
		this.sequence = entityService.getSequenceByName(sequenceName);
		this.steps = this.sequence.getSteps();
		this.currentStep = this.steps.get(0);
		this.isIntialized = Boolean.TRUE;
	}

	public List<Step> getSteps() {
		return this.steps;
	}

	public Step getCurrentStep() {
		return this.currentStep;
	}

	/**
	 * Return a Step by name in the current Sequence.
	 * 
	 * @param stepName
	 * @return
	 */
	public Step getStep(String stepName) {

		List<Step> allSteps = getSteps();

		for (int i = 0; i < allSteps.size() - 1; i++) {
			Step s = allSteps.get(i);
			if (s.getStepName().equals(stepName)) {
				this.currentStep = s;
				return s;
			}
		}

		return null;
	}

	/**
	 * Returns the next Step in the Sequence. Does not pre-process any Page
	 * events.
	 * 
	 * @param fromStep
	 * @return
	 * @throws PageEventException
	 */
	public final Step getNextStep(Step fromStep) throws PageEventException {

		if (null == fromStep)
			return getStartStep();

		List<Step> allSteps = getSteps();

		int ndx = allSteps.indexOf(fromStep);

		if (ndx == -1) // not found
			return fromStep;

		int nextndx = ndx + 1;

		if (nextndx >= allSteps.size() - 1) // out of bounds
			throw new NoSuchElementException("No more Steps in the current Sequence");

		Step nextStep = allSteps.get(nextndx);

		this.currentStep = nextStep;

		return nextStep;
	}

	/**
	 * Return the next Step in the Sequence FIXME: instead of passing in
	 * fromStep reference, provide auto reference currentStep.
	 * 
	 * @param fromStep
	 * @param event
	 * @return
	 * @throws PageEventException
	 * @throws PageValidationException
	 */
	public final PageResults getNextPage(
			Step fromStep, String event, HttpSession session, HttpServletRequest request,
				Object command) throws PageEventException, PageValidationException {

		Step formInputStep = fromStep;
		String pageContent = "Page content errror.";
		String gotoPage = StringUtils.EMPTY;
		StringBuilder logMessage = new StringBuilder();
		
		/*
		 * Execute the current page's event. Pass the http session, request
		 * variables in via a PageContext
		 */
		PageResults pageResults = executeEvent(event, session, request, command);
		logMessage.append("Event {");
		logMessage.append(event);
		logMessage.append("}");		
		
		if (pageResults.hasGotoPage()) {
			gotoPage = pageResults.getGotoPage();
			fromStep = getStep(gotoPage);
			logMessage.append(" --> Page [");
			logMessage.append(gotoPage);
			logMessage.append("]");
		}

		/*
		 * Execute the current page's validator.
		 */
		ValidationResults validationResults =
				executeValidator(event, session, request, command,
						eventService.getPageContext());
		
		// if (validationResults)
		logMessage.append(" --> Validator <");
		logMessage.append("");
		logMessage.append(">");
		//} 
		
		if (validationResults.hasError()) {
			fromStep = formInputStep;
			logMessage.append(" *ERR* ");
		}

		log.info(logMessage);
		
		/*
		 * Capture the validation messages for display through one object.
		 */
		pageResults.setValidationResults(validationResults);

		/*
		 * Get a pointer to the next Step (Page) in the Sequence.
		 */
		Step nextStep = null;
		
		if (pageResults.hasGotoPage()) {
			nextStep = fromStep;
		} else {
			nextStep = getNextStep(fromStep);	
		}
		
		/*
		 * If any errors, warnings, or validation messages are present, then
		 * remain on the same page for the user to correct.
		 */
		if (	pageResults.hasErrored()		||
				pageResults.hasWarnings()		||
				validationResults.hasError()	|| 
				validationResults.hasValidationMessages()) {

			nextStep = fromStep;
		}

		this.currentStep = nextStep;

		/*
		 * Get the rendered page, merged content with variable data.
		 */
		pageContent = contentService.getPage(nextStep, eventService.getPageContext());

		pageResults.setPageContent(pageContent);

		return pageResults;
	}

	/**
	 * Return the next Page (Step) in the Sequence. 
	 * 
	 * @param pageName
	 * @param event
	 * @return
	 * @throws PageEventException
	 * @throws PageValidationException
	 */

	public final PageResults getNextPage(String pageName, String event,
			HttpSession session, HttpServletRequest request, Object command)
			throws PageEventException, PageValidationException {
		Step step = entityService.getStepByName(pageName);
		return getNextPage(step, event, session, request, command);
	}

	/**
	 * Return the first Step in a Sequence.
	 * 
	 * @return
	 */
	public final Step getStartStep() {

		List<Step> allSteps = getSteps();

		Step startStep = null;

		if (allSteps.size() > 0)
			startStep = allSteps.get(0);

		this.currentStep = startStep;

		return startStep;
	}

	/**
	 * Return the first Page (by event) in a Sequence of Steps.
	 * 
	 * @param event
	 * @param session
	 * @param request
	 * @param command
	 * @return
	 * @throws PageEventException
	 */
	public final String getStartPage(Event event, HttpSession session,
			HttpServletRequest request, Object command) throws PageEventException {

		return getStartPage(event.value(), session, request, command);
	}

	/**
	 * Return the first Page (by event name) in a Sequence of Steps.
	 * 
	 * @param event
	 * @param session
	 * @param request
	 * @param command
	 * @return
	 * @throws PageEventException
	 */	
	public final String getStartPage(String eventName, HttpSession session,
			HttpServletRequest request, Object command) throws PageEventException {

		String pageContent = "Page content errror.";

		executeEvent(eventName, session, request, command);

		Step startStep = getStartStep();

		pageContent =
				contentService.getPage(startStep, eventService.getPageContext());

		this.currentStep = startStep;

		return pageContent;
	}
	
	/**
	 * Returns the specified Page for the given Step. Does not pre-process any
	 * Page events.
	 */
	public String getPage() {
		return contentService
				.getPage(getCurrentStep(), eventService.getPageContext());
	}

	/*
	 * Execute an event (by name), passing in the PageContext (Http variables and form
	 * data).
	 */
	public PageResults executeEvent(String eventName, HttpSession session,
			HttpServletRequest request, Object command) throws PageEventException {

		PageResults pageResults = null;

		if (StringUtils.isNotEmpty(eventName)) {

			/*
			 * Obtain the current page context.
			 */
			createPageContext(session, request, command); // XXX
			//validationService.setPageContext(eventService.getPageContext());
			
			/*
			 * Execute the current page's event, by step name.
			 */
			pageResults = eventService.executeEventByName(eventName);

			/*
			 * Developer events must all return some form of results
			 */
			if (null == pageResults)
				throw new PageEventException(String.format(
						"PageResults for event %s cannot be null.", eventName));
		} else {

			pageResults = new PageResults();
			pageResults.setSuccessful();
		}

		return pageResults;

	}
	
	/*
	 * Execute an event (by event), passing in the PageContext (Http variables and form
	 * data).
	 */
/*	private PageResults executeEvent(Event event, HttpSession session,
			HttpServletRequest request, Object command) throws PageEventException {

		return executeEvent(event.value(), session, request, command);
	}*/

	/*
	 * Create a PageContext for a given request.
	 */
	@SuppressWarnings("unchecked")
	private void createPageContext(HttpSession session, HttpServletRequest request,
			Object command) {
		
		/*
		 * Create the page context.
		 */
		
		this.httpSession = session;
		this.httpRequest = request;

		eventService.setPageContext(getCurrentStep());
		eventService.setHttpSession(session);
		eventService.setHttpRequest(request);

		/* FIXME: This needs to be moved! */
		eventService.getPageContext().setCommand(command);
		
	}

	/*
	 * Execute a Page validator and return the results of checking
	 * the datatype and form values.
	 */
	private ValidationResults executeValidator(String eventName, HttpSession session,
			HttpServletRequest request, Object command, PageContext pageContext)
			throws PageValidationException {

		ValidationResults validationResults = null;

		if (StringUtils.isNotEmpty(eventName)) {

			// String eventName = event.name();
			
			validationService.setPageContext(eventService.getPageContext());

			/*
			 * Execute the current page's validator, by step event name.
			 */
			validationResults = validationService.executeValidatorByEventName(eventName);

			/*
			 * Validators must all return some form of results
			 */
			if (null == validationResults)
				throw new PageValidationException(String.format(
						"ValidationResults for validator %s cannot be null.", eventName));
		} else {

			validationResults = new ValidationResults();
			validationResults.setSuccessful();
		}

		return validationResults;
	}

	private Boolean isIntialized = Boolean.FALSE;

	public void initialized(boolean b) {
		isIntialized = b;
	}

	public Boolean isIntialized() {
		return isIntialized;
	}

}
