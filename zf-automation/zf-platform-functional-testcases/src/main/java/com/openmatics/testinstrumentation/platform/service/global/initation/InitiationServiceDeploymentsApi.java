package com.openmatics.testinstrumentation.platform.service.global.initation;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.apache.http.HttpStatus;

import javax.naming.ServiceUnavailableException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;

//import com.openmatics.testinstrumentation.platform.service.global.initation.IInitiationServiceDeploymentsApi;

public class InitiationServiceDeploymentsApi extends RestApiBase implements IInitiationServiceDeploymentsApi {

    private final String SOURCE = "initiate/deployments/%s/versions/%s/clients/%s";
    private static final String REQUEST_ID_KEY = "requestId";
    private final String EMPTY_BODY = "{}";
    public InitiationServiceDeploymentsApi(IServiceConfiguration property) throws Exception {
        super("global", property);
    }


    @Override
    public HttpResponse AssignServiceVersion2Client(String serviceName, String version, String clientId) throws Exception {
        String source = String.format("initiate/deployments/%s/versions/%s/clients/%s",
                serviceName, version, clientId);
        return super.getPostResponse(source, "{}");
    }

    @Override
    public HttpResponse DeleteServiceVersion2Client(String serviceName, String version, String clientId) throws Exception {
        String source = String.format("initiate/deployments/%s/versions/%s/clients/%s",
                serviceName, version, clientId);
        return super.getDeleteResponse(source);
    }

    @Override
    public String assignServiceVersion2Client(String artifactId, String version, String clientId) {
        String source = String.format(SOURCE, artifactId, version, clientId);
        try {
            HttpResponse response = super.getPostResponse(source, EMPTY_BODY);
            switch (response.getStatus()) {
                case HttpStatus.SC_OK:
                    return response.getContentAsJson().getString(REQUEST_ID_KEY);
                case HttpStatus.SC_UNAUTHORIZED:
                    throw new NotAuthorizedException("Bad authorization");
                case HttpStatus.SC_NOT_FOUND:
                    throw new NotFoundException(String.format("The assignment failed. ArtifactId: %s, Version: %s, Client: %s.", artifactId, version, clientId));
                default:
                    throw new WebApplicationException("Unknown exception");
            }
        } catch (IOException | ServiceUnavailableException e) {
            throw new RuntimeException(String.format("The assignment failed. ArtifactId: %s, Version: %s, Client: %s.", artifactId, version, clientId), e);
        }
    }

    @Override
    public String unassignServiceVersion2Client(String artifactId, String version, String clientId) {
        String source = String.format(SOURCE, artifactId, version, clientId);
        try {
            HttpResponse response = super.getDeleteResponse(source);
            switch (response.getStatus()) {
                case HttpStatus.SC_OK:
                    return response.getContentAsJson().getString(REQUEST_ID_KEY);
                case HttpStatus.SC_UNAUTHORIZED:
                    throw new NotAuthorizedException("Bad authorization");
                case HttpStatus.SC_NOT_FOUND:
                    throw new NotFoundException(String.format("The unassignment failed. ArtifactId: %s, Version: %s, Client: %s.", artifactId, version, clientId));
                default:
                    throw new WebApplicationException("Unknown exception");
            }
        } catch (IOException | ServiceUnavailableException e) {
            throw new RuntimeException(String.format("The unassignment failed. ArtifactId: %s, Version: %s, Client: %s.", artifactId, version, clientId), e);
        }
    }
}
