package com.openmatics.testinstrumentation.service.sms;

import com.openmatics.testinstrumentation.platform.service.global.initation.IInitiationServiceDeploymentsApi;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.Deployment;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceVersionDescriptor;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.repository.DeploymentRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 26.04.2019.
 */
public class DeploymentService {
    private static final int ATTEMPT = 10;
    private static final long SLEEP_S = 5;
    private DeploymentRepository deploymentRepository;
    private IInitiationServiceDeploymentsApi deploymentsApi;

    public DeploymentService(DeploymentRepository deploymentRepository, IInitiationServiceDeploymentsApi deploymentsApi) {
        this.deploymentRepository = deploymentRepository;
        this.deploymentsApi = deploymentsApi;
    }

    public List<Deployment> loadAllServiceDescriptorDeployments(String groupId, String artifactId) {
        List<Deployment> deploymentsByArtifactId = deploymentRepository.getAllByGroupIdAndArtifactId(groupId, artifactId);
        return deploymentsByArtifactId;
    }

    public List<Deployment> loadAllByServiceVersionDescriptorId(String serviceVersionDescriptorId) {
        List<Deployment> deploymentsByArtifactId = deploymentRepository.getAllByServiceVersionDescriptorId(serviceVersionDescriptorId);
        return deploymentsByArtifactId;
    }

    public boolean isVersionAssignedToClient(String serviceVersionDescriptorId, String clientId) {
        return loadAllByServiceVersionDescriptorId(serviceVersionDescriptorId).stream().anyMatch(deployment -> clientId.equalsIgnoreCase(deployment.getClientId()));
    }

    public boolean isVersionDeployedToClient(String serviceVersionDescriptorId, String clientId) {
        return loadAllByServiceVersionDescriptorId(serviceVersionDescriptorId).stream().anyMatch(deployment -> clientId.equalsIgnoreCase(deployment.getClientId()) && deployment.getDeploymentStatus().equalsIgnoreCase("DEPLOYED"));
    }

    public boolean assignVersionToClient(ServiceVersionDescriptor serviceVersionDescriptor, ServiceDescriptor serviceDescriptor, String clientId) {
        boolean assigned = isVersionAssignedToClient(serviceVersionDescriptor.getId(), clientId);
        if (!assigned) {
            deploymentsApi.assignServiceVersion2Client(serviceDescriptor.getArtifactId(), serviceVersionDescriptor.getVersionName(), clientId);
            assigned = waitForDeployment(serviceVersionDescriptor.getId(), clientId);
        }
        return assigned;
    }

    private synchronized boolean waitForDeployment(String serviceVersionDescriptorId, String clientId) {

        try {
            for (int i = 0; i < ATTEMPT; i++) {
                if (isVersionDeployedToClient(serviceVersionDescriptorId, clientId)) {
                    return true;
                }
                TimeUnit.SECONDS.timedWait(this, SLEEP_S);
            }
        } catch (InterruptedException e) {
            System.err.println("The thread is interrupted during the waiting." + e);
        }
        return false;
    }
}
