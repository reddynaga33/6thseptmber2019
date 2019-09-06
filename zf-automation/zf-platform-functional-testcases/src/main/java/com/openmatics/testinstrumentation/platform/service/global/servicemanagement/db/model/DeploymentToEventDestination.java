package com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;

/**
 * Created by Zdeněk Záhlava (zdenek.zahlava@zf.cz) on 21. 03. 2019
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeploymentToEventDestination {

    private String id;
    private String type;
    private String deploymentId;
    private String eventDestinationId;


    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public String getEventDestinationId() {
        return eventDestinationId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public void setEventDestinationId(String eventDestinationId) {
        this.eventDestinationId = eventDestinationId;
    }

    @Override
    public String toString() {
        return "DeploymentToEventDestination{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", deploymentId='" + deploymentId + '\'' +
                ", eventDestinationId='" + eventDestinationId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeploymentToEventDestination that = (DeploymentToEventDestination) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getDeploymentId(), that.getDeploymentId()) &&
                Objects.equals(getEventDestinationId(), that.getEventDestinationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getDeploymentId(), getEventDestinationId());
    }
}
