package com.openmatics.testinstrumentation.platform.service.global.clientmanagement;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

//TODO: Add javaDoc, rename method according to java standard, fix typing errors
//TODO: remove too general exception
//TODO: the method shouldn't return httpResponse
public interface IClientManagementServiceApplicationApi {

    HttpResponse AssignAppToGroup(String appId, String groupId)throws Exception;

    HttpResponse GetAssignementAppToGroup(String appId, String groupId)throws Exception;

    HttpResponse DeleteAssignementAppToGroup(String appId, String groupId)throws Exception;

}
