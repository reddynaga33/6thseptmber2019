package com.openmatics.testinstrumentation.platform.service.global.initation;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IInitiationServiceConfigurationPropertiesApi {

    HttpResponse CreateClientConfigurationProperties(String clientId, String content) throws Exception;

}
