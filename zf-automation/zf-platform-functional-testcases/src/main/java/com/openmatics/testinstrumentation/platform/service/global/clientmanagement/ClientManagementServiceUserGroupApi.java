package com.openmatics.testinstrumentation.platform.service.global.clientmanagement;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;


public class ClientManagementServiceUserGroupApi extends RestApiBase implements IClientManagementServiceUserGroupApi {

    public static String SOURCE = "userGroups";


    public ClientManagementServiceUserGroupApi(IServiceConfiguration property) throws Exception {
        super("global", property);
    }


    public HttpResponse getUserGroups()throws Exception {
        return super.getGetResponse(SOURCE);
    }

    public HttpResponse getUserGroups(String queryParamClientId) throws Exception {
        return super.getGetResponse(String.format("%s?clientId=%s", SOURCE, queryParamClientId));
    }

    public HttpResponse getUserGroup(String id)throws Exception {
        return super.getGetResponse(String.format("%s/%s", SOURCE, id));
    }

    public HttpResponse createUserGroup(String content)throws Exception {
        return super.getPostResponse(SOURCE, content);
    }

    public HttpResponse updateUserGroup(String id, String content)throws Exception {
        return super.getPutResponse(String.format("%s/%s", SOURCE, id), content);
    }

    public HttpResponse deleteUserGroup(String id)throws Exception {
        return super.getDeleteResponse(String.format("%s/%s", SOURCE, id));
    }





}
