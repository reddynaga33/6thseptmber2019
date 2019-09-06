package com.openmatics.testinstrumentation.platform.service.global.devicemanagement.configuration;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DeviceConfigurationFileRestApi
     extends RestApiBase implements IDeviceConfigurationFileRestApi {

    public static String SOURCE = "deviceconfiguration/files";

    public DeviceConfigurationFileRestApi(IServiceConfiguration property) throws java.net.MalformedURLException {
        super("global", property);
    }

    public HttpResponse getOne(String id) throws Exception {
        throw new NotImplementedException();
    }

    public HttpResponse create(String content) throws Exception {
        throw new NotImplementedException();
    }

    public HttpResponse upload(String content) throws Exception {
        throw new NotImplementedException();
    }

    public HttpResponse attach(String content) throws Exception {
        throw new NotImplementedException();
    }

    public HttpResponse delete(String id) throws Exception {
        throw new NotImplementedException();
    }



}
