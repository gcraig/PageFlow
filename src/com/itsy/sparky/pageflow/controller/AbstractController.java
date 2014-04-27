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

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;

import com.itsy.sparky.controller.JsonView;
import com.itsy.sparky.pageflow.domain.PageIterator;
import com.itsy.sparky.pageflow.service.EntityService;

public abstract class AbstractController {

	public ModelAndView render(ModelAndView model, Collection<?> objects,
			HttpServletRequest request, HttpServletResponse response) {

		String ren = request.getParameter("ren");

		if ("json".equalsIgnoreCase(ren)) {
			return jsonView.renderJSON(model.getModelMap(), response);
		} else if ("xml".equalsIgnoreCase(ren)) {
			return jsonView.renderXML(objects, response);
		} else {
			return model;
		}

	}

	protected void enableNavButtons(PageIterator itr, HttpServletRequest request) {

		// empty string == false

		String disableFirst = "", disableNext = "", disablePrev = "", disableLast = "";

		if (itr.hasPrevious() == false) {
			disableFirst = "disabled";
			disablePrev = "disabled";
		}

		if (itr.hasNext() == false) {
			disableNext = "disabled";
			disableLast = "disabled";
		}

		/*
		 * if (currentIndex > first && currentIndex < last) { }
		 */

		request.setAttribute("disableFirst", disableFirst);
		request.setAttribute("disablePrev", disablePrev);
		request.setAttribute("disableNext", disableNext);
		request.setAttribute("disableLast", disableLast);
	}

	private JsonView jsonView;

	protected EntityService service;

	public JsonView getJsonView() {
		return jsonView;
	}

	@Autowired
	@Required
	public void setJsonView(JsonView jsonView) {
		this.jsonView = jsonView;
	}

	public EntityService getService() {
		return service;
	}

	@Autowired
	@Required
	public void setService(EntityService service) {
		this.service = service;
	}
}
