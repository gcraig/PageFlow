/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Generic Events for processing Processes, Sequences, Steps, and Properties.
 * Not to be confused with PageEvents which are annotated ala JPA, Struts, etc.
 * 
* @author gcraig
 * 
*/
@Entity
@Table(name = "meta_events")
@SuppressWarnings("serial")
public class MetaEvent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_description")
    @Basic(optional = true)
    private String eventDescription;

    @Column(name = "event_class_name")
    private String eventClassName;

    @Column(name = "event_validator_class_name")
    private String eventValidatorClassName;

    @Column(name = "event_ord_index")
    private Integer eventOrdIndex;

    @Column(name = "event_status")
    @Basic(optional = true)
    private String eventStatus;

    @Column(name = "event_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventStartDate;

    @Column(name = "event_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventEndDate;

    @Column(name = "fk_event_type")
    private String eventType;

    public MetaEvent() {
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventClassName() {
        return eventClassName;
    }

    public void setEventClassName(String eventClassName) {
        this.eventClassName = eventClassName;
    }

    public String getEventValidatorClassName() {
        return eventValidatorClassName;
    }

    public void setEventValidatorClassName(String eventValidatorClassName) {
        this.eventValidatorClassName = eventValidatorClassName;
    }

    public Integer getEventOrdIndex() {
        return eventOrdIndex;
    }

    public void setEventOrdIndex(Integer eventOrdIndex) {
        this.eventOrdIndex = eventOrdIndex;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "MetaEvent [eventId=" + eventId + ", eventName=" + eventName
                + ", eventClassName=" + eventClassName + ", eventStatus=" + eventStatus
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result
                = prime * result
                + ((eventClassName == null) ? 0 : eventClassName.hashCode());
        result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
        result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
        result
                = prime * result + ((eventOrdIndex == null) ? 0 : eventOrdIndex.hashCode());
        result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
        return result;
    }

    @Override
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
        MetaEvent other = (MetaEvent) obj;
        if (eventClassName == null) {
            if (other.eventClassName != null) {
                return false;
            }
        } else if (!eventClassName.equals(other.eventClassName)) {
            return false;
        }
        if (eventId == null) {
            if (other.eventId != null) {
                return false;
            }
        }
        return true;
    }
}
