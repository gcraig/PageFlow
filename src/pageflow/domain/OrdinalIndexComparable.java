/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.domain;

import java.util.Comparator;

import pageflow.model.Step;

public class OrdinalIndexComparable implements Comparator<Step> {

// todo: BaseEntity
    /**
     * Compare each Step and sort by ordinal index
     */
    public int compare(Step step1, Step step2) {

        Integer ndx1 = step1.getStepOrdIndex();
        Integer ndx2 = step2.getStepOrdIndex();
        /*
         if (null == ndx1)
         ndx1 = Integer.MAX_VALUE;

         if (null == ndx2)
         ndx2 = Integer.MAX_VALUE;
         */
        return (ndx2 > ndx1 ? -1 : (ndx2 == ndx1 ? 0 : 1));
    }

}
