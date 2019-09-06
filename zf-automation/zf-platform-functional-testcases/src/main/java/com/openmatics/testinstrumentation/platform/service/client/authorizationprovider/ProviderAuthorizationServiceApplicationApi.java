package com.openmatics.testinstrumentation.platform.service.client.authorizationprovider;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public class ProviderAuthorizationServiceApplicationApi extends RestApiBase implements IProviderAuthorizationServiceApplicationsApi {

    public static String SOURCE = "applications";

    public ProviderAuthorizationServiceApplicationApi(IServiceConfiguration property, String clientId) throws Exception {
        super(clientId, property);
    }


    public HttpResponse getApplications() throws Exception {
        return super.getGetResponse(SOURCE);
    }

    public HttpResponse getApplication(String id) throws Exception {
        return super.getGetResponse(String.format("%s/%s", SOURCE, id));
    }

    public HttpResponse getApplicationByExtId( String extId) throws Exception {
         return super.getGetResponse(String.format("%s/idExt/%s", SOURCE, extId));

    }

    public HttpResponse getApplicationByExtAppId( String extAppId) throws Exception {
        return super.getGetResponse(String.format("%s/appId/%s", SOURCE, extAppId));
    }

    public HttpResponse updateApplication(String id, String content) throws Exception {
        return super.getPutResponse(id, content);
    }
}
