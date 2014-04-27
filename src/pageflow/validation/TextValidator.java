/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.validation;

import common.utils.ObjectUtils;

public class TextValidator extends BaseValidator {

    public ValidationResults validate() {
        Object value = new Object(); // this.getDataType().getValue();
        ValidationResults results = new ValidationResults();
        if (ObjectUtils.isEmpty(value) == false) {
            results.setSuccessful();
        }
        return results;
    }
}
