/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package validation;

import common.utils.ObjectUtils;

public class TextValidator extends BaseValidator {

    public ValidationResults validate() {
        Object value = this.getDataType().getValue();
        ValidationResults results = new ValidationResults();
        results.setIsSatisfied(ObjectUtils.isEmpty(value) == false);
        return results;
    }
}
