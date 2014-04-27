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
package com.itsy.sparky.pageflow.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("serial")
public class BaseEntity implements Serializable {

	final protected String defaultString(String s) {
		if (s == null)
			return StringUtils.EMPTY;
		else
			return s;
	}

	final protected Integer defaultInt(Integer i, Integer defaultValue) {
		if (i == null)
			return defaultValue;
		else
			return i;
	}
}
