package com.itsy.sparky.common.utils;


/**
 * <p>Title: FieldFormatter</p>
 * <p>Description: Provides miscellaneous formatting functions for data displayed on and retrieved from the user interface</p>
 * <p>Company: SysTec/Black Diamond Software</p>
 * @author Julia Filho
 * @version See ClearCase
 */

import java.util.*;
import java.text.*;

import java.sql.Timestamp;
import org.apache.log4j.Logger;

import java.io.*;




public class FieldFormatter
{

  private static Logger logger = Logger.getLogger(FieldFormatter.class);

  private static SimpleDateFormat yearFormatter = new SimpleDateFormat("yyyy");
  private static SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
  private static SimpleDateFormat timeHoursFormatter = new SimpleDateFormat("HH");
  private static SimpleDateFormat timeMinutesFormatter = new SimpleDateFormat("mm");
  private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
  private static SimpleDateFormat dateTimeFormatterAMPM = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
  private static SimpleDateFormat timeFormatterAMPM = new SimpleDateFormat("hh:mm a");
  private static SimpleDateFormat timeFormatterNoLeadingAMPM = new SimpleDateFormat("h:mm a");
  private static SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm");
  
  private static SimpleDateFormat dateTimeFormatterNoSpecial = new SimpleDateFormat("yyyyMMddHHmmss");
  private static SimpleDateFormat dateFormatterNoSpecial = new SimpleDateFormat("yyyyMMdd");


  private static DecimalFormat literFormatter = new DecimalFormat("##0.000");
  private static DecimalFormat kilogramFormatter = new DecimalFormat("#####0.000");

  private static DecimalFormat currencyFormatter = new DecimalFormat("#####0.00");

  
  public FieldFormatter()
  {
  }

  public static String formatDateTimeNoSpecial(Date d)
  {
      return dateTimeFormatterNoSpecial.format(d);
  }

  public static String formatDateNoSpecial(Date d)
  {
      return dateFormatterNoSpecial.format(d);
  }
  
  public static String formatTime(Date d)
  {
      return timeFormatter.format(d);
  }
  
  public static String formatHours(Date d)
  {
      return timeHoursFormatter.format(d);
  }

  public static String formatYear(Date d)
  {
      return yearFormatter.format(d);
  }

  public static String formatMinutes(Date d)
  {
      return timeMinutesFormatter.format(d);
  }



  public static String formatLiters(double l)
  {
      return literFormatter.format(l);
  }

  public static String formatKilograms(double kg)
  {
      return kilogramFormatter.format(kg);
  }


  public static String formatDate(Date d)
  {
      if (d != null)
      {
          return dateFormatter.format(d);
      }

      return "";
  }



  public static String formatDateAndTime(Date d)
  {
    return dateTimeFormatter.format(d);
  }

  public static String formatDateAndTimeAMPM(Date d)
  {
    return dateTimeFormatterAMPM.format(d);
  }

  public static String formatTimeAMPM(Date d)
  {
    return timeFormatterAMPM.format(d);
  }

  public static String formatTimeNoLeadingAMPM(Date d)
  {
    return timeFormatterNoLeadingAMPM.format(d);
  }


  public static String lastPartOf(String id, int n)
  {
    return (id.substring(id.length()-n));
  }


  public static String lastPartOf(Object o, String delimeter)
  {
    String s = null;

    if (o != null)
    {
      s = o.toString();
      int p = s.lastIndexOf(delimeter);

      if (p != -1)
      {
        return (s.substring(p + 1));
      }
    }

    return (s);

  }


  public static String lastPartOf(Object o)
  {
    return (lastPartOf(o, "."));
  }



  public static String formatSsn(String ssn)
  {
    if (ssn == null)
    {
      return "";
    }
    String formattedSsn = ssn;
    if (formattedSsn.length() == 8)
    {
      formattedSsn = "0" + formattedSsn;
    }
    if (formattedSsn.length() == 9)
    {
      formattedSsn = formattedSsn.substring(0, 3) + "-" + formattedSsn.substring(3, 5) + "-" + formattedSsn.substring(5);
    }
    return formattedSsn;
  }


  /*
  public static String formatDate(Date aDate)
  {
    if (aDate == null)
    {
      return "";
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(aDate);
    //if month or day are only 1 digit, prepend "0"

    String month = (cal.get(cal.MONTH) + 1) + "";
    if (month.length() == 1)
    {
      month = "0" + month;
    }
    String day = (cal.get(cal.DAY_OF_MONTH) + "");
    if (day.length() == 1)
    {
      day = "0" + day;
    }
    String year = (cal.get(cal.YEAR) + "");
    return (month + "/" + day + "/" + year);
  }
*/



  public static String formatTimestamp(Timestamp aTimestamp)
  {
    if (aTimestamp == null)
    {
      return "";
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(aTimestamp);
    cal.setLenient(false);
    String sec = (cal.get(cal.SECOND)) + "";
    String min = (cal.get(cal.MINUTE)) + "";
    String hour = (cal.get(cal.HOUR_OF_DAY)) + "";
    String month = (cal.get(cal.MONTH) + 1) + "";
    String day = (cal.get(cal.DAY_OF_MONTH) + "");
    String year = (cal.get(cal.YEAR) + "");
    if (sec.length() == 1)
    {
      sec = "0" + sec;
    }
    if (min.length() == 1)
    {
      min = "0" + min;
    }
    if (hour.length() == 1)
    {
      hour = "0" + hour;
    }
    if (month.length() == 1)
    {
      month = "0" + month;
    }
    if (day.length() == 1)
    {
      day = "0" + day;
    }
    return (month + "/" + day + "/" + year + " " + hour + ":" + min + ":" + sec);
  }



  public static String formatDateTime(Date aDate)
  {
    if (aDate == null)
    {
      return "";
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(aDate);
    cal.setLenient(false);
    String sec = (cal.get(cal.SECOND)) + "";
    String min = (cal.get(cal.MINUTE)) + "";
    String hour = (cal.get(cal.HOUR_OF_DAY)) + "";
    String month = (cal.get(cal.MONTH) + 1) + "";
    String day = (cal.get(cal.DAY_OF_MONTH) + "");
    String year = (cal.get(cal.YEAR) + "");
    return (month + "/" + day + "/" + year + " " + hour + ":" + min + ":" + sec);
  }



  public static String formatMilitaryDateTime(Date aDate)
  {
    if (aDate == null)
    {
      return "";
    }
    Calendar cal = Calendar.getInstance();
    cal.setLenient(false);
    cal.setTime(aDate);
    String min = (cal.get(cal.MINUTE)) + "";
    String hour = (cal.get(cal.HOUR_OF_DAY)) + "";
    String month = (cal.get(cal.MONTH) + 1) + "";
    String day = (cal.get(cal.DAY_OF_MONTH) + "");
    String year = (cal.get(cal.YEAR) + "");
    if (hour.length() == 1)
    {
      hour = "0" + hour;
    }
    if (min.length() == 1)
    {
      min = "0" + min;
    }
    return (month + "/" + day + "/" + year + " " + hour + ":" + min);
  }



  //method to display 23:59 as "Midnight"

  public static String formatAdvMilitaryDateTime(Date aDate)
  {
    if (aDate == null)
    {
      return "";
    }
    
    Calendar cal = Calendar.getInstance();
    
    cal.setLenient(false);
    cal.setTime(aDate);
    
    String min = (cal.get(Calendar.MINUTE)) + "";
    String hour = (cal.get(Calendar.HOUR_OF_DAY)) + "";
    String month = (cal.get(Calendar.MONTH) + 1) + "";
    String day = (cal.get(Calendar.DAY_OF_MONTH) + "");
    String year = (cal.get(Calendar.YEAR) + "");
    
    if (hour.length() == 1)
    {
      hour = "0" + hour;
    }
    if (min.length() == 1)
    {
      min = "0" + min;
    }
    
    String time = hour + ":" + min;
    
    if (time.equalsIgnoreCase("23:59"))
    {
      time = "Midnight";
    }
    
    return (month + "/" + day + "/" + year + " " + time);
  }






  public static String formatDateDayOfWeek(Date aDate)
  {
    SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MM/dd/yyyy");
    if (aDate == null)
    {
      return "";
    }
    return (formatter.format(aDate));
  }





  public static String formatPhone(String phone)
  {
    if (phone == null)
    {
      return "";
    }
    String formattedPhone = null;
    if (phone.length() != 10)
    {
      formattedPhone = phone;
    }
    else
    {
      formattedPhone = "(" + phone.substring(0, 3) + ") " + phone.substring(3, 6) + "-" + phone.substring(6);
    }
    return formattedPhone;
  }



  public static String formatString(String str)
  {
    str = str.trim();
    if (str != null && str.length() > 0)
    {
      str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
      int startPosition = 0;
      int spacePosition = str.indexOf(" ");
      if (spacePosition > -1)
      {
        StringBuffer value = new StringBuffer();
        String temp = null;
        while (spacePosition > -1)
        {
          temp = str.substring(startPosition, spacePosition);
          temp = temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase() + " ";
          value.append(temp);
          str = str.substring(spacePosition + 1).trim();
          str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
          spacePosition = str.indexOf(" ");
        }
        value.append(str);
        str = value.toString();
      }
      startPosition = 0;
      spacePosition = str.indexOf(".");
      while (spacePosition > -1)
      {
        int length = str.length() - 1;
        if (length > spacePosition)
        {
          str = str.substring(0, spacePosition) + str.substring(spacePosition, spacePosition + 2).toUpperCase() + str.substring(spacePosition + 2);
        }
        startPosition = spacePosition + 1;
        spacePosition = str.indexOf(".", startPosition);
      }
    }
    return str;
  }


  public static String trimSpaces(String str)
  {
    return (str.trim());
  }



  public static String trimSpaces_OLD(String str)
  {
    str = str.trim();
    if (str != null && str.length() > 0)
    {
      int startPosition = 0;
      int spacePosition = str.indexOf(" ");
      if (spacePosition > -1)
      {
        StringBuffer value = new StringBuffer();
        String temp = null;
        while (spacePosition > -1)
        {
          temp = str.substring(startPosition, spacePosition);
          value.append(temp);
          str = str.substring(spacePosition + 1).trim();
          spacePosition = str.indexOf(" ");
        }
        value.append(str);
        str = value.toString();
      }
    }
    return str;
  }


  public static String formatUnitNumber(String unitNumber)
  {
    if (unitNumber == null)
    {
      return "";
    }
    String formattedSsn = unitNumber.trim();
    if (formattedSsn.length() >= 13)
    {
      formattedSsn = formattedSsn.substring(0, 5) + " " + formattedSsn.substring(5, 7) + " " + formattedSsn.substring(7);
    }
    return formattedSsn;
  }



  public static String formatExpiryDate(Date aDate)
  {
    if (aDate == null)
    {
      return "";
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(aDate);
    String month = (cal.get(cal.MONTH) + 1) + "";
    String day = (cal.get(cal.DAY_OF_MONTH) + "");
    String year = (cal.get(cal.YEAR) + "").substring(2, 4);
    return (month + "/" + day + "/" + year);
  }



  public static String getFormatString(String dateValue)
  {
    String formatString = null;
    String dateValues[] = dateValue.split("/");
    if (dateValues.length == 3)
    {
      if (dateValues[2].length() == 2)
      {
        formatString = "MM/dd/yy";
      }
      else
      {
        if (dateValues[2].length() == 4)
        {
          formatString = "MM/dd/yyyy";
        }
        else
        {
          if (dateValues[2].length() > 2)
          {
            int index = dateValues[2].indexOf(" ");
            if (index == 2)
            {
              formatString = "MM/dd/yy";
            }
            else
            {
              if (index == 4)
              {
                formatString = "MM/dd/yyyy";
              }
            }
            if (dateValues[2].length() > 4)
            {
              String time = dateValues[2].substring(index + 1);
              String timeValue[] = time.split(":");
              if (timeValue.length == 2)
              {
                if (!timeValue[0].trim().equals(""))
                {
                  if (Integer.parseInt(timeValue[0]) > 12)
                  {
                    formatString += " HH:mm";
                  }
                  else
                  {
                    if (timeValue[1].toUpperCase().indexOf("AM") != -1 || timeValue[1].toUpperCase().indexOf("PM") != -1)
                    {
                      if (timeValue[1].substring(2, 3).equals(" "))
                      {
                        formatString += " hh:mm a";
                      }
                      else
                      {
                        formatString += " hh:mma";
                      }
                    }
                    else
                    {
                      formatString += " HH:mm";
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return formatString;
  }



  public static String formatCurrency(double amount)
  {
    //NumberFormat fmt = NumberFormat.getCurrencyInstance();
    //return fmt.format(amount);
       return currencyFormatter.format(amount);
  }



  public static String formatCurrency(Double amount)
  {
    return formatCurrency(amount.doubleValue());
  }



  public static String formatCurrency(double amount, Locale locale)
  {
    NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
    return fmt.format(amount);
  }



  public static String formatCurrency(Double amount, Locale locale)
  {
    return formatCurrency(amount.doubleValue(), locale);
  }



 


 


  public static Boolean convertYesNoStringToBoolean(String str)
  {
    if (str.equalsIgnoreCase("Y") || str.equalsIgnoreCase("Yes"))
    {
      return new Boolean(true);
    }
    else
    {
      return new Boolean(false);
    }
  }



  public static String convertBooleanToYesNoString(Boolean bool)
  {
    if (bool.booleanValue())
    {
      return "Yes";
    }
    else
    {
      return "No";
    }
  }


  private static final String ISO_CHECK_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ*";

  public static String getISBTCheckChar(String input) //throws Exception
  {


    byte[] inputBytes = null;

    try
    {
       inputBytes = input.getBytes("US-ASCII");
    }
    catch (UnsupportedEncodingException ex)
    {
        return("<unknown>");
    }

    int i;
    int charIndex = 0;
    for (i = 0; i < inputBytes.length; i++)
    {
      byte currentByte = inputBytes[i];
      int val = currentByte - 48;
      if (val > 9)
      {
        val -= 7;
      }
      if (currentByte == 42)
      { // "*"
        val = 36;
      }
      charIndex = ( (charIndex + val) * 2) % 37;
    }
    charIndex = (38 - charIndex) % 37;
    return Character.toString(ISO_CHECK_CHARS.charAt(charIndex));
  }


  public static String getCheckDigitTipText(String s)
  {
      return ("Unit '" + s.toUpperCase() + "', CheckDigit='" + FieldFormatter.getISBTCheckChar(s.toUpperCase()) + "'");
  }


  

  private static Calendar getCalendarDate(Date d)
  {
      Calendar calendarDate = Calendar.getInstance();
      calendarDate.setTime(d);
      return calendarDate;
  }


  public static int getHoursFromDate(Date d)
  {
      return getCalendarDate(d).get(Calendar.HOUR_OF_DAY);
  }

  public static int getYearFromDate(Date d)
  {
      return getCalendarDate(d).get(Calendar.YEAR);
  }


  public static Date setHours(Date d, int hours)
  {
      Calendar calendarDate = getCalendarDate(d);
      calendarDate.set(Calendar.HOUR_OF_DAY, hours);

      return calendarDate.getTime();
  }


  public static Date setMinutes(Date d, int minutes)
  {
      Calendar calendarDate = getCalendarDate(d);
      calendarDate.set(Calendar.MINUTE, minutes);

      return calendarDate.getTime();
  }

  public static Date setSeconds(Date d, int seconds)
  {
      Calendar calendarDate = getCalendarDate(d);
      calendarDate.set(Calendar.SECOND, seconds);

      return calendarDate.getTime();
  }

  public static Date clearTime(Date d)
  {
      Calendar calendarDate = getCalendarDate(d);
      calendarDate.set(Calendar.HOUR_OF_DAY, 0);
      calendarDate.set(Calendar.MINUTE, 0);
      calendarDate.set(Calendar.SECOND, 0);
      calendarDate.set(Calendar.MILLISECOND, 0);

      return calendarDate.getTime();
  }


  public static Timestamp clearTime(Timestamp d)
  {
      Calendar calendarDate = getCalendarDate(d);
      calendarDate.set(Calendar.HOUR_OF_DAY, 0);
      calendarDate.set(Calendar.MINUTE, 0);
      calendarDate.set(Calendar.SECOND, 0);
      calendarDate.set(Calendar.MILLISECOND, 0);

      return new Timestamp(calendarDate.getTimeInMillis());
  }


  public static Timestamp setTimeToEndOfDay(Timestamp d)
  {
      Calendar calendarDate = getCalendarDate(d);
      calendarDate.set(Calendar.HOUR_OF_DAY, 23);
      calendarDate.set(Calendar.MINUTE, 59);
      calendarDate.set(Calendar.SECOND, 00);
      calendarDate.set(Calendar.MILLISECOND, 000);

      return new Timestamp(calendarDate.getTimeInMillis());
  }

  public static Date setTimeToEndOfDay(Date d)
  {
      Calendar calendarDate = getCalendarDate(d);
      calendarDate.set(Calendar.HOUR_OF_DAY, 23);
      calendarDate.set(Calendar.MINUTE, 59);
      calendarDate.set(Calendar.SECOND, 00);
      calendarDate.set(Calendar.MILLISECOND, 000);

      return new Date(calendarDate.getTimeInMillis());
  }



    private static final SimpleDateFormat dateTimeFormatterDefaultYearFirst = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateTimeFormatterDefaultYearLast = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private static final SimpleDateFormat dateTimeFormatterDefaultYearLastNoSeconds = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    private static final SimpleDateFormat dateTimeFormatterYearFirst = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  
  
    private static String ensureTimePortionIsPresent(String dateString)
    {
        if (dateString.indexOf(":") == -1)
        {
            dateString = dateString + " 00:00:00";
        }
        
        return dateString;
    }
    
    
    //
    // try to process the date in the format yyyy-dd-mm HH:mm:ss.  If that doesn't work, try using the format mm/dd/yyyy HH:mm:ss.  If that fails, the date is invalid.    
    //
    
    public static Date createDateVariableFormat(String dateString)
    {
        Date d = createDateTime(dateTimeFormatterDefaultYearFirst, dateString);
        
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

    
    
    public static Date createDateTime(SimpleDateFormat dateTimeFormatter, String dateTimeString)
    {
        java.util.Date d = null;
        
        dateTimeFormatter.setLenient(false);
        
        if (Utility.isNotEmpty(dateTimeString))
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
  
  
}
