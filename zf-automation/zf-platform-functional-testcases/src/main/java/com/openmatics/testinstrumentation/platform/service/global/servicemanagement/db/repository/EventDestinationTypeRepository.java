package com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.repository;


import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.EventDestinationType;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Zdeněk Záhlava (zdenek.zahlava@zf.cz) on 21. 03. 2019
 */
public class EventDestinationTypeRepository {

    private static final String ALIAS_ID = "id";
    private static final String ALIAS_NAME = "name";
    private static final String ALIAS_VERSION = "version";

    private final DbConnectionFactory connectionFactory;

    public EventDestinationTypeRepository(DbConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public EventDestinationType  getByName(String name) throws SQLException {
        String query = "SELECT  [event_type].id " + ALIAS_ID + " , " +
                "[event_type].name " + ALIAS_NAME + " , " +
                "[event_type].version " + ALIAS_VERSION + " " +
                "FROM [service_management_service_schema].[event_type] " +
                "WHERE name=?";
        EventDestinationType type = null;
        try (DbQueryService service = new DbQueryService(connectionFactory)){
            ResultSet rs = service.executeQuery(query, name);
            if (rs.next()){
                type = load(rs);
            }
        }
        return type;
    }

    private EventDestinationType load(ResultSet rs) throws SQLException {
        EventDestinationType type = null;
        if (rs.next()) {
            type = new EventDestinationType()
                    .setId(rs.getString(ALIAS_ID))
                    .setName(rs.getString(ALIAS_NAME))
                    .setVersion(rs.getString(ALIAS_VERSION));
        }
        return type;
    }

}
