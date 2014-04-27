/*
 * Sparky Dynamic Workflow Engine
 * IT Synergistics, Inc.
 * Copyright (c) 2012, 2013
 */

package com.itsy.sparky.datatype;

public interface HTMLComponent {

    public String getHtmlId();

    public void setHtmlId(String htmlId);

    public String getHtmlTemplate();

    public void setHtmlTemplate(String htmlTemplate);

    public String getHtmlValue();

    public void setHtmlValue(String htmlValue);

    public String getHtmlLabel();

    public void setHtmlLabel(String htmlLabel);

    public String getHTML();

    public void setHTML();

    public boolean getWarningFlag();

    public void setWarningFlag(boolean b);

    public boolean getErrorFlag();

    public void setErrorFlag(boolean b);

    public boolean getFocusFlag();

    public void setFocusFlag();

}
