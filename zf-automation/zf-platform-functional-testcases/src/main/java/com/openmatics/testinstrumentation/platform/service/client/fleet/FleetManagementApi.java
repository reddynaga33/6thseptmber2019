package com.openmatics.testinstrumentation.platform.service.client.fleet;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.ServiceApiBase;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;

public class FleetManagementApi extends ServiceApiBase {

    private IFleetManagementFleetApi fleetApi;
    private String clientKey;


    public FleetManagementApi(String envKey, String clientId) throws Exception {
        super(new ServiceConfiguration("fleet-management-service", new EnvironmentConfiguration(envKey)));
        this.clientKey = clientId;
    }

    public IFleetManagementFleetApi getFleetApi() throws Exception {
        if (fleetApi == null){
            fleetApi =  new FleetManagementFleetApi(getServiceProperty(), clientKey);
        }
        return fleetApi;
    }
}
