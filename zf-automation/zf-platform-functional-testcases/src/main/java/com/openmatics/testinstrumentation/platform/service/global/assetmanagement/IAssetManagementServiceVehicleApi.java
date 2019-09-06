package com.openmatics.testinstrumentation.platform.service.global.assetmanagement;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IAssetManagementServiceVehicleApi {

    HttpResponse getVehicles() throws Exception;

    HttpResponse getVehiclesListViaPost(String content) throws Exception; // ? is correct

    HttpResponse getVehicle(String id) throws Exception;

    HttpResponse createVehicle(String content) throws Exception;

    HttpResponse updateVehicle(String id, String content) throws Exception;

    HttpResponse deleteVehicle(String Id) throws Exception;

}
