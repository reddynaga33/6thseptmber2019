package com.openmatics.testinstrumentation.automotive.cases.dashboard.ui;

import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.utils.selenium.ISeleniumService;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.ResultSet;


public class VehicleDetail {

    private ISeleniumService service = null;
    private WebDriver webDriver;
    private final DbConnectionFactory connectionFactory;


    public VehicleDetail(ISeleniumService service, DbConnectionFactory connectionFactory) {
        this.service = service;
        this.webDriver = service.getDriver();
        this.connectionFactory = connectionFactory;
    }

    /**
     * Load values from signal_value of real-time-data-service and its to the otherExt in to input vehicle JsonObject
     * @param vehicle
     * @throws Exception
     */
    public void getSignalValuesFromDb(JSONObject vehicle) throws Exception
    {
        if (!vehicle.has("otherExt")) vehicle.put("otherExt", new JSONObject());
        String vehicleId = vehicle.getString("id");

        String query = "SELECT * FROM [real_time_data_service_schema].[signal_value] WHERE vehicle_id='%s'";
        query = String.format(query, vehicleId);
        JSONArray SignalValuesColl = new JSONArray();

        try (DbQueryService sqlAccess = new DbQueryService(connectionFactory)) {
            ResultSet rs = sqlAccess.executeQuery(query);
            while (rs.next()) {
                String value = rs.getString("value");
                if (value != null) {
                    JSONObject signalValue = new JSONObject();
                    signalValue.put("lastTimestam", rs.getTime("last_timestamp"));
                    signalValue.put("signalName", rs.getString("signal_name"));
                    signalValue.put("type", rs.getString("type"));
                    signalValue.put("value", rs.getString("value"));
                    SignalValuesColl.put(signalValue);
                }
            }
            sqlAccess.closeQuery(rs);
        }
        vehicle.getJSONObject("otherExt").put("signalValues",SignalValuesColl);
        System.out.println(vehicle);
    }

    /**
     * In vehicle detail select tab Technical Overview
     * @throws Exception
     */
    public void selectTabTechnicalOverview() throws Exception
    {
        String overviewSelector = "//div[contains(@class,'tab') and contains(@ng-click, 'VEHICLE_OVERVIEW')]";
        WebDriverWait wait = service.getWait(10, 200);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(overviewSelector))).click();
    }

    /**
     * Find in Technical overview gps position (latitude or longtitude depends on input ValueType - 'lat' or 'long')
     * and assert this against value from vehicle from object signals value which in otherExt.
     * @param vehicle
     * @param valueType - 'lat' or some other
     * @param timeOutSec
     * @throws Exception
     */
    public String assertGpsPosition(JSONObject vehicle, String valueType, int timeOutSec) throws Exception
    {
        JSONArray signalsValues = vehicle.getJSONObject("otherExt").getJSONArray("signalValues");
        String locator = "//vehicle-signal/*/div[text()='%s']/preceding-sibling::div[contains(@class,'value ng-binding')]";
        String signalName;

        if (valueType.toLowerCase().startsWith("lat"))
        {
            signalName = "gps.latitude";
            locator = String.format(locator, "Latitude");
        }
        else
        {
            signalName = "gps.longitude";
            locator = String.format(locator, "Longitude");
        }

        WebDriverWait wait = service.getWait(10, 333);
        JSONObject vehicleLongtitude = JsonUtils.getJsonItem(signalsValues, "signalName", signalName);
        String vehicleValue = vehicleLongtitude.getString("value");

        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(locator),1));
        WebElement signal = webDriver.findElement(By.xpath(locator));
        String pageValue = signal.getText();
        System.out.println("Find postition in UI: " + pageValue);
        Assert.assertEquals(pageValue, vehicleValue);
        return pageValue;
    }

}



