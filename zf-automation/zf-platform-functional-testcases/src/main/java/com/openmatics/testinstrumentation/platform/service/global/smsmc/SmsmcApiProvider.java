package com.openmatics.testinstrumentation.platform.service.global.smsmc;

import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.SmsmcEventApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.SmsmcRestApi;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;

import java.sql.ResultSet;

public class SmsmcApiProvider {

    private PlatformInstrumentation platformInstrumentation;
    private SmsmcEventApi eventApi;
    private SmsmcRestApi restApi;
    @Deprecated
    private DbConnectionFactory dbConnectionFactory;

    @Deprecated
    public SmsmcApiProvider(PlatformInstrumentation platformInstrumentation, DbConnectionFactory dbConnectionFactory) throws Exception {
        this.platformInstrumentation = platformInstrumentation;
        this.dbConnectionFactory = dbConnectionFactory;
    }

    public SmsmcApiProvider(PlatformInstrumentation platformInstrumentation) {
        this.platformInstrumentation = platformInstrumentation;
    }

    public SmsmcEventApi getEventApi() {
        if (eventApi == null) {
            eventApi = new SmsmcEventApi(platformInstrumentation.getEnvConf().getServiceBusProperty());
        }
        return eventApi;
    }

    public SmsmcRestApi getRestApi() throws Exception {
        if (restApi == null) {
            restApi = new SmsmcRestApi(platformInstrumentation.getEnvConf());
        }
        return restApi;
    }

    @Deprecated
    public Boolean isRecordInServiceUsesDestination(String nameIdentifier, String serviceDescriptorId, boolean isSingleTenant) {
        if (dbConnectionFactory == null) {
            throw new RuntimeException("The connection factory is null");
        }
        String query = String.format("SELECT COUNT(*) FROM [service_management_service_schema].[service_uses_destination]" +
                " WHERE name_identifier = '%s' AND service_descriptor_id = '%s' AND single_tenant = '%s'", nameIdentifier, serviceDescriptorId, isSingleTenant);
        try {
            DbQueryService sqlAccess = new DbQueryService(dbConnectionFactory);
            ResultSet rs = sqlAccess.executeQuery(query);
            rs.next();
            return rs.getInt(1) > 0;
        } catch (Exception e) {
            System.err.println("isRecordInServiceUsesDestination failed due to " + e.getMessage());
            throw new RuntimeException("Can't read from database", e);
        }
    }

}
