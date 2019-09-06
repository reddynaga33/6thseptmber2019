package com.openmatics.testinstrumentation.platform.service.client.authorizationprovider;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IProviderAuthorizationServiceGroupsApi {

    HttpResponse getGroups() throws Exception;

    //returns via database id
    HttpResponse getGroup(String Id) throws Exception;

    HttpResponse getGroupByExtId(String extId) throws Exception;

    HttpResponse updateGroup(String Id, String content) throws Exception;

}
