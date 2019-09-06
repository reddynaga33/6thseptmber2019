package com.openmatics.testinstrumentation.platform.service.app.backoffice;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public class BackofficeAuthorizationPrivilegeApi extends RestApiBase implements IBackofficeAuthorizationPrivilegeApi {

    public static String SOURCE = "/authorization/privileges";

    public BackofficeAuthorizationPrivilegeApi(IServiceConfiguration property, String clientId) throws Exception {
        super(clientId, property);
    }

    public HttpResponse getPrivileges() throws Exception {
        return super.getGetResponse(SOURCE);
    }

}
