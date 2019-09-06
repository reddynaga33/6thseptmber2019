package com.openmatics.testinstrumentation.platform.service.global.initation;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public class InitiationServiceDeviceApi extends RestApiBase implements IInitiationServiceDeviceApi {

    private String SOURCE = "initiate/device";

    public InitiationServiceDeviceApi(IServiceConfiguration property) throws Exception {
        super("global", property);
    }

    @Override
    public HttpResponse createDevice(String content) throws Exception {
        return super.getPostResponse(SOURCE, content);
    }

    @Override
    public HttpResponse updateDevice(String id, String content) throws Exception {
        return null;
    }

    @Override
    public HttpResponse deleteDevice(String id) throws Exception {
        return null;
    }

}
