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

import java.util.List;

import org.springframework.stereotype.Service;

import com.itsy.sparky.common.utils.Log;
import com.itsy.sparky.pageflow.dao.PageFlowDAO;
import com.itsy.sparky.pageflow.model.Flow;
import com.itsy.sparky.pageflow.model.Process;
import com.itsy.sparky.pageflow.model.Property;
import com.itsy.sparky.pageflow.model.Sequence;
import com.itsy.sparky.pageflow.model.Step;

/**
 * Entity Service responsible for returning (read-only) collections of a
 * Sparky pageflow: 
 * <p>
 * Processes ->* Sequences ->* Steps ->* Properties.
 * <p>
 * All one-to-many subordinate classes will be loaded by hibernate.
 */

@Service
@SuppressWarnings({ "rawtypes" })
public class EntityService extends AbstractService {

	/*
	 * Get List of model objects
	 */

	/**
	 * Get a list of all Processes.
	 */
	public List<Process> getProcessList() {
		return pageFlowDAO.getProcessList();
	}

	/**
	 * Get a list of all Sequences associated to a Process.
	 */
	public List<Sequence> getSequenceList(int processId) {
		return pageFlowDAO.getSequenceList(processId);
	}

	/**
	 * Get a list of all Steps associated to a Sequence.
	 */
	public List<Step> getStepList(int sequenceId) {
		return pageFlowDAO.getStepList(sequenceId);
	}

	/**
	 * Get a list of all Properties associated to a Step.
	 */
	public List<Property> getPropertyList(int stepId) {
		return pageFlowDAO.getPropertyList(stepId);
	}

	/*
	 * Get individual model objects by id or name
	 */

	public Process getProcessByName(String processName) {
		return (Process) pageFlowDAO.getEntityByName(
				PageFlowDAO.ENTITY_PROCESS, processName);
	}

	public Sequence getSequenceByName(String sequenceName) {
		return pageFlowDAO.getSequenceByName(sequenceName);
	}

	public Step getStepByName(String stepName) {
		return (Step) pageFlowDAO.getEntityByName(PageFlowDAO.ENTITY_STEP,
				stepName);
	}

	public Property getPropertyByName(String propertyName) {
		return (Property) pageFlowDAO.getEntityByName(
				PageFlowDAO.ENTITY_PROPERTY, propertyName);
	}

	public Process getProcessById(int processId) {
		return (Process) pageFlowDAO.getEntityById(PageFlowDAO.ENTITY_PROCESS,
				processId);
	}

	public Sequence getSequenceById(int sequenceId) {
		return (Sequence) pageFlowDAO.getEntityById(
				PageFlowDAO.ENTITY_SEQUENCE, sequenceId);
	}

	public Step getStepById(int stepId) {
		return (Step) pageFlowDAO
				.getEntityById(PageFlowDAO.ENTITY_STEP, stepId);
	}

	public Property getPropertyById(int propertyId) {
		return (Property) pageFlowDAO.getEntityById(
				PageFlowDAO.ENTITY_PROPERTY, propertyId);
	}

	public Flow getFlowById(int flowId) {
		return (Flow) pageFlowDAO
				.getEntityById(PageFlowDAO.ENTITY_FLOW, flowId);
	}
}
