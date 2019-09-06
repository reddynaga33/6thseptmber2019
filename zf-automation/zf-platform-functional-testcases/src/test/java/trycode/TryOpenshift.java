package trycode;

import com.openmatics.testinstrumentation.automotive.VehicleConfiguration;
import com.openmatics.testinstrumentation.platform.*;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientConfiguration;
import com.openshift.restclient.ResourceKind;
import com.openshift.restclient.model.*;


public class TryOpenshift {

    private static String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJ0YXVyaSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJzZXJ2aWNlLW1hbmFnZW1lbnQtc2VydmljZS10b2tlbi03NjIyaiIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJzZXJ2aWNlLW1hbmFnZW1lbnQtc2VydmljZSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6ImUxZTMwNjNmLThlNzctMTFlOC1iMjE0LTAwMGQzYTM4N2UyNyIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDp0YXVyaTpzZXJ2aWNlLW1hbmFnZW1lbnQtc2VydmljZSJ9.LgGbCtTC83AJS_2SQuWEAhzLhGWjFrwp2PZr7RmRTZtRvFwElhHS3N8i7XnQmgbHHpHkLLaX5yN6Uv8ZKnVtm_EGreJ-dgh22Ii9Te8mFeOd2rOA6zIzZq798trknQKBDuXfCIanuwYj3oKAqahCXT5sWHtkkDce5EblQjXprFMulzJKJcUBbW6bemBk6IfIQZ91cNNOSDIf8Eo714i7jmMheFBhSK6DKWWYkzqlj18MY-lf86tRl8_tiHQnUiqMr5GX_GMhm5KuljR5bK4n9VefHix1SKLfHGCEs28BddTU4oZC_9hQPCx9-LnFZkzGhqRcW_d4tn7meV0FcY8jaA";

    public static void main(String[] args) throws Exception {
        String env = "daedalus";
        System.out.println("Test openshift");

        com.openshift.restclient.ClientBuilder clientBuilder = new com.openshift.restclient.ClientBuilder("https://daedalus.openmatics.com");
        clientBuilder.usingToken(token);
        com.openshift.restclient.IClient client = clientBuilder.build();

        //IConfigMap request = client.getResourceFactory().stub(ResourceKind.CONFIG_MAP,  "tauri", "apidocs-service") ;
        //IList list = client.cr
        //apidocs-service
        try {
            System.out.println("Call method: client.list(...)");
            java.util.List<IResource> deploymentsList = client.list(ResourceKind.DEPLOYMENT_CONFIG, env);
            System.out.println("After call method: client.list(...)");
            System.out.println("Count:" + deploymentsList.size());
            System.out.println(deploymentsList);
        }
        catch (Exception e){
            System.out.println("In block catch.");
            System.out.println("Exceptions:" + e.getMessage());
            //e.printStackTrace();
            System.exit(0);
        }
        finally
        {
            System.out.println("Finally");
            //if (client != null) client.close();
            //System.exit(0);

        }
        System.out.println("After Finally");

        //org.apache.commons.lang.StringUtils ut;
        //org.apache.commons.lang3.StringUtils.defaultIfBlank();
        //org.apache.commons.lang.StringUtils.defaultIfEmpty();

        IList list = client.get(ResourceKind.CONFIG_MAP, env);
        // PODS
        //IPod asmPod = client.get(ResourceKind.POD, "asset-management-service-67-q9wjc", env);
        //System.out.println(asmPod);
        java.util.List<IResource> list2 = client.list(ResourceKind.POD, env);
        java.util.List<IPod> listIPods = list2.stream().map(iResource -> (IPod) iResource).collect(java.util.stream.Collectors.toList());
        listIPods.forEach(pod -> System.out.println(pod.getName() + " -> " + pod.getStatus()));

        //System.exit(0);

        //System.out.println(listIPod);
        //System.out.println(list.getItems());

        /*
        System.out.println(listIDeployment);
        for (IDeploymentConfig config : listIDeployment){
            System.out.println(config.getName());
        }
        */
        //com.openshift.restclient.utils.ResourceStatus
        /* POD status:
        Running
        Completed
        Error
        OOMKilled
        CrashLoopBackOff
        TERMINATING

        */


       // java.util.List<String> myList = new java.util.ArrayList<String>();

        //myList.stream().map().toString();

        /*
        System.out.println(request.getClass());
        System.out.println(request.getData());
        System.out.println(request.getProject());
        System.out.println(request.getNamespace());
        System.out.println(request.getName());

        List<IResource> list = client.list(ResourceKind.POD, envConf.getEnvKey());
        return list.stream().map(iResource -> (Pod) iResource).collect(Collectors.toList());

        */
        //IProject project =  (IProject)client.create(request);
        /*
        ClientUpgradeRequest request = new ClientUpgradeRequest();
        request.setHeader("Origin", baseUrl.toString());
        request.setHeader("User-Agent", "openshift-restclient-java");
        request.setHeader("Authorization", "Bearer " + token);
        return request;
        */


    }

    public static void mainOld(String[] args) throws Exception {
        String clientSuffix = "33";
        IEnvironmentConfiguration envProp = new EnvironmentConfiguration("ares");
        ClientConfiguration cliConf = new ClientConfiguration(envProp, clientSuffix);
        System.out.println("CmsClient");
        //System.out.println(cliConf.loadDefaultClient().toString());
        VehicleConfiguration vehConf = new VehicleConfiguration(envProp, cliConf);
        System.out.println("Vehicles");
        System.out.println(vehConf.loadVehicles());
    }
}
