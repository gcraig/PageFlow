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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Generic form backing bean for
 */
public class HashMapCommand {

	public HashMapCommand() {
		System.out.println("HashMapCommand created.");
	}

	private Map<String, Object> properties = new HashMap<String, Object>();

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public String toString() {
		Iterator iterator = properties.keySet().iterator();
		String s = "";

		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			String value = properties.get(key).toString();
			s += key + "=" + value + " ";
		}

		return s;
	}
}
