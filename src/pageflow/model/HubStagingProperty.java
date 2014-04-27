/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the hub_staging_property database table.
 */
@Entity
@Table(name = "hub_staging_property")
@SuppressWarnings("serial")
public class HubStagingProperty extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "staging_id")
    private int id;

// @Column(name="processed_timestamp")
// private Timestamp processedTimestamp;
// @Column(name="staging_property_name")
// private String propertyName;
    @Column(name = "staging_property_value")
    private String propertyValue;

// private String source;
// @Column(name="staging_timestamp")
// private Timestamp stagingTimestamp;
// bi-directional many-to-one association to HubDonor
// @ManyToOne
// @JoinColumn(name="hub_id")
// private HubDonor hubDonor;
    public HubStagingProperty() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*
     * public Timestamp getProcessedTimestamp() { return
     * this.processedTimestamp; } public void setProcessedTimestamp(Timestamp
     * processedTimestamp) { this.processedTimestamp = processedTimestamp; }
     */

    /*
     * public String getPropertyName() { return this.propertyName; } public void
     * setPropertyName(String propertyName) { this.propertyName = propertyName;
     * }
     */
    public String getPropertyValue() {
        return this.propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
    /*
     * public String getSource() { return this.source; } public void
     * setSource(String source) { this.source = source; }
     */
    /*
     * public Timestamp getStagingTimestamp() { return this.stagingTimestamp; }
     * public void setStagingTimestamp(Timestamp stagingTimestamp) {
     * this.stagingTimestamp = stagingTimestamp; }
     */

    /*
     * public HubDonor getHubDonor() { return this.hubDonor; } public void
     * setHubDonor(HubDonor hubDonor) { this.hubDonor = hubDonor; }
     */
}
