/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package datatype;

@SuppressWarnings("serial")
public class DataTypeException extends RuntimeException {

    public DataTypeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
