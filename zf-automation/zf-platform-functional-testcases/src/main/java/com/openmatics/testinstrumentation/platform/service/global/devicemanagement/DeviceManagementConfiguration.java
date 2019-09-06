package com.openmatics.testinstrumentation.platform.service.global.devicemanagement;

import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientConfiguration;
import com.openmatics.testinstrumentation.utils.ResourceLoader;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class DeviceManagementConfiguration {

    private final String keyGenPath = "/src/lib/iot-hub-key-generator-1.0.0-SNAPSHOT-jar-with-dependencies.jar";
    private IEnvironmentConfiguration envConf;
    private ClientConfiguration clientConf;

    public DeviceManagementConfiguration(IEnvironmentConfiguration envConf, ClientConfiguration clientConf) {
        this.clientConf = clientConf;
        this.envConf = envConf;
    }

    public Map<String, JSONObject> loadDevices(Map<String, JSONObject> vehicles) throws Exception {
        String path = clientConf.getResourcePath("device");
        String deviceTemplate = ResourceLoader.LoadAsString(path);
        Map<String, JSONObject> devices = new HashMap<String, JSONObject>();
            for( Map.Entry<String, JSONObject> entry: vehicles.entrySet()){
                if( entry.getValue().has("id") ){
                    JSONObject device = new JSONObject(deviceTemplate);
                    device.put("assetId", entry.getValue().getString("id"));
                    device.put("boxId", entry.getKey());
                    device.put("boxDescription", entry.getKey());
                    String key = getDevicePrimaryKey(entry.getKey());
                    device.put("primaryKey", key);
                    device.put("secondaryKey", key);
                    device.put("clientIds", entry.getValue().getJSONArray("clientIdList"));
                    devices.put(entry.getKey(), device);
                }
        }
        return devices;
    }



    public String getDevicePrimaryKey(String macAddress) throws Exception {
        String locationLib = System.getProperties().get("basedir") + keyGenPath;
        ProcessBuilder pb = new ProcessBuilder("java","-jar", locationLib, macAddress);
        Process process = pb.start();
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        System.out.println("Standard output of the command device key gen:\n");
        String key = null;
        String line;
        while ((line = stdInput.readLine()) != null)
        {
            System.out.println(line);
            if (line.contains("key")){
                key = line.replace("key:","").replace(" ","");
                key = key.replace("\r","").replace("\n","");
            }
        }
        // read any errors from the attempted command
        System.out.println("Standard error of the command (if any):\n");
        while ((line = stdError.readLine()) != null)
        {
            System.out.println(line);
        }
        System.out.println(String.format("Key for device is: '%s'", key));
        return key;
    }

}
