package com.openmatics.testinstrumentation.platform.service.global.deviceconfigurationhierarchy;

import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientConfiguration;
import com.openmatics.testinstrumentation.utils.ResourceLoader;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DeviceConfigurationHierarchyConfiguration {

    public static final String GREMLIN_CONN_STRING_TEMPLATE_PATH = "src/test/resources/environment/%s/gremlin.yaml";
    private static final String DEFAULT_DEVICECONF_RESOURCE_TEMPLATE_00 = "platform/deviceconfigurationhierarchy/ConfigurationTemplate00.json";
    private IEnvironmentConfiguration envConf;
    private ClientConfiguration clientConf;

    public DeviceConfigurationHierarchyConfiguration(PlatformInstrumentation platformInstr){
        this.clientConf = platformInstr.getClientConf();
        this.envConf = platformInstr.getEnvConf();
    }

    public JSONObject loadDefaultConfig() throws Exception {
        JSONObject config = ResourceLoader.LoadAsJson(DEFAULT_DEVICECONF_RESOURCE_TEMPLATE_00);
        return config;
    }


    public Map<String, JSONObject> loadDefaultConfig(Map<String, JSONObject> devices) throws Exception {

        Map<String, JSONObject> configs = new HashMap<String, JSONObject>();
        JSONObject confTemplate = loadDefaultConfig();

        for( Map.Entry<String, JSONObject> entry: devices.entrySet()){
            JSONObject config = new JSONObject(confTemplate.toString());
            config.put("metadata", entry.getKey());
            configs.put(entry.getKey(), config);
        }

        return configs;
    }

    public String getConnectionString2Gremlin(String envKey){
        return  String.format(GREMLIN_CONN_STRING_TEMPLATE_PATH, envKey);
    }


    public static String getValueOfVertexProperty(LinkedHashMap properties, String name){
        String result = null;
        for (Object key : properties.keySet()) {
            if (key.equals(name)){
                ArrayList list = (ArrayList)properties.get(key);
                for(int i = 0 ; i < list.size(); i++){
                    LinkedHashMap values = (LinkedHashMap) list.get(i);
                    for (Object valueName : values.keySet()) {
                        result =(String)values.get(valueName);
                    }
                }
            }
        }
        return result;
    }

}
