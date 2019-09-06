package com.openmatics.testinstrumentation.platform.cases.authorizationprovider;

import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.platform.service.client.authorizationprovider.*;
import com.openmatics.testinstrumentation.utils.exception.ObjectExistsException;
import com.openmatics.testinstrumentation.utils.exception.ObjectNotExistsException;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.testng.Assert;
import org.json.*;
import java.util.*;

public class CrudRole {

    private String clientId = null;
    private String roleCode = "NEW_AUTOMATED_TEST_MANAGER_00";
    private PlatformInstrumentation platformInstr;
    private AuthorizationConfiguration configuration;
    private AuthorizationProviderApi apsApi;
    private JSONObject role;
    private List<String> usersIds;
    private List<String> groupsIds;
    private List<String> privilegeIds;

    public CrudRole(PlatformInstrumentation platformInstr) throws Exception {
        this.platformInstr = platformInstr;
    }


    public void givenClient() throws Exception {
        clientId = platformInstr.getClientConf().getClient().getId();
        configuration = new AuthorizationConfiguration(platformInstr.getEnvConf(), clientId);
    }

    public void givenRoleNotExists() throws Exception {
        role = configuration.getRoleTemplateByCode(roleCode);
        apsApi = new AuthorizationProviderApi(platformInstr.getEnvConf().getEnvKey(), clientId);
        HttpResponse response = apsApi.getRoleApi().getRoles();
        for(Object it : response.getContentAsJsonArray()){
            JSONObject role = (JSONObject)it;
            String code = role.getString("code");
            String message = String.format("Role %s already exists.", code);
            Assert.assertNotEquals(roleCode, code, message);
        }
    }

    public void givenGroups() throws Exception {
        /*
        groupsIds = new ArrayList<>();
        List<String> groupsNames = Arrays.asList("AutomatedTestGroup_01","AutomatedTestGroup_02");
        // #get all groups, check if exists current and eventually create
        HttpResponse responseAllGroups = apsApi.getGroupApi().getGroups();
        JSONArray existsGroups =  responseAllGroups.getContentAsJsonArray();
        for (String name : groupsNames){
            JSONObject group = JsonUtils.getJsonItem(existsGroups, "name", name);
            if (group != null){
                groupsIds.add(group.getString("id"));
            }
            else {
               ClientManagementApi CmsApi = new ClientManagementApi(platformInstr.getEnvConf());
               JSONObject newGroup = new JSONObject();
               newGroup.put("name", name);
               newGroup.put("enabled", true);
               //newGroup.put("creationTime", "2017-09-11T12:59:59.999Z");
               //newGroup.put("client", String.format("[{\"id\":\"%s\"}]", clientId));
               newGroup.put("client", new JSONObject().put("id", clientId));
               HttpResponse response = CmsApi.getUserGroupApi().createUserGroup(newGroup.toString());
               Assert.assertEquals(response.getStatus(), 200);
               groupsIds.add(response.getContentAsJson().getString("id"));
            }
        }
        */
    }

    public void givenPrivileges() throws Exception {
        privilegeIds = new ArrayList<>();
        JSONArray privileges = configuration.getPrivilegesTemplates();
        HttpResponse responseAllPrivileges = apsApi.getPrivilegApi().getPrivileges();
        JSONArray existingPrivileges =  responseAllPrivileges.getContentAsJsonArray();
        for (Object item : privileges) {
            JSONObject privilege = (JSONObject)item;
            String code = privilege.getString("code");
            JSONObject currentPrivilege =  JsonUtils.getJsonItem(existingPrivileges, "code", privilege.getString("code"));
            if (currentPrivilege != null) {
                privilegeIds.add(currentPrivilege.getString("id"));
            } else {
               throw new ObjectExistsException(String.format("Privilege with code %s not exists !"));
            }
        }
    }

    public void givenUser() throws Exception {
        usersIds = new ArrayList<>();
        JSONArray users = platformInstr.getClientConf().getUsersTemplates();
        HttpResponse responseAllUsers = apsApi.getUsertApi().getUsers();
        JSONArray restUsers =  responseAllUsers.getContentAsJsonArray();
        for (Object item : users) {
            JSONObject user = (JSONObject) item;
            String login = String.format("%s.%s",user.getString("givenName"), user.getString("surname"));
            JSONObject restUser = JsonUtils.getJsonItem(restUsers, "surname", login);
            if (user != null) {
                //id in coresponde with extId from AD
                usersIds.add(restUser.getString("id"));
            } else {
                throw new ObjectNotExistsException(user.toString());
            }
        }
    }

    public void whenICreateNewRole() throws  Exception {
        role =  configuration.getRoleTemplateByCode(roleCode);
        // #set fields of new role
        role.put("groups", groupsIds);
        JSONArray jsonArrayPrivileges = new JSONArray();
        for (String id : privilegeIds){
            jsonArrayPrivileges.put(new JSONObject().put("id", id));
        }
        role.put("privileges", jsonArrayPrivileges);
        JSONArray jsonArrayUser = new JSONArray();
        for (String id : usersIds){
            jsonArrayUser.put(new JSONObject().put("idExt", id));
        }
        role.put("users", jsonArrayUser);
        // #call rest post new role
        HttpResponse response = apsApi.getRoleApi().createRole(role.toString());
        Assert.assertEquals(response.getStatus(), 200);
        System.out.println(response.getContentAsString());
        JSONObject newRole = response.getContentAsJson();
        Utils.assertEqualsRole(newRole, role);
        role.put("id", newRole.getString("id"));
    }

    public void thatICanGetTheNewRole() throws  Exception {
        HttpResponse response = apsApi.getRoleApi().getRole(role.getString("id"));
        Assert.assertEquals(response.getStatus(), 200);
        JSONObject getRole = response.getContentAsJson();
        Utils.assertEqualsRole(getRole, role);
    }

    public void whenIUpdateTheRole() throws  Exception {
        // update all fields of role
        role.put("code", role.getString("code") + "_update");
        role.put("name", role.getString("name") + "_update");
        role.put("description", role.getString("description") + "_update");
        JSONArray jsonArrayPrivileges = new JSONArray();
        privilegeIds.remove(0);
        for (String id : privilegeIds){
            jsonArrayPrivileges.put(new JSONObject().put("id", id));
        }
        role.put("privileges", jsonArrayPrivileges);
        usersIds.remove(0);
        JSONArray jsonArrayUser = new JSONArray();
        for (String id : usersIds){
            jsonArrayUser.put(new JSONObject().put("idExt", id));
        }
        role.put("users", jsonArrayUser);
        HttpResponse response = apsApi.getRoleApi().updateRole(role.getString("id"),role.toString());
        Assert.assertEquals(response.getStatus(), 200);
        System.out.println(response.getContentAsString());
        JSONObject updatedRole = response.getContentAsJson();
        Utils.assertEqualsRole(updatedRole, role);
    }

    public void thatICanGetTheUpdatedRole() throws  Exception {
        HttpResponse response = apsApi.getRoleApi().getRole(role.getString("id"));
        Assert.assertEquals(response.getStatus(), 200);
        JSONObject getRole = response.getContentAsJson();
        Utils.assertEqualsRole(getRole, role);
    }

    public void whenIDeleteTheRole() throws  Exception {
        HttpResponse response = apsApi.getRoleApi().deleteRole(role.getString("id"));
        Assert.assertEquals(response.getStatus(), 204);
    }

    public void thatICanNotGetDeletedTheRole() throws  Exception {
        HttpResponse response = apsApi.getRoleApi().getRole(role.getString("id"));
        Assert.assertEquals(response.getStatus(), 404);
    }

}
