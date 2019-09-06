package com.openmatics.testinstrumentation.platform.service.global.devicemanagement.configuration;

import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.ServiceApiBase;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;

public class DeviceConfigurationRestApi extends ServiceApiBase {

    private IDeviceConfigurationLayerRestApi layer = null;
    private IDeviceConfigurationConfigurationRestApi configuration = null;
    private IDeviceConfigurationFileRestApi file = null;
    private IDeviceConfigurationDeviceOverviewRestApi deviceOverview = null;

    public DeviceConfigurationRestApi(IEnvironmentConfiguration envConf) throws Exception {
        super(new ServiceConfiguration("device-management-service", envConf));
    }

    public IDeviceConfigurationLayerRestApi getLayer() throws Exception {
        if (layer == null){
            layer =  new DeviceConfigurationLayerRestApi(getServiceProperty());
        }
        return layer;
    }

    public IDeviceConfigurationConfigurationRestApi getConfiguration() throws Exception {
        if (configuration == null){
            configuration =  new DeviceConfigurationConfigurationRestApi(getServiceProperty());
        }
        return configuration;
    }

    public IDeviceConfigurationFileRestApi getFile() throws Exception {
        if (file == null){
            file =  new DeviceConfigurationFileRestApi(getServiceProperty());
        }
        return file;
    }

    public IDeviceConfigurationDeviceOverviewRestApi getDeviceOverview() throws Exception {
        if (deviceOverview == null){
            deviceOverview =  new DeviceConfigurationDeviceOverviewRestApi(getServiceProperty());
        }
        return deviceOverview;
    }
}
