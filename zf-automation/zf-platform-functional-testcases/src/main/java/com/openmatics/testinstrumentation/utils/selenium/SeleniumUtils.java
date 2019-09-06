package com.openmatics.testinstrumentation.utils.selenium;

//import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

    public static void clickOnElementByXPath(ISeleniumService service, String xpath, int timeoutSec) {
        WebDriverWait wait = service.getWait(timeoutSec, 200);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }

}
