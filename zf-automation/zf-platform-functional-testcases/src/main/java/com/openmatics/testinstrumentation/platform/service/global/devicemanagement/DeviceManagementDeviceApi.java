package com.openmatics.testinstrumentation.platform.service.global.devicemanagement;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;


public class DeviceManagementDeviceApi extends RestApiBase implements IDeviceManagementDeviceApi {

    public static String SOURCE = "devices";

    public DeviceManagementDeviceApi(IServiceConfiguration property) throws Exception {
        super("global", property);
    }

    @Override
    public HttpResponse createDevice(String content) throws Exception{
        return super.getPostResponse(SOURCE, content);
    }

    @Override
    public HttpResponse getDevices() throws Exception{
        return super.getGetResponse(SOURCE);
    }

    @Override
    public HttpResponse getDeviceByAssetId(String assetId) throws Exception{
        return super.getDeleteResponse(String.format("%s/byAssetId/%s", SOURCE, assetId));
    }

    @Override
    public HttpResponse deleteDevice(String id) throws Exception{
        return super.getDeleteResponse(String.format("%s/%s", SOURCE, id));
    }


}
