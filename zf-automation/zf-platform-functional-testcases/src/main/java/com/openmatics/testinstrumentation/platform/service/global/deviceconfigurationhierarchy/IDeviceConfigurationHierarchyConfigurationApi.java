package com.openmatics.testinstrumentation.platform.service.global.deviceconfigurationhierarchy;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IDeviceConfigurationHierarchyConfigurationApi {

    HttpResponse getOne(String id) throws Exception;

    HttpResponse create(String content) throws Exception;

    HttpResponse update(String id, String content) throws Exception;

    HttpResponse delete(String id) throws Exception;

}
