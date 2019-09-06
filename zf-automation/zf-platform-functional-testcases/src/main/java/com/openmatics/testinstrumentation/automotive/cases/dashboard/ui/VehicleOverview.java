package com.openmatics.testinstrumentation.automotive.cases.dashboard.ui;

import com.openmatics.testinstrumentation.automotive.AutomotiveInstrumentation;
import com.openmatics.testinstrumentation.utils.selenium.ISeleniumService;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;


public class VehicleOverview {

    private final String VEHICLE_ROWS_CSS = "div.vehicle-row";
    private ISeleniumService service = null;
    AutomotiveInstrumentation instrumentation;
    private WebDriver webDriver;

    public VehicleOverview(AutomotiveInstrumentation instrumentation, ISeleniumService service)
    {
       this.service = service;
       this.instrumentation = instrumentation;
       this.webDriver = service.getDriver();
    }

    /**
     * In vehicle overview fill filter (searching vehicles) input value
     * @param value
     */
    public void filterVehiclesInOverview(String value)
    {
        String inputFilterXpath = "//om-filter-input[contains(@class,'ng-scope ng-isolate-scope')]/input";
        WebDriverWait wait = service.getWait(10, 500);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='toogle-btn is-folded']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(inputFilterXpath))).sendKeys(value);
    }


    /**
     * Verify that was in overview filtered one require vehicle
     * @param vehicle
     */
    public void assertOneVehicleInList(JSONObject vehicle)
    {
        String licencePlate = vehicle.getString("licensePlate");
        WebDriverWait wait = service.getWait(10, 500);

        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(VEHICLE_ROWS_CSS),1));
        List<WebElement> vehiclesRows = webDriver.findElements(By.cssSelector(VEHICLE_ROWS_CSS));

        System.out.println("Filtered vehicle: " + vehiclesRows.get(0).getText());
        Assert.assertTrue(vehiclesRows.get(0).getText().contains(licencePlate));
        Assert.assertEquals(vehiclesRows.size(), 1);
    }


    /**
     * In vehicle overview find vehicle by licencePlate and click for open VehicleDetail and return it's windows hadle.
     * Because in now is detail opened on the new tab so is necessary switch in to new tab.
     * @param vehicle
     * @return
     * @throws Exception
     */
    public String openVehicleDetail(JSONObject vehicle) throws Exception
    {
        String xpath = "//div[contains(@class,'vehicle-row flex-wrapper')]//span[contains(.,'%s')]/parent::div/parent::div/parent::div/a";
        int tabCount = webDriver.getWindowHandles().size();
        String licencePlate = vehicle.getString("licensePlate");
        xpath = String.format(xpath, licencePlate);
        WebDriverWait wait = service.getWait(10, 333);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();

        // If was opened new tab
        if (tabCount < webDriver.getWindowHandles().size())
        {
            service.getDriver().findElement(By.tagName("body")).sendKeys(Keys.CONTROL, Keys.TAB);
            tabCount++;
        }
        return webDriver.getWindowHandles().toArray()[tabCount - 1].toString();
    }

}



