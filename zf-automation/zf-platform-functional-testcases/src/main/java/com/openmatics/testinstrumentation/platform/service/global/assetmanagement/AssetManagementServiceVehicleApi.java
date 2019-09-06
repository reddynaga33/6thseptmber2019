package com.openmatics.testinstrumentation.platform.service.global.assetmanagement;

import com.openmatics.testinstrumentation.platform.service.*;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;


public class AssetManagementServiceVehicleApi extends RestApiBase implements IAssetManagementServiceVehicleApi {

    public static String SOURCE = "vehicles";

    public AssetManagementServiceVehicleApi(IServiceConfiguration property) throws Exception {
        super("global", property);
    }

    @Override
    public HttpResponse getVehicles() throws Exception{
        return super.getGetResponse(SOURCE);
    }

    @Override
    public HttpResponse getVehiclesListViaPost(String content) throws Exception{
        return super.getPostResponse(String.format("%s/list", SOURCE), content);
    }

    @Override
    public HttpResponse getVehicle(String id) throws Exception{
        return super.getGetResponse(String.format("%s/%s", SOURCE, id));
    }

    @Override
    public HttpResponse createVehicle(String content) throws Exception{
        return super.getPostResponse( SOURCE, content);
    }

    @Override
    public HttpResponse updateVehicle(String id, String content) throws Exception{
        return super.getPatchResponse(String.format("%s/%s", SOURCE, id), content);
    }

    @Override
    public HttpResponse deleteVehicle(String id) throws Exception{
        return super.getDeleteResponse(String.format("%s/%s", SOURCE, id));
    }

}
