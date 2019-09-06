package com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.repository;


import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;

/**
 * Created by Zdeněk Záhlava (zdenek.zahlava@zf.cz) on 21. 03. 2019
 */
public class DeploymentToEventDestinationRepository {

    private final DbConnectionFactory connectionFactory;

    public DeploymentToEventDestinationRepository(DbConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public int deleteByLikeNameIdentifier(String likeDestinationNameIdentifier){
        String query = "DELETE [service_management_service_schema].[deployment_to_ev_dest] FROM [service_management_service_schema].[deployment_to_ev_dest] " +
                "JOIN [service_management_service_schema].[event_destination] " +
                "ON [event_destination].id = [deployment_to_ev_dest].[event_destination_id] " +
                "WHERE name_identifier like ?";

        try (DbQueryService queryService = new DbQueryService(connectionFactory)){
            return queryService.executeUpdate(query, likeDestinationNameIdentifier);
        }
        catch (Exception e){
            System.err.println("The database operation can be executed due to: " + e);
            throw new RuntimeException("The database operation can be executed due to ", e);
        }
    }

    public int deleteByDeploymentId(String deploymentId) {
        String query = "DELETE [service_management_service_schema].[deployment_to_ev_dest] FROM [service_management_service_schema].[deployment_to_ev_dest] " +
                "WHERE deployment_id=?";

        try (DbQueryService queryService = new DbQueryService(connectionFactory)) {
            return queryService.executeUpdate(query, deploymentId);
        } catch (Exception e) {
            System.err.println("The database operation can be executed due to: " + e);
            throw new RuntimeException("The database operation can be executed due to ", e);
        }
    }
}
