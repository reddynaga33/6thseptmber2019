package com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.repository;


import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceVersionDescriptor;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Zdeněk Záhlava (zdenek.zahlava@zf.cz) on 21. 03. 2019
 */
public class ServiceVersionDescriptorRepository {

    private static final String ALIAS_ID = "id";
    private static final String ALIAS_NAME = "name";
    private static final String ALIAS_SERVICE_DESCRIPTOR_ID = "service";
    private final DbConnectionFactory connectionFactory;

    public ServiceVersionDescriptorRepository(DbConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public ServiceVersionDescriptor findByNameAndServiceDescriptorGroupIdAndArtifactId(String name, String groupId, String artifactId) {
        final String query = "SELECT v.[id] as " + ALIAS_ID + ", " +
                "      v.[version_name] as " + ALIAS_NAME + ", " +
                "      v.[service_descriptor_id] as " + ALIAS_SERVICE_DESCRIPTOR_ID +
                "  FROM [service_management_service_schema].[service_version_descriptor] v " +
                "  JOIN [service_management_service_schema].[service_descriptor] s ON s.id = v.service_descriptor_id " +
                "  WHERE v.version_name=? AND s.group_id=? AND s.artifact_id=?";
        try (DbQueryService sqlAccess = new DbQueryService(connectionFactory)) {
            ResultSet rs = sqlAccess.executeQuery(query, name, groupId, artifactId);
            return convert(rs);
        } catch (SQLException e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }

    }

    private ServiceVersionDescriptor convert(ResultSet rs) throws SQLException {
        ServiceVersionDescriptor serviceVersionDescriptor = null;
        if (rs.next()) {
            serviceVersionDescriptor = new ServiceVersionDescriptor()
                    .setId(rs.getString(ALIAS_ID))
                    .setServiceDescriptorId(rs.getString(ALIAS_SERVICE_DESCRIPTOR_ID))
                    .setVersionName(rs.getString(ALIAS_NAME));
        }
        return serviceVersionDescriptor;
    }


}
