package com.openmatics.testinstrumentation.platform.service.client.authorizationprovider;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IProviderAuthorizationServiceRolesApi {

    HttpResponse getRoles() throws Exception;

    //returns via database id
    HttpResponse getRole(String Id) throws Exception;

    HttpResponse createRole(String content) throws Exception;

    HttpResponse updateRole(String Id, String content) throws Exception;

    HttpResponse deleteRole(String Id) throws Exception;

}
