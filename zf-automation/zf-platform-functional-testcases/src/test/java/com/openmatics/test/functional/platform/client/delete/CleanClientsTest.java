package com.openmatics.test.functional.platform.client.delete;

import com.openmatics.test.functional.DbBaseTest;
import com.openmatics.testinstrumentation.platform.cases.client.delete.CleanClients;
import com.openmatics.testinstrumentation.platform.service.global.assetmanagement.model.Asset;
import com.openmatics.testinstrumentation.utils.exception.ObjectNotExistsException;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.testng.Action;
import com.openmatics.testinstrumentation.utils.testng.Arrange;
import com.openmatics.testinstrumentation.utils.testng.Assert;
import com.openmatics.testinstrumentation.utils.testng.ResultUtil;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


@Test(suiteName = "GeneralFunctional", testName = "DeleteClientInfrastructure",
        groups = {"DeleteClientInfrastructure", "CleanClients"})
public class CleanClientsTest extends DbBaseTest {

    private CleanClients cleanClients;
    private String clientId;
    private List<Asset> assets;

    public CleanClientsTest() {
    }

    public CleanClientsTest(String envKey, String clientId, String clientSuffix) throws Exception {
        super(envKey, clientId, clientSuffix);
    }

    @BeforeClass
    public void beforeClassCleanClientTest() throws Exception {
        cleanClients = new CleanClients(this.platformInstrumentation, this.getAutomotiveInstr(), connectionFactory);
    }


    @Arrange("Check if client exists if not skip this test and all group")
    @Test()
    public void givenClient() throws Exception {
        if (platformInstrumentation.getClientConf().getInputClientId() != null) {
            clientId = platformInstrumentation.getClientConf().getInputClientId();
        } else {
            // if not provided on input clientId i get it by templete (for it has to be provided clientSuffix)
            // and has to exists in Active direcory
            clientId = platformInstrumentation.getClientConf().getClient().getId();
        }
        if (clientId == null) {
            String message = String.format("CmsClient not found.");
            ResultUtil.setResultSkip(new ObjectNotExistsException(message));
        }
    }

    @Arrange("Load deployed services from openshift and via rest from db)")
    @Test(dependsOnMethods = "givenClient")
    public void getDeployedServices() throws Exception {
        cleanClients.getDeployedServices(clientId);
    }

    @Action("Remove client services from docker containers via Initiation service")
    @Assert("Wait for request and check if result is 'DELETE'")
    @Test(dependsOnMethods = "getDeployedServices")
    public void demobilizationAllServices() throws Exception {
        cleanClients.demobilizationAllServices(clientId);
    }

    @Assert("Check that deployments of the client was deleted from openshift")
    @Test(dependsOnMethods = "demobilizationAllServices")
    public void thenOpenshiftDeploymentsAreDeleted() throws Exception {
        cleanClients.thenOpenshiftDeploymentsAreDeleted(clientId);
    }

    @Assert("Check that services of the client was deleted from openshift.")
    @Test(dependsOnMethods = "demobilizationAllServices")
    public void thenOpenshiftServicesAreDeleted() throws Exception {
        cleanClients.thenOpenshiftServicesAreDeleted(clientId);
    }


    @Assert("Check that config maps of the client was deleted from openshift.")
    @Test(dependsOnMethods = "demobilizationAllServices")
    public void thenOpenshiftConfigMapsAreDeleted() throws Exception {
        cleanClients.thenOpenshiftConfigMapsAreDeleted(clientId);
    }

    @Assert("Check that pods of the client was deleted from openshift.")
    @Test(dependsOnMethods = "demobilizationAllServices")
    public void thenOpenshiftPodsAreDeleted() throws Exception {
        cleanClients.thenOpenshiftPodsAreDeleted();
    }

    @Ignore
    @Assert("Deployment records in globalDb (table deployment) are deleted.")
    @Test(dependsOnMethods = "demobilizationAllServices")
    public void thenDeploymentRecordsAreDeletedFromGlobalDb() throws Exception {
        //TODO Deployment records in globalDb (table deployment) are deleted.
    }

    @Ignore
    @Assert("Db user are deleted in clientDb.")
    @Test(dependsOnMethods = "demobilizationAllServices")
    public void thenDbUserAreDeletedFromClientDb() throws Exception {
        //TODO Db user are deleted in clientDb. ?
    }


    @Action("Remove service assignment from client")
    @Test(dependsOnMethods = "demobilizationAllServices")
    public void deleteServiceAssignment() throws Exception {
        cleanClients.deleteServiceAssignment(clientId);
    }

    @Ignore
    @Assert("Service/app now are not assigned to the client")
    @Test(dependsOnMethods = "demobilizationAllServices")
    public void thenXXX() throws Exception {
        //TODO Service/app now are not assigned to the client
    }


    @Arrange("Assets of client to delete")
    @Test(dependsOnMethods = "demobilizationAllServices")
    public void givenAssets() throws Exception {
        assets = cleanClients.getClientAssets(clientId);
    }

    @Action("Deleted devices")
    @Test(dependsOnMethods = "givenAssets")
    public void deleteDevices() {
        // delete if assigned to the only one client. Other nothing.
        assets.stream()
                .filter(asset -> asset.getClientIdList().size() == 1) // delete if assigned to the only one client.
                .forEach(asset -> cleanClients.deleteDevice(asset));
    }


    @Action("Deleted vehicles")
    @Test(dependsOnMethods = "deleteDevices")
    public void deleteVehicles() {
        // delete if assigned to the only one client.
        assets.stream()
                .filter(asset -> asset.getClientIdList().size() == 1) // delete if assigned to the only one client.
                .forEach(asset -> cleanClients.deleteVehicle(asset));
        // if assigned to the more clients only remove current client from this asset
        assets.stream()
                .filter(asset -> asset.getClientIdList().size() > 1) // update asset -> remove current client
                .forEach(asset -> cleanClients.removeClientFromAsset(asset, clientId));
    }


    @Action("Deactivate users - remove it from client group")
    @Test(dependsOnMethods = "demobilizationAllServices")
    public void deactivateUsers() throws Exception {
        cleanClients.removingUsersFromClientGroup(clientId);
    }

    @Ignore
    @Assert("Created users are now not members of client group")
    @Test(dependsOnMethods = "demobilizationAllServices")
    public void thenUsersAreNotMembersOfClient() throws Exception {
        // TODO - check that created users are now not members of client group
    }


    @Arrange("Get client dabase name of client if db exists")
    @Test(dependsOnMethods = {"demobilizationAllServices", "deleteVehicles", "deleteDevices"})
    public void givenClientDbName() throws Exception {
        if (cleanClients.getClientClientDatabaseName(clientId) == null) {
            throw new SkipException("Database not exists therefore it can't be deleted.");
        }
    }


    @Action("Remove client database")
    @Test(dependsOnMethods = {"givenClientDbName"})
    public void whenIDropClientDb() throws Exception {
        cleanClients.dropClientDatabaseByClientId(clientId);
    }


    @Assert("I can't get client db name because db is droped")
    @Test(dependsOnMethods = {"whenIDropClientDb"})
    public void thenICanNotGetClientDbName() throws Exception {
        org.testng.Assert.assertNull(cleanClients.getClientClientDatabaseName(clientId));
    }

    @Override
    protected DbConnectionFactory initiateDbConnection() throws SQLException {
        return new DbConnectionFactory(platformInstrumentation.getEnvConf().getDbConnectionString());
    }


    /* -- Not implemented yet. Probably will be delete with client ?
    @Ignore
    @Action
    @Test(dependsOnMethods = "demobilizationAllServices")
    public void deleteDefaultProperties() throws  Exception {
        cleanClients.deleteDefaultProperties();
    }

    @Ignore
    @Action
    @Test(dependsOnMethods = "demobilizationAllServices")
    public void deleteConfigurations() throws  Exception {
        cleanClients.deleteConfigurations();
    }
    */
}
