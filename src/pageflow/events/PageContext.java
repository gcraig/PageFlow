/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.events;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import pageflow.model.Property;
import pageflow.model.Step;

/**
 * Container for given execution of a page (step), its properties, and any mvc
 * supporting objects needed for rendering pages and executing events. Decouples
 * the events/pages from the framework.
 */
public final class PageContext {

    private final Map<String, Map<String, Object>> properties
            = new HashMap<String, Map<String, Object>>();

    private final Map<String, Object> httpValues = new HashMap<String, Object>();

    private Step currentStep;

    public final void generatePageContext(Step step) {

// PageContext pageContext = new PageContext();
        List<Property> properties = step.getProperties();
        for (Property property : properties) {

            /*
             * Convenience conversion stripping off "property" prefix,
             * simplifies template writing
             */
            String propertyName = property.getPropertyName();
            Hashtable<String, Object> values = new Hashtable<String, Object>();
            values.put("name", property.getPropertyName());
            values.put("dataType", property.getPropertyDatatype());
            values.put("description", property.getPropertyDescription());
            values.put("displayName", property.getPropertyDisplayName());
            values.put("displayValue", StringUtils.EMPTY); // template
            values.put("value", property.getValue());
            values.put("validatorType", property.getPropertyValidatorType());
            values.put("htmlId", property.getPropertyHtmlId());
            values.put("id", property.getPropertyId());
            values.put("ordIndex", property.getPropertyOrdIndex());
            values.put("status", property.getPropertyStatus());
            put(propertyName, values);
        }

        addStep(step);
    }

    /**
     * Adds a name/value pair to the context.
     *     
* @param propertyName The name to key the property value with.
     * @param values The corresponding property values.
     * @return Object that was replaced in the the Context if applicable or null
     * if not.
     */
    public Object put(String propertyName, Hashtable<String, Object> values) {
        /*
         * don't even continue if propertyName is null
         */
        if (propertyName == null) {
            return null;
        }

        properties.put(propertyName, values);

        return values;
    }

    public Map<String, Map<String, Object>> getProperties() {
        return this.properties;
    }

    public Map<String, Object> get(String key) {
        return properties.get(key);
    }

    /**
     * Adds the HttpSession to the current context, but dynamically to decouple
     * the web and service tiers.
     *
     * @param httpSession
     */
    public void setHttpSession(Object httpSession) {
        this.httpValues.put("httpSession", httpSession);
    }

    /**
     * Adds the HttpRequest to the current context, but dynamically to decouple
     * the web and service tiers.
     *
     * @param httpSession
     */
    public void setHttpRequest(Object httpRequest) {
        this.httpValues.put("httpRequest", httpRequest);
    }

    public Map<String, Object> getHttpValues() {
        return this.httpValues;
    }

    /**
     * Adds a the current Step to the context.
     *     
* @param step The step or page for the current flow.
     */
    public void addStep(Step step) {
        this.currentStep = step;
    }

    @Override
    public String toString() {
        return "PageContext [properties=" + properties + ", currentStep=" + currentStep
                + "]";
    }

    private Object command;

    public void setCommand(Object command) {
        this.command = command;
    }

    public Object getCommand() {
        return this.command;
    }

}
