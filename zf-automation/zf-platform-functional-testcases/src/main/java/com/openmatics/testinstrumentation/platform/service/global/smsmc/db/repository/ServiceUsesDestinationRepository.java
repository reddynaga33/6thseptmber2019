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
 *         date: 06.03.2019.
 */
public class ServiceUsesDestinationRepository {
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_NAME_IDENTIFIER = "nameIdentifier";
    public static final String ALIAS_TYPE = "type";
    public static final String ALIAS_SERVICE_DESCRIPTOR_ID = "serviceDescriptorId";
    public static final String ALIAS_PARTITIONING = "partitioning";
    public static final String ALIAS_SINGLE_TENANT = "singleTenant";
    public static final String ALIAS_DIRECTION = "direction";
    private final DbConnectionFactory dbConnectionFactory;

    public ServiceUsesDestinationRepository(DbConnectionFactory dbConnectionFactory) {
        this.dbConnectionFactory = dbConnectionFactory;
    }

    /**
     * Find destinations by the service descriptor.
     *
     * @param serviceDescriptorId service descriptor id
     * @return list of destinations.
     */
    public List<Destination> findServiceUsesDestinationByServiceDescriptorId(String serviceDescriptorId) {
        String sqlQuery = "SELECT " +
                "[id] as " + ALIAS_ID + ", " +
                "[name_identifier] as " + ALIAS_NAME_IDENTIFIER + ", " +
                "[type] as " + ALIAS_TYPE + ", " +
                "[service_descriptor_id] as " + ALIAS_SERVICE_DESCRIPTOR_ID + ", " +
                "[partitioning] as " + ALIAS_PARTITIONING + ", " +
                "[single_tenant] as " + ALIAS_SINGLE_TENANT + ", " +
//                "[message_type_id] as message_type_id, " +
                "[direction] as " + ALIAS_DIRECTION + " " +
                "FROM [service_management_service_schema].[service_uses_destination]  " +
                "WHERE service_descriptor_id = ?";
        System.out.println(sqlQuery +" -> " + serviceDescriptorId);
        List<Destination> destinations;
        try (DbQueryService sqlAccess = new DbQueryService(dbConnectionFactory)) {
            ResultSet resultSet = sqlAccess.executeQuery(sqlQuery, serviceDescriptorId);
            destinations = convertToEntities(resultSet);
        } catch (Exception e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
        return destinations;
    }

    /**
     * Find destinations by the service descriptor id and name identifier.
     *
     * @param serviceDescriptorId service descriptor id
     * @param nameIdentifier      name identifier
     * @return Destination with specified service descriptor and name identifier.
     */
    public Destination findByServiceDescriptorIdAndNameIdentifier(String serviceDescriptorId, String nameIdentifier) {
        String sqlQuery = "SELECT " +
                "[id] as " + ALIAS_ID + ", " +
                "[name_identifier] as " + ALIAS_NAME_IDENTIFIER + ", " +
                "[type] as " + ALIAS_TYPE + ", " +
                "[service_descriptor_id] as " + ALIAS_SERVICE_DESCRIPTOR_ID + ", " +
                "[partitioning] as " + ALIAS_PARTITIONING + ", " +
                "[single_tenant] as " + ALIAS_SINGLE_TENANT + ", " +
                "[direction] as " + ALIAS_DIRECTION + " " +
                "FROM [service_management_service_schema].[service_uses_destination] " +
                "WHERE service_descriptor_id = ? AND name_identifier = ?";
        List<Destination> destinations;
        try (DbQueryService sqlAccess = new DbQueryService(dbConnectionFactory)) {
            ResultSet resultSet = sqlAccess.executeQuery(sqlQuery, serviceDescriptorId, nameIdentifier);
            destinations = convertToEntities(resultSet);
        } catch (Exception e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
        return destinations.isEmpty() ? null : destinations.get(0);
    }

    /**
     * Delete destinations by the service descriptor.
     *
     * @param serviceDescriptorId service descriptor id
     */
    public void deleteServiceUsesDestinationByServiceDescriptorId(String serviceDescriptorId) {
        String sqlQuery = "DELETE FROM [service_management_service_schema].[service_uses_destination] WHERE service_descriptor_id = ?";
        System.out.println(sqlQuery + "->" + serviceDescriptorId);
        try (DbQueryService sqlAccess = new DbQueryService(dbConnectionFactory)) {
            int i = sqlAccess.executeUpdate(sqlQuery, serviceDescriptorId);
            System.out.println(String.format("Delete %d service destinations of service descriptor id %s", i, serviceDescriptorId));
        } catch (Exception e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
    }

    private List<Destination> convertToEntities(ResultSet resultSet) throws SQLException {
        List<Destination> destinations = new ArrayList<>();
        while (resultSet.next()) {
            Destination destination = new Destination();
            destination.setType(DestinationType.valueOf(resultSet.getString(ALIAS_TYPE)))
                    .setDirection(Direction.valueOf(resultSet.getString(ALIAS_DIRECTION)))
                    .setNameIdentifier(resultSet.getString(ALIAS_NAME_IDENTIFIER))
                    .setPartitioning(resultSet.getBoolean(ALIAS_PARTITIONING))
                    .setSingleClient(resultSet.getBoolean(ALIAS_SINGLE_TENANT))
                    .setId(resultSet.getString(ALIAS_ID));
            destinations.add(destination);
        }
        return destinations;
    }
}
