package com.openmatics.testinstrumentation.service.smsmc;

import com.openmatics.testinstrumentation.platform.entity.wrapper.Operation;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.ISmsmcServiceRestApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.service.SmsmcServiceEventApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository.ServiceDescriptorRepository;
import com.openmatics.testinstrumentation.utils.exception.ObjectNotExistsException;

import java.util.List;
import java.util.UUID;

/**
 * TODO: These service should use REST API and EVENT API as in ConfigurationService
 * @author marek.rasocha@inventi.cz
 *         date: 28.03.2019.
 */
public class ServiceDescriptorService {
    private ServiceDescriptorRepository serviceDescriptorRepository;
    private SmsmcServiceEventApi serviceEventApi;
    private ISmsmcServiceRestApi smsmcServiceRestApi;


    public ServiceDescriptorService(ServiceDescriptorRepository serviceDescriptorRepository, SmsmcServiceEventApi serviceEventApi, ISmsmcServiceRestApi smsmcServiceRestApi) {
        this.serviceDescriptorRepository = serviceDescriptorRepository;
        this.serviceEventApi = serviceEventApi;
        this.smsmcServiceRestApi = smsmcServiceRestApi;
    }

    /**
     * Creates request that creates the service descriptor with current attributes.
     *
     * @param groupId    groupId
     * @param artifactId artifactID
     * @param appId      application id that corresponds with ObjectId from the AAD's application.
     * @return sagaId or sagaUrl - depends on API. RestApi - URL, eventApi: sagaId.
     */
    public String saveServiceDescriptor(String groupId, String artifactId, String appId) {
        String sagaId = sendEvent(Operation.CREATE, groupId, artifactId, appId);
        return sagaId;
    }

    /**
     * Delete service descriptor from the database. by groupId and artifactId.
     * // TODO: Use API after the implementation in the SMS-MC
     *
     * @param groupId    groupId
     * @param artifactId artifactID
     */
    public void deleteServiceDescriptor(String groupId, String artifactId) {
        serviceDescriptorRepository.deleteServiceDescriptorByGroupIdAndArtifactId(groupId, artifactId);
    }

    /**
     * Find service descriptor by the groupId and artifact id.
     *
     * @param groupId    groupId
     * @param artifactId artifactID
     * @return founded service descriptor or null.
     */
    public ServiceDescriptor findByGroupIdAndArtifactId(String groupId, String artifactId) {
        List<ServiceDescriptor> servicesAll = smsmcServiceRestApi.getServicesAll();
        ServiceDescriptor serviceDescriptor1 = servicesAll.stream().filter(serviceDescriptor ->
                artifactId.equalsIgnoreCase(serviceDescriptor.getArtifactId()) &&
                        groupId.equalsIgnoreCase(serviceDescriptor.getGroupId()))
                .findAny().orElse(null);
        return serviceDescriptor1;
    }

    /**
     * Call get client SMSMC rest API and check if service is assigned to the client
     *
     * @param clientId
     * @param serviceDescriptorId
     * @return
     * @throws Exception
     */
    public boolean isServiceDescriptorAssignedToClient(String clientId, String serviceDescriptorId) throws Exception {
        try {
            ServiceDescriptor serviceDescriptor = smsmcServiceRestApi.getServiceOne(serviceDescriptorId);
            boolean b = serviceDescriptor.getClients().stream().anyMatch(client -> client.getId().equalsIgnoreCase(clientId));
            return b;
        } catch (ObjectNotExistsException e) {
            throw new RuntimeException("The service descriptor doesn't exist");
        }
    }

    private String sendEvent(Operation operation, String groupId, String artifactId, String appId) {
        return serviceEventApi.sendServiceDescriptorChange(operation, new ServiceDescriptor(
                UUID.randomUUID().toString(),
                artifactId,
                groupId,
                false,
                appId
        ));
    }
}
