package com.openmatics.testinstrumentation.platform.service.global.initation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.platform.service.global.initation.model.ServiceVersionIdentifier;
import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.apache.http.HttpStatus;

import javax.naming.ServiceUnavailableException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 29.04.2019.
 */
public class InitiationServiceServiceVersionApi extends RestApiBase implements IInitiationServiceServiceVersionApi {

    private static final String IMAGE = "it-doesn't-matter";
    private static final String REQUEST_ID_KEY = "requestId";
    private static final String SOURCE_VERSION = "initiate/services/%s/versions";

    public InitiationServiceServiceVersionApi(IServiceConfiguration property) {
        super("global", property);
    }

    @Override
    public String createServiceVersionDescriptor(String artifactId, String versionName) {
        String path = String.format(SOURCE_VERSION, artifactId);
        try {
            String content = JsonUtils.toJson(new ServiceVersionIdentifier(IMAGE, versionName));
            HttpResponse response = super.getPostResponse(path, content);
            switch (response.getStatus()) {
                case HttpStatus.SC_OK:
                    return response.getContentAsJson().getString(REQUEST_ID_KEY);
                case HttpStatus.SC_UNAUTHORIZED:
                    throw new NotAuthorizedException("Bad authorization");
                case HttpStatus.SC_NOT_FOUND:
                    throw new NotFoundException(String.format("The serviceVersionDescriptor creation is failed. The service descriptor: '%s' not found", artifactId));
                default:
                    throw new WebApplicationException("Unknown exception");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't serialize the content of the message.", e);
        } catch (IOException | ServiceUnavailableException e) {
            throw new RuntimeException("The creating service descriptor failed.", e);
        }
    }
}
