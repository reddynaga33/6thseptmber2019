package com.openmatics.testinstrumentation.platform.service.app.backoffice;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IBackofficeAuthorizationRoleApi {

    HttpResponse getRoles() throws Exception;

    HttpResponse getRole(String extId) throws Exception;

    HttpResponse createRole(String content) throws Exception;

    HttpResponse updateRole(String id, String content) throws Exception;

    HttpResponse deleteRole(String id) throws Exception;

}
