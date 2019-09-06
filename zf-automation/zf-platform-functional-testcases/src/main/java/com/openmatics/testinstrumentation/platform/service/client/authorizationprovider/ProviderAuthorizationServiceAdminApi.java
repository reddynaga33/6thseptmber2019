package com.openmatics.testinstrumentation.platform.service.client.authorizationprovider;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public class ProviderAuthorizationServiceAdminApi extends RestApiBase implements IProviderAuthorizationServiceAdminApi {

    public static String SOURCE = "admin";

    public ProviderAuthorizationServiceAdminApi(IServiceConfiguration property, String clientId) throws Exception {
        super(clientId, property);
    }


    public HttpResponse setAdmin(String extUserId) throws Exception {
        return super.getPutResponse(String.format("%s/setAdmin/%s", SOURCE, extUserId),"");
    }
}
