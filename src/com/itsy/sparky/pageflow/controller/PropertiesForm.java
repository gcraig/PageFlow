/*
 * Sparky Dynamic Workflow Engine
 * IT Synergistics, Inc.
 * Copyright (c) 2012, 2013
 */

package com.itsy.sparky.pageflow.controller;

import com.itsy.sparky.datatype.HTMLComponent;
import org.apache.commons.collections.functors.InstantiateFactory;
import org.apache.commons.collections.list.LazyList;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class PropertiesForm {

    private List<HTMLComponent> components;

    public PropertiesForm() {

        components = LazyList.decorate(
                new ArrayList<HTMLComponent>(),
                new InstantiateFactory(HTMLComponent.class));
    }

}
