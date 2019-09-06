package com.openmatics.testinstrumentation.platform.service.app.backoffice;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IBackofficeAuthorizationUserApi {

    HttpResponse getUsers() throws Exception;

    HttpResponse getUserByExtId(String extId) throws Exception;

    HttpResponse updateUserByExtId(String extId, String content) throws Exception;

}
