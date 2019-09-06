package com.openmatics.testinstrumentation.platform.service.global.servicemanagement;

import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientConfiguration;
import com.openmatics.testinstrumentation.utils.ResourceLoader;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.json.*;

import java.util.*;


public class ServiceConfiguration {

    private IEnvironmentConfiguration envConf;
    private ClientConfiguration clientConf;


    public ServiceConfiguration(IEnvironmentConfiguration envConf, ClientConfiguration clientConf){
        this.clientConf = clientConf;
        this.envConf = envConf;
    }


    public Map<String, JSONObject> loadRestClientServices() throws Exception {
        Map<String, JSONObject> servicesMap = new HashMap<String, JSONObject>();
        String path = ClientConfiguration.resolveResourcePath("services2client");
        JSONArray services2assign  = ResourceLoader.loadAsJsonArray(path);
        int servicesCount = services2assign.length();
        ServiceManagementApi api = new ServiceManagementApi(envConf);
        HttpResponse response = api.getServiceApi().getServices();
        JSONArray servicesFromRest = response.getContentAsJsonArray();
        for (int index = 0; index < servicesFromRest.length(); index ++){
            JSONObject item = (JSONObject)servicesFromRest.get(index);
            String artifactId = item.getString("artifactId");
            for ( int y = 0; y < services2assign.length(); y++){
               if (services2assign.get(y).equals(artifactId)){
                   services2assign.remove(y);
                   servicesMap.put(artifactId, item);
               }
            }
        }
        if (services2assign.length() != 0) throw new RuntimeException("Unexpected count of loading service");
        if (servicesMap.size() != servicesCount) throw new RuntimeException("Unexpected count of loading service");
        System.out.println("Count of service assign to group: " + servicesMap.size());
        System.out.println(servicesMap);
        return servicesMap;
    }

}
