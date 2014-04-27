/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package validation;

import datatype.DataType;

/**
 * Validator interface is responsible for simple "encapsulated" internal check
 * if the value object is correct or not.
 */
public interface Validator {

    public abstract ValidationResults validate();

    public abstract void setDataType(DataType dataType);

    public abstract DataType getDataType();
}
