package com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.repository;

import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 04.03.2019.
 */
public class ServiceDescriptorRepository {
    private static final String ALIAS_ID = "id";
    private static final String ALIAS_ARTIFACT_ID = "artifactId";
    private static final String ALIAS_GROUP_ID = "groupId";
    private static final String ALIAS_SINGLE_CLIENT = "singleClient";
    private static final String ALIAS_APP_ID = "appId";
    private final DbConnectionFactory connectionFactory;

    public ServiceDescriptorRepository(DbConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public ServiceDescriptor getByGroupIdAndArtifactId(String groupId, String artifactId) {
        String query = "SELECT [artifact_id] as " + ALIAS_ARTIFACT_ID + " , " +
                "            [single_client] as " + ALIAS_SINGLE_CLIENT + " , " +
                "            [application_id] as " + ALIAS_APP_ID + " , " +
                "            [group_id] as " + ALIAS_GROUP_ID + " , " +
                "            [id] as " + ALIAS_ID + " " +
                "            FROM [service_management_service_schema].[service_descriptor]" +
                "            WHERE group_id=? AND artifact_id=?";
        try {
            DbQueryService sqlAccess = new DbQueryService(connectionFactory);
            ResultSet rs = sqlAccess.executeQuery(query, groupId, artifactId);
            rs.next();
            ServiceDescriptor serviceDescriptor = new ServiceDescriptor(
                    rs.getString(ALIAS_ID),
                    rs.getString(ALIAS_ARTIFACT_ID),
                    rs.getString(ALIAS_GROUP_ID),
                    rs.getBoolean(ALIAS_SINGLE_CLIENT),
                    rs.getString(ALIAS_APP_ID)
            );
            sqlAccess.closeQuery(rs);
            return serviceDescriptor;
        } catch (SQLException e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
    }

    public int switchToStMode(String serviceDescriptorId) {
        String query = "Update [service_management_service_schema].[service_descriptor]" +
                "            SET [single_client]='true' " +
                "            WHERE [id]=?";
        try (DbQueryService sqlAccess = new DbQueryService(connectionFactory)) {
            int i = sqlAccess.executeUpdate(query, serviceDescriptorId);
            return i;
        } catch (SQLException e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
    }

}
