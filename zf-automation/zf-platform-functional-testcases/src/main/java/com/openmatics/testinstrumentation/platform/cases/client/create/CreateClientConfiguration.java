package com.openmatics.testinstrumentation.platform.cases.client.create;

import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.service.global.initation.IInitiationServiceConfigurationPropertiesApi;
import com.openmatics.testinstrumentation.platform.service.global.initation.InitiationApi;
import com.openmatics.testinstrumentation.platform.service.global.initation.InitiationResultContext;
import com.openmatics.testinstrumentation.utils.ResourceLoader;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;
import org.json.JSONObject;
import org.testng.Assert;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class CreateClientConfiguration  {

    private final String sqlQueryTemplate = "select prop_key, prop_value from service_management_service_schema.property where type='CLIENT' and client_id = '%s'";
    private String clientProperties = null;
    private InitiationApi iniApi = null;
    private PlatformInstrumentation platformInstr;
    private String clientId;
    private final DbConnectionFactory connectionFactory;

    public CreateClientConfiguration(PlatformInstrumentation platformInstr, DbConnectionFactory connectionFactory) throws Exception {
        this.platformInstr = platformInstr;
        clientId = platformInstr.getClientConf().getClient().getId();
        iniApi = new InitiationApi(platformInstr.getEnvConf().getEnvKey());

        this.connectionFactory = connectionFactory;
    }

    public void checkClientClientConfigurationNotExist() {
        Map<String, String> values = loadConfigurationExisting(clientId);
        Assert.assertEquals(values.size(), 0, "CmsClient already has some property.");
    }


    public void createClientConfiguration() throws Exception {
        String dhHost =  platformInstr.getEnvConf().getDbHost();
        System.out.println("dbHost" + dhHost);
        String propertyJsonPath = platformInstr.getClientConf().getResourcePath("clientproperties");
        String content = ResourceLoader.LoadAsString(propertyJsonPath);
        clientProperties = String.format(content, dhHost, clientId, clientId);
        IInitiationServiceConfigurationPropertiesApi initPropertyApi = iniApi.getPropertiesApi();
        HttpResponse response = initPropertyApi.CreateClientConfigurationProperties(clientId, clientProperties);

        Assert.assertEquals(response.getStatus(), 200);
        String requestId = response.getContentAsJson().getString("requestId");
        JSONObject requestResponse =  InitiationResultContext.wait4result(iniApi.getRequestApi(), requestId,1);
        Assert.assertEquals(((JSONObject) requestResponse.getJSONArray("results").get(0)).getString("operation"),"CREATE");
    }


    public void checkClientConfigurationPersistInDb() throws Exception {
        Map<String, String> values = loadConfigurationExisting(clientId);
        System.out.println("Count of client CmsClientConfiguration property in DB: " + values.size());
        JSONObject jClientProperty = new JSONObject(clientProperties);
        Assert.assertEquals(values.size(), JSONObject.getNames(jClientProperty).length);
        for (java.util.Map.Entry<String, String> entry : values.entrySet()) {
            Assert.assertEquals(entry.getValue(), jClientProperty.getString(entry.getKey()));
        }
    }

    private Map<String, String> loadConfigurationExisting(String clientId) {
        Map<String, String> values = new HashMap<>();
        //# load properties from db
        String sql = String.format(sqlQueryTemplate, clientId);
        try {
            DbQueryService dbAccess = new DbQueryService(connectionFactory);
            ResultSet rs = dbAccess.executeQuery(sql);
            while (rs.next()){
                values.put(rs.getString(1), rs.getString(2));
            }
            dbAccess.closeQuery(rs);
        } catch (SQLException e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
        return values;
    }
    public void getClientConfigurationByApi() throws Exception {
       throw new NotImplementedException();
    }

}
