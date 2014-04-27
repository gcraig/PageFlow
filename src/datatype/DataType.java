/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package datatype;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pageflow.model.Property;
import pageflow.validation.ValidationResults;

/**
 * Core object that all Sparky value objects should extend.
 */
@SuppressWarnings("serial")
public class DataType extends Property {

    static Log logger = LogFactory.getLog(DataType.class);

    protected String validationRegEx;
//protected IValidator validator;

    public DataType() {
        super();
//logger.info(LogUtility.begins("DataType"));
// htmlTemplate = "${htmlLabel}: ${htmlId} ${htmlValue}";
/*
         if (null == fieldName || null == fieldType || null == validationRegEx) {
         throw new InitializationException(
         "fieldName, fieldType, or validationRegEx is not initialized.")
         }
         */
    }

    public String getValidationRegEx() {
        return validationRegEx;
    }

    public void setValidationRegEx(String validationRegEx) {
        this.validationRegEx = validationRegEx;
    }

    public ValidationResults validate() {
        ValidationResults results = new ValidationResults();
        return results;
    }

    /**
     * Formats the object using Apache's StringBuilder
     *     
* @return formatted string representing this object
     */
    public String toString() {
        try {
            return ToStringBuilder.reflectionToString(this,
                    ToStringStyle.MULTI_LINE_STYLE);
        } catch (Throwable t) {
            return t.getMessage();
        }
    }

    public String toHTML() {

// http://stackoverflow.com/questions/14044715/strsubstitutor-replacement-with-jre-libraries

        /* Fetch cached appropriate html template file */

        /*
         if (null == htmlTemplate || null == htmlLabel)
         return "";

         if (null == htmlId)
         return fieldType;

         if (null == htmlValue) {
         Map<String, String> values = new HashMap<String, String>();
         values.put("htmlId", htmlId);
         StrSubstitutor sub = new StrSubstitutor(values);
         htmlValue = sub.replace(htmlTemplate);
         }

         return htmlValue;
         */
        return "";
    }

    /*
     public IValidator getValidator() {
     return validator;
     }

     public void setValidator(IValidator validator) {
     this.validator = validator;
     }
     */
}
