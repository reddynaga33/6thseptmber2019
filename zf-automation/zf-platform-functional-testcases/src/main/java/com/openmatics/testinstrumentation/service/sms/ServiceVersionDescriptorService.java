package com.openmatics.testinstrumentation.service.sms;

import com.openmatics.testinstrumentation.platform.service.global.initation.IInitiationServiceServiceVersionApi;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceVersionDescriptor;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.repository.ServiceVersionDescriptorRepository;

import java.util.concurrent.TimeUnit;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 29.04.2019.
 */
public class ServiceVersionDescriptorService {
    private static final int ATTEMPT = 10;
    private static final long SLEEP_S = 5;
    private ServiceVersionDescriptorRepository serviceVersionDescriptorRepository;
    private IInitiationServiceServiceVersionApi serviceServiceVersionApi;

    public ServiceVersionDescriptorService(ServiceVersionDescriptorRepository serviceVersionDescriptorRepository, IInitiationServiceServiceVersionApi serviceServiceVersionApi) {
        this.serviceVersionDescriptorRepository = serviceVersionDescriptorRepository;
        this.serviceServiceVersionApi = serviceServiceVersionApi;
    }

    public ServiceVersionDescriptor saveServiceVersionDescriptor(String name, ServiceDescriptor serviceDescriptor) {
        ServiceVersionDescriptor save = serviceVersionDescriptorRepository.findByNameAndServiceDescriptorGroupIdAndArtifactId(name, serviceDescriptor.getGroupId(), serviceDescriptor.getArtifactId());
        if (save == null) {
            serviceServiceVersionApi.createServiceVersionDescriptor(serviceDescriptor.getArtifactId(), name);
            save = waitForVersion(name, serviceDescriptor.getGroupId(), serviceDescriptor.getArtifactId());
        }
        return save;
    }

    private synchronized ServiceVersionDescriptor waitForVersion(String name, String artifactId, String groupId) {
        ServiceVersionDescriptor save = null;
        try {
            for (int i = 0; i < ATTEMPT; i++) {
                save = serviceVersionDescriptorRepository.findByNameAndServiceDescriptorGroupIdAndArtifactId(name, groupId, artifactId);
                if (save != null) {
                    break;
                }
                TimeUnit.SECONDS.timedWait(this, SLEEP_S);
            }
        } catch (InterruptedException e) {
            System.err.println("The thread is interrupted during the waiting." + e);
        }
        return save;
    }
}
