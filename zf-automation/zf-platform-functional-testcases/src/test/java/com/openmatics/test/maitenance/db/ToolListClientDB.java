package com.openmatics.test.maitenance.db;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientManagementApi;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import com.openmatics.testinstrumentation.utils.sql.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.*;

public class ToolListClientDB {

    public static void main(String[] args) throws Exception {
        String envKey = "tauri";
        //getClientsDatabasesNames(envKey);
        getClientsDatabasesToDelete(envKey);
     }

    public static void getClientsDatabasesToDelete(String envKey) throws Exception {

        String substringContained = "automated";
        IEnvironmentConfiguration configuration = new EnvironmentConfiguration(envKey);
        PlatformInstrumentation inst = new PlatformInstrumentation(configuration);
        ClientManagementApi cms = new ClientManagementApi(envKey);
        HashMap<String, String> map = new HashMap<>();
        HttpResponse response = cms.getClientApi().getClients();

        if (response.getStatus() == 200){
            JSONArray clients = response.getContentAsJsonArray();
            try(DbConnectionFactory connections = new DbConnectionFactory(inst.getEnvConf().getDbServerConnectionString())) {
                for (Object item : clients) {
                    JSONObject client = (JSONObject) item;
                    String name = client.getString("name");
                    if (name.toLowerCase().contains(substringContained.toLowerCase())) {
                        String clientId = client.getString("id");
                        String query = String.format("SELECT * FROM master.dbo.sysdatabases where name = 'client-%s-db'", clientId);
                        try (DbQueryService queryService = new DbQueryService(connections)) {
                            ResultSet rs = queryService.executeQuery(query);
                            if (rs.next()){
                                map.put(name, String.format("DROP DATABASE IF EXISTS [%s] -- %s",
                                        rs.getString("name"),
                                        client.getString("creationTime")));
                            }
                        }
                    }
                }
            }
        }

        for(Map.Entry<String, String> entry : map.entrySet()){
            System.out.println("GO");
            System.out.println("-- client: " + entry.getKey());
            System.out.println( entry.getValue());
            System.out.println("GO");
        }
    }

    public static List<String> getClientsDatabasesNames(String envKey) throws Exception {
        IEnvironmentConfiguration configuration = new EnvironmentConfiguration(envKey);
        PlatformInstrumentation inst = new PlatformInstrumentation(configuration);
        ClientManagementApi cms = new ClientManagementApi(envKey);
        List<String> dbNames = new ArrayList<String>();
        List<String> clientNames = new ArrayList<String>();
        HashMap<String, JSONObject> map = new HashMap<>();
        try(DbConnectionFactory connections = new DbConnectionFactory(inst.getEnvConf().getDbServerConnectionString())) {
            String query = "SELECT * FROM master.dbo.sysdatabases where name like 'client-%-db'";
            try (DbQueryService queryService = new DbQueryService(connections)) {
                ResultSet rs = queryService.executeQuery(query);
                while (rs.next()){
                    dbNames.add(rs.getString("name"));
                }
            }
        }
        System.out.println("dbNames size is: " + dbNames.size());
        HttpResponse response = cms.getClientApi().getClients();
        if (response.getStatus() == 200)
        {
            JSONArray clients = response.getContentAsJsonArray();
            for (String dbName : dbNames) {
                for (Object item : clients) {
                    JSONObject client = (JSONObject) item;
                    String clientId = client.getString("id");
                    String clientName = client.getString("name");
                    if (dbName.contains(clientId) && (! dbName.contains("8870965f-d5cd-4d2e-a2f9-71eeb65ba2fd"))) {
                        client.put("dbName", dbName);
                        map.put(clientName, client);
                        clientNames.add(clientName);
                    }
                }
            }
        }
        System.out.println("ClintsMap size is: " + map.size());
        clientNames.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return clientNames;
    }

}
