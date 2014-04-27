/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package common.utils;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * Title: Utility
 * </p>
 * <p>
 * Description: Utility class for reusable methods
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: Lambent Technologies
 * </p>
 *
 * @author : Hozefa Palitanawala
 * @version 1.0
 */
public class Utility {

    static final Logger logger = Logger.getLogger(Utility.class);

    public Utility() {
    }

    public static boolean startsWithLowerCase(String s) {
        if (Utility.isNotEmpty(s)) {
            char c = s.charAt(0);
            return (c >= 'a' && c <= 'z');
        }

        return false;

    }

    public static String lastPartOf(String s, String delimiter) {
        int pos = s.lastIndexOf(delimiter);

        if (pos == -1) {
            return s;
        }

        return s.substring(s.lastIndexOf(delimiter) + 1);
    }

    public static String lastPartOf(Class c, String delimiter) {
        return lastPartOf(c.toString(), delimiter);
    }

	//
    // operations returning information about the contents of Collections
    //
    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNotEmpty(List list) {
        return list != null && list.isEmpty() == false;
    }

	//
    // operations returning information about the contents of Lists
    //
    public static boolean isEmpty(Collection list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNotEmpty(Collection list) {
        return list != null && list.isEmpty() == false;
    }

    public static boolean isEmpty(String field) {
        return (field == null || field.trim().length() == 0);
    }

    public static boolean isNotEmpty(String field) {
        return (field != null && field.trim().length() > 0);
    }

    public static String trim(String field) {
        if (field != null) {
            return field.trim();
        }

        return field;
    }

    public static boolean equalStrings(String field, String compareTo) {
        return ((isEmpty(field) == false) && trim(field).equalsIgnoreCase(
                compareTo));
    }

    public static String escapeEmbeddedQuotes(String s) {
        String result = "";
        char c;

        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);

            if (c == '\'') {
                result = result + "''";
            } else {
                result = result + c;
            }

        }

        return result;

    }

}
