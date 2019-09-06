package com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.repository;


import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.Deployment;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zdeněk Záhlava (zdenek.zahlava@zf.cz) on 21. 03. 2019
 */
public class DeploymentRepository {
    private static final String ALIAS_ID = "id";
    private static final String ALIAS_CLIENT_ID = "clientId";
    private static final String ALIAS_DEPLOYMENT_STATUS = "status";
    private static final String ALIAS_VERSION_ID = "versionId";

    private final DbConnectionFactory connectionFactory;

    public DeploymentRepository(DbConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Deployment getDeployment(String serviceVersionDescriptorId, String clientId) throws SQLException {
        String query = "SELECT [deployment].id as " + ALIAS_ID + ", " +
                "[deployment].client_id as " + ALIAS_CLIENT_ID + ", " +
                "[deployment].service_version_descriptor_id as " + ALIAS_VERSION_ID + ", " +
                "[deployment].deployment_status as " + ALIAS_DEPLOYMENT_STATUS + " " +
                "FROM [service_management_service_schema].[deployment] " +
                "WHERE service_version_descriptor_id=? AND and client_id=?";
        Deployment deployment = null;
        try (DbQueryService service = new DbQueryService(connectionFactory)){
            ResultSet rs = service.executeQuery(query, serviceVersionDescriptorId, clientId);
            if (rs.next()){
                deployment = loadDeployment(rs);
            }
        }
        return deployment;
    }

    public List<Deployment> getAllByServiceVersionDescriptorId(String serviceVersionDescriptorId) {
        String query = "SELECT " +
                "[deployment].id as " + ALIAS_ID + ", " +
                "[deployment].client_id as " + ALIAS_CLIENT_ID + ", " +
                "[deployment].service_version_descriptor_id as " + ALIAS_VERSION_ID + ", " +
                "[deployment].deployment_status as " + ALIAS_DEPLOYMENT_STATUS + " " +
                "FROM [service_management_service_schema].[deployment] " +
                "WHERE [deployment].[service_version_descriptor_id] = ?";

        List<Deployment> deployments = new ArrayList<>();
        try (DbQueryService service = new DbQueryService(connectionFactory)) {
            ResultSet rs = service.executeQuery(query, serviceVersionDescriptorId);
            Deployment deployment;
            while ((deployment = loadDeployment(rs)) != null) {
                deployments.add(deployment);
            }
        } catch (SQLException e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
        return deployments;
    }

    public List<Deployment> getAllByGroupIdAndArtifactId(String groupId, String artifactId) {
        final String aliasServiceDescriptorId = "serviceDescriptorId";
        final String aliasServiceVersionDescriptorId = "serviceVersionDescriptorId";
        String query = "SELECT " +
                "d.id as " + ALIAS_ID + ", " +
                "d.client_id as " + ALIAS_CLIENT_ID + ", " +
                "d.service_version_descriptor_id as " + ALIAS_VERSION_ID + ", " +
                "d.deployment_status as " + ALIAS_DEPLOYMENT_STATUS + ", " +
                "s.short_name as shortName, " +
                "s.artifact_id as artifactId, " +
                "s.single_client as singleClient, " +
                "s.application_id as applicationId, " +
                "s.group_id as serviceDescriptorGroupId, " +
                "s.id as " + aliasServiceDescriptorId + ", " +
                "v.id as " + aliasServiceVersionDescriptorId + " " +
                "FROM [service_management_service_schema].[deployment] d " +
                "JOIN [service_management_service_schema].[service_version_descriptor] v " +
                "ON d.service_version_descriptor_id=v.id " +
                "JOIN [service_management_service_schema].[service_descriptor] s " +
                "ON v.service_descriptor_id=s.id " +
                "WHERE s.group_id = ? AND s.artifact_id = ?";

        List<Deployment> deployments = new ArrayList<>();
        try (DbQueryService service = new DbQueryService(connectionFactory)) {
            ResultSet rs = service.executeQuery(query, groupId, artifactId);
            Deployment deployment;
            while ((deployment = loadDeployment(rs)) != null) {
                deployment.setServiceDescriptorId(rs.getString(aliasServiceDescriptorId))
                        .setServiceVersionDescriptorId(rs.getString(aliasServiceVersionDescriptorId));
                deployments.add(deployment);
            }
        } catch (SQLException e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
        return deployments;
    }

    private Deployment loadDeployment(ResultSet rs) throws SQLException {
        Deployment deployment = null;
        if (rs.next()) {
            deployment = new Deployment()
                    .setId(rs.getString(ALIAS_ID))
                    .setClientId(rs.getString(ALIAS_CLIENT_ID))
                    .setDeploymentStatus(rs.getString(ALIAS_DEPLOYMENT_STATUS))
                    .setServiceVersionDescriptorId(rs.getString(ALIAS_VERSION_ID));
        }
        return deployment;
    }

}
