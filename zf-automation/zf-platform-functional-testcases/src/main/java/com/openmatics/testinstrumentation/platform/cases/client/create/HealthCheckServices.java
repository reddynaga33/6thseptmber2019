package com.openmatics.testinstrumentation.platform.cases.client.create;

import com.openmatics.testinstrumentation.platform.service.global.healthcheck.HealthCheckServiceApi;
import com.openmatics.testinstrumentation.platform.service.global.healthcheck.IHealthCheckServiceApi;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import java.util.Map;

public class HealthCheckServices {

    private final int WAIT_SLEEP_TIME_MS= 10000;
    private final int COUNT_WAIT_SLEEP_CYCLES= 60;
    private PlatformInstrumentation platformInstr;
    private Map<String, JSONObject> services;
    private String clientId;
    private IHealthCheckServiceApi api;

    public HealthCheckServices(PlatformInstrumentation platformInstr) throws Exception {
        this.platformInstr = platformInstr;
        this.clientId = platformInstr.getClientConf().getClient().getId();
        this.api = new HealthCheckServiceApi(platformInstr.getEnvConf());
    }

    public void getAssignmentServices() throws Exception {
        services = platformInstr.getServiceConf().loadRestClientServices();
    }

    public void callServicesHealthCheck() throws Exception
    {
        for (Map.Entry<String, JSONObject> entry : services.entrySet())
        {
            callServicesHealthCheck(clientId, entry.getKey());
        }
    }

    public void callServicesHealthCheck(String clientID, String serviceKey) throws Exception {
        HttpResponse rr = null;
        int count = 0;
        while(count++ < COUNT_WAIT_SLEEP_CYCLES){
            System.out.println(String.format("Try get %s health check response %s/%s steps %s ms",
                    serviceKey ,count, COUNT_WAIT_SLEEP_CYCLES, WAIT_SLEEP_TIME_MS));
            try{
                rr = api.getHealthCheck(clientId, serviceKey);
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("Occurs exceptions: " + e.getMessage());
                System.out.println("Try again");
            }
            if (rr != null){
                if(rr.getStatus() == 200) break;
            }
            Thread.sleep(WAIT_SLEEP_TIME_MS);
        }

        if (rr == null) throw new AssertionError("Could not get health check response!");
        Assert.assertEquals(rr.getStatus(),200);
        Assert.assertEquals(rr.getContentAsString().toLowerCase(),"ok");
    }

}
