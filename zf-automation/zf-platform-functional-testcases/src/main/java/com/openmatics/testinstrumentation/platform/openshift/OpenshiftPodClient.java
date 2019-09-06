package com.openmatics.testinstrumentation.platform.openshift;

import com.openmatics.testinstrumentation.platform.utils.OpenshiftClient;
import com.openshift.restclient.model.IPod;

import java.util.List;
import java.util.stream.Collectors;

public class OpenshiftPodClient {

    private OpenshiftResourceClient loader;

    public OpenshiftPodClient(OpenshiftClient client) {
        loader = new OpenshiftResourceClient(client);
    }

    public List<IPod> getAllPods() {
        return loader.getAllPods()
                .stream()
                .map(res -> (IPod) res)
                .collect(Collectors.toList());
    }

    public List<IPod> getPodsByDeploymentConfig(String dcName) {
        return loader.getPodsByDeploymentConfig(dcName)
                .stream()
                .map(res -> (IPod) res)
                .collect(Collectors.toList());
    }

    public List<IPod> getPodsBySubstringInName(String name) {
        return loader.filterBySubstringInName(loader.getAllPods(), name)
                .stream()
                .map(res -> (IPod) res)
                .collect(Collectors.toList());
    }

    public List<IPod> getClientPods() {
        return loader.filterTypeClient(loader.getAllPods())
                .stream()
                .map(resource -> (IPod) resource)
                .collect(Collectors.toList());
    }

    public List<IPod> getGlobalPods() {
        return loader.filterTypeGlobal(loader.getAllPods())
                .stream()
                .map(resource -> (IPod) resource)
                .collect(Collectors.toList());
    }

}
