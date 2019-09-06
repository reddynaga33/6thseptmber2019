package com.openmatics.testinstrumentation.automotive;

import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientConfiguration;

public class AutomotiveInstrumentation {

    private IEnvironmentConfiguration envConfiguration;
    private VehicleConfiguration vehicleConfiguration;
    private ClientConfiguration clientConfiguration;

    public AutomotiveInstrumentation(IEnvironmentConfiguration envConf, String clientSuffix)
    {
        this.envConfiguration = envConf;
        this.clientConfiguration = new ClientConfiguration(envConf, clientSuffix);
        this.vehicleConfiguration = new VehicleConfiguration(envConfiguration, clientConfiguration);
    }

    public AutomotiveInstrumentation(IEnvironmentConfiguration envConf, ClientConfiguration clientConf)
    {
        this.envConfiguration = envConf;
        this.clientConfiguration = clientConf;
        this.vehicleConfiguration = new VehicleConfiguration(envConfiguration, clientConf);
    }

    public IEnvironmentConfiguration getEnvConf(){
        return envConfiguration;
    }

    public ClientConfiguration getClientConf() {
        return clientConfiguration;
    }

    public VehicleConfiguration getVehicleConf() {
        return vehicleConfiguration;
    }

}
