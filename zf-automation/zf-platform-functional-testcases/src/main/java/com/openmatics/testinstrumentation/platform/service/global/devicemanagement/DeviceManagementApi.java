package com.openmatics.testinstrumentation.platform.service.global.devicemanagement;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.ServiceApiBase;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;

public class DeviceManagementApi extends ServiceApiBase {

    private IDeviceManagementDeviceApi api = null;

    public DeviceManagementApi(String envKey) throws Exception {
        super(new ServiceConfiguration("device-management-service", new EnvironmentConfiguration(envKey)));
    }

    public DeviceManagementApi(IEnvironmentConfiguration envConf) throws Exception {
        super(new ServiceConfiguration("device-management-service", envConf));
    }

    public IDeviceManagementDeviceApi getApi() throws Exception {
        if (api == null){
            api =  new DeviceManagementDeviceApi(getServiceProperty());
        }
        return api;
    }

}
