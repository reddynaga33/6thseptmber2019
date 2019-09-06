package com.openmatics.testinstrumentation.service.sms;

import com.openmatics.testinstrumentation.platform.entity.wrapper.CUDRequestEvent;
import com.openmatics.testinstrumentation.platform.entity.wrapper.Operation;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.IServiceManagementServiceServicesApi;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.repository.ServiceDescriptorRepository;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 26.04.2019.
 */
public class ServiceDescriptorService {
    private IServiceManagementServiceServicesApi serviceManagementServiceServicesApi;
    private ServiceDescriptorRepository serviceDescriptorRepository;

    public ServiceDescriptorService(IServiceManagementServiceServicesApi serviceManagementServiceServicesApi, ServiceDescriptorRepository serviceDescriptorRepository) {
        this.serviceManagementServiceServicesApi = serviceManagementServiceServicesApi;
        this.serviceDescriptorRepository = serviceDescriptorRepository;
    }

    public void createServiceDescriptor(ServiceDescriptor serviceDescriptor) {
        this.serviceManagementServiceServicesApi.sendServiceDescriptorChange(new CUDRequestEvent<>(Operation.CREATE, serviceDescriptor));
    }

    public void updateServiceDescriptor(ServiceDescriptor serviceDescriptor) {
        this.serviceManagementServiceServicesApi.sendServiceDescriptorChange(new CUDRequestEvent<>(Operation.UPDATE, serviceDescriptor));
    }

    public void deleteServiceDescriptor(ServiceDescriptor serviceDescriptor) {
        this.serviceManagementServiceServicesApi.sendServiceDescriptorChange(new CUDRequestEvent<>(Operation.DELETE, serviceDescriptor));
    }

    public void switchToStModeViaDatabase(ServiceDescriptor serviceDescriptor) {
        serviceDescriptorRepository.switchToStMode(serviceDescriptor.getId());
    }
}
