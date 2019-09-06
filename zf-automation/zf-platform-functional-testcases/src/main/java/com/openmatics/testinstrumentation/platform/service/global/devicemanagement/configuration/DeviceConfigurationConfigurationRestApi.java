package com.openmatics.testinstrumentation.platform.service.global.devicemanagement.configuration;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Provide CmsClientConfiguration for DeviceConfiguration rest api therefore
 * double use the CmsClientConfiguration in the name is correct. It has sense.
 */
public class DeviceConfigurationConfigurationRestApi
        extends RestApiBase implements IDeviceConfigurationConfigurationRestApi {

    public static String SOURCE = "deviceconfiguration/configurations";

    public DeviceConfigurationConfigurationRestApi(IServiceConfiguration property) throws java.net.MalformedURLException {
        super("global", property);
    }

    public DeviceConfigurationConfigurationRestApi(IServiceConfiguration property, String host) throws java.net.MalformedURLException {
        super("global", property, host);
    }

    public HttpResponse getOne(String id) throws Exception {
        return super.getGetResponse(String.format("%s/%s", SOURCE, id));
    }

    public HttpResponse create(String content) throws Exception {
        return super.getPostResponse(SOURCE, content);
    }

    public HttpResponse update(String id, String content) throws Exception {
        throw new NotImplementedException();
    }

    public HttpResponse delete(String id) throws Exception {
        throw new NotImplementedException();
    }

    public HttpResponse updatePropertiesByMerge(String id, String content) throws Exception {
        throw new NotImplementedException();
    }

    public HttpResponse updatePropertiesByOverride(String id, String content) throws Exception {
        throw new NotImplementedException();
    }

    public HttpResponse getFiles(String id) throws Exception {
        throw new NotImplementedException();
    }
}
