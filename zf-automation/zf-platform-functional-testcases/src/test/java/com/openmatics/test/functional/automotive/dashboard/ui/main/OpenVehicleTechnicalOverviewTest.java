package com.openmatics.test.functional.automotive.dashboard.ui.main;

import com.openmatics.test.functional.DbBaseTest;
import com.openmatics.testinstrumentation.automotive.cases.dashboard.ui.VehicleDetail;
import com.openmatics.testinstrumentation.automotive.cases.dashboard.ui.VehicleOverview;
import com.openmatics.testinstrumentation.platform.cases.ui.Logon;
import com.openmatics.testinstrumentation.utils.selenium.ISeleniumService;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.testng.Action;
import com.openmatics.testinstrumentation.utils.testng.Arrange;
import com.openmatics.testinstrumentation.utils.testng.Assert;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

@Test(groups ="VehicleDetail")
public class OpenVehicleTechnicalOverviewTest extends DbBaseTest {

    String uiAppUrlTemplate = "https://dashboard-%s.openmatics.com/#/%s/";
    String userNameTemplate = "testuser.selenium00@zfiotcdo%s.onmicrosoft.com";
    String userPassw = "StartStart1";
    String userName = null;
    String clientId = null;
    JSONObject vehicle = null;

    Logon tcLogon;
    VehicleOverview tcVehicleOverview;
    VehicleDetail tcVehicleDetail;


    @BeforeClass
    public void beforeClassOpenVehicleTechnicalOverviewTest() throws Exception
    {
        clientId = platformInstrumentation.getClientConf().getClient().getId();
        ISeleniumService service = getSeleniumService();
        service.setDimension(1600,1200);
        tcVehicleOverview = new VehicleOverview(getAutomotiveInstr(), service);
        tcVehicleDetail = new VehicleDetail(service, connectionFactory);
        String uiAppUrl = String.format(uiAppUrlTemplate, envKey.toLowerCase(), clientId);
        tcLogon = new Logon(service, uiAppUrl);
    }

    @Arrange("load given user and assign to the current client")
    @Test()
    public void givenUser() throws Exception
    {
        userName = String.format(userNameTemplate, envKey);
        tcLogon.givenDefaultSeleniumUser(userName, clientId, platformInstrumentation.getEnvConf());
    }


    @Action("Logon to the application DashboardUi.")
    @Test(dependsOnMethods = "givenUser")
    public void whenILogon() throws Exception
    {
        tcLogon.Logon(userName, userPassw, 10);
    }


    @Assert("Assert user is displayed as logon user in the page (right top corner).")
    @Test(dependsOnMethods = "whenILogon")
    public void thenTheUserNameIsVisibleAsLogedUser() throws Exception
    {
        tcLogon.LogonAssert(userName, 10);
    }


    @Arrange("Get given vehicle for filtering in overview")
    @Test(dependsOnMethods = "thenTheUserNameIsVisibleAsLogedUser")
    public void givenVehicle() throws Exception
    {
        Map<String,JSONObject> vehicles = getAutomotiveInstr().getVehicleConf().loadVehicles(true,1);
        vehicle = vehicles.get( (vehicles.keySet().toArray())[0]);
    }



    @Action("Filer vehicles on view - open filter and put licence place for filtering")
    @Test(dependsOnMethods = "givenVehicle")
    public void whenIUseVehicleFilter()
    {
        String licencePlate = vehicle.getString("licensePlate");
        tcVehicleOverview.filterVehiclesInOverview(licencePlate);
    }


    @Assert("Verify if given vehicle was filtered in overview")
    @Test(dependsOnMethods = "whenIUseVehicleFilter")
    public void thenInListVehicleIsOneVehicle()
    {
        tcVehicleOverview.assertOneVehicleInList(vehicle);
    }


    @Arrange("Open vehicle detail")
    @Test(dependsOnMethods = "thenInListVehicleIsOneVehicle")
    public void givenUiVehicleDetail() throws Exception
    {
        String windowsHandlerName = tcVehicleOverview.openVehicleDetail(vehicle);
        // Vehicle detail is open in in new Tab
        getSeleniumService().getDriver().switchTo().window(windowsHandlerName);
    }



    @Arrange("Get given gps position load from DB and add to extension object in vehicle")
    @Test(dependsOnMethods = "thenInListVehicleIsOneVehicle")
    public void givenGpsPosition() throws Exception
    {
        // Signals values will be added to vehicle Json as json array to the new 'otherExt' object
        tcVehicleDetail.getSignalValuesFromDb(vehicle);
    }


    @Action("In vehicle detail select tab with Technical overview")
    @Test(dependsOnMethods = "givenUiVehicleDetail")
    public void whenISelectTechnicalOverview() throws Exception
    {
        tcVehicleDetail.selectTabTechnicalOverview();
    }


    @Assert("If in Technical overview is visible correct gps position latitude")
    @Test(dependsOnMethods = "whenISelectTechnicalOverview")
    public void thenICanReadGpsPositionLatitude() throws Exception
    {
        tcVehicleDetail. assertGpsPosition(vehicle, "Lat", 10);
    }

    @Assert("If in Technical overview is visible correct gps position longitude")
    @Test(dependsOnMethods = "whenISelectTechnicalOverview")
    public void thenICanReadGpsPositionLongitude() throws Exception
    {
        tcVehicleDetail.assertGpsPosition(vehicle, "Long", 10);
    }


    @Action("Logout user from UI")
    @Test(dependsOnMethods={"whenILogon"}, priority = 1000)
    public void whenUserLogout() throws Exception
    {
        tcLogon.Logout(10);
    }


    @Assert("Check if user is logout form UI")
    @Test(dependsOnMethods={"whenUserLogout"}, priority = 1000)
    public void thatIGetInBrowserMsLogoutPage() throws Exception
    {
        tcLogon.LogoutAssert(10);
    }

    @Override
    protected DbConnectionFactory initiateDbConnection() throws Exception {
        return new DbConnectionFactory(platformInstrumentation.getEnvConf().getDbConnectionString(this.platformInstrumentation.getClientConf().getClient().getId()));
    }

}