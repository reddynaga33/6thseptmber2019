package com.openmatics.testinstrumentation.platform.service.global.clientmanagement.db;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class ClientCacheRepository {

    private final DbConnectionFactory connectionFactory;

    public ClientCacheRepository(DbConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public List<String> getAllWithExcludesList( List<String> excludesIds) throws Exception {
        List<String> result = new ArrayList<String>();
        try(DbQueryService dbService = new DbQueryService(connectionFactory)){
            ResultSet rs = dbService.executeQuery("select * from  [asset_management_service_schema].[client_cache]");
            while(rs.next()){
                String id = rs.getString("id");
                if (excludesIds == null || !excludesIds.contains(id)){
                    result.add(id);
                }
            }
        }
        return result;
    }

    public List<String> getAll(List<String> excludesIds) throws Exception {
        return getAllWithExcludesList(null);
    }

}
