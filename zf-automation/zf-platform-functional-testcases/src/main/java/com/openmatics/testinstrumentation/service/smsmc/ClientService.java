package com.openmatics.testinstrumentation.service.smsmc;

import com.openmatics.testinstrumentation.platform.entity.Client;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.ISmsmcClientRestApi;

import java.util.List;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 02.05.2019.
 */
public class ClientService {
    private ISmsmcClientRestApi clientRestApi;

    public ClientService(ISmsmcClientRestApi clientRestApi) {
        this.clientRestApi = clientRestApi;
    }

    public List<Client> findClient() {
        return clientRestApi.getAllClients();
    }

    public Client findClientByName(String name) {
        return clientRestApi.getAllClients().stream().filter(client -> name.equalsIgnoreCase(client.getName())).findAny().orElse(null);
    }
}
