package com.openmatics.testinstrumentation.platform.service.client.fleet;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public class FleetManagementFleetApi extends RestApiBase implements IFleetManagementFleetApi {

    public static String SOURCE = "fleets";

    public FleetManagementFleetApi(IServiceConfiguration property, String clientId) throws Exception {
        super(clientId, property);
    }

    public HttpResponse getFleets() throws Exception {
        return super.getGetResponse(SOURCE);
    }

    public HttpResponse getFleet(String id) throws Exception {
        return super.getGetResponse(String.format("%s/%s", SOURCE, id));
    }

    public HttpResponse createFleet(String content) throws Exception {
        return super.getPostResponse(SOURCE, content);
    }

    public HttpResponse updateFleet(String id, String content) throws Exception {
        return super.getPutResponse(String.format("%s/%s", SOURCE, id), content);
    }

    public HttpResponse deleteFleet(String id) throws Exception {
        return super.getDeleteResponse(String.format("%s/%s", SOURCE, id));
    }

    public HttpResponse getFleetAssets(String id) throws Exception {
        return super.getGetResponse(String.format("%s/%s/assets", SOURCE, id));
    }

    // returns net status code 422 for Unprocessable entity
    public HttpResponse updateFleetAssets(String id, String content) throws Exception {
        return super.getPutResponse(String.format("%s/%s/assets", SOURCE, id), content);
    }

}
