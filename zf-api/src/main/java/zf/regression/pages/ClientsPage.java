package zf.regression.pages;

import java.util.List;
import java.util.Spliterator;

import org.openqa.selenium.By;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import zf.pages.ZFAddClientPage;
import zf.pages.ZFClientHomePage;

public class ClientsPage extends RestApiUtility {
	private static final String SubClientIDFromDB = null;
	List<String> SelectQueryResult = null;
	JsonReader jsonData=new JsonReader();
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	JSONObject clientPropertyjsonObject = JsonReader.getJsonObject("ClientPropertyTopicName");
	DatabaseUtility databaseutility=new DatabaseUtility();
	ZFAddClientPage addsubclient=new ZFAddClientPage();
	ZFClientHomePage clientHomepage=new ZFClientHomePage();
	


	private static By CLIENTS_ICON			      			=By.xpath("//a[@title='Clients']");
	private static By CLIENTS_CLIENTDETAILEXPANDARROW_DD    =By.xpath("(//div[@class='client-table-row-wrapper']/div[contains(@class,'client-table-row')]/div[contains(@class,'row no-gutter')]/div[contains(@class,'width-4')]/div/span[contains(@class,'arrow')])[2]");
	private static By EDIT_CLIENT_BT						=By.xpath("(//div[@class='fw table-row-additioanl-details open']//span[@class='margin-left-20'][contains(text(),'Edit Client')])");
	private static By CLIENT_FAX_EB							=By.xpath("//input[@id='fax']");
	private static By CLIENT_TELEPHONE_EB					=By.xpath("//input[@id='telephone']");
	private static By CLIENT_WEBSITE_EB						=By.xpath("//input[@id='website']");
	private static By SAVE_EDIT_BT							=By.xpath("//div[@class='tab1-content']//span[contains(text(),'Save Edit')]");
	private static By CLIENTS_EDIT_TOASTER					=By.xpath("//div[text()='Account was updated successfully.']");
	private static By CLIENT								=By.xpath("//span[@class='margin-left-x'][contains(text(),'Openmatics 1 Test')]");
	private static By ClIENT_IMAGE1 						=By.xpath("//div[@class='client-table-row ng-star-inserted padding-level-2 odd-rows']//img");
	private static By ClIENT_IMAGE							=By.xpath("(//span[@class='arrow up'])[2]");
	private static By SUBCLIENTCOUNT                        =By.xpath("(//span[@class='arrow up'])[2]");
	private static By EXPAND_ARROW_BT		                =By.xpath("(//i[@aria-label='Next slide'])[1]");
	
	public String  addSubClient() {
		clientHomepage.navigateToClientPage();
		
		String addSubClient = addsubclient.addSubClient();
		return addSubClient;
	}
	public void VerifyTheNewlyCreatedSubClientIntoApplicationDB(String ClientName) {
		
		try {
			String SelectQuery = jsonObject.getAsString("SelectSubClientDetails");
			SelectQuery = SelectQuery.replaceFirst("tempValue", ClientName);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			if(SelectQueryResult.toString().contains(ClientName)) {
				info("Creaetd SubClient "+ ClientName+" is aviable in DB");
			}
			else {
				testFailed("Created SubClient "+ ClientName+" is not aviable in DB");
			}
		}catch(Exception e) {
			testFailed("Exception occured while validating data from DB:"+e.getMessage());
		}
		
	}
	public String VerifyTheSubClientIntoApplicationDB(String ClientName) {
		String Result=null;
		String[] Split=null;
		String Id=null;
		try {
			String SelectQuery = jsonObject.getAsString("SelectSubClientDetails");
			SelectQuery = SelectQuery.replaceFirst("tempValue", ClientName);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			Result=SelectQueryResult.get(0);
			Split=Result.split(" ");
			Id=Split[0];
			if(SelectQueryResult.toString().contains(ClientName)) {
				info("Creaetd SubClient "+ ClientName+" is available in DB");
			}
			else {
				testFailed("Creaetd SubClient "+ ClientName+" is not available in DB");
			}
		}catch(Exception e) {
			testFailed("Exception occured while validating data from DB:"+e.getMessage());
		}
		return Id;
	}

	public void VerifyTheNewlyCreatedSubClientInformationFromAPI(String SubClientID) {
		try{

			Response SubClientResponse = GetServices("client", SubClientID);
			if(SubClientResponse.jsonPath().getJsonObject("id").equals(SubClientID)) {
				info("The SubClient details response body is "+SubClientResponse.getBody().asString());
				testPassed("SubClient id is from response is :" +SubClientResponse.jsonPath().getJsonObject("id"));
			}
			else {
				testFailed("The SubClient details response body is "+SubClientResponse.getBody().asString());
			}
		}
		catch (Exception e) {
			testFailed("Exception occured while validating sub client detail using get and the error message is :"+e.getMessage());
		}
	}
	
	public void VerifyDeleteSubClientInformationFromAPI(String servicename,String SubClientID) {
		try{

			Response SubClientResponse = DeleteServices(servicename, SubClientID);
			if(SubClientResponse.getStatusCode()==204) {
				testPassed("SubClient id is deleted  :" +SubClientResponse.jsonPath().getJsonObject("id"));
			}
			else {
				testFailed("The SubClient details response body is "+SubClientResponse.getBody().asString());
			}
		}
		catch (Exception e) {
			testFailed("Exception occured while validating sub client detail using get and the error message is :"+e.getMessage());
		}
	}
	public void VerifyUserCanEditTheSubClient() {
		JSONObject clientjsonObject = JsonReader.getJsonObject("EditClientDetails");
		try {
			
			clientHomepage.navigateToClientPage();
			
			waitElementVisibleClick(EDIT_CLIENT_BT, 300);
			elementSendKeyWithActions(CLIENT_FAX_EB, clientjsonObject.getAsString("Fax"));
			elementSendKeys(CLIENT_TELEPHONE_EB, clientjsonObject.getAsString("Telephone"));
			elementSendKeys(CLIENT_WEBSITE_EB, clientjsonObject.getAsString("Website"));
			elementClick(SAVE_EDIT_BT);
			verifyToastermessage(clientjsonObject.getAsString("SuccessMessage"));

		}catch (Exception e) {
			info(e.getMessage());
		}

	}
	public void VerifyUserCanUpdateSubclientCreatedByBackofficeUI() {
		
		JSONObject jsonObject2 = JsonReader.getJsonObject("EditClientDetails");
		try {
			clientHomepage.navigateToClientPage();
//		Need to add code for search sub client 
//			waitElementVisibleClick(CLIENTS_CLIENTDETAILEXPANDARROW_DD, 300);
			waitElementVisibleClick(EDIT_CLIENT_BT, 300);
			elementSendKeys(CLIENT_FAX_EB, jsonObject2.getAsString("Fax"));
			elementClick(SAVE_EDIT_BT);
			verifyToastermessage(jsonObject2.getAsString("SuccessMessage"));
		}catch (Exception e) {
			info(e.getMessage());
		}
	}
	public void verifyToastermessage(String Jsondata) {
		try {
			String toasterText = waitElementVisibleGetTextForToaster(CLIENTS_EDIT_TOASTER,100);
			compareText(jsonData.getJsonData(Jsondata),toasterText);
		} catch (InterruptedException e) {

			info(e.getMessage());
		}
	}
	
	public void updateSubclientInfoInAPI(String servicename,String payload,String SubClientIDFromDB) {
		Response SubClientResponse = null;
		JSONObject createServiceDescriptorJson=null;
		try{
			createServiceDescriptorJson=JsonReader.getJsonObject(payload);	
		
			SubClientResponse=PutServices(servicename,createServiceDescriptorJson, SubClientIDFromDB);
		if(SubClientResponse.getStatusCode()==204) {
			testPassed("SubClient is updated");
		}
		else {
			testFailed("The SubClient details not updated");
		}
	}
	catch (Exception e) {
		testFailed("Exception occured while validating sub client detail using get and the error message is :"+e.getMessage());
	}
	}
}