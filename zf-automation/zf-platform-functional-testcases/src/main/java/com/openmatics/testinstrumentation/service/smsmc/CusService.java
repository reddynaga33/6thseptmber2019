package com.openmatics.testinstrumentation.service.smsmc;

import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.ISmsmcCusApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.db.model.ClientUsesService;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository.ClientUsesServiceRepository;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 24.04.2019.
 */
public class CusService {
    private ClientUsesServiceRepository clientUsesServiceRepository;
    private ISmsmcCusApi clientEventApi;

    public CusService(ISmsmcCusApi clientEventApi) {
        this(null, clientEventApi);
    }

    public CusService(ClientUsesServiceRepository clientUsesServiceRepository, ISmsmcCusApi clientEventApi) {
        this.clientUsesServiceRepository = clientUsesServiceRepository;
        this.clientEventApi = clientEventApi;
    }

    public boolean isRecordInDbClientUsesService(String clientId, String serviceDescriptorId) {
        if (clientUsesServiceRepository == null) {
            throw new IllegalStateException("The client uses repository is not initialized.");
        }
        ClientUsesService cus = clientUsesServiceRepository.findByClientIdAndServiceDescriptor(clientId, serviceDescriptorId);
        return cus != null;
    }

    public String unassignServiceToClient(String serviceDescriptorId, String clientId) {
        String sagaId = clientEventApi.unassignServiceToClient(serviceDescriptorId, clientId);
        return sagaId;
    }

    public String assignServiceToClient(String serviceDescriptorId, String clientId) {
        String sagaId = clientEventApi.assignServiceToClient(serviceDescriptorId, clientId);
        return sagaId;
    }
}
