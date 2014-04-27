/*
 * Sparky Dynamic Workflow Engine
 * IT Synergistics, Inc.
 * Copyright (c) 2012, 2013
 */

package com.itsy.sparky.pageflow.domain;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import com.itsy.sparky.pageflow.model.Sequence;
import com.itsy.sparky.pageflow.model.Step;

// pageflowiterator <e>
public class StepIterator implements ListIterator<Step> {

	/*
	 * Collections of the data-driven page flow.
	 * Process 1:* -> Sequences -> * Steps -> Properties
	 */

	private Sequence sequence;
	private List<Step> steps;
	private int currentIndex = 0;
	
	public StepIterator(Sequence sequence) {
		this.sequence = sequence;
		this.steps = this.sequence.getSteps();
		sortStepsByOrdIndex();
	}

	private void sortStepsByOrdIndex() {
		/* Sort Step collection by ordinal display index */
		Collections.sort(steps, new OrdinalIndexComparable());
	}

	public boolean hasNext() {
		return (currentIndex < (steps.size() - 1));
	}

	public boolean hasPrevious() {
		return (currentIndex > 0);
	}

	public Step next() {
		if (hasNext()) 
			return steps.get(++currentIndex);
		else
			throw new NoSuchElementException();
	}

	public Step previous() {
		if (hasPrevious())
			return steps.get(--currentIndex);
		else
			throw new NoSuchElementException();
	}

	public int nextIndex() {
		int idx = currentIndex + 1;
		int last = steps.size() - 1;
		return (idx >= last) ? last : idx;
	}

	public int previousIndex() {
		int idx = currentIndex - 1;
		return (idx < 0) ? 0 : idx;
	}

	public Step first() {
		//if (null == steps || steps.size() < 1)
		//	throw new NoSuchElementException();
		this.currentIndex = 0;
		return steps.get(this.currentIndex);
	}

	public Step last() {
		//if (null != steps && steps.size() == 0) {
			this.currentIndex = steps.size() - 1;
			return steps.get(this.currentIndex);
		//}
		//else
		//	throw new NoSuchElementException();
	}
	
	public int getCurrentIndex() {
		return currentIndex;
	}
	
	public List<Step> getSteps() {
		return this.steps;
	}

	public void remove() {
		
	}
	
	public void set(Step e) {
		
	}
	
	public void add(Step e) {
		
	}
}
