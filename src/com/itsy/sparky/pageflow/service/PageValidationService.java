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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsy.sparky.pageflow.dao.EventsDAO;
import com.itsy.sparky.pageflow.events.PageContext;
import com.itsy.sparky.pageflow.model.Step;
import com.itsy.sparky.pageflow.validation.BaseValidator;
import com.itsy.sparky.pageflow.validation.ValidationResults;

/**
 * Validation Service responsible to for returning error, and warning messages
 * for a given form's values and any custom checks, prior to returning a page.
 */

@Service
@SuppressWarnings("rawtypes")
public class PageValidationService extends AbstractService {

	static Log log = LogFactory.getLog(PageValidationService.class);
	
	@Autowired
	EventsDAO eventsDAO;
	
	public ValidationResults executeValidatorByEventName(String eventName)
			throws PageValidationException {
		//MetaEvent event = eventsDAO.getEventByName(name);
		log.info(String.format("Executing validator for event: [%s]: ", eventName));
		return executePageValidator(eventName);
	}
	
	private ValidationResults executePageValidator(String eventName)
			throws PageValidationException {

		/* conventions: IInterface, no checking for nulls, handle exceptions,
		   page services manage flow and handle forwards */
		
		if (StringUtils.isNotEmpty(eventName)) {
			
			// log.info(String.format("Instantiating page validator: [%s]: ", validatorName));
	
			try {
	
				BaseValidator validator = (BaseValidator) instantiateValidator(eventName);
				
				if (null != validator) {
					log.info(String.format("Validator: [%s]: ", validator));
					return validator.doValidate(getPageContext(), this.httpSession, this.httpRequest);
				}
	
			} catch (java.lang.ClassNotFoundException e) {
				log.info(String.format("Validator not found"));
			} catch (InstantiationException e) {
				log.info(String.format("Validator instantiation error."));
			} catch (IllegalAccessException e) {
				log.info(String.format("Validator illegal access error."));
			}
	
/*			throw new PageValidationException(
					String.format("Error processing Validator for Event: [%s]", eventName));
*/		
		}
		
		ValidationResults validationResults = new ValidationResults();
		validationResults.setSuccessful();
		
		return validationResults;
	}
		
	public PageContext getPageContext() {
		return pageContext;
	}

	public void setPageContext(Step step) {
		this.pageContext = new PageContext();
		this.pageContext.generatePageContext(step);
	}

	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}
	
	protected PageContext pageContext;	
}
