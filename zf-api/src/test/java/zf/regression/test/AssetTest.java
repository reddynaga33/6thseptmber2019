package zf.regression.test;

import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.MicrosoftLoginPage;
import zf.regression.pages.AssetPage;

public class AssetTest extends AssetPage{
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {

		startTest(testName.getName());

	}

 	@Test 
	public void C64519CreatenewAssetWithUI() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		String createdAssetstWithAllFeilds = createAssetstWithAllFeilds();
		String assetID = assetCreationDBvalidation(createdAssetstWithAllFeilds,"SelectAssetDetailsWithName");
		deleteAssetInDataBase(assetID);
	}

 	@Test
	public void C64521updateExistingAsset() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		String AssetstName = createAssetstWithAllFeilds();
		String updatedAssetName = UpdateExistingAssetst(AssetstName);
		String assetID = assetCreationDBvalidation(updatedAssetName,"SelectAssetDetailsWithName");
		deleteAssetInDataBase(assetID);
	}

 	@Test
	public void C64522checkCreatedAssetInDB() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		String createdAssetstName = createAssetstWithAllFeilds();
		String assetID = assetCreationDBvalidation(createdAssetstName,"SelectAssetDetailsWithName");
		deleteAssetInDataBase(assetID);

	}

 	@Test
	public void C64523getCreatedAssetINRESTAPI() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		String createdAssetstName = createAssetstWithAllFeilds();
		String assetCreationDBvalidation = assetCreationDBvalidation(createdAssetstName,"SelectAssetDetailsWithName");
		GetNewlyCreatedAssetDetails("asset",assetCreationDBvalidation);
		deleteAssetInDataBase(assetCreationDBvalidation);
	}

 	@Test
	public void C64524checkUpdatedAssetInDB() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		String createdAssetstName = createAssetstWithAllFeilds();
		String updateExistingAssetst = UpdateExistingAssetst(createdAssetstName);
		String UpdatedassetID = assetCreationDBvalidation(updateExistingAssetst,"SelectAssetDetailsWithName");
		deleteAssetInDataBase(UpdatedassetID);
	}

 	@Test
	public void C64525VerifyTheUpdatedAssetByAPI() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		String createdAssetstName = createAssetstWithAllFeilds();
		String updateExistingAssetst = UpdateExistingAssetst(createdAssetstName);
		String assetCreationDBvalidation = assetCreationDBvalidation(updateExistingAssetst,"SelectAssetDetailsWithName");
		VerifyTheUpdatedAssetByAPI("asset",assetCreationDBvalidation);
		deleteAssetInDataBase(assetCreationDBvalidation);
	}

 	@Test
	public void C64527createAssetWithRestAPI() {
		String assetID = createAssetstWithRestAPI("asset","CreateAssetWithRestAPI");
		deleteAssetInDataBase(assetID);
	}


 	@Test
	public void C64531checkcreatedAssetWithRestAPIInDB() {
		String createAssetstWithRestAPI = createAssetstWithRestAPI("asset","CreateAssetWithRestAPI");
		String assetID = assetCreationDBvalidation(createAssetstWithRestAPI,"SelectAssetDetailsWithID");
		deleteAssetInDataBase(assetID);
	}

 	@Test
	public void C64532VerifyTheAssetCreatedFromAPIisAvailableIntoBackofficeUI() {
		String AssetName=createAssetstWithRestAPIReturnAssetName("asset","CreateAssetWithRestAPI");
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		VerifyTheAssetCreatedFromAPIisAvailableIntoBackofficeUI(AssetName);
		String AssetID = assetCreationDBvalidation(AssetName,"SelectAssetDetailsWithName");
		deleteAssetInDataBase(AssetID);
	}

 	@Test
	public void C64533VerifyUserCanUpdateTheAssetCreatedFromAPIByUsingBackofficeUI() {
		String AssetName=createAssetstWithRestAPIReturnAssetName("asset","CreateAssetWithRestAPI");
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		VerifyUserCanUpdateTheAssetCreatedFromAPIByUsingBackofficeUI(AssetName);
		String AssetID = assetCreationDBvalidation(AssetName,"SelectAssetDetailsWithName");
		deleteAssetInDataBase(AssetID);
	}

 	@Test
	public void C64537VerifyTheUpdatedAssetIntoApplicationDB() {
		String AssetID=createAssetstWithRestAPI("asset","CreateAssetWithRestAPI");
		 UpdateAssestByAPIReturnAssetID(AssetID, "asset");
		deleteAssetInDataBase(AssetID);

	}

 	@Test
	public void C64534VerifyUserCanUpdatedTheAssetCreatedFromUIByUsingAPIAndChangesReflectedOnBackofficeUI() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		String AssetName=createAssetstWithAllFeilds();
		String AssetID=assetCreationDBvalidation(AssetName,"SelectAssetDetailsWithName");
		String UpdatedAssetName=VerifyUserCanUpdatedTheAssetCreatedFromUIByUsingAPIAndChangesReflectedOnBackofficeUI(AssetID,"asset");
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		VerifyTheAssetCreatedFromAPIisAvailableIntoBackofficeUI(UpdatedAssetName);
		deleteAssetInDataBase(AssetID);
	}
	

	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}

}
