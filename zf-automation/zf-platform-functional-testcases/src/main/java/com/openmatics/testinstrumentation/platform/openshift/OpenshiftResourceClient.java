package com.openmatics.testinstrumentation.platform.openshift;

import com.openmatics.testinstrumentation.platform.utils.OpenshiftClient;
import com.openmatics.testinstrumentation.utils.GuidUtils;
import com.openshift.restclient.model.IResource;

import java.util.List;
import java.util.stream.Collectors;

public class OpenshiftResourceClient {

    OpenshiftClient client;

    public OpenshiftResourceClient(OpenshiftClient client) {
        this.client = client;
    }

    public List<IResource> getAllBuilds() {
        return client.getAllBuilds();
    }

    public List<IResource> getAllConfigMaps() {
        return client.getAllConfigMaps();
    }

    public List<IResource> getAllDeploymentConfigs() {
        return client.getAllDeploymentConfigs();
    }

    public List<IResource> getAllServices() {
        return client.getAllServices();
    }

    public List<IResource> getAllPods() {
        return client.getAllPods();
    }

    public List<IResource> getPodsByDeploymentConfig(String dcName) {
        return client.getPodsByDeploymentConfig(dcName);
    }

    public List<IResource> getAllDeploymentConfig() {
        return client.getAllDeploymentConfigs();
    }

    protected List<IResource> filterByName(List<IResource> resources, String name) {
        return resources.stream()
                .filter(res -> res.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    protected List<IResource> filterByClientId(List<IResource> resources, String clientId) {
        return resources.stream()
                .filter(res -> res.getName().toLowerCase().contains(clientId.toLowerCase()))
                .collect(Collectors.toList());
    }

    protected List<IResource> filterBySubstringInName(List<IResource> resources, String name) {
        return resources.stream()
                .filter(res -> res.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    protected List<IResource> filterTypeClient(List<IResource> resources) {
        return resources.stream()
                .filter(res -> GuidUtils.containsGuid(res.getName()))
                .collect(Collectors.toList());
    }

    protected List<IResource> filterTypeGlobal(List<IResource> resources) {
        return resources.stream()
                .filter(res -> !GuidUtils.containsGuid(res.getName()))
                .collect(Collectors.toList());
    }

}
