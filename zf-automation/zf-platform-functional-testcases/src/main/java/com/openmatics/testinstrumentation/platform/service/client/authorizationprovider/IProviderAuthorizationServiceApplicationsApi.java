package com.openmatics.testinstrumentation.platform.service.client.authorizationprovider;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IProviderAuthorizationServiceApplicationsApi {

    HttpResponse getApplications() throws Exception;

    //returns via database id
    HttpResponse getApplication(String Id) throws Exception;

    HttpResponse getApplicationByExtId( String extId) throws Exception;

    HttpResponse getApplicationByExtAppId( String extAppId) throws Exception;

    HttpResponse updateApplication(String Id, String content) throws Exception;

}
