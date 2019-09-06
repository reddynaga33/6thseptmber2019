package com.openmatics.test.functional.platform.client.create;

import com.openmatics.test.functional.ApiTestBase;
import com.openmatics.testinstrumentation.platform.cases.client.create.HealthCheckServices;
import com.openmatics.testinstrumentation.utils.testng.Arrange;
import com.openmatics.testinstrumentation.utils.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(testName="CreateClientInfrastructure", groups="HealthCheckServices", dependsOnGroups="AssignServices2Client")
public class HealthCheckServicesTest extends ApiTestBase {

    private HealthCheckServices healthCheckServices;

    @BeforeClass
    public void beforeClassHealthCheckServicesTest() throws Exception {
        healthCheckServices = new HealthCheckServices(platformInstrumentation);
    }

    @Arrange("Load services to assign")
    @Test()
    public void givenAssignmentServices() throws Exception {
        healthCheckServices.getAssignmentServices();
    }

    @Assert("Call HealthCheck and verify running services")
    @Test(dependsOnMethods = "givenAssignmentServices")
    public void thatICanCallHealthCheckOfTheServices() throws Exception {
        healthCheckServices.callServicesHealthCheck();
    }

}
