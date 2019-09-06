package com.openmatics.testinstrumentation.platform.entity.servicestartedmc;

import java.util.List;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 01.03.2019.
 */
public class ServiceStartedMC {
    private String groupId;
    private String artifactId;
    private List<ServiceStartedMCDestination> serviceStartedMCDestinations;

    public ServiceStartedMC() {}

    public ServiceStartedMC(String groupId, String artifactId, List<ServiceStartedMCDestination> serviceStartedMCDestinations) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.serviceStartedMCDestinations = serviceStartedMCDestinations;
    }

    public String getGroupId() {
        return groupId;
    }

    public ServiceStartedMC setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public ServiceStartedMC setArtifactId(String artifactId) {
        this.artifactId = artifactId;
        return this;
    }

    public List<ServiceStartedMCDestination> getServiceStartedMCDestinations() {
        return serviceStartedMCDestinations;
    }

    public ServiceStartedMC setServiceStartedMCDestinations(List<ServiceStartedMCDestination> serviceStartedMCDestinations) {
        this.serviceStartedMCDestinations = serviceStartedMCDestinations;
        return this;
    }
}
