package com.openmatics.testinstrumentation.platform.service.client.authorizationprovider;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public class ProviderAuthorizationServiceGroupApi extends RestApiBase implements IProviderAuthorizationServiceGroupsApi {

    public static String SOURCE = "groups";

    public ProviderAuthorizationServiceGroupApi(IServiceConfiguration property, String clientId) throws Exception {
        super(clientId, property);
    }


    public HttpResponse getGroups() throws Exception {
        return super.getGetResponse(SOURCE);
    }

    public HttpResponse getGroup(String id) throws Exception {
        return super.getGetResponse(String.format("%s/%s", SOURCE, id));
    }

    public HttpResponse getGroupByExtId(String extId) throws Exception {
        return super.getGetResponse(String.format("%s/idExt/%s", SOURCE, extId));
    }

    public HttpResponse updateGroup(String id, String content) throws Exception {
        return super.getPutResponse(id, content);
    }
}
