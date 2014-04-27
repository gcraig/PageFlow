/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package datatype;

public class UnitNumberCodabar extends UnitNumber {

    protected Object validationRegEx = "^[A-D][0-9\\+$:\\-/.]*[A-D]$";

}
