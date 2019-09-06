package com.openmatics.testinstrumentation.platform.service.global.initation;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.ServiceApiBase;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;

public class InitiationApi extends ServiceApiBase {

    private IInitiationServiceConfigurationPropertiesApi propertiesApi = null;
    private InitiationServiceRequestApi requestApi = null;
    private IInitiationServiceDeploymentsApi deploymentsApi = null;
    private IInitiationServiceVehicleApi vehicleApi = null;
    private IInitiationServiceDeviceApi deviceApi = null;
    private IInitiationServiceServiceVersionApi versionApi = null;

    public InitiationApi(String envKey) throws Exception {
        super(new ServiceConfiguration("initiation-service", new EnvironmentConfiguration(envKey)));
    }

    public InitiationApi(IEnvironmentConfiguration envConf) throws Exception {
        super(new ServiceConfiguration("initiation-service", envConf));
    }


    public IInitiationServiceConfigurationPropertiesApi getPropertiesApi() throws Exception {
        if (propertiesApi == null) {
            propertiesApi = new InitiationServiceConfigurationPropertiesRestApi(getServiceProperty());
        }
        return propertiesApi;
    }

    public IInitiationServiceRequestApi getRequestApi() throws Exception {
        if (requestApi == null) {
            requestApi = new InitiationServiceRequestApi(getServiceProperty());
        }
        return requestApi;
    }

    public IInitiationServiceDeploymentsApi getDeploymentsApi() throws Exception {
        if (deploymentsApi == null) {
            deploymentsApi = new InitiationServiceDeploymentsApi(getServiceProperty());
        }
        return deploymentsApi;
    }

    public IInitiationServiceVehicleApi getVehicleApi() throws Exception {
        if (vehicleApi == null) {
            vehicleApi = new InitiationServiceVehicleApi(getServiceProperty());
        }
        return vehicleApi;
    }

    public IInitiationServiceDeviceApi getDeviceApi() throws Exception {
        if (deviceApi == null) {
            deviceApi = new InitiationServiceDeviceApi(getServiceProperty());
        }
        return deviceApi;
    }

    public IInitiationServiceServiceVersionApi getVersionApi() {
        if (versionApi == null) {
            versionApi = new InitiationServiceServiceVersionApi(getServiceProperty());
        }
        return versionApi;
    }

}
