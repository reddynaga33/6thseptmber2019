package com.openmatics.testinstrumentation.platform.cases.deviceconfigurationhierarchy;

import com.openmatics.testinstrumentation.automotive.AutomotiveInstrumentation;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.service.global.deviceconfigurationhierarchy.*;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.apache.tinkerpop.gremlin.driver.*;
import org.json.*;
import org.testng.Assert;

import java.util.*;
import java.util.concurrent.*;

public class DeviceConfigurationHierarchyTestCase {

    private PlatformInstrumentation platformInstr;
    private AutomotiveInstrumentation automotiveInstr;
    private DeviceConfigurationHierarchyConfiguration deviceTestConf;
    private DeviceConfigurationHierarchyRestApi api;


    public DeviceConfigurationHierarchyTestCase(PlatformInstrumentation platformInstrumentation, AutomotiveInstrumentation automotiveInstr)throws Exception {

        this.platformInstr = platformInstrumentation;
        this.automotiveInstr = automotiveInstr;
        this.deviceTestConf = new DeviceConfigurationHierarchyConfiguration(platformInstr);
        api = new DeviceConfigurationHierarchyRestApi(platformInstr.getEnvConf());
    }


    public Map<String, JSONObject> givenDeviceConfigurations(int configCount) throws Exception {

        Map<String, JSONObject> configs;
        Map<String, JSONObject> vehicles = automotiveInstr.getVehicleConf().loadVehicles(true, configCount);
        if (vehicles.size() != configCount) throw new RuntimeException("Vehicles count for creating don't has value: " + configCount);
        Map<String, JSONObject> devices = platformInstr.getDeviceConf().loadDevices(vehicles);
        if (devices.size() != configCount) throw new RuntimeException("Devices count for creating don't has value: " + configCount);
        configs = deviceTestConf.loadDefaultConfig(devices);
        if (configs.size() != configCount) throw new RuntimeException("Configs count for creating don't has value: " + configCount);
        return configs;
    }


    public List<HttpResponse> whenICreateNewConfigurations(Map<String, JSONObject> configs) throws Exception {

        List<HttpResponse> responseList = new ArrayList<HttpResponse>();
        for( Map.Entry<String, JSONObject> entry: configs.entrySet()){
            String content = entry.getValue().toString();
            HttpResponse response = api.getConfiguration().create(content);
            responseList.add(response);
        }
        return responseList;
    }


    public void thenResponseStatusOfNewConfigurationsIsOk(List<HttpResponse> responseList) throws Exception {

        for (HttpResponse item : responseList){
            String message = String.format("Status of response new CmsClientConfiguration is %s", item.getStatus());
            Assert.assertEquals(item.getStatus(), 200, message);
        }
    }


    public void thenResponseHasCorrectContentWithTheNewConfigurations(List<HttpResponse> responseList) throws Exception {

        for (HttpResponse item : responseList){
            String message = String.format("Status of response new CmsClientConfiguration is %s", item.getStatus());
            assertNewConfig(item.getContentAsJson(), new JSONObject(item.getRequest().getContent()));
        }
    }


    public void thenNewConfigurationsAreStoredInGraphDb(List<HttpResponse> responseList) throws Exception {

        for (HttpResponse item : responseList){
            List<Result> resultList = readGraph(this.getId(item));
            Assert.assertEquals(resultList.size(), 1);
            assertNewConfig(resultList.get(0), item.getRequest().getContent());
        }
    }


    public void thenICanGetNewConfigurationsByRest(List<HttpResponse> responseList) throws Exception {

        for (HttpResponse item : responseList){
            HttpResponse response = api.getConfiguration().getOne(getId(item));
            Assert.assertEquals(response.getStatus(), 200);
            JSONObject newConfig = response.getContentAsJson();
            assertNewConfig(newConfig, new JSONObject(item.getRequest().getContent()));
        }
    }


    private void assertNewConfig(JSONObject newConfig, JSONObject requestConfig) throws Exception {

        Assert.assertEquals(newConfig.getString("CmsClientConfiguration"), requestConfig.getString("CmsClientConfiguration"));
        Assert.assertEquals(newConfig.getString("metadata"), requestConfig.getString("metadata"));
        if (requestConfig.isNull("layerId")){
            Assert.assertTrue(newConfig.isNull("layerId"));
        }
        else {
            Assert.assertEquals(newConfig.getString("layerId"), requestConfig.getString("layerId"));
        }
    }

    private void assertNewConfig(org.apache.tinkerpop.gremlin.driver.Result result, String config){

        JSONObject origConfig = new JSONObject(config);
        LinkedHashMap vertex = (LinkedHashMap)result.getObject();
        LinkedHashMap vProperties = (LinkedHashMap)vertex.get("properties");
        System.out.println("vProperties:");
        System.out.println(vProperties);

        String metadata = DeviceConfigurationHierarchyConfiguration.getValueOfVertexProperty(vProperties, "metadata");
        String configuration = DeviceConfigurationHierarchyConfiguration.getValueOfVertexProperty(vProperties, "CmsClientConfiguration");

        Assert.assertEquals(metadata, origConfig.getString("metadata"));
        Assert.assertEquals(configuration, origConfig.getString("CmsClientConfiguration"));
    }


    private List<Result> readGraph(String id)  throws Exception {

        String connectionConfigFile = deviceTestConf.getConnectionString2Gremlin(platformInstr.getEnvConf().getEnvKey());
        Cluster cluster;
        Client client;
        cluster = Cluster.open(connectionConfigFile);
        client = cluster.connect();
        String query=String.format("g.V('%s')", id);
        List<Result> resultList = null;
        try
        {
            ResultSet results = client.submit(query);
            CompletableFuture<List<Result>> completableFutureResults = results.all();
            resultList = completableFutureResults.get(20, TimeUnit.SECONDS);
            for (Result result : resultList) {
                System.out.println("Query result:");
                System.out.println(result.toString());
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            if (client != null) client.close();
        }
        return resultList;
    }


    private String getId(HttpResponse response) throws Exception {
        return response.getContentAsJson().getString("id");
    }

}

    /*Please not delete for now, it can be use later ?
    @Deprecated()
    private void assertNewConfig(JSONObject newConfig, String boxId) throws Exception {
        // ??? String boxId = newConfig.getJSONObject("properties").getString("boxId");
        JSONObject config = configs.get(boxId);
        // DODO this assert as utils in DeviceManagementConfiguration
        Assert.assertEquals(newConfig.getString("layerId"), config.getString("layerId"));
        // DODO Title can be null
        Assert.assertEquals(newConfig.getString("title"), config.getString("title"));
        JSONObject newProperties = newConfig.getJSONObject("properties");
        JSONObject properties = config.getJSONObject("properties");
        Assert.assertEquals(newProperties.getString("boxId"), properties.getString("boxId"));
        Assert.assertEquals(newProperties.getString("testChar"), properties.getString("testChar"));
        Assert.assertEquals(newProperties.getInt("testInt"), properties.getInt("testInt"));
        Assert.assertTrue(newProperties.isNull("testNull"));
        // DODO test Array
        JSONArray newTestArray = newProperties.getJSONArray("testArray");
        JSONArray testArray = properties.getJSONArray("testArray");
        Assert.assertEquals(newTestArray.length(), testArray.length());
        for(int index=0; index <  newTestArray.length(); index ++){
            Assert.assertEquals(newTestArray.get(index), testArray.get(index));
        }
    }
    */