package com.openmatics.testinstrumentation.platform.service.global.devicemanagement.configuration;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IDeviceConfigurationConfigurationRestApi {

    HttpResponse getOne(String id) throws Exception;

    HttpResponse create(String content) throws Exception;

    HttpResponse update(String id, String content) throws Exception;

    HttpResponse delete(String id) throws Exception;

    HttpResponse updatePropertiesByMerge(String id, String content) throws Exception;

    HttpResponse updatePropertiesByOverride(String id, String content) throws Exception;

    HttpResponse getFiles(String id) throws Exception;

}
