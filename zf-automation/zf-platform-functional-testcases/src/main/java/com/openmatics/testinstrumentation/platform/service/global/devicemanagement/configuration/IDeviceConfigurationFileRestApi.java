package com.openmatics.testinstrumentation.platform.service.global.devicemanagement.configuration;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IDeviceConfigurationFileRestApi {

    HttpResponse getOne(String id) throws Exception;

    HttpResponse create(String content) throws Exception;

    HttpResponse upload(String content) throws Exception;

    HttpResponse attach(String content) throws Exception;

    HttpResponse delete(String id) throws Exception;

}
