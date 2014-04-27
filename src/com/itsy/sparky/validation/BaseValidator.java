/*
 * Sparky Dynamic Workflow Engine
 * IT Synergistics, Inc.
 * Copyright (c) 2012, 2013
 */

package com.itsy.sparky.validation;

import static com.itsy.sparky.common.utils.ObjectUtils.isEmpty;

import com.itsy.sparky.datatype.DataType;

public class BaseValidator implements Validator {

	protected DataType dataType;

	public ValidationResults validate() {
		Object value = this.getDataType().getValue();
		ValidationResults results = new ValidationResults();
		results.setIsSatisfied(isEmpty(value) == false);
		return results;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
}
