/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.service;

/**
 * PageValidation Exception thrown for errors on forms and invalid data values.
 */
@SuppressWarnings("serial")
public class PageValidationException extends Exception {

    public PageValidationException(String message) {
        super(message);
    }
}
