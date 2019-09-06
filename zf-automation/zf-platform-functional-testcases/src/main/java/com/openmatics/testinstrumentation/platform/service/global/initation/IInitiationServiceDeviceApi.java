package com.openmatics.testinstrumentation.platform.service.global.initation;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IInitiationServiceDeviceApi {

    HttpResponse createDevice(String content) throws Exception;

    HttpResponse updateDevice(String id, String content) throws Exception;

    HttpResponse deleteDevice(String id) throws Exception;

}
