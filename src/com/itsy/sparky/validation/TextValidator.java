/*
 * Sparky Dynamic Workflow Engine
 * IT Synergistics, Inc.
 * Copyright (c) 2012, 2013
 */

package com.itsy.sparky.validation;

import com.itsy.sparky.common.utils.ObjectUtils;

public class TextValidator extends BaseValidator {

	public ValidationResults validate() {
		Object value = this.getDataType().getValue();
		ValidationResults results = new ValidationResults();
		results.setIsSatisfied(ObjectUtils.isEmpty(value) == false);
		return results;
	}
}
