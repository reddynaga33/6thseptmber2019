package com.openmatics.testinstrumentation.platform.service.global.healthcheck;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IHealthCheckServiceApi {

    HttpResponse getHealthCheck(String clientId, String artifactId) throws Exception;

}
