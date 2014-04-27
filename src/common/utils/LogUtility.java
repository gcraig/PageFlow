/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package common.utils;

/**
 * @author karl
 */
public class LogUtility {

    private static final String TAG_BEGINS = "BEGINS";
    private static final String TAG_ENDS = "ENDS";
    private static final String TAG_ERROR = "[ERROR]";
    private static final String TAG_WARNING = "[WARNING]";

    private static final String SEPARATOR = ":   ";

    public static String getThreadID() {
        String s = Thread.currentThread().getName();
        int pos = s.indexOf(" for queue");
        int pos2 = s.indexOf("ExecuteThread: ");

        if (pos != -1) {
            return s.substring(pos2 + 15, pos).replaceAll("'", "");
        }
        return (s);
    }

    private static String id(HttpSession session) {
        String eCode = "";

        if (session != null) {
            eCode = (String) session.getAttribute("empCode");
        }

        if (eCode == null) {
            eCode = "";
        }

        return "[" + eCode + "][" + getThreadID() + "]";
    }

    private static String id(HttpServletRequest request) {
        if (request != null) {
            return id(request.getSession());
        }

        return id((HttpSession) null);
    }

    public static String toMessage(HttpSession session, String message) {
        return id(session) + SEPARATOR + message;
    }

    public static String toMessage(HttpServletRequest request, String message) {
        return id(request) + SEPARATOR + message;
    }

    public static String toMessage(String message) {
        return id((HttpSession) null) + SEPARATOR + message;
    }

    public static String toError(HttpServletRequest request, String message) {
        return id(request) + TAG_ERROR + SEPARATOR + message;
    }

    public static String toError(HttpSession session, String message) {
        return id(session) + TAG_ERROR + SEPARATOR + message;
    }

    public static String toError(String message) {
        return id((HttpSession) null) + TAG_ERROR + SEPARATOR + message;
    }

    public static String toWarning(HttpServletRequest request, String message) {
        return id(request) + TAG_WARNING + SEPARATOR + message;
    }

    public static String toWarning(HttpSession session, String message) {
        return id(session) + TAG_WARNING + SEPARATOR + message;
    }

    public static String toWarning(String message) {
        return id((HttpSession) null) + TAG_WARNING + SEPARATOR + message;
    }

    public static String begins(HttpServletRequest request, String methodName) {
        return toMessage(request, methodName) + " " + TAG_BEGINS;
    }

    public static String begins(HttpSession session, String methodName) {
        return toMessage(session, methodName) + " " + TAG_BEGINS;
    }

    public static String begins(String methodName) {
        return toMessage(methodName) + " " + TAG_BEGINS;
    }

    public static String ends(HttpServletRequest request, String methodName) {
        return toMessage(request, methodName) + " " + TAG_ENDS;
    }

    public static String ends(HttpSession session, String methodName) {
        return toMessage(session, methodName) + " " + TAG_ENDS;
    }

    public static String ends(String methodName) {
        return toMessage(methodName) + " " + TAG_ENDS;
    }

    public static String quote(String s) {
        return "'" + s + "'";
    }

    public static String valueOf(String name, String value) {
        return name + "=" + quote(value);
    }

}
