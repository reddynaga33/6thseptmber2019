package com.openmatics.testinstrumentation.platform.service.global.initation;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IInitiationServiceRequestApi {

    HttpResponse GetResult(String requestId) throws Exception;

}
