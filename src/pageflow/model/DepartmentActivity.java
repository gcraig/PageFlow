/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "vw_departments")
public class DepartmentActivity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "dept_description")
    private String deptDescription;

    @Column(name = "dept_display_name")
    private String deptDisplayName;

    @Column(name = "dept_end_date")
    private Timestamp deptEndDate;

    @Lob
    @Column(name = "dept_icon")
    private byte[] deptIcon;
    @Id
    @Column(name = "dept_id")
    private int deptId;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "dept_ord_index")
    private Integer deptOrdIndex;

    @Column(name = "dept_page_override")
    private String deptPageOverride;

    @Column(name = "dept_start_date")
    private Timestamp deptStartDate;

    @Column(name = "dept_status")
    private String deptStatus;

    public DepartmentActivity() {
    }

    public String getDeptDescription() {
        return this.deptDescription;
    }

    public void setDeptDescription(String deptDescription) {
        this.deptDescription = deptDescription;
    }

    public String getDeptDisplayName() {
        return this.deptDisplayName;
    }

    public void setDeptDisplayName(String deptDisplayName) {
        this.deptDisplayName = deptDisplayName;
    }

    public Timestamp getDeptEndDate() {
        return this.deptEndDate;
    }

    public void setDeptEndDate(Timestamp deptEndDate) {
        this.deptEndDate = deptEndDate;
    }

    public byte[] getDeptIcon() {
        return this.deptIcon;
    }

    public void setDeptIcon(byte[] deptIcon) {
        this.deptIcon = deptIcon;
    }

    public int getDeptId() {
        return this.deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getDeptOrdIndex() {
        if (null == this.deptOrdIndex) {
            this.deptOrdIndex = Integer.MAX_VALUE;
        }
        return this.deptOrdIndex;
    }

    public void setDeptOrdIndex(int deptOrdIndex) {
        this.deptOrdIndex = deptOrdIndex;
    }

    public String getDeptPageOverride() {
        return this.deptPageOverride;
    }

    public void setDeptPageOverride(String deptPageOverride) {
        this.deptPageOverride = deptPageOverride;
    }

    public Timestamp getDeptStartDate() {
        return this.deptStartDate;
    }

    public void setDeptStartDate(Timestamp deptStartDate) {
        this.deptStartDate = deptStartDate;
    }

    public String getDeptStatus() {
        return this.deptStatus;
    }

    public void setDeptStatus(String deptStatus) {
        this.deptStatus = deptStatus;
    }

    public String toString() {
        return String.format(
                "Department [deptId=%s, deptName=%s]",
                deptId,
                deptName);
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + deptId;
        result
                = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DepartmentActivity other = (DepartmentActivity) obj;
        if (deptId != other.deptId) {
            return false;
        }
        if (deptName == null) {
            if (other.deptName != null) {
                return false;
            }
        } else if (!deptName.equals(other.deptName)) {
            return false;
        }
        return true;
    }

}
