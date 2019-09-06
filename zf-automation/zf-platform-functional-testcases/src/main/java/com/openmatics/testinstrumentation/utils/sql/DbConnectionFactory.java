package com.openmatics.testinstrumentation.utils.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 06.03.2019.
 */
public class DbConnectionFactory implements AutoCloseable {
    private static final String MSSQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String ORACLE = "oracle.jdbc.OracleDriver";

    private final String connectionString;
    private Connection connection = null;

    public DbConnectionFactory(String connectionString) {
        this.connectionString = connectionString;
    }

    public Connection getJDBCConnection() throws SQLException {
        if (connection == null) {
            getDbConnection();
        }
        else {
            if (connection.isClosed()){
                connection = DriverManager.getConnection(connectionString);
            }
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    private void getDbConnection() throws SQLException {
        try {
            Class.forName(MSSQL);
        } catch (ClassNotFoundException e) {
            System.err.println("The drivew not found: " + e.getMessage());
        }
        connection = DriverManager.getConnection(connectionString);
    }

    @Override
    public void close() throws Exception {
        closeConnection();
    }
}
