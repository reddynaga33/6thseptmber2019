package MulticlientlibsPages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;

import com.microsoft.azure.servicebus.Message;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import framework.ServiceBusClients;
import framework.ServiceBusConnectionConfigurations;
import framework.ServiceBusDestinationsHandlers;
import net.minidev.json.JSONObject;

public class BroadcastEventForAllClientPage extends RestApiUtility{
	List<String> SelectQueryResult = null;
	JsonReader jsonData=new JsonReader();
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	JSONObject APSjsonObject = JsonReader.getJsonObject("AutherizationProviderService");
	DatabaseUtility databaseutility=new DatabaseUtility();
	private static ServiceBusDestinationsHandlers sbDestinationsHandler = null;
	private static ServiceBusClients sbClient = null;
	private static  By ROLE_ICON   	 	                        =By.xpath("//a[@title='Roles']");
	private static  By ROLE_FILTER_ST  				            =By.xpath("//i[@class='om-icon-filter-down ng-trigger ng-trigger-iconRotation']");
	private static  By ROLE_SEARCH_EB  				            =By.xpath("//input[@placeholder='Search']");
	private static  By ROLE_SEARCHFIRSTVALUE_DT  	            =By.xpath("(//span[@class='name'])[1]");
	private static  By ROLE_PRIVILEGE_LIST  	            =By.xpath("(//div[@class='itemsList'])[1]//div");
	
	public String[] adminPrivilegeList= {"Asset Create","Asset Delete","Asset Edit","Asset Read","Client Create","Client Delete","Client Edit",
			"Client Read","Configuration Create","Configuration Edit","Další vlastní právo","Dashboard thresholds config","Device Create",
			"Device Delete","Device Edit","Device Read","Edit Target Path","Fleet Create","Fleet Delete","Fleet Edit","Fleet Read",
			"Role Create","Role Delete","Role Edit","SLA Read Operation","SLA Write Operation","User Create","User Delete","User Edit",
			"User Read","Vehicle Create","Vehicle Delete","Vehicle Edit","Vehicle Read","View Admin Console","View Target Path",
			"Visibility Create","Visibility Delete","Visibility Edit","Visibility Read","Vlastní právo"};
	public String[] operatorPrivilegeList= {"Asset Read","Client Edit","Device Read","Vehicle Delete","Vehicle Read","View Target Path",
			"Visibility Read","Vlastní právo"};
	public String[] fleetPrivilegeList= {"Client Edit","Client Read","Fleet Create","Fleet Delete","Fleet Edit","Fleet Read",
		"User Create","Vehicle Create","Vehicle Delete","Vehicle Edit","Vehicle Read"};
	
	
	
	public void configureServiceBus() {
		try{
			JSONObject jsonObject = JsonReader.getJsonObject("serviceBus");
			ServiceBusConnectionConfigurations serviceBusProperty = new ServiceBusConnectionConfigurations(jsonObject);
			sbDestinationsHandler = new ServiceBusDestinationsHandlers(serviceBusProperty);
			sbClient = new ServiceBusClients(jsonObject.getAsString("connectionString"));

		}catch(Exception e) {
			testFailed("Exception occured while validating data from DB:"+e.getMessage());
		}
	}

	
	public HashMap<String, Integer> configureTopicSubscription(String APSPayload) {
		HashMap<String, Integer> subscriptionMessageCountAfterConfigure = new HashMap<String, Integer>();
		try{
	
			Message message=new Message(JsonReader.getJsonObject(APSPayload).toString());
			sbDestinationsHandler.ensureTopicCreation(APSjsonObject.getAsString("AppRegistration"));
			sbDestinationsHandler.ensureSubscriptionCreation(APSjsonObject.getAsString("AppRegistration"), APSjsonObject.getAsString("APSSubscriptionName"));
			subscriptionMessageCountAfterConfigure.put("AppRegistration",(int)sbDestinationsHandler.getSubscriptionMessageCount(APSjsonObject.getAsString("AppRegistration"), APSjsonObject.getAsString("APSSubscriptionName")));
			info("Message count for SubScriptions  is : "+subscriptionMessageCountAfterConfigure);
			sbClient.sendToTopic(APSjsonObject.getAsString("AppRegistration"), message);
		}catch(Exception e) {
			testFailed("Exception occured while creating the subscription:"+e.getMessage());
		}
		return subscriptionMessageCountAfterConfigure;
	}

	
	public HashMap<String, Integer> DeleteTopicSubscription(HashMap<String, Integer> subscriptionMessageCount) {
		HashMap<String, Integer> subscriptionMessageCountBeforeDelete = new HashMap<String, Integer>();
		try{
			subscriptionMessageCountBeforeDelete.put("AppRegistration",(int)sbDestinationsHandler.getSubscriptionMessageCount(APSjsonObject.getAsString("AppRegistration"), APSjsonObject.getAsString("APSSubscriptionName")));
			sbDestinationsHandler.deleteSubscription(APSjsonObject.getAsString("AppRegistration"), APSjsonObject.getAsString("APSSubscriptionName"));
			serviceBusValidation(subscriptionMessageCount,subscriptionMessageCountBeforeDelete);
		}catch(Exception e) {
			testFailed("Exception occured while deleting  the subscription:"+e.getMessage());
		}
		return subscriptionMessageCountBeforeDelete;
	}


	public void serviceBusValidation(HashMap<String, Integer> subscriptionMessageAfterConfiure ,HashMap<String, Integer> subscriptionMessageBeforeDelete) {

		try{
			
				if(subscriptionMessageAfterConfiure.get("AppRegistration")<subscriptionMessageBeforeDelete.get("AppRegistration") ) {
					testPassed("Message count  before sending is more than  count after receiving the message is  Hence message is received to the subscription");
				}else {
					testFailed("Message count is same");
				}
			
		}catch(Exception e) {
			testFailed("Exception occured while comparing  the subscription message count:"+e.getMessage());
		}
	}
	
	public void validatedefaultAdminRoleInUI(String role,String[] privilegeList) {
		sleep(10000);
		elementClick(ROLE_ICON);
		sleep(10000);
		if(!elementAvailability(ROLE_SEARCH_EB)) {
			elementClick(ROLE_FILTER_ST);
		}
		elementSendKeys(ROLE_SEARCH_EB, role);
		sleep(20000);
		if(privilegeList.length==elementCount(ROLE_PRIVILEGE_LIST)) {
			info("The total privilege list count :"+elementCount(ROLE_PRIVILEGE_LIST)+" and default privilege list count is:"+privilegeList.length+" and Administartor privilage list : : "+elementsGetText(ROLE_PRIVILEGE_LIST));
			for(int privilegecount=0;privilegecount<privilegeList.length;privilegecount++) {
	
			if(elementsGetText(ROLE_PRIVILEGE_LIST).contains(privilegeList[privilegecount].toLowerCase())) {
				testPassed(" The default list contain : "+privilegeList[privilegecount]);
			}
			else {
				testFailed("The default data :"+privilegeList[privilegecount]+"does not contain in the ");
			}}}
		else {
			testFailed("The total privilege list count :"+elementCount(ROLE_PRIVILEGE_LIST)+" and default privilege list count is:"+privilegeList.length);
		}
		
	}
	
public void validateDefaultRoleInUI(){
	try {
	validatedefaultAdminRoleInUI("Administrator",adminPrivilegeList);
	validatedefaultAdminRoleInUI("Operator",operatorPrivilegeList);
	validatedefaultAdminRoleInUI("Fleet Manager",fleetPrivilegeList);
	
	}catch(Exception e) {
		testFailed("An exception occured:"+e.getMessage());}
	}
}
