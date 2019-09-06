package com.openmatics.testinstrumentation.platform.cases.client.create;

import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientManagementApi;
import com.openmatics.testinstrumentation.platform.service.global.initation.InitiationApi;
import com.openmatics.testinstrumentation.platform.service.global.initation.InitiationResultContext;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.utils.ServiceVersionResolver;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import java.util.*;
import java.util.concurrent.TimeoutException;

public class AssignServices2Client {

    private PlatformInstrumentation platformInstr;
    private ClientManagementApi clientManagementApi = null;
    private Map<String, JSONObject> services;
    protected List<String> appIds = new ArrayList<String>();
    private String clientId;
    private String envKey;

    public AssignServices2Client(PlatformInstrumentation platformInstr) throws Exception {
        this.platformInstr = platformInstr;
        this.clientId = platformInstr.getClientConf().getClient().getId();
        this.envKey = platformInstr.getEnvConf().getEnvKey();
    }

    public void loadServicesAssignToClient() throws Exception {
        services = platformInstr.getServiceConf().loadRestClientServices();
    }


    public void deployServicesToClient() throws Exception {
        /**/
        InitiationApi api = new InitiationApi(envKey);
        for (Map.Entry<String, JSONObject> entry : services.entrySet())
        {
            String version = ServiceVersionResolver.getLastVersion(entry.getKey(), entry.getValue());
            System.out.println("Last find version for " + entry.getKey() + " is: ");
            System.out.println(version);
            JSONObject requestResult = null;
            int count = 0;
            while(true){
                if (count == 10) throw new TimeoutException("Waiting for db credentials is over !");
                HttpResponse response = api.getDeploymentsApi().AssignServiceVersion2Client(entry.getKey(), version, clientId);
                Assert.assertEquals(response.getStatus(), 200);
                String requestId = response.getContentAsJson().getString("requestId");
                requestResult = InitiationResultContext.wait4result(api.getRequestApi(), requestId, 1, 3000, 10);
                boolean hasFailCode = requestResult.getJSONArray("results").getJSONObject(0).getJSONObject("payload").has("failCode");
                if (hasFailCode){
                    String failCode = requestResult.getJSONArray("results").getJSONObject(0).getJSONObject("payload").getString("failCode");
                    if (!failCode.equals("DATABASE_CREDENTIALS_FAILED")){
                        break;
                    }
                }
                else {
                    break;
                }
                Thread.sleep(5000);
                count++;
            }
            Assert.assertEquals(((JSONObject) requestResult.getJSONArray("results").get(0)).getString("operation"),"CREATE");
            int waitTime = 10000;
            System.out.println("Now will be wait for docker stabilization in ms: " + waitTime);
            Thread.sleep(waitTime);
        }

    }

    public void assignServicesToGroup() throws Exception {
        String envKey = platformInstr.getEnvConf().getEnvKey();
        clientManagementApi = new ClientManagementApi(envKey);
        for (Map.Entry<String, JSONObject> entry : services.entrySet()) {
            JSONObject servicesConf = platformInstr.getEnvConf().getValues().getJSONObject("services");
            String appId = servicesConf.getJSONObject(entry.getKey()).getString("aadId");
            appIds.add(appId);
            System.out.println(String.format("Try add app %s to the clients group.", entry.getKey()));
            HttpResponse response = clientManagementApi.getApplicationApi().AssignAppToGroup(appId, clientId);
            //TODO repair doc (via doc return 200 and assign entity issue was create cpf-1062)
            Assert.assertEquals(response.getStatus(),204);
        }
    }

    public void getAssignmentAppToGroup() throws Exception {
        for (String appId : appIds) {
            HttpResponse response = clientManagementApi.getApplicationApi().GetAssignementAppToGroup(appId, clientId);
            int status = response.getStatus();
            Assert.assertEquals(status, 200);
            JSONObject client = response.getContentAsJson();
            Assert.assertEquals(client.getString("id"), clientId);
            JSONArray apps = client.getJSONArray("applications");
            Assert.assertEquals(apps.length(), services.size());
            boolean containsApp = false;
            for(Object item :  apps ){
                JSONObject app = (JSONObject)item;
                if (app.getString("appId").equals(appId)) containsApp = true;
            }
            Assert.assertEquals(containsApp, true);
        }
    }


    public void getClientWithAssignedServices()  throws Exception {
        ClientManagementApi api = new ClientManagementApi(envKey);
        HttpResponse response = api.getClientApi().getClientById(clientId);
        JSONArray applications = response.getContentAsJson().getJSONArray("applications");
        Assert.assertEquals(applications.length(),  services.size());
        for (Map.Entry<String, JSONObject> entry : services.entrySet())
        {
            boolean hasApp = false;
            for(int index=0; index < applications.length(); index++){
                String name = applications.getJSONObject(index).getString("displayName").toLowerCase();
                if (entry.getKey().toLowerCase().replace("-","").equals(name)){
                    hasApp = true;
                    break;
                }
            }
            if (!hasApp){
                System.out.println(String.format("No found service %s in client result", entry.getKey()));
            }
        }
    }

}
