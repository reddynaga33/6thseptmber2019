package com.openmatics.testinstrumentation.platform.service.global.initation;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;


public class InitiationServiceConfigurationPropertiesRestApi
        extends RestApiBase
        implements IInitiationServiceConfigurationPropertiesApi {

    public static String CLIENTS_SOURCE = "initiate/services/configuration";

    public InitiationServiceConfigurationPropertiesRestApi(IServiceConfiguration property) throws Exception {
        super("global", property);
    }

    @Override
    public HttpResponse CreateClientConfigurationProperties(String clientId, String content) throws Exception {
        return super.getPostResponse(CLIENTS_SOURCE + "/client/" + clientId, content);
    }


}
