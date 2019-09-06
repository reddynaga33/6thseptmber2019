package com.openmatics.test.functional.platform.client.create;

import com.openmatics.test.functional.ApiTestBase;
import com.openmatics.testinstrumentation.platform.cases.client.create.CreateUsers;
import com.openmatics.testinstrumentation.utils.testng.*;
import org.json.JSONArray;
import org.testng.annotations.*;


@Test(testName="CreateClientInfrastructure", groups="CreateUsers", dependsOnGroups="CreateClient")
public class CreateUsersTest extends ApiTestBase {

    private JSONArray users;
    private CreateUsers createUsers;

    @BeforeClass
    public void beforeClassCreateUsersTest() throws Exception {
        createUsers = new CreateUsers(platformInstrumentation);
    }

    @Arrange()
    @Test
    public void givenUsersToCreate() throws Exception {
        users = createUsers.givenUsers();
    }


    @Arrange
    @Test(dependsOnMethods = "givenUsersToCreate")
    public void givenUsersToCreateNotExists() throws Exception {
        createUsers.checkUsersToCreateNotExists(users);
    }


    @Action
    @Test(dependsOnMethods = "givenUsersToCreateNotExists")
    public void whenICreateUsers() throws Exception {
        createUsers.createUsers(users);
    }


    @Assert
    @Test(dependsOnMethods = "whenICreateUsers")
    public void thenICanGetTheUsersInClientExtendedInfo() throws Exception {
        createUsers.getClientWithUsersByCmsRestAndAssert(users);
    }


    @Assert
    @Test(dependsOnMethods = "whenICreateUsers")
    public void thenICanGetNewUserByRest() throws Exception {
        createUsers.getTheUserByCmsRestAndAssert(users);
    }


    @Ignore
    @Assert
    @Test(dependsOnMethods = "whenICreateUsers")
    public void thenICanGetPasswordFromEmail() throws Exception {
        createUsers.receiveEmailsWithUserPassword();
    }

}
