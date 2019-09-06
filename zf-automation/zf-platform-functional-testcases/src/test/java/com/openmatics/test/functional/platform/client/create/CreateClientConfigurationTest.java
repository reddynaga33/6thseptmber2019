package com.openmatics.test.functional.platform.client.create;

import com.openmatics.test.functional.DbBaseTest;
import com.openmatics.testinstrumentation.platform.cases.client.create.CreateClientConfiguration;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.testng.Action;
import com.openmatics.testinstrumentation.utils.testng.Arrange;
import com.openmatics.testinstrumentation.utils.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;



@Test(testName="CreateClientInfrastructure", groups="CreateClientConfiguration", dependsOnGroups="CreateClient")
public class CreateClientConfigurationTest extends DbBaseTest {

    private CreateClientConfiguration createClientConfiguration;

    @BeforeClass
    public void beforeClassCreateClientDefaultPropertiesTest() throws Exception {
        createClientConfiguration = new CreateClientConfiguration(platformInstrumentation, connectionFactory);
    }

    @Arrange("Check that client has not any CmsClientConfiguration")
    @Test()
    public void givenClientClientConfigurationNotExist()  throws Exception {
        createClientConfiguration.checkClientClientConfigurationNotExist();
    }


    @Action("Create new client CmsClientConfiguration (CmsClientConfiguration for creating was loaded from project resource)")
    @Test(dependsOnMethods = "givenClientClientConfigurationNotExist")
    public void whenICreateClientConfiguration() throws Exception {
        createClientConfiguration.createClientConfiguration();
    }


    @Assert("Check that new client CmsClientConfiguration is stored in db in client management service")
    @Test(dependsOnMethods = "whenICreateClientConfiguration")
    public void thatTheClientConfigurationPersistInDb() throws Exception {
        createClientConfiguration.checkClientConfigurationPersistInDb();
    }


    @Ignore
    @Assert("Check that new client CmsClientConfiguration property are returned via API")
    @Test(dependsOnMethods = "whenICreateClientConfiguration")
    public void thatICanGetTheClientConfiguration() throws Exception {
        // TODO check via Rest - now not implemented yet
        createClientConfiguration.getClientConfigurationByApi();
    }


    @Override
    protected DbConnectionFactory initiateDbConnection() throws Exception {
        return new DbConnectionFactory(platformInstrumentation.getEnvConf().getDbConnectionString());
    }


}
