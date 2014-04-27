/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.utils.Log;
import pageflow.dao.EventsDAO;
import pageflow.events.PageContext;
import pageflow.events.PageEvent;
import pageflow.model.MetaEvent;
import pageflow.model.Step;

/**
 * PageEvent Service responsible for executing events (by page, name, and id).
 */
@Service
@SuppressWarnings({"rawtypes"})
public class PageEventService extends AbstractService {

    @Autowired
    EventsDAO eventsDAO;

    public List<MetaEvent> getEventsList() {
        return eventsDAO.getEventsList();
    }

    /*
     * public void executePageEvents(Step step) { }
     */
    public PageResults executeEventByName(String eventName)
            throws PageEventException {
//MetaEvent event = eventsDAO.getEventByName(name);
        Log.info(this, String.format("Executing event: [%s]: ", eventName));
        return executePageEvent(eventName);
    }

    /*	public PageResults executeEventById(Integer id)
     throws PageEventException {
     MetaEvent event = eventsDAO.getEventById(id);
     Log.info(this, String.format("Executing event: [%s]: ", event));
     return executePageEvent(event.getEventClassName());
     }*/
    private PageResults executePageEvent(String eventName)
            throws PageEventException {

        /* conventions: IInterface, no checking for nulls, handle exceptions,
         page services manage flow and handle forwards */
        Log.info(this, String.format("Instantiating event: [%s]: ", eventName));

        try {

            PageEvent pageEvent = (PageEvent) instantiateEvent(eventName);

            Log.info(this, String.format("PageEvent: [%s]: ", pageEvent));

            return pageEvent.doExecute(getPageContext(), this.httpSession, this.httpRequest);

        } catch (java.lang.ClassNotFoundException e) {
            Log.info(this, String.format("PageEvent not found"));
        } catch (InstantiationException e) {
            Log.info(this, String.format("PageEvent instantiation error."));
        } catch (IllegalAccessException e) {
            Log.info(this, String.format("PageEvent illegal access error."));
        }

        throw new PageEventException(
                String.format("Error processing PageEvent: [%s]", eventName));
    }

    public PageContext getPageContext() {
        return pageContext;
    }

    public void setPageContext(Step step) {
        this.pageContext = new PageContext();
        this.pageContext.generatePageContext(step);
    }

    protected PageContext pageContext;

}
