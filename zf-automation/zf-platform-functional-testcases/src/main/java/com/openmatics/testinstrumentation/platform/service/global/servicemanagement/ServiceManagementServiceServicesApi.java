package com.openmatics.testinstrumentation.platform.service.global.servicemanagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.azure.servicebus.Message;
import com.openmatics.testinstrumentation.platform.entity.wrapper.CUDRequestEvent;
import com.openmatics.testinstrumentation.platform.entity.wrapper.EventEnvelope;
import com.openmatics.testinstrumentation.platform.entity.wrapper.EventHeader;
import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusClient;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.joda.time.DateTime;

import java.util.UUID;

public class ServiceManagementServiceServicesApi extends RestApiBase implements IServiceManagementServiceServicesApi {
    private static final String SERVICE_DESCRIPTOR_CHANGE = "com.openmatics.cloud.core.service_service-management-service_servicedescriptorchangerequest";
    private ServiceBusClient client;


    public ServiceManagementServiceServicesApi(IServiceConfiguration property, ServiceBusClient client) throws java.net.MalformedURLException {
        super("global", property);
        this.client = client;
    }

    @Override
    public HttpResponse getServices() throws Exception {
        return super.getGetResponse("services");
    }

    @Override
    public void sendServiceDescriptorChange(CUDRequestEvent<ServiceDescriptor> serviceDescriptor) {
        EventHeader eventHeader = new EventHeader(UUID.randomUUID().toString(), new DateTime(), SERVICE_DESCRIPTOR_CHANGE, UUID.randomUUID().toString());
        EventEnvelope<CUDRequestEvent<ServiceDescriptor>> event = new EventEnvelope<>(eventHeader, serviceDescriptor);
        try {
            client.sendToTopic(SERVICE_DESCRIPTOR_CHANGE, new Message(JsonUtils.toJson(event)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("The message body can't be serialized", e);
        }
    }

}
