/*
 * Sparky Dynamic Workflow Engine
 * IT Synergistics, Inc.
 * Copyright (c) 2012, 2013
 */

package com.itsy.sparky.pageflow.validation;

import com.itsy.sparky.common.utils.ObjectUtils;

public class TextValidator extends BaseValidator {

	public ValidationResults validate() {
		Object value = new Object(); // this.getDataType().getValue();
		ValidationResults results = new ValidationResults();
		if (ObjectUtils.isEmpty(value) == false)
			results.setSuccessful();
		return results;
	}
}
