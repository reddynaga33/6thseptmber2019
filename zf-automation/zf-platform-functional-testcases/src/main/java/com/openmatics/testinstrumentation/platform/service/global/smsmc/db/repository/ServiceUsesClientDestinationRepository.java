package com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository;

import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.Destination;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.DestinationType;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.Direction;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 16.04.2019.
 */
public class ServiceUsesClientDestinationRepository {
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_NAME_IDENTIFIER = "nameIdentifier";
    public static final String ALIAS_TYPE = "type";
    public static final String ALIAS_MESSAGE_TYPE_ID = "msgtype";
    public static final String ALIAS_PARTITIONING = "partitioning";
    public static final String ALIAS_DIRECTION = "direction";
    public static final String ALIAS_CUS_ID = "cus";
    private final DbConnectionFactory dbConnectionFactory;

    public ServiceUsesClientDestinationRepository(DbConnectionFactory dbConnectionFactory) {
        this.dbConnectionFactory = dbConnectionFactory;
    }

    /**
     * Find destinations by the service descriptor id.
     *
     * @param serviceDescriptorId service descriptor id
     * @return list of destinations relates to the service descriptor.
     */
    public List<Destination> findByServiceDescriptorId(String serviceDescriptorId) {
        String sqlQuery = "SELECT " +
                "d.id as " + ALIAS_ID + ", " +
                "d.direction as " + ALIAS_DIRECTION + ", " +
                "d.name_identifier as " + ALIAS_NAME_IDENTIFIER + ", " +
                "d.partitioning as " + ALIAS_PARTITIONING + ", " +
                "d.type as " + ALIAS_TYPE + ", " +
                "d.client_uses_service_id as " + ALIAS_CUS_ID + ", " +
                "d.message_type_id as " + ALIAS_MESSAGE_TYPE_ID +
                "  FROM [service_management_service_schema].[service_uses_client_destination] d " +
                "  JOIN service_management_service_schema.client_uses_service cs ON cs.id = d.client_uses_service_id" +
                "  where cs.service_descriptor_id = ?";

        try (DbQueryService sqlAccess = new DbQueryService(dbConnectionFactory)) {
            ResultSet resultSet = sqlAccess.executeQuery(sqlQuery, serviceDescriptorId);
            List<Destination> destinations = convertToEntities(resultSet);
            return destinations;
        } catch (Exception e) {
            System.err.println("The database operation can be executed due to: " + e);
            throw new RuntimeException("The database operation can be executed due to " + e);
        }
    }


    private List<Destination> convertToEntities(ResultSet resultSet) throws SQLException {
        List<Destination> destinations = new ArrayList<>();
        while (resultSet.next()) {
            Destination destination = new Destination();
            destination.setId(resultSet.getString(ALIAS_ID))
                    .setDirection(Direction.valueOf(resultSet.getString(ALIAS_DIRECTION)))
                    .setNameIdentifier(resultSet.getString(ALIAS_NAME_IDENTIFIER))
                    .setPartitioning(resultSet.getBoolean(ALIAS_PARTITIONING))
                    .setType(DestinationType.valueOf(resultSet.getString(ALIAS_TYPE)))
                    // This destinations are always single client
                    .setSingleClient(true);
            destinations.add(destination);
        }
        return destinations;
    }
}
