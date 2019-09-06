package com.openmatics.test.functional.platform.client.create;

import com.openmatics.test.functional.ApiTestBase;
import com.openmatics.testinstrumentation.platform.cases.client.create.CreateClient;
import com.openmatics.testinstrumentation.utils.testng.Action;
import com.openmatics.testinstrumentation.utils.testng.Arrange;
import com.openmatics.testinstrumentation.utils.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


@Test(testName="CreateClientInfrastructure", groups="CreateClient")
public class CreateClientTest extends ApiTestBase {

    private CreateClient createClient;

    @BeforeClass
    public void beforeClassCreateClientTest(){
        createClient = new CreateClient(this.platformInstrumentation);
    }

    @Arrange
    @Test()
    public void givenClientDoesNotExist() throws Exception {
        createClient.clientDoesNotExist();
    }


    @Action
    @Test(dependsOnMethods = "givenClientDoesNotExist")
    public void whenICreateNewClient() throws  Exception {
        createClient.createNewClient();
    }

    @Assert
    @Test(dependsOnMethods = "whenICreateNewClient")
    public void thatICanGetTheNewClient() throws  Exception {
        createClient.getNewClient();
    }


    @Ignore
    @Assert
    @Test(dependsOnMethods = "whenICreateNewClient")
    public void thatCreatedChangeRequestEventOfTheNewClient() throws  Exception {
        //Now not implementation yet
        throw new NotImplementedException();
    }

}
