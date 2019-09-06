package com.openmatics.test.functional.platform.servicemanagement_mc;


import com.openmatics.test.functional.DbBaseTest;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientManagementApi;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.SmsmcApiProvider;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.saga.model.SagaType;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository.ClientUsesServiceRepository;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository.ServiceDescriptorRepository;
import com.openmatics.testinstrumentation.service.cms.ApplicationService;
import com.openmatics.testinstrumentation.service.smsmc.CusService;
import com.openmatics.testinstrumentation.service.smsmc.SagaService;
import com.openmatics.testinstrumentation.service.smsmc.ServiceDescriptorService;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.testng.Action;
import com.openmatics.testinstrumentation.utils.testng.Arrange;
import com.openmatics.testinstrumentation.utils.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static com.openmatics.test.functional.platform.servicemanagement_mc.util.ClientUsesServiceTestUtil.ARTIFACT_ID;
import static com.openmatics.test.functional.platform.servicemanagement_mc.util.ClientUsesServiceTestUtil.GROUP_ID;
import static org.assertj.core.api.Java6Assertions.assertThat;

/*
See to issue CPF-1543, CPF-1545
cmd fro run:
mvn clean test -DclientSuffix=225 -DenvKey=tauri -Dtest=ClientUsesServiceRestAcceptanceTest -DsuiteXmlFile=src/test/suite/EmptySuite.xml
or
mvn clean test -DclientSuffix=225 -DenvKey=tauri -DsuiteXmlFile=src/test/suite/platform/servicemanagement_mc/MainFactorySuite.xml

Prerquisities:
is created fake service test-smsmc-service and registered in Active Directory and in global DB
is created service descriptor and service version in sms schema. Service is also added to the config
there is sufficient only appId value.
*/

@Test(testName = "ClientUsesServiceRestAcceptanceTest")
public class ClientUsesServiceRestAcceptanceTest extends DbBaseTest {
    private static final String APP_ID_SERVICE_NAME = "multiclient-test-service";
    private static final int ATTEMPT = 10;
    private static final int TIMEOUT_S = 5;

    private ServiceDescriptor serviceDescriptor = null;
    private ServiceDescriptorService serviceDescriptorService;
    private ApplicationService applicationService;
    private SagaService sagaService;

    private String clientId;
    private String appId;
    private CusService cusService;


    @Override
    protected DbConnectionFactory initiateDbConnection() throws SQLException {
        return new DbConnectionFactory(platformInstrumentation.getEnvConf().getDbConnectionString());
    }

    @BeforeClass
    public void beforeClientUsesServiceRestAcceptanceTest() throws Exception {
        SmsmcApiProvider smsmcTest = new SmsmcApiProvider(this.platformInstrumentation);
        ClientManagementApi cmsApi = new ClientManagementApi(this.platformInstrumentation.getEnvConf());
        ServiceConfiguration serviceConfiguration = new ServiceConfiguration(APP_ID_SERVICE_NAME, platformInstrumentation.getEnvConf());
        appId = serviceConfiguration.getApplicationClientId();

        serviceDescriptorService = new ServiceDescriptorService(new ServiceDescriptorRepository(connectionFactory), smsmcTest.getEventApi().getSmsmcServiceEventApi(), smsmcTest.getRestApi().getServiceApi());
        cusService = new CusService(new ClientUsesServiceRepository(connectionFactory), smsmcTest.getRestApi().getCUSApi());
        sagaService = new SagaService(smsmcTest.getRestApi().getSagaApi());
        applicationService = new ApplicationService(cmsApi.getApplicationApi());
        deleteEntities();
    }

    @AfterClass
    public void afterClientUsesServiceRestAcceptanceTest() throws Exception {
        deleteEntities();
    }

    @Test()
    public void givenClient() throws Exception {
        clientId = platformInstrumentation.getClientConf().getClient().getId();
    }

    @Test(dependsOnMethods = "givenClient")
    public void givenServiceDescriptor() throws Exception {
        String sagaId = serviceDescriptorService.saveServiceDescriptor(GROUP_ID, ARTIFACT_ID, appId);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaId, SagaType.SERVICE_DESCRIPTOR, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga '" + sagaId + "' about saving service descriptor success?").isTrue();
        serviceDescriptor = serviceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
        System.out.println("Test is using service descriptor: " + serviceDescriptor);
    }

    @Arrange("Check if not exists record in db in table [client_uses_service] for client and service")
    @Test(dependsOnMethods = "givenServiceDescriptor")
    public void givenAssignNotExistsInDb() throws Exception {
        org.testng.Assert.assertFalse(cusService.isRecordInDbClientUsesService(clientId, serviceDescriptor.getId()));
    }

    @Arrange("Check if the assignment exist in the service descriptor.")
    @Test(dependsOnMethods = "givenAssignNotExistsInDb")
    public void givenAssignNotExistInRestResponse() throws Exception {
        org.testng.Assert.assertFalse(serviceDescriptorService.isServiceDescriptorAssignedToClient(clientId, serviceDescriptor.getId()));
    }


    @Arrange("Check if not service assigned to client group in Active Directory")
    @Test(dependsOnMethods = "givenAssignNotExistInRestResponse")
    public void givenAssignNotExistsInAD() throws Exception {
        assertThat(applicationService.isApplicationAssignedToClient(appId, clientId)).isFalse();
    }


    @Action("Call rest Api for creating assignement given client and given service")
    @Test(dependsOnMethods = "givenAssignNotExistsInAD")
    public void whenICallRestAssignClientToService() throws Exception {
        String sagaUrl = cusService.assignServiceToClient(serviceDescriptor.getId(), clientId);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaUrl, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga '" + sagaUrl + "' about assign service to client success?").isTrue();
    }


    @Test(dependsOnMethods = "whenICallRestAssignClientToService")
    public void thenICanReadRecordInDbWithAssignment() throws Exception {
        org.testng.Assert.assertTrue(cusService.isRecordInDbClientUsesService(clientId, serviceDescriptor.getId()));
    }


    @Assert("Get response service descriptor and check if has client")
    @Test(dependsOnMethods = "thenICanReadRecordInDbWithAssignment")
    public void thenICanFindClientInServiceRestResponse() throws Exception {
        org.testng.Assert.assertTrue(serviceDescriptorService.isServiceDescriptorAssignedToClient(clientId, serviceDescriptor.getId()));
    }


    @Assert("Check if in Active Directory client group is memeber of service group")
    @Test(dependsOnMethods = "thenICanFindClientInServiceRestResponse")
    public synchronized void thenICanGetAssignmentInAdByCms() throws Exception {
        boolean exist = false;
        for (int i = 0; i < ATTEMPT; i++) {
            if (applicationService.isApplicationAssignedToClient(appId, clientId)) {
                exist = true;
                break;
            }
            TimeUnit.SECONDS.timedWait(this, TIMEOUT_S);
        }
        assertThat(exist).as("Check if the application is assigned to the client.").isTrue();
    }


    @Assert("Check if the service descriptor exist in the database")
    @Test(dependsOnMethods = "thenICanGetAssignmentInAdByCms")
    public void thenICanGeAppIdFromServiceDescriptorDb() throws Exception {
        ServiceDescriptor sd = serviceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
        org.testng.Assert.assertEquals(sd.getAppId(), appId);
    }


    @Action("Call rest Api to remove client from service")
    @Test(dependsOnMethods = "thenICanGeAppIdFromServiceDescriptorDb")
    public void whenICallRestUnassignedClientFromService() throws Exception {
        String sagaUrl = cusService.unassignServiceToClient(serviceDescriptor.getId(), clientId);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaUrl, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga '" + sagaUrl + "' about unassign service to client success?").isTrue();
    }

    @Assert("Record with assignement was removed from DB")
    @Test(dependsOnMethods = "whenICallRestUnassignedClientFromService")
    public void thenRecordOfAssignmentDeletedFromDb() throws Exception {
        org.testng.Assert.assertFalse(cusService.isRecordInDbClientUsesService(clientId, serviceDescriptor.getId()));
    }


    @Test(dependsOnMethods = "thenRecordOfAssignmentDeletedFromDb")
    public void thenICanNotFindClientInServiceRestResponse() throws Exception {
        org.testng.Assert.assertFalse(serviceDescriptorService.isServiceDescriptorAssignedToClient(clientId, serviceDescriptor.getId()));
    }


    @Assert("Check if in Active Directory client group do not memeber of service group")
    @Test(dependsOnMethods = "thenICanNotFindClientInServiceRestResponse")
    public synchronized void thenICanNotGetAssignmentInAdByCms() throws Exception {
        boolean exist = true;
        for (int i = 0; i < ATTEMPT; i++) {
            if (!applicationService.isApplicationAssignedToClient(appId, clientId)) {
                exist = false;
                break;
            }
            TimeUnit.SECONDS.timedWait(this, TIMEOUT_S);
        }
        assertThat(exist).as("Check if the application is assigned to the client.").isFalse();
    }

    private void deleteEntities() {
        serviceDescriptor = serviceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
        if (serviceDescriptor != null) {
            String sagaId = cusService.unassignServiceToClient(serviceDescriptor.getId(), clientId);
            boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaId, SagaType.CLIENT_USES_SERVICE, ATTEMPT);
            if (sagaSuccess) {
                System.out.println("Client uses service was deleted.");
            } else {
                System.out.println("Unassignment service from client is failed. Perhaps the assignment doesn't exist.");
            }
            serviceDescriptorService.deleteServiceDescriptor(GROUP_ID, ARTIFACT_ID);
            serviceDescriptor = null;
        }
    }

}

