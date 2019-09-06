package com.openmatics.testinstrumentation.platform.service.global.clientmanagement;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IClientManagementServiceUserGroupApi {

    HttpResponse getUserGroups()throws Exception;

    HttpResponse getUserGroups(String paramClientId) throws Exception;

    HttpResponse getUserGroup(String id)throws Exception;

    HttpResponse createUserGroup(String content)throws Exception;

    HttpResponse updateUserGroup(String id, String content)throws Exception;

    HttpResponse deleteUserGroup(String id)throws Exception;

}
