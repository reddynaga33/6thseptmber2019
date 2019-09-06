package com.openmatics.test.functional.platform.client.create;

import com.openmatics.test.functional.DbBaseTest;
import com.openmatics.testinstrumentation.platform.cases.client.create.CreateClientDefaultProperties;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.testng.Action;
import com.openmatics.testinstrumentation.utils.testng.Arrange;
import com.openmatics.testinstrumentation.utils.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;


@Test(testName="CreateClientInfrastructure", groups="CreateClientDefaultProperties", dependsOnGroups="CreateClientConfiguration")
public class CreateClientDefaultPropertiesTest extends DbBaseTest {

    private CreateClientDefaultProperties createClientDefaultProperties;

    @BeforeClass
    public void beforeClassCreateClientDefaultPropertiesTest() throws Exception {
        createClientDefaultProperties = new CreateClientDefaultProperties(platformInstrumentation, connectionFactory);
    }

    @Arrange("Check that the client is without default properties")
    @Test()
    public void givenDefaultClientPropertiesNotExist() throws Exception{
        createClientDefaultProperties.checkDefaultClientPropertiesNotExist();
    }

    @Action("Update the client default properties via rest API client-setting-service")
    @Test(dependsOnMethods = "givenDefaultClientPropertiesNotExist")
    public void whenIUpdateDefaultClientProperties() throws Exception{
        createClientDefaultProperties.updateDefaultClientProperties();
     }

    @Assert("Get created properties via rest API client-setting-service")
    @Test(dependsOnMethods = "whenIUpdateDefaultClientProperties")
    public void thatICanGetTheNewDefaultClientProperties()throws Exception {
        createClientDefaultProperties.getNewDefaultClientPropertiesByRest();
    }

    @Assert("Check the new properties is stored in DB")
    @Test(dependsOnMethods = "whenIUpdateDefaultClientProperties")
    public void thatTheNewDefaultClientPropertiesPersistInDb()throws Exception {
        createClientDefaultProperties.checkNewDefaultClientPropertiesPersistInDb();
    }

    @Override
    protected DbConnectionFactory initiateDbConnection() throws SQLException {
        return new DbConnectionFactory(platformInstrumentation.getEnvConf().getDbConnectionString());
    }
}
