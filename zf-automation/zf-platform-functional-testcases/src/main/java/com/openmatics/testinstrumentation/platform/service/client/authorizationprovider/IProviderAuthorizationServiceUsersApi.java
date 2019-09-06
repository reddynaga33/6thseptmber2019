package com.openmatics.testinstrumentation.platform.service.client.authorizationprovider;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IProviderAuthorizationServiceUsersApi {

    HttpResponse getUsers() throws Exception;

    HttpResponse getUserByExtId(String extId) throws Exception;

    //returns by database roleId
    HttpResponse getUserByRoleId(String roleId) throws Exception;

    HttpResponse updateUserByExtId(String extId, String content) throws Exception;

}
