package com.openmatics.testinstrumentation.platform.cases.client.delete;

import com.fasterxml.jackson.core.type.TypeReference;
import com.openmatics.testinstrumentation.automotive.AutomotiveInstrumentation;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.openshift.OpenshiftPodClient;
import com.openmatics.testinstrumentation.platform.service.global.assetmanagement.AssetManagementApi;
import com.openmatics.testinstrumentation.platform.service.global.assetmanagement.model.Asset;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientManagementApi;
import com.openmatics.testinstrumentation.platform.service.global.devicemanagement.DeviceManagementApi;
import com.openmatics.testinstrumentation.platform.service.global.initation.IInitiationServiceRequestApi;
import com.openmatics.testinstrumentation.platform.service.global.initation.InitiationApi;
import com.openmatics.testinstrumentation.platform.service.global.initation.InitiationResultContext;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.ServiceManagementApi;
import com.openmatics.testinstrumentation.platform.utils.OpenshiftClient;
import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;
import com.openmatics.testinstrumentation.utils.sql.SqlUtils;
import com.openshift.restclient.model.IDeploymentConfig;
import com.openshift.restclient.model.IPod;
import com.openshift.restclient.model.IResource;
import com.openshift.restclient.utils.ResourceStatus;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.SkipException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class CleanClients {

    private PlatformInstrumentation plfInstr;
    private AutomotiveInstrumentation autoInstr;
    private AssetManagementApi assetApi;
    private DeviceManagementApi deviceApi;
    private OpenshiftClient openshift;
    private String envKey;
    private List<JSONObject> deployments;
    private List<IDeploymentConfig> deploymentConfigs;
    private List<Asset> assets = null;
    private final DbConnectionFactory connectionFactory;


    public CleanClients(PlatformInstrumentation plfInstr, AutomotiveInstrumentation autoInstr, DbConnectionFactory connectionFactory) throws Exception {
        this.plfInstr = plfInstr;
        this.autoInstr = autoInstr;
        envKey = plfInstr.getEnvConf().getEnvKey();
        this.assetApi = new AssetManagementApi(envKey);
        this.deviceApi = new DeviceManagementApi(envKey);
        this.connectionFactory = connectionFactory;
    }

    public void getDeployedServices(String clientId) throws Exception {
        loadDeployedServices(clientId);
        deploymentConfigs = loadDeployedServicesOpenshift(clientId);
        if (deployments.size() != deploymentConfigs.size()) {
            System.out.println("Deployments via rest:");
            deployments.stream().forEach(json -> System.out.println(json));
            System.out.println("Deployments configs from  openshift:");
            deploymentConfigs.stream().forEach(config -> System.out.println(config.getName()));
            System.out.println("[Warning] Coutn deployments in db is different from count deployment from openshift");
            //throw new UnexpectedStateException("Coutn deployments in db is different from count deployment from openshift");
        }
    }

    public void demobilizationAllServices(String clientId) throws Exception {
        InitiationApi api = new InitiationApi(envKey);
        System.out.println("Will be removing services from docker of clientId=" + clientId);
        for (JSONObject deploy : deployments) {
            String artifactId = deploy.getString("artifactId");
            String version = deploy.getString("version");
            System.out.println(String.format("%s %s", artifactId, version));
            HttpResponse response = api.getDeploymentsApi().DeleteServiceVersion2Client(artifactId, version, clientId);
            Assert.assertEquals(response.getStatus(), 200);
            String requestId = response.getContentAsJson().getString("requestId");
            IInitiationServiceRequestApi requestApi = api.getRequestApi();
            JSONObject result = InitiationResultContext.wait4result(requestApi, requestId, 1, 3000, 20);
            Assert.assertEquals(InitiationResultContext.getOperationStatus(result), "DELETE");
        }
    }

    public void thenOpenshiftDeploymentsAreDeleted(String clientId) throws Exception {

        List<IDeploymentConfig> deploymentConfig = loadDeployedServicesOpenshift(clientId);

        if (deploymentConfig.size() != 0) {
            String message = String.format("In openshift is rest of deploymments after removing: %s", deploymentConfig.toString());
            Assert.fail(message);
        }
    }

    public void thenOpenshiftServicesAreDeleted(String clientId) throws Exception {

        List<IResource> allServices = getOpenshift().getAllServices();
        java.util.stream.Stream<IResource> stream = allServices.stream().filter(isClientResource(clientId));
        List<IResource> clientServices = stream.collect(Collectors.toList());

        if (clientServices.size() != 0) {
            String message = String.format("In openshift is rest of services after removing: %s", clientServices.toString());
            Assert.fail(message);
        }
    }

    public void thenOpenshiftConfigMapsAreDeleted(String clientId) throws Exception {

        List<IResource> allConfigsMaps = getOpenshift().getAllConfigMaps();
        java.util.stream.Stream<IResource> stream = allConfigsMaps.stream().filter(isClientResource(clientId));
        List<IResource> configsMaps = stream.collect(Collectors.toList());

        if (configsMaps.size() != 0) {
            String message = String.format("In openshift is rest of config maps after removing: %s", configsMaps.toString());
            Assert.fail(message);
        }
    }

    public void thenOpenshiftPodsAreDeleted() throws Exception {
        OpenshiftPodClient podLoader = new OpenshiftPodClient(getOpenshift());
        List<IPod> runningPods = new ArrayList<IPod>();

        for (IDeploymentConfig item : deploymentConfigs) {
            List<IPod> pods = podLoader.getPodsByDeploymentConfig(item.getName());
            pods.stream().filter(pod -> !pod.getStatus().equalsIgnoreCase(ResourceStatus.TERMINATING)).forEach(pod -> runningPods.add(pod));
        }

        if (runningPods.size() != 0) {
            StringBuilder message = new StringBuilder("Some not terminating pods are still in openshift: " + System.lineSeparator());
            runningPods.stream().forEach(pod -> message.append(pod.getName() + " " + pod.getStatus() + System.lineSeparator()));
            Assert.fail(message.toString());
        }
    }

    public void deleteServiceAssignment(String clientId) throws Exception {
        String envKey = plfInstr.getEnvConf().getEnvKey();
        ClientManagementApi api = new ClientManagementApi(envKey);
        System.out.println("Will be remove this service from clientId=" + clientId);

        for (JSONObject deploy : deployments) {
            String artifactId = deploy.getString("artifactId");
            String version = deploy.getString("version");
            System.out.println(String.format("%s %s", artifactId, version));

            JSONObject servicesConf = plfInstr.getEnvConf().getValues().getJSONObject("services");
            String appId = servicesConf.getJSONObject(artifactId).getString("aadId");
            System.out.println(String.format("Try remove app %s with id %s from the clients group %s.", artifactId, appId, clientId));

            HttpResponse response = api.getApplicationApi().DeleteAssignementAppToGroup(appId, clientId);
            int status = response.getStatus();
            String message = String.format("Unexpected response status %s", status);
            // TODO check right state 404 versus 409
            Assert.assertTrue((status == 200 || status == 404 || status == 204 || status == 409), message);
        }
    }

    public List<Asset> getClientAssets(String clientId) throws Exception {
        HttpResponse assetsResponse = assetApi.getAssetApi().getAssets();
        List<Asset> assets = JsonUtils.fromJson(assetsResponse.getContentAsString(), new TypeReference<List<Asset>>() {});
        return assets.stream().filter(isAssignedToClient(clientId)).collect(Collectors.toList());
    }

    public void removeClientFromAsset(Asset asset, String clientId){
        List<String> clientIds = asset.getClientIdList().stream()
                .filter(id -> ! id.equalsIgnoreCase(clientId))
                .collect(Collectors.toList());
        asset.setClientIdList(clientIds);
        try {
            HttpResponse response = assetApi.getAssetApi().updateAsset(asset.getId(), JsonUtils.toJson(asset));
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }

    public void deleteDevices(List<Asset> assets) throws Exception {
        assets.stream().forEach(asset -> deleteDevice(asset));
    }

    public void deleteVehicles(List<Asset> assets) {
        assets.stream().forEach(asset -> deleteVehicle(asset));
    }


    public void deleteDevice(Asset asset) {
        try {
            HttpResponse getDeviceResponse = deviceApi.getApi().getDeviceByAssetId(asset.getId());
            if (getDeviceResponse.getStatus() == 200) {
                deviceApi.getApi().deleteDevice(getDeviceResponse.getContentAsJson().getString("id"));
            } else {
                System.out.println("It can not possible delete device for asset id: " + asset.getId());
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }


    public void deleteVehicle(Asset asset) {
        try {
            HttpResponse res = assetApi.getVehicleApi().deleteVehicle(asset.getId());
            int status = res.getStatus();
            String message = String.format("Unexpected response status %s", status);
            Assert.assertTrue((status == 200 || status == 404 || status == 204), message);
        } catch (Exception e) {
            System.err.println(e.toString());
            throw new RuntimeException(e);
        }
    }


    public void removingUsersFromClientGroup(String clientId) throws Exception {
        ClientManagementApi api = new ClientManagementApi(envKey);
        List<JSONObject> users = loadUsers(api, clientId);
        for (Object item : users) {
            JSONObject user = (JSONObject) item;
            String userId = user.getString("id");
            HttpResponse res = api.getUserApi().deleteUserGroupClientMembership(userId, clientId);
            int status = res.getStatus();
            Assert.assertTrue((status == 200 || status == 204), "Unexpected status: " + status);
        }
    }


    public void deleteDefaultProperties() throws Exception {
        //TODO method deleteDefaultProperties
    }

    public void deleteConfigurations() throws Exception {
        //TODO method deleteConfigurations
    }

    /**
     * @param clientId
     * @return Return client db name from master.dbo.sysdatabases. If client db not exists return null. (client-<clientId>-db)
     */
    public String getClientClientDatabaseName(String clientId) throws Exception {
        String name = null;
        String dbName = SqlUtils.getClientDbName(clientId);
        String query = String.format("SELECT * FROM master.dbo.sysdatabases where name = '%s'", dbName);
        // TODO use DbConnectionFactoryPool (This class will be created latest)
        try (DbConnectionFactory serverConnection = new DbConnectionFactory(plfInstr.getEnvConf().getDbServerConnectionString())) {
            try (DbQueryService sqlAccess = new DbQueryService(serverConnection)) {
                ResultSet rs = sqlAccess.executeQuery(query);
                if (rs.next()) name = rs.getString("name");
            }
        }
        return name;
    }

    /**
     * Drop client db by clientId if exists. Create db name client-<clientId>-db and drop it
     *
     * @param clientId
     * @throws Exception
     */
    public void dropClientDatabaseByClientId(String clientId) throws Exception {
        if (envKey.equalsIgnoreCase("iris") || envKey.equalsIgnoreCase("prometheus")) {
            throw new SkipException("For Iris and Prometheus not allowed delete DB");
        }
        String dbName = SqlUtils.getClientDbName(clientId);
        String query = String.format("DROP DATABASE IF EXISTS [%s]", dbName);
        try (DbConnectionFactory serverConnection = new DbConnectionFactory(plfInstr.getEnvConf().getDbServerConnectionString())) {
            try (DbQueryService sqlAccess = new DbQueryService(serverConnection)) {
                sqlAccess.executeUpdate(query);
            }
        }
    }


    private List<JSONObject> loadUsers(ClientManagementApi api, String clientId) throws Exception {
        HttpResponse res = api.getClientApi().getClientByIdExtended(clientId);
        Assert.assertEquals(res.getStatus(), 200);
        List<JSONObject> users = new ArrayList<>();
        for (Object item : res.getContentAsJson().getJSONArray("users")) {
            JSONObject user = (JSONObject) item;
            System.out.println(user);
            if (user.has("surname")
                    && user.getString("surname").contains(ClientConfiguration.DEFAULT_USER_BASE_NAME)) {
                users.add(user);
            }
        }
        return users;
    }


    private void loadDeployedServices(String clientId) throws Exception {
        ServiceManagementApi smsApi = new ServiceManagementApi(envKey);
        HttpResponse res = smsApi.getDeploymentApi().getDeployments(null);
        Assert.assertEquals(res.getStatus(), 200);
        deployments = new ArrayList<>();
        for (Object item : res.getContentAsJsonArray()) {
            JSONObject deploy = (JSONObject) item;
            if (deploy.getString("clientId").equals(clientId)) deployments.add(deploy);
        }
        System.out.println("Count of deployment services from via rest: " + deployments.size());
    }

    private List<IDeploymentConfig> loadDeployedServicesOpenshift(String clientId) throws Exception {
        List<IDeploymentConfig> allConfigs = getOpenshift().getDeploymentConfigList();
        List<IDeploymentConfig> clientConfigs = new ArrayList<>();
        for (IDeploymentConfig config : allConfigs) {
            if (config.getName().contains(clientId)) clientConfigs.add(config);
        }
        System.out.println("Count of loaded deployment configs from opneshift: " + clientConfigs.size());
        return clientConfigs;
    }

    private OpenshiftClient getOpenshift() {
        if (openshift == null) openshift = new OpenshiftClient(plfInstr.getEnvConf());
        return openshift;
    }

    private static Predicate<IResource> isClientResource(String clientId) {
        return p -> p.getName().contains(clientId);
    }

    private static Predicate<Asset> isAssignedToClient(String clientId) {
        return p -> p.getClientIdList().stream().filter(id -> id.equals(clientId)).count() > 0;
    }
}
