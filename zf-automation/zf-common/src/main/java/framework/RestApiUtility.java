package framework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.testng.Assert;
import net.minidev.json.JSONObject;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.TestLogger;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import framework.ExtentReport;


public class RestApiUtility extends ElementManager{
	public static JsonReader jsonObj = null;
	static JSONObject jsonObject=null;
	public RestApiUtility (){
		jsonObj = new JsonReader();
		jsonObject = JsonReader.getJsonObject(EnvironmentManager.getEnvironmentFile());

	}
	/*
	 * ****************************************************************************** 
	 * Name : getAssettoken 
	 * Parameters : authorizationURL (service authorization url) ,serviceName(service name)
	 * Purpose : To get the Authorization token of the Asset service
	 * ******************************************************************************
	 */	

	public String getAssettoken(String authorizationURL,String serviceName) {		
		String strToken = null;
		Map<String,String> form = null;
		try {
			form = new HashMap<String,String>();
			TestLogger.appInfo(" Populating form parameters of AssetAuthorization Token ");
			form.put("auth_url", authorizationURL);
			form.put("grant_type", jsonObject.getAsString("GRANT_TYPE"));
			form.put("client_id", jsonObject.getAsString("ASSET_ADD_ID"));
			form.put("client_secret", jsonObject.getAsString("ASSET_ADD_SECRET"));
			form.put("resource", jsonObject.getAsString("ASSET_RESOURCE"));
			form.put("Content-Type", jsonObject.getAsString("AUTHORIZATION_CONTENT_TYPE"));				
			strToken = getToken(form,serviceName);
			ExtentReport.info("Token genereated successfuly "+strToken);
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while populating form parameters of AssetAuthorization Token "+e.getMessage());
		}
		return strToken;

	}

	public String getVehicletoken(String authorizationURL,String serviceName) {
		return getAssettoken(authorizationURL,serviceName);
	}

	public String getChargingStationtoken(String authorizationURL,String serviceName) {
		return getAssettoken(authorizationURL,serviceName);
	}

	/*
	 * ****************************************************************************** 
	 * Name : getCreateServicetoken 
	 * Parameters : authorizationURL (service authorization url) ,serviceName(service name)
	 * Purpose : To get the Authorization token of the CreateService
	 * ******************************************************************************
	 */	

	public String getCreateServicetoken(String authorizationURL,String serviceName) {		
		String strToken = null;
		Map<String,String> form = null;
		try {
			form = new HashMap<String,String>();
			TestLogger.appInfo(" Populating form parameters of CreateService Authorization Token ");
			form.put("auth_url", authorizationURL);
			form.put("grant_type", jsonObject.getAsString("GRANT_TYPE"));
			form.put("client_id", jsonObject.getAsString("INITS_AAD_ID"));
			form.put("client_secret", jsonObject.getAsString("INITS_AAD_SECRET"));
			form.put("resource", jsonObject.getAsString("INITIATION_RESOURCE"));
			form.put("Content-Type", jsonObject.getAsString("AUTHORIZATION_CONTENT_TYPE"));
			strToken = getToken(form,serviceName);	
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while populating form parameters of ClientAuthorization Token "+e.getMessage());
		}
		return strToken;
	}

	/*
	 * ****************************************************************************** 
	 * Name : getClientServicetoken 
	 * Parameters : authorizationURL (service authorization url) ,serviceName(service name)
	 * Purpose : To get the Authorization token of the ClientService
	 * ******************************************************************************
	 */
	public String getClientServicetoken(String authorizationURL,String serviceName) {		
		String strToken = null;
		Map<String,String> form = null;
		try {
			form = new HashMap<String,String>();
			TestLogger.appInfo(" Populating form parameters of ClientService Authorization Token ");
			form.put("auth_url", authorizationURL);
			form.put("grant_type", jsonObject.getAsString("GRANT_TYPE"));
			form.put("client_id", jsonObject.getAsString("SMS_ADD_ID"));
			form.put("client_secret", jsonObject.getAsString("SMS_ADD_SECRET"));
			form.put("resource", jsonObject.getAsString("SMS_ADD_ID"));
			form.put("Content-Type", jsonObject.getAsString("AUTHORIZATION_CONTENT_TYPE"));
			strToken = getToken(form,serviceName);	
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while populating form parameters of ClientAuthorization Token "+e.getMessage());
		}
		return strToken;
	}

	public String getAPStoken(String authorizationURL,String serviceName) {		
		String strToken = null;
		Map<String,String> form = null;
		try {
			form = new HashMap<String,String>();
			TestLogger.appInfo(" Populating form parameters of UserAuthorization Token ");
			form.put("auth_url", authorizationURL);
			form.put("grant_type", jsonObject.getAsString("GRANT_TYPE"));
			form.put("client_id", jsonObject.getAsString("APS_AAD_ID"));
			form.put("client_secret", jsonObject.getAsString("APS_AAD_SECRET"));
			form.put("resource", jsonObject.getAsString("APS_AAD_ID"));
			form.put("Content-Type", jsonObject.getAsString("AUTHORIZATION_CONTENT_TYPE"));
			strToken = getToken(form,serviceName);	
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while populating form parameters of UserAuthorization Token "+e.getMessage());
		}
		return strToken;
	}
	public String getFleetServicetoken(String authorizationURL,String serviceName) {		
		String strToken = null;
		Map<String,String> form = null;
		try {
			form = new HashMap<String,String>();
			TestLogger.appInfo(" Populating form parameters of ClientService Authorization Token ");
			form.put("auth_url", authorizationURL);
			form.put("grant_type", jsonObject.getAsString("GRANT_TYPE"));
			form.put("client_id", jsonObject.getAsString("FLEET_ADD_ID"));
			form.put("client_secret", jsonObject.getAsString("FLEET_ADD_SECRET"));
			form.put("resource", jsonObject.getAsString("FLEET_RESOURCE"));
			form.put("Content-Type", jsonObject.getAsString("AUTHORIZATION_CONTENT_TYPE"));
			strToken = getToken(form,serviceName);	
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while populating form parameters of ClientAuthorization Token "+e.getMessage());
		}
		return strToken;
	}

	public String getMultiClientServicetoken(String authorizationURL,String serviceName) {		
		String strToken = null;
		Map<String,String> form = null;
		try {
			form = new HashMap<String,String>();
			TestLogger.appInfo(" Populating form parameters of ClientService Authorization Token ");
			form.put("auth_url", authorizationURL);
			form.put("grant_type", jsonObject.getAsString("GRANT_TYPE"));
			form.put("client_id", jsonObject.getAsString("SAMPLE_ADD_ID"));
			form.put("client_secret", jsonObject.getAsString("SAMPLE_ADD_SECRET"));
			form.put("resource", jsonObject.getAsString("SAMPLE_ADD_ID"));
			form.put("Content-Type", jsonObject.getAsString("AUTHORIZATION_CONTENT_TYPE"));
			strToken = getToken(form,serviceName);	
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while populating form parameters of ClientAuthorization Token "+e.getMessage());
		}
		return strToken;
	}

	public String getPropulsionTypetoken(String authorizationURL,String serviceName) {		
		String strToken = null;
		Map<String,String> form = null;
		try {
			form = new HashMap<String,String>();
			TestLogger.appInfo(" Populating form parameters of ClientService Authorization Token ");
			form.put("auth_url", authorizationURL);
			form.put("grant_type", jsonObject.getAsString("GRANT_TYPE"));
			form.put("resource", jsonObject.getAsString("ASSET_RESOURCE"));
			form.put("client_id", jsonObject.getAsString("ASSET_ADD_ID"));
			form.put("client_secret", jsonObject.getAsString("ASSET_ADD_SECRET"));
			form.put("Content-Type", jsonObject.getAsString("AUTHORIZATION_CONTENT_TYPE"));
			strToken = getToken(form,serviceName);	
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while populating form parameters of ClientAuthorization Token "+e.getMessage());
		}
		return strToken;
	}


	/*
	 * ****************************************************************************** 
	 * Name : getDevicetoken 
	 * Parameters : authorizationURL (service authorization url) ,serviceName(service name)
	 * Purpose : To get the Authorization token of the Device service
	 * ******************************************************************************
	 */

	public String getDevicetoken(String authorizationURL,String serviceName) {
		String strToken = null;
		Map<String,String> form = null;
		try {
			form = new HashMap<String,String>();
			TestLogger.appInfo(" Populating form parameters of DeviceAuthorization Token ");
			form.put("auth_url", authorizationURL);
			form.put("grant_type", jsonObject.getAsString("GRANT_TYPE"));
			form.put("client_id", jsonObject.getAsString("DEVICE_ADD_ID"));
			form.put("client_secret", jsonObject.getAsString("DEVICE_ADD_SECRET"));
			form.put("resource", jsonObject.getAsString("DEVICE_RESOURCE"));
			form.put("Content-Type", jsonObject.getAsString("AUTHORIZATION_CONTENT_TYPE"));	
			strToken = getToken(form,serviceName);	
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while populating form parameters of DeviceAuthorization Token "+e.getMessage());
		}
		return strToken;		
	}

	/*
	 * ****************************************************************************** 
	 * Name : getClienttoken 
	 * Parameters : authorizationURL (service authorization url) ,serviceName(service name)
	 * Purpose : To get the Authorization token of the Client service
	 * ******************************************************************************
	 */

	public String getClienttoken(String authorizationURL,String serviceName) {	
		String strToken = null;
		Map<String,String> form = null;
		try {
			form = new HashMap<String,String>();
			TestLogger.appInfo(" Populating form parameters of ClientAuthorization Token ");
			form.put("auth_url", authorizationURL);
			form.put("grant_type", jsonObject.getAsString("GRANT_TYPE"));
			form.put("client_id", jsonObject.getAsString("CLIENT_AAD_ID"));
			form.put("client_secret", jsonObject.getAsString("CLIENT_AAD_SERCET"));
			form.put("resource", jsonObject.getAsString("CLIENT_RESOURCE"));
			form.put("Content-Type", jsonObject.getAsString("AUTHORIZATION_CONTENT_TYPE"));
			strToken = getToken(form,serviceName);	
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while populating form parameters of ClientAuthorization Token "+e.getMessage());
		}
		return strToken;
	}

	/*
	 * ****************************************************************************** 
	 * Name : getUsertoken 
	 * Parameters : authorizationURL (service authorization url) ,serviceName(service name)
	 * Purpose : To get the Authorization token of the User service
	 * ******************************************************************************
	 */	
	public String getUsertoken(String authorizationURL,String serviceName) {		
		String strToken = null;
		Map<String,String> form = null;
		try {
			form = new HashMap<String,String>();
			TestLogger.appInfo(" Populating form parameters of UserAuthorization Token ");
			form.put("auth_url", authorizationURL);
			form.put("grant_type", jsonObject.getAsString("GRANT_TYPE"));
			form.put("client_id", jsonObject.getAsString("CLIENT_AAD_ID"));
			form.put("client_secret", jsonObject.getAsString("CLIENT_AAD_SERCET"));
			form.put("resource", jsonObject.getAsString("CLIENT_RESOURCE"));
			form.put("Content-Type", jsonObject.getAsString("AUTHORIZATION_CONTENT_TYPE"));
			strToken = getToken(form,serviceName);	
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while populating form parameters of UserAuthorization Token "+e.getMessage());
		}
		return strToken;
	}

	/*
	 * ****************************************************************************** 
	 * Name : setFormParameters 
	 * Parameters : httpRequest (RequestSpecification instance) ,form(form key value pairs)
	 * Purpose : Setting the form parameters required for token generation
	 * ******************************************************************************
	 */
	static public RequestSpecification setFormParameters(RequestSpecification httpRequest,Map<String,String> form) {
		try {
			TestLogger.appInfo(" Populating form parameters of form ");
			httpRequest.formParam("grant_type",form.get("grant_type"));
			httpRequest.formParam("resource",form.get("resource"));
			httpRequest.formParam("client_id",form.get("client_id"));
			httpRequest.formParam("client_secret",form.get("client_secret"));
			httpRequest.header("Content-Type", form.get("Content-Type"));
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while populating form parameters of form parameters of Token "+e.getMessage());
		}
		return httpRequest;
	}

	/*
	 * ****************************************************************************** 
	 * Name : getToken 
	 * Parameters : form (form key, value pairs ) ,serviceName(service name)
	 * Purpose : Creating the token of specified service by using form key value pairs
	 * ******************************************************************************
	 */

	static public String getToken(Map<String,String> form,String servicename) {
		Response response = null;
		String access_token = null;
		RequestSpecification httpRequest = null;
		try {
			RestAssured.baseURI = form.get("auth_url");
			httpRequest = RestAssured.given();
			setFormParameters(httpRequest,form);

			response = httpRequest.request(Method.POST);	
			info(" The response of "+servicename+" getToken is : "+response.body().asString());

			access_token = response.jsonPath().getJsonObject("access_token");	
			info(" The generated access token for "+servicename+" is : "+access_token);
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating access token for service "+servicename+" : "+e.getMessage());
		}
		return access_token;
	}	

	/*
	 * ****************************************************************************** 
	 * Name : getAuthorizationToken 
	 * Parameters : serviceName(service name)
	 * Purpose : Creating the Authorization token of specified service 
	 * ******************************************************************************
	 */


	public String getAuthorizationURL() {
		String base_url = null;
		String aad_tenant = null;
		String tauri_authorization_url = null;
		base_url = jsonObject.getAsString("LOGIN_MICROSOFT");
		aad_tenant = jsonObject.getAsString("AAD_TENANT");
		tauri_authorization_url = "https://"+base_url+"/"+aad_tenant+"/oauth2/token";
		return tauri_authorization_url;
	}

	public String getAuthorizationToken(String serviceName) {
		String base_url = null;
		String aad_tenant = null;
		String tauri_authorization_url = null;
		String token = null; 
		base_url = jsonObject.getAsString("LOGIN_MICROSOFT");
		aad_tenant = jsonObject.getAsString("AAD_TENANT");
		tauri_authorization_url = "https://"+base_url+"/"+aad_tenant+"/oauth2/token";
		try {
			switch(serviceName) {
			case "asset":			
				token = getAssettoken(tauri_authorization_url,serviceName);
				break;
			case "vehicle":			
				token = getVehicletoken(tauri_authorization_url,serviceName);
				break;
			case "chargingstation":			
				token = getChargingStationtoken(tauri_authorization_url,serviceName);
				break;
			case "client":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "device":
				token = getDevicetoken(tauri_authorization_url,serviceName);
				break;
			case "user":
				token = getUsertoken(tauri_authorization_url,serviceName);
				break;
			case "createservice":
				token = getCreateServicetoken(tauri_authorization_url,serviceName);
				break;
			case "clientservice":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "createversion":
				token = getCreateServicetoken(tauri_authorization_url,serviceName);
				break;
			case "servicedescriptorservice":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "propulsionType":
				token = getPropulsionTypetoken(tauri_authorization_url,serviceName);
				break;
			case "ServiceDescriptorWithAppId":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "sagastatusfail":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "assignServiceToClientWithoutAppid":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "getPropulsionTypeDetails":
				token = getPropulsionTypetoken(tauri_authorization_url,serviceName);
				break;
			case "getSpecificPropulsionTypeDetails":
				token = getPropulsionTypetoken(tauri_authorization_url,serviceName);
				break;
			case "propulsion":
				token = getPropulsionTypetoken(tauri_authorization_url,serviceName);
				break;
			case "VehicleType":
				token = getVehicletoken(tauri_authorization_url,serviceName);
				break;	
			case "vehicleType":
				token = getVehicletoken(tauri_authorization_url,serviceName);
				break;
			case "vehicleTypeOne":
				token = getVehicletoken(tauri_authorization_url,serviceName);
				break;	
			case "serviceCatalog":
				token = getServiceCatalogtoken(tauri_authorization_url,serviceName);
				break;
			case "sandbox":
				token = getServiceCatalogtoken(tauri_authorization_url,serviceName);
				break;
			case "sagastatussuccess":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "getAllRegistration":
				token = getServiceCatalogtoken(tauri_authorization_url,serviceName);
				break;
			case "SagaEntityToValidateTheCUSConfig":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "ConfigPropertyForCUS":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetConfigPropertyForCUS":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "updatedClientProperty":			
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;	
			case "clientafterdeletingonetag":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;	
			case "singleTagMultipleProperties":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;	
			case "CreateUpdateClientProperty":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetSagaEntity":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetCUSPropertyDetails":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;

			case "GetClientProperty":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetConfigHierarchicalClientProperty":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "DeletePropertyClientConfigProperty":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetCUSProperty":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetConfigHierarchicalCUSProperty":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "DeletePropertyCUSConfigProperty":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetWrongClientIDPropertyDetails":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CreateUpdateCUSProperty":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetWrongSSIDPropertyDetails":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GlobalInfoClients":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "ClientContext":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "ClientContextExtractorHierarchy":
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "ClientContextExtractorUser":
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "PrometheusMetrics":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "HealthCheck":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "proxyGreeting":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "getGreetingLogForClient":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "ClientGreetingsClient1":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "ClientGreetingsClient2":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GreetingslogsClient1":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GreetingslogsClient2":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "destinationUsingRestAPI":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CreateServiceDescriptor":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetserviceDescriptor":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetClientDetails":			
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetServiceDetailsOfAClient":			
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetCUSDetailsOfCreatedCUSUser":			
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetSDIDOfTheUser":			
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "assignNewServicesToClient":			
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CreateFleet":			
				token = getFleetServicetoken(tauri_authorization_url,serviceName);
				break;
			case "UpdateFleet":			
				token = getFleetServicetoken(tauri_authorization_url,serviceName);
				break;
			case "Fleet":			
				token = getFleetServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GlobalGreetings":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
			case "SendGreetingsToClient":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "ClientGreetingWithWrongClientID":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "SendGreetingsToClientAndGlobalDB":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "SendGreetingsNoClient":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "SendGreetingsNoClientGlobalDB":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetGreetingsToClient":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetGreetingsNoClient":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GreetingFromRollback":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetCurrentClientIDWithNoClientID":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetClientDetailsWithoutClientID":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetClientContextWithWrongClientID":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetCurrentContextIDWithNoClientID":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetCurrentClientIDWithWrongClientID":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CurrentClientIDWithClientId":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "RollbackGlobalGreetings":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "proxyCurrentContext":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "proxyGreetingNoClientId":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CreateGreetingLogsWithWrongClientID":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetGreetingWithWrongClientID":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "SendGreetingWithWrongClientID":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetMessageWithWrongClientIDCorrectGreetingID":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CurrentClientContextWithCliendId":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetServiceDescriptor":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetWrongClientIdDetails":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CurrentClientContextWithNoClientID":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CurrentClientContextWithWrongCliendId":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CurrentRequestContextNoclientId":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "ServicestartedMC":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CreateGreeting":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "DeleteGreetingWithCorrectClientWrongGreeting":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CreateServiceDescriptorWithNoUUID":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "assignSampleService":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "deleteSampleService":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "assignSampleServiceWrongClientID":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "EditClientWithAPI":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "AssignClientServiceSD":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "UnAssignSDFromClient":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetServiceDetailsOfWrongClient":			
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetIClientDetails":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "EditIClientDetails":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "CreateIClientDetails":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "GetClientsDetails":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "GetClientExtendedDetails":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "GetApplicationExtendedDetails":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "GetClientDetailsOfApplication":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "GetOneClientDetails":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "AssignApplication":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "GetClientApplication":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "MulitClientSampleServiceSubscription":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "UserGroup":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "GetUserGroupDetails":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "CreateUserGroup":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;	
			case "DeleteUserGroup":			
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "EditUserGroup":
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "UserClient":
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "CreateUser":
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "DeleteUser":
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "UpdateUser":
				token = getClienttoken(tauri_authorization_url,serviceName);
				break;
			case "getDestination":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;	
			case "UserGetTopicDestination":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;	
			case "getEventHubDestination":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;	
			case "getDestinationWithEnumValues":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;	
			case "UserClientDetails":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;	
			case "getUserDeleteDestination":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetQueueDestinations":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetDestinations":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CreateServDescWithCorrectUUID":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetServDescWithCorrectUUID":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetValidateServDescWithCorrectUUID":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetAllDestinationsFromStorage":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GreetingLogType":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GreetingLogwithNoClientId":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CreateGreetingLogWithClientId":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetGreetingLog":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GreetingLogIdWithNoClientId":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "UserMesgToClientwithWrongClientIdGreet":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "AssignAssetToFleet":			
				token = getFleetServicetoken(tauri_authorization_url,serviceName);
				break;
			case "UpdateFleetDetails":			
				token = getFleetServicetoken(tauri_authorization_url,serviceName);
				break;
			case "AssociatedAssetForFleet":			
				token = getFleetServicetoken(tauri_authorization_url,serviceName);
				break;
			case "VerifyAssignedAsset":			
				token = getFleetServicetoken(tauri_authorization_url,serviceName);
				break;
			case "FleetService":
				token = getClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CreateNewRole":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "GetUsersWithRoleCodeUsingNewAPI":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "CurrentClientContext":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CurrentContextClient":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "CurrentRequestContextClient":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "UpdateGreetingLogWithClientId":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "DeleteSpecificGreetingWithClientId":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetGreetingLogWithClientId":
				token = getMultiClientServicetoken(tauri_authorization_url,serviceName);
				break;
			case "GetAppsUsingNewAPI":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "GetSpecificAppDetailsUsingNewAPI":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "GetSpecificAppDetailsWithWrongAppID":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "GetSpecificAppDetailsWithAppID":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "InvalidAppIdToApplicationApi":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "NoAppIdToApplicationApiDetails":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "AppIdToApplicationApi":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "InvalidIdExtApplicationApi":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "GetClientGroups":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "GetClientSpecificGroup":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "GetClientInvalidGroup":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "GetClientGroupIdExt":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "GetClientGroupRole":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "GetClientRoles":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "GetClientRoleIdDetails":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			case "getAvailablePrivilage":
				token = getAPStoken(tauri_authorization_url,serviceName);
				break;
			}
			info("For "+serviceName+" Authorization Token is generated");
		}catch(Exception e) {
			info("An exception has occured while generating Authorization access token for service "+serviceName+" : "+e.getMessage());
		}
		return token;
	}

	/*
	 * ****************************************************************************** 
	 * Name : Get 
	 * Parameters : url(the complete url of the resource), token(the authorization token generated)
	 * Purpose : Getting the Jsonpath of the Get Requests Response 
	 * ******************************************************************************
	 */
	static public Response Get(String url,String token,String servicename,String configureValue) {
		RequestSpecification httpRequest = null;
		Response getResponse = null;
		try {			


			switch(servicename) {

			case "ConfigPropertyForCUS":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "CreateUpdateClientProperty":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "GetConfigHierarchicalCUSProperty":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given().queryParam("serviceDescriptorName", configureValue);
				break;
			case "GetWrongClientIDPropertyDetails":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given().queryParam("reconfigure", configureValue);
				break;
			case "GetCUSPropertyDetails":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given().queryParam("serviceDescriptorName", configureValue);
				break;
			case "GetCUSProperty":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given().queryParam("serviceDescriptorName", configureValue);
				break;
			case "getTopic":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given().queryParam("type", configureValue);
				break;
			case "GetClientExtendedDetails":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given().queryParam("extendedInfo", configureValue);
				break;
			case "GetApplicationExtendedDetails":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given().queryParam("extendedInfo", configureValue);
				break;
			case "UserGroup":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given().queryParam("groupId",configureValue);
				break;
			case "UserGetTopicDestination":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given().queryParam("type", configureValue);
				break;
			case "getEventHubDestination":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given().queryParam("type", configureValue);
				break;
			case "getDestinationWithEnumValues":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given().queryParam("type", configureValue);
				break;
			case "VerifyAssignedAsset":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given().queryParam("showAssetsIds", configureValue);
				break;
			}	
			httpRequest.header("authorization", "Bearer "+token);
			getResponse = httpRequest.request(Method.GET);
			long seconds= getResponse.getTime();
			info("The response time is : "+seconds+" Milli Seconds");
			info("The response status code for Get Request is : "+getResponse.getStatusCode());
		}catch(Exception e) {
			info("An exception has occured while generating JSONPath for Get request : "+e.getMessage());
		}
		return getResponse;
	}
	
	static public Response Get(String url,String token,String servicename,String configureValue1,String configureValue2) {
		RequestSpecification httpRequest = null;
		Response getResponse = null;
		try {			


			switch(servicename) {

		
			case "GetUserGroupDetails":
				RestAssured.baseURI = url;
				HashMap<String, String> parameters=new HashMap<String, String>();
				parameters.put("clientInfo", configureValue1);
				parameters.put("userInfo", configureValue2);
				httpRequest = RestAssured.given();
				httpRequest.params(parameters);
				break;
		
			
			}	
			httpRequest.header("authorization", "Bearer "+token);
			getResponse = httpRequest.request(Method.GET);
			long seconds= getResponse.getTime();
			info("The response time is : "+seconds+" Milli Seconds");
			info("The response status code for Get Request is : "+getResponse.getStatusCode());
		}catch(Exception e) {
			info("An exception has occured while generating JSONPath for Get request : "+e.getMessage());
		}
		return getResponse;
	}

	/*
	 * ****************************************************************************** 
	 * Name : Get 
	 * Parameters : url(the complete url of the resource), token(the authorization token generated)
	 * Purpose : Getting the Jsonpath of the Get Requests Response 
	 * ******************************************************************************
	 */

	static public Response Get(String url,String token) {
		RequestSpecification httpRequest = null;
		Response getResponse = null;
		try {
			RestAssured.baseURI = url;
			httpRequest = RestAssured.given();
			httpRequest.header("authorization", "Bearer "+token);
			getResponse = httpRequest.request(Method.GET);
			long seconds= getResponse.getTime();
			//			long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
			info("The response time is : "+seconds+" Milli Seconds");
			info("The response status code for Get Request is : "+getResponse.getStatusCode());
		}catch(Exception e) {
			info("An exception has occured while generating JSONPath for Get request : "+e.getMessage());
		}
		return getResponse;
	}

	/*
	 * ****************************************************************************** 
	 * Name : CreateAsset 
	 * Parameters : payloadTemplate(The Json structure of the asset payload),payLoadValues(Data Values for the payloadTemplate)
	 * Purpose : Creating the Payload for Asset Create Post request
	 * ******************************************************************************
	 */	

	@SuppressWarnings({ "static-access", "unchecked" })
	public JSONObject CreateAsset(String payloadTemplate,String payLoadValues)	{
		JSONObject CreateAssetTemplate = null;
		JSONObject CreateAssetData = null;
		List<String> temp = null;
		String updatedListVal = null;
		try {
			//JsonReader.getJsonObject("Tc01addUser");
			CreateAssetTemplate = JsonReader.getJsonObject(payloadTemplate);		
			CreateAssetData = JsonReader.getJsonObject(payLoadValues);		
			CreateAssetTemplate.put("name",CreateAssetData.get("name").toString() + getRandomNumber());
			CreateAssetTemplate.put("description",CreateAssetData.get("description").toString() +getRandomNumber());		
			temp = (List<String>) CreateAssetTemplate.get("clientIdList");
			updatedListVal = (String) CreateAssetData.get("clientIdList");

			temp.set(0, updatedListVal);
			CreateAssetTemplate.put("clientIdList",temp );	

			ExtentReport.info("The Generated Payload for Post Asset Create request is : "+CreateAssetTemplate.toJSONString());

		}catch(Exception e) {
			ExtentReport.info("An exception has occured while generating Payload for Post Asset Create request : "+e.getMessage());
		}
		return CreateAssetTemplate;
	}


	/*
	 * ****************************************************************************** 
	 * Name : CreateUser 
	 * Parameters : payloadTemplate(The Json structure of the User payload),payLoadValues(Data Values for the payloadTemplate)
	 * Purpose : Creating the Payload for User Create Post request
	 * ******************************************************************************
	 */


	public JSONObject CreateUser(String payloadTemplate,String payLoadValues)	{

		JSONObject CreateUserTemplate = null;
		JSONObject CreateUserData = null;
		try {
			CreateUserTemplate = JsonReader.getJsonObject(payloadTemplate);
			CreateUserData = JsonReader.getJsonObject(payLoadValues);	
			CreateUserTemplate.put("displayName",CreateUserData.get("displayName").toString() +getRandomNumber());
			info("The Generated Payload for Post User Create request is : "+CreateUserTemplate.toJSONString());
		}catch(Exception e) {
			info("An exception has occured while generating Payload for Post User Create request : "+e.getMessage());
		}
		return CreateUserTemplate;
	}	


	/*
	 * ****************************************************************************** 
	 * Name : CreateDevice 
	 * Parameters : payloadTemplate(The Json structure of the device payload),payLoadValues(Data Values for the payloadTemplate)
	 * Purpose : Creating the Payload for User Create Post request
	 * ******************************************************************************
	 */


	public JSONObject CreateDevice(String payloadTemplate,String payloadValues)	{

		JSONObject CreateDeviceTemplate = null;
		JSONObject CreateDeviceData = null;
		try {
			CreateDeviceTemplate = JsonReader.getJsonObject(payloadTemplate);
			CreateDeviceData = JsonReader.getJsonObject(payloadTemplate);	
			CreateDeviceTemplate.put("assetId",payloadValues);
			CreateDeviceTemplate.put("id",CreateDeviceData.get("id").toString() + getRandomNumber());
			ExtentReport.info("The Generated Payload for Post User Create request is : "+CreateDeviceTemplate.toJSONString());
		}catch(Exception e) {
			ExtentReport.info("An exception has occured while generating Payload for Post User Create request : "+e.getMessage());
		}
		return CreateDeviceTemplate;
	}



	/*
	 * ****************************************************************************** 
	 * Name : CreateService 
	 * Parameters : payloadTemplate(The Json structure of the CreateService payload),payLoadValues(Data Values for the payloadTemplate)
	 * Purpose : Creating the Payload for CreateService Post request
	 * ******************************************************************************
	 */
	public JSONObject CreateService(String payloadTemplate,String payloadValues)	{

		JSONObject CreateServiceTemplate = null;
		JSONObject CreateServiceData = null;
		try {
			CreateServiceTemplate = JsonReader.getJsonObject(payloadTemplate);
			CreateServiceData = JsonReader.getJsonObject(payloadValues);
			CreateServiceTemplate.put("artifactId",CreateServiceData.get("artifactId").toString() + getRandomNumber());
			ExtentReport.info("The Generated Payload for Post CreateService request is : "+CreateServiceTemplate.toJSONString());
		}catch(Exception e) {
			ExtentReport.info("An exception has occured while generating Payload for Post CreateService request : "+e.getMessage());
		}
		return CreateServiceTemplate;
	}


	/*
	 * ****************************************************************************** 
	 * Author : Venkata
	 * Name : Post 
	 * Parameters : url(The complete URL of the service),Token(generated Authorization Token)
	 * Purpose : generating a post request using generated token
	 * ******************************************************************************
	 */
	public Response Post(String url,String token) {

		RequestSpecification httpRequest = null;
		Response getResponse = null;
		Response postresponse = null;

		try {
			RestAssured.baseURI = url;
			httpRequest = RestAssured.given();
			//*****************************************************************************
			httpRequest.header("authorization", "Bearer "+token);
			httpRequest.header("Content-Type", "application/json");		
			getResponse = httpRequest.request(Method.POST);
			postresponse = getResponse;
			long seconds= getResponse.getTime();
			//			long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
			info("The response time is : "+seconds+" Milli Seconds");
			info("The response status code for post request is : "+postresponse.getStatusCode());
		}catch(Exception e) {
			info("An exception has occured while generating Post request is : "+e.getMessage());
		}
		return postresponse;
	}

	/*
	 * ****************************************************************************** 
	 * Name : Post 
	 * Parameters : url(The complete URL of the service),Token(generated Authorization Token),ServiceName(name of the service),payloadTemplate(Structure of the json payload),payloadValues(values of the JsonObject keys)
	 * Purpose : generating a post request for specified service using generated token
	 * ******************************************************************************
	 */
	public Response Post(String url,String token,String servicename,String payloadTemplate, String payloadValues) {

		RequestSpecification httpRequest = null;
		JSONObject JsonService = null;
		Response getResponse = null;
		Response postresponse = null;

		try {
			RestAssured.baseURI = url;
			httpRequest = RestAssured.given();
			JsonService = new JSONObject();
			//*****************************************************************************
			switch(servicename) {
			case "asset":				
				JsonService = CreateAsset(payloadTemplate,payloadValues);
				break;
			case "user":
				JsonService = CreateUser(payloadTemplate,payloadValues);
				break;
			case "createservice":				
				JsonService = CreateService(payloadTemplate,payloadValues);
				break;
			case "device":
				JsonService = CreateDevice(payloadTemplate,payloadValues);
				break;	

			}		
			//*****************************************************************************
			httpRequest.header("authorization", "Bearer "+token);
			httpRequest.header("Content-Type", "application/json");		
			httpRequest.body(JsonService.toJSONString());	
			getResponse = httpRequest.request(Method.POST);
			//postresponse = getResponse.body().asString();	
			long seconds= getResponse.getTime();
			//			long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
			info("The response time is : "+seconds+" Milli Seconds");
			postresponse = getResponse;
			info("The response status code for post request for th service "+ servicename  +" : "+postresponse.getStatusCode());
		}catch(Exception e) {
			info("An exception has occured while generating Post request for Service "+servicename+" : "+e.getMessage());
		}
		return postresponse;


	}

	/*
	 * ****************************************************************************** 
	 * Name : Post 
	 * Parameters : url(The complete URL of the service),Token(generated Authorization Token),ServiceName(name of the service),payloadValues(Key,value pairs of payload)
	 * Purpose : generating a post request for specified service 
	 * ******************************************************************************
	 */
	static public Response Post(String url,String token,String servicename, JSONObject payloadValues) {

		RequestSpecification httpRequest = null;
		JSONObject JsonService = null;
		Response getResponse = null;

		try {
			RestAssured.baseURI = url;
			httpRequest = RestAssured.given();
			JsonService = new JSONObject();
			//*****************************************************************************
			switch(servicename) {

			case "device":
				JsonService = (payloadValues);
				break;	
			case "user":
				JsonService = (payloadValues);
				break;	
			case "createservice":
				JsonService = (payloadValues);
				break;
			case "createversion":
				JsonService = (payloadValues);
				break;	
			case "asset":
				JsonService = (payloadValues);
				break;	
			case "vehicle":
				JsonService = (payloadValues);
				break;	

			case "assignSampleService":
				JsonService = (payloadValues);
				break;
			case "chargingstation":
				JsonService = (payloadValues);
				break;
			case "registration":
				JsonService = (payloadValues);
				break;	
			case "serviceCatalog":
				JsonService = (payloadValues);
				break;	
			case "sandbox":
				JsonService = (payloadValues);
				break;	
			case "VehicleType":
				JsonService = (payloadValues);
				break;
			case "propulsion":
				JsonService = (payloadValues);
				break;
			case "proxyGreeting":
				JsonService = (payloadValues);
				break;
			case "ClientGreetingsClient1":
				JsonService = (payloadValues);
				break;
			case "ClientGreetingsClient2":
				JsonService = (payloadValues);
				break;
			case "CreateFleet":
				JsonService = (payloadValues);
				break;
			case "GlobalGreetings":
				JsonService = (payloadValues);
			case "ClientGreetingsClient":
				JsonService = (payloadValues);
				break;
			case "WrongClientGreetingsClient":
				JsonService = (payloadValues);
				break;
			case "ClientGreetingsForGlobalDB":
				JsonService = (payloadValues);
				break;
			case "SendGreetingsToClient":
				JsonService = (payloadValues);
				break;
			case "SendGreetingsToClientAndGlobalDB":
				JsonService = (payloadValues);
				break;
			case "CreateGreetingLogsWithWrongClientID":
				JsonService = (payloadValues);
				break;
			case "CreateServiceDescriptor":
				JsonService = (payloadValues);
				break;
			case "CreateGreeting":
				JsonService = (payloadValues);
				break;
			case "CreateIClientDetails":
				JsonService = (payloadValues);
				break;
			case "CreateUserGroup":
				JsonService = (payloadValues);
				break;
			case "CreateUser":
				JsonService = (payloadValues);
				break;
			case "GreetingLogType":
				JsonService = (payloadValues);
				break;
			case "client":
				JsonService = (payloadValues);
				break;
			case "CreateNewRole":
				JsonService = (payloadValues);
				break;
			case "RollbackGlobalGreetings":
				JsonService = (payloadValues);
				break;
			case "CreateGreetingLogWithClientId":
				JsonService = (payloadValues);
				break;
			}		
			//*****************************************************************************
			httpRequest.header("authorization", "Bearer "+token);
			httpRequest.header("Content-Type", "application/json");		
			httpRequest.body(JsonService.toJSONString());	

			info("Payload used to create "+ JsonService.toJSONString());
			getResponse = httpRequest.request(Method.POST);
			long seconds= getResponse.getTime();
			//			long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
			info("The response time is : "+seconds+" Milli Seconds");
			info("The response status code for post request for th service "+ servicename  +" : "+getResponse.getStatusCode());
		}catch(Exception e) {
			Assert.fail("An exception has occured while generating Post request for Service "+servicename+" : "+e.getMessage());
		}
		return getResponse;


	}


	/*
	 * ****************************************************************************** 
	 * Name : Put 
	 * Parameters : url(The complete URL of the service),Token(generated Authorization Token),ServiceName(name of the service),payload(payload for the request)
	 * Purpose : generating a Put request for specified service using generated token
	 * ******************************************************************************
	 */
	static public Response Put(String url,String token,String servicename,String payload) {

		RequestSpecification httpRequest = null;
		Response getResponse = null;
		Response putresponse = null;

		try {			

			//*****************************************************************************
			switch(servicename) {
			case "clientservice":
				RestAssured.baseURI = url+payload;
				httpRequest = RestAssured.given();
				break;
			case "ServiceDescriptorWithAppId":
				RestAssured.baseURI = url+payload;
				httpRequest = RestAssured.given();
				break;
			case "assignServiceToClientWithoutAppid":
				RestAssured.baseURI = url+payload;
				httpRequest = RestAssured.given();
				break;
			case "assignNewServicesToClient":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "SendGreetingWithWrongClientID":
				RestAssured.baseURI = url+payload;
				httpRequest = RestAssured.given();
				break;
			case "assignSampleService":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "assignSampleServiceWrongClientID":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			}		
			//*****************************************************************************
			httpRequest.header("authorization", "Bearer "+token);

			getResponse = httpRequest.request(Method.PUT);
			//putresponse = getResponse.jsonPath();
			putresponse = getResponse;
			long seconds = getResponse.getTime();
			//			long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
			info("The response time is : "+seconds+" Seconds");
			info("The response status code for put request for the service "+ servicename  +" : "+putresponse.getStatusCode());
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating Put request for Service "+servicename+" : "+e.getMessage());
		}
		return putresponse;

	}
	static public Response Put(String url,String token,String servicename) {

		RequestSpecification httpRequest = null;
		Response getResponse = null;
		Response putresponse = null;

		try {			

			//*****************************************************************************
			switch(servicename) {

			case "CreateServiceDescriptorWithNoUUID":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "AssignClientServiceSD":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "FleetService":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;

			}		

			//*****************************************************************************
			httpRequest.header("authorization", "Bearer "+token);

			getResponse = httpRequest.request(Method.PUT);
			//putresponse = getResponse.jsonPath();
			putresponse = getResponse;
			long seconds= getResponse.getTime();
			//			long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
			info("The response time is : "+seconds+" Milli Seconds");
			info("The response status code for put request for the service "+ servicename  +" : "+putresponse.getStatusCode());
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating Put request for Service "+servicename+" : "+e.getMessage());
		}
		return putresponse;

	}
	/*
	 * ****************************************************************************** 
	 * Name : Put 
	 * Parameters : url(The complete URL of the service),Token(generated Authorization Token),ServiceName(name of the service),payload(payload for the request)
	 * Purpose : generating a Put request for specified service using generated token
	 * ******************************************************************************
	 */
	static public Response Put(String url,String token,String servicename,JSONObject payload) {

		RequestSpecification httpRequest = null;
		Response getResponse = null;
		Response putresponse = null;
		try {			

			//*****************************************************************************
			switch(servicename) {

			case "EditIClientDetails":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "ConfigPropertyForCUS":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "EditUserGroup":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
			case "UpdateUser":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "CreateServDescWithCorrectUUID":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "assignSampleService":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "assignSampleServiceWrongClientID":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "GreetingLogIdWithNoClientId":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "AssignAssetToFleet":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "UpdateFleetDetails":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "UpdateGreetingLogWithClientId":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			}	

			//*****************************************************************************
			httpRequest.header("authorization", "Bearer "+token);
			httpRequest.header("Content-Type", "application/json");		
			httpRequest.body(payload.toJSONString());	
			getResponse = httpRequest.request(Method.PUT);
			putresponse = getResponse;
			long seconds= getResponse.getTime();
			//			long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
			info("The response time is : "+seconds+" Milli Seconds");
			info("The response status code for put request for the service "+ servicename  +" : "+putresponse.getStatusCode());
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating Put request for Service "+servicename+" : "+e.getMessage());
		}
		return putresponse;
	}
	/*
	 * ****************************************************************************** 
	 * Name : Put 
	 * Parameters : url(The complete URL of the service),Token(generated Authorization Token),ServiceName(name of the service),payload(payload for the request)
	 * Purpose : generating a Put request for specified service using generated token
	 * ******************************************************************************
	 */
	static public Response Put(String url,String token,String servicename,JSONObject payload, String configureValue) {

		RequestSpecification httpRequest = null;
		Response getResponse = null;
		Response putresponse = null;
		try {			

			//*****************************************************************************
			switch(servicename) {

			case "ConfigPropertyForCUS":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "CreateUpdateClientProperty":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given().queryParam("reconfigure", configureValue);
				break;
			case "CreateUpdateCUSProperty":
				RestAssured.baseURI = url;
				HashMap<String, String> parameters=new HashMap<String, String>();
				parameters.put("reconfigure", configureValue);
				parameters.put("serviceDescriptorName", jsonObject.getAsString("SERVICEDESCRIPTORID"));
				httpRequest = RestAssured.given();
				httpRequest.params(parameters);
				break;
			case "CreateServiceDescriptor":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "UpdateFleet":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "asset":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "EditClientWithAPI":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			case "SendGreetingWithWrongClientID":
				RestAssured.baseURI = url;
				httpRequest = RestAssured.given();
				break;
			}	

			//*****************************************************************************
			httpRequest.header("authorization", "Bearer "+token);
			httpRequest.header("Content-Type", "application/json");		
			httpRequest.body(payload.toJSONString());	
			getResponse = httpRequest.request(Method.PUT);
			putresponse = getResponse;
			long seconds= getResponse.getTime();
			//			long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
			info("The response time is : "+seconds+" Milli Seconds");
			info("The response status code for put request for the service "+ servicename  +" : "+putresponse.getStatusCode());
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating Put request for Service "+servicename+" : "+e.getMessage());
		}
		return putresponse;
	}


	static public Response Put(String url,String token,String servicename,JSONObject payload, String configureValue,String SDName) {

		RequestSpecification httpRequest = null;
		Response getResponse = null;
		Response putresponse = null;
		try {			

			//*****************************************************************************
			switch(servicename) {


			case "CreateUpdateCUSProperty":
				RestAssured.baseURI = url;
				HashMap<String, String> parameters=new HashMap<String, String>();
				parameters.put("reconfigure", configureValue);
				parameters.put("serviceDescriptorName",SDName );
				httpRequest = RestAssured.given();
				httpRequest.params(parameters);
				break;
			}

			//*****************************************************************************
			httpRequest.header("authorization", "Bearer "+token);
			httpRequest.header("Content-Type", "application/json");		
			httpRequest.body(payload.toJSONString());	
			getResponse = httpRequest.request(Method.PUT);
			putresponse = getResponse;
			long seconds= getResponse.getTime();
			//			long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
			info("The response time is : "+seconds+" Milli Seconds");
			info("The response status code for put request for the service "+ servicename  +" : "+putresponse.getStatusCode());
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating Put request for Service "+servicename+" : "+e.getMessage());
		}
		return putresponse;
	}

	/*
	 * ****************************************************************************** 
	 * Name : PostJson 
	 * Parameters : url(The complete URL of the service),Token(generated Authorization Token),ServiceName(name of the service),payloadTemplate(Structure of the json payload),payloadValues(values of the JsonObject keys)
	 * Purpose : generating a post request for specified service using generated token
	 * ******************************************************************************
	 */
	public Response PostJson(String url,String token,String servicename,String payloadTemplate,String payloadValues) {

		RequestSpecification httpRequest = null;
		JSONObject JsonService = null;
		Response getResponse = null;
		Response postresponse = null;
		try {
			RestAssured.baseURI = url;
			httpRequest = RestAssured.given();
			JsonService = new JSONObject();
			//*****************************************************************************
			switch(servicename) {
			case "createservice":
				JsonService = CreateService(payloadTemplate,payloadValues);
				break;
			case "device":
				JsonService = CreateDevice(payloadTemplate,payloadValues);
				break;
			}		
			//*****************************************************************************
			httpRequest.header("authorization", "Bearer "+token);
			httpRequest.header("Content-Type", "application/json");		
			httpRequest.body(JsonService.toJSONString());	
			getResponse = httpRequest.request(Method.POST);
			postresponse = getResponse;
			info("The response status code for post request for th service "+ servicename  +" : "+postresponse.getStatusCode());
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating Post request for Service "+servicename+" : "+e.getMessage());
		}
		return postresponse;


	}

	/*
	 * ****************************************************************************** 
	 * Name : Delete 
	 * Parameters : url(The complete URL of the service),Token(generated Authorization Token)
	 * Purpose : generating a delete request using generated token
	 * ******************************************************************************
	 */

	public Response Delete(String url,String token) {
		RequestSpecification httpRequest = null;
		Response getResponse = null;
		try { 
			RestAssured.baseURI = url;
			httpRequest = RestAssured.given();
			httpRequest.header("authorization", "Bearer "+token);
			httpRequest.header("Content-Type", "application/json");		
			getResponse = httpRequest.request(Method.DELETE);
			long seconds= getResponse.getTime();
			info("The response time is : "+seconds+" Milli Seconds");
			info("The delete message status code is : "+getResponse.statusCode());			
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating delete request using URL "+url+" : "+e.getMessage());
		}

		return getResponse;
	}

	public Response Delete(String url,String token, String servicename, String reconfigureValue) {
		RequestSpecification httpRequest = null;
		Response getResponse = null;
		try{
			switch(servicename) {
			case "DeletePropertyCUSConfigProperty":
				RestAssured.baseURI = url;
				HashMap<String, String> parameters=new HashMap<String, String>();
				parameters.put("reconfigure", reconfigureValue);
				parameters.put("serviceDescriptorId", jsonObject.getAsString("SERVICEDESCRIPTORID"));
				httpRequest = RestAssured.given();
				httpRequest.params(parameters);
				break;
			case "DeletePropertyClientConfigProperty":
				RestAssured.baseURI = url;

				httpRequest = RestAssured.given().queryParam("reconfigure", reconfigureValue);

				break;}
			httpRequest.header("authorization", "Bearer "+token);
			httpRequest.header("Content-Type", "application/json");		
			getResponse = httpRequest.request(Method.DELETE);
			long seconds= getResponse.getTime();
			//			long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
			info("The response time is : "+seconds+" Milli Seconds");
			info("The delete message status code is : "+getResponse.statusCode());			
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating delete request using URL "+url+" : "+e.getMessage());
		}
		return getResponse;
	}

	public Response Delete(String url,String token, String servicename, String reconfigureValue,String serviceDetails) {
		RequestSpecification httpRequest = null;
		Response getResponse = null;
		try{
			switch(servicename) {
			case "DeletePropertyCUSConfigProperty":
				RestAssured.baseURI = url;
				HashMap<String, String> parameters=new HashMap<String, String>();
				parameters.put("reconfigure", reconfigureValue);
				parameters.put("serviceDescriptorName", serviceDetails);
				httpRequest = RestAssured.given();
				httpRequest.params(parameters);
				break;
			}
			httpRequest.header("authorization", "Bearer "+token);
			httpRequest.header("Content-Type", "application/json");		
			getResponse = httpRequest.request(Method.DELETE);
			long seconds= getResponse.getTime();
			info("The response time is : "+seconds+" Milli Seconds");
			info("The delete message status code is : "+getResponse.statusCode());			
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating delete request using URL "+url+" : "+e.getMessage());
		}
		return getResponse;
	}
	/*
	 * ****************************************************************************** 
	 * Name : PutServices 
	 * Parameters : servicename(name of the service),payload(payload required for the request)
	 * Purpose : For getting the list of specified service as json
	 * ******************************************************************************
	 */
	public Response PutServices(String servicename,JSONObject payload) {
		Response ServiceManagementService = null;
		String base_url = null;
		String token = null;

		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);
		try {
			switch(servicename) {
			case "clientservice":
				String ClientServiceId = (String)payload.get("clientserviceid");
				String ServiceId = (String)payload.get("serviceid");
				String ServiceManagementClient = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String Clients = jsonObject.getAsString("CLIENT");
				String urlInitiateService = base_url +"/"+ServiceManagementClient+"/"+Clients+"/"+ClientServiceId+"/service/";
				ExtentReport.info("The InitiateService url is :"+urlInitiateService);
				ServiceManagementService =  Put(urlInitiateService,token,servicename,ServiceId);
				break;
			case "ServiceDescriptorWithAppId":
				String ServiceDescriptorClientId = (String)payload.get("client1id");
				String ServiceDescriptorServiceId = (String)payload.get("ServiceId");
				String ServiceescriptorManagementClient = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String urlInitiateServiceDescriptor = base_url +"/"+ServiceescriptorManagementClient+"/"+"clients"+"/"+ServiceDescriptorClientId+"/service/"+ServiceDescriptorServiceId;
				ExtentReport.info("The InitiateService url is :"+urlInitiateServiceDescriptor);
				ServiceManagementService =  Put(urlInitiateServiceDescriptor,token,servicename,ServiceDescriptorServiceId);
				break;
			case "assignServiceToClientWithoutAppid":
				String ServiceDescriptorWithOutAppIdClientId = (String)payload.get("client1id");
				String ServiceDescriptorWithOutAppIdServiceId = (String)payload.get("ServiceId");
				String ServiceescriptorWithOutAppIdManagementClient = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String urlInitiateServiceDescriptorWithOutAppId = base_url +"/"+ServiceescriptorWithOutAppIdManagementClient+"/"+"clients"+"/"+ServiceDescriptorWithOutAppIdClientId+"/service/";
				ExtentReport.info("The InitiateService url is :"+urlInitiateServiceDescriptorWithOutAppId);
				ServiceManagementService =  Put(urlInitiateServiceDescriptorWithOutAppId,token,servicename,ServiceDescriptorWithOutAppIdServiceId);
				break;
			case "SendGreetingWithWrongClientID":
				String SendGreetingWithWrongClientIdUrl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");	
				String SendGreetingWithWrongClient = jsonObject.getAsString("CLIENT_WRONGID");
				String SendGreetingIDProperty = jsonObject.getAsString("GREETING_ID1");
				String urlSendGreetingWithWrongClientID = base_url +"/"+SendGreetingWithWrongClientIdUrl+"/"+"clients"+"/"+SendGreetingWithWrongClient+"/greeting-logs/"+SendGreetingIDProperty;
				ExtentReport.info("The InitiateService url is :"+urlSendGreetingWithWrongClientID);
				ServiceManagementService =  Put(urlSendGreetingWithWrongClientID,token,servicename,payload,SendGreetingWithWrongClient);
				break;
			case "CreateServiceDescriptor":
				String CreateServiceDescriptor = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String Services = jsonObject.getAsString("SERVICES");
				String serviceDetails=payload.get("uuid").toString();
				payload.remove("uuid");
				//				String ServiceDescriptorID=jsonObject.getAsString("SERVICEDESCRIPTORID");
				String urlCreateServiceDescriptor = base_url +"/"+CreateServiceDescriptor+"/"+Services+"/"+serviceDetails;
				ExtentReport.info("The InitiateService url is :"+urlCreateServiceDescriptor);
				ServiceManagementService =  Put(urlCreateServiceDescriptor,token,servicename,payload,serviceDetails);
				break;
			case "assignSampleServiceWrongClientID":
				String ServiceWrongClientID = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String ClientDetails = jsonObject.getAsString("CLIENTS");
				String wrongClientID = jsonObject.getAsString("CLIENT_WRONGID");
				String sampleServicenameClient = jsonObject.getAsString("SERVICE");
				String serviceID = jsonObject.getAsString("SAMPLE_SERVICE_ID");
				String urlInitiateSampleService = base_url +"/"+ServiceWrongClientID+"/"+ClientDetails+"/"+wrongClientID+"/"+sampleServicenameClient+"/"+serviceID;
				ExtentReport.info("The InitiateService url is :"+urlInitiateSampleService);
				ServiceManagementService = Put(urlInitiateSampleService,token,servicename,payload);
				break;
			case "GreetingLogIdWithNoClientId":
				String MulticlientSampleService = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String MulticlientSampleServiceClient = jsonObject.getAsString("CLIENTS");
				String MulticlientSampleServiceGreeting=jsonObject.getAsString("GREETING_LOGS");
				String MulticlientSampleServiceGreetingId=jsonObject.getAsString("GREETINGIDNOCLIENTID");
				String urlMulticlientSampleService = base_url +"/"+MulticlientSampleService+"/"+MulticlientSampleServiceClient+"/"+MulticlientSampleServiceGreeting+"/"+MulticlientSampleServiceGreetingId;
				ExtentReport.info("The InitiateService url is :"+urlMulticlientSampleService);
				ServiceManagementService =  Put(urlMulticlientSampleService,token,servicename,payload);
				break;
			case "AssignAssetToFleet":
				String FleetUrl = jsonObject.getAsString("FLEET_URL");
				String ClientID = jsonObject.getAsString("CLIENTID");
				String FLEETS = jsonObject.getAsString("FLEETS");
				String Assets = jsonObject.getAsString("ASSETS");
				String urlFleet = base_url +"/"+ClientID+"/"+FleetUrl+"/"+FLEETS+"/"+Assets;
				ExtentReport.info("The fleet url is :"+urlFleet);
				ServiceManagementService = Put(urlFleet,token,servicename,payload);
				break;
			case "UpdateFleetDetails":
				String UpdateFleetDetailsUrl = jsonObject.getAsString("FLEET_URL");
				String UpdateFleetDetailsClientID = jsonObject.getAsString("CLIENTID");
				String UpdateFleetDetailsFLEETS = jsonObject.getAsString("FLEETS");
				String urlUpdateFleet = base_url +"/"+UpdateFleetDetailsClientID+"/"+UpdateFleetDetailsUrl+"/"+UpdateFleetDetailsFLEETS;
				ExtentReport.info("The fleet url is :"+urlUpdateFleet);
				ServiceManagementService = Put(urlUpdateFleet,token,servicename,payload);
				break;
			case "assignServiceToClient":
				String SDClientId = jsonObject.getAsString("CLIENTID");
				String SDServiceName =payload.toString();
				String SDManagementClient = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String urlInitiateSDService = base_url +"/"+SDManagementClient+"/"+"clients"+"/"+SDClientId+"/service/"+SDServiceName;
				ExtentReport.info("The InitiateService url is :"+urlInitiateSDService);
				ServiceManagementService =  Put(urlInitiateSDService,token,servicename);
				break;
			}
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating PutSeServiceIdrvices request for the service "+servicename+" : "+e.getMessage());
		}

		return ServiceManagementService;		
	}

	/*
	 * ****************************************************************************** 
	 * Name : PutServices 
	 * Parameters : servicename(name of the service)
	 * Purpose : For getting the list of specified service
	 * ******************************************************************************
	 */
	public Response PutServices(String servicename) {
		Response ServiceManagementService = null;
		String base_url = null;
		String token = null;
		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);
		try {
			switch(servicename) {
			case "CreateServiceDescriptorWithNoUUID":
				String CreateServiceDescriptor = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String Services = jsonObject.getAsString("SERVICES");
				String urlCreateServiceDescriptor = base_url +"/"+CreateServiceDescriptor+"/"+Services;
				ExtentReport.info("The InitiateService url is :"+urlCreateServiceDescriptor);
				ServiceManagementService =  Put(urlCreateServiceDescriptor,token,servicename);
				break;
			case "FleetService":
				String FleetManagement = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String Service = jsonObject.getAsString("SERVICE");
				String Clients= jsonObject.getAsString("CLIENT");
				String ClientNEW=jsonObject.getAsString("CLIENTNEW");
				String Fleet=jsonObject.getAsString("FLEET_URL");
				String FleetManagementServiceUrl = base_url +"/"+FleetManagement+"/"+Clients+"/"+ClientNEW+"/"+Service+"/"+Fleet;
				ExtentReport.info("The InitiateService url is :"+FleetManagementServiceUrl);
				ServiceManagementService =  Put(FleetManagementServiceUrl,token,servicename);
			}
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating PutSeServiceIdrvices request for the service "+servicename+" : "+e.getMessage());
		}

		return ServiceManagementService;		
	}
	/*
	 * ****************************************************************************** 
	 * Name : PutServices 
	 * Parameters : servicename(name of the service),ServiceId(Service Id or common Id to process)
	 * Purpose : For getting the list of specified service with service id
	 * ******************************************************************************
	 */
	public Response PutServices(String servicename,String ServiceId) {
		Response ServiceManagementService = null;
		String base_url = null;
		String token = null;
		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);
		try {
			switch(servicename) {
			case "AssignClientServiceSD":
				String ServiceDescriptorSercice = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String Services = jsonObject.getAsString("SERVICE");
				String Clients= jsonObject.getAsString("CLIENTS");
				String ClientID = jsonObject.getAsString("CLIENTID");
				String urlCreateServiceDescriptor = base_url +"/"+ServiceDescriptorSercice+"/"+Clients+"/"+ClientID+"/"+Services+"/"+ServiceId;
				ExtentReport.info("The InitiateService url is :"+urlCreateServiceDescriptor);
				ServiceManagementService =  Put(urlCreateServiceDescriptor,token,servicename);
				break;

			}
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating PutSeServiceIdrvices request for the service "+servicename+" : "+e.getMessage());
		}

		return ServiceManagementService;		
	}
	/*
	 * ****************************************************************************** 
	 * Name : GetServices 
	 * Parameters : servicename(name of the service)
	 * Purpose : For getting the list of specified service as json
	 * ******************************************************************************
	 */

	public Response GetServices(String servicename ) {

		String base_url = null;
		String token = null;

		Response getJSON = null;
		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);
		try {

			switch(servicename) {
			case "device":
				String DevicesUrl = jsonObject.getAsString("DEVICE_URL");
				String Devices = jsonObject.getAsString("DEVICES");
				String urlDevice = base_url +"/"+DevicesUrl+"/"+Devices+"/";
				getJSON = Get(urlDevice,token);
				break;
			case "asset":
				String AssetsUrl = jsonObject.getAsString("ASSET_URL");
				String Assets = jsonObject.getAsString("ASSETS");
				String urlAsset = base_url +"/"+AssetsUrl+"/"+Assets+"/";
				ExtentReport.info("The asset url is :"+urlAsset);
				getJSON = Get(urlAsset,token);
				break;
			case "client":
				String ClientsUrl = jsonObject.getAsString("CLIENT_URL");
				String Clients = jsonObject.getAsString("CLIENTS");;
				String urlClient = base_url +"/"+ClientsUrl+"/"+Clients;
				ExtentReport.info("The asset url is :"+urlClient);
				getJSON = Get(urlClient,token);
				break;	
			case "clientservice":
				String ServiceManagementClient = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String Client = jsonObject.getAsString("CLIENT");
				String urlInitiateService = base_url +"/"+ServiceManagementClient+"/"+Client;
				ExtentReport.info("The InitiateService url is :"+urlInitiateService);
				getJSON = Get(urlInitiateService,token);
				break;
			case "servicedescriptorservice":
				String ServiceManagementMultiClient = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String Services = jsonObject.getAsString("SERVICES");
				String urlServiceDescriptorService = base_url +"/"+ServiceManagementMultiClient+"/"+Services+"/";
				ExtentReport.info("The InitiateService url is :"+urlServiceDescriptorService);
				getJSON = Get(urlServiceDescriptorService,token);
				break;
			case "getPropulsionTypeDetails":
				String AssetUrl = jsonObject.getAsString("ASSET_URL");
				String PropulsionType = jsonObject.getAsString("PROPULSIONTYPE");
				String urlPropulsionType = base_url +"/"+AssetUrl+"/"+PropulsionType;
				ExtentReport.info("The InitiateService url is :"+urlPropulsionType);
				getJSON = Get(urlPropulsionType,token);
				break;
			case "vehicleType":
				String VehicleTypesUrl = jsonObject.getAsString("VEHICLE_URL");
				String VehicleTypes = jsonObject.getAsString("VEHICLETYPE");
				String urlVehicleTypes = base_url +"/"+VehicleTypesUrl+"/"+VehicleTypes;
				ExtentReport.info("The vehicle url is :"+urlVehicleTypes);
				getJSON = Get(urlVehicleTypes,token);
				break;
			case "vehicleTypeOne":
				String VehicleTypesOneUrl = jsonObject.getAsString("VEHICLE_URL");
				String VehicleTypesOne = jsonObject.getAsString("VEHICLETYPE");
				String VehicleTypesOneID = jsonObject.getAsString("VEHICLETYPEONE");
				String urlVehicleTypesOne = base_url +"/"+VehicleTypesOneUrl+"/"+VehicleTypesOne+"/"+VehicleTypesOneID;
				ExtentReport.info("The vehicle url is :"+urlVehicleTypesOne);
				getJSON = Get(urlVehicleTypesOne,token);
			case "getSpecificPropulsionTypeDetails":
				String AssetUrl1 = jsonObject.getAsString("ASSET_URL");
				String PropulsionType1 = jsonObject.getAsString("PROPULSIONTYPE");
				String urlPropulsionType1 = base_url +"/"+AssetUrl1+"/"+PropulsionType1;
				ExtentReport.info("The InitiateService url is :"+urlPropulsionType1);
				getJSON = Get(urlPropulsionType1,token);
				break;
			case "getAllRegistration":
				String BaseUrl = jsonObject.getAsString("BASE_URL");
				String ServiceCatalogUrl = jsonObject.getAsString("SERVICECATALOG_URL");
				String ListRegistrationUrl = jsonObject.getAsString("LIST_REGISTRATION");
				String urlgetAllRegistration = BaseUrl +"/"+ServiceCatalogUrl+"/"+ListRegistrationUrl;
				ExtentReport.info("The InitiateService url is :"+urlgetAllRegistration);
				getJSON = Get(urlgetAllRegistration,token);
				break;
			case "updatedClientProperty":
				String ServiceManagementUpdatedClientProperty = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String UpdatedClientProperty = jsonObject.getAsString("CLIENTPROPERTY");
				String UpdatedClientPropertyID = jsonObject.getAsString("CLIENT1_ID");
				String UpdatedClientPropertyNew = jsonObject.getAsString("HIERARCHICAL");
				String urlUpdatedClientProperty = base_url +"/"+ServiceManagementUpdatedClientProperty+"/"+UpdatedClientProperty+"/"+UpdatedClientPropertyID+"/"+UpdatedClientPropertyNew;
				ExtentReport.info("The ClientProperty url is :"+urlUpdatedClientProperty);
				getJSON = Get(urlUpdatedClientProperty,token);
				break;	
			case "GetClientProperty":
				String CreateUpdatePropertyURL = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String CreateUpdatePropertyConfig = jsonObject.getAsString("CONFIG");
				String CreateUpdatePropertyClient = jsonObject.getAsString("CLIENTS");
				String CreateUpdatePropertyClientID = jsonObject.getAsString("CLIENTID");
				String urlGetClientCreateUpdateProperty = base_url +"/"+CreateUpdatePropertyURL+"/"+CreateUpdatePropertyConfig+"/"+CreateUpdatePropertyClient+"/"+CreateUpdatePropertyClientID;
				ExtentReport.info("The ClientProperty url is :"+urlGetClientCreateUpdateProperty);
				getJSON = Get(urlGetClientCreateUpdateProperty,token);
				break;
			case "GetConfigHierarchicalClientProperty":
				String ConfigHierarchicalPropertyURL = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String ConfigHierarchicalConfig = jsonObject.getAsString("CONFIG");
				String ConfigHierarchicalClient = jsonObject.getAsString("CLIENTS");
				String ConfigHierarchicalClientID = jsonObject.getAsString("CLIENTID");
				String ConfigHierarchicalHierarchical = jsonObject.getAsString("HIERARCHICAL");
				String urlGetConfigHierarchicalProperty = base_url +"/"+ConfigHierarchicalPropertyURL+"/"+ConfigHierarchicalConfig+"/"+ConfigHierarchicalClient+"/"+ConfigHierarchicalClientID+"/"+ConfigHierarchicalHierarchical;
				ExtentReport.info("The ClientProperty url is :"+urlGetConfigHierarchicalProperty);
				getJSON = Get(urlGetConfigHierarchicalProperty,token);
				break;
			case "GetCUSProperty":
				String CUSUClientManagement = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String CUSClientId = jsonObject.getAsString("CLIENTID");
				String CUSClient = jsonObject.getAsString("CLIENTS");
				String ServiceDescriptorID= jsonObject.getAsString("SERVICEDESCRIPTORID");
				String urlInitiateCUSProperty = base_url +"/"+CUSUClientManagement+"/"+"config"+"/"+CUSClient+"/"+CUSClientId;
				ExtentReport.info("The CUS Property url is :"+urlInitiateCUSProperty);
				getJSON = Get(urlInitiateCUSProperty,token,servicename,ServiceDescriptorID);
				break;

			case "GetConfigHierarchicalCUSProperty":
				String HierarchicalCUSUClientManagement = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String HierarchicalCUSClientId = jsonObject.getAsString("CLIENTID");
				String HierarchicalCUSClient = jsonObject.getAsString("CLIENTS");
				String HierarchicalCUS = jsonObject.getAsString("HIERARCHICAL");
				String HierarchicalConfig = jsonObject.getAsString("CONFIG");
				urlInitiateCUSProperty = base_url +"/"+HierarchicalCUSUClientManagement+"/"+HierarchicalConfig+"/"+HierarchicalCUSClient+"/"+HierarchicalCUSClientId+"/"+HierarchicalCUS;
				ExtentReport.info("The CUS Property url is :"+urlInitiateCUSProperty);
				getJSON = Get(urlInitiateCUSProperty,token,servicename,jsonObject.getAsString("SERVICEDESCRIPTORID"));
				break;
			case "GetWrongSSIDPropertyDetails":
				String WrongSSIDCUSUClientManagement = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String WrongSSIDCUSClientId = jsonObject.getAsString("CLIENTID");
				String WrongSSIDCUSClient = jsonObject.getAsString("CLIENTS");
				String WrongSSIDServiceDescriptorID= jsonObject.getAsString("WRONGSERVICEDESCRIPTIONID");
				String urlInitiateWrongSSIDCUSProperty = base_url +"/"+WrongSSIDCUSUClientManagement+"/"+"config"+"/"+WrongSSIDCUSClient+"/"+WrongSSIDCUSClientId+WrongSSIDServiceDescriptorID;
				ExtentReport.info("The CUS Property url is :"+urlInitiateWrongSSIDCUSProperty);
				getJSON = Get(urlInitiateWrongSSIDCUSProperty,token);
				break;
			case "GlobalInfoClients":
				String MultiClientSampleService = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String MultiClientSampleServiceGlobalInfo = jsonObject.getAsString("GLOBAL_INFO");
				String GlobalInfoClientsClients = jsonObject.getAsString("CLIENTS");
				String urlInitiateGlobalInfoClients = base_url +"/"+MultiClientSampleService+"/"+MultiClientSampleServiceGlobalInfo+"/"+GlobalInfoClientsClients;
				ExtentReport.info("The GlobalInfoClients url is :"+urlInitiateGlobalInfoClients);
				getJSON = Get(urlInitiateGlobalInfoClients,token);
				break;
			case "PrometheusMetrics":
				MultiClientSampleService = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String Metrics = jsonObject.getAsString("METRICS");
				String prometheus = jsonObject.getAsString("PROMETHEUS");
				String urlInitiatePrometheusMetrics = base_url +"/"+MultiClientSampleService+"/"+Metrics+"/"+prometheus;
				ExtentReport.info("The PrometheusMetrics url is :"+urlInitiatePrometheusMetrics);
				getJSON = Get(urlInitiatePrometheusMetrics,token);
				break;
			case "ClientContextExtractorHierarchy":
				String ClientUrl = jsonObject.getAsString("CLIENT_URL");
				String ClientContext = jsonObject.getAsString("CLIENT_CONTEXT");
				String Client2Id = jsonObject.getAsString("CLIENT1_ID");
				String Hierarchy = jsonObject.getAsString("HIERARCHY");
				String urlInitiateClientContextExtractor = base_url +"/"+ClientUrl+"/"+ClientContext+"/"+Client2Id+"/"+Hierarchy;
				ExtentReport.info("The ClientContextExtractor url is :"+urlInitiateClientContextExtractor);
				getJSON = Get(urlInitiateClientContextExtractor,token);
				break;
			case "ClientContextExtractorUser":
				ClientUrl = jsonObject.getAsString("CLIENT_URL");
				ClientContext = jsonObject.getAsString("CLIENT_CONTEXT");
				Client2Id = jsonObject.getAsString("CLIENT1_ID");
				String Users = jsonObject.getAsString("USERS");
				Clients = jsonObject.getAsString("CLIENT");
				String urlInitiateClientContextUsers = base_url +"/"+ClientUrl+"/"+ClientContext+"/"+Clients+"/"+Client2Id+"/"+Users;
				ExtentReport.info("The ClientContextExtractor url is :"+urlInitiateClientContextUsers);
				getJSON = Get(urlInitiateClientContextUsers,token);
				break;
			case "HealthCheck":
				MultiClientSampleService = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String Health = jsonObject.getAsString("HEALTH");
				String Check = jsonObject.getAsString("CHECK");
				String urlInitiateHealthCheck = base_url +"/"+MultiClientSampleService+"/"+Health+"/"+Check;
				ExtentReport.info("The ClientContextExtractor url is :"+urlInitiateHealthCheck);
				getJSON = Get(urlInitiateHealthCheck,token);
				break;
			case "getGreetingLogForClient":
				String GreetingLogForClientService = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String getGreetingLogForClient = jsonObject.getAsString("CLIENTS");
				String getGreetingLogForClient1ID = jsonObject.getAsString("CLIENT2_ID");
				String urlgetGreetingLogForClient = base_url +"/"+GreetingLogForClientService+"/"+getGreetingLogForClient+"/"+getGreetingLogForClient1ID+"/"+"greeting-logs";
				ExtentReport.info("The GlobalInfoClients url is :"+urlgetGreetingLogForClient);
				getJSON = Get(urlgetGreetingLogForClient,token);
				break;
			case "GetClientDetails":
				String ClientDetailsPropertyURL =jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String ClientDetailsPropertyClient = jsonObject.getAsString("CLIENTS");
				String urlClientDetailsProperty = base_url +"/"+ClientDetailsPropertyURL+"/"+ClientDetailsPropertyClient;
				ExtentReport.info("The ClientProperty url is :"+urlClientDetailsProperty);
				getJSON = Get(urlClientDetailsProperty,token);
				break;
			case "GetServiceDetailsOfAClient":
				String GetServiceDetailsOfAClientURL = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String GetServiceDetailsOfAClient = jsonObject.getAsString("CLIENTS");
				String GetServiceDetailsOfAClientID = jsonObject.getAsString("CLIENTID");
				String urlGetServiceDetailsOfAClient = base_url +"/"+GetServiceDetailsOfAClientURL+"/"+GetServiceDetailsOfAClient+"/"+GetServiceDetailsOfAClientID;
				ExtentReport.info("The ClientProperty url is :"+urlGetServiceDetailsOfAClient);
				getJSON = Get(urlGetServiceDetailsOfAClient,token);
				break;
			case "GetCUSDetailsOfCreatedCUSUser":
				String CUSDetailsOfCreatedCUSUserUrl = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String CUSDetailsOfCreatedCUSUserClient = jsonObject.getAsString("CLIENTS");
				String CUSDetailsOfCreatedCUSUserClientId = jsonObject.getAsString("CLIENTID");
				String CUSDetailsOfCreatedCUSUserService = jsonObject.getAsString("SERVICE");
				String CUSDetailsOfCreatedCUSUserServiceDescriptorID= jsonObject.getAsString("SERVICEDESCRIPTORID");
				String urlInitiateCUSDetailsOfCreatedCUSUser = base_url +"/"+CUSDetailsOfCreatedCUSUserUrl+"/"+CUSDetailsOfCreatedCUSUserClient+"/"+CUSDetailsOfCreatedCUSUserClientId+"/"+CUSDetailsOfCreatedCUSUserService+"/"+CUSDetailsOfCreatedCUSUserServiceDescriptorID;
				ExtentReport.info("The CUS Property url is :"+urlInitiateCUSDetailsOfCreatedCUSUser);
				getJSON = Get(urlInitiateCUSDetailsOfCreatedCUSUser,token);
				break;
			case "GetCUSPropertyDetails":
				String CUSClientManagement = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String CUSId = jsonObject.getAsString("CLIENTID");
				String CUS = jsonObject.getAsString("CLIENTS");
				String CUSServiceDescriptorID= jsonObject.getAsString("SERVICEDESCRIPTORID");
				String urlInitiateCUSPropertyID = base_url +"/"+CUSClientManagement+"/"+"config"+"/"+CUS+"/"+CUSId;
				ExtentReport.info("The CUS Property url is :"+urlInitiateCUSPropertyID);
				getJSON = Get(urlInitiateCUSPropertyID,token,servicename,CUSServiceDescriptorID);
				break;
			case "GlobalGreetings":
				String GlobalService = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String Global = jsonObject.getAsString("GLOBAL");
				String GreetingLog = jsonObject.getAsString("GREETING_LOGS");
				String urlInitiateGlobalGreeting = base_url +"/"+GlobalService+"/"+Global+"/"+GreetingLog;
				ExtentReport.info("The InitiateService url is :"+urlInitiateGlobalGreeting);
				getJSON = Get(urlInitiateGlobalGreeting,token);
				break;
			case "GetGreetingsNoClient":
				String GetClientGreeting = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String GetClients = jsonObject.getAsString("CLIENT");
				//				String ClientID = jsonObject.getAsString("CLIENT1_ID");
				String urlInitiateGetGreetingsService = base_url +"/"+GetClientGreeting+"/"+GetClients+"/greet";
				ExtentReport.info("The InitiateService url is :"+urlInitiateGetGreetingsService);
				getJSON =  Get(urlInitiateGetGreetingsService,token);
				break;
			case "proxyCurrentContext":
				String proxyCurrentContext = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String proxyClients = jsonObject.getAsString("CLIENT");
				String proxy = jsonObject.getAsString("PROXY");
				String urlInitiateproxyContext = base_url +"/"+proxyCurrentContext+"/"+proxyClients+"/"+proxy+"/"+"current-contexts";
				ExtentReport.info("The InitiateService url is :"+urlInitiateproxyContext);
				getJSON = Get(urlInitiateproxyContext,token);
			case "GetClientContextWithWrongClientID":				 
				String ClientContextWithWrongIDPropertyURL = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String ClientContextWithWrongIDPropertyClient = jsonObject.getAsString("CLIENTS");
				String ClientContextWithWrongID = jsonObject.getAsString("CLIENT_WRONGID_2");
				String ClientContextPropertyCurrentClient = jsonObject.getAsString("CURRENT_CLIENT_CONTEXT");
				String urlClientContextWithWrongIDProperty = base_url +"/"+ClientContextWithWrongIDPropertyURL+"/"+ClientContextWithWrongIDPropertyClient+"/"+"/"+ClientContextWithWrongID+"/"+ClientContextPropertyCurrentClient;
				ExtentReport.info("The ClientProperty url is :"+urlClientContextWithWrongIDProperty);
				getJSON = Get(urlClientContextWithWrongIDProperty,token);
				break;
			case "GetCurrentContextIDWithNoClientID":				 
				String CurrentContextIDWithNoClientIDPropertyURL = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String CurrentContextIDWithNoClientIDClient = jsonObject.getAsString("CLIENTS");
				String CurrentContextPropertyCurrentContext = jsonObject.getAsString("CURRENT_CONTEXTS");
				String urlCurrentContextProperty = base_url +"/"+CurrentContextIDWithNoClientIDPropertyURL+"/"+CurrentContextIDWithNoClientIDClient+"/"+"/"+CurrentContextPropertyCurrentContext;
				ExtentReport.info("The ClientProperty url is :"+urlCurrentContextProperty);
				getJSON = Get(urlCurrentContextProperty,token);
				break;

			case "GetCurrentClientIDWithWrongClientID":				 
				String CurrentClientIDWithWrongIDUrl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String CurrentClientWithWrongIDPropertyClient = jsonObject.getAsString("CLIENTS");
				String CurrentClientWithWrongID = jsonObject.getAsString("CLIENT_WRONGID_2");
				String ClientPropertyCurrentClientID = jsonObject.getAsString("CURRENT_CLIENT_ID");
				String urlCurrentClientWithWrongIDProperty = base_url +"/"+CurrentClientIDWithWrongIDUrl+"/"+CurrentClientWithWrongIDPropertyClient+"/"+"/"+CurrentClientWithWrongID+"/"+ClientPropertyCurrentClientID;
				ExtentReport.info("The ClientProperty url is :"+urlCurrentClientWithWrongIDProperty);
				getJSON = Get(urlCurrentClientWithWrongIDProperty,token);
				break;

			case "GetCurrentClientIDWithNoClientID":				 
				String CurrentClientIDWithNoClientIDUrl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String CurrentClientIDWithNoClientIDProperty = jsonObject.getAsString("CLIENTS");
				String CurrentClientWithNoClientID = jsonObject.getAsString("CURRENT_CLIENT_ID");
				String urlCurrentClientWithNoClientID = base_url +"/"+CurrentClientIDWithNoClientIDUrl+"/"+CurrentClientIDWithNoClientIDProperty+"/"+CurrentClientWithNoClientID;
				ExtentReport.info("The ClientProperty url is :"+urlCurrentClientWithNoClientID);
				getJSON = Get(urlCurrentClientWithNoClientID,token);
				break;
			case "GetClientDetailsWithoutClientID":
				String ClientDetailsWithoutIDPropertyURL = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String ClientDetailsWithoutIDPropertyClient = jsonObject.getAsString("CLIENTS");
				String ClientDetailsPropertyCurrentClient = jsonObject.getAsString("CURRENT_CLIENT");
				String urlClientDetailsWithoutIDProperty = base_url +"/"+ClientDetailsWithoutIDPropertyURL+"/"+ClientDetailsWithoutIDPropertyClient+"/"+ClientDetailsPropertyCurrentClient;
				ExtentReport.info("The ClientProperty url is :"+urlClientDetailsWithoutIDProperty);
				getJSON = Get(urlClientDetailsWithoutIDProperty,token);
				break;
			case "GetMessageWithWrongClientIDCorrectGreetingID":
				String GetMessageURL = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String GetMessageClients = jsonObject.getAsString("CLIENT");		 
				String GetMessageWrongID = jsonObject.getAsString("CLIENT_WRONGID_2");	
				String GreetingLOGS = jsonObject.getAsString("GREETING_LOGS");	
				String GreetingID = jsonObject.getAsString("GREETING_ID");
				String urlMessageWithWrongClientIDCorrectGreetingID= base_url +"/"+GetMessageURL+"/"+GetMessageClients+"/"+GetMessageWrongID+"/"+GreetingLOGS+"/"+GreetingID;
				ExtentReport.info("The PrometheusMetrics url is :"+urlMessageWithWrongClientIDCorrectGreetingID);
				getJSON = Get(urlMessageWithWrongClientIDCorrectGreetingID,token);
				break;
			case "GetGreetingWithWrongClientID":
				String GetGreetingWithWrongClientIDURL = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String GetGreetingWithWrongClientIDClients = jsonObject.getAsString("CLIENT");		 
				String GetGreetingWrongID = jsonObject.getAsString("CLIENT_WRONGID");	
				String GreetingLogs = jsonObject.getAsString("GREETING_LOGS");
				String urlGetGreetingWithWrongClientID= base_url +"/"+GetGreetingWithWrongClientIDURL+"/"+GetGreetingWithWrongClientIDClients+"/"+GetGreetingWrongID+"/"+GreetingLogs;
				ExtentReport.info("The PrometheusMetrics url is :"+urlGetGreetingWithWrongClientID);
				getJSON = Get(urlGetGreetingWithWrongClientID,token);
				break;
			case "GetWrongClientIdDetails":			
				String GetCurrentWrongClientIDurl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String GetCurrentWrongClientID = jsonObject.getAsString("CLIENT");
				String GetCurrentWrongClientIDId = jsonObject.getAsString("CLIENT_WRONGID");
				String GetCurrentWrongClientName = jsonObject.getAsString("CURRENTCLIENT");
				String urlGetCurrentWrongClient = base_url +"/"+GetCurrentWrongClientIDurl+"/"+GetCurrentWrongClientID+"/"+GetCurrentWrongClientIDId+"/"+GetCurrentWrongClientName;
				ExtentReport.info("The InitiateService url is :"+urlGetCurrentWrongClient);
				getJSON = Get(urlGetCurrentWrongClient,token);
				break;
			case "CurrentClientContextWithNoClientID":			
				String CurrentClientContextWithNoClientIDurl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String CurrentClientContextWithNoClientID = jsonObject.getAsString("CLIENT");
				String CurrentClientContextWithNoClient = jsonObject.getAsString("CURRENTCLIENTCONTEXT");
				String urlCurrentClientContextWithNoClient = base_url +"/"+CurrentClientContextWithNoClientIDurl+"/"+CurrentClientContextWithNoClientID+"/"+CurrentClientContextWithNoClient;
				ExtentReport.info("The InitiateService url is :"+urlCurrentClientContextWithNoClient);
				getJSON = Get(urlCurrentClientContextWithNoClient,token);
				break;
			case "CurrentClientContextWithWrongCliendId":			
				String CurrentClientContextWithWrongCliendIdurl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String CurrentClientContextWithWrongCliendId = jsonObject.getAsString("CLIENT");
				String CurrentClientContextWithWrongCliendIdID = jsonObject.getAsString("CLIENT_WRONGID");				
				String CurrentClientContext = jsonObject.getAsString("CURRENT_CONTEXTS");
				String urlCurrentClientContextWithWrongCliendId = base_url +"/"+CurrentClientContextWithWrongCliendIdurl+"/"+CurrentClientContextWithWrongCliendId+"/"+CurrentClientContextWithWrongCliendIdID+"/"+CurrentClientContext;
				ExtentReport.info("The InitiateService url is :"+urlCurrentClientContextWithWrongCliendId);
				getJSON = Get(urlCurrentClientContextWithWrongCliendId,token);
				break;
			case "CurrentRequestContextNoclientId":			
				String CurrentRequestContextNoclientIdurl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String CurrentRequestContextNoclientId = jsonObject.getAsString("CLIENT");
				String CurrentRequestContextNoclientIdID = jsonObject.getAsString("CURRENT_REQUEST_CONTEXT");				
				String urlCurrentRequestContextNoclientId = base_url +"/"+CurrentRequestContextNoclientIdurl+"/"+CurrentRequestContextNoclientId+"/"+CurrentRequestContextNoclientIdID;
				ExtentReport.info("The InitiateService url is :"+urlCurrentRequestContextNoclientId);
				getJSON = Get(urlCurrentRequestContextNoclientId,token);
				break;

			case "GetServiceDetailsOfWrongClient":
				String GetServiceDetailsOfWrongClientURL = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String GetServiceDetailsOfWrongClient = jsonObject.getAsString("CLIENTS");
				String GetServiceDetailsOfWrongClientID = jsonObject.getAsString("CLIENT_WRONGID");
				String urlGetServiceDetailsOfWrongClient = base_url +"/"+GetServiceDetailsOfWrongClientURL+"/"+GetServiceDetailsOfWrongClient+"/"+GetServiceDetailsOfWrongClientID;
				ExtentReport.info("The ClientProperty url is :"+urlGetServiceDetailsOfWrongClient);
				getJSON = Get(urlGetServiceDetailsOfWrongClient,token);
				break;
			case "GetClientsDetails":
				String IClientUrl = jsonObject.getAsString("CLIENT_URL");
				String IClients = jsonObject.getAsString("CLIENTS");
				String urlICLients = base_url +"/"+IClientUrl+"/"+IClients;
				ExtentReport.info("The Users url is :"+urlICLients);
				getJSON =  Get(urlICLients,token);
				break;
			case "UserGroup":
				ClientUrl = jsonObject.getAsString("CLIENT_URL");
				Users = jsonObject.getAsString("USERS");
				String ClientId = jsonObject.getAsString("CLIENTID");
				String urlUserGroup = base_url +"/"+ClientUrl+"/"+Users;
				ExtentReport.info("The Users url is :"+urlUserGroup);
				getJSON =  Get(urlUserGroup,token,servicename,ClientId);
				break;
			case "user":
				ClientUrl = jsonObject.getAsString("CLIENT_URL");
				Users = jsonObject.getAsString("USERS");
				String urlUser = base_url +"/"+ClientUrl+"/"+Users;
				ExtentReport.info("The Users url is :"+urlUser);
				getJSON =  Get(urlUser,token);
				break;
			case "getDestination":			
				String getDestinationurl = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String getDestinationId = jsonObject.getAsString("DESTINATIONS");					
				String urlgetDestination = base_url +"/"+getDestinationurl+"/"+getDestinationId+"/";
				ExtentReport.info("The InitiateService url is :"+urlgetDestination);
				getJSON = Get(urlgetDestination,token);
				break;	
			case "GetQueueDestinations":
				String GetQueueClientManagement = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String GetQueueDestinations = jsonObject.getAsString("DESTINATIONS");
				String GetQueueDestinationsType = jsonObject.getAsString("TYPEQUEUE");
				String urlGetQueueDestinations = base_url +"/"+GetQueueClientManagement+"/"+GetQueueDestinations+"?"+GetQueueDestinationsType;
				ExtentReport.info("The Queue Destination url is :"+urlGetQueueDestinations);
				getJSON = Get(urlGetQueueDestinations,token);
				break;	
			case "GetDestinations":
				String GetDestinationsClientManagement = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String GetDestinations = jsonObject.getAsString("DESTINATIONS");
				String urlGetDestinations = base_url +"/"+GetDestinationsClientManagement+"/"+GetDestinations;
				ExtentReport.info("The Queue Destination url is :"+urlGetDestinations);
				getJSON = Get(urlGetDestinations,token);
				break;
			case "GetAllDestinationsFromStorage":
				String GetAllDestinationsURL = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String GetAllDestinationsGlobal= jsonObject.getAsString("GLOBAL");
				String GetAllDestination = jsonObject.getAsString("DESTINATION");
				String urlGetAllDestination= base_url +"/"+GetAllDestinationsURL+"/"+GetAllDestinationsGlobal+"/"+GetAllDestination;
				ExtentReport.info("The PrometheusMetrics url is :"+urlGetAllDestination);
				getJSON = Get(urlGetAllDestination,token);
				break;
			case "GreetingLogwithNoClientId":			
				String GreetingLogwithNoClientIdurl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String GreetingLogwithNoClientId = jsonObject.getAsString("CLIENTS");					
				String GreetingLogwithNoClientIdGreeting = jsonObject.getAsString("GREETING_LOGS");
				String urlGreetingLogwithNoClientId = base_url +"/"+GreetingLogwithNoClientIdurl+"/"+GreetingLogwithNoClientId+"/"+GreetingLogwithNoClientIdGreeting;
				ExtentReport.info("The InitiateService url is :"+urlGreetingLogwithNoClientId);
				getJSON = Get(urlGreetingLogwithNoClientId,token);
				break;
			case "UserMesgToClientwithWrongClientIdGreet":			
				String UserMesgToClientwithWrongClientIdGreeturl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String UserMesgToClientwithWrongClientIdGreetId = jsonObject.getAsString("CLIENTS");
				String UserMesgToClientwithWrongClientIdGreetID = jsonObject.getAsString("WRONGGREETINGID");				
				String UserMesgToClientwithWrongClientIdGreet = jsonObject.getAsString("GREETING_LOGS");
				String UserMesgToClientwithWrongClientIdGreeting = jsonObject.getAsString("WRONGCLIENTGREETINGID");
				String urlUserMesgToClientwithWrongClientIdGreet = base_url +"/"+UserMesgToClientwithWrongClientIdGreeturl+"/"+UserMesgToClientwithWrongClientIdGreetId+"/"+UserMesgToClientwithWrongClientIdGreetID+"/"+UserMesgToClientwithWrongClientIdGreet+"/"+UserMesgToClientwithWrongClientIdGreeting;
				ExtentReport.info("The InitiateService url is :"+urlUserMesgToClientwithWrongClientIdGreet);
				getJSON = Get(urlUserMesgToClientwithWrongClientIdGreet,token);
				break;
			case "UserClientDetails":			
				String UserClientDetailsurl = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String UserClientDetailsId = jsonObject.getAsString("CLIENTS");					
				String urlUserClientDetails = base_url +"/"+UserClientDetailsurl+"/"+UserClientDetailsId;
				ExtentReport.info("The InitiateService url is :"+urlUserClientDetails);
				getJSON = Get(urlUserClientDetails,token);
				break;
			case "Fleet":
				String FleetUrl = jsonObject.getAsString("FLEET_URL");
				String ClientID = jsonObject.getAsString("CLIENTID");
				String FLEETS = jsonObject.getAsString("FLEETS");
				String urlFleet = base_url +"/"+ClientID+"/"+FleetUrl+"/"+FLEETS;
				ExtentReport.info("The fleet url is :"+urlFleet);
				getJSON =  Get(urlFleet,token);
				break;
			case "GetServiceDescriptor":
				String GetServiceDescriptorurl = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String services=jsonObject.getAsString("SERVICES");
				String urlToGetServiceDescriptor = base_url +"/"+GetServiceDescriptorurl+"/"+services;
				ExtentReport.info("The InitiateService url is :"+urlToGetServiceDescriptor);
				getJSON = Get(urlToGetServiceDescriptor,token);
				break;
			case "GetUsersWithRoleCodeUsingNewAPI":
				String UserClientID = jsonObject.getAsString("CLIENTID");
				String AUTH_PROVIDER_SERV = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String USERS_ROLECODE = jsonObject.getAsString("USERS_ROLECODE");
				String ROLECODE1 = jsonObject.getAsString("ROLECODE_1");
				String urlToGetUsers = base_url +"/"+UserClientID+"/"+AUTH_PROVIDER_SERV+"/"+USERS_ROLECODE+"/"+ROLECODE1;
				ExtentReport.info("The InitiateService url is :"+urlToGetUsers);
				getJSON = Get(urlToGetUsers,token);
				break;
			case "CurrentClientContext":				 
				String CurrentContextIDURL = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String CurrentContextClient = jsonObject.getAsString("CLIENTS");
				String CurrentContextClientID=jsonObject.getAsString("CLIENT1_ID");
				String CurrentContext = jsonObject.getAsString("CURRENT_CLIENT_CONTEXT");
				String urlCurrentContext = base_url +"/"+CurrentContextIDURL+"/"+CurrentContextClient+"/"+CurrentContextClientID+"/"+CurrentContext;
				ExtentReport.info("The ClientProperty url is :"+urlCurrentContext);
				getJSON = Get(urlCurrentContext,token);
				break;
			case "CurrentClientContextWithCliendId":			
				String CurrentClientContextWithCliendIdurl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String CurrentClientContextWithCliendId = jsonObject.getAsString("CLIENT");
				String CurrentClientContextWithCliendIdID = jsonObject.getAsString("CLIENT1_ID");				
				String CurrentClientContextID = jsonObject.getAsString("CURRENT_CLIENT");
				String urlCurrentClientContextWithCliendId = base_url +"/"+CurrentClientContextWithCliendIdurl+"/"+CurrentClientContextWithCliendId+"/"+CurrentClientContextWithCliendIdID+"/"+CurrentClientContextID;
				ExtentReport.info("The InitiateService url is :"+urlCurrentClientContextWithCliendId);
				getJSON = Get(urlCurrentClientContextWithCliendId,token);
				break;
			case "CurrentClientIDWithClientId":			
				String CurrentClientIdurl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String CurrentClientId = jsonObject.getAsString("CLIENT");
				String CurrentID = jsonObject.getAsString("CLIENT1_ID");				
				String CurrentClientID = jsonObject.getAsString("CURRENT_CLIENT_ID");
				String urlCurrentClientCliendId = base_url +"/"+CurrentClientIdurl+"/"+CurrentClientId+"/"+CurrentID+"/"+CurrentClientID;
				ExtentReport.info("The InitiateService url is :"+urlCurrentClientCliendId);
				getJSON = Get(urlCurrentClientCliendId,token);
				break;
			case "CurrentContextClient":				 
				String CurrentContextURL = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String CurrentContextClientId = jsonObject.getAsString("CLIENTS");
				String CurrentContextID=jsonObject.getAsString("CLIENT1_ID");
				String CurrentContextDetail = jsonObject.getAsString("CURRENT_CONTEXTS");
				String urlCurrentContextID = base_url +"/"+CurrentContextURL+"/"+CurrentContextClientId+"/"+CurrentContextID+"/"+CurrentContextDetail;
				ExtentReport.info("The ClientProperty url is :"+urlCurrentContextID);
				getJSON = Get(urlCurrentContextID,token);
				break;
			case "CurrentRequestContextClient":				 
				String CurrentRequestURL = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String CurrentRequestClientId = jsonObject.getAsString("CLIENTS");
				String CurrentRequestID=jsonObject.getAsString("CLIENT1_ID");
				String CurrentRequestDetail = jsonObject.getAsString("CURRENT_REQUEST_CONTEXT");
				String urlCurrentRequestID = base_url +"/"+CurrentRequestURL+"/"+CurrentRequestClientId+"/"+CurrentRequestID+"/"+CurrentRequestDetail;
				ExtentReport.info("The ClientProperty url is :"+urlCurrentRequestID);
				getJSON = Get(urlCurrentRequestID,token);
				break;
			case "GetAppsUsingNewAPI":
				String APPClientID = jsonObject.getAsString("CLIENTID");
				String AUTH_PROVIDER_SER = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String APPLICATIONS = jsonObject.getAsString("APPLICATIONS");
				String urlToGetApps = base_url +"/"+APPClientID+"/"+AUTH_PROVIDER_SER+"/"+APPLICATIONS;
				ExtentReport.info("The InitiateService url is :"+urlToGetApps);
				getJSON = Get(urlToGetApps,token);
				break;
			case "GetSpecificAppDetailsUsingNewAPI":
				String SpecificAPPClientID = jsonObject.getAsString("CLIENTID");
				String SpecificApp_AUTH_PROVIDER_SER = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String SpecificApp_APPLICATIONS = jsonObject.getAsString("APPLICATIONS");
				String APP_ID = jsonObject.getAsString("APP_ID");
				String urlToGetSpecificAppDetails = base_url +"/"+SpecificAPPClientID+"/"+SpecificApp_AUTH_PROVIDER_SER+"/"+SpecificApp_APPLICATIONS+"/"+APP_ID;
				ExtentReport.info("The InitiateService url is :"+urlToGetSpecificAppDetails);
				getJSON = Get(urlToGetSpecificAppDetails,token);
				break;
			case "GetSpecificAppDetailsWithWrongAppID":
				String SpecificAPPWithWrongID = jsonObject.getAsString("CLIENTID");
				String SpecificApp_APS = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String SpecificApp_APP = jsonObject.getAsString("APPLICATIONS");
				String WRONGAPP_ID = jsonObject.getAsString("WRONG_APP_ID");
				String urlToGetSpecificAppDetailsWithWrongID = base_url +"/"+SpecificAPPWithWrongID+"/"+SpecificApp_APS+"/"+SpecificApp_APP+"/"+WRONGAPP_ID;
				ExtentReport.info("The InitiateService url is :"+urlToGetSpecificAppDetailsWithWrongID);
				getJSON = Get(urlToGetSpecificAppDetailsWithWrongID,token);
				break;
			case "GetSpecificAppDetailsWithAppID":
				String SpecificAPPWithAppID = jsonObject.getAsString("CLIENTID");
				String SpecificAppWithID_APS = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String SpecificAppWithID_APP = jsonObject.getAsString("APPLICATIONS");
				String Specific_APP_ID = jsonObject.getAsString("APP_ID_NEW");
				String CORRECT_APP_ID = jsonObject.getAsString("CORRECT_APP_ID");
				String urlToGetSpecificAppDetailsWithID = base_url +"/"+SpecificAPPWithAppID+"/"+SpecificAppWithID_APS+"/"+SpecificAppWithID_APP+"/"+Specific_APP_ID+"/"+CORRECT_APP_ID;
				ExtentReport.info("The InitiateService url is :"+urlToGetSpecificAppDetailsWithID);
				getJSON = Get(urlToGetSpecificAppDetailsWithID,token);
				break;
			case "InvalidAppIdToApplicationApi":
				String InvalidAppIdToApplicationApiClient = jsonObject.getAsString("CLIENTID");
				String InvalidAppIdToApplicationApiAuthorization = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String InvalidAppIdToApplicationApiApplications = jsonObject.getAsString("APPLICATIONS");		
				String InvalidAppIdToApplicationApiAppId = jsonObject.getAsString("WRONGAPPID");
				String urlInvalidAppIdToApplicationApi = base_url +"/"+InvalidAppIdToApplicationApiClient+"/"+InvalidAppIdToApplicationApiAuthorization+"/"+InvalidAppIdToApplicationApiApplications+"/"+InvalidAppIdToApplicationApiAppId;
				ExtentReport.info("The Users url is :"+urlInvalidAppIdToApplicationApi);
				getJSON =  Get(urlInvalidAppIdToApplicationApi,token);
				break;
			case "NoAppIdToApplicationApiDetails":
				String AppIdToApplicationApiDetailsClient = jsonObject.getAsString("CLIENTID");
				String AppIdToApplicationApiDetailsAuthorization = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String AppIdToApplicationApiDetailsApplication = jsonObject.getAsString("APPLICATIONS");		
				String AppIdToApplicationApiDetailsAppId = jsonObject.getAsString("APPID");
				String urlAppIdToApplicationApiDetails = base_url +"/"+AppIdToApplicationApiDetailsClient+"/"+AppIdToApplicationApiDetailsAuthorization+"/"+AppIdToApplicationApiDetailsApplication+"/"+AppIdToApplicationApiDetailsAppId;
				ExtentReport.info("The Users url is :"+urlAppIdToApplicationApiDetails);
				getJSON =  Get(urlAppIdToApplicationApiDetails,token);
				break;
			case "AppIdToApplicationApi":
				String AppIdToApplicationApiClient = jsonObject.getAsString("CLIENTID");
				String AppIdToApplicationApiAuthorization = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String AppIdToApplicationApiApplication = jsonObject.getAsString("APPLICATIONS");		
				String AppIdToApplicationApiIdExt = jsonObject.getAsString("IDEXT");
				String AppIdToApplicationApiIdExtID = jsonObject.getAsString("IDEXTID");
				String urlAppIdToApplicationApi = base_url +"/"+AppIdToApplicationApiClient+"/"+AppIdToApplicationApiAuthorization+"/"+AppIdToApplicationApiApplication+"/"+AppIdToApplicationApiIdExt+"/"+AppIdToApplicationApiIdExtID;
				ExtentReport.info("The Users url is :"+urlAppIdToApplicationApi);
				getJSON =  Get(urlAppIdToApplicationApi,token);
				break;
			case "InvalidIdExtApplicationApi":
				String InvalidIdExtApplicationApiClient = jsonObject.getAsString("CLIENTID");
				String InvalidIdExtApplicationApiAuthorization = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String InvalidIdExtApplicationApiApplication = jsonObject.getAsString("APPLICATIONS");		
				String InvalidIdExtApplicationApiIdExt = jsonObject.getAsString("IDEXT");
				String InvalidIdExtApplicationApiIdExtid = jsonObject.getAsString("WRONGIDEXTID");
				String urlInvalidIdExtApplicationApi = base_url +"/"+InvalidIdExtApplicationApiClient+"/"+InvalidIdExtApplicationApiAuthorization+"/"+InvalidIdExtApplicationApiApplication+"/"+InvalidIdExtApplicationApiIdExt+"/"+InvalidIdExtApplicationApiIdExtid;
				ExtentReport.info("The Users url is :"+urlInvalidIdExtApplicationApi);
				getJSON =  Get(urlInvalidIdExtApplicationApi,token);
				break;
			case "GetClientGroups":
				String GroupsClientID = jsonObject.getAsString("CLIENTID");
				String ClientAuthorization = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String ClientGroups = jsonObject.getAsString("GROUPS");
				String urlClientGroup = base_url +"/"+GroupsClientID+"/"+ClientAuthorization+"/"+ClientGroups+"/";
				ExtentReport.info("The Users url is :"+urlClientGroup);
				getJSON =  Get(urlClientGroup,token);
				break;
			case "GetClientRoles":
				String ClientRole = jsonObject.getAsString("CLIENTID");
				String APSROle = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String Role = jsonObject.getAsString("ROLES");
				String urlClientRole = base_url +"/"+ClientRole+"/"+APSROle+"/"+Role;
				ExtentReport.info("The Users url is :"+urlClientRole);
				getJSON =  Get(urlClientRole,token);
				break;
			case "getAvailablePrivilage":
				String ClientPrivilege = jsonObject.getAsString("CLIENTID");
				String APSprivilage = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String Privilege = jsonObject.getAsString("PRIVILEGES");
				String urlClientPrivilege = base_url +"/"+ClientPrivilege+"/"+APSprivilage+"/"+Privilege;
				ExtentReport.info("The Users url is :"+urlClientPrivilege);
				getJSON =  Get(urlClientPrivilege,token);
				break;
			case "GetGreetingLog":			
				String GreetingLogClientIdurl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String GreetingLogClient = jsonObject.getAsString("CLIENTS");	
				String GreetingLogClientId = jsonObject.getAsString("CLIENTID");
				String GreetingLogwithGreeting = jsonObject.getAsString("GREETING_LOGS");
				String urlGreetingLogClientId = base_url +"/"+GreetingLogClientIdurl+"/"+GreetingLogClient+"/"+GreetingLogClientId+"/"+GreetingLogwithGreeting;
				ExtentReport.info("The InitiateService url is :"+urlGreetingLogClientId);
				getJSON = Get(urlGreetingLogClientId,token);
				break;


			}
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating GetServices request for the service "+servicename+" : "+e.getMessage());
		}

		return getJSON;
	}


	/*
	 * ****************************************************************************** 
	 * Name : GetServices 
	 * Parameters : servicename(name of the service),serviceId(service id)
	 * Purpose : For getting the list of specified service as json
	 * ******************************************************************************
	 */

	public Response GetServices(String servicename ,String serviceId) {

		String base_url = null;
		String token = null;
		Response getJSON = null;
		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);
		try {
			switch(servicename) {
			case "device":
				String DevicesUrl = jsonObject.getAsString("DEVICE_URL");
				String Devices = jsonObject.getAsString("DEVICES");
				String urlDevice = base_url +"/"+DevicesUrl+"/"+Devices+"/"+serviceId;
				getJSON = Get(urlDevice,token);
				break;
			case "asset":
				String AssetsUrl = jsonObject.getAsString("ASSET_URL");
				String Assets = jsonObject.getAsString("ASSETS");
				String urlAsset = base_url +"/"+AssetsUrl+"/"+Assets+"/"+serviceId;
				ExtentReport.info("The asset url is :"+urlAsset);
				getJSON = Get(urlAsset,token);
				break;
			case "chargingstation":
				String ChargingStationUrl = jsonObject.getAsString("ASSET_URL");
				String ChargingStation = jsonObject.getAsString("CHARGINGSTATIONS");
				String urlChargingStation = base_url +"/"+ChargingStationUrl+"/"+ChargingStation+"/"+serviceId;
				ExtentReport.info("The Charging Station url is :"+urlChargingStation);
				getJSON = Get(urlChargingStation,token);
				break;
			case "vehicle":
				String VehiclesUrl = jsonObject.getAsString("VEHICLE_URL");
				String Vehicles = jsonObject.getAsString("VEHICLES");
				String urlVehicle = base_url +"/"+VehiclesUrl+"/"+Vehicles+"/"+serviceId;
				ExtentReport.info("The vehicle url is :"+urlVehicle);
				sleep(30000);
				getJSON = Get(urlVehicle,token);
				break;
			case "client":
				String ClientsUrl = jsonObject.getAsString("CLIENT_URL");
				String Clients = jsonObject.getAsString("CLIENTS");;
				String urlClient = base_url +"/"+ClientsUrl+"/"+Clients+"/"+serviceId;
				ExtentReport.info("The asset url is :"+urlClient);
				getJSON = Get(urlClient,token);
				break;	
			case "user":
				String ClientUrl = jsonObject.getAsString("CLIENT_URL");
				String Users = jsonObject.getAsString("USERS");
				String urlUser = base_url +"/"+ClientUrl+"/"+Users+"/"+serviceId;
				ExtentReport.info("The Users url is :"+urlUser);
				getJSON =  Get(urlUser,token);
				break;
			case "clientservice":
				String ClientServiceUrl = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String urlClientService = base_url +"/"+ClientServiceUrl+serviceId;
				ExtentReport.info("The Users url is :"+urlClientService);
				getJSON =  Get(urlClientService,token);
				break;
			case "getSpecificPropulsionTypeDetails":

				String AssetUrl = jsonObject.getAsString("ASSET_URL");
				String PropulsionType = jsonObject.getAsString("PROPULSIONTYPE");
				String urlPropulsionType = base_url +"/"+AssetUrl+"/"+PropulsionType+"/"+serviceId;
				ExtentReport.info("The InitiateService url is :"+urlPropulsionType);
				getJSON = Get(urlPropulsionType,token);
				break;
			case "vehicleTypeOne":
				String VehicleTypesOneUrl = jsonObject.getAsString("VEHICLE_URL");
				String VehicleTypesOne = jsonObject.getAsString("VEHICLETYPE");
				String urlVehicleTypesOne = base_url +"/"+VehicleTypesOneUrl+"/"+VehicleTypesOne+"/"+serviceId;
				ExtentReport.info("The vehicle url is :"+urlVehicleTypesOne);
				getJSON = Get(urlVehicleTypesOne,token);
				break;
			case "sagastatussuccess":
				String SagaClient = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String urlSagaService = base_url +"/"+SagaClient+serviceId;
				ExtentReport.info("The InitiateService url is :"+urlSagaService);
				getJSON = Get(urlSagaService,token);
				break;
			case "VehicleType":
				String VehicleTypesUrl = jsonObject.getAsString("VEHICLE_URL");
				String VehicleTypes = jsonObject.getAsString("VEHICLETYPE");
				String urlVehicleTypes = base_url +"/"+VehicleTypesUrl+"/"+VehicleTypes+"/"+serviceId;
				ExtentReport.info("The vehicle url is :"+urlVehicleTypes);
				getJSON = Get(urlVehicleTypes,token);
				break;
			case "sagastatusfail":
				SagaClient = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String urlSagaService1 = base_url +"/"+SagaClient+serviceId;
				ExtentReport.info("The InitiateService url is :"+urlSagaService1);
				Thread.sleep(4000);
				getJSON = Get(urlSagaService1,token);
				break;
			case "SagaEntityToValidateTheCUSConfig":
				SagaClient = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				urlSagaService = base_url +"/"+SagaClient+serviceId;
				ExtentReport.info("The InitiateService url is :"+urlSagaService);
				getJSON = Get(urlSagaService,token);
				break;
			case "GetConfigPropertyForCUS":
				String CUSUpdateClientManagement = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String ClientId = jsonObject.getAsString("CLIENTID");
				String Client = jsonObject.getAsString("CLIENT");
				String False= jsonObject.getAsString("FALSE");
				String ServiceDescriptorID= jsonObject.getAsString("SERVICEDESCRIPTORID");
				String urlInitiateCUSUpdate = base_url +"/"+CUSUpdateClientManagement+"/"+"config"+"/"+Client+"/"+ClientId+"?"+"reconfigure="+False+"&"+"serviceDescriptorId="+ServiceDescriptorID;
				ExtentReport.info("The InitiateService url is :"+urlInitiateCUSUpdate);
				getJSON = Get(urlInitiateCUSUpdate,token);
				break;
			case "GetSagaEntity":
				String ServiceClientManagementurl = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String urlToGetSagaStatus = base_url +"/"+ServiceClientManagementurl+serviceId;
				ExtentReport.info("The InitiateService url is :"+urlToGetSagaStatus);
				getJSON = Get(urlToGetSagaStatus,token);
				break;
			case "GetServiceDescriptor":
				String GetServiceDescriptorurl = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String services=jsonObject.getAsString("SERVICES");
				String urlToGetServiceDescriptor = base_url +"/"+GetServiceDescriptorurl+"/"+services+"/"+serviceId;
				ExtentReport.info("The InitiateService url is :"+urlToGetServiceDescriptor);
				getJSON = Get(urlToGetServiceDescriptor,token);
				break;
			case "GetWrongClientIDPropertyDetails":
				String GetWrongClientIDPropertyurl = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String GetWrongClientIDPropertyClientId = jsonObject.getAsString("CLIENT_WRONGID");
				String GetWrongClientIDPropertyConfig = jsonObject.getAsString("CONFIG");
				String GetWrongClientIDPropertyClient = jsonObject.getAsString("CLIENTS");
				String urlGetWrongClientIDPropertyClient = base_url +"/"+GetWrongClientIDPropertyurl+"/"+GetWrongClientIDPropertyConfig+"/"+GetWrongClientIDPropertyClient+"/"+GetWrongClientIDPropertyClientId;
				ExtentReport.info("The InitiateService url is :"+urlGetWrongClientIDPropertyClient);
				getJSON = Get(urlGetWrongClientIDPropertyClient,token,servicename,serviceId);
				break;
			case "ClientContext":
				String MultiClientSampleService = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				Clients = jsonObject.getAsString("CLIENTS");
				String ClientID = jsonObject.getAsString("CLIENT2_ID");
				String urlInitiateClientContext = base_url +"/"+MultiClientSampleService+"/"+Clients+"/"+ClientID+"/"+serviceId;
				ExtentReport.info("The Client Context url is :"+urlInitiateClientContext);
				getJSON = Get(urlInitiateClientContext,token);
				break;
			case "GreetingslogsClient2":
				String Greetingslogs1 = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String ClientDetails = jsonObject.getAsString("CLIENTS");
				String Client1ID = jsonObject.getAsString("CLIENT2_ID");
				String urlInitiateClientgreetinglogs = base_url +"/"+Greetingslogs1+"/"+ClientDetails+"/"+Client1ID+"/"+"greeting-logs"+"/"+serviceId;
				ExtentReport.info("The Client Context url is :"+urlInitiateClientgreetinglogs);
				getJSON = Get(urlInitiateClientgreetinglogs,token);
				break;
			case "GreetingslogsClient1":
				String Greetingslogs2 = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String ClientDetails2 = jsonObject.getAsString("CLIENTS");
				String Client2ID = jsonObject.getAsString("CLIENT1_ID");
				String urlInitiateClientgreetinglogs2 = base_url +"/"+Greetingslogs2+"/"+ClientDetails2+"/"+Client2ID+"/"+"greeting-logs"+"/"+serviceId;
				ExtentReport.info("The Client Context url is :"+urlInitiateClientgreetinglogs2);
				getJSON = Get(urlInitiateClientgreetinglogs2,token);
				break;
			case "destinationUsingRestAPI":
				String messageDestinationClientService = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String urlmessageDestinationClientService = base_url +"/"+messageDestinationClientService+"/"+"destinations/"+serviceId;
				ExtentReport.info("The Message or Event destination  url is :"+urlmessageDestinationClientService);
				getJSON = Get(urlmessageDestinationClientService,token);
				break;
			case "GetserviceDescriptor":
				String CreateServiceDescriptor = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String Services = jsonObject.getAsString("SERVICES");
				//				String ServiceDescriptorID=jsonObject.getAsString("SERVICEDESCRIPTORID");
				String urlCreateServiceDescriptor = base_url +"/"+CreateServiceDescriptor+"/"+Services+"/"+serviceId;
				ExtentReport.info("The InitiateService url is :"+urlCreateServiceDescriptor);
				getJSON = Get(urlCreateServiceDescriptor,token);
				break;
			case "GetSDIDOfTheUser":
				String SDIDOfTheUserUrl = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String Client1Details = jsonObject.getAsString("CLIENTS");
				String ClientIDs = jsonObject.getAsString("CLIENT1_ID");
				String SDIDOfTheUserService = jsonObject.getAsString("SERVICE");
				String urlInitiateSDIDOfTheUser = base_url +"/"+SDIDOfTheUserUrl+"/"+Client1Details+"/"+ClientIDs+"/"+SDIDOfTheUserService+"/"+serviceId;
				ExtentReport.info("The CUS Property url is :"+urlInitiateSDIDOfTheUser);
				getJSON = Get(urlInitiateSDIDOfTheUser,token);
				break;
			case "Fleet":
				String FleetUrl = jsonObject.getAsString("FLEET_URL");
				ClientID = jsonObject.getAsString("CLIENTID");
				String FLEETS = jsonObject.getAsString("FLEETS");
				String urlFleet = base_url +"/"+ClientID+"/"+FleetUrl+"/"+FLEETS+"/"+serviceId;
				ExtentReport.info("The fleet url is :"+urlFleet);
				getJSON =  Get(urlFleet,token);
				break;
			case "GlobalGreetings":
				String GlobalService = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String Global = jsonObject.getAsString("GLOBAL");
				String GreetingLog = jsonObject.getAsString("GREETING_LOGS");
				String urlInitiateGlobalGreeting = base_url +"/"+GlobalService+"/"+Global+"/"+GreetingLog+"/"+serviceId;
				ExtentReport.info("The InitiateService url is :"+urlInitiateGlobalGreeting);
				getJSON = Get(urlInitiateGlobalGreeting,token);
				break;
			case "GetGreetingsToClient":
				String GetClientGreeting = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String GetClients = jsonObject.getAsString("CLIENT");
				//				String ClientID = jsonObject.getAsString("CLIENT1_ID");
				String urlInitiateGetGreetingsService = base_url +"/"+GetClientGreeting+"/"+GetClients+"/"+serviceId+"/greet";
				ExtentReport.info("The InitiateService url is :"+urlInitiateGetGreetingsService);
				getJSON =  Get(urlInitiateGetGreetingsService,token);
				break;
			case "proxyCurrentContext":
				String proxyCurrentContext = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String proxyClients = jsonObject.getAsString("CLIENT");
				String proxy = jsonObject.getAsString("PROXY");
				String urlInitiateproxyContext = base_url +"/"+proxyCurrentContext+"/"+proxyClients+"/"+serviceId+"/"+proxy+"/"+"current-contexts";
				ExtentReport.info("The InitiateService url is :"+urlInitiateproxyContext);
				getJSON = Get(urlInitiateproxyContext,token);
				break;
			case "GetWrongClientIdDetails":			
				String GetCurrentWrongClientIDurl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String GetCurrentWrongClientID = jsonObject.getAsString("CLIENT");
				String GetCurrentWrongClientIDId = jsonObject.getAsString("CLIENT_WRONGID");
				String GetCurrentWrongClientName = jsonObject.getAsString("CURRENTCLIENT");
				String urlGetCurrentWrongClient = base_url +"/"+GetCurrentWrongClientIDurl+"/"+GetCurrentWrongClientID+"/"+GetCurrentWrongClientIDId+"/"+GetCurrentWrongClientName;
				ExtentReport.info("The InitiateService url is :"+urlGetCurrentWrongClient);
				getJSON = Get(urlGetCurrentWrongClient,token);
				break;
			case "GetOneClientDetails":
				String IClientUrl = jsonObject.getAsString("CLIENT_URL");
				String IClients = jsonObject.getAsString("CLIENTS");
				String urlICLients = base_url +"/"+IClientUrl+"/"+IClients+"/"+serviceId;
				ExtentReport.info("The Users url is :"+urlICLients);
				getJSON =  Get(urlICLients,token);
				break;
			case "GetClientDetailsOfApplication":
				String ClientDetailsOfAppUrl = jsonObject.getAsString("CLIENT_URL");
				String Applications = jsonObject.getAsString("APPLICATIONS");
				String AppClients = jsonObject.getAsString("CLIENTS");
				String urlApplicationCLients = base_url +"/"+ClientDetailsOfAppUrl+"/"+Applications+"/"+serviceId+"/"+AppClients;
				ExtentReport.info("The Users url is :"+urlApplicationCLients);
				getJSON =  Get(urlApplicationCLients,token);
				break;
			case "UserClient":
				ClientUrl = jsonObject.getAsString("CLIENT_URL");
				Users = jsonObject.getAsString("USERS");
				Clients = jsonObject.getAsString("CLIENT");
				urlUser = base_url +"/"+ClientUrl+"/"+Users+"/"+serviceId+"/"+Clients;
				ExtentReport.info("The Users url is :"+urlUser);
				getJSON =  Get(urlUser,token);
				break;
			case "getDestinationWithEnumValues":			
				String test="global/service-management-service-multiclient/destinations";
				String urlgetDestinationWithEnumValues = base_url +"/"+test;
				ExtentReport.info("The InitiateService url is :"+urlgetDestinationWithEnumValues);
				getJSON = Get(urlgetDestinationWithEnumValues,token,servicename,serviceId);
				break;
			case "UserGetTopicDestination":			
				String UserGetTopicDestinationurl = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String UserGetTopicDestinationId = jsonObject.getAsString("DESTINATIONS");					
				String urlUserGetTopicDestination = base_url +"/"+UserGetTopicDestinationurl+"/"+UserGetTopicDestinationId;
				ExtentReport.info("The InitiateService url is :"+urlUserGetTopicDestination);
				getJSON = Get(urlUserGetTopicDestination,token,servicename,serviceId);
				break;	
			case "getEventHubDestination":			
				String getEventHubDestinationurl = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String getEventHubDestinationId = jsonObject.getAsString("DESTINATIONS");	
				String urlgetEventHubDestination = base_url +"/"+getEventHubDestinationurl+"/"+getEventHubDestinationId;
				ExtentReport.info("The InitiateService url is :"+urlgetEventHubDestination);
				getJSON = Get(urlgetEventHubDestination,token,servicename,serviceId);
				break;	
			case "GetServDescWithCorrectUUID":
				String GetServDescWithCorrectUUIDCLIENTMGT = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String urlGetServDescWithCorrectUUID = base_url +"/"+GetServDescWithCorrectUUIDCLIENTMGT+"/"+serviceId;
				ExtentReport.info("The Queue Destination url is :"+urlGetServDescWithCorrectUUID);
				getJSON = Get(urlGetServDescWithCorrectUUID,token);
				break;
			case "GetValidateServDescWithCorrectUUID":
				String GetValServDescWithCorrectUUIDCLIENTMGT = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String GetValServDescWithCorrectUUIDServices = jsonObject.getAsString("SERVICES");
				String urlGetValServDescWithCorrectUUID = base_url +"/"+GetValServDescWithCorrectUUIDCLIENTMGT+"/"+GetValServDescWithCorrectUUIDServices+"/"+serviceId;
				ExtentReport.info("The Queue Destination url is :"+urlGetValServDescWithCorrectUUID);
				getJSON = Get(urlGetValServDescWithCorrectUUID,token);
				break;
			case "VerifyAssignedAsset":
				String AssignedAssetFleetUrl = jsonObject.getAsString("FLEET_URL");
				String AssignedAssetClientID = jsonObject.getAsString("CLIENTID");
				String AssignedAssetFLEETS = jsonObject.getAsString("FLEETS");
				String AssignedAsseturlFleet = base_url +"/"+AssignedAssetClientID+"/"+AssignedAssetFleetUrl+"/"+AssignedAssetFLEETS;
				ExtentReport.info("The fleet url is :"+AssignedAsseturlFleet);
				getJSON = Get(AssignedAsseturlFleet,token,servicename,serviceId);
				break;
			case "AssociatedAssetForFleet":
				String AssociateAssetFleetFleetUrl = jsonObject.getAsString("FLEET_URL");
				String AssociateAssetFleetClientID = jsonObject.getAsString("CLIENTID");
				String AssociateAssetFleetFLEETS = jsonObject.getAsString("FLEETS");
				String  AssociateAssetFleetAssets = jsonObject.getAsString("ASSETS");
				String AssociateAssetFleeturlFleet = base_url +"/"+AssociateAssetFleetClientID+"/"+AssociateAssetFleetFleetUrl+"/"+AssociateAssetFleetFLEETS+"/"+serviceId+"/"+AssociateAssetFleetAssets;
				ExtentReport.info("The fleet url is :"+AssociateAssetFleeturlFleet);
				getJSON = Get(AssociateAssetFleeturlFleet,token);
				break;
			case "GetCUSProperty":
				String CUSUClientManagement = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String CUSClientId = jsonObject.getAsString("CLIENTID");
				String CUSClient = jsonObject.getAsString("CLIENTS");
				String urlInitiateCUSProperty = base_url +"/"+CUSUClientManagement+"/"+"config"+"/"+CUSClient+"/"+CUSClientId;
				ExtentReport.info("The CUS Property url is :"+urlInitiateCUSProperty);
				getJSON = Get(urlInitiateCUSProperty,token,servicename,serviceId);
				break;
			case "GetConfigHierarchicalCUSProperty":
				String HierarchicalCUSUClientManagement = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String HierarchicalCUSClientId = jsonObject.getAsString("CLIENTID");
				String HierarchicalCUSClient = jsonObject.getAsString("CLIENTS");
				String HierarchicalCUS = jsonObject.getAsString("HIERARCHICAL");
				String HierarchicalConfig = jsonObject.getAsString("CONFIG");
				urlInitiateCUSProperty = base_url +"/"+HierarchicalCUSUClientManagement+"/"+HierarchicalConfig+"/"+HierarchicalCUSClient+"/"+HierarchicalCUSClientId+"/"+HierarchicalCUS;
				ExtentReport.info("The CUS Property url is :"+urlInitiateCUSProperty);
				getJSON = Get(urlInitiateCUSProperty,token,servicename,serviceId);
				break;
			case "GetUsersWithRoleCodeUsingNewAPI":
				String UserClientID = jsonObject.getAsString("CLIENTID");
				String AUTH_PROVIDER_SERV = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String USERS_ROLECODE = jsonObject.getAsString("USERS_ROLECODE");
				String urlToGetUsers = base_url +"/"+UserClientID+"/"+AUTH_PROVIDER_SERV+"/"+USERS_ROLECODE+"/"+serviceId;
				ExtentReport.info("The InitiateService url is :"+urlToGetUsers);
				getJSON = Get(urlToGetUsers,token);
				break;
			case "GetGreetingLogWithClientId":
				String GetGreetinglogUrl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				Clients = jsonObject.getAsString("CLIENT");
				ClientID= jsonObject.getAsString("CLIENTID");
				String GetGreetingLogs=jsonObject.getAsString("GREETING_LOGS");
				String GetGreetingurl = base_url +"/"+GetGreetinglogUrl+"/"+Clients+"/"+ClientID+"/"+GetGreetingLogs+"/"+serviceId;
				ExtentReport.info("The Registration url is :"+GetGreetingurl);
				getJSON = Get(GetGreetingurl,token);
				break;
			case "GetClientSpecificGroup":
				String ClientIDGroups = jsonObject.getAsString("CLIENTID");
				String Authorization = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String Groups = jsonObject.getAsString("GROUPS");
				String urlClientSpecificGroup = base_url +"/"+ClientIDGroups+"/"+Authorization+"/"+Groups+"/"+serviceId;
				ExtentReport.info("The Users url is :"+urlClientSpecificGroup);
				getJSON =  Get(urlClientSpecificGroup,token);
				break;
			case "GetClientInvalidGroup":
				String ClientIDInvalidGroups = jsonObject.getAsString("CLIENTID");
				String AuthorizationInvalid = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String InvalidGroups = jsonObject.getAsString("GROUPS");
				String urlClientSpecificInvalidGroup = base_url +"/"+ClientIDInvalidGroups+"/"+AuthorizationInvalid+"/"+InvalidGroups+"/"+serviceId;
				ExtentReport.info("The Users url is :"+urlClientSpecificInvalidGroup);
				getJSON =  Get(urlClientSpecificInvalidGroup,token);
				break;
			case "GetClientGroupIdExt":
				String ClientIDGroup = jsonObject.getAsString("CLIENTID");
				String APSClient = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String GroupDetail = jsonObject.getAsString("GROUPS");
				String GroupIdExt = jsonObject.getAsString("GROUPIDEXT");
				String urlClientGroupIdExt = base_url +"/"+ClientIDGroup+"/"+APSClient+"/"+GroupDetail+"/"+GroupIdExt+"/"+serviceId;
				ExtentReport.info("The Users url is :"+urlClientGroupIdExt);
				getJSON =  Get(urlClientGroupIdExt,token);
				break;
			case "GetClientGroupRole":
				String ClientRole = jsonObject.getAsString("CLIENTID");
				String APSClientrole = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String RoleGroupDetail = jsonObject.getAsString("GROUPS");
				String GroupRole = jsonObject.getAsString("ROLE");
				String urlClientGroupRole = base_url +"/"+ClientRole+"/"+APSClientrole+"/"+RoleGroupDetail+"/"+GroupRole+"/"+serviceId;
				ExtentReport.info("The Users url is :"+urlClientGroupRole);
				getJSON =  Get(urlClientGroupRole,token);
				break;
			case "GetClientRoleIdDetails":
				String ClientIDRole = jsonObject.getAsString("CLIENTID");
				String APSClientRoles = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String Role = jsonObject.getAsString("ROLES");
				String urlClientRole = base_url +"/"+ClientIDRole+"/"+APSClientRoles+"/"+Role+"/"+serviceId;
				ExtentReport.info("The Users url is :"+urlClientRole);
				getJSON =  Get(urlClientRole,token);
				break;
				
			}
		}catch(Exception e) {
			testFailed("An exception has occured while generating GetServices request for the service "+servicename+" : "+e.getMessage());
		}

		return getJSON;
	}


	/*
	 * ****************************************************************************** 
	 * Name : GetServices 
	 * Parameters : servicename(name of the service),serviceId(service id),servicedetails(Details of a service)
	 * Purpose : For getting the list of specified service as json
	 * ******************************************************************************
	 */
	public Response GetServices(String servicename ,String serviceId,String servicedetails) {

		String base_url = null;
		String token = null;
		Response getJSON = null;
		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);
		try {
			switch(servicename) {

			case "GetClientExtendedDetails":
				String IClientUrl = jsonObject.getAsString("CLIENT_URL");
				String IClients = jsonObject.getAsString("CLIENTS");
				String urlICLients = base_url +"/"+IClientUrl+"/"+IClients+"/"+serviceId;
				ExtentReport.info("The Users url is :"+urlICLients);
				getJSON = Get(urlICLients,token,servicename,servicedetails);
				break;
			case "GetApplicationExtendedDetails":
				String ApplicationClientUrl = jsonObject.getAsString("CLIENT_URL");
				String IApplications = jsonObject.getAsString("APPLICATIONS");
				String urlApplication = base_url +"/"+ApplicationClientUrl+"/"+IApplications+"/"+serviceId;
				ExtentReport.info("The Users url is :"+urlApplication);
				getJSON = Get(urlApplication,token,servicename,servicedetails);
				break;
			case "GetClientApplication":
				String ApplicationUrl = jsonObject.getAsString("CLIENT_URL");
				String Applications = jsonObject.getAsString("APPLICATIONS");
				String ApplicationClients = jsonObject.getAsString("CLIENTS");
				String urlApplicationClients = base_url +"/"+ApplicationUrl+"/"+Applications+"/"+serviceId+"/"+ApplicationClients+"/"+servicedetails;
				ExtentReport.info("The Users url is :"+urlApplicationClients);
				getJSON = Get(urlApplicationClients,token);
				break;
			}
		}catch(Exception e) {
			testFailed("An exception has occured while generating GetServices request for the service "+servicename+" : "+e.getMessage());
		}

		return getJSON;
	}


	public Response GetServices(String servicename ,String serviceId,String servicedetail1,String servicedetail2) {

		String base_url = null;
		String token = null;
		Response getJSON = null;
		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);
		try {
			switch(servicename) {

			case "GetUserGroupDetails":
				String IClientUrl = jsonObject.getAsString("CLIENT_URL");
				String IClients = jsonObject.getAsString("USERGROUPS");
				String urlICLients = base_url +"/"+IClientUrl+"/"+IClients+"/"+serviceId;
				ExtentReport.info("The Users url is :"+urlICLients);
				getJSON = Get(urlICLients,token,servicename,servicedetail1,servicedetail2);
				break;
			
			}
		}catch(Exception e) {
			testFailed("An exception has occured while generating GetServices request for the service "+servicename+" : "+e.getMessage());
		}

		return getJSON;
	}

	/*
	 * ****************************************************************************** 
	 * Name : CreateServicesJson 
	 * Parameters : servicename(name of the service),payloadTemplate(Structure of the json payload),payloadValues(values of the JsonObject keys)
	 * Purpose : For Creating Specified Service
	 * ******************************************************************************
	 */
	public Response CreateServicesJson(String servicename,String payloadtemplate,String payloadValues) {
		Response CreateServiceString = null;
		String base_url = null;
		String token = null;

		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);
		try {
			switch(servicename) {
			case "createservice":
				String CreateServiceUrl = EnvironmentManager.getCreateServiceUrl();
				String InitiateService = EnvironmentManager.getInitiateService();
				String urlInitiateService = base_url +"/"+CreateServiceUrl+"/"+InitiateService+"/";
				ExtentReport.info("The InitiateService url is :"+urlInitiateService);
				CreateServiceString =  PostJson(urlInitiateService,token,servicename,payloadtemplate,payloadValues);
				break;
			case "device":
				String DeviceUrl = EnvironmentManager.getDeviceUrl();
				String Devices = EnvironmentManager.getDevices();
				String urlDevice = base_url +"/"+DeviceUrl+"/"+Devices+"/";
				ExtentReport.info("The Users url is :"+urlDevice);
				CreateServiceString =  PostJson(urlDevice,token,servicename,payloadtemplate,payloadValues);
			}
			TestLogger.appInfo("The Createservice response status code for  "+ servicename +" is "+CreateServiceString.getStatusCode());
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating CreateServices request for the service "+servicename+" : "+e.getMessage());
		}

		return CreateServiceString;
	}

	/*
	 * ****************************************************************************** 
	 * Name : DeleteServices 
	 * Parameters : servicename(name of the service),id(service id)
	 * Purpose : For Deleting Specified Service
	 * ******************************************************************************
	 */
	public Response DeleteServices(String servicename,String id) {
		Response DeleteServiceString = null;
		String base_url = null;
		String token = null;
		jsonObj = new JsonReader();
		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);
		try {
			switch(servicename) {
			case "asset":
				String AssetsUrl = jsonObject.getAsString("ASSET_URL");
				String Assets = jsonObject.getAsString("ASSETS");
				String urlAssetDelete = base_url +"/"+AssetsUrl+"/"+Assets+"/"+id;
				ExtentReport.info("The asset url is :"+urlAssetDelete);
				DeleteServiceString =  Delete(urlAssetDelete,token);
				break;
			case "vehicle":
				String VehiclesUrl = jsonObject.getAsString("VEHICLE_URL");
				String Vehicles = jsonObject.getAsString("VEHICLETYPE");
				String urlVehicleDelete = base_url +"/"+VehiclesUrl+"/"+Vehicles+"/"+id;
				ExtentReport.info("The vehicle url is :"+urlVehicleDelete);
				DeleteServiceString =  Delete(urlVehicleDelete,token);
				break;
			case "user":
				String UsersUrl = jsonObject.getAsString("CLIENT_URL");
				String Users = jsonObject.getAsString("USERS");
				String urlUserDelete = base_url +"/"+UsersUrl+"/"+Users+"/"+id;
				ExtentReport.info("The User url is :"+urlUserDelete);
				DeleteServiceString =  Delete(urlUserDelete,token);
				break;
			case "chargingstation":
				String ChargingStationUrl = jsonObject.getAsString("ASSET_URL");
				String ChargingStation = jsonObject.getAsString("CHARGINGSTATIONS");
				String urlChargingStation = base_url +"/"+ChargingStationUrl+"/"+ChargingStation+"/"+id;
				ExtentReport.info("The Charging Station url is :"+urlChargingStation);
				DeleteServiceString = Delete(urlChargingStation,token);
				break;
			case "device":
				String DeviceUrl = jsonObject.getAsString("DEVICE_URL");
				String Devices = jsonObject.getAsString("DEVICES");
				String urlDeviceDelete = base_url +"/"+DeviceUrl+"/"+Devices+"/"+id;
				ExtentReport.info("The Device url is :"+urlDeviceDelete);
				DeleteServiceString =  Delete(urlDeviceDelete,token);
				break;
			case "propulsionType":
				String PropulsionUrl = jsonObject.getAsString("ASSET_URL");
				String Propulsion = jsonObject.getAsString("PROPULSIONTYPE");
				String urlDeletePropulsion = base_url +"/"+PropulsionUrl+"/"+Propulsion+"/"+id;
				ExtentReport.info("The Registration url is :"+urlDeletePropulsion);
				DeleteServiceString =  Delete(urlDeletePropulsion,token);	
				break;
			case "DeletePropertyClientConfigProperty":
				String DeletePropertyClientConfigUrl = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String DeletePropertyClientConfig = jsonObject.getAsString("CONFIG");
				String DeletePropertyClientConfigClient = jsonObject.getAsString("CLIENTS");
				String DeletePropertyClientConfigClientID = jsonObject.getAsString("CLIENTID");
				String urlDeletePropertyClientConfigProperty = base_url +"/"+DeletePropertyClientConfigUrl+"/"+DeletePropertyClientConfig+"/"+DeletePropertyClientConfigClient+"/"+DeletePropertyClientConfigClientID;
				ExtentReport.info("The Registration url is :"+urlDeletePropertyClientConfigProperty);
				DeleteServiceString =  Delete(urlDeletePropertyClientConfigProperty,token,servicename,id);	
				break;
			case "DeletePropertyCUSConfigProperty":
				String DeletePropertyCUSConfigUrl = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String DeletePropertyCUSConfig = jsonObject.getAsString("CONFIG");
				String DeletePropertyCUSConfigClient = jsonObject.getAsString("CLIENTS");
				String DeletePropertyCUSConfigClientID = jsonObject.getAsString("CLIENTID");
				String urlDeletePropertyCUSConfigProperty = base_url +"/"+DeletePropertyCUSConfigUrl+"/"+DeletePropertyCUSConfig+"/"+DeletePropertyCUSConfigClient+"/"+DeletePropertyCUSConfigClientID;
				ExtentReport.info("The Registration url is :"+urlDeletePropertyCUSConfigProperty);
				DeleteServiceString =  Delete(urlDeletePropertyCUSConfigProperty,token,servicename,id);	
				break;
			case "Fleet":
				String FleetUrl = jsonObject.getAsString("FLEET_URL");
				String ClientID = jsonObject.getAsString("CLIENTID");
				String FLEETS = jsonObject.getAsString("FLEETS");
				String urlFleet = base_url +"/"+ClientID+"/"+FleetUrl+"/"+FLEETS+"/"+id;
				ExtentReport.info("The fleet url is :"+urlFleet);
				DeleteServiceString =  Delete(urlFleet,token);
				break;
			case "GlobalGreetings":
				String GlobalService = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String Global = jsonObject.getAsString("GLOBAL");
				String GreetingLog = jsonObject.getAsString("GREETING_LOGS");
				String urlInitiateGlobalGreeting = base_url +"/"+GlobalService+"/"+Global+"/"+GreetingLog+"/"+id;
				ExtentReport.info("The InitiateService url is :"+urlInitiateGlobalGreeting);
				DeleteServiceString = Delete(urlInitiateGlobalGreeting,token);
				break;
			case "deleteSampleService":
				String sampleService = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String Client = jsonObject.getAsString("CLIENTS");
				String sampleServiceClientID = jsonObject.getAsString("CLIENTID");
				String sampleServicename = jsonObject.getAsString("SERVICE");
				String urlInitiatedeleteSampleService = base_url +"/"+sampleService+"/"+Client+"/"+sampleServiceClientID+"/"+sampleServicename+"/"+id;
				ExtentReport.info("The InitiateService url is :"+urlInitiatedeleteSampleService);
				DeleteServiceString = Delete(urlInitiatedeleteSampleService,token);
				break;
			case "DeleteGreetingWithCorrectClientWrongGreeting":
				String DeleteGreetingUrl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String Clients = jsonObject.getAsString("CLIENT");
				String ClientId= jsonObject.getAsString("CLIENTID")+getRandomNumber();
				String GreetingLogs=jsonObject.getAsString("GREETING_LOGS");
				String DeleteGreeting = base_url +"/"+DeleteGreetingUrl+"/"+Clients+"/"+ClientId+"/"+GreetingLogs+"/"+id;
				ExtentReport.info("The Registration url is :"+DeleteGreeting);
				DeleteServiceString =  Delete(DeleteGreeting,token);	
				break;
			case "DeleteUserGroup":
				String ClientUrl = jsonObject.getAsString("CLIENT_URL");
				String UserGroups = jsonObject.getAsString("USERGROUPS");
				String urlDeleteUserGroup = base_url +"/"+ClientUrl+"/"+UserGroups+"/"+id;
				ExtentReport.info("The Delete User Group url is :"+urlDeleteUserGroup);
				DeleteServiceString =  Delete(urlDeleteUserGroup,token);
				break;
			case "DeleteUser":
				ClientUrl = jsonObject.getAsString("CLIENT_URL");
				Users = jsonObject.getAsString("USERS");
				String urlDeleteUser = base_url +"/"+ClientUrl+"/"+Users+"/"+id;
				ExtentReport.info("The Delete User Group url is :"+urlDeleteUser);
				DeleteServiceString =  Delete(urlDeleteUser,token);
				break;
			case "UnAssignSDFromClient":
				String SDService = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String SDClient = jsonObject.getAsString("CLIENTS");
				String SDClientID = jsonObject.getAsString("CLIENTID");
				String SDServicename = jsonObject.getAsString("SERVICE");
				String urlInitiatedeleteSD = base_url +"/"+SDService+"/"+SDClient+"/"+SDClientID+"/"+SDServicename+"/"+id;
				ExtentReport.info("The InitiateService url is :"+urlInitiatedeleteSD);
				DeleteServiceString = Delete(urlInitiatedeleteSD,token);
				break;
			case "DeleteSpecificGreetingWithClientId":
				String DeleteGreetinglogUrl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				Clients = jsonObject.getAsString("CLIENT");
				ClientID= jsonObject.getAsString("CLIENTID");
				String DeleteGreetingLogs=jsonObject.getAsString("GREETING_LOGS");
				String DeleteGreetingurl = base_url +"/"+DeleteGreetinglogUrl+"/"+Clients+"/"+ClientID+"/"+DeleteGreetingLogs+"/"+id;
				ExtentReport.info("The Registration url is :"+DeleteGreetingurl);
				DeleteServiceString =  Delete(DeleteGreetingurl,token);	
				break;
			}
			info("The response status code after deleting the service  "+ servicename +" is "+DeleteServiceString.getStatusCode());
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while deleting the service "+servicename+" : "+e.getMessage());
		}
		return DeleteServiceString;
	}


	/*
	 * ****************************************************************************** 
	 * Name : CreateServices 
	 * Parameters : servicename(name of the service),payloadTemplate(Structure of the json payload),payloadValues(values of the JsonObject keys)
	 * Purpose : For Creating Specified Service
	 * ******************************************************************************
	 */
	public Response CreateServices(String servicename,String payloadTemplate,String payloadValues) {
		Response CreateServiceString = null;
		String base_url = null;
		String token = null;

		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);
		try {
			switch(servicename) {
			case "asset":
				String AssetsUrl = jsonObject.getAsString("ASSET_URL");
				String Assets = jsonObject.getAsString("ASSETS");
				String urlAsset = base_url +"/"+AssetsUrl+"/"+Assets;
				ExtentReport.info("The asset url is :"+urlAsset);
				CreateServiceString =  Post(urlAsset,token,servicename,payloadTemplate,payloadValues);
				break;
			case "user":
				String ClientUrl = jsonObject.getAsString("CLIENT_URL");
				String Users = jsonObject.getAsString("USERS");
				String urlUser = base_url +"/"+ClientUrl+"/"+Users+"/";
				ExtentReport.info("The Users url is :"+urlUser);
				CreateServiceString =  Post(urlUser,token,servicename,payloadTemplate,payloadValues);
				break;
			case "createservice":
				String CreateServiceUrl = jsonObject.getAsString("CREATESERVICE_URL");
				String InitiateService = jsonObject.getAsString("INITIATE_SERVICE");
				String urlInitiateService = base_url +"/"+CreateServiceUrl+"/"+InitiateService+"/";
				ExtentReport.info("The InitiateService url is :"+urlInitiateService);
				CreateServiceString =  Post(urlInitiateService,token,servicename,payloadTemplate,payloadValues);
				break;
			case "device":
				String DeviceUrl = jsonObject.getAsString("DEVICE_URL");
				String Devices = jsonObject.getAsString("DEVICES");
				String urlDevice = base_url +"/"+DeviceUrl+"/"+Devices+"/";
				ExtentReport.info("The Users url is :"+urlDevice);
				CreateServiceString =  Post(urlDevice,token,servicename,payloadTemplate,payloadValues);


			}
			info("The Createservice response status code for service name :  "+ servicename +" is "+CreateServiceString.getStatusCode());
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating CreateServices request for the service "+servicename+" : "+e.getMessage());
		}

		return CreateServiceString;
	}

	public Response DeleteServices(String servicename) {
		Response DeleteServiceString = null;

		String base_url = null;
		String token = null;
		jsonObj = new JsonReader();
		base_url = jsonObject.getAsString("BASE_URL");
		String tauri_authorization_url = null;
		tauri_authorization_url = getAuthorizationURL();

		try {
			switch(servicename) {
			case "DeletePropertyFromClientConfig":
				token = getClientServicetoken(tauri_authorization_url,"deletePropertiesOfClient");
				String PropertiesOfClientUrl = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String Config = jsonObject.getAsString("CONFIG");
				String Clients = jsonObject.getAsString("CLIENT");
				String ClientID = jsonObject.getAsString("CLIENTID");
				String False=jsonObject.getAsString("FALSE");
				String deletePropertiesOfClient = base_url +"/"+PropertiesOfClientUrl+"/"+Config+"/"+Clients+"/"+ClientID+"?reconfigure="+False;
				ExtentReport.info("The Registration url is :"+deletePropertiesOfClient);
				DeleteServiceString =  Delete(deletePropertiesOfClient,token);	
				break;
			case "DeleteGreeting":
				token = getMultiClientServicetoken(tauri_authorization_url,"DeleteGreeting");
				String DeleteGreetingUrl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				Clients = jsonObject.getAsString("CLIENT");
				ClientID= jsonObject.getAsString("CLIENTID")+getRandomNumber();
				String GreetingLogs=jsonObject.getAsString("GREETING_LOGS");
				String DeleteGreeting = base_url +"/"+DeleteGreetingUrl+"/"+Clients+"/"+ClientID+"/"+GreetingLogs;
				ExtentReport.info("The Registration url is :"+DeleteGreeting);
				DeleteServiceString =  Delete(DeleteGreeting,token);	
				break;
			case "NoClientDeleteGreeting":
				token = getMultiClientServicetoken(tauri_authorization_url,"DeleteGreeting");
				DeleteGreetingUrl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				Clients = jsonObject.getAsString("CLIENT");
				GreetingLogs=jsonObject.getAsString("GREETING_LOGS");
				DeleteGreeting = base_url +"/"+DeleteGreetingUrl+"/"+Clients+"/"+GreetingLogs;
				ExtentReport.info("The Registration url is :"+DeleteGreeting);
				DeleteServiceString =  Delete(DeleteGreeting,token);	
				break;
			case "DeleteNoGreetinId":
				token = getMultiClientServicetoken(tauri_authorization_url,"DeleteGreeting");
				String DeleteNoGreetingUrl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String Global=jsonObject.getAsString("GLOBAL");
				GreetingLogs = jsonObject.getAsString("GREETING_LOGS");
				String DeleteNoGreetingID = base_url +"/"+DeleteNoGreetingUrl+"/"+Global+"/"+GreetingLogs;
				ExtentReport.info("The Registration url is :"+DeleteNoGreetingID);
				DeleteServiceString =  Delete(DeleteNoGreetingID,token);	
				break;
			case "DeleteGreetingLogWithClientId":
				token = getMultiClientServicetoken(tauri_authorization_url,"DeleteGreetingLogWithClientId");
				String DeleteGreetinglogUrl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				Clients = jsonObject.getAsString("CLIENT");
				ClientID= jsonObject.getAsString("CLIENTID");
				String DeleteGreetingLogs=jsonObject.getAsString("GREETING_LOGS");
				String DeleteGreetingurl = base_url +"/"+DeleteGreetinglogUrl+"/"+Clients+"/"+ClientID+"/"+DeleteGreetingLogs;
				ExtentReport.info("The Registration url is :"+DeleteGreetingurl);
				DeleteServiceString =  Delete(DeleteGreetingurl,token);	
				break;
			}
			info("The response status code  after deleting the service : "+ servicename +" is "+DeleteServiceString.getStatusCode());
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while deleting the service "+servicename+" : "+e.getMessage());
		}
		return DeleteServiceString;
	}


	public Response DeleteServices(String servicename,String serviceId,String servicedetails ) {
		Response DeleteServiceString = null;

		String base_url = null;
		String token = null;
		jsonObj = new JsonReader();
		base_url = jsonObject.getAsString("BASE_URL");
		String tauri_authorization_url = null;
		tauri_authorization_url = getAuthorizationURL();

		try {
			switch(servicename) {
			case "UnassignApplication":
				token = getClienttoken(tauri_authorization_url,"UnassignApplication");
				String ApplicationUrl = jsonObject.getAsString("CLIENT_URL");
				String Applications = jsonObject.getAsString("APPLICATIONS");
				String ApplicationClients = jsonObject.getAsString("CLIENTS");
				String urlApplicationClients = base_url +"/"+ApplicationUrl+"/"+Applications+"/"+serviceId+"/"+ApplicationClients+"/"+servicedetails;
				ExtentReport.info("The Registration url is :"+urlApplicationClients);
				DeleteServiceString =  Delete(urlApplicationClients,token);	
				break;
			case "DeletePropertyCUSConfigProperty":
				token = getClientServicetoken(tauri_authorization_url,"DeletePropertyCUSConfigProperty");
				String DeletePropertyCUSConfigUrl = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String DeletePropertyCUSConfig = jsonObject.getAsString("CONFIG");
				String DeletePropertyCUSConfigClient = jsonObject.getAsString("CLIENTS");
				String DeletePropertyCUSConfigClientID = jsonObject.getAsString("CLIENTID");
				String urlDeletePropertyCUSConfigProperty = base_url +"/"+DeletePropertyCUSConfigUrl+"/"+DeletePropertyCUSConfig+"/"+DeletePropertyCUSConfigClient+"/"+DeletePropertyCUSConfigClientID;
				ExtentReport.info("The Registration url is :"+urlDeletePropertyCUSConfigProperty);
				DeleteServiceString =  Delete(urlDeletePropertyCUSConfigProperty,token,servicename,serviceId,servicedetails);	
				break;

			}
			info("The response status code  after deleting the service : "+ servicename +" is "+DeleteServiceString.getStatusCode());
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while deleting the service "+servicename+" : "+e.getMessage());
		}
		return DeleteServiceString;
	}

	/*
	 * ****************************************************************************** 
	 * Name : CreateServices 
	 * Parameters : servicename(name of the service),payloadValues(values of the JsonObject keys)
	 * Purpose : For Creating Specified Service
	 * ******************************************************************************
	 */

	public Response CreateServices(String servicename,JSONObject payloadValues) {
		Response CreateServiceString = null;
		String base_url = null;
		String token = null;

		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);

		try {
			switch(servicename) {			

			case "device":
				String DeviceUrl = jsonObject.getAsString("DEVICE_URL");
				String Devices = jsonObject.getAsString("DEVICES");
				String urlDevice = base_url +"/"+DeviceUrl+"/"+Devices+"/";
				ExtentReport.info("The Users url is :"+urlDevice);
				CreateServiceString =  Post(urlDevice,token,servicename,payloadValues);
			case "user":
				String ClientUrl = jsonObject.getAsString("CLIENT_URL");
				String Users = jsonObject.getAsString("USERS");
				String urlUser = base_url +"/"+ClientUrl+"/"+Users+"/";
				ExtentReport.info("The Users url is :"+urlUser);
				CreateServiceString =  Post(urlUser,token,servicename,payloadValues);
				break;	
			case "createservice":
				String CreateServiceUrl = jsonObject.getAsString("CREATESERVICE_URL");
				String InitiateService = jsonObject.getAsString("INITIATE_SERVICE");
				String urlInitiateService = base_url +"/"+CreateServiceUrl+"/"+InitiateService+"/";

				ExtentReport.info("The InitiateService url is :"+urlInitiateService);
				CreateServiceString =  Post(urlInitiateService,token,servicename,payloadValues);
				break;
			case "createversion":
				String CreateversionUrl = jsonObject.getAsString("CREATESERVICE_URL");
				String InitiateServiceVer = jsonObject.getAsString("INITIATE_SERVICE");
				String ServiceName = (String)payloadValues.get("servicename");
				payloadValues.remove("servicename");				
				String urlInitiateServicever = base_url +"/"+CreateversionUrl+"/"+InitiateServiceVer+"/"+ServiceName+"/versions";
				ExtentReport.info("The InitiateService url is :"+urlInitiateServicever);
				CreateServiceString =  Post(urlInitiateServicever,token,servicename,payloadValues);
				break;
			case "asset":
				String AssetsUrl = jsonObject.getAsString("ASSET_URL");
				String Assets = jsonObject.getAsString("ASSETS");
				String urlAsset = base_url +"/"+AssetsUrl+"/"+Assets;
				ExtentReport.info("The asset url is :"+urlAsset);
				CreateServiceString =  Post(urlAsset,token,servicename,payloadValues);
				break;
			case "vehicle":
				String VehicleUrl = jsonObject.getAsString("VEHICLE_URL");
				String Vehicles = jsonObject.getAsString("VEHICLES");
				String urlVehicle = base_url +"/"+VehicleUrl+"/"+Vehicles;
				ExtentReport.info("The vehicles url is :"+urlVehicle);
				CreateServiceString =  Post(urlVehicle,token,servicename,payloadValues);
				break;
			case "chargingstation":
				String ChargingStationsUrl = jsonObject.getAsString("ASSET_URL");
				String ChargingStations = jsonObject.getAsString("CHARGINGSTATIONS");
				String urlChargingStations = base_url +"/"+ChargingStationsUrl+"/"+ChargingStations;
				ExtentReport.info("The Charging Station url is :"+urlChargingStations);
				CreateServiceString =  Post(urlChargingStations,token,servicename,payloadValues);
				break;
			case "registration":
				String RegistrationUrl = jsonObject.getAsString("REGISTRATION_URL");
				String Registration = jsonObject.getAsString("REGISTRATION");
				String urlRegistration = base_url +"/"+RegistrationUrl+"/"+Registration;
				ExtentReport.info("The Registration url is :"+urlRegistration);
				CreateServiceString =  Post(urlRegistration,token,servicename,payloadValues);
				break;
			case "serviceCatalog":
				String AcceptRegistrationUrl = jsonObject.getAsString("SERVICECATALOG_URL");
				String AcceptRegistration = jsonObject.getAsString("REGISTRATION");
				String urlAcceptRegistration = base_url +"/"+AcceptRegistrationUrl+"/"+AcceptRegistration;
				ExtentReport.info("The Registration url is :"+urlAcceptRegistration);
				CreateServiceString =  Post(urlAcceptRegistration,token,servicename,payloadValues);
				break;
			case "propulsion":
				String CreatePropulsionUrl = jsonObject.getAsString("PROPULSION_URL");
				String CreatePropulsion = jsonObject.getAsString("PROPULSION");
				String urlCreatePropulsion = base_url +"/"+CreatePropulsionUrl+"/"+CreatePropulsion;
				ExtentReport.info("The CreatePropulsion url is :"+urlCreatePropulsion);
				CreateServiceString =  Post(urlCreatePropulsion,token,servicename,payloadValues);   
				break;
			case "sandbox":
				String sandboxUrl = jsonObject.getAsString("SERVICECATALOG_URL");
				String sandbox = jsonObject.getAsString("SANDBOXSUB");
				String urlSubscribeSandbox = base_url +"/"+sandboxUrl+"/"+sandbox;
				ExtentReport.info("The Registration url is :"+urlSubscribeSandbox);
				CreateServiceString =  Post(urlSubscribeSandbox,token,servicename,payloadValues);
				break;
			case "VehicleType":
				String VehicleTypesUrl = jsonObject.getAsString("VEHICLE_URL");
				String VehicleTypes = jsonObject.getAsString("VEHICLETYPE");
				String urlVehicleTypes = base_url +"/"+VehicleTypesUrl+"/"+VehicleTypes;
				ExtentReport.info("The vehicle url is :"+urlVehicleTypes);
				CreateServiceString =  Post(urlVehicleTypes,token,servicename,payloadValues);
				break;
			case "CreateFleet":
				String FleetUrl = jsonObject.getAsString("FLEET_URL");
				String ClientID = jsonObject.getAsString("CLIENTID");
				String FLEETS = jsonObject.getAsString("FLEETS");
				String urlCreateFleet = base_url +"/"+ClientID+"/"+FleetUrl+"/"+FLEETS+"/";
				ExtentReport.info("The fleet url is :"+urlCreateFleet);
				sleep(8000);
				CreateServiceString =  Post(urlCreateFleet,token,servicename,payloadValues);
				break;
			case "CreateGreetingLogsWithWrongClientID":
				String CreateGreetingWithWrongIDURL = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String Clients = jsonObject.getAsString("CLIENTS");
				String ClientIDs = jsonObject.getAsString("CLIENT_WRONGID_2");
				String GreetingLOGS = jsonObject.getAsString("GREETING_LOGS");
				String UrlCreateGreetingWithWrongID = base_url +"/"+CreateGreetingWithWrongIDURL+"/"+Clients+"/"+ClientIDs+"/"+GreetingLOGS;
				ExtentReport.info("The Client Context url is :"+UrlCreateGreetingWithWrongID);
				CreateServiceString = Post(UrlCreateGreetingWithWrongID,token,servicename,payloadValues);
				break;
			case "GreetingLogType":
				String GreetingLogTypeUrl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String GreetingLogType = jsonObject.getAsString("CLIENTS");
				String urlGreetingLogType = base_url +"/"+GreetingLogTypeUrl+"/"+GreetingLogType;
				ExtentReport.info("The GreetingLog url is :"+urlGreetingLogType);
				CreateServiceString =  Post(urlGreetingLogType,token,servicename,payloadValues);
				break;

			case "CreateGreetingLogWithClientId":
				String CreateGreetingURL = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String CreateGreetClients = jsonObject.getAsString("CLIENTS");
				String CreateGreetClientIDs = jsonObject.getAsString("CLIENT1_ID");
				String CreateGreetGreetingLOGS = jsonObject.getAsString("GREETING_LOGS");
				String UrlCreateGreeting = base_url +"/"+CreateGreetingURL+"/"+CreateGreetClients+"/"+CreateGreetClientIDs+"/"+CreateGreetGreetingLOGS;
				ExtentReport.info("The Client Context url is :"+UrlCreateGreeting);
				CreateServiceString = Post(UrlCreateGreeting,token,servicename,payloadValues);
				break;
			}
			info("The Createservice response status code for service name :  "+ servicename +" is "+CreateServiceString.getStatusCode());
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating CreateServices request for the service "+servicename+" : "+e.getMessage());
		}

		return CreateServiceString;
	}


	/*
	 * ****************************************************************************** 
	 * Name : CreateUser 
	 * Parameters : payLoad(JsonObject Payload)	 * 
	 * Purpose : For Creating User
	 * ******************************************************************************
	 */
	public String CreateUser(String payLoad) {

		Response userCreated = null;
		String createUserID = null;	
		JSONObject CreateUserJson = null;

		try {
			CreateUserJson = JsonReader.getJsonObject(payLoad);			
			String displayName = CreateUserJson.get("displayName").toString() + getRandomNumber();
			CreateUserJson.put("displayName",displayName);
			ExtentReport.info("Created UserData payload is "+CreateUserJson.toJSONString());			
			userCreated = CreateServices("user",CreateUserJson);				
			if( userCreated!=null && userCreated.getStatusCode()==200) {
				ExtentReport.info("The created User response status code is : "+userCreated.getStatusCode());
				ExtentReport.info("The created User response is : "+userCreated.getBody().asString());
				createUserID = userCreated.getBody().asString();	
				ExtentReport.info("The created User id is : "+createUserID);
			}
			else {
				ExtentReport.info("An exception has occured while Creating User payload: "+userCreated.getStatusCode());
			}
		}catch(Exception e) {

		}


		return createUserID;
	}


	/*
	 * ****************************************************************************** 
	 * Name : CreateVehicle 
	 * Parameters : payLoad(JsonObject Payload)	
	 * Purpose : For Creating Vehicle
	 * ******************************************************************************
	 */
	public String CreateVehicle(String payLoad) {
		JSONObject CreateVehicleJson = null;
		String vehicleId = null;
		Response VehicleResponse = null;

		try {
			CreateVehicleJson = JsonReader.getJsonObject(payLoad);			
			CreateVehicleJson.put("name",CreateVehicleJson.get("name").toString() + getRandomNumber());
			CreateVehicleJson.put("description",CreateVehicleJson.get("description").toString() + getRandomNumber());
			CreateVehicleJson.put("licensePlate",CreateVehicleJson.get("licensePlate").toString() + getRandomNumber());
			CreateVehicleJson.put("vin",CreateVehicleJson.get("vin").toString() + getRandomNumber(10,100));

			ExtentReport.info("Executing Post Request against Vehicle using payload : "+CreateVehicleJson.toJSONString());

			VehicleResponse = CreateServices("vehicle",CreateVehicleJson);	

			if( VehicleResponse!=null && VehicleResponse.getStatusCode()==200) {
				ExtentReport.info("The created vehicle response status code is : "+VehicleResponse.getStatusCode());
				ExtentReport.info("The created vehicle response is : "+VehicleResponse.getBody().asString());
				vehicleId = VehicleResponse.getBody().asString();	
				ExtentReport.info("The created vehicle id is : "+vehicleId);
			}
			else {
				ExtentReport.info("The created vehicle response status code is : "+VehicleResponse.getStatusCode());
			}


		}catch(Exception e) {
			ExtentReport.info("An exception has occured while Creating vehicle payload: "+payLoad);
			vehicleId = null;
		}		

		return vehicleId;
	}

	/*
	 * ****************************************************************************** 
	 * Name : GetUser 
	 * Parameters : createUserID(id of the user)	 * 
	 * Purpose : For retrieving the specific user
	 * ******************************************************************************
	 */
	public String GetUser(String createUserID) {
		Response userJson = null;			
		String userId = null;

		try {
			userId = (String)JsonReader.getJsonObject(createUserID).get("id");
		}catch(Exception e) {
			//ExtentReport.info(createUserID +" dictionary is not found in the data file hence using it as user Id string ");
			userId = createUserID;
		}		
		userJson = GetServices("user",userId);
		if(userJson.getStatusCode()==200) {
			ExtentReport.info("User with UserId "+userId +" is present in the available Users");
			ExtentReport.info("User with UserId "+userId +" is present in the available Users and its response body is : "+ userJson.getBody().asString());			
			return userJson.getBody().asString();
		}else {
			ExtentReport.info("User with UserId "+userId +" is not present in the available Users");
			return null;
		}

	}

	/*
	 * ****************************************************************************** 
	 * Name : GetAsset 
	 * Parameters : createAssetID(id of the Asset)	 * 
	 * Purpose : For retrieving the specific Asset
	 * ******************************************************************************
	 */
	public String GetAsset(String createAssetID) {
		Response assetJson = null;			
		String assetId = null;
		String assetResponse = null;

		try {
			assetId = (String)JsonReader.getJsonObject(createAssetID).get("id");
		}catch(Exception e) {
			assetId = createAssetID;
		}	
		ExtentReport.info("Getting Asset details using assetId : "+assetId);

		try {
			assetJson = GetServices("asset",assetId);
			if(assetJson.getStatusCode()==200) {
				assetResponse = assetJson.getBody().asString();
				ExtentReport.info("AssetId : "+assetId +" is present in the available Assets");
			}else {
				ExtentReport.info("Asset with AssetId "+assetId +" is not present in the available Assets and the response message is : "+assetJson.getBody().jsonPath().getString("message"));
				assetResponse = null;
			}	
		}catch(Exception e) {
			ExtentReport.info("An execption has generated while working with getAsset and the message is : "+e.getMessage());
		}
		return assetResponse;		
	}


	/*
	 * ****************************************************************************** 
	 * Name : GetVehicle 
	 * Parameters : createVehicleID(id of the vehicle)	 * 
	 * Purpose : For retrieving the specific Vehicle
	 * ******************************************************************************
	 */
	public String GetVehicle(String createVehicleID) {
		Response vehicleJson = null;			
		String vehicleId = null;
		String vehicleResponse = null;

		try {
			vehicleId = (String)JsonReader.getJsonObject(createVehicleID).get("id");
		}catch(Exception e) {
			//ExtentReport.info(createVehicleID +" dictionary is not found in the data file hence using it as Vehicle Id string ");
			vehicleId = createVehicleID;
		}	
		ExtentReport.info("Executing Get Request against Vehicle using vehicleId : "+vehicleId);
		try {
			vehicleJson = GetServices("vehicle",vehicleId);
			if(vehicleJson.getStatusCode()==200) {
				ExtentReport.info("Vehicle with vehicleId "+vehicleId +" is present in the available Vehicles");
				ExtentReport.info("Vehicle with vehicleId "+vehicleId +" is present in the available Vehicles and its response body is : "+ vehicleJson.getBody().asString());			
				vehicleResponse = vehicleJson.getBody().asString();
			}else {
				ExtentReport.info("Vehicle with vehicleId "+vehicleId +" is not present in the available Vehicles");
				vehicleResponse = null;
			}	
		}catch(Exception e) {
			ExtentReport.info("An execption has generated while working with getvehicle and the message is : "+e.getMessage());
		}
		return vehicleResponse;

	}


	/*
	 * ****************************************************************************** 
	 * Name : GetChargingStation 
	 * Parameters : createChargingStationID(id of the Charging Station)	 * 
	 * Purpose : For retrieving the specific Charging Station
	 * ******************************************************************************
	 */
	public String GetChargingStation(String createChargingStationID) {
		Response ChargingStationJson = null;			
		String ChargingStationId = null;
		String ChargingStationResponse = null;

		try {
			ChargingStationId = (String)JsonReader.getJsonObject(createChargingStationID).get("id");
		}catch(Exception e) {
			ChargingStationId = createChargingStationID;
		}	

		ExtentReport.info("Executing Get Request against ChargingStation using chargingStationId : "+ChargingStationId);

		try {
			ChargingStationJson = GetServices("chargingstation",ChargingStationId);
			if(ChargingStationJson.getStatusCode()==200) {
				ExtentReport.info("Charging Station with ChargingStationId "+ChargingStationId +" is present in the available Charging Stations");
				ExtentReport.info("Charging Station with ChargingStationId "+ChargingStationId +" is present in the available Charging Stations and its response body is : "+ ChargingStationJson.getBody().asString());			
				ChargingStationResponse = ChargingStationJson.getBody().asString();
			}else {
				ExtentReport.info("Charging Station with ChargingStationId "+ChargingStationId +" is not present in the available Charging Stations");
				ChargingStationResponse = null;
			}	
		}catch(Exception e) {
			ExtentReport.info("An execption has generated while working with getChargingStation and the message is : "+e.getMessage());
		}
		return ChargingStationResponse;		
	}


	/*
	 * ****************************************************************************** 
	 * Name : DeleteAsset 
	 * Parameters : createAssetID(id of the Asset)	 * 
	 * Purpose : For deleting the specific Asset using Asset Id
	 * ******************************************************************************
	 */
	public boolean DeleteAsset(String createAssetID) {

		Response deleteAssetResponse = null;		
		boolean result = false;
		String assetId = null;

		try {
			assetId = (String)JsonReader.getJsonObject(createAssetID).get("id");
		}catch(Exception e) {
			//ExtentReport.info(createAssetID +" dictionary is not found in the data file hence using it as asset Id string ");
			assetId = createAssetID;
		}

		ExtentReport.info("Deleting the Asset using assetId : "+assetId);

		try {
			deleteAssetResponse = DeleteServices("asset",assetId);

			if( deleteAssetResponse!=null && deleteAssetResponse.getStatusCode()==200) {
				ExtentReport.info("The deleteted Asset id "+assetId+" status code is "+deleteAssetResponse.getStatusCode());
				ExtentReport.info("The Asset id "+assetId+" is deleted successfully");
				result = true;
			}
			else {
				ExtentReport.info("The deleteted Asset "+assetId+" status code is "+deleteAssetResponse.getStatusCode());
				ExtentReport.info("The deleteted Asset "+assetId+" status code is "+deleteAssetResponse.getBody().jsonPath().getString("message"));
				ExtentReport.info("The Asset id "+assetId+" is not deleted successfully");
				result = false;
			}
		}catch(Exception e) {
			ExtentReport.info("An execption has generated while working with deleteAsset and the message is : "+e.getMessage());
		}

		return result;
	}

	/*
	 * ****************************************************************************** 
	 * Name : DeleteVehicle 
	 * Parameters : createVehicleID(id of the vehicle)	 * 
	 * Purpose : For deleting the specific Vehicle using vehicle Id
	 * ******************************************************************************
	 */
	public boolean DeleteVehicle(String createVehicleID) {

		Response deleteVehicleResponse = null;		
		boolean result = false;
		String vehicleId = null;

		try {
			vehicleId = (String)JsonReader.getJsonObject(createVehicleID).get("id");
		}catch(Exception e) {
			vehicleId = createVehicleID;
		}

		ExtentReport.info("Executing Delete Request against vehicle using vehicleId : "+vehicleId);

		try {
			deleteVehicleResponse = DeleteServices("vehicle",vehicleId);

			if( deleteVehicleResponse!=null && deleteVehicleResponse.getStatusCode()==204) {
				ExtentReport.info("The deleteted Vehicle id "+vehicleId+" status code is "+deleteVehicleResponse.getStatusCode());
				ExtentReport.info("The deleteted Vehicle id "+vehicleId+" response is "+deleteVehicleResponse.getBody().asString());
				ExtentReport.info("The Vehicle id "+vehicleId+" is deleted successfully");
				result = true;
			}
			else {
				ExtentReport.info("The deleteted vehicle "+vehicleId+" status code is "+deleteVehicleResponse.getStatusCode());
				ExtentReport.info("The Vehicle id "+vehicleId+" is not deleted successfully");
				result = false;
			}
		}catch(Exception e) {
			ExtentReport.info("An execption has generated while working with deleteVehicle and the message is : "+e.getMessage());
		}

		return result;
	}

	/*
	 * ****************************************************************************** 
	 * Name : DeleteChargingStation 
	 * Parameters : createChargingStationID(id of the ChargingStation)	 * 
	 * Purpose : For deleting the specific Charging Station using ChargingStation Id
	 * ******************************************************************************
	 */
	public boolean DeleteChargingStation(String createChargingStationID) {

		Response deleteChargingStationResponse = null;		
		boolean result = false;
		String ChargingStationId = null;

		try {
			ChargingStationId = (String)JsonReader.getJsonObject(createChargingStationID).get("id");
		}catch(Exception e) {
			ChargingStationId = createChargingStationID;
		}

		ExtentReport.info("Executing Delete Request against ChargingStation using ChargingStationId : "+ChargingStationId);

		try {
			deleteChargingStationResponse = DeleteServices("chargingstation",ChargingStationId);

			if( deleteChargingStationResponse!=null && deleteChargingStationResponse.getStatusCode()==204) {
				ExtentReport.info("The deleteted ChargingStation id "+ChargingStationId+" status code is "+deleteChargingStationResponse.getStatusCode());
				ExtentReport.info("The deleteted ChargingStation id "+ChargingStationId+" response is "+deleteChargingStationResponse.getBody().asString());
				ExtentReport.info("The ChargingStation id "+ChargingStationId+" is deleted successfully");
				result = true;
			}
			else {
				ExtentReport.info("The deleteted ChargingStation "+ChargingStationId+" status code is "+deleteChargingStationResponse.getStatusCode());
				ExtentReport.info("The deleteted ChargingStation "+ChargingStationId+" status code is "+deleteChargingStationResponse.getBody().jsonPath().getString("message"));
				ExtentReport.info("The ChargingStation id "+ChargingStationId+" is not deleted successfully");
				result = false;
			}
		}catch(Exception e) {
			ExtentReport.info("An execption has generated while working with deleteChargingStation and the message is : "+e.getMessage());
		}
		return result;
	}


	/*
	 * ****************************************************************************** 
	 * Name : DeleteUser 
	 * Parameters : createUserID(id of the User)	 
	 * Purpose : For deleting the specific user using User Id
	 * ******************************************************************************
	 */
	public boolean DeleteUser(String createUserID) {

		Response deleteUserResponse = null;		
		boolean result = false;
		String userId = null;

		try {
			userId = (String)JsonReader.getJsonObject(createUserID).get("id");
		}catch(Exception e) {
			userId = createUserID;
		}

		ExtentReport.info("Executing Delete Request against User using userId : "+userId);
		deleteUserResponse = DeleteServices("user",userId);

		if( deleteUserResponse!=null && deleteUserResponse.getStatusCode()==204) {
			ExtentReport.info("The deleteted user id "+userId+" status code is "+deleteUserResponse.getStatusCode());
			ExtentReport.info("The deleteted user id "+userId+" response is "+deleteUserResponse.getBody().asString());
			ExtentReport.info("The user with userid "+userId+" is deleted successfully");
			result = true;
		}
		else {
			ExtentReport.info("The deleteted user "+userId+" status code is "+deleteUserResponse.getStatusCode());
			ExtentReport.info("The deleteted user "+userId+" status code is "+deleteUserResponse.getBody().jsonPath().getString("message"));
			ExtentReport.info("The user with userid "+userId+" is not deleted successfully");
			result = false;
		}

		return result;
	}

	public String getServiceDescriptorToken(String authorizationURL,String serviceName) {		

		String strToken = null;
		Map<String,String> form = null;

		try {
			form = new HashMap<String,String>();
			TestLogger.appInfo(" Populating form parameters of ClientService Authorization Token ");
			form.put("auth_url", authorizationURL);
			form.put("grant_type", jsonObject.getAsString("GRANT_TYPE"));
			form.put("client_id", jsonObject.getAsString("SMS_ADD_ID"));
			form.put("client_secret", jsonObject.getAsString("SMS_ADD_SECRET"));
			form.put("resource", jsonObject.getAsString("SMS_ADD_ID"));
			form.put("Content-Type", jsonObject.getAsString("AUTHORIZATION_CONTENT_TYPE"));
			strToken = getToken(form,serviceName);	
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while populating form parameters of ClientAuthorization Token "+e.getMessage());
		}
		return strToken;

	}


	public String getServiceCatalogtoken(String authorizationURL,String serviceName) {                        

		By MICROSOFTLOGIN_EMAILUSERNAME_EB         =By.xpath("//input[@type='email']");	
		By MICROSOFTLOGIN_EMAILPASSWORD_EB         =By.id("i0118");
		By MICROSOFTLOGIN_NEXT_BT                  =By.id("idSIButton9");
		By MICROSOFTLOGIN_STAYSIGNIN_BT            =By.xpath("//input[@id='idBtn_Back']");
		String strToken = null;

		try {

			DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
			DriverManager.getDriverInstance().navigate().to(EnvironmentManager.getAzureUrl());

			elementSendKeys(MICROSOFTLOGIN_EMAILUSERNAME_EB,"shaik.ansari@sasken.com");
			elementClick(MICROSOFTLOGIN_NEXT_BT);
			if(elementDisplayed(MICROSOFTLOGIN_EMAILPASSWORD_EB)) {
				elementSendKeyWithActions(MICROSOFTLOGIN_EMAILPASSWORD_EB,passwordDecript("Peace!23"));
				waitElementVisibleClick(MICROSOFTLOGIN_NEXT_BT,100);
				elementClick(MICROSOFTLOGIN_STAYSIGNIN_BT);
			}
			sleep(6000);
			DriverManager.getDriverInstance().navigate().to(EnvironmentManager.getServieCatalogTokenUrl());
			String currentUrl = DriverManager.getDriverInstance().getCurrentUrl();
			String[] splitbefore = currentUrl.split("=");
			String[] splitafter = splitbefore[1].split("&");
			strToken = splitafter[0];
			DriverManager.getDriverInstance().close();
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while populatsing form parameters of ClientAuthorization Token "+e.getMessage());
		}
		return strToken;

	}

	public Response PutServices(String servicename,JSONObject payload, String serviceDetails) {
		Response ServiceManagementService = null;
		String base_url = null;
		String token = null;
		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);

		try {
			switch(servicename) {
			case "CreateUpdateClientProperty":
				String ServiceManagementClientProperty = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String Config = jsonObject.getAsString("CONFIG");
				String Clients = jsonObject.getAsString("CLIENT");
				String ClientID=jsonObject.getAsString("CLIENTID");
				String urlCreateUpdateClientProperty = base_url +"/"+ServiceManagementClientProperty+"/"+Config+"/"+Clients+"/"+ClientID;
				ExtentReport.info("The InitiateService url is :"+urlCreateUpdateClientProperty);
				ServiceManagementService =  Put(urlCreateUpdateClientProperty,token,servicename,payload,serviceDetails);
				break;
			case "CreateUpdateCUSProperty":
				String ServiceManagementClientforCUS = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String ConfigCUS = jsonObject.getAsString("CONFIG");
				String ClientsCUS = jsonObject.getAsString("CLIENT");
				String ClientIDCUS=jsonObject.getAsString("CLIENTID");
				String urlCreateUpdateCUS = base_url +"/"+ServiceManagementClientforCUS+"/"+ConfigCUS+"/"+ClientsCUS+"/"+ClientIDCUS;
				ExtentReport.info("The InitiateService url is :"+urlCreateUpdateCUS);
				ServiceManagementService =  Put(urlCreateUpdateCUS,token,servicename,payload,serviceDetails);
				break;
			case "CreateServiceDescriptor":
				String CreateServiceDescriptor = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String Services = jsonObject.getAsString("SERVICES");
				//				String ServiceDescriptorID=jsonObject.getAsString("SERVICEDESCRIPTORID");
				String urlCreateServiceDescriptor = base_url +"/"+CreateServiceDescriptor+"/"+Services+"/"+serviceDetails;
				ExtentReport.info("The InitiateService url is :"+urlCreateServiceDescriptor);
				ServiceManagementService =  Put(urlCreateServiceDescriptor,token,servicename,payload,serviceDetails);
				break;
			case "assignNewServicesToClient":
				String assignServiceDescriptor = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");
				String assignClients = jsonObject.getAsString("CLIENT");
				String assignClientID=jsonObject.getAsString("CLIENTID");
				String assignServices = jsonObject.getAsString("SERVICE");
				//				String ServiceDescriptorID=jsonObject.getAsString("SERVICEDESCRIPTORID");
				String urlAssignServiceDescriptor = base_url +"/"+assignServiceDescriptor+"/"+assignClients+"/"+assignClientID+"/"+assignServices+"/"+serviceDetails;
				ExtentReport.info("The InitiateService url is :"+urlAssignServiceDescriptor);
				ServiceManagementService =  Put(urlAssignServiceDescriptor,token,servicename,serviceDetails);
				break;
			case "UpdateFleet":
				String FleetUrl = jsonObject.getAsString("FLEET_URL");
				String FleetClientID = jsonObject.getAsString("CLIENTID");
				String FLEETS = jsonObject.getAsString("FLEETS");
				String urlUpdateFleet = base_url +"/"+FleetClientID+"/"+FleetUrl+"/"+FLEETS+"/"+serviceDetails;
				ExtentReport.info("The fleet url is :"+urlUpdateFleet);
				ServiceManagementService =  Put(urlUpdateFleet,token,servicename,payload,serviceDetails);
				break;
			case "asset":
				String AssetsUrl = jsonObject.getAsString("ASSET_URL");
				String Assets = jsonObject.getAsString("ASSETS");
				String urlAsset = base_url +"/"+AssetsUrl+"/"+Assets+"/"+serviceDetails;
				ExtentReport.info("The asset url is :"+urlAsset);
				ServiceManagementService = Put(urlAsset,token,servicename,payload,serviceDetails);
				break;
			case "assignSampleService":
				String sampleService = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String Client = jsonObject.getAsString("CLIENTS");
				String sampleServiceClientID = jsonObject.getAsString("CLIENTID");
				String sampleServicename = jsonObject.getAsString("SERVICE");
				String urlInitiatedeleteSampleService = base_url +"/"+sampleService+"/"+Client+"/"+sampleServiceClientID+"/"+sampleServicename+"/"+serviceDetails;
				ExtentReport.info("The InitiateService url is :"+urlInitiatedeleteSampleService);
				ServiceManagementService = Put(urlInitiatedeleteSampleService,token,servicename,payload);
				break;
			case "EditClientWithAPI":
				String ClientsUrl = jsonObject.getAsString("CLIENT_URL");
				String EditClients = jsonObject.getAsString("CLIENTS");;
				String urlClient = base_url +"/"+ClientsUrl+"/"+EditClients+"/"+serviceDetails;
				ExtentReport.info("The asset url is :"+urlClient);
				ServiceManagementService = Put(urlClient,token,servicename,payload,serviceDetails);
				break;
			case "EditIClientDetails":
				String IClientUrl = jsonObject.getAsString("CLIENT_URL");
				String IClients = jsonObject.getAsString("CLIENTS");
				String urlInitiateClients = base_url +"/"+IClientUrl+"/"+IClients+"/"+serviceDetails;
				ExtentReport.info("The url is :"+urlInitiateClients);
				ServiceManagementService = Put(urlInitiateClients,token,servicename,payload);
				break;
			case "EditUserGroup":
				String UserGroupUrl = jsonObject.getAsString("CLIENT_URL");
				String UsersGroup = jsonObject.getAsString("USERGROUPS");
				String urlEditUserGroup = base_url +"/"+UserGroupUrl+"/"+UsersGroup+"/"+serviceDetails;
				ExtentReport.info("The Edit User Group url is :"+urlEditUserGroup);
				ServiceManagementService = Put(urlEditUserGroup,token,servicename,payload);
				break;
			case "UpdateUser":
				ClientsUrl = jsonObject.getAsString("CLIENT_URL");
				String Users = jsonObject.getAsString("USERS");
				String urlUpdateUser = base_url +"/"+ClientsUrl+"/"+Users+"/"+serviceDetails;
				ExtentReport.info("The Edit User Group url is :"+urlUpdateUser);
				ServiceManagementService = Put(urlUpdateUser,token,servicename,payload);
				break;
			case "CreateServDescWithCorrectUUID":
				String ServiceDescClient = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String ServiceDescServices = jsonObject.getAsString("SERVICES");
				String urlServDescr = base_url +"/"+ServiceDescClient+"/"+ServiceDescServices+"/"+serviceDetails;
				ExtentReport.info("The InitiateService url is :"+urlServDescr);
				ServiceManagementService = Put(urlServDescr,token,servicename,payload);
				break;
			case "UpdateGreetingLogWithClientId":
				String CreateGreetingURL = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String CreateGreetClients = jsonObject.getAsString("CLIENTS");
				String CreateGreetClientIDs = jsonObject.getAsString("CLIENT1_ID");
				String CreateGreetGreetingLOGS = jsonObject.getAsString("GREETING_LOGS");
				String UrlCreateGreeting = base_url +"/"+CreateGreetingURL+"/"+CreateGreetClients+"/"+CreateGreetClientIDs+"/"+CreateGreetGreetingLOGS+"/"+serviceDetails;
				ExtentReport.info("The Client Context url is :"+UrlCreateGreeting);
				ServiceManagementService = Put(UrlCreateGreeting,token,servicename,payload);
				break;
			}
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating PutSeServiceIdrvices request for the service "+servicename+" : "+e.getMessage());
		}

		return ServiceManagementService;		
	}

	public Response PutServices(String servicename,JSONObject payload, String serviceDetails,String SDName) {
		Response ServiceManagementService = null;
		String base_url = null;
		String token = null;
		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);

		try {
			switch(servicename) {

			case "CreateUpdateCUSProperty":
				String ServiceManagementClientforCUS = jsonObject.getAsString("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");				 
				String ConfigCUS = jsonObject.getAsString("CONFIG");
				String ClientsCUS = jsonObject.getAsString("CLIENT");
				String ClientIDCUS=jsonObject.getAsString("CLIENTID");
				String urlCreateUpdateCUS = base_url +"/"+ServiceManagementClientforCUS+"/"+ConfigCUS+"/"+ClientsCUS+"/"+ClientIDCUS;
				ExtentReport.info("The InitiateService url is :"+urlCreateUpdateCUS);
				ServiceManagementService =  Put(urlCreateUpdateCUS,token,servicename,payload,serviceDetails,SDName);
				break;

			}
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating PutSeServiceIdrvices request for the service "+servicename+" : "+e.getMessage());
		}

		return ServiceManagementService;		
	}

	public Response PostServices(String servicename) {
		Response ServiceManagementService = null;
		String base_url = null;
		String token = null;
		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);
		try {
			switch(servicename) {
			case "ServicestartedMC":
				String ClientGreeting = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String GlobalInfo = jsonObject.getAsString("GLOBAL_INFO");
				String ServiceStarted = jsonObject.getAsString("SERVICESTARTED");
				String Run = jsonObject.getAsString("RUN");
				String urlInitiateService = base_url +"/"+ClientGreeting+"/"+GlobalInfo+"/"+ServiceStarted+"/"+Run;
				ExtentReport.info("The InitiateService url is :"+urlInitiateService);
				ServiceManagementService =  Post(urlInitiateService,token);
				break;
			case "MulitClientSampleServiceSubscription":
				ClientGreeting = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				GlobalInfo = jsonObject.getAsString("GLOBAL_INFO");
				ServiceStarted = jsonObject.getAsString("SERVICESTARTED");
				Run = jsonObject.getAsString("RUN");
				String urlMultiClientSampleSevice = base_url +"/"+ClientGreeting+"/"+GlobalInfo+"/"+ServiceStarted+"/"+Run;
				ExtentReport.info("The InitiateService url is :"+urlMultiClientSampleSevice);
				ServiceManagementService = Post(urlMultiClientSampleSevice,token);
				break;


			}
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating PutSeServiceIdrvices request for the service "+servicename+" : "+e.getMessage());
		}
		return ServiceManagementService;
	}

	public Response PostServices(String servicename, String serviceId,String clientId) {
		Response ServiceManagementService = null;
		String base_url = null;
		String token = null;
		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);
		try {
			switch(servicename) {
			case "AssignApplication":
				String IClientUrl = jsonObject.getAsString("CLIENT_URL");
				String IApplicationUrl = jsonObject.getAsString("APPLICATIONS");
				String IClients = jsonObject.getAsString("CLIENTS");
				String urlInitiateClients = base_url +"/"+IClientUrl+"/"+IApplicationUrl+"/"+serviceId+"/"+IClients+"/"+clientId;
				ExtentReport.info("The url is :"+urlInitiateClients);
				ServiceManagementService =  Post(urlInitiateClients,token);
				break;
			}
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating PutSeServiceIdrvices request for the service "+servicename+" : "+e.getMessage());
		}
		return ServiceManagementService;
	}
	public Response PostServices(String servicename,JSONObject payload) {
		Response ServiceManagementService = null;
		String base_url = null;
		String token = null;


		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);
		try {
			switch(servicename) {
			case "ClientGreetingsClient1":
				String ClientGreeting = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String Clients = jsonObject.getAsString("CLIENT");
				String ClientID = jsonObject.getAsString("CLIENT1_ID");
				String urlInitiateService = base_url +"/"+ClientGreeting+"/"+Clients+"/"+ClientID+"/greet";
				ExtentReport.info("The InitiateService url is :"+urlInitiateService);
				ServiceManagementService =  Post(urlInitiateService,token,servicename,payload);
				break;
			case "ClientGreetingsClient2":
				String ClientGreeting2 = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String Clients2 = jsonObject.getAsString("CLIENT");
				String ClientID2 = jsonObject.getAsString("CLIENT2_ID");
				String urlInitiateService2 = base_url +"/"+ClientGreeting2+"/"+Clients2+"/"+ClientID2+"/greet";
				ExtentReport.info("The InitiateService url is :"+urlInitiateService2);
				ServiceManagementService =  Post(urlInitiateService2,token,servicename,payload);
				break;
			case "proxyGreeting":
				String proxyClientGreeting2 = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String proxyClients2 = jsonObject.getAsString("CLIENT");
				String proxyClientID2 = jsonObject.getAsString("CLIENT2_ID");
				String urlInitiateproxyGreeting = base_url +"/"+proxyClientGreeting2+"/"+proxyClients2+"/"+proxyClientID2+"/proxy/greet";
				ExtentReport.info("The InitiateService url is :"+urlInitiateproxyGreeting);
				ServiceManagementService =  Post(urlInitiateproxyGreeting,token,servicename,payload);
				break;
			case "GlobalGreetings":
				String GlobalService = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String Global = jsonObject.getAsString("GLOBAL");
				String GreetingLog = jsonObject.getAsString("GREETING_LOGS");
				String urlInitiateGlobalGreeting = base_url +"/"+GlobalService+"/"+Global+"/"+GreetingLog;
				ExtentReport.info("The InitiateService url is :"+urlInitiateGlobalGreeting);
				ServiceManagementService =  Post(urlInitiateGlobalGreeting,token,servicename,payload);
				break;
			case "SendGreetingsNoClientGlobalDB":
				payload.remove("clientid");
				String SendGreetwithoutClientGreeting = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String SendGreetwithoutClientClient = jsonObject.getAsString("CLIENT");
				String urlInitiateServiceGreetwithoutClient = base_url +"/"+SendGreetwithoutClientGreeting+"/"+SendGreetwithoutClientClient+"/greet/datasources";
				ExtentReport.info("The InitiateService url is :"+urlInitiateServiceGreetwithoutClient);
				ServiceManagementService =  Post(urlInitiateServiceGreetwithoutClient,token,servicename,payload);
				break;
			case "SendGreetingsNoClient":
				payload.remove("clientid");
				String ServiceSendGreetwithNOClient = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String SendGreetwithNoClient = jsonObject.getAsString("CLIENT");
				String urlInitiateServiceGreetNoClient = base_url +"/"+ServiceSendGreetwithNOClient+"/"+SendGreetwithNoClient+"/greet";
				ExtentReport.info("The InitiateService url is :"+urlInitiateServiceGreetNoClient);
				ServiceManagementService =  Post(urlInitiateServiceGreetNoClient,token,servicename,payload);
				break;
			case "GreetingFromRollback":
				payload.remove("clientid");
				String ServiceSendRollGreetwithNOClient = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String SendGreetRollwithNoClient = jsonObject.getAsString("CLIENT");
				String urlInitiateServiceRollGreetNoClient = base_url +"/"+ServiceSendRollGreetwithNOClient+"/"+SendGreetRollwithNoClient+"/greet/datasources/rollback";
				ExtentReport.info("The InitiateService url is :"+urlInitiateServiceRollGreetNoClient);
				ServiceManagementService =  Post(urlInitiateServiceRollGreetNoClient,token,servicename,payload);
				break;
			case "proxyGreetingNoClientId":
				String proxyClientGreeting = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String proxyClients = jsonObject.getAsString("CLIENT");
				urlInitiateproxyGreeting = base_url +"/"+proxyClientGreeting+"/"+proxyClients+"/"+"/proxy/greet";
				ExtentReport.info("The InitiateService url is :"+urlInitiateproxyGreeting);
				ServiceManagementService =  Post(urlInitiateproxyGreeting,token,servicename,payload);
				break;
			case "CreateGreeting":
				String Greetingslogs = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String ClientDetails = jsonObject.getAsString("CLIENTS");
				ClientID = jsonObject.getAsString("CLIENT2_ID");
				String urlInitiateClientgreetinglogs = base_url +"/"+Greetingslogs+"/"+ClientDetails+"/"+ClientID+"/"+"greeting-logs";
				ExtentReport.info("The Client Context url is :"+urlInitiateClientgreetinglogs);
				ServiceManagementService =  Post(urlInitiateClientgreetinglogs,token,servicename,payload);
				break;
			case "CreateIClientDetails":
				String IClientUrl = jsonObject.getAsString("CLIENT_URL");
				String IClients = jsonObject.getAsString("CLIENTS");
				String urlInitiateClients = base_url +"/"+IClientUrl+"/"+IClients;
				ExtentReport.info("The url is :"+urlInitiateClients);
				ServiceManagementService =  Post(urlInitiateClients,token,servicename,payload);
				break;
			case "CreateUserGroup":
				String ClientUrl = jsonObject.getAsString("CLIENT_URL");
				String UserGroups = jsonObject.getAsString("USERGROUPS");
				String urlCreateUserGroup = base_url +"/"+ClientUrl+"/"+UserGroups;
				ExtentReport.info("The InitiateService url is :"+urlCreateUserGroup);
				ServiceManagementService = Post(urlCreateUserGroup,token,servicename,payload);
				break;
			case "CreateUser":
				ClientUrl = jsonObject.getAsString("CLIENT_URL");
				UserGroups = jsonObject.getAsString("USERS");
				String urlCreateUser = base_url +"/"+ClientUrl+"/"+UserGroups;
				ExtentReport.info("The InitiateService url is :"+urlCreateUser);
				ServiceManagementService = Post(urlCreateUser,token,servicename,payload);
				break;
			case "client":
				String ClientsUrl = jsonObject.getAsString("CLIENT_URL");
				Clients = jsonObject.getAsString("CLIENTS");;
				String urlClient = base_url +"/"+ClientsUrl+"/"+Clients;
				ExtentReport.info("The asset url is :"+urlClient);
				ServiceManagementService = Post(urlClient,token,servicename,payload);
				break;	
			case "CreateNewRole":
				String Client_ID = jsonObject.getAsString("CLIENTID");
				String APS = jsonObject.getAsString("AUTHORIZATION_PROVIDER_SERVICE");
				String Roles = jsonObject.getAsString("ROLES");
				String urlCreateNewRole = base_url +"/"+Client_ID+"/"+APS+"/"+Roles;
				ExtentReport.info("The InitiateService url is :"+urlCreateNewRole);
				ServiceManagementService = Post(urlCreateNewRole,token,servicename,payload);
				break;
			case "RollbackGlobalGreetings":
				String Rollbackurl = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");
				String RollBackGlobal = jsonObject.getAsString("GLOBAL");
				String RollBackGlobalGreeting = jsonObject.getAsString("GREETING_LOGS");
				String RollBack = jsonObject.getAsString("ROLLBACK");
				String urlInitiateRollBack = base_url +"/"+Rollbackurl+"/"+RollBackGlobal+"/"+RollBackGlobalGreeting+"/"+RollBack;
				ExtentReport.info("The Client Context url is :"+urlInitiateRollBack);
				ServiceManagementService =  Post(urlInitiateRollBack,token,servicename,payload);
				break;

			}
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating PutSeServiceIdrvices request for the service "+servicename+" : "+e.getMessage());
		}
		return ServiceManagementService;
	}

	public Response PostServices(String servicename,JSONObject payload, String serviceID) {
		Response ServiceManagementService = null;
		String base_url = null;
		String token = null;
		String ClientGreeting = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
		String Clients = jsonObject.getAsString("CLIENT");

		base_url = jsonObject.getAsString("BASE_URL");
		token = getAuthorizationToken(servicename);
		try {
			switch(servicename) {
			case "SendGreetingsToClient":
				payload.remove("clientid");
				//				String ClientGreeting = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				//				String Clients = jsonObject.getAsString("CLIENT");
				//				String ClientID = jsonObject.getAsString("CLIENT1_ID");
				String urlInitiateService = base_url +"/"+ClientGreeting+"/"+Clients+"/"+serviceID+"/greet";
				ExtentReport.info("The InitiateService url is :"+urlInitiateService);
				ServiceManagementService =  Post(urlInitiateService,token,servicename,payload);
				break;
			case "SendGreetingsToClientAndGlobalDB":
				payload.remove("clientid");
				//				String Greeting = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				//				String Client = jsonObject.getAsString("CLIENT");
				//				String ClientID = jsonObject.getAsString("CLIENT1_ID");
				String urlInitiateServiceGlobalDB = base_url +"/"+ClientGreeting+"/"+Clients+"/"+serviceID+"/greet/datasources";
				ExtentReport.info("The InitiateService url is :"+urlInitiateServiceGlobalDB);
				ServiceManagementService =  Post(urlInitiateServiceGlobalDB,token,servicename,payload);
				break;

			case "GreetingFromRollback":
				payload.remove("clientid");
				//				String GreetingFrom = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				//				String Client = jsonObject.getAsString("CLIENT");
				//				String ClientID = jsonObject.getAsString("CLIENT1_ID");
				String urlInitiateServiceFromRollback = base_url +"/"+ClientGreeting+"/"+Clients+"/"+serviceID+"/greet/datasources/rollback"; 
				ExtentReport.info("The InitiateService url is :"+urlInitiateServiceFromRollback);
				ServiceManagementService =  Post(urlInitiateServiceFromRollback,token,servicename,payload);
				break;
			case "proxyGreeting":
				String proxyClientGreeting2 = jsonObject.getAsString("MULTICLIENT_SAMPLE_SERVICE_ALL");				 
				String proxyClients2 = jsonObject.getAsString("CLIENT");
				String urlInitiateproxyGreeting = base_url +"/"+proxyClientGreeting2+"/"+proxyClients2+"/"+serviceID+"/proxy/greet";
				ExtentReport.info("The InitiateService url is :"+urlInitiateproxyGreeting);
				ServiceManagementService =  Post(urlInitiateproxyGreeting,token,servicename,payload);
				break;
			}
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while generating PutSeServiceIdrvices request for the service "+servicename+" : "+e.getMessage());
		}
		return ServiceManagementService;
	}


	public String getResponseValue(Response responseData, String value) {

		String responseValue=null;
		try{
			responseValue=responseData.jsonPath().getJsonObject(value);
			return responseValue;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return responseValue;}


}