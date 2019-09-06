package com.openmatics.testinstrumentation.platform.service.global.assetmanagement;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.ServiceApiBase;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;

public class AssetManagementApi extends ServiceApiBase {

    private IAssetManagementServiceVehicleApi vehicleApi = null;
    private IAssetManagementServiceAssetApi assetsApi = null;

    public AssetManagementApi(String envKey) throws Exception {
        super(new ServiceConfiguration("asset-management-service", new EnvironmentConfiguration(envKey)));
    }

    public AssetManagementApi(IEnvironmentConfiguration envConf) throws Exception {
        super(new ServiceConfiguration("asset-management-service", envConf));
    }

    public IAssetManagementServiceVehicleApi getVehicleApi() throws Exception {
        if (vehicleApi == null){
            vehicleApi =  new AssetManagementServiceVehicleApi(getServiceProperty());
        }
        return vehicleApi;
    }

    public IAssetManagementServiceAssetApi getAssetApi() throws Exception {
        if (assetsApi == null){
            assetsApi =  new AssetManagementServiceAssetApi(getServiceProperty());
        }
        return assetsApi;
    }
}
