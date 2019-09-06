package com.openmatics.test.functional.deviceconfigurationhierarchy;

import com.openmatics.test.functional.ApiTestBase;
import com.openmatics.testinstrumentation.platform.cases.deviceconfigurationhierarchy.DeviceConfigurationHierarchyTestCase;
import com.openmatics.testinstrumentation.utils.testng.*;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.json.JSONObject;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;

@Test(groups = "CreateDeviceConfiguration")
public class CreateDeviceConfigurationHierarchyTest extends ApiTestBase {

    private Map<String, JSONObject> givenDeviceConfigurations;
    private List<HttpResponse> responseList;

    private DeviceConfigurationHierarchyTestCase testcase;


    @BeforeClass
    public void beforeCreateConfigurationTest() throws Exception {
        // remove after finish development
        if (!platformInstrumentation.getEnvConf().getEnvKey().equalsIgnoreCase("daedalus")){
            throw new org.testng.SkipException("This test now is only for daedalus.");
        }
        testcase = new DeviceConfigurationHierarchyTestCase(platformInstrumentation, getAutomotiveInstr());
    }


    @Arrange("Load given device for which it will be created CmsClientConfiguration.")
    @Test()
    public void givenDeviceConfigurations() throws Exception {
        givenDeviceConfigurations = testcase.givenDeviceConfigurations(2);
    }


    @Ignore("Now not Implemented - Is it necessary")
    @Test(dependsOnMethods = "givenDeviceConfigurations")
    public void givenConfigurationsNotExist() throws Exception {
        // TODO for now ignore
    }


    @Action
    @Test(dependsOnMethods = "givenDeviceConfigurations")
    public void whenICreateNewConfigurations() throws Exception {
        responseList = testcase.whenICreateNewConfigurations(givenDeviceConfigurations);
    }


    @Assert("Creating methods returns right http status.")
    @Test(dependsOnMethods = "whenICreateNewConfigurations")
    public void thenResponseStatusOfNewConfigurationsIsOk() throws Exception {
        testcase.thenResponseStatusOfNewConfigurationsIsOk(responseList);
    }


    @Assert("Creating methods returns right new CmsClientConfiguration.")
    @Test(dependsOnMethods = "thenResponseStatusOfNewConfigurationsIsOk")
    public void thenResponseHasCorrectContentWithTheNewConfigurations() throws Exception {
        testcase.thenResponseHasCorrectContentWithTheNewConfigurations(responseList);
    }


    @Assert
    @Test(dependsOnMethods = "whenICreateNewConfigurations")
    public void thenNewConfigurationsAreStoredInGraphDb() throws Exception {
        testcase.thenNewConfigurationsAreStoredInGraphDb(responseList);
    }


    @Assert
    @Test(dependsOnMethods = "whenICreateNewConfigurations")
    public void thenICanGetNewConfigurationsByRest() throws Exception {
        testcase.thenICanGetNewConfigurationsByRest(responseList);
    }

}
