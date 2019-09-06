package com.openmatics.test.maitenance.cloneclient;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientManagementApi;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;

import java.sql.ResultSet;
import java.util.*;

public class AddUserToClient {

    public static void main(String[] args) throws Exception
    {

        String envKey = "daedalus";
        IEnvironmentConfiguration configuration = new EnvironmentConfiguration(envKey);
        PlatformInstrumentation inst = new PlatformInstrumentation(configuration);
        ClientManagementApi cms = new ClientManagementApi(envKey);
        String originClientId = "ee986775-b80c-4cd5-9858-359d61a5cf12";
        String clientId="df8545d8-7179-44eb-94f7-51e6561e963b";

        List<String> users = new ArrayList<>();
        String query = "select * from [authorization_provider_service_schema].[users]";
        try(DbConnectionFactory connFactory = new DbConnectionFactory(inst.getEnvConf().getDbConnectionString(originClientId))){
            try(DbQueryService dbAccess = new DbQueryService(connFactory)) {
                ResultSet rs = dbAccess.executeQuery(query);
                while (rs.next()) {
                    users.add(rs.getString("id_ext"));
                }
            }
        }

        List<String> badRequest = new ArrayList<>();
        for (String userId : users) {
            System.out.println(userId);
            HttpResponse response = cms.getUserApi().createUserGroupClientMembership(userId, clientId);
            if (!(response.getStatus() == 200 || response.getStatus() == 204)) {
                badRequest.add(response.getContentAsString());
            }
        }

        System.out.println("*************  bad **********************");
        for (String message : badRequest) {
            System.out.println(message);
            System.out.println("---------------");
        }

    }


}
