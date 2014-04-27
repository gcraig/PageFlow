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
package com.itsy.sparky.pageflow.controller;

import static com.itsy.sparky.common.utils.Log.info;

import java.util.List;

import com.itsy.sparky.datatype.DataType;

public class ComponentList {

	public ComponentList() {
		info(this, "ComponentList CREATED");
	}

	private List<DataType> components;

	public List<DataType> getComponents() {
		return components;
	}

	public void setComponents(List<DataType> components) {
		this.components = components;
	}
}
