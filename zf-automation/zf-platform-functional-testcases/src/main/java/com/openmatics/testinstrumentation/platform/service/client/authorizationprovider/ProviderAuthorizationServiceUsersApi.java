package com.openmatics.testinstrumentation.platform.service.client.authorizationprovider;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public class ProviderAuthorizationServiceUsersApi extends RestApiBase implements IProviderAuthorizationServiceUsersApi {

    public static String SOURCE = "users";

    public ProviderAuthorizationServiceUsersApi(IServiceConfiguration property, String clientId) throws Exception {
        super(clientId, property);
    }


    public HttpResponse getUsers() throws Exception {
        return super.getGetResponse(SOURCE);
    }

    public HttpResponse getUserByExtId(String extId) throws Exception {
        return super.getGetResponse(String.format("%s/idExt/%s", SOURCE, extId));
    }

    public HttpResponse getUserByRoleId(String roleId) throws Exception  {
        return super.getGetResponse(String.format("%s/roleId/%s", SOURCE, roleId));
    }

    public HttpResponse updateUserByExtId(String extId, String content) throws Exception {
        return super.getPutResponse(String.format("%s/%s", SOURCE, extId), content);
    }

}
