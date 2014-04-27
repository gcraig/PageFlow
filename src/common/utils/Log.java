/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package common.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import pageflow.model.Process;
import pageflow.model.Property;
import pageflow.model.Sequence;
import pageflow.model.Step;

public class Log {

    private static final String TAG_BEGINS = "BEGIN ";
    private static final String TAG_ENDS = "END ";
    private static final String TAG_ERROR = "[ERROR] ";
    private static final String TAG_WARNING = "[WARNING] ";
    private static final String SEPARATOR = ":   ";
    private static Logger logger;

    /*
     Log.debug(this, "Log this message");
     Log.debug(this, "Log this message", session);
     Log.error(this, "Log this error", session, exception);
     info
     warn
     error
     begins
     ends
     */

    /* Debug logging levels */
    public static void
            debug(Object obj, String msg) {
        logMessage(Priority.DEBUG, obj, msg, (HttpSession) null, null);
    }

    public static void
            debug(Object obj, String msg, Throwable t) {
        logMessage(Priority.DEBUG, obj, msg, (HttpSession) null, t);
    }

    public static void
            debug(Object obj, String msg, HttpServletRequest request) {
        logMessage(Priority.DEBUG, obj, msg, request, null);
    }

    public static void
            debug(Object obj, String msg, HttpSession session) {
        logMessage(Priority.DEBUG, obj, msg, (HttpSession) null, null);
    }

    public static void
            debug(Object obj, String msg, HttpServletRequest request, Throwable t) {
        logMessage(Priority.DEBUG, obj, msg, request, t);
    }

    public static void
            debug(Object obj, String msg, HttpSession session, Throwable t) {
        logMessage(Priority.DEBUG, obj, msg, session, t);
    }

    /* Info logging levels */
    public static void
            info(Object obj, String msg) {
        logMessage(Priority.INFO, obj, msg, (HttpSession) null, null);
    }

    public static void
            info(Object obj, Object msg) {
        logMessage(Priority.INFO, obj, msg.toString(), (HttpSession) null, null);
    }

    public static void
            info(Object obj, String msg, Throwable t) {
        logMessage(Priority.INFO, obj, msg, (HttpSession) null, t);
    }

    public static void
            info(Object obj, Object msg, Throwable t) {
        logMessage(Priority.INFO, obj, msg.toString(), (HttpSession) null, t);
    }

    public static void
            info(Object obj, String msg, HttpServletRequest request) {
        logMessage(Priority.INFO, obj, msg, request, null);
    }

    public static void
            info(Object obj, Object msg, HttpServletRequest request) {
        logMessage(Priority.INFO, obj, msg.toString(), request, null);
    }

    public static void
            info(Object obj, String msg, HttpSession session) {
        logMessage(Priority.INFO, obj, msg, (HttpSession) null, null);
    }

    public static void
            info(Object obj, Object msg, HttpSession session) {
        logMessage(Priority.INFO, obj, msg.toString(), (HttpSession) null, null);
    }

    public static void
            info(Object obj, String msg, HttpServletRequest request, Throwable t) {
        logMessage(Priority.INFO, obj, msg, request, t);
    }

    public static void
            info(Object obj, Object msg, HttpServletRequest request, Throwable t) {
        logMessage(Priority.INFO, obj, msg.toString(), request, t);
    }

    public static void
            info(Object obj, String msg, HttpSession session, Throwable t) {
        logMessage(Priority.INFO, obj, msg, session, t);
    }

    public static void
            info(Object obj, Object msg, HttpSession session, Throwable t) {
        logMessage(Priority.INFO, obj, msg.toString(), session, t);
    }

    private static void
            logMessage(Priority priority, Object obj, String message,
                    HttpServletRequest request, Throwable t) {
        String employeeCode = getEmployeeCode(request);
        doLogMessage(priority, obj, message, employeeCode, t);
    }

    private static void
            logMessage(Priority priority, Object obj, String message,
                    HttpSession session, Throwable t) {
        String employeeCode = getEmployeeCode(session);
        doLogMessage(priority, obj, message, employeeCode, t);
    }

    private static void
            doLogMessage(Priority priority, Object obj, String message,
                    String employeeCode, Throwable t) {
        String className = "";
        String threadId = getThreadId();

        if (className != null) {
            className = obj.getClass().getSimpleName();
        }

        Logger logger = Logger.getLogger(obj.getClass());

        StringBuffer buf = new StringBuffer(message);
        buf.insert(0, employeeCode);
        buf.insert(0, threadId);
        message = buf.toString();

        if (logger.isEnabledFor(priority)) {

            if (t == null) {
                logger.log(priority, message);
            } else {
                logger.log(priority, message, t);
            }
        }
    }

    private static String getThreadId() {
        String s = Thread.currentThread().getName();
        int pos = s.indexOf(" for queue");
        int pos2 = s.indexOf("ExecuteThread: ");

        if (pos != -1) {
            return s.substring(pos2 + 15, pos).replaceAll("'", "");
        }

        s = (s == null || s.trim().equals("")) ? "" : String.format("[%s] ", s);
        return s;
    }

    private static String getEmployeeCode(HttpServletRequest request) {
        String empCode = "";
        if (request != null) {
            empCode = (String) request.getAttribute("empCode");
        }
        if (empCode == null || empCode.trim().equals("")) {
            empCode = getEmployeeCode(request.getSession());
        }
        if (empCode == null || empCode.trim().equals("")) {
            empCode = "";
        }
        return empCode;
    }

    private static String getEmployeeCode(HttpSession session) {
        String empCode = "";
        if (session != null) {
            empCode = (String) session.getAttribute("empCode");
        }
        if (empCode == null || empCode.trim().equals("")) {
            empCode = "";
        }
        return empCode;
    }

    /*
     public static void begins(Object obj, HttpServletRequest request) {
     String methodName = obj.getClass().getName();
     StringBuffer sb = new StringBuffer();
     sb.append(TAG_BEGINS + methodName);
     sb.append(id(request) + SEPARATOR + message);
     sb.append(request.toString())
     debug(request, methodName) + " " + TAG_BEGINS;
     }

     public static String begins(HttpSession session, String methodName) {
     return debug(session, methodName) + " " + TAG_BEGINS;
     }

     public static String begins(String methodName) {
     return debug(methodName) + " " + TAG_BEGINS;
     }
     */
    public static void log(Object obj, Object entity) {
        if (entity == null) {
            return;
        }
        if (entity instanceof Process) {
            logProcess(obj, (Process) entity);
        } else if (entity instanceof Sequence) {
            logSequence(obj, (Sequence) entity);
        } else if (entity instanceof Step) {
            logStep(obj, (Step) entity);
        }
    }

    public static void logProcess(Object obj, Process process) {

        info(obj, repeat("=", 60));

        info(obj, process);

        for (Sequence sequence : process.getSequences()) {

            info(obj, "\t" + sequence);

            for (Step step : sequence.getSteps()) {

                info(obj, "\t\t" + step);

                for (Property property : step.getProperties()) {

                    info(obj, "\t\t\t" + property);
                }

            }
        }

        info(obj, repeat("=", 60));
    }

    public static void logSequence(Object obj, Sequence sequence) {

        info(obj, repeat("=", 60));

        info(obj, sequence);

        for (Step step : sequence.getSteps()) {

            info(obj, "\t" + step);

            for (Property property : step.getProperties()) {

                info(obj, "\t\t" + property);
            }

        }

        info(obj, repeat("=", 60));
    }

    public static void logStep(Object obj, Step step) {

        info(obj, repeat("=", 60));

        info(obj, step);

        for (Property property : step.getProperties()) {

            info(obj, "\t" + property);
        }

        info(obj, repeat("=", 60));
    }

    public static void logProcessList(Object obj, List<Process> result) {

        info(obj, repeat("=", 60));

        for (Process process : result) {

            info(obj, process);

            for (Sequence sequence : process.getSequences()) {

                info(obj, "\t" + sequence);

                for (Step step : sequence.getSteps()) {

                    info(obj, "\t\t" + step);
                }
            }
        }

        info(obj, repeat("=", 60));
    }

    public static void logPropertyList(Object obj, List<Property> result) {

        info(obj, repeat("=", 60));

        for (Property property : result) {

            info(obj, property);
        }

        info(obj, repeat("=", 60));
    }

    public static String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }
}
