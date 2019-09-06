package com.openmatics.testinstrumentation.platform.service.global.clientsetting;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IClientSettingServiceClientPropertiesApi {

    HttpResponse GetClientProperties(String clientId)throws Exception;

    HttpResponse UpdateClientProperties(String clientId, String properties)throws Exception;

}
