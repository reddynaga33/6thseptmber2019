package com.openmatics.testinstrumentation.platform.cases.asset;

import com.openmatics.testinstrumentation.automotive.AutomotiveInstrumentation;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.initation.*;
import com.openmatics.testinstrumentation.platform.service.global.assetmanagement.*;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.json.*;
import org.testng.Assert;
import java.util.*;


public class CreateVehicles {

    public static Map<String, JSONObject> vehicles;
    protected AssetManagementApi assetApi;
    protected InitiationApi initiationApi;
    private PlatformInstrumentation platformInstr;
    private AutomotiveInstrumentation automotiveInstr;


    public CreateVehicles(PlatformInstrumentation platformInstr, AutomotiveInstrumentation automotiveInstr){
        this.platformInstr = platformInstr;
        this.automotiveInstr = automotiveInstr;
    }

    @Deprecated
    public CreateVehicles(IEnvironmentConfiguration envConf,String clientSuffix) throws Exception {
        this.platformInstr = new PlatformInstrumentation(envConf, clientSuffix);
        //platformInstr.getClientConf().getClient().getId();
    }


    public void getVehiclesToCreate() throws Exception {
        vehicles = automotiveInstr.getVehicleConf().loadVehicles();
    }


    public void checkVehiclesToCreateNotExist() throws Exception {
        assetApi = new AssetManagementApi(platformInstr.getEnvConf().getEnvKey());
        HttpResponse response = assetApi.getVehicleApi().getVehicles();
        JSONArray existingVehicles = response.getContentAsJsonArray();
        for( Map.Entry<String, JSONObject> entry: vehicles.entrySet()){
            for(int i=0; i < existingVehicles.length(); i++){
                String assertMessage = String.format("Object with name %s already exists.", entry.getKey());
                org.testng.Assert.assertNotEquals(entry.getKey(), existingVehicles.getJSONObject(i).getString("name"), assertMessage);
            }

        }
    }

    public void checkAssetsOfVehiclesToCreateNotExist()  throws Exception {
        assetApi = new AssetManagementApi(platformInstr.getEnvConf().getEnvKey());
        HttpResponse response = assetApi.getAssetApi().getAssets();
        JSONArray existingAssets = response.getContentAsJsonArray();
        for( Map.Entry<String, JSONObject> entry: vehicles.entrySet()){
            for(int i=0; i < existingAssets.length(); i++){
                String assertMessage = String.format("Object with name %s already exists.", entry.getKey());
                org.testng.Assert.assertNotEquals(entry.getKey(), existingAssets.getJSONObject(i).getString("name"), assertMessage);
            }
        }
    }


    public void createVehicles() throws Exception {
        /**/
        initiationApi = new InitiationApi(platformInstr.getEnvConf().getEnvKey());
        for( Map.Entry<String, JSONObject> entry: vehicles.entrySet()){
            HttpResponse response = initiationApi.getVehicleApi().createVehicle(entry.getValue().toString());
            String requestId = response.getContentAsJson().getString("requestId");
            JSONObject requestResult = InitiationResultContext.wait4result(initiationApi.getRequestApi(), requestId, 1 );
            String resultOperation = requestResult.getJSONArray("results").getJSONObject(0).getString("operation");
            String assertMessage = String.format("Unexpected operation for request id '%s.", requestId);
            Assert.assertEquals(resultOperation, "CREATE", assertMessage);
        }

    }

    public void getNewVehicles() throws Exception {
        HttpResponse response = assetApi.getVehicleApi().getVehicles();
        JSONArray restVehicles = response.getContentAsJsonArray();
        for( Map.Entry<String, JSONObject> entry: vehicles.entrySet()){
            boolean exists = false;
            for(int i=0; i < restVehicles.length(); i++){
                JSONObject rVehicle = restVehicles.getJSONObject(i);
                if (entry.getKey().equals(rVehicle.getString("name"))){
                    exists = true;
                    entry.getValue().put("id", rVehicle.getString("id"));
                }
            }
            String message = String.format("Vehicle was create vehicle with name %s.", entry.getKey());
            Assert.assertTrue(exists, message);
        }
    }

    public void getAssetsOfNewVehicles() throws Exception {
        HttpResponse response = assetApi.getAssetApi().getAssets();
        JSONArray existingAssets = response.getContentAsJsonArray();
        for( Map.Entry<String, JSONObject> entry: vehicles.entrySet()){
            boolean exists = false;
            for(int i=0; i < existingAssets.length(); i++){
                JSONObject existAsset = existingAssets.getJSONObject(i);
                if (entry.getKey().equals(existAsset.getString("name"))){
                    exists = true;
                    entry.getValue().put("id",existAsset.getString("id"));
                }
            }
            String message = String.format("Assert was create vehicle with name %s.", entry.getKey());
            Assert.assertTrue(exists, message);
        }
    }

}

