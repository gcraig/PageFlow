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

@Entity
@Table(name = "meta_event_types")
@SuppressWarnings("serial")
public class MetaEventType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_type")
    private String eventType;

    @Column(name = "event_type_description")
    private String eventTypeDescription;

    public MetaEventType() {
    }

    public String getEventType() {
        return this.eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventTypeDescription() {
        return this.eventTypeDescription;
    }

    public void setEventTypeDescription(String eventTypeDescription) {
        this.eventTypeDescription = eventTypeDescription;
    }

    public String toString() {
        return "MetaEventType [eventType=" + eventType + "]";
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
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
        MetaEventType other = (MetaEventType) obj;
        if (eventType == null) {
            if (other.eventType != null) {
                return false;
            }
        } else if (!eventType.equals(other.eventType)) {
            return false;
        }
        return true;
    }

}
