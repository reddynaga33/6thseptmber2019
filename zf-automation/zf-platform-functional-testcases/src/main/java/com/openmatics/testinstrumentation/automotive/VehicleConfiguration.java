package com.openmatics.testinstrumentation.automotive;

import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.assetmanagement.AssetManagementServiceVehicleApi;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientConfiguration;
import com.openmatics.testinstrumentation.utils.ResourceLoader;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;


public class VehicleConfiguration {

    private final String TEST_OBJECT_SOURCE_DIR = "automotive/AutomatedTest00.%s.json";
    private final int VEHICLE_COUNT = 10;
    private int vehicleStartIndex = 20;
    private IEnvironmentConfiguration envConf;
    private ClientConfiguration clientConf;

    public VehicleConfiguration(IEnvironmentConfiguration envConf, ClientConfiguration clientConf){
        this.clientConf = clientConf;
        this.envConf = envConf;
    }

    public int getVehicleCountConstatnt(){
        return VEHICLE_COUNT;
    }

    public int getVehicleStartIndex(){
        return vehicleStartIndex;
    }

    public Map<String,JSONObject> loadVehicles() throws Exception {
        List<String> clientIds = new ArrayList<>();
        if (clientConf.getClient().getId() != null){
            clientIds.add(clientConf.getClient().getId());
        }
        return loadVehicles(clientIds, false, VEHICLE_COUNT);
    }

    public Map<String,JSONObject> loadVehicles(boolean initIds, int vehicleCount) throws Exception {
        List<String> clientIds = new ArrayList<>();
        clientIds.add(clientConf.getClient().getId());
        return loadVehicles(clientIds, initIds, vehicleCount);
    }

    public Map<String,JSONObject> loadVehicles(boolean initIds) throws Exception {
        return loadVehicles(initIds, VEHICLE_COUNT);
    }

    public Map<String,JSONObject> loadVehicles(List<String> clientIds, boolean initIds) throws Exception {
        return loadVehicles(clientIds, initIds, VEHICLE_COUNT);
    }

    public Map<String,JSONObject> loadVehicles(List<String> clientIds, boolean initIds, int vehicleCount) throws Exception {
        Map<String,JSONObject> vehicles = new HashMap<String, JSONObject>();
        String clientSuffix = clientConf.getClientSuffix();
        if (clientSuffix.length() > 5) throw new RuntimeException("CmsClient suffix " + clientSuffix + " has length over limit 5.");
        int formatLengh = 8 - clientSuffix.length();
        String indexFormatPattern = "%0" + formatLengh + "d";
        System.out.println(indexFormatPattern);
        for(int index=vehicleStartIndex; index < (vehicleCount + vehicleStartIndex); index++ ){
            Map<String, String> vehProp = new HashMap<String,String>();
            String fIndex = String.format(indexFormatPattern, index); // String.format("%05d", index);
            vehProp.put("fIndex", fIndex);
            vehProp.put("clientSuffix", clientConf.getClientSuffix());
            String path = String.format(TEST_OBJECT_SOURCE_DIR, "vehicle");
            JSONObject vehicle = ResourceLoader.LoadAsJson(path, vehProp);
            vehicle.remove("clientIdList");
            vehicle.put("clientIdList", clientIds);
            vehicles.put(vehicle.getString("name"), vehicle);
        }
        if (initIds) putVehicleId(vehicles);
        System.out.println("[tc] Vehicles to creating:");
        System.out.println(vehicles);
        return vehicles;
    }

    private void putVehicleId(Map<String, JSONObject> vehicles) throws Exception {
        HttpResponse res = (new AssetManagementServiceVehicleApi(new ServiceConfiguration("asset-management-service", envConf))).getVehicles();
        JSONArray rVehicles = res.getContentAsJsonArray();
        for( Map.Entry<String, JSONObject> entry: vehicles.entrySet()) {
            for(int i=0; i < rVehicles .length(); i++ ){
                if (entry.getKey().equals(rVehicles.getJSONObject(i).getString("name"))){
                    String id = rVehicles.getJSONObject(i).getString("id");
                    entry.getValue().put("id", id);
                    break;
                }
            }
        }
    }
}
