/*
 * Sparky Dynamic Workflow Engine
 * IT Synergistics, Inc.
 * Copyright (c) 2012, 2013
 */

package com.itsy.sparky.validation;

import com.itsy.sparky.datatype.DataType;

/**
 * Validator interface is responsible for simple "encapsulated" internal check
 * if the value object is correct or not.
 */
public interface Validator {

    public abstract ValidationResults validate();

	public abstract void setDataType(DataType dataType);

	public abstract DataType getDataType();
}
