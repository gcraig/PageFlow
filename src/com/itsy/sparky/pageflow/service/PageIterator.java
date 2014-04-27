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
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;
import com.itsy.sparky.pageflow.model.Sequence;
import com.itsy.sparky.pageflow.model.Step;

/**
 * Page iterator for stepping through a collection of Steps.
 */

@Component
class PageIterator {

	@Autowired
	EntityService entityService;

	Sequence sequence;

	Step currentStep;

	boolean isIntialized = false;

	private ListIterator<Step> listIterator;

	private boolean nextWasCalled = false;
	private boolean previousWasCalled = false;

	PageIterator() {
	}
	
	void init(Integer sequenceId) {
		this.sequence = entityService.getSequenceById(sequenceId);
		//Log.logSequence(this, sequence);
		initIterator();
		initialized(true);
	}

	void init(String sequenceName) {
		this.sequence = entityService.getSequenceByName(sequenceName);
		//Log.logSequence(this, sequence);
		initIterator();
		initialized(true);
	}

	void init(Sequence sequence) {
		this.sequence = sequence;
		//Log.logSequence(this, sequence);
		initIterator();
		initialized(true);
	}

	private void initIterator() {
		this.listIterator = this.sequence.getSteps().listIterator(0);
				
/*		List<Step> steps = this.sequence.getSteps();
	    List<Step> clone = new ArrayList<Step>();
	    for(Step item : steps) clone.add((Step)item.clone());
	    this.listIterator = clone.listIterator();*/
	}

	Step next() {
		/*
		nextWasCalled = true;
		if (previousWasCalled) {
			previousWasCalled = false;
			listIterator.next();
		}*/
		
		Step s = listIterator.next();
		currentStep = s;
		return s;
	}

	Step previous() {
		/*
		if (nextWasCalled) {
			listIterator.previous();
			nextWasCalled = false;
		}
		previousWasCalled = true;
		*/
		
		Step s = listIterator.previous();
		currentStep = s;
		return s;
	}

	boolean hasNext() {
		return listIterator.hasNext();
	}

	boolean hasPrevious() {
		return listIterator.hasPrevious();
	}

	List<Step> getSteps() {
		return this.sequence.getSteps();
	}

	Step getCurrentStep() {		
		
		/* If the iterator has not yet been used, the current step is null, peek ahead
		 * to actually the first Step, via Google Peeking Iterator.
		 */
		if (null == this.currentStep) {
			PeekingIterator<Step> iter = Iterators.peekingIterator(listIterator);
			this.currentStep = iter.peek();
			
			//this.currentStep = getSteps().listIterator(0).next();
			//this.listIterator = getSteps().listIterator(0);
		}
		
		return this.currentStep;
	}

	public void setCurrentStep(Step step) {
		ListIterator<Step> listIterator = this.sequence.getSteps().listIterator();
		while (listIterator.hasNext()) {
			Step indexedStep = listIterator.next();
			if (step.equals(indexedStep)) {
				this.listIterator = listIterator;
				this.currentStep = indexedStep;
			}
		}
	}
	
	void initialized(boolean b) {
		isIntialized = b;
	}

	Boolean isIntialized() {
		return isIntialized;
	}
}
