package com.openmatics.testinstrumentation.platform.service.client.authorizationprovider;

import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.utils.ResourceLoader;
import org.json.JSONArray;
import org.json.JSONObject;

public class AuthorizationConfiguration {

    private String resourceFolder = "platform/authorizationprovider/";
    private IEnvironmentConfiguration envConf;
    private String clientId;
    private JSONArray roles = null;
    private JSONArray privileges = null;

    public AuthorizationConfiguration(IEnvironmentConfiguration envConf, String clientId){
        this.clientId = clientId;
        this.envConf = envConf;
    }

    public JSONObject getRoleTemplateByCode(String code) throws Exception {
        if (roles == null){
            roles = ResourceLoader.loadAsJsonArray(resourceFolder + "Roles.json");
        }
        for(Object it : roles){
            JSONObject role = (JSONObject)it;
            if (role.getString("code").equals(code)) return role;
        }
        return null;
    }

    public JSONArray getPrivilegesTemplates() throws Exception {
        if (privileges == null){
            privileges = ResourceLoader.loadAsJsonArray(resourceFolder + "Privileges.json");
        }
        return privileges;
    }

    public JSONObject getPrivilegesTemplatesByCode(String code) throws Exception {
        if (privileges == null){
            privileges = ResourceLoader.loadAsJsonArray(resourceFolder + "Privileges.json");
        }
        for(Object it : roles){
            JSONObject privilige = (JSONObject)it;
            if (privilige.getString("code").equals(code)) return privilige;
        }
        return null;
    }




}
