/*
 * Sparky Dynamic Workflow Engine
 * IT Synergistics, Inc.
 * Copyright (c) 2012, 2013
 */

package com.itsy.sparky.datatype;

import java.text.SimpleDateFormat;

import com.itsy.sparky.common.utils.FieldFormatter;

public class Date extends DataType {

	static private final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
	
	protected java.util.Date date = null;

	public Date()
	{
		date = null;
	}
	
	public Date(String dateString)
	{
		date = FieldFormatter.createDateVariableFormat(dateString);
	}
	
    public void isToday() {

    }

    public void isPast() {

    }

    public void isFuture() {

    }
    
    public String toString()
    {
    	if (date != null)
    	{
    		return dateFormatter.format(date);
    	}
    	
    	return null;
    }
    
    private static final SimpleDateFormat dateTimeFormatterDefaultYearFirst = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateTimeFormatterDefaultYearLast = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private static final SimpleDateFormat dateTimeFormatterDefaultYearLastNoSeconds = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    private static final SimpleDateFormat dateTimeFormatterYearFirst = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
    
    public static java.util.Date createDateVariableFormat(String dateString)
    {
    	java.util.Date d = createDateTime(dateTimeFormatterDefaultYearFirst, dateString);
        
        if (d == null)
        {
             d = createDateTime(dateTimeFormatterDefaultYearLast, dateString);            
        }

        if (d == null)
        {
             d = createDateTime(dateTimeFormatterYearFirst, dateString);
        }
        
        if (d == null)
        {
           d = createDateTime(dateTimeFormatterDefaultYearLastNoSeconds, dateString);
        }
        
        return d;
    }
    
    public static java.util.Date createDateTime(SimpleDateFormat dateTimeFormatter, String dateTimeString)
    {
        java.util.Date d = null;
        
        dateTimeFormatter.setLenient(false);
        
        if (dateTimeString != null && dateTimeString.length() > 0)
        {
            try
            {
                d = dateTimeFormatter.parse(ensureTimePortionIsPresent(dateTimeString));
            } 
            catch (Exception ex)
            {                
            }

        }
        
        return d;        

    }
    
    private static String ensureTimePortionIsPresent(String dateString)
    {
        if (dateString.indexOf(":") == -1)
        {
            dateString = dateString + " 00:00:00";
        }
        
        return dateString;
    }

}
