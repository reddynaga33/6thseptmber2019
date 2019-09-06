package com.openmatics.testinstrumentation.platform.service.global.assetmanagement;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IAssetManagementServiceAssetApi {

    HttpResponse getAssets() throws Exception;

    HttpResponse getAssetOne(String id) throws Exception;

    HttpResponse updateAsset(String id, String content) throws Exception;


    /*
    HttpResponse getAsset(String id) throws Exception;

    HttpResponse createAsset(String content) throws Exception;

    HttpResponse updateAsset(String id, String content) throws Exception;

    HttpResponse deleteAsset(String id) throws Exception;
    */


}
