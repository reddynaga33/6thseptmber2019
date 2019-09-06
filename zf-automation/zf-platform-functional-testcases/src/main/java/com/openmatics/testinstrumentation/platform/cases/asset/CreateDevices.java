package com.openmatics.testinstrumentation.platform.cases.asset;

import com.openmatics.testinstrumentation.automotive.AutomotiveInstrumentation;
import com.openmatics.testinstrumentation.platform.service.global.devicemanagement.*;
import com.openmatics.testinstrumentation.platform.service.global.initation.*;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.json.*;
import org.testng.*;
import java.util.*;

public class CreateDevices {

    public static Map<String, JSONObject> devices;
    protected InitiationApi initiationApi;
    protected DeviceManagementApi deviceApi;
    private PlatformInstrumentation platformInstr;
    private AutomotiveInstrumentation automotiveInstr;

    public CreateDevices(PlatformInstrumentation PlatformInstrumentation, AutomotiveInstrumentation automotiveInstr)throws Exception {
        this.platformInstr = PlatformInstrumentation;
        this.automotiveInstr = automotiveInstr;
    }

    public void loadDevicesToCreate() throws Exception {
        if (CreateVehicles.vehicles == null) {
            CreateVehicles.vehicles = automotiveInstr.getVehicleConf().loadVehicles(true);
        }
        devices = platformInstr.getDeviceConf().loadDevices(CreateVehicles.vehicles);
        System.out.println("Device to creating:");
        System.out.println(devices);
    }

    public void checkDevicesToCreateNotExist() throws Exception {
        deviceApi = new DeviceManagementApi(platformInstr.getEnvConf().getEnvKey());
        HttpResponse response = deviceApi.getApi().getDevices();
        JSONArray existingDevices = response.getContentAsJsonArray();
        for( Map.Entry<String, JSONObject> entry: devices.entrySet()){
            for(int i=0; i < existingDevices.length(); i++){
                Assert.assertNotEquals(entry.getValue(), existingDevices.getJSONObject(i).getString("id"));
            }
        }
    }

    public void createDevices() throws Exception {
        initiationApi = new InitiationApi(platformInstr.getEnvConf().getEnvKey());
        for( Map.Entry<String, JSONObject> entry: devices.entrySet()){
            HttpResponse response = initiationApi.getDeviceApi().createDevice(entry.getValue().toString());
            String requestId = response.getContentAsJson().getString("requestId");
            JSONObject requestResult = InitiationResultContext.wait4result(initiationApi.getRequestApi(), requestId, 1 );
            String resultOperation = requestResult.getJSONArray("results").getJSONObject(0).getString("operation");
            String assertMessage = String.format("Unexpected operation for request id '%s.", requestId);
            Assert.assertEquals(resultOperation, "CREATE", assertMessage);
        }
    }

    public void getNewDevices() throws Exception {
        if (deviceApi == null)deviceApi = new DeviceManagementApi(platformInstr.getEnvConf().getEnvKey());
        HttpResponse response = deviceApi.getApi().getDevices();
        JSONArray restDevices = response.getContentAsJsonArray();
        for( Map.Entry<String, JSONObject> entryDevice: devices.entrySet()){
            boolean exists = false;
            for(int i=0; i < restDevices.length(); i++){
                JSONObject restDevice = restDevices.getJSONObject(i);
                if (entryDevice.getKey().equals(restDevice.getString("id"))){
                    exists = true;
                    entryDevice.getValue().put("id",restDevice.getString("id"));
                    assertCompareDevice(restDevice, entryDevice.getValue());
                    break;
                }
            }
            Assert.assertTrue(exists);
        }
    }


    /* ------May be next -------- */
    public void trySendMessageToIothub() throws Exception {
        //TODO implemantation
    }

    private void assertCompareDevice(JSONObject d1, JSONObject d2){
        //TODO  implemantation
    }

}

