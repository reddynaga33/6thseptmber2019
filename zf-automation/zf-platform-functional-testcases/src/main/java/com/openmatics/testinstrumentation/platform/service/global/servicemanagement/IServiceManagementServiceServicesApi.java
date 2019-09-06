package com.openmatics.testinstrumentation.platform.service.global.servicemanagement;

import com.openmatics.testinstrumentation.platform.entity.wrapper.CUDRequestEvent;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IServiceManagementServiceServicesApi {

    HttpResponse getServices() throws Exception;

    /**
     * Send Service descriptor change event
     * @param serviceDescriptor Service Descptor request body
     */
    void sendServiceDescriptorChange(CUDRequestEvent<ServiceDescriptor> serviceDescriptor);
}
