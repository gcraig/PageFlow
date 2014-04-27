/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "meta_flows")
@SuppressWarnings("serial")
public class Flow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "flow_id")
    private int flowId;

    @Column(name = "flow_description")
    private String flowDescription;

    @Column(name = "flow_display_name")
    private String flowDisplayName;

    @Column(name = "flow_end_date")
    private Timestamp flowEndDate;

    @Lob
    @Column(name = "flow_icon")
    private byte[] flowIcon;

    @Column(name = "flow_name")
    private String flowName;

    @Column(name = "flow_ord_index")
    private Integer flowOrdIndex;

    @Column(name = "flow_page_override")
    private String flowPageOverride;

    @Column(name = "flow_start_date")
    private Timestamp flowStartDate;

    @Column(name = "flow_status")
    private String flowStatus;

    /* bi-directional many-to-one association to MetaProperty */
    @OneToMany(mappedBy = "metaFlow")
    private List<MetaProperty> metaProperties;

    public Flow() {
    }

    public int getFlowId() {
        return this.flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }

    public String getFlowDescription() {
        return this.flowDescription;
    }

    public void setFlowDescription(String flowDescription) {
        this.flowDescription = flowDescription;
    }

    public String getFlowDisplayName() {
        return this.flowDisplayName;
    }

    public void setFlowDisplayName(String flowDisplayName) {
        this.flowDisplayName = flowDisplayName;
    }

    public Timestamp getFlowEndDate() {
        return this.flowEndDate;
    }

    public void setFlowEndDate(Timestamp flowEndDate) {
        this.flowEndDate = flowEndDate;
    }

    public byte[] getFlowIcon() {
        return this.flowIcon;
    }

    public void setFlowIcon(byte[] flowIcon) {
        this.flowIcon = flowIcon;
    }

    public String getFlowName() {
        return this.flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public Integer getFlowOrdIndex() {
        return this.flowOrdIndex;
    }

    public void setFlowOrdIndex(int flowOrdIndex) {
        this.flowOrdIndex = flowOrdIndex;
    }

    public String getFlowPageOverride() {
        return this.flowPageOverride;
    }

    public void setFlowPageOverride(String flowPageOverride) {
        this.flowPageOverride = flowPageOverride;
    }

    public Timestamp getFlowStartDate() {
        return this.flowStartDate;
    }

    public void setFlowStartDate(Timestamp flowStartDate) {
        this.flowStartDate = flowStartDate;
    }

    public String getFlowStatus() {
        return this.flowStatus;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    public List<MetaProperty> getMetaProperties() {
        return this.metaProperties;
    }

    public void setMetaProperties(List<MetaProperty> metaProperties) {
        this.metaProperties = metaProperties;
    }

    public String toString() {
        return "Flow [flowId=" + flowId + ", flowName=" + flowName + "]";
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + flowId;
        result = prime * result + ((flowName == null) ? 0 : flowName.hashCode());
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
        Flow other = (Flow) obj;
        if (flowId != other.flowId) {
            return false;
        }
        if (flowName == null) {
            if (other.flowName != null) {
                return false;
            }
        } else if (!flowName.equals(other.flowName)) {
            return false;
        }
        return true;
    }

}
