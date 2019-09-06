package com.openmatics.testinstrumentation.platform.service.global.assetmanagement;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;


public class AssetManagementServiceAssetApi extends RestApiBase implements IAssetManagementServiceAssetApi {

    public static String SOURCE = "assets";

    public AssetManagementServiceAssetApi(IServiceConfiguration property) throws Exception {
        super("global", property);
    }

    @Override
    public HttpResponse getAssets() throws Exception{
        return super.getGetResponse(SOURCE);
    }

    @Override
    public HttpResponse getAssetOne(String id) throws Exception{
        return super.getGetResponse(SOURCE + "/" + id);
    }

    @Override
    public HttpResponse updateAsset(String id, String content) throws Exception
    {
        return super.getPutResponse(SOURCE + "/" + id, content);
    }

}
