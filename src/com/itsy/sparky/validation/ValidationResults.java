/*
 * Sparky Dynamic Workflow Engine
 * IT Synergistics, Inc.
 * Copyright (c) 2012, 2013
 */

package com.itsy.sparky.validation;

import java.io.Serializable;
import java.util.List;

/**
 * Resolves the error messages from the passed in error/success code (and/or
 * message key).
 */
@SuppressWarnings("serial")
public final class ValidationResults implements Serializable {

    protected List<WarningMessage> warnings;
    protected List<ErrorMessage> errors;
    protected List<InfoMessage> infos;
    protected SuccessMessage success;
    private Boolean isSatisfied;

    public void addWarning(WarningMessage message) {
        warnings.add(message);
    }

    public final Boolean getIsSatisfied() {
        return isSatisfied;
    }

    public final void setIsSatisfied(Boolean isSatisfied) {
        this.isSatisfied = isSatisfied;
    }

    public void addSuccess(String message) {
        this.success = new SuccessMessage();
        this.success.setDescription(message);
        //this.success.setSeverity(SUCCESS);
    }
}
