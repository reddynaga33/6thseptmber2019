package com.openmatics.testinstrumentation.platform.utils.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@Deprecated()
public class Logon2AppUI {

    static String USER_PROFILE_MENU_XPATH = "//user-profile[contains(@class,'ng-isolate-scope')]/div/div[contains(@class,'dropdown')]";
    static String USER_LOGOUT_ITEM_XPATH = "//a[contains(@class,'menu-item') and contains(@ng-click,'$ctrl.logout()')]";

    public static void Logon(WebDriver driver, String user, String passwd, int timeOutInSec) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("loginfmt"))).sendKeys(user);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9"))).click();
        driver.findElement(By.name("passwd")).sendKeys(passwd + Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("idBtn_Back"))).click();
        System.out.println(driver.getCurrentUrl());
    }


    public static void Logout(WebDriver driver, int timeOutInSec) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
        // In try for case then on page is not toaste message
        try{
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));
        }
        catch(Exception e) {}

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(USER_PROFILE_MENU_XPATH))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(USER_LOGOUT_ITEM_XPATH))).click();
    }

    public static void exploreXpath(WebDriver driver )
    {
        String USER_LOGOUT_ITEM_CSS = "a[class=menu-item][ng-click*='logout']";
        String USER_PROFILE_MENU_CSS = "user-profile.ng-isolate-scope";

        WebDriverWait wait = new WebDriverWait(driver, 5);
        // In try for case then on page is not toaste message
        try{
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));
        }
        catch(Exception e) {}

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(USER_PROFILE_MENU_XPATH))).click();
        WebElement userProfile = driver.findElement(By.xpath(USER_PROFILE_MENU_XPATH));
        System.out.println("userProfile: " + userProfile.getText());

        WebElement menuHeader = userProfile.findElement(By.xpath("./div[1]"));
        System.out.println("menuHeader: " + menuHeader.getAttribute("ng-click"));

        WebElement dropdown = menuHeader.findElement(By.xpath("..//div[contains(@class,'dropdown')]"));
        System.out.println("dropdown: " + dropdown.getAttribute("class"));

        java.util.List<WebElement> aList = dropdown.findElements(By.xpath("..//a"));
        System.out.println("Count on finded elements: " + aList.size());

        for (WebElement element : aList){
            System.out.println("------------------------------");
            System.out.println(element.getText());
        }
    }

}
