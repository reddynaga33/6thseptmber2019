package com.openmatics.testinstrumentation.platform.service.global.servicemanagement;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.ServiceApiBase;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusClient;

public class ServiceManagementApi extends ServiceApiBase {

    private IServiceManagementServiceServicesApi serviceApi = null;
    private IServiceManagementServiceDeploymentsApi deploymentApi = null;
    private ServiceBusClient serviceBusClient;

    public ServiceManagementApi(String envKey) throws Exception {
        this(new EnvironmentConfiguration(envKey));
    }

    public ServiceManagementApi(IEnvironmentConfiguration envConf) throws Exception {
        super(new ServiceConfiguration("service-management-service", envConf));
        serviceBusClient = new ServiceBusClient(envConf.getServiceBusProperty().getConnectionString());
    }


    public IServiceManagementServiceServicesApi getServiceApi() throws Exception {
        if (serviceApi == null){
            serviceApi =  new ServiceManagementServiceServicesApi(getServiceProperty(), serviceBusClient);
        }
        return serviceApi;
    }

    public IServiceManagementServiceDeploymentsApi getDeploymentApi() throws Exception {
        if (deploymentApi == null){
            deploymentApi = new ServiceManagementServiceDeploymentsApi(getServiceProperty());
        }
        return deploymentApi;
    }

}
