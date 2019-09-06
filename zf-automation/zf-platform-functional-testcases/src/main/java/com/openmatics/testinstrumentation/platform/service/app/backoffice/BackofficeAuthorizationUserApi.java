package com.openmatics.testinstrumentation.platform.service.app.backoffice;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public class BackofficeAuthorizationUserApi extends RestApiBase implements IBackofficeAuthorizationUserApi {

    public static String SOURCE = "/authorization/users";

    public BackofficeAuthorizationUserApi(IServiceConfiguration property, String clientId) throws Exception {
        super(clientId, property);
    }

    public HttpResponse getUsers() throws Exception {
        return super.getGetResponse(SOURCE);
    }

    public HttpResponse getUserByExtId(String extId) throws Exception {
        return super.getGetResponse(String.format("%s/%s", SOURCE, extId));
    }

    public HttpResponse updateUserByExtId(String extId, String content) throws Exception {
        return super.getPutResponse(String.format("%s/%s", SOURCE, extId), content);
    }

}
