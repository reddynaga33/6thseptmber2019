package com.openmatics.testinstrumentation.platform;

import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.model.CmsClientTemplate;
import com.openmatics.testinstrumentation.platform.service.global.devicemanagement.DeviceManagementConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.ServiceConfiguration;

public class PlatformInstrumentation {

    private IEnvironmentConfiguration envConfiguration;
    private ClientConfiguration clientConfiguration;
    private DeviceManagementConfiguration deviceManagementConfiguration;
    private ServiceConfiguration serviceConfiguration;

    public PlatformInstrumentation(IEnvironmentConfiguration envConf){
        this.envConfiguration = envConf;
        this.clientConfiguration = new ClientConfiguration(envConf);
    }

    public PlatformInstrumentation(IEnvironmentConfiguration envConf, String clientId){
        this.envConfiguration = envConf;
        this.clientConfiguration = new ClientConfiguration(envConf, clientId);
    }

    public PlatformInstrumentation(IEnvironmentConfiguration envConf, CmsClientTemplate clientTemplate){
        this.envConfiguration = envConf;
        this.clientConfiguration = new ClientConfiguration(envConf, clientTemplate);
    }

    public IEnvironmentConfiguration getEnvConf(){
        return envConfiguration;
    }

    public ClientConfiguration getClientConf() {
        return clientConfiguration;
    }

    public DeviceManagementConfiguration getDeviceConf() {
        if (deviceManagementConfiguration == null) {
            deviceManagementConfiguration = new DeviceManagementConfiguration(envConfiguration, clientConfiguration);
        }
        return deviceManagementConfiguration;
    }

    public ServiceConfiguration getServiceConf() {
        if (serviceConfiguration == null) {
            serviceConfiguration = new ServiceConfiguration(envConfiguration, clientConfiguration);
        }
        return serviceConfiguration;
    }
}
