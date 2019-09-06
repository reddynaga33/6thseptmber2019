package com.openmatics.test.functional.platform.authorizationprovider;

import com.openmatics.test.functional.ApiTestBase;
import com.openmatics.testinstrumentation.platform.cases.authorizationprovider.CrudRole;
import com.openmatics.testinstrumentation.utils.testng.*;
import org.testng.annotations.*;


public class CrudRoleTest extends ApiTestBase {

    private CrudRole crudRole;


    @BeforeClass
    public void beforeCrudRoleTest() throws Exception {
        //platformInstrumentation = new PlatformInstrumentation(new EnvironmentConfiguration(envKey));
        crudRole = new CrudRole(platformInstrumentation);
    }

    @Arrange
    @Test()
    public void getGivenClient() throws Exception {
        crudRole.givenClient();
    }

    @Arrange
    @Test(dependsOnMethods = "getGivenClient")
    public void givenRoleNotExists() throws Exception {
        crudRole.givenRoleNotExists();
    }

    @Arrange
    @Test(dependsOnMethods = "givenRoleNotExists")
    public void givenGroups() throws Exception {
        // now is out of using
        //crudRole.givenGroups();
    }

    @Arrange
    @Test(dependsOnMethods = "givenRoleNotExists")
    public void givenPrivileges() throws Exception {
        crudRole.givenPrivileges();
    }

    @Arrange
    @Test(dependsOnMethods = "givenRoleNotExists")
    public void givenUsers() throws Exception {
        crudRole.givenUser();
    }

    @Action
    @Test(dependsOnMethods = {"givenRoleNotExists","givenGroups","givenPrivileges","givenUsers"})
    public void whenICreateNewRole() throws  Exception {
        crudRole. whenICreateNewRole();
    }

    @Assert
    @Test(dependsOnMethods = "whenICreateNewRole")
    public void thatICanGetTheNewRole() throws  Exception {
        crudRole.thatICanGetTheNewRole();
    }

    @Action
    @Test(dependsOnMethods = {"thatICanGetTheNewRole"})
    public void whenIUpdateTheRole() throws  Exception {
        crudRole.whenIUpdateTheRole();
    }

    @Assert
    @Test(dependsOnMethods = "whenIUpdateTheRole")
    public void thatICanGetTheUpdatedRole() throws  Exception {
        crudRole.thatICanGetTheUpdatedRole();
    }

    @Ignore
    @Action
    @Test(dependsOnMethods = {"thatICanGetTheUpdatedRole"})
    public void whenIUpdateTheRoleRemovePrivilegesAndUsers() throws  Exception {

    }

    @Action
    @Test(dependsOnMethods = "thatICanGetTheNewRole")
    public void whenIDeleteTheRole() throws  Exception {
        crudRole.whenIDeleteTheRole();
    }

    @Assert
    @Test(dependsOnMethods = "whenIDeleteTheRole")
    public void thatICanNotGetDeletedTheRole() throws  Exception {
        crudRole.thatICanNotGetDeletedTheRole();
    }


    @Ignore("Not necessary because role is deleted in before steps. Alert method is not implemented")
    @Cleaning
    @AfterTest
    public void cleaningAfterTest() throws  Exception {

    }

}
