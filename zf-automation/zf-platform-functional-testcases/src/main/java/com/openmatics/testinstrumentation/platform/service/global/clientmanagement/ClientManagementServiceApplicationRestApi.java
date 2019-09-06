package com.openmatics.testinstrumentation.platform.service.global.clientmanagement;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;


public class ClientManagementServiceApplicationRestApi extends RestApiBase implements IClientManagementServiceApplicationApi {

    public static String CLIENTS_SOURCE = "applications/%s/clients/%s";


    public ClientManagementServiceApplicationRestApi(IServiceConfiguration property) throws java.net.MalformedURLException {
        super("global", property);
    }

    @Override
    public HttpResponse AssignAppToGroup(String appId, String groupId) throws Exception {
        String resource = String.format(CLIENTS_SOURCE, appId, groupId);
        return super.getPostResponse(resource, EMPTY_CONTENT);
    }

    @Override
    public HttpResponse GetAssignementAppToGroup(String appId, String groupId)throws Exception {
        return super.getGetResponse(String.format(CLIENTS_SOURCE, appId, groupId));
    }

    /**
     *
     * @param appId
     * @param groupId
     * @return
     * @throws Exception
     */
    @Override
    public HttpResponse DeleteAssignementAppToGroup(String appId, String groupId)throws Exception {
        return super.getDeleteResponse(String.format(CLIENTS_SOURCE, appId, groupId));
    }


}
