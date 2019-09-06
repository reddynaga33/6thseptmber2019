package com.openmatics.test.functional.platform.client.create;

import com.openmatics.test.functional.ApiTestBase;
import com.openmatics.testinstrumentation.platform.cases.client.create.AssignServices2Client;
import com.openmatics.testinstrumentation.utils.testng.Action;
import com.openmatics.testinstrumentation.utils.testng.Arrange;
import com.openmatics.testinstrumentation.utils.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(testName="CreateClientInfrastructure", groups="AssignServices2Client", dependsOnGroups="CreateClientDefaultProperties")
public class AssignServices2ClientTest extends ApiTestBase {

    private AssignServices2Client assignServices2Client;

    @BeforeClass
    public void beforeClassAssignServices2ClientTest() throws Exception {
        assignServices2Client = new AssignServices2Client(platformInstrumentation);
    }

    @Arrange("Load services which may be assign to the client")
    @Test()
    public void givenServicesAssignToClient() throws Exception {
        assignServices2Client.loadServicesAssignToClient();
    }


    @Action("Assign service to the client by initiation service")
    @Test(dependsOnMethods = "givenServicesAssignToClient")
    public void whenIDeployServicesToClient() throws Exception {
        assignServices2Client.deployServicesToClient();
    }

    @Action("Assign service to the client group in add")
    @Test(dependsOnMethods = "whenIDeployServicesToClient")
    public void whenIAssignServicesToGroup() throws Exception {
        assignServices2Client.assignServicesToGroup();
    }

    @Assert
    @Test(dependsOnMethods = "whenIAssignServicesToGroup")
    public void thatICanGetTheAssignmentAppToGroup()  throws Exception {
        assignServices2Client.getAssignmentAppToGroup();
    }

    @Assert("Check that service are returned in app list in response of getClient via API")
    @Test(dependsOnMethods = "whenIAssignServicesToGroup")
    public void thatICanGetClientWithTheServices()  throws Exception {
        assignServices2Client.getClientWithAssignedServices();
    }

}
