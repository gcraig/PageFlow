/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("serial")
public class BaseEntity implements Serializable {

    final protected String defaultString(String s) {
        if (s == null) {
            return StringUtils.EMPTY;
        } else {
            return s;
        }
    }

    final protected Integer defaultInt(Integer i, Integer defaultValue) {
        if (i == null) {
            return defaultValue;
        } else {
            return i;
        }
    }
}
