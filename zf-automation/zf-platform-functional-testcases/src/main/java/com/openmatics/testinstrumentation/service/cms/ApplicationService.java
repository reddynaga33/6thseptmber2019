package com.openmatics.testinstrumentation.service.cms;

import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.IClientManagementServiceApplicationApi;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.apache.http.HttpStatus;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 25.04.2019.
 */
public class ApplicationService {
    private IClientManagementServiceApplicationApi applicationApi;

    public ApplicationService(IClientManagementServiceApplicationApi applicationApi) {
        this.applicationApi = applicationApi;
    }

    public boolean isApplicationAssignedToClient(String appId, String clientId) {
        HttpResponse response = null;
        try {
            response = applicationApi.GetAssignementAppToGroup(appId, clientId);
        } catch (Exception e) {
            System.err.println("Some problem during checking assignment app to the group.");
        }
        return response != null && response.getStatus() == HttpStatus.SC_OK;
    }

}
