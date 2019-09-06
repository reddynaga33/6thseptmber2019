package com.openmatics.testinstrumentation.platform.service.global.clientmanagement;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IClientManagementServiceUserApi {

    HttpResponse getUsers() throws Exception;

    HttpResponse getUser(String Id) throws Exception;

    HttpResponse getUserExtendInfo(String Id) throws Exception;

    HttpResponse createUser(String content) throws Exception;

    HttpResponse updateUser(String id, String content) throws Exception;

    HttpResponse deleteUser(String Id) throws Exception;

    HttpResponse createUserGroupClientMembership(String user_id, String client_id) throws Exception;

    HttpResponse deleteUserGroupClientMembership(String user_id, String client_id) throws Exception;

}
