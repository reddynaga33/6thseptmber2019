package com.openmatics.testinstrumentation.platform.service.global.clientmanagement;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;


public class ClientManagementServiceUserApi extends RestApiBase implements IClientManagementServiceUserApi {

    public static String SOURCE = "users";


    public ClientManagementServiceUserApi(IServiceConfiguration property) throws Exception {
        super("global", property);
    }

    @Override
    public HttpResponse getUser(String id)throws Exception{
        return super.getGetResponse(String.format("%s/%s", SOURCE, id));
    }

    @Override
    public HttpResponse getUserExtendInfo(String id) throws Exception {
       return  super.getGetResponse(String.format("%s/%s?extendedInfo=true", SOURCE, id));
    }

    @Override
    public HttpResponse getUsers()throws Exception{
        return super.getGetResponse(SOURCE);
    }

    @Override
    public HttpResponse createUser(String content)throws Exception{
        return super.getPostResponse(SOURCE, content);
    }

    @Override
    public HttpResponse updateUser(String id, String content)throws Exception{
        return super.getPatchResponse(String.format("%s/%s", SOURCE, id), content);
    }

    @Override
    public HttpResponse deleteUser(String id)throws Exception{
        return super.getDeleteResponse(String.format("%s/%s", SOURCE, id));
    }

    @Override
    public HttpResponse createUserGroupClientMembership(String userId, String clientId) throws Exception {
        return super.getPostResponse(String.format("%s/%s/clients/%s", SOURCE, userId, clientId), EMPTY_CONTENT);
    }

    @Override
    public HttpResponse deleteUserGroupClientMembership(String userId, String clientId) throws Exception{
        return super.getDeleteResponse(String.format("%s/%s/clients/%s", SOURCE, userId, clientId));
    }



}
