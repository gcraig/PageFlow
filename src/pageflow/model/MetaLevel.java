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
@Table(name = "meta_levels")
@SuppressWarnings("serial")
public class MetaLevel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "level_id")
    private int levelId;

    @Column(name = "level_name")
    private String levelName;

    public MetaLevel() {
    }

    public int getLevelId() {
        return this.levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return this.levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String toString() {
        return "MetaLevel [levelId=" + levelId + ", levelName=" + levelName + "]";
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + levelId;
        result = prime * result + ((levelName == null) ? 0 : levelName.hashCode());
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
        MetaLevel other = (MetaLevel) obj;
        if (levelId != other.levelId) {
            return false;
        }
        if (levelName == null) {
            if (other.levelName != null) {
                return false;
            }
        } else if (!levelName.equals(other.levelName)) {
            return false;
        }
        return true;
    }

}
