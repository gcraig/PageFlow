/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.service;

/**
 * Generic Page Exception
 */
@SuppressWarnings("serial")
public class PageException extends Exception {

    public PageException(String message) {
        super(message);
    }
}
