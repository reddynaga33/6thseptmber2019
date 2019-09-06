package com.openmatics.testinstrumentation.platform.service.global.devicemanagement.configuration;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DeviceConfigurationDeviceOverviewRestApi
        extends RestApiBase implements IDeviceConfigurationDeviceOverviewRestApi {

    public static String SOURCE = "deviceconfiguration/device";

    public DeviceConfigurationDeviceOverviewRestApi(IServiceConfiguration property) throws java.net.MalformedURLException {
        super("global", property);
    }

    public HttpResponse getEffectiveConfiguration(String id) throws Exception {
        throw new NotImplementedException();
    }
}
