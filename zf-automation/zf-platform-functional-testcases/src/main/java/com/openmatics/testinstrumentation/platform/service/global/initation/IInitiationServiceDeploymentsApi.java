package com.openmatics.testinstrumentation.platform.service.global.initation;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IInitiationServiceDeploymentsApi {

    @Deprecated
    HttpResponse AssignServiceVersion2Client(String serviceName, String version, String clientId) throws Exception;

    @Deprecated
    HttpResponse DeleteServiceVersion2Client(String serviceName, String version, String clientId) throws Exception;

    /**
     * Assign the version to the client
     *
     * @param artifactId artifact id of the service
     * @param version    version name
     * @param clientId   clientId
     * @return requestId
     */
    String assignServiceVersion2Client(String artifactId, String version, String clientId);

    /**
     * Unassign the version to the client
     *
     * @param artifactId artifact id of the service
     * @param version    version name
     * @param clientId   clientId
     * @return requestId
     */
    String unassignServiceVersion2Client(String artifactId, String version, String clientId);
}
