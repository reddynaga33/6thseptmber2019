package zf.test;

import org.testng.annotations.Test;
import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.MicrosoftLoginPage;
import zf.pages.ZFAssetPage;

public class ZFAssetTest extends ZFAssetPage{
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());

	}
	//TestRail#C48954 - Navigate to Assets Page

		 //TestRail#C48965 - Verify the UI of Search section in Asset landing Page
		@Test
		public void AAC48954_C48965assetPageValidation() {
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			assetPageValidation();
		}
		//TestRail#C48966 - Verify search with  valid search key with basic search parameters
		@Test
		public void C48966assetSearch() {
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			String Assetname =createAssetstWithAllFeilds();
			assetSearch(Assetname);
		}
		//TestRail#C48968 - Verify search with advanced search filters
		@Test
		public void C48968assetAdvancedSearch() {
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			assetAdvancedSearch();
		}
		//TestRail#C48969 - Verify Clear All Filter in Assets Home Page
		 @Test
		public void C48969clearAllFilter() {
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			clearAllFilter();
		}
		 //TestRail#C48967 - Verify search with non-existing/invalid search key
		 @Test
		 public void C48967assetSearchWithInvalidKey() {
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			assetSearchWithInvalidKey();
		}
	//TestRail#C48958 - Verify the UI of Create Asset Page -> Additional Details 
	 @Test
		public void AAC48958CreateAssetstWithAllFeilds() {
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			createAssetstWithAllFeilds();
		}

	//TestRail#C49606 - Verify the button 'Set Actions' present in Assets > Create Asset>Basic Details  
	 @Test
		public void C49606CreateAssetstWithActions() {
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			CreateAssetstWithActions();
		}
	//TestRail#C48963 - Edit the details of  an existing asset
	@Test
		public void C48963editExistAssetDetails() {
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			editExistAssetDetails();
		}
	//TestRail#C48960 - Create child asset of newly added asset
	@Test
		public void C48960CreateChildAssettWithAllFeilds(){
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			createChildAssetWithAllFeilds();
		}
	//TestRail#C52534 - Verify that a user is able to create an asset with Asset type Vehicle by providing valid inputs for all autopopulated metadata keys
	@Test
		public void C52534CreateAssetstWithAllAutopopulatedMetadataKeys(){
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			createAssetstWithAllAutopopulatedMetadataKeys();
		}

	//TestRail#C52535 -Verify that a user is able to create an asset with Asset type Vehicle by providing valid inputs for only the mandatory autopopulated metadata keys
	@Test
		public void C52535CreateAssetstWithMandatoryAutopopulatedMetadataKeys(){
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			createAssetstWithMandatoryAutopopulatedMetadataKeys();
		}
	//TestRail#C48956 - Verify the UI of Create Asset Page -> Basic Details 
	@Test
		public void C48956FieldValidationsMandatoryFieldVin(){
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			fieldValidationsMandatoryFieldVin();
		}
	//TestRail#C52533 - Verify that all vehicle related metadata is autopopulated when user try to create an asset with AssetType Vehicle
	@Test
		public void C52533VerifyVehicleAssetstWithMandatoryAutopopulatedMetadataKeys(){
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			verifyVehicleAssetstWithMandatoryAutopopulatedMetadataKeys();
		}
	//TestRail#C48961 - Create Asset -> Without mandatory inputs
	@Test
		public void C48961CreateAssetWithoutMandatoryInput(){
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			createAssetWithoutMandatoryInput();
		}
	//TestRail#C49611 - Verify the functionality of Execute Actions of an added asset_without parameters deactivate
	@Test
		public void C49611ExecuteActionWithoutParamater(){
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			executeActionWithoutParamater();
		}
	//TestRail#C49612 - Verify the functionality of Execute Actions of an added asset with parameters activate
	@Test
		public void C49612ExecuteActionWithParamater(){
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			executeActionWithParamater();
		}

	//TestRail#C49613 - Verify the functionality of Edit actions
	@Test
		public void C49613EditAssetstActions() {
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			editAssetstActions();
		}
	//TestRail#C49610 - Verify the functionality of adding an asset with actions
	@Test
		public void C49610VerifyAssetActionsUI() {
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			verifyAssetActionsUI();
		}
	//TestRail#C48955 - Verify the UI of Assets landing page
	@Test
		public void C48955OperatorHasNoAccessToCreateAsset()
		{		
			microsoftlogin.microsoftLogin(EnvironmentManager.getOperatorUserName(),EnvironmentManager.getOperatorPassword());
			operatorHasNoAccessToCreateAsset();
		}

	@Test
	public void C63899UserWithNoPrivilegeCannotAccessAssetsPage()
		{		
			microsoftlogin.microsoftLogin(EnvironmentManager.getNoPrivilegeUserName(),EnvironmentManager.getNoPrivilegePassword());
			userWithNoPrivilegeCannotAccessAssetsPage();
		}
	//TestRail#C49341 - Sort Assets in Assets Home page
	@Test
		public void C49341SortAssetsInAssetsHomepage()
		{		
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			SortAssetsInAssetsHomepage();
		}

	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
}
