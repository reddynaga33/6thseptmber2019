package zf.ClientManagementServiceSuite.pages;

import com.google.gson.JsonObject;

import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class IUserGroupServicePage extends RestApiUtility{

	public void UserGroups() {
		Response UserGroupResponse = null;
		UserGroupResponse=GetServices("UserGroup");
		info("The Response Body is : "+UserGroupResponse.getBody().asString());
		if(UserGroupResponse.getStatusCode()==200) {
			testPassed("Getting Users from the group is successfull");
		}else {
			testFailed("Getting Users from the group is successfull");
		}
	}
	public String VerifyUserIsAbleToCreateUserGroup() {
		Response CreateUserGroupResponse = null;
		JSONObject UserGroupJson =null;
		String UserGroupId=null;
		UserGroupJson=JsonReader.getJsonObject("CreateUserGroup");
		UserGroupJson.put("name",UserGroupJson.get("name").toString()+getRandomNumber() );
		CreateUserGroupResponse=PostServices("CreateUserGroup",UserGroupJson);
		UserGroupId=CreateUserGroupResponse.getBody().asString();
		info("The Reposne Body is :"+CreateUserGroupResponse.getBody().asString());

		if(CreateUserGroupResponse.getStatusCode()==200) {
			testPassed("The User Group Creation is successfull");
		}else {
			testFailed("The User Group Create is not successfull");
		}
		return UserGroupId;
	}
	public void DeleteUserGroupId(String UserGroupId) {

		Response DeleteUserGroupResponse = null;
		DeleteUserGroupResponse=DeleteServices("DeleteUserGroup", UserGroupId);
		System.out.println(DeleteUserGroupResponse.getBody().asString());
	}

	public void VerifyUserIsAbleToUpdateUserGroup(String UserGroupId) {
		Response EditUserGroupResponse = null;
		JSONObject EditUserGroupJson =null;
		EditUserGroupJson=JsonReader.getJsonObject("EditUserGroup");
		EditUserGroupJson.put("id", UserGroupId);
		EditUserGroupJson.put("name",EditUserGroupJson.get("name").toString()+getRandomNumber() );
		EditUserGroupResponse=PutServices("EditUserGroup", EditUserGroupJson, UserGroupId);
		info("The Response Body is :"+EditUserGroupResponse.getBody().asString());
		if(EditUserGroupResponse.getStatusCode()==204) {
			testPassed("The Edit User Group with Group Id is successfull");
		}else {
			testFailed("The Edit User Group with Group Id is not successfull");
		}
	}

	public void getGroupDetails(String servicename,String userGroupId,String userinfo,String clientInfo) {
		Response UserGroupResponse = null;
		try {
			UserGroupResponse=GetServices(servicename,userGroupId,userinfo,clientInfo);
			if(UserGroupResponse.getStatusCode()==200) {
				testPassed("The Get reponse details is :   "+UserGroupResponse.getBody().asString());
			}else {
				testFailed("The Get response details is ::"+UserGroupResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured during get service and error message is : "+e.getMessage());
		}
	}

}