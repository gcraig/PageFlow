/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Generic form backing bean for
 */
public class HashMapCommand {

    public HashMapCommand() {
        System.out.println("HashMapCommand created.");
    }

    private Map<String, Object> properties = new HashMap<String, Object>();

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public String toString() {
        Iterator iterator = properties.keySet().iterator();
        String s = "";

        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            String value = properties.get(key).toString();
            s += key + "=" + value + " ";
        }

        return s;
    }
}
