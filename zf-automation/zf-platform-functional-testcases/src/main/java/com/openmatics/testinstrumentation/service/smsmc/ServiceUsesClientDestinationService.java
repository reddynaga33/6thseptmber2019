package com.openmatics.testinstrumentation.service.smsmc;

import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.Destination;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository.ServiceUsesClientDestinationRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This service is managing the SC destination
 * @author marek.rasocha@inventi.cz
 *         date: 01.04.2019.
 */
public class ServiceUsesClientDestinationService {
    private ServiceUsesClientDestinationRepository serviceUsesClientDestinationRepository;

    public ServiceUsesClientDestinationService(ServiceUsesClientDestinationRepository serviceUsesClientDestinationRepository) {
        this.serviceUsesClientDestinationRepository = serviceUsesClientDestinationRepository;
    }


    /**
     * Find destination's names from the SC destinations. The destination are found by service descriptor.
     *
     * @param serviceDescriptorId serviceDescriptorID
     * @return list of destination name.
     */
    public List<String> getDestinationNamesByServiceDescriptorId(String serviceDescriptorId) {
        List<String> destinationsName = serviceUsesClientDestinationRepository.findByServiceDescriptorId(serviceDescriptorId)
                .stream()
                .map(Destination::getNameIdentifier)
                .collect(Collectors.toList());
        return destinationsName;
    }
}
