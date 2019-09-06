package com.openmatics.testinstrumentation.platform.service.app.backoffice;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IBackofficeAuthorizationPrivilegeApi {

    HttpResponse getPrivileges() throws Exception;

}
