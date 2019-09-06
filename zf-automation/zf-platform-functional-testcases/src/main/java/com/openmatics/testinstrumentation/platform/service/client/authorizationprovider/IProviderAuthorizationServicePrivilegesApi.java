package com.openmatics.testinstrumentation.platform.service.client.authorizationprovider;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IProviderAuthorizationServicePrivilegesApi {

    HttpResponse getPrivileges() throws Exception;

    HttpResponse getPrivilegesByExtUserId(String extUserId) throws Exception;

    HttpResponse getPrivilegesByExtAppId(String extAppId) throws Exception;

}
