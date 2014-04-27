/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.events;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import pageflow.controller.HashMapCommand;
import pageflow.service.PageEventException;
import pageflow.service.PageResults;

public abstract class PageEvent implements IPageEvent {

    protected final PageResults SUCCESS = new PageResults();

    protected final PageResults ERROR = new PageResults();

    public PageEvent() {
        SUCCESS.setSuccessful();
        ERROR.setErrored();
    }

    public PageResults doExecute(
            PageContext pageContext,
            HttpSession httpSession,
            HttpServletRequest httpRequest) throws PageEventException {

        /* inject the http session and request parameters */
        pageContext.setHttpSession(httpSession);
        pageContext.setHttpRequest(httpRequest);

        return execute(pageContext);
    }

    /*
     * Convenience methods for data property manipulation; retrieving from page,
     * and storing/retrieving from session.
     */
    /**
     * Retrieve the Http Session associated with the PageContext
     *
     * @param context
     * @return
     */
    protected HttpSession getHttpSession(PageContext context) {
        Map<String, Object> httpValues = context.getHttpValues();
        HttpSession httpSession = (HttpSession) httpValues.get("httpSession");
        return httpSession;
    }

    /**
     * Save a value in the http session.
     *
     * @param context
     * @param key
     * @param value
     */
    protected final void setSessionValue(PageContext context, String key, Object value) {
        Map<String, Object> httpValues = context.getHttpValues();
        HttpSession httpSession = (HttpSession) httpValues.get("httpSession");
        httpSession.setAttribute(key, value);
    }

    /**
     * Retrieve a value on the page, via the http request.
     *
     * @param context
     * @param key
     * @return
     */
    protected final String getFormValue(PageContext context, String key) {
        HashMapCommand command = (HashMapCommand) context.getCommand();
        return command.getProperties().get(key).toString();
    }

    /**
     * Retrieve a value in the http session.
     *
     * @param context
     * @param key
     * @return
     * @throws PageEventException
     */
    protected final String getSessionValue(PageContext context, String key) throws PageEventException {

        return getSessionObject(context, key).toString();
    }

    /**
     * Retrieve an object in the http session.
     *
     * @param context
     * @param key
     * @return
     * @throws PageEventException
     */
    protected final Object getSessionObject(PageContext context, String key) throws PageEventException {
        Map<String, Object> httpValues = context.getHttpValues();
        HttpSession httpSession = (HttpSession) httpValues.get("httpSession");

        Object obj = null;

        /*
         * If session has expired, we need to safely
         */
        try {
            obj = httpSession.getAttribute(key);
        } catch (NullPointerException e) {
            throw new PageEventException("Http Session has expired.");
        }

        return obj;
    }

    /*
     * Convenience hooks, to allow subclasses to override only necessary
     * functionality.
     */
    @Override
    public PageResults execute(PageContext context) throws PageEventException {
        return null;
    }

    @Override
    public void resultsForward(String pageName) {
    }

    @Override
    public void resultsAddErrorMessage(String key, String message) {
    }

    @Override
    public void resultsAddWarningMessage(String key, String message) {
    }

    @Override
    public void resultsAddValidationMessage(String key, String message) {
    }
}
