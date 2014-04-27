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
package com.itsy.sparky.pageflow.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Set;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsy.sparky.pageflow.events.PageContext;
import com.itsy.sparky.pageflow.model.Property;
import com.itsy.sparky.pageflow.model.Step;

/**
 * Content Service responsible for returning (read-only) Html-display-ready
 * page(s) for a given Step.
 */

@Service
@SuppressWarnings({ "rawtypes" })
public class PageContentService extends AbstractService {

	@Autowired
	EntityService entityService;

	/*
	 * TODO: keep a reference to each page, step, and markups, in a cached-
	 * hash-table, OR use ecache
	 */

	/* FIXME: PageContext coupling needs some work */
	
	/** 
	 * Get a Page's (Step) markup (by stepId) and merge the PageContext's variables 
	 * to render a fully functional page.
	 *  
	 * @param stepId
	 * @param pageContext
	 * @return
	 */
	public String getPage(Integer stepId, PageContext pageContext) {
		Step step = entityService.getStepById(stepId);
		return getPage(step, pageContext);
	}

	/** 
	 * Get a Page's (Step) markup (by stepName) and merge the PageContext's variables 
	 * to render a fully functional page.
	 *  
	 * @param stepId
	 * @param pageContext
	 * @return
	 */
	public String getPage(String stepName, PageContext pageContext) {
		Step step = entityService.getStepByName(stepName);
		return getPage(step, pageContext);
	}

	/** 
	 * Get a Page's (Step) markup (by step) and merge the PageContext's variables 
	 * to render a fully functional page.
	 *  
	 * @param stepId
	 * @param pageContext
	 * @return
	 */
	public String getPage(Step step, PageContext pageContext) {
		/* FIXME: test inputs for null */
		return processMarkup(
				pageContext, 
				step.getProperties(),
				step.getPageDescriptor().getMarkup()); 
	}

	/**
	 * Merge variable content with a page html markup.
	 * 
	 * @param pageContext
	 * @param properties
	 * @param markup
	 * @return
	 */
	public String processMarkup(
			PageContext pageContext,
			List<Property> properties, 
			String markup) {

		/*
		 * Create a velocity context for merging the property variables and
		 * their values.
		 */
		VelocityContext velocityContext = new VelocityContext();
		
		/* Copy the decoupled PageContext variables to the template engine. */
		Set keySet = pageContext.getProperties().keySet();
		for (Object key : keySet) {
			velocityContext.put(key.toString(), pageContext.get(key.toString()));
		}

		/* Merge the markup and the variables to generate a page. */
		StringWriter writer = new StringWriter();
		Velocity.init();
		Velocity.evaluate(velocityContext, writer, "", markup);
		return writer.getBuffer().toString();
	}
	
	/**
	 * Generate JSP cached versions on disk that reflect the markup stored
	 * in table meta_page_content.
	 */
	public void generateContentJSP() {
		
		List<Step> steps = entityService.getStepList(41);
		
		for (Step step : steps) {

			StringBuffer markup = new StringBuffer();
			
			String indent = "						";

			markup.append(indent);
			markup.append("<%-- auto-prepended content start --%>\n");
			
			markup.append(indent);
			markup.append("<%@taglib prefix=\"form\" uri=\"http://www.springframework.org/tags/form\" %>\n");
			
			markup.append(indent);
			markup.append("<%@taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>\n");
			
			markup.append(indent);			
			markup.append("<%-- auto-prepended content end --%>\n\n");
 			
			String[] lines = step.getPageDescriptor().getMarkup().split("\n");
			
			for (String s : lines) {
				markup.append(indent);
				markup.append(s);
				markup.append("\n");
			}
			
			markup.append("\n\n");
			
			markup.append(indent);			
			markup.append("<%-- auto-appended content start --%>\n");
			
			List<Property> properties = step.getProperties();
			for (Property prop : properties) {
				if (prop.getPropertyHidden()) {
					markup.append(indent);			
					markup.append(String.format("<form:hidden id=\"%s\" path=\"properties['%s']\"/>\n",
						prop.getPropertyName(),
						prop.getPropertyName()));
				}
			}

			markup.append(indent);			
			markup.append("<%-- auto-appended content end --%>\n\n");

			try {
				
				write(
					"E:/workspace/trunk/DINAssignment/WebContent/views/generated/"
						+ step.getStepName() + ".jsp", markup.toString());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void write(String filename, String text) throws IOException  {
		
	    Writer out = new OutputStreamWriter(new FileOutputStream(filename));
	    try {
	      out.write(text);
	    }
	    finally {
	      out.close();
	    }
	  }
}
