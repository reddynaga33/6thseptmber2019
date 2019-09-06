package com.openmatics.testinstrumentation.platform.service.client.authorizationprovider;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public class ProviderAuthorizationServicePrivilegesApi extends RestApiBase implements IProviderAuthorizationServicePrivilegesApi {

    public static String SOURCE = "privileges";

    public ProviderAuthorizationServicePrivilegesApi(IServiceConfiguration property, String clientId) throws Exception {
        super(clientId, property);
    }


    public HttpResponse getPrivileges() throws Exception {
        return super.getGetResponse(SOURCE);
    }

    public HttpResponse getPrivilegesByExtUserId(String extId) throws Exception {
        return super.getGetResponse(String.format("%s/user/%s", SOURCE, extId));
    }

    public HttpResponse getPrivilegesByExtAppId(String extAppId) throws Exception {
        return super.getGetResponse(String.format("%s/application/%s", SOURCE, extAppId));
    }
}
