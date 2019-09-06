package com.openmatics.testinstrumentation.platform.service.configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 02.05.2019.
 */
public class DeploymentConfiguration {
    private List<String> clients = new ArrayList<>();


    public List<String> getClients() {
        return clients;
    }

    public DeploymentConfiguration setClients(List<String> clients) {
        this.clients = clients;
        return this;
    }
}
