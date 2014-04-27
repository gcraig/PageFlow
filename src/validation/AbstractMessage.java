/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package validation;

public abstract class AbstractMessage {

    protected String code;
    protected String severity;
    protected String description;

    public final String getCode() {
        return code;
    }

    public final void setCode(String code) {
        this.code = code;
    }

    public final String getSeverity() {
        return severity;
    }

    public final void setSeverity(String severity) {
        this.severity = severity;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        this.description = description;
    }
}
