package com.openmatics.testinstrumentation.platform.service.global.clientmanagement;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.ServiceApiBase;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;


public class ClientManagementApi extends ServiceApiBase {

    private IClientManagementServiceClientApi clientApi = null;
    private IClientManagementServiceApplicationApi applicationApiApi = null;
    private IClientManagementServiceUserApi userApi = null;
    private IClientManagementServiceUserGroupApi userGroupApi = null;



    public ClientManagementApi(String envKey) throws Exception {
        super(new ServiceConfiguration("client-management-service",new EnvironmentConfiguration(envKey)));
    }

    public ClientManagementApi(IEnvironmentConfiguration envConf) throws Exception {
        super(new ServiceConfiguration("client-management-service", envConf));
    }


    public IClientManagementServiceClientApi getClientApi() throws Exception {
        if (clientApi == null){
            clientApi =  new ClientManagementServiceClientRestApi(getServiceProperty());
        }
        return clientApi;
    }

    public IClientManagementServiceApplicationApi getApplicationApi() throws Exception {
        if (applicationApiApi == null) {
            applicationApiApi =  new ClientManagementServiceApplicationRestApi(getServiceProperty());
        }
        return applicationApiApi;
    }

    public IClientManagementServiceUserApi getUserApi() throws Exception {
        if (userApi == null) {
            userApi =  new ClientManagementServiceUserApi(getServiceProperty());
        }
        return userApi;
    }

    public IClientManagementServiceUserGroupApi getUserGroupApi() throws Exception {
        if (userGroupApi == null) {
            userGroupApi =  new ClientManagementServiceUserGroupApi(getServiceProperty());
        }
        return userGroupApi;
    }

}
