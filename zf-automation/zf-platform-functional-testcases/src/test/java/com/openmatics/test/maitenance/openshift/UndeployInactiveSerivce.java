package com.openmatics.test.maitenance.openshift;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.test.maitenance.ClientLoader;
import com.openmatics.testinstrumentation.platform.openshift.OpenshiftPodClient;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.PermanentClientIdList;
import com.openmatics.testinstrumentation.platform.utils.OpenshiftClient;
import com.openmatics.testinstrumentation.utils.GuidUtils;
import com.openshift.restclient.model.IDeploymentConfig;
import com.openshift.restclient.model.IPod;
import com.openshift.restclient.model.IResource;

import java.util.*;
import java.util.stream.Collectors;

public class UndeployInactiveSerivce {

    public static void main(String[] args) throws Exception {
        String envKey = "daedalus";
        EnvironmentConfiguration envConfig = new EnvironmentConfiguration(envKey);
        OpenshiftClient opClient = new OpenshiftClient(envConfig);
        OpenshiftPodClient resources = new OpenshiftPodClient(opClient);
        List<IPod> pods = resources.getAllPods();
        List<IPod> runningPods =
                pods.stream()
                        .filter(pod ->
                                pod.getStatus().equalsIgnoreCase("Running") ||
                                        pod.getStatus().equalsIgnoreCase("CrashLoopBackOff") ||
                                        pod.getStatus().equalsIgnoreCase("ContainerCreating"))
                        .collect(Collectors.toList());


        runningPods.stream().forEach(pod -> System.out.println(String.format("%s %s", pod.getName(), pod.getStatus())));

        System.out.println("Running pods" + runningPods.size());


    }

    public static void mainOld(String[] args) throws Exception {
        String envKey = "daedalus";
        EnvironmentConfiguration envConfig = new EnvironmentConfiguration(envKey);
        OpenshiftClient opClient = new OpenshiftClient(envConfig);
        OpenshiftPodClient resources = new OpenshiftPodClient(opClient);
        List<IPod> list = resources.getGlobalPods();
        //System.out.println("list count:" + list.size());
        /*
        list.stream()
                .filter(res -> ((IPod)res).getStatus().equalsIgnoreCase("Running") ||
                        ((IPod) res).getStatus().equalsIgnoreCase("CrashLoopBackOff") ||
                        ((IPod) res).getStatus().equalsIgnoreCase("ContainerCreating"))
                .forEach(res -> System.out.println(String.format("%s %s", res.getName(), ((IPod)res).getStatus())));
        */
        list.stream()
                .forEach(res -> System.out.println(String.format("%s %s", res.getName(), res.getStatus())));
    }

    public static void filterClientResource(List<IResource> resource) throws Exception {
        List<IResource> result = resource.stream()
                .filter(res -> GuidUtils.containsGuid(res.getName()))
                .collect(Collectors.toList());
    }

    public static void getAllActivePods() throws Exception {
        String envKey = "daedalus";
        List<String> permanentClient = new PermanentClientIdList().get(envKey);
        EnvironmentConfiguration envConfig = new EnvironmentConfiguration(envKey);
        PlatformInstrumentation instrumentation = new PlatformInstrumentation(envConfig);
        OpenshiftClient opClient = new OpenshiftClient(envConfig);
        List<IPod> pods = opClient.getPodList();
        List<IPod> filteredPods = pods.stream()
                .filter(pod -> pod.getStatus().equalsIgnoreCase("Running") ||
                        pod.getStatus().equalsIgnoreCase("CrashLoopBackOff") ||
                        pod.getStatus().equalsIgnoreCase("ContainerCreating"))
                .filter(pod -> pod.getName().equalsIgnoreCase(""))
                .collect(Collectors.toList());

        filteredPods.stream().forEach(pod -> System.out.println(pod.getName() + " " + pod.getStatus()));
    }

    public static void getIllegalPods() throws Exception {
        String envKey = "daedalus";
        List<String> permanentClient = new PermanentClientIdList().get(envKey);
        EnvironmentConfiguration envConfig = new EnvironmentConfiguration(envKey);
        PlatformInstrumentation instrumentation = new PlatformInstrumentation(envConfig);
        OpenshiftClient opClient = new OpenshiftClient(envConfig);
        List<IPod> pods = opClient.getPodList();

        System.out.println("Illegal pods====");
        for (IPod pod : pods) {
            Boolean isIllegal = true;
            for (String clientId : permanentClient) {
                if (pod.getName().toLowerCase().contains(clientId.toLowerCase())) {
                    isIllegal = false;
                }
                //System.out.println(String.format("\tClientId: %s - podName: %s - " +
                //        "%s", clientId, pod.getName(), pod.getStatus()));
            }
            if (isIllegal) {

                System.out.println(String.format("\tPodName: %s - " +
                        "%s", pod.getName(), pod.getStatus()));
            }
        }
        System.out.println(String.format("END - Illegals pods size %s.====", "null"));


    }

    public static void getAllPods() throws Exception {
    }

    public static void getAllPodsX() throws Exception {
        String envKey = "daedalus";
        EnvironmentConfiguration envConfig = new EnvironmentConfiguration(envKey);
        PlatformInstrumentation instrumentation = new PlatformInstrumentation(envConfig);
        OpenshiftClient opClient = new OpenshiftClient(envConfig);
        ClientLoader clientLoader = new ClientLoader(instrumentation);
        HashMap<String, String> clients = clientLoader.loadBySubstringInName("automated", false);
        List<IDeploymentConfig> dcList = opClient.getDeploymentConfigList();
        List<IPod> pods = opClient.getPodList();
        System.out.println("count pods: " + pods.size());

        for (Map.Entry<String, String> entry : clients.entrySet()) {
            //System.out.println(entry.getKey());
            int count = 0;
            for (IDeploymentConfig dc : dcList) {
                if (dc.getName().contains(entry.getKey())) count++;
            }
            if (0 < count) {
                System.out.println("*******************");
                System.out.println(entry.getKey());
                System.out.println("count of deployments: " + count);
                System.out.println(entry.getValue());
            }

        }
    }

    public static String getQueryClientDeplyments(String clientId) {

        return "SELECT\n" +
                "\t[deployment].[id] as deplymentId,\n" +
                "\t[deployment_status] as status,\n" +
                "\t(isnull([short_name], [artifact_id])) as name,\n" +
                "\t[version_name] as version \n" +
                "FROM [service_management_service_schema].[deployment]\n" +
                "join [service_management_service_schema].[service_version_descriptor] on\n" +
                "\t[service_version_descriptor].[id] = [deployment].[service_version_descriptor_id]\n" +
                "join [service_management_service_schema].[service_descriptor]\ton\n" +
                "\t[service_descriptor].[id] = [service_version_descriptor].[service_descriptor_id]\n" +
                "where\n" +
                "[client_id] = '" + clientId + "'";
    }
}
