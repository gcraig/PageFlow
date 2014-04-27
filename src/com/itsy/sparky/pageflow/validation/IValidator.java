/*
 * Sparky Dynamic Workflow Engine
 * IT Synergistics, Inc.
 * Copyright (c) 2012, 2013
 */

package com.itsy.sparky.pageflow.validation;

import com.itsy.sparky.pageflow.events.PageContext;

/**
 * Validator interface is responsible for simple "encapsulated" internal check
 * if the value object is correct or not.
 */
public interface IValidator {

	public abstract ValidationResults validate(PageContext context);
	
	public void resultsAddValidationMessage(String key, String message);
	
	/*
	public abstract void setDataType(DataType dataType);

	public abstract DataType getDataType();
	*/	
}
