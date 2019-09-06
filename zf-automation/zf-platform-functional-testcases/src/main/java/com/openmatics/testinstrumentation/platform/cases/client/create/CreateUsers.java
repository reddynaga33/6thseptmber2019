package com.openmatics.testinstrumentation.platform.cases.client.create;

import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientManagementApi;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.utils.ResourceLoader;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.*;

/**
 *  Create new users for new client which will be use in next test.
 */
public class CreateUsers {

    private ClientManagementApi clientApi;
    private PlatformInstrumentation platformInstr;

    /**
     * Constructor
     * @param platformInstr
     * @throws Exception
     */
    public CreateUsers(PlatformInstrumentation platformInstr) throws Exception {
        this.platformInstr = platformInstr;
        platformInstr.getClientConf().getClient().getId();
        clientApi = new ClientManagementApi(platformInstr.getEnvConf());
    }


    /**
     * Load user to create from users template from resouces
     * @return users as JSONArray
     * @throws Exception
     */
    public JSONArray givenUsers() throws Exception {

        String path = platformInstr.getClientConf().getResourcePath("users");
        Map<String, String> values = new HashMap<String, String>();
        values.put("clientId", platformInstr.getClientConf().getClient().getId());
        values.put("clientSuffix", platformInstr.getClientConf().getClientSuffix());
        return ResourceLoader.loadAsJsonArray(path, values);
    }

    /**
     * Execute check if given users not exists in platform and if yes throws assert exception
     * @param users as json array
     * @throws Exception
     */
    public void checkUsersToCreateNotExists(JSONArray users) throws Exception {

        String clientId = platformInstr.getClientConf().getClient().getId();
        HttpResponse response = clientApi.getClientApi().getClientByIdExtended(clientId);
        for(Object item : users){
            JSONObject user = (JSONObject)item;
            for(Object it : response.getContentAsJson().getJSONArray("users")){
                JSONObject clientUser = (JSONObject)it;
                String name = user.getString("displayName");
                String message = String.format("User %s already exists.", name);
                Assert.assertNotEquals(clientUser.getString("displayName"), name, message);
            }
        }
    }

    /**
     * Create new users by rest CmsClient management service
     * and add new user id to the json object in given users
     * @param users collections users in json array
     * @throws Exception
     */
    public void createUsers(JSONArray users) throws Exception {
        for(int i = 0; i < users.length(); i++){
            JSONObject user = users.getJSONObject(i);
            HttpResponse response = clientApi.getUserApi().createUser(user.toString());
            Assert.assertEquals(response.getStatus(),200);
            user.put("id", response.getContentAsString());
        }
    }

    /**
     * Get client with extended info by rest of CmsClient management service
     * and there are given users and assert its fields
     * @param users collections users in json array
     * @throws Exception
     */
    public void getClientWithUsersByCmsRestAndAssert(JSONArray users) throws Exception {
        String clientId = platformInstr.getClientConf().getClient().getId();
        HttpResponse response = clientApi.getClientApi().getClientByIdExtended(clientId);
        JSONArray clientUsers = response.getContentAsJson().getJSONArray("users");
        Assert.assertEquals(clientUsers.length(), users.length());
        for(Object item : clientUsers){
            JSONObject clientUser = (JSONObject)item;
            JSONObject user = getUser(clientUser.getString("id"), users);
            assertEqualsUsers(clientUser, user);
        }
    }

    /**
     * Get each given user by id by CmsClient management service and assert its fields
     * @param users collections users in json array
     * @throws Exception
     */
    public void getTheUserByCmsRestAndAssert(JSONArray users) throws Exception {
        for(int index = 0; index < users.length(); index++){
            JSONObject user = users.getJSONObject(index);
            HttpResponse response = clientApi.getUserApi().getUser(user.getString("id"));
            JSONObject userFromRest = response.getContentAsJson();
            assertEqualsUsers(userFromRest, user);
        }
    }

    public void receiveEmailsWithUserPassword(){
        throw new NotImplementedException();
    }

    private void assertEqualsUsers(JSONObject u1, JSONObject u2){
        Assert.assertEquals(u1.getString("id"), u2.getString("id"));
        Assert.assertEquals(u1.getString("givenName"), u2.getString("givenName"));
        Assert.assertEquals(u1.getString("surname"), u2.getString("surname"));
        Assert.assertEquals(u1.getString("displayName"), u2.getString("displayName"));
        Assert.assertEquals(u1.getString("email"), u2.getString("email"));
        // Probbaly it can be possible create user only as enabled
        //Assert.assertEquals(u1.getBoolean("enabled"), u2.getBoolean("enabled"));
        // TODO and enable after update it is possible set dnable to false ?
        //TODO check creation time ?
    }

    private JSONObject getUser(String id, JSONArray array){
        for(int index = 0; index < array.length(); index++ ) {
            if (array.getJSONObject(index).getString("id").equals(id)) return array.getJSONObject(index);
        }
        return null;
    }

}
