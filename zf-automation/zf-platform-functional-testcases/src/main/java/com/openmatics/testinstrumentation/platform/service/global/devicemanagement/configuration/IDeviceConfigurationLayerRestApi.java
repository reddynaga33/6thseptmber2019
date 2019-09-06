package com.openmatics.testinstrumentation.platform.service.global.devicemanagement.configuration;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IDeviceConfigurationLayerRestApi {

    HttpResponse getLayers() throws Exception;

    HttpResponse getLayer(String id) throws Exception;

    HttpResponse createLayer(String content) throws Exception;

    HttpResponse updateLayer(String id, String content) throws Exception;

    HttpResponse deleteLayer(String id) throws Exception;

}
