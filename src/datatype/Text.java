/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package datatype;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pageflow.validation.ValidationResults;

@SuppressWarnings("serial")
public class Text extends DataType {

    static Log logger = LogFactory.getLog(Text.class);

    protected int maxLength = 0;

    public Text() {        /*
         * default template, user definable in database
         * todo: add a meta_datatypes table with templates
         */
//super.htmlTemplate = "";

        super.validationRegEx = "[0-9a-zA-Z' ']{1,}";
    }

    /**
     * Validate the Text field and return all error and warning messages
     */
    public ValidationResults validate() {
        /*ValidationResults results = new ValidationResults();
         if (null != value) {
         results.setIsSatisfied(value.length() <= maxLength);
         results.addSuccess("Text is successfully validated.");
         }
         return results;*/

        return null;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    /*    public static void main(String[] args) {

     Text t = new Text();
     //t.htmlId = "text1";
     //t.htmlLabel = "Greeting:";
     //t.htmlValue = "Hello World!";

     // replace this with AOP
     //logger.info(LogUtility.begins("DataType"));

     // process required elements
     if (t.htmlId == null)
     t.htmlId = t.getClass().getSimpleName() + "001";

     logger.info(t.processTemplate());
     //logger.info(LogUtility.ends("DataType"));
     }*/
}
