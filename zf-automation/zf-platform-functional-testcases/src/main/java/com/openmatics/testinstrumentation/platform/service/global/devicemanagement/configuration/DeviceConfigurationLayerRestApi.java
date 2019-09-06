package com.openmatics.testinstrumentation.platform.service.global.devicemanagement.configuration;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DeviceConfigurationLayerRestApi
        extends RestApiBase implements IDeviceConfigurationLayerRestApi {

    public static String SOURCE = "deviceconfiguration/layers";

    public DeviceConfigurationLayerRestApi(IServiceConfiguration property) throws java.net.MalformedURLException {
        super("global", property);
    }

    public HttpResponse getLayers() throws Exception {
        throw new NotImplementedException();
    }

    public HttpResponse getLayer(String id) throws Exception {
        throw new NotImplementedException();
    }

    public HttpResponse createLayer(String content) throws Exception {
        throw new NotImplementedException();
    }

    public HttpResponse updateLayer(String id, String content) throws Exception {
        throw new NotImplementedException();
    }

    public HttpResponse deleteLayer(String id) throws Exception {
        throw new NotImplementedException();
    }
}
