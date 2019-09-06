package com.openmatics.testinstrumentation.platform.service.global.initation;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public class InitiationServiceVehicleApi extends RestApiBase implements IInitiationServiceVehicleApi {

    private String SOURCE = "initiate/vehicle";

    public InitiationServiceVehicleApi(IServiceConfiguration property) throws Exception {
        super("global", property);
    }

    @Override
    public HttpResponse createVehicle(String content) throws Exception {
        return super.getPostResponse(SOURCE, content);
    }

    @Override
    public HttpResponse updateVehicle(String id, String content) throws Exception {
        return super.getPatchResponse(String.format("%s/%s", SOURCE, id), content);
    }

    @Override
    public HttpResponse deleteVehicle(String id) throws Exception {
        return super.getDeleteResponse(String.format("%s/%s", SOURCE, id));
    }

}
