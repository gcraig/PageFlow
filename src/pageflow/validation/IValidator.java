/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.validation;

import pageflow.events.PageContext;

/**
 * Validator interface is responsible for simple "encapsulated" internal check
 * if the value object is correct or not.
 */
public interface IValidator {

    public abstract ValidationResults validate(PageContext context);

    public void resultsAddValidationMessage(String key, String message);

    /*
     * public abstract void setDataType(DataType dataType);
     * 
     * public abstract DataType getDataType();
     */
}
