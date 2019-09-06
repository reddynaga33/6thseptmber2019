package com.openmatics.testinstrumentation.platform.service.global.clientsetting;

import com.openmatics.testinstrumentation.platform.service.*;
//import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public class ClientSettingServiceClientPropertiesRestApi
        extends RestApiBase implements IClientSettingServiceClientPropertiesApi {

    public ClientSettingServiceClientPropertiesRestApi(IServiceConfiguration property) throws Exception {
        super("global", property);
    }

    @Override
    public HttpResponse GetClientProperties(String clientId) throws Exception {
        return super.getGetResponse(String.format("clients/%s/properties", clientId));
    }

    @Override
    public  HttpResponse UpdateClientProperties(String clientId, String properties) throws Exception {
        String resource = String.format("clients/%s/properties", clientId);
        return super.getPostResponse(resource, properties);
    }



}
