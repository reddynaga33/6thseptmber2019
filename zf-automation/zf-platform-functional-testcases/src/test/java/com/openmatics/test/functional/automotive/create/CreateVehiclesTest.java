package com.openmatics.test.functional.automotive.create;

import com.openmatics.test.functional.ApiTestBase;
import com.openmatics.testinstrumentation.platform.cases.asset.CreateVehicles;
import com.openmatics.testinstrumentation.utils.testng.*;
import org.testng.annotations.*;

@Test(groups = "CreateVehicles")
public class CreateVehiclesTest extends ApiTestBase {

    CreateVehicles createVehicles = null;

    @BeforeClass
    public void beforeClassCreateVehiclesTest(){
        createVehicles = new CreateVehicles(platformInstrumentation, getAutomotiveInstr());
    }


    @Arrange("Load vehicles for creating form resource json template.")
    @Test()
    public void givenVehiclesToCreate() throws Exception {
        createVehicles.getVehiclesToCreate();
    }

    @Arrange("Check that vehicle for creating not exist in cloud")
    @Test(dependsOnMethods = "givenVehiclesToCreate")
    public void givenVehiclesToCreateNotExist()throws Exception {
        createVehicles.checkVehiclesToCreateNotExist();
    }

    @Arrange("Check that assets of vehicle for creating not exist in cloud")
    @Test(dependsOnMethods = "givenVehiclesToCreate")
    public void givenAssetsOfVehiclesToCreateNotExist()throws Exception {
        createVehicles.checkAssetsOfVehiclesToCreateNotExist();
    }

    @Action("Create vehicles via initiation service request")
    @Test(dependsOnMethods = {"givenAssetsOfVehiclesToCreateNotExist","givenVehiclesToCreateNotExist"})
    public void whenICreateVehicles() throws Exception {
        createVehicles.createVehicles();
    }


    @Assert("Check if vehicles was created they are contains in result asset-management-service loadVehicles")
    @Test(dependsOnMethods = "whenICreateVehicles")
    public void thenICanGetTheNewVehicles() throws Exception {
        createVehicles.getNewVehicles();
    }


    @Assert("Check if for vehicles each vehicles was created asset -> Asset-management-service getAssets")
    @Test(dependsOnMethods = "whenICreateVehicles")
    public void thenICanGetAssetsOfTheNewVehicles() throws Exception {
        createVehicles.getAssetsOfNewVehicles();
    }

    // TODO check all fields of assets

}

