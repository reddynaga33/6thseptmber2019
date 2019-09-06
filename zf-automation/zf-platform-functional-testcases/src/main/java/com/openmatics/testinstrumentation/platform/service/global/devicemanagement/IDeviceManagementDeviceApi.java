package com.openmatics.testinstrumentation.platform.service.global.devicemanagement;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IDeviceManagementDeviceApi {

    HttpResponse getDevices() throws Exception;

    HttpResponse getDeviceByAssetId(String assetId) throws Exception;

    HttpResponse createDevice(String content) throws Exception;

    HttpResponse deleteDevice(String id) throws Exception;


}
