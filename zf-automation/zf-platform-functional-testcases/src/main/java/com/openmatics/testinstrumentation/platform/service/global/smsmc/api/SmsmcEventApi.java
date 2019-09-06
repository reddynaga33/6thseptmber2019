package com.openmatics.testinstrumentation.platform.service.global.smsmc.api;

import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.client.SmsmcClientEventApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.configuration.SmsmcConfigurationEventApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.service.SmsmcServiceEventApi;
import com.openmatics.testinstrumentation.platform.utils.ServiceBusConnectionConfiguration;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusClient;

public class SmsmcEventApi {
    private ServiceBusClient client;
    private SmsmcServiceEventApi serviceEventApi;
    private ISmsmcCusApi cusApi;
    private SmsmcConfigurationEventApi configurationEventApi;

    public SmsmcEventApi(ServiceBusConnectionConfiguration serviceBusConnectionConfiguration) {
        client = new ServiceBusClient(serviceBusConnectionConfiguration.getConnectionString());
    }

    public SmsmcServiceEventApi getSmsmcServiceEventApi() {
        if (serviceEventApi == null) {
            serviceEventApi = new SmsmcServiceEventApi(client);
        }
        return serviceEventApi;
    }

    public ISmsmcCusApi getCusApi() {
        if (cusApi == null) {
            cusApi = new SmsmcClientEventApi(client);
        }
        return cusApi;
    }

    public ISmsmcConfigurationModifyApi getConfigurationModifyApi() {
        if (configurationEventApi == null) {
            configurationEventApi = new SmsmcConfigurationEventApi(client);
        }
        return configurationEventApi;
    }

}
