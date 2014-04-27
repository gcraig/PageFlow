/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.controller;

import static com.itsy.sparky.common.utils.Log.info;

import java.util.List;

import datatype.DataType;

public class ComponentList {

    public ComponentList() {
        info(this, "ComponentList CREATED");
    }

    private List<DataType> components;

    public List<DataType> getComponents() {
        return components;
    }

    public void setComponents(List<DataType> components) {
        this.components = components;
    }
}
