/*
 * Sparky Dynamic Workflow Engine
 * IT Synergistics, Inc.
 * Copyright (c) 2012, 2013
 */

package com.itsy.sparky.datatype;

public class UnitNumberCodabar extends UnitNumber {

    protected Object validationRegEx = "^[A-D][0-9\\+$:\\-/.]*[A-D]$";

}
