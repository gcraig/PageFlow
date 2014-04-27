/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package validation;

import static com.itsy.sparky.common.utils.ObjectUtils.isEmpty;

import datatype.DataType;

public class BaseValidator implements Validator {

    protected DataType dataType;

    public ValidationResults validate() {
        Object value = this.getDataType().getValue();
        ValidationResults results = new ValidationResults();
        results.setIsSatisfied(isEmpty(value) == false);
        return results;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
}
