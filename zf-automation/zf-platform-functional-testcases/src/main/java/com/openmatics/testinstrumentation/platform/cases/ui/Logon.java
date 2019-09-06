package com.openmatics.testinstrumentation.platform.cases.ui;

import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientManagementApi;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import com.openmatics.testinstrumentation.utils.selenium.ISeleniumService;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Logon {

    static final String USER_PROFILE_MENU_XPATH = "//user-profile[contains(@class,'ng-isolate-scope')]/div/div[contains(@class,'dropdown')]";
    static final String USER_LOGOUT_ITEM_XPATH = "//a[contains(@class,'menu-item') and contains(@ng-click,'$ctrl.logout()')]";
    static final String USER_NAME_SPAN_CSS = "span.user-name.ng-binding";
    static final String MS_LOGIN_PAGE_URL = "https://login.microsoftonline.com";
    static final int WAIT_TIME_SLEEP = 250;
    private ISeleniumService service;
    private String url;

    public Logon(ISeleniumService service, String url){
      this.service = service;
      this.url = url;
    }


    /**
     * Get given user for test and also add to the client.
     * Selenium will log with this user and execute test with his logon.
     * @param name
     * @param clientId
     * @throws Exception
     */
    public void givenDefaultSeleniumUser(String name, String clientId, IEnvironmentConfiguration configuration) throws Exception
    {
        String userId = null;
        ClientManagementApi cmsApi = new ClientManagementApi(configuration);
        for(Object item : cmsApi.getUserApi().getUsers().getContentAsJsonArray()){
            JSONObject user = (JSONObject) item;
            if (user.getString("login").equals(name)){
                userId = user.getString("id");
                break;
            }
        }
        cmsApi.getUserApi().createUserGroupClientMembership(userId, clientId);
    }


    public String givenTheNewUserFormMail(String name, String emailContext) throws Exception
    {
        throw new NotImplementedException();
    }


    /**
     * Logon user to the portal on given url e.g. dashboard, backoffice ...
     * @param user
     * @param passw
     * @throws Exception
     */
    public void Logon(String user, String passw, int timeOutInSec) throws Exception
    {
        service.getDriver().get(url);
        WebDriverWait wait = new WebDriverWait(service.getDriver(), timeOutInSec);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("loginfmt"))).sendKeys(user);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9"))).click();
        service.getDriver().findElement(By.name("passwd")).sendKeys(passw + Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("idBtn_Back"))).click();
        System.out.println(service.getDriver().getCurrentUrl());
    }


    /**
     * Verify is user logon this way - check if his name in user profile area (right-top corner)
     * @param userName
     * @throws Exception
     */
    public void LogonAssert(String userName, int timeOutInSec)throws Exception
    {
        WebDriverWait wait = service.getWait(timeOutInSec, WAIT_TIME_SLEEP);
        //In try for case when on page is apears toaste message
        try{
            wait.until(ExpectedConditions.invisibilityOf(service.getDriver().findElement(By.id("toast-container"))));
        }
        catch(Exception e) {}

        By userSpanSelector = By.cssSelector(USER_NAME_SPAN_CSS);
        wait.until(ExpectedConditions.numberOfElementsToBe(userSpanSelector, 1));
        wait.until(ExpectedConditions.visibilityOf(service.getDriver().findElement(userSpanSelector)));
        Assert.assertEquals(service.getDriver().findElement(userSpanSelector).getText(), userName);
    }


    /**
     * Logout current user from platform ui
     * @throws Exception
     */
    public void Logout(int timeOutInSec) throws Exception
    {
        WebDriverWait wait = service.getWait(timeOutInSec, WAIT_TIME_SLEEP);
        //In try for case when on page is apears toaste message
        try{
            wait.until(ExpectedConditions.invisibilityOf(service.getDriver().findElement(By.id("toast-container"))));
        }
        catch(Exception e) {}

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(USER_PROFILE_MENU_XPATH))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(USER_LOGOUT_ITEM_XPATH))).click();
    }


    /**
     * Verify was user logout.
     * Page is redirect to miscrosoft page
     * @throws Exception
     */
    public void LogoutAssert(int timeOutInSec) throws Exception
    {
        WebDriverWait wait = service.getWait(timeOutInSec, WAIT_TIME_SLEEP);
        wait.until(ExpectedConditions.urlContains(MS_LOGIN_PAGE_URL));
    }

}
