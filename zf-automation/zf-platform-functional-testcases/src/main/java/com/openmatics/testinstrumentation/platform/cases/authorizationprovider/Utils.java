package com.openmatics.testinstrumentation.platform.cases.authorizationprovider;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

public class Utils {

    public static void assertEqualsRole(JSONObject newRole, JSONObject role) throws Exception {
        Assert.assertEquals(newRole.getString("code"), role.getString("code"));
        Assert.assertEquals(newRole.getString("name"), role.getString("name"));
        Assert.assertEquals(newRole.getString("description"), role.getString("description"));
        //Assert.assertEquals(role1.getString("groups"), role2.getString("groups")); // now groups not in using
        if (role.isNull("users")){
            // TODO - compare null versus empty [] array
        }
        else {
            Assert.assertEquals(newRole.getJSONArray("users").length(), role.getJSONArray("users").length());
            for (Object item : role.getJSONArray("users")) {
                JSONObject user = (JSONObject) item;
                boolean isOk = false;
                for (Object newItem : newRole.getJSONArray("users")) {
                    JSONObject newUser = (JSONObject) newItem;
                    if (user.getString("idExt").equals(newUser.getString("idExt"))){
                        isOk = true;
                        break;
                    }
                }
                String message = String.format("Assigned user idExt %s not found in new role.", user.getString("idExt"));
                Assert.assertEquals(isOk, true, message);
            }
        }

        // privileges
        if (role.isNull("privileges")){
            // TODO - compare null versus empty [] array
        }
        else {
            Assert.assertEquals(role.getJSONArray("privileges").length(), role.getJSONArray("privileges").length());
            for(Object item : role.getJSONArray("privileges")){
                JSONObject privilege = (JSONObject)item;
                boolean isChecked = false;
                for (Object newItem : newRole.getJSONArray("privileges")) {
                    JSONObject newPrivileges = (JSONObject) newItem;
                    if (privilege.getString("id").equals(privilege.getString("id"))){
                        assertEqualsPrivilege(newPrivileges, privilege);
                        isChecked = true;
                        break;
                    }
                }
                String message = String.format("Different in privileges code %s.", role.getString("code"));
                Assert.assertEquals(isChecked, true, message);
            }
        }

    }

    public static void assertEqualsPrivilege(JSONObject newRole, JSONObject role) throws Exception {
        Assert.assertEquals(newRole.getString("code"), role.getString("code"));
        Assert.assertEquals(newRole.optString("name"), role.optString("name"));
        Assert.assertEquals(newRole.optString("description"), role.optString("description"));
        // Assert groups
        // Assert users
        // Assert roles
        // Assert applications
        // Assert application
    }

}
