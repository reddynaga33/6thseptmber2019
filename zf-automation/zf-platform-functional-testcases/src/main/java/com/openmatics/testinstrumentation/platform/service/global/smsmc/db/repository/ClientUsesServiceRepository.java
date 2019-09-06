package com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository;

import com.openmatics.testinstrumentation.platform.service.global.smsmc.db.model.ClientUsesService;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 24.04.2019.
 */
public class ClientUsesServiceRepository {
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_CLIENT_ID = "clientId";
    public static final String ALIAS_SERVICE_DESCRIPTOR_ID = "serviceDescriptorId";

    private final DbConnectionFactory dbConnectionFactory;

    public ClientUsesServiceRepository(DbConnectionFactory dbConnectionFactory) {
        this.dbConnectionFactory = dbConnectionFactory;
    }

    public ClientUsesService findByClientIdAndServiceDescriptor(String clientId, String serviceDescriptorId) {
        String query = "SELECT [id] as " + ALIAS_ID + ", " +
                "   [client_id] as " + ALIAS_CLIENT_ID + ", " +
                "   [service_descriptor_id]  as " + ALIAS_SERVICE_DESCRIPTOR_ID +
                "   FROM [service_management_service_schema].[client_uses_service] " +
                "   WHERE [client_id]=? AND [service_descriptor_id]=?";
        ClientUsesService clientUsesService;
        try (DbQueryService sqlAccess = new DbQueryService(dbConnectionFactory)) {
            ResultSet rs = sqlAccess.executeQuery(query, clientId, serviceDescriptorId);
            clientUsesService = convertToEntity(rs);
            return clientUsesService;
        } catch (Exception e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
    }

    private ClientUsesService convertToEntity(ResultSet resultSet) throws SQLException {
        ClientUsesService clientUsesService = null;
        if (resultSet.next()) {
            clientUsesService = new ClientUsesService()
                    .setServiceDescriptorId(resultSet.getString(ALIAS_SERVICE_DESCRIPTOR_ID))
                    .setClientId(resultSet.getString(ALIAS_CLIENT_ID))
                    .setId(resultSet.getString(ALIAS_ID));
        }
        return clientUsesService;
    }
}
