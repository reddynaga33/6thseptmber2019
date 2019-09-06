package framework;

import java.io.IOException;
import java.util.Properties;


/*
 * This class having the config properties methods
 */

public class EnvironmentManager {

	/*
	 * properties declaration
	 */ 
	private static Properties properties;  

	/*
	 * This method returns the URL value
	 */
	public static String getUrl() {
		return properties.getProperty("URL");
	}

	public static String getEnvironmentProperties(String value) {
		return properties.getProperty(value);
	}
	public static String getEnvironmentSCProperties() {
		return properties.getProperty("SERVICECATALOGSUPERADMIN");
	}
	public static String getEnvironmentSCPWDProperties() {
		return properties.getProperty("SERVICECATALOGSUPERADMINPWD");
	}
	/*
=======


	/**
>>>>>>> Stashed changes
	 * This method returns the Email URL value
	 */
	public static String getEmailUrl() {
		return properties.getProperty("EmailURL");
	}

	/*
	 * This method returns the IRISURL value
	 */
	public static String getIRISUrl() {
		return properties.getProperty("IRISURL");
	}

	/*
	 * This method returns the portal URL value
	 */
	public static String getPortalUrl() {
		return properties.getProperty("PORTALURL");
	}


	public static String getIRISPortalUrl() {
		return properties.getProperty("IRISPORTALURL");
	}
	
	/*
	 * This method returns the different client URL value
	 */
	public static String getIRISOtherClientUrl() {
		return properties.getProperty("IRISOTHERCLIENTURL");
	}
	public static String getIRISOtherClientDashboardUrl() {
		return properties.getProperty("IRISOTHERCLIENTDASHBOARDURL");
	}
	public static String getIRISOtherClientTripsUrl() {
		return properties.getProperty("IRISOTHERCLIENTTRIPSURL");
	}
	public static String getIRISOtherClientMaintenanceUrl() {
		return properties.getProperty("IRISOTHERCLIENTMAINTENANCEURL");
	}
	public static String getIRISOtherClientAssetUrl() {
		return properties.getProperty("IRISOTHERCLIENTASSETURL");
	}
	public static String getIRISOtherClientBackofficeUrl() {
		return properties.getProperty("IRISOTHERCLIENTBACKOFFICEURL");
	}
	public static String getIRISOtherClienthelpUrl() {
		return properties.getProperty("IRISOTHERCLIENTHELPURL");
	}
	
	public static String getOtherClientUrl() {
		return properties.getProperty("OTHERCLIENTURL");
	}
	
	/*
	 * This method returns the public landingpage URL value
	 */
	public static String getSCPublicLandingPageTauriUrl() {
		return properties.getProperty("SCPUBLICLANDINGPAGETAURI");
	}

	
	/*
	 * This method returns the public landingpage URL value
	 */
	public static String getSCPublicLandingPageDaedalusUrl() {
		return properties.getProperty("SCPUBLICLANDINGPAGEDAEDALUS");
	}
	
	/*
	 * This method returns the SCO URL value
	 */
	public static String getSCOTauriUrl() {
		return properties.getProperty("SCOTAURI");
	}

	/*
	 * This method returns the SCO URL value
	 */
	public static String getSCODaedalusUrl() {
		return properties.getProperty("SCODAEDALUS");
	}
	
	/*
	 * This method returns the Project dashboard URL value
	 */
	public static String getSCProjectDashboardTauriUrl() {
		return properties.getProperty("SCPROJECTDASHBOARDTAURI");
	}

	/*
	 * This method returns the Project dashboard URL value
	 */
	public static String getSCProjectDashboardDaedalusUrl() {
		return properties.getProperty("SCPROJECTDASHBOARDDAEDALUS");
	}
	/*
	 * This method returns the Browser value
	 */
	public static String getBrowserName() {
		return properties.getProperty("BROWSER");
	}

	/*
	 * This method returns the TIMEOUT value
	 */
	public static long getDelayTime() {
		return  Long.parseLong(properties.getProperty("TIMEOUT"));
	}

	/*
	 * This method returns the Username value
	 */
	public static String getAdminUserName() {
		return properties.getProperty("ADMINUSERNAME");
	}

	/*
	 * This method returns the Password value
	 */

	public static String getAdminPassword() { 
		return properties.getProperty("ADMINPASSWORD");
	}


	public static String getIRISAdminUserName() {
		return properties.getProperty("IRISADMINUSERNAME");
	}

	/*
	 * This method returns the Password value
	 */

	public static String getIRISAdminPassword() { 
		return properties.getProperty("IRISADMINPASSWORD");
	}

	/*
	 * This method returns the Username value
	 */

	public static String getOperatorUserName() {
		return properties.getProperty("OPERATORUSERNAME");
	}

	/*
	 * This method returns the Password value
	 */

	public static String getOperatorPassword() { 
		return properties.getProperty("OPERATORPASSWORD");
	}
	public static String getIRISOperatorUserName() {
		return properties.getProperty("IRISOPERATORUSERNAME");
	}

	/*
	 * This method returns the Password value
	 */

	public static String getIRISOperatorPassword() { 
		return properties.getProperty("IRISOPERATORPASSWORD");
	}

	/*
	 * This method returns the NoPrivilege Username value
	 */

	public static String getIRISNoPrivilegeUserName() {
		return properties.getProperty("IRISNOPRIVILEGEUSERNAME");
	}

	/*
	 * This method returns the NoPrivilege User Password value
	 */
	public static String getIRISNoPrivilegePassword() { 
		return properties.getProperty("IRISNOPRIVILEGEUSERPASSWORD");
	}
	public static String getNoPrivilegeUserName() {
		return properties.getProperty("NOPRIVILEGEUSERNAME");
	}

	/*
	 * This method returns the NoPrivilege User Password value
	 */
	public static String getNoPrivilegePassword() { 
		return properties.getProperty("NOPRIVILEGEUSERPASSWORD");
	}
	/*
	 * This method returns the NoPrivilege Username value
	 */

	public static String getPrivilegeWithThresholdUserName() {
		return properties.getProperty("PRIVILEGEWITHTHRESHOILDACCESSUSERNAME");
	}

	/*
	 * This method returns the NoPrivilege User Password value
	 */
	public static String getPrivilegeWithThresholdPassword() { 
		return properties.getProperty("PRIVILEGEWITHTHRESHOILDACCESSPASSWORD");
	}
	public static String getPrivilegeofSubClientCreationAdminUserName() {
		return properties.getProperty("PRIVILEGEWITHSUBCLIENTCREATEACCESSUSERNAME");
	}

	/*
	 * This method returns the NoPrivilege User Password value
	 */
	public static String getPrivilegeofSubClientCreationAdminPassword() { 
		return properties.getProperty("PRIVILEGEWITHSUBCLIENTCREATEACCESSPASSWORD");
	}
	public static String getIRISNOPrivilegeWithThresholdUserName() {
		return properties.getProperty("IRISNOPRIVILEGEWITHTHRESHOILDACCESSUSERNAME");
	}



	/*
	 * This method returns the NoPrivilege User Password value
	 */
	public static String getIRISNOPrivilegeWithThresholdPassword() { 
		return properties.getProperty("IRISNOPRIVILEGEWITHTHRESHOILDACCESSPASSWORD");
	}
	/*
	 * This method returns the Privilege Username which has 2 clients value
	 */

	public static String getPrivilegeMultiClientsUserName() {
		return properties.getProperty("PRIVILEGEMULTICLIENTSUSERNAME");
	}
	public static String getIRISPrivilegeMultiClientsUserName() {
		return properties.getProperty("IRISPRIVILEGEMULTICLIENTSUSERNAME");
	}
	public static String getIRISPrivilegeMultiClientsPassword() { 
		return properties.getProperty("IRISPRIVILEGEMULTICLIENTSPASSWORD");}
	/*
	 * This method returns the NoPrivilege User Password which has 2 clients value
	 */
	public static String getPrivilegeMultiClientsPassword() { 
		return properties.getProperty("NOPRIVILEGEUSERPASSWORD");
	}


	/*
	 * This method returns the NoPrivilege Username value
	 */

	public static String getNoUseEditPrivilegeUserName() {
		return properties.getProperty("NOUSEREDITPRIVILEGEUSERNAME");
	}

	/*
	 * This method returns the NoPrivilege User Password value
	 */
	public static String getNoUseEditPrivilegePassword() { 
		return properties.getProperty("NOUSEREDITPRIVILEGEPASSWORD");
	}

	public static String getIRISNoUserEditPrivilegeUserName() {
		return properties.getProperty("IRISNOUSEREDITPRIVILEGEUSERNAME");
	}

	/*
	 * This method returns the NoPrivilege User Password value
	 */
	public static String getIRISNoUserEditPrivilegePassword() { 
		return properties.getProperty("IRISNOUSEREDITPRIVILEGEPASSWORD");
	}

	public static String getWrongAdminUserName() {
		return properties.getProperty("ADMINUSERNAME1");
	}

	/*
	 * This method returns the NoPrivilege User Password value
	 */
	public static String getWrongAdminPassword() { 
		return properties.getProperty("ADMINPASSWORD1");
	}
	/*
	 * This method returns the Sender email id value
	 */
	public static String getEmailId() {
		return properties.getProperty("SENDEREMAILID");
	}

	/*
	 * This method returns the excel data path
	 */
	public static String getDataFromExcelPath() {
		return properties.getProperty("MASTERDATAEXCELFILE");
	}

	/*
	 * This method returns the JSON data path
	 */
	public static String getDataFromJsonPath() {
		return properties.getProperty("MASTERDATAJSONFILE");
	}
	/*
	 * This method returns the Report path
	 */
	public static String getReportPath() {
		return properties.getProperty("REPORTPATH");
	}

	/*
	 * This method returns the certificate path
	 */
	public static String getCertificatePath() {
		return properties.getProperty("CERTIFICATEPATH");
	}

	/*
	 * This method returns the Screenshot path
	 */
	public static String getScreenShotPath() {
		return properties.getProperty("SCREENSHOTPATH");
	}

	/*
	 * This method returns the Common Slash
	 */
	public static String getCommonSlash() {
		return properties.getProperty("COMMONSLASH");
	}

	/*
	 * This method returns the .HTML
	 */
	public static String getDotHtml() {
		return properties.getProperty("DOTHTML");
	}
	/*
	 * This method returns the .PNG
	 */
	public static String getDotPng() {
		return properties.getProperty("DOTPNG");
	}

	/*
	 * This method returns the ReportName
	 */
	public static String getReportName() {
		return properties.getProperty("REPORTNAME");
	}

	/*
	 * This method returns the ReportName
	 */
	public static String getReportTitle() {
		return properties.getProperty("REPORTTITLE");
	}

	/*
	 * This method returns the DateFormat
	 */
	public static String getDateForamt() {
		return properties.getProperty("DATEFORMAT");
	}

	/*
	 * This method returns the Test Data SheetName
	 */
	public static String getTestDataSheetName() {
		return properties.getProperty("TESTDATASHEET");
	}


	/*
	 * This method returns the MySql Server KEY
	 */
	public static String getMySqlServer() {
		return properties.getProperty("MYSQLSERVER");
	}

	/*
	 * This method returns the MySql Driver
	 */
	public static String getMySqlDriver() {
		return properties.getProperty("MYSQLDRIVER");
	}

	/*
	 * This method returns the MySql Jdbc driver string
	 */
	public static String getMySqlJdbc() {
		return properties.getProperty("MYSQLJDBC");
	}

	/*
	 * This method returns the MySql Host
	 */
	public static String getMySqlHost() {
		return properties.getProperty("MYSQLHOST");
	}

	/*
	 * This method returns the MySql Port
	 */
	public static String getMySqlPort() {
		return properties.getProperty("MYSQLPORT");
	}

	/*
	 * This method returns the MySql Database
	 */
	public static String getMySqlDatabase() {
		return properties.getProperty("MYSQLDATABASE");
	}

	/*
	 * This method returns the MySql User
	 */
	public static String getMySqlUser() {
		return properties.getProperty("MYSQLUSER");
	}

	/*
	 * This method returns the MySql Password
	 */
	public static String getMySqlPassword() {
		return properties.getProperty("MYSQLPASSWORD");
	}	


	/*
	 * This method returns the Sql Server key
	 */
	public static String getSqlServer() {
		return properties.getProperty("SQLSERVER");
	}
	public static String getdaedalusSqlServer() {
		return properties.getProperty("DAEDALUSSQLSERVER");
	}

	/*
	 * This method returns the Sql Server Driver
	 */
	public static String getSqlServerDriver() {
		return properties.getProperty("SQLSERVERDRIVER");
	}

	/*
	 * This method returns the Sql Server Jdbc driver string
	 */
	public static String getSqlServerJdbc() {
		return properties.getProperty("SQLSERVERJDBC");
	}

	/*
	 * This method returns the Sql Server Host
	 */
	public static String getSqlServerHost() {
		return properties.getProperty("SQLSERVERHOST");
	}

	/*
	 * This method returns the Sql Server Port
	 */
	public static String getSqlServerPort() {
		return properties.getProperty("SQLSERVERPORT");
	}

	/*
	 * This method returns the Sql Server Database
	 */
	public static String getSqlServerDatabase() {
		return properties.getProperty("SQLSERVERDATABASE");
	}

	/*
	 * This method returns the Sql Server User
	 */
	public static String getSqlServerUser() {
		return properties.getProperty("SQLSERVERUSER");
	}

	/*
	 * This method returns the Sql Server User
	 */
	public static String getSqlServerPassword() {
		return properties.getProperty("SQLSERVERPASSWORD");
	}

	//**********************************RestAPI*******************************************

	public static String getEnvironmentFile() {
		return properties.getProperty("TAURI_ENVIRONMENT_FILE");		
	}



	public static String getGrantType() {
		return properties.getProperty("GRANT_TYPE");

	}


	public static String getAssetResource() {
		return properties.getProperty("ASSET_RESOURCE");

	}


	public static String getClientResource() {
		return properties.getProperty("CLIENT_RESOURCE");

	}

	public static String getInitiationResource() {
		return properties.getProperty("INITIATION_RESOURCE");

	}


	public static String getClientServiceResource() {
		return properties.getProperty("CLIENT_SERVICE_RESOURCE");

	}


	public static String getAuthorizationContentType() {
		return properties.getProperty("AUTHORIZATION_CONTENT_TYPE");

	}


	public static String getLoginMicrosoft() {
		return properties.getProperty("LOGIN_MICROSOFT");

	}


	public static String getDeviceResource() {
		return properties.getProperty("DEVICE_RESOURCE");

	}


	public static String getBaseUrl() {
		return properties.getProperty("BASE_URL");

	}


	public static String getDeviceUrl() {
		return properties.getProperty("DEVICE_URL");

	}


	public static String getDevices() {
		return properties.getProperty("DEVICES");

	}


	public static String getServiceManagementServiceMultiClient() {
		return properties.getProperty("SERVICE_MANAGEMENT_SERVICE_MULTICLIENT");

	}

	public static String getAddClientToService() {
		return properties.getProperty("ADD_CLIENT_TO_SERVICE");

	}

	public static String getCookie() {
		return properties.getProperty("COOKIE");

	}


	public static String getContentType() {
		return properties.getProperty("CONTENT_TYPE");

	}

	public static String getAssetUrl() {
		return properties.getProperty("ASSET_URL");

	}


	public static String getAssets() {
		return properties.getProperty("ASSETS");

	}


	public static String getClientsUrl() {
		return properties.getProperty("CLIENT_URL");

	}

	public static String getClients() {
		return properties.getProperty("CLIENTS");

	}


	public static String getCreateAssetJson() {
		return properties.getProperty("CREATE_ASSET_JSON");

	}

	public static String getCreateAssetData() {
		return properties.getProperty("CREATE_ASSET_DATA");

	}


	public static String getUsers() {
		return properties.getProperty("USERS");

	}


	public static String getCreateUserJson() {
		return properties.getProperty("CREATE_USER_JSON");

	}

	public static String getCreateUserData() {
		return properties.getProperty("CREATE_USER_DATA");

	}


	//************************************************************************************

	public static String getAssetAddId() {
		return properties.getProperty("ASSET_ADD_ID");

	}

	public static String getAssetAddSecret() {
		return properties.getProperty("ASSET_ADD_SECRET");

	}


	public static String getDeviceAddId() {
		return properties.getProperty("DEVICE_ADD_ID");

	}

	public static String getDeviceAddSecret() {
		return properties.getProperty("DEVICE_ADD_SECRET");

	}



	public static String getClientAadId() {
		return properties.getProperty("CLIENT_AAD_ID");

	}

	public static String getClientAadSercet() {
		return properties.getProperty("CLIENT_AAD_SERCET");

	}

	public static String getInitiationAadId() {
		return properties.getProperty("INITS_AAD_ID");

	}


	public static String getClientServiceAddId() {
		return properties.getProperty("SMS_ADD_ID");

	}

	public static String getInitiationAadSecret() {
		return properties.getProperty("INITS_AAD_SECRET");

	}


	public static String getClientServiceAddSecret() {
		return properties.getProperty("SMS_ADD_SECRET");

	}


	public static String getAadTenant() {
		return properties.getProperty("AAD_TENANT");

	}

	public static String getCreateServiceUrl() {
		return properties.getProperty("CREATESERVICE_URL");

	}

	public static String getInitiateService() {
		return properties.getProperty("INITIATE_SERVICE");

	}

	public static String getServieCatalogTokenUrl() {
		return properties.getProperty("SERVICECATALOGTOKENURL");

	}

	public static String getAzureUrl() {
		return properties.getProperty("AZUREPORTALURL");

	}


	/*
	 * Static block to initializing the properties method
	 */
	static {
		properties = new Properties();
		try {
			properties.load(EnvironmentManager.class.getClassLoader().getResourceAsStream("config/config.properties"));
		} catch (IOException e) {
			TestLogger.appInfo("Exception was thrown at EnvironmentManager class - static block" + e.getMessage());
			e.printStackTrace();
		}
	}

}