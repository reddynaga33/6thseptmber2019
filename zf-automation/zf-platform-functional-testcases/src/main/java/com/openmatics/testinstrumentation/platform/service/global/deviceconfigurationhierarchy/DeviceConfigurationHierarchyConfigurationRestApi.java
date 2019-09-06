package com.openmatics.testinstrumentation.platform.service.global.deviceconfigurationhierarchy;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DeviceConfigurationHierarchyConfigurationRestApi
        extends RestApiBase implements IDeviceConfigurationHierarchyConfigurationApi {

    public static String SOURCE = "CmsClientConfiguration";

    public DeviceConfigurationHierarchyConfigurationRestApi(IServiceConfiguration property) throws java.net.MalformedURLException {
        super("global", property);
    }

    public DeviceConfigurationHierarchyConfigurationRestApi(IServiceConfiguration property, String host) throws java.net.MalformedURLException {
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

}
