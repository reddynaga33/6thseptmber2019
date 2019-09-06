package com.openmatics.testinstrumentation.service.sms;

import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.EventDestination;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.repository.EventDestinationRepository;

import java.util.List;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 26.04.2019.
 */
public class EventDestinationService {
    private EventDestinationRepository eventDestinationRepository;

    public EventDestinationService(EventDestinationRepository eventDestinationRepository) {
        this.eventDestinationRepository = eventDestinationRepository;
    }

    public List<EventDestination> loadEventDestinationByDeploymentId(String deploymentId) {
        List<EventDestination> destinations = eventDestinationRepository.findByDeploymentId(deploymentId);
        return destinations;
    }
}
