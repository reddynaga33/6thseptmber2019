package com.openmatics.testinstrumentation.platform.service.global.initation;

import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public class InitiationServiceRequestApi extends RestApiBase implements IInitiationServiceRequestApi {

    public InitiationServiceRequestApi(IServiceConfiguration property) throws Exception {
        super("global", property);
    }


    @Override
    public HttpResponse GetResult(String requestId) throws Exception {
        return super.getGetResponse("initiate/request/" + requestId);
    }

}
