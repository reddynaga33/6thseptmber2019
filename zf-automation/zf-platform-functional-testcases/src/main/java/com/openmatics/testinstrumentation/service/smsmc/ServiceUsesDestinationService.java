package com.openmatics.testinstrumentation.service.smsmc;

import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.Destination;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.DestinationType;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.Direction;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository.ServiceUsesDestinationRepository;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusDestinationsHandler;
import org.apache.commons.lang.NotImplementedException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This service is managing the MC destination
 *
 * @author marek.rasocha@inventi.cz
 *         date: 01.04.2019.
 */
public class ServiceUsesDestinationService {
    private ServiceUsesDestinationRepository serviceUsesDestinationRepository;
    private ServiceBusDestinationsHandler serviceBusHandler;

    public ServiceUsesDestinationService(ServiceUsesDestinationRepository serviceUsesDestinationRepository, ServiceBusDestinationsHandler serviceBusHandler) {
        this.serviceUsesDestinationRepository = serviceUsesDestinationRepository;
        this.serviceBusHandler = serviceBusHandler;
    }

    /**
     * Delete destinations by service descriptor.
     * //TODO: The method will not need after implementation deleting service descriptor in the SMS.
     *
     * @param serviceDescriptorId service descriptor
     */
    public void deleteByServiceDescriptorId(String serviceDescriptorId) {
        List<Destination> destinationForDelete = serviceUsesDestinationRepository.findServiceUsesDestinationByServiceDescriptorId(serviceDescriptorId);
        deleteDefaultDestination(destinationForDelete);
        serviceUsesDestinationRepository.deleteServiceUsesDestinationByServiceDescriptorId(serviceDescriptorId);
    }

    public void deleteByServiceDescriptorId(String serviceDescriptorId, boolean deleteFromServiceBus) {
        if (deleteFromServiceBus) {
            List<Destination> destinationForDelete = serviceUsesDestinationRepository.findServiceUsesDestinationByServiceDescriptorId(serviceDescriptorId);
            deleteDefaultDestination(destinationForDelete);
        }
        serviceUsesDestinationRepository.deleteServiceUsesDestinationByServiceDescriptorId(serviceDescriptorId);
    }

    public boolean isRecordInServiceUsesDestination(String serviceDescriptorId, String nameIdentifier) {
        Destination destination = serviceUsesDestinationRepository.findByServiceDescriptorIdAndNameIdentifier(serviceDescriptorId, nameIdentifier);
        return destination != null;
    }
    private void deleteDefaultDestination(List<Destination> deletedDestination) {
        deletedDestination.stream()
                .filter(destination -> destination.getType().equals(DestinationType.TOPIC))
                .forEach(destination -> serviceBusHandler.deleteTopic(destination.getNameIdentifier()));
        deletedDestination.stream()
                .filter(destination -> destination.getType().equals(DestinationType.QUEUE))
                .forEach(destination -> serviceBusHandler.deleteQueue(destination.getNameIdentifier()));
    }

    public List<String> getDestinationNamesByServiceDescriptorId(String serviceDescriptorId) {
        List<String> destinationsName = serviceUsesDestinationRepository.findServiceUsesDestinationByServiceDescriptorId(serviceDescriptorId)
                .stream()
                .map(Destination::getNameIdentifier)
                .collect(Collectors.toList());
        return destinationsName;
    }

    /**
     * Checks if the destination exist in the service bus.
     *
     * @param destination      destination
     * @param subscriptionName subscription name
     * @return exist in the service or not
     */
    public boolean existDestinationInServiceBus(Destination destination, String subscriptionName) {
        boolean exist;
        switch (destination.getType()) {
            case QUEUE:
                exist = serviceBusHandler.isQueueExists(destination.getNameIdentifier());
                System.out.println(String.format("Queue '%s' is exist '%s'", destination.getNameIdentifier(), exist));
                break;
            case TOPIC:
                if (destination.getDirection() == Direction.OUT) {
                    exist = serviceBusHandler.isTopicExists(destination.getNameIdentifier());
                    System.out.println(String.format("Topic '%s' is exist '%s'", destination.getNameIdentifier(), exist));
                } else {
                    exist = serviceBusHandler.isSubscriptionExists(destination.getNameIdentifier(), subscriptionName);
                    System.out.println(String.format("Topic subscription '%s/%s' is exist '%s'", destination.getNameIdentifier(), subscriptionName, exist));
                }
                System.out.println("Destination: " + destination);
                break;
            default:
                throw new NotImplementedException(destination.getType() + " - Is not implemented.");
        }

        return exist;
    }
}
