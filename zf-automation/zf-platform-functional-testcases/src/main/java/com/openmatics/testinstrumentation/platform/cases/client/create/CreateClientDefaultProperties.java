package com.openmatics.testinstrumentation.platform.cases.client.create;

import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.service.global.clientsetting.ClientSettingApi;
import com.openmatics.testinstrumentation.platform.service.global.clientsetting.DefaultClientProperties;
import com.openmatics.testinstrumentation.utils.exception.ObjectExistsException;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;
import org.json.JSONObject;
import org.testng.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class CreateClientDefaultProperties {

    private ClientSettingApi api;
    private DefaultClientProperties defaultProperties = new DefaultClientProperties();
    private String clientId;
    private final DbConnectionFactory connectionFactory;

    public CreateClientDefaultProperties(PlatformInstrumentation platformInstr, DbConnectionFactory connectionFactory) throws Exception {
        this.clientId = platformInstr.getClientConf().getClient().getId();
        this.connectionFactory = connectionFactory;
        api = new ClientSettingApi(platformInstr.getEnvConf().getEnvKey());
    }

    public void checkDefaultClientPropertiesNotExist() throws Exception {
        HttpResponse response = api.getClientApi().GetClientProperties(clientId);
        //{"clientId":"736710c8-8b97-4b9e-b85a-bd4691236e3a","clientProperties":{}}
        JSONObject clientProperties = response.getContentAsJson();
        if (! clientProperties.getString("clientId").equals(clientId))
            throw new RuntimeException(String.format("In property response was expected clientId =%s.", clientId));
        if (clientProperties.getJSONObject("clientProperties").length() != 0 )
            throw new ObjectExistsException("Properties already exists");
    }

    public void updateDefaultClientProperties() throws Exception{
        String content = defaultProperties.getJsonFromFields();
        HttpResponse response = api.getClientApi().UpdateClientProperties(clientId, content);
        Assert.assertEquals(response.getStatus(), 200);
        System.out.println(response.getContentAsString());
     }

    public void getNewDefaultClientPropertiesByRest ()throws Exception {
        HttpResponse response = api.getClientApi().GetClientProperties(clientId);
        Assert.assertEquals(response.getStatus(), 200);
        JSONObject result = response.getContentAsJson();
        Assert.assertEquals(result.getString("clientId"), clientId);
        JSONObject properties = result.getJSONObject("clientProperties");
        Assert.assertEquals(properties.names().length(), DefaultClientProperties.class.getDeclaredFields().length);
        for(Object name : properties.names()){
            String expectedValue = (String)DefaultClientProperties.class.getDeclaredField((String)name).get(defaultProperties);
            System.out.println(String.format("Check values: %s\t%s", properties.getString((String)name), expectedValue));
            Assert.assertEquals(properties.getString((String)name), expectedValue);
        }
    }

    public void checkNewDefaultClientPropertiesPersistInDb()throws Exception {
        String query = "select property_key, property_value from client_settings_service_schema.client_properties where client_id = ?";
        java.util.Map<String, String> values = new HashMap<>();
        try {
            DbQueryService sql = new DbQueryService(connectionFactory);
            ResultSet rs = sql.executeQuery(query, clientId);
            while (rs.next()) {
                values.put(rs.getString(1), rs.getString(2));
            }
            sql.closeQuery(rs);
        } catch (SQLException e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
        Assert.assertEquals(values.size(), DefaultClientProperties.class.getDeclaredFields().length);
        for (java.util.Map.Entry<String, String> entry : values.entrySet())
        {
            String value = (String)DefaultClientProperties.class.getDeclaredField(entry.getKey()).get(defaultProperties);
            System.out.println(String.format("Check values: %s\t%s", entry.getValue(), value));
            Assert.assertEquals(entry.getValue(), value);
        }
    }
}
