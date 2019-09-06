package com.openmatics.test.maitenance;

import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientManagementApi;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;

import java.util.*;

public class ClientLoader {

    private ClientManagementApi cms;
    PlatformInstrumentation inst;

    public ClientLoader(PlatformInstrumentation inst) throws Exception {
        cms = new ClientManagementApi(inst.getEnvConf().getEnvKey());
        this.inst = inst;
    }

    public HashMap<String, String> loadBySubstringInName(String substringContained, boolean onlyIfExistsInDb )throws Exception  {
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
                        if (onlyIfExistsInDb){
                            try (DbQueryService queryService = new DbQueryService(connections)) {
                                ResultSet rs = queryService.executeQuery(query);
                                if (rs.next()){
                                    map.put(clientId, rs.getString("name"));
                                }
                            }
                        } else {
                            map.put(clientId, name);
                        }
                    }
                }
            }
        }
        return map;
    }


}
