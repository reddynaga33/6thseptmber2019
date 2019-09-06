package com.openmatics.testinstrumentation.platform.service.app.backoffice;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.ServiceApiBase;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;


public class BackofficeApi extends ServiceApiBase {

    private IBackofficeAuthorizationUserApi authorizationUserApi;
    private IBackofficeAuthorizationRoleApi authorizationRoleApi;
    private IBackofficeAuthorizationPrivilegeApi authorizationPrivilegeApi;
    private String clientKey;

    public BackofficeApi(String envKey, String clientId) throws Exception {
        super(new ServiceConfiguration("backoffice-service", new EnvironmentConfiguration(envKey)));
        this.clientKey = clientId;
    }


    public IBackofficeAuthorizationUserApi getAuthorizationUserApi() throws Exception {
        if (authorizationUserApi == null){
            authorizationUserApi =  new BackofficeAuthorizationUserApi(getServiceProperty(), clientKey);
        }
        return authorizationUserApi;
    }

    public IBackofficeAuthorizationRoleApi getAuthorizationRoleApi() throws Exception {
        if (authorizationRoleApi == null){
            authorizationRoleApi =  new BackofficeAuthorizationRoleApi(getServiceProperty(), clientKey);
        }
        return authorizationRoleApi;
    }

    public IBackofficeAuthorizationPrivilegeApi getAuthorizationPrivilegeApi() throws Exception {
        if (authorizationPrivilegeApi == null){
            authorizationPrivilegeApi =  new BackofficeAuthorizationPrivilegeApi(getServiceProperty(), clientKey);
        }
        return authorizationPrivilegeApi;
    }


}
