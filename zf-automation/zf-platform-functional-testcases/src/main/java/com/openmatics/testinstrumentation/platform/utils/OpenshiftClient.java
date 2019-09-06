package com.openmatics.testinstrumentation.platform.utils;


import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openshift.restclient.ResourceKind;
import com.openshift.restclient.model.*;
import com.openshift.restclient.ClientBuilder;

import java.util.*;
import java.util.stream.Collectors;


public class OpenshiftClient {

    private com.openshift.restclient.IClient client;
    private IEnvironmentConfiguration envConf;
    private String META_DEPLOYMENT_CONFIG = "deploymentconfig";
    private String envKey;

    public OpenshiftClient(IEnvironmentConfiguration envConf) {
        this.envConf = envConf;
        this.envKey = envConf.getEnvKey();
        ClientBuilder clientBuilder = new ClientBuilder(envConf.getOpenshiftHostName());
        clientBuilder.usingToken(envConf.getOpenshiftSaToken());
        client = clientBuilder.build();
    }


    //Builds
    public List<IResource> getAllBuilds() {
        return client.list(ResourceKind.BUILD, envKey);
    }

    //Deployment
    public List<IResource> getAllDeploymentConfigs() {
        return client.list(ResourceKind.DEPLOYMENT_CONFIG, envConf.getEnvKey());
    }

    @Deprecated
    public List<IDeploymentConfig> getDeploymentConfigList() {
        List<IResource> list = getAllDeploymentConfigs();
        return list.stream().map(iResource -> (IDeploymentConfig) iResource).collect(Collectors.toList());
    }

    public IDeploymentConfig getDeploymentConfig(String name) {
        return client.get(ResourceKind.DEPLOYMENT_CONFIG, name, envConf.getEnvKey());
    }


    //Service
    public List<IResource> getAllServices() {
        return client.list(ResourceKind.SERVICE, envConf.getEnvKey());
    }

    @Deprecated
    public List<IService> getServiceList() {
        List<IResource> list = getAllServices();
        return list.stream().map(iResource -> (IService) iResource).collect(Collectors.toList());
    }

    public IConfigMap getService(String name) {
        return client.get(ResourceKind.SERVICE, name, envConf.getEnvKey());
    }


    //ConfigMap
    public List<IResource> getAllConfigMaps() {
        return client.list(ResourceKind.CONFIG_MAP, envConf.getEnvKey());
    }

    @Deprecated
    public List<IConfigMap> getConfigMapList() {
        List<IResource> list = getAllConfigMaps();
        return list.stream().map(iResource -> (IConfigMap) iResource).collect(Collectors.toList());
    }

    public IConfigMap getConfigMap(String name) {
        return client.get(ResourceKind.CONFIG_MAP, name, envConf.getEnvKey());
    }


    //Pod
    public List<IResource> getAllPods() {
        return client.list(ResourceKind.POD, envConf.getEnvKey());
    }

    @Deprecated
    public List<IPod> getPodList() {
        List<IResource> list = getAllPods();
        return list.stream().map(iResource -> (IPod) iResource).collect(Collectors.toList());
    }

    @Deprecated
    public List<IPod> getPodsByDeploymentConfigList(String dcName) {
        Map<String, String> filter = new HashMap<>();
        filter.put(META_DEPLOYMENT_CONFIG, dcName);
        List<IResource> list = client.list(ResourceKind.POD, envConf.getEnvKey(), filter);
        return list.stream().map(iResource -> (IPod) iResource).collect(Collectors.toList());
    }

    public List<IResource> getPodsByDeploymentConfig(String dcName) {
        Map<String, String> filter = new HashMap<>();
        filter.put(META_DEPLOYMENT_CONFIG, dcName);
        return client.list(ResourceKind.POD, envConf.getEnvKey(), filter);
    }

    public IPod getPod(String name) {
        return client.get(ResourceKind.POD, name, envConf.getEnvKey());
    }

}
