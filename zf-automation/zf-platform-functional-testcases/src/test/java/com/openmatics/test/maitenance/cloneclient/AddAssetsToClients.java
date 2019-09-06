package com.openmatics.test.maitenance.cloneclient;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.service.global.assetmanagement.AssetManagementApi;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import com.openmatics.testinstrumentation.utils.sql.*;
//import gherkin.deps.com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AddAssetsToClients {

    //For load tests
    public static void main(String[] args) throws Exception
    {
        String envKey = "tauri";
        IEnvironmentConfiguration configuration = new EnvironmentConfiguration(envKey);
        PlatformInstrumentation inst = new PlatformInstrumentation(configuration);
        AssetManagementApi asm = new AssetManagementApi(envKey);
        String originClientId = "8870965f-d5cd-4d2e-a2f9-71eeb65ba2fd";
        String clientId="8b1b5e21-b7ec-4f74-8286-19906ca09c4a";

        List<String> assets = new ArrayList<>();
        String query = "select top 5 * from [fleet_management_service_schema].[asset] order by name";

        try(DbConnectionFactory connFactory = new DbConnectionFactory(inst.getEnvConf().getDbConnectionString(originClientId))){
            try(DbQueryService dbAccess = new DbQueryService(connFactory)) {
                ResultSet rs = dbAccess.executeQuery(query);
                while (rs.next()){
                    assets.add(rs.getString("asset_id"));
                }
            }
        }

        int index = 0;
        for(String assetId : assets){
            System.out.println(assetId);
            JSONObject asset = null;
            HttpResponse response = asm.getAssetApi().getAssetOne(assetId);
            if (response.getStatus() == 200)
            {
                asset = response.getContentAsJson();
                JSONArray clientIdList = asset.getJSONArray("clientIdList");
                if (! hasStringValue(clientIdList, clientId) )
                {
                    System.out.println("Add client");
                    clientIdList.put(clientId);
                    // Update
                    System.out.println(asset);
                    HttpResponse updateResponse = asm.getAssetApi().updateAsset(assetId, asset.toString());
                    System.out.println("Update status of response: " + updateResponse.getStatus());
                }
            }
        }
    }


    public static boolean hasStringValue(JSONArray array, String value){
        for(Object item : array){
            String origin = (String) item;
            if (origin.equals(value)) return true;
        }
        return false;
    }

}
