package com.openmatics.testinstrumentation.platform.service.app.backoffice;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public class BackofficeAuthorizationRoleApi extends RestApiBase implements IBackofficeAuthorizationRoleApi {

    public static String SOURCE = "/authorization/roles";

    public BackofficeAuthorizationRoleApi(IServiceConfiguration property, String clientId) throws Exception {
        super(clientId, property);
    }

    public HttpResponse getRoles() throws Exception {
        return super.getGetResponse(SOURCE);
    }

    public HttpResponse getRole(String id) throws Exception {
        return super.getGetResponse(String.format("%s/%s", SOURCE, id));
    }

    public HttpResponse createRole(String content) throws Exception {
        return super.getPostResponse(SOURCE, content);
    }

    public HttpResponse updateRole(String id, String content) throws Exception {
        return super.getPutResponse(String.format("%s/%s", SOURCE, id), content);
    }

    public HttpResponse deleteRole(String id) throws Exception {
        return super.getDeleteResponse(String.format("%s/%s", SOURCE, id));
    }

}
