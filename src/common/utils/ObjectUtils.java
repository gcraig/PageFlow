/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package common.utils;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * Object Utilities
 *
 * @author gcraig
 */
public class ObjectUtils {

    static Log logger = LogFactory.getLog(ObjectUtils.class);

    /**
     * Checks to see if the object is null or Collection is empty
     *
     * @return whether or not the object is empty
     */
    public static final boolean isEmpty(Object value) {

        if (value == null) {
            return true;
        }

        if (value instanceof Collection) {
            return ObjectUtils.isEmpty((Collection) value);
        } else if (value.getClass().isArray()) {
            return (Array.getLength(value) == 0);
        } else {
            return value.equals(null);
        }
    }

    /**
     * Checks to see if the value is null or has zero elements
     *
     * @return whether or not the object is empty
     */
    public static final boolean isEmpty(Collection value) {
        return (value == null || value.size() == 0);
    }

    public static final boolean isNotEmpty(Object value) {
        return !isEmpty(value);
    }

    public static final boolean isNotEmpty(Collection value) {
        return !isEmpty(value);
    }
}
