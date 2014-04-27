/*
 * Sparky Dynamic Workflow Engine
 * IT Synergistics, Inc.
 * Copyright (c) 2012, 2013
 */

package com.itsy.sparky.datatype;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itsy.sparky.common.utils.FieldFormatter;

public class Day extends DataType {

	private static final SimpleDateFormat dateFormatter = 
			new SimpleDateFormat("MM/dd/yyyy");

	protected Date date;

	public Day() {
		date = new Date();
	}

	public Day(String dateString) {
		date = FieldFormatter.createDateVariableFormat(dateString);
	}
	
	public void setDayNoTime(String dateString) {
		try {
			this.date = dateFormatter.parse(dateString);
		} catch (ParseException e) {
			throw new DataTypeException(
				String.format("Parsing day %s error.", dateString), e);
		}
	}

	public Boolean isToday() {
		return (date.compareTo(new Date()) == 0);
	}

	public Boolean isPast() {
		return (date.compareTo(new Date()) < 0);
	}

	public Boolean isFuture() {
		return (date.compareTo(new Date()) > 0);
	}

	public Boolean isSameDay(Date day) {
		return (date.compareTo(day) == 0);
	}
	
	public Boolean isBirthDay() {
		return isBirthDay(new Date());
	}

	public Boolean isBirthDay(Date day) {
		SimpleDateFormat birthDayFormatter = new SimpleDateFormat("MM/dd");
		Date a;
		Date b;
		try {
			a = birthDayFormatter.parse(birthDayFormatter.format(date));
			b = birthDayFormatter.parse(birthDayFormatter.format(day));
		} catch (ParseException e) {
			throw new DataTypeException(
				String.format("Parsing day %s error.", day), e);
		}
		return (a.compareTo(b) == 0);
	}
	
	public String toString() {
		return dateFormatter.format(date);
	}

	private static final SimpleDateFormat dateTimeFormatterDefaultYearFirst =
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final SimpleDateFormat dateTimeFormatterDefaultYearLast =
			new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	private static final SimpleDateFormat dateTimeFormatterDefaultYearLastNoSeconds =
			new SimpleDateFormat("MM/dd/yyyy HH:mm");

	private static final SimpleDateFormat dateTimeFormatterYearFirst =
			new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static java.util.Date createDateVariableFormat(String dateString) {
	
		Date d = createDateTime(dateTimeFormatterDefaultYearFirst, dateString);

		if (d == null) {
			d = createDateTime(dateTimeFormatterDefaultYearLast, dateString);
		}

		if (d == null) {
			d = createDateTime(dateTimeFormatterYearFirst, dateString);
		}

		if (d == null) {
			d = createDateTime(dateTimeFormatterDefaultYearLastNoSeconds, dateString);
		}
		
		return d;
	}

	public static java.util.Date createDateTime(SimpleDateFormat dateTimeFormatter,
			String dateTimeString) {
		Date d = null;

		dateTimeFormatter.setLenient(false);

		if (dateTimeString != null && dateTimeString.length() > 0) {
			try {
				d = dateTimeFormatter.parse(ensureTimePortionIsPresent(dateTimeString));
			} catch (Exception ex) {
			}
		}
		return d;
	}

	private static String ensureTimePortionIsPresent(String dateString) {
		if (dateString.indexOf(":") == -1) {
			dateString = dateString + " 00:00:00";
		}
		return dateString;
	}
}
