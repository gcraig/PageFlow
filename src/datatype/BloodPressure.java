/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package datatype;

public class BloodPressure extends DataType {

    protected Integer systolic;
    protected Integer diastolic;

	// check system preferences for # of readings
	// if validation outside of acceptable, user defined range,
    // enable second property for a second reading
	// validation service needs to enable/disable additional widgets and
    // functionality based upon
	// deferral available if threshold is exceeded
	// additional validation of various datatypes:
    // pulse pass:fail, or a value
	// need to add a status, enable/disable, visible flag to Property
	// TechID first readings == logged in person, second readings can be new
    // technician
	// tab order
	// ajax-centric
	// thresholdable value, pass in procedure types, donation types, threshold
    // notification type
	// header/footer action
}
