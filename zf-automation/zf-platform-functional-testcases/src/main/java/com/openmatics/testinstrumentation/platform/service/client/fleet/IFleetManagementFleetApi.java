package com.openmatics.testinstrumentation.platform.service.client.fleet;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IFleetManagementFleetApi {

        HttpResponse getFleets() throws Exception;

        HttpResponse getFleet(String id) throws Exception;

        HttpResponse createFleet(String content) throws Exception;

        HttpResponse updateFleet(String id, String content) throws Exception;

        HttpResponse deleteFleet(String id) throws Exception;

        HttpResponse getFleetAssets(String id) throws Exception;

        // returns net status code 422 for Unprocessable entity
        HttpResponse updateFleetAssets(String id, String content) throws Exception;

}
