/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.domain;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;

import pageflow.model.Sequence;
import pageflow.model.Step;
import pageflow.service.EntityService;

public class PageIterator<T> {

    @Autowired
    EntityService entityService;

//@Autowired
//ContentService contentService;
    Sequence sequence;

    @SuppressWarnings("rawtypes")
    private final ListIterator listIterator;

    private boolean nextWasCalled = false;
    private boolean previousWasCalled = false;

    public PageIterator(Sequence sequence) {
        this.sequence = sequence;
        this.listIterator = sequence.getSteps().listIterator();

//List<Step> steps = entityService.getStepList(sequence.getSequenceId());
//this.listIterator = steps.listIterator();
//List<Property> properties = entityService.getPropertyList(step.getStepId());
//ListIterator<Property> listIterator = properties.listIterator();
    }

    public Step next() {
        nextWasCalled = true;
        if (previousWasCalled) {
            previousWasCalled = false;
            listIterator.next();
        }

//PageDescriptor p
        return (Step) listIterator.next();
    }

    public Step previous() {
        if (nextWasCalled) {
            listIterator.previous();
            nextWasCalled = false;
        }
        previousWasCalled = true;
        return (Step) listIterator.previous();
    }

    public boolean hasNext() {
        return listIterator.hasNext();
    }

    public boolean hasPrevious() {
        return listIterator.hasPrevious();
    }

    public List<Step> getSteps() {
        return this.sequence.getSteps();
    }
}
