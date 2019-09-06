package com.openmatics.testinstrumentation.platform.service.global.deviceconfigurationhierarchy;

import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.ServiceApiBase;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;

public class DeviceConfigurationHierarchyRestApi extends ServiceApiBase {

    private IDeviceConfigurationHierarchyConfigurationApi configuration = null;
    //private IDeviceConfigurationLayerRestApi layer = null;
    //private IDeviceConfigurationFileRestApi file = null;


    public DeviceConfigurationHierarchyRestApi(IEnvironmentConfiguration envConf) throws Exception {
        super(new ServiceConfiguration("device-CmsClientConfiguration-hierarchy-service", envConf));
    }


    public IDeviceConfigurationHierarchyConfigurationApi getConfiguration() throws Exception {
        if (configuration == null){
            configuration =  new DeviceConfigurationHierarchyConfigurationRestApi(getServiceProperty());
        }
        return configuration;
    }


    /* do not delete - It will be later
    public IDeviceConfigurationLayerRestApi getLayer() throws Exception {
        if (layer == null){
            layer =  new DeviceConfigurationLayerRestApi(getServiceProperty());
        }
        return layer;
    }

    public IDeviceConfigurationFileRestApi getFile() throws Exception {
        if (file == null){
            file =  new DeviceConfigurationFileRestApi(getServiceProperty());
        }
        return file;
    }
    */

}
