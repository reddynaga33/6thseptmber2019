package com.openmatics.testinstrumentation.platform.service.global.smsmc.api;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.ServiceApiBase;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.client.SmsmcClientRestApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.configuration.SmsmcConfigurationRestApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.saga.SmsmcSagaRestApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.service.SmsmcServiceRestApi;


public class SmsmcRestApi extends ServiceApiBase {

    private SmsmcClientRestApi clientApi = null;
    private ISmsmcServiceRestApi serviceApi = null;
    private ISmsmcSagaRestApi sagaApi = null;
    private SmsmcConfigurationRestApi configurationApi = null;

    public SmsmcRestApi(String envKey) throws Exception {
        this(new EnvironmentConfiguration(envKey));
    }

    public SmsmcRestApi(IEnvironmentConfiguration envConf) throws Exception {
        super(new ServiceConfiguration("service-management-service-multiclient", envConf));
    }

    public ISmsmcClientRestApi getClientApi() {
        if (clientApi == null) clientApi = new SmsmcClientRestApi(getServiceProperty());
        return clientApi;
    }

    public ISmsmcCusApi getCUSApi() {
        if (clientApi == null) clientApi = new SmsmcClientRestApi(getServiceProperty());
        return clientApi;
    }

    public ISmsmcServiceRestApi getServiceApi() throws Exception {
        if (serviceApi == null) serviceApi = new SmsmcServiceRestApi(getServiceProperty());
        return serviceApi;
    }


    public ISmsmcSagaRestApi getSagaApi() throws Exception {
        if (sagaApi == null) sagaApi = new SmsmcSagaRestApi(getServiceProperty());
        return sagaApi;
    }

    public ISmsmcConfigurationReadApi getConfigurationReadApi() {
        if (configurationApi == null)
            configurationApi = new SmsmcConfigurationRestApi(getServiceProperty());
        return configurationApi;
    }

    public ISmsmcConfigurationModifyApi getConfigurationModifyApi() {
        if (configurationApi == null)
            configurationApi = new SmsmcConfigurationRestApi(getServiceProperty());
        return configurationApi;
    }

}
