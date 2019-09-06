package com.openmatics.test.functional.automotive.delete;

//import com.openmatics.testinstrumentation.automotive.cases.DeleteVehicles;
import com.openmatics.testinstrumentation.utils.testng.*;
import org.testng.annotations.*;

@Test(groups ="DeleteVehicles")
public class DeleteVehiclesTest {

    //DeleteVehicles deleteVehicles = null;

    @BeforeClass
    public void beforeClassDeleteVehiclesTest(){
        //deleteVehicles = new CreateVehicles(platformInstrumentation, getAutomotiveInstr());
    }


    // TODO clean from template or claen all vehicles of current client
    @Arrange("Load vehicles for deleting form resource json template.")
    @Test()
    public void givenVehiclesToDelete() throws Exception {
        //deleteVehicles.getVehiclesToCreate();
    }

    @Test(dependsOnMethods = "givenVehiclesToDelete")
    public void deleteDevices() throws Exception
    {
        //TODO
    }

    @Test(dependsOnMethods = "deleteDevices")
    public void deleteVehicles() throws Exception
    {
        //TODO
    }

}
