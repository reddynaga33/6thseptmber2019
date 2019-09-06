package com.openmatics.testinstrumentation.platform.service.client.authorizationprovider;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.*;


public class AuthorizationProviderApi extends ServiceApiBase {

    private IProviderAuthorizationServiceUsersApi userApi = null;
    private IProviderAuthorizationServiceRolesApi roleApi = null;
    private IProviderAuthorizationServicePrivilegesApi privilegApi = null;
    private IProviderAuthorizationServiceGroupsApi groupApi = null;
    private IProviderAuthorizationServiceApplicationsApi applicationApi = null;
    private IProviderAuthorizationServiceAdminApi adminApi = null;
    private String clientKey;



    public AuthorizationProviderApi(String envKey, String clientId) throws Exception {
        super(new ServiceConfiguration("authorization-provider-service", new EnvironmentConfiguration(envKey)));
        this.clientKey = clientId;
    }

    public IProviderAuthorizationServiceUsersApi getUsertApi() throws Exception {
        if (userApi == null){
            userApi =  new ProviderAuthorizationServiceUsersApi(getServiceProperty(), clientKey);
        }
        return userApi;
    }

    public IProviderAuthorizationServiceRolesApi getRoleApi() throws Exception {
        if (roleApi == null){
            roleApi =  new ProviderAuthorizationServiceRolesApi(getServiceProperty(), clientKey);
        }
        return roleApi;
    }

    public IProviderAuthorizationServicePrivilegesApi getPrivilegApi() throws Exception {
        if (privilegApi == null){
            privilegApi =  new ProviderAuthorizationServicePrivilegesApi(getServiceProperty(), clientKey);
        }
        return privilegApi;
    }

    public IProviderAuthorizationServiceGroupsApi getGroupApi() throws Exception {
        if (groupApi == null){
            groupApi =  new ProviderAuthorizationServiceGroupApi(getServiceProperty(), clientKey);
        }
        return groupApi;
    }

    public IProviderAuthorizationServiceApplicationsApi getApplicationApi() throws Exception {
        if (applicationApi == null){
            applicationApi =  new ProviderAuthorizationServiceApplicationApi(getServiceProperty(), clientKey);
        }
        return applicationApi;
    }

    public IProviderAuthorizationServiceAdminApi getAdminApi() throws Exception {
        if (adminApi == null){
            adminApi =  new ProviderAuthorizationServiceAdminApi(getServiceProperty(), clientKey);
        }
        return adminApi;
    }

}
