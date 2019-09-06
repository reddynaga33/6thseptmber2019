package com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository;

import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 06.03.2019.
 */
public class ServiceDescriptorRepository {
    private static final String ALIAS_SHORT_NAME = "shortName";
    private static final String ALIAS_ARTIFACT_ID = "artifactId";
    private static final String ALIAS_SINGLE_CLIENT = "singleClient";
    private static final String ALIAS_APP_ID = "applicationId";
    private static final String ALIAS_GROUP_ID = "groupId";
    private static final String ALIAS_SERVICE_DESCRIPTOR_ID = "serviceDescriptorId";
    private final DbConnectionFactory dbConnectionFactory;

    public ServiceDescriptorRepository(DbConnectionFactory dbConnectionFactory) {
        this.dbConnectionFactory = dbConnectionFactory;
    }

    /**
     * Find service descriptor by the artifact id.
     * <p>
     * TODO: The artifactId is not unique in the SMS. Unique key is defined by groupID and artifactID.
     *
     * @param artifactId artifact id
     * @return service descriptor with the artifact id.
     */
    public ServiceDescriptor findServiceDescriptorByArtifactId(String artifactId) {
        String query = "SELECT [short_name] as " + ALIAS_SHORT_NAME + ", " +
                "\n            [artifact_id] as " + ALIAS_ARTIFACT_ID + "," +
                "\n            [single_client] as " + ALIAS_SINGLE_CLIENT + "," +
                "\n            [application_id] as " + ALIAS_APP_ID + "," +
                "\n            [group_id] as " + ALIAS_GROUP_ID + "," +
                "\n            [id] as " + ALIAS_SERVICE_DESCRIPTOR_ID +
                "\n            FROM [service_management_service_schema].[service_descriptor]" +
                "\n            WHERE artifact_id=?";
        ServiceDescriptor serviceDescriptor;
        try (DbQueryService sqlAccess = new DbQueryService(dbConnectionFactory)) {
            ResultSet rs = sqlAccess.executeQuery(query, artifactId);
            serviceDescriptor = convertToEntity(rs);
        } catch (Exception e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
        return serviceDescriptor;
    }

    /**
     * find service descriptor by the groupID and artifactId.
     *
     * @param groupId    group id of the service descriptor.
     * @param artifactId artifact id of the service descriptor.
     * @return founded service descriptor.
     */
    public ServiceDescriptor findServiceDescriptorByGroupIdArtifactId(String groupId, String artifactId) {
        String query = "SELECT [short_name] as " + ALIAS_SHORT_NAME + ", " +
                "\n            [artifact_id] as " + ALIAS_ARTIFACT_ID + "," +
                "\n            [single_client] as " + ALIAS_SINGLE_CLIENT + "," +
                "\n            [application_id] as " + ALIAS_APP_ID + "," +
                "\n            [group_id] as " + ALIAS_GROUP_ID + "," +
                "\n            [id] as " + ALIAS_SERVICE_DESCRIPTOR_ID +
                "\n            FROM [service_management_service_schema].[service_descriptor]" +
                "\n            WHERE group_id=? AND artifact_id=?";
        ServiceDescriptor serviceDescriptor;
        try (DbQueryService sqlAccess = new DbQueryService(dbConnectionFactory)) {
            ResultSet rs = sqlAccess.executeQuery(query, groupId, artifactId);
            serviceDescriptor = convertToEntity(rs);
        } catch (Exception e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
        return serviceDescriptor;
    }

    /**
     * Delete service descriptor by service descriptor id.
     * @param serviceDescriptorId descriptor id.
     */
    public void deleteServiceDescriptorById(String serviceDescriptorId) {
        String query = ("DELETE FROM [service_management_service_schema].[service_descriptor] WHERE id=?");
        try (DbQueryService sqlAccess = new DbQueryService(dbConnectionFactory)) {
            sqlAccess.executeUpdate(query, serviceDescriptorId);
        } catch (Exception e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
    }

    /**
     * Delete service descriptor by the artifactId and groupId
     * @param groupId group id of the service descriptor.
     * @param artifactId artifact id of the service descriptor.
     */
    public void deleteServiceDescriptorByGroupIdAndArtifactId(String groupId, String artifactId) {
        String query = ("DELETE FROM [service_management_service_schema].[service_descriptor] WHERE group_id=? AND artifact_id=?");
        try (DbQueryService sqlAccess = new DbQueryService(dbConnectionFactory)) {
            sqlAccess.executeUpdate(query, groupId, artifactId);
        } catch (Exception e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
    }

    private ServiceDescriptor convertToEntity(ResultSet resultSet) throws SQLException {
        ServiceDescriptor serviceDescriptor = null;
        if (resultSet.next()) {
            serviceDescriptor = new ServiceDescriptor(
                    resultSet.getString(ALIAS_SERVICE_DESCRIPTOR_ID),
                    resultSet.getString(ALIAS_ARTIFACT_ID),
                    resultSet.getString(ALIAS_GROUP_ID),
                    resultSet.getBoolean(ALIAS_SINGLE_CLIENT),
                    resultSet.getString(ALIAS_APP_ID)
            );
        }
        return serviceDescriptor;
    }
}
