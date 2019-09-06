package com.openmatics.test.functional.automotive.create;

import com.openmatics.test.functional.ApiTestBase;
import com.openmatics.testinstrumentation.platform.cases.asset.CreateDevices;
import com.openmatics.testinstrumentation.utils.testng.*;
import org.testng.annotations.*;


@Test(groups = "CreateDevices")
public class CreateDevicesTest extends ApiTestBase {

    private CreateDevices createDevices;

    @BeforeClass
    public void beforeClassCreateDevicesTest() throws Exception {
        createDevices = new CreateDevices(platformInstrumentation, getAutomotiveInstr());
    }

    @Arrange("Load devices for creating form resource json template.")
    @Test()
    public void givenDevicesToCreate() throws Exception {
        createDevices.loadDevicesToCreate();
    }


    @Arrange("Check that devices for creating not exist")
    @Test(dependsOnMethods = "givenDevicesToCreate")
    public void givenDevicesToCreateNotExist()throws Exception {
        createDevices.checkDevicesToCreateNotExist();
    }


    @Action("Create device via initiation service request")
    @Test(dependsOnMethods = "givenDevicesToCreateNotExist")
    public void whenICreateDevice() throws Exception {
        createDevices.createDevices();
    }



    @Assert("Check if vehicles was created they are contains in response of device management service")
    @Test(dependsOnMethods = "whenICreateDevice")
    public void thatICanGetTheNewDevices() throws Exception {
        // TODO compare fields of created devices ?
        createDevices.getNewDevices();
    }


    @Ignore
    @Assert("Check if possible send real time message to device (Iothub) and message stored to the DB ")
    @Test(dependsOnMethods = "whenICreateDevice")
    public void thatICanSendMessageToTheNewDeviceViaIothub() throws Exception {
        createDevices.trySendMessageToIothub();
    }


}

