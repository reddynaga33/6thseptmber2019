package com.openmatics.testinstrumentation.platform.cases.client.create;

import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.model.CmsClient;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.*;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.json.*;
import org.testng.Assert;

// TODO check creation time on cases.client (resolve Utc, diferent time on server and also next ...)
public class CreateClient  {

    private PlatformInstrumentation platformInstr;
    private ClientManagementApi api;
    private JSONObject defaultClient;
    private String newClientId;


    public CreateClient(PlatformInstrumentation platformInstr){
        this.platformInstr = platformInstr;
    }

    public void clientDoesNotExist() throws Exception {
        api = new ClientManagementApi(platformInstr.getEnvConf().getEnvKey());
        defaultClient = platformInstr.getClientConf().getTemplate().getValue();
        HttpResponse response = api.getClientApi().getClients();
        Assert.assertEquals(response.getStatus(), 200);
        JSONArray clients = response.getContentAsJsonArray();
        for(int i=0; i<clients.length(); i++){
            JSONObject jObject = clients.getJSONObject(i);
            Assert.assertNotEquals(jObject.getString("name"), defaultClient.getString("name"));
        }
    }

    public String createNewClient() throws  Exception {
        HttpResponse response = api.getClientApi().createClient(defaultClient.toString());
        newClientId = response.getContentAsString();
        Assert.assertEquals(response.getStatus(), 200);
        return newClientId;
    }


    public void getNewClient() throws  Exception {
        CmsClient client = platformInstr.getClientConf().getClient();
        CmsClient clientFromTemplate = JsonUtils.fromJson(defaultClient.toString(), CmsClient.class);
        clientFromTemplate.setId(client.getId());
        clientFromTemplate.setCreationTime(client.getCreationTime());
        Assert.assertEquals(client.getName(), clientFromTemplate.getName());
    }

}
