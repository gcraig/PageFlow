/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package datatype;

import java.util.Calendar;

public class EligibilityDate extends Day {

    public static final int INDEFINITE_DEFERRAL_YEARS = 100;

    public boolean isEligible() {
        return false;
    }

    public String toString() {
        if (date != null) {

            Calendar iDate = Calendar.getInstance();
            iDate.add(Calendar.YEAR, INDEFINITE_DEFERRAL_YEARS);

            if (date.after(iDate.getTime())) {
                return "Indefinite";
            }

            return super.toString();
        }

        return null;
    }

}
