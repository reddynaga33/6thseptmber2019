package zf.ClientManagementServiceSuite.pages;

import com.google.gson.JsonObject;

import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class IUserServicePage extends RestApiUtility{

	public void VerifyUserIsAbleToGetTheUserDetails() {

		Response UsersResponse = null;
		UsersResponse=GetServices("user");
		info("Getting Users list reponse body is: "+UsersResponse.getBody().asString());
		if(UsersResponse.getStatusCode()==200) {
			testPassed("The Getting Users list is successfull");
		}else {
			testFailed("The Getting Users list is no successfull");
		}
	}
	public void VerifyUserIsAbleToGetTheUserDetailsOfaClient() {

		Response ClientUserDetailsResponse = null;
		ClientUserDetailsResponse=GetServices("");

	}
	public void VerifyUserIsAbleToGetTheClientDetailsAssociatedToTheUserId(String CreateUser) {

		Response UserDetialsResponse = null;
		UserDetialsResponse=GetServices("UserClient",CreateUser);
		info("The Response Body is: "+UserDetialsResponse.getBody().asString());
		if(UserDetialsResponse.getStatusCode()==200) {
			info("Client Id of associated to the user is :"+UserDetialsResponse.jsonPath().getJsonObject("id"));
			testPassed("Getting the Client details associated to the User Id is successfull");
		}else {
			testFailed("Getting the Client details associated to the User Id is not successfull");
		}
	}
	public String VerifyUserIsAbleToCreateUser() {
		
		Response createUserResponse = null;
		String UserId=null;
		JSONObject Payload = JsonReader.getJsonObject("CreateUser");
		Payload.put("givenName",Payload.getAsString("givenName"));
		Payload.put("surname",Payload.getAsString("surname"));
		Payload.put("displayName",Payload.getAsString("displayName"));
		createUserResponse=PostServices("CreateUser",Payload);
		info("The Created User Response is :"+createUserResponse.getBody().asString());
		UserId=createUserResponse.getBody().asString();
		if(createUserResponse.getStatusCode()==200) {
			testPassed("The User creation is Successfull and Created User Id is: "+UserId);
		}else {
			testFailed("The User Creation is not successfull and the rsponse is :"+createUserResponse.getBody().asString());
		}
		return UserId;
	}
	public void VerrifyUserIsAbleToDeleteTheUser(String CreatedUserId) {

		Response DeleteUserResponse = null;
		DeleteUserResponse=DeleteServices("DeleteUser", CreatedUserId);
		if(DeleteUserResponse.getStatusCode()==200) {
			testPassed("The User id "+CreatedUserId+" is Deleted Successfully");
		}else {
			testFailed("The User id "+CreatedUserId+" is not Deleted. The response is ::"+DeleteUserResponse.getBody().asString());
		}
	}
	public void VerifyUserIsAbleToGetSingleUserDetails(String UserId) {
		
		Response GetSingleUserResponse = null;
		try {
		GetSingleUserResponse=GetServices("user",UserId);
		info("The Response of the Getting User Details is: "+GetSingleUserResponse.getBody().asString());
		if(GetSingleUserResponse.getStatusCode()==200) {
			testPassed("The Details of The User Id "+UserId+" is Successfull");
		}else{
			testFailed("The Details of The User Id "+UserId+" is Not Availble");
		}}catch(Exception e) {
			testFailed("An exception occured and the error message is :: "+e.getMessage());
		}
	}
	public void VerifyUserIsAbleToUpdateTheUserDetails(String CreatedUserId) {
		
		Response UpdateUserResponse = null;
		try {
		JSONObject UpdateUserJson = JsonReader.getJsonObject("UpdateUserDetails");
		UpdateUserJson.put("id",CreatedUserId);
		UpdateUserJson.put("displayName",UpdateUserJson.getAsString("displayName")+"Updated"+getRandomNumber());
		UpdateUserResponse=PutServices("UpdateUser", UpdateUserJson, CreatedUserId);
		info("The Updated User Response is :"+UpdateUserResponse.getBody().asString());
		if(UpdateUserResponse.getStatusCode()==204) {
			testPassed("The User Id "+CreatedUserId+" is Updated Successfully");
		}else {
			testFailed("The User Id "+CreatedUserId+" is Not Available");
		}}catch(Exception e) {
			testFailed("An exception occured and the error message is :: "+e.getMessage());
		}
	}
}