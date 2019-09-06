package com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.repository;


import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.EventDestination;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Zdeněk Záhlava (zdenek.zahlava@zf.cz) on 21. 03. 2019
 */
public class EventDestinationRepository {
    private static final String ALIAS_ID = "id";
    private static final String ALIAS_DEST_TYPE = "destType";
    private static final String ALIAS_NAME = "name";
    private static final String ALIAS_EVENT_TYPE_ID = "eventType";

    private final DbConnectionFactory connectionFactory;

    public EventDestinationRepository(DbConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public EventDestination getByName(String nameIdentifier) {
        EventDestination destination = null;
        String query = "SELECT [event_destination].id as " + ALIAS_ID + " , " +
                "[event_destination].dest_type as " + ALIAS_DEST_TYPE + " , " +
                "[event_destination].name_identifier as " + ALIAS_NAME + " , " +
                "[event_destination].event_type_id as " + ALIAS_EVENT_TYPE_ID + " " +
                "FROM [service_management_service_schema].[event_destination] " +
                "WHERE name_identifier=?";
        try (DbQueryService service = new DbQueryService(connectionFactory)){
            ResultSet rs = service.executeQuery(query, nameIdentifier);
            if (rs.next()) destination = load(rs);
        } catch (SQLException e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
        return destination;
    }

    public List<EventDestination> findByDeploymentId(String deploymentId) {
        String query = "SELECT [event_destination].id as " + ALIAS_ID + " , " +
                "[event_destination].dest_type as " + ALIAS_DEST_TYPE + " , " +
                "[event_destination].name_identifier as " + ALIAS_NAME + " , " +
                "[event_destination].event_type_id as " + ALIAS_EVENT_TYPE_ID + " " +
                "FROM [service_management_service_schema].[deployment_to_ev_dest] " +
                "JOIN [service_management_service_schema].[event_destination] " +
                "ON [deployment_to_ev_dest].[event_destination_id]=[event_destination].[id] " +
                "WHERE [deployment_id]=?";
        List<EventDestination> eventDestinations = new ArrayList<>();
        try (DbQueryService service = new DbQueryService(connectionFactory)) {
            ResultSet rs = service.executeQuery(query, deploymentId);
            EventDestination destination;
            while ((destination = load(rs)) != null) {
                eventDestinations.add(destination);
            }
            return eventDestinations;
        } catch (SQLException e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
    }

    public String createIfNotExist(EventDestination eventDestination) {
        EventDestination destination;
        destination = getByName(eventDestination.getNameIdentifier());
        if (destination == null){
           return create(eventDestination);
        }
        return destination.getId();
    }

    public String create(EventDestination eventDestination) {
        String query = "INSERT INTO [service_management_service_schema].[event_destination] " +
                "(id, dest_type, name_identifier, event_type_id) " +
                "VALUES ( ?, ?, ?, ?)";

        String id =  UUID.randomUUID().toString();
        try (DbQueryService service = new DbQueryService(connectionFactory)){
            service.executeUpdate(query,
                    id,
                    eventDestination.getDestinationType(),
                    eventDestination.getNameIdentifier(),
                    eventDestination.getEventTypeId());
        } catch (SQLException e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
        return id;
    }

    public int deleteByLikeNameIdentifier(String likeDestinationNameIdentifier) {
        String query = "DELETE FROM [service_management_service_schema].[event_destination] WHERE name_identifier like ?";

        try (DbQueryService queryService = new DbQueryService(connectionFactory)) {
            return queryService.executeUpdate(query, likeDestinationNameIdentifier);
        } catch (Exception e) {
            System.err.println("The database operation can be executed due to: " + e);
            throw new RuntimeException("The database operation can be executed due to ", e);
        }
    }

    private EventDestination load(ResultSet rs) throws SQLException {
        EventDestination eventDestination = null;
        if (rs.next()) {
            eventDestination = new EventDestination()
                    .setId(rs.getString(ALIAS_ID))
                    .setDestinationType(rs.getString(ALIAS_DEST_TYPE))
                    .setNameIdentifier(rs.getString(ALIAS_NAME))
                    .setEventTypeId(rs.getString(ALIAS_EVENT_TYPE_ID));
        }
        return eventDestination;
    }
}
