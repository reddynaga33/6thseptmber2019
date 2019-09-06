package com.openmatics.testinstrumentation.platform.service.global.smsmc.api;

import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.utils.exception.ObjectNotExistsException;

import java.util.List;

public interface ISmsmcServiceRestApi {

    /**
     * Find all service descriptors.
     *
     * @return All service descriptor
     */
    List<ServiceDescriptor> getServicesAll();

    /**
     * Find service descriptor by id.
     *
     * @param serviceDescriptorId service descriptor id
     * @return service descriptor
     */
    ServiceDescriptor getServiceOne(String serviceDescriptorId) throws ObjectNotExistsException;

}
