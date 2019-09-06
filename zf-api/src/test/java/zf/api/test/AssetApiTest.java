package zf.api.test;

import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.api.pages.AssetApiPage;
import zf.regression.pages.AssetPage;


public class AssetApiTest extends AssetApiPage{
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	
//	@Test(priority=1,enabled = true)
	public void CreateAsset() throws InterruptedException {
		createAsset("asset","CreateAsset");
	}
	
//	@Test(priority=1,enabled = true)
	public void ValidatenewlycreatedAssetInDB() throws InterruptedException {
		String assetID =createAsset("asset","CreateAsset");
		validateNewAssetInDB(assetID);
	}
	
//	@Test(priority=1,enabled = true)
	public void ValidatenewlycreatedAssetUsingApi() {
		String assetID = createAsset("asset","CreateAsset");
		validateNewAssetusingGetAPI(assetID);
	}
	AssetPage asp=new AssetPage();
	@Test(priority=1,enabled = true)
	public void DeleteAssetInDB() {
//		String assetID = createAsset("asset","CreateAsset");
//		deleteAssetInDB(assetID);
		
		String assetsID=asp.assetCreationDBvalidation("TEST12345303735","SelectAssetDetailsWithName");
		deleteAssetInDataBase(assetsID);}
	
	
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}

}
