package com.openmatics.testinstrumentation.platform.service.global.clientsetting;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.ServiceApiBase;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;

public class ClientSettingApi extends ServiceApiBase {

    private IClientSettingServiceClientPropertiesApi propertiesApi = null;

    public ClientSettingApi(String envKey)  throws Exception {
        super(new ServiceConfiguration("client-settings-service", new EnvironmentConfiguration(envKey)));
    }

    public ClientSettingApi(IEnvironmentConfiguration envConf)  throws Exception {
        super(new ServiceConfiguration("client-settings-service", envConf));
    }

    public IClientSettingServiceClientPropertiesApi getClientApi() throws Exception {
        if (propertiesApi == null){
            propertiesApi =  new ClientSettingServiceClientPropertiesRestApi(getServiceProperty());
        }
        return propertiesApi;
    }
}
