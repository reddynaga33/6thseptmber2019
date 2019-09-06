package com.openmatics.testinstrumentation.platform.service.global.devicemanagement.configuration;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IDeviceConfigurationDeviceOverviewRestApi {

    HttpResponse getEffectiveConfiguration(String id) throws Exception;

}
