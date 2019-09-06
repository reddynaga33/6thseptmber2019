package com.openmatics.testinstrumentation.utils.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The class encapsulates JDBC query. You should close the query after each #executeQuery.
 */
public class DbQueryService implements AutoCloseable {

    private ResultSet resultSet;
    private final DbConnectionFactory connectionFactory;


    public DbQueryService(DbConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public ResultSet executeQuery(String sql, String... params) throws SQLException {
        System.out.println(sql);
        PreparedStatement preparedStatement = connectionFactory.getJDBCConnection().prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public int executeUpdate(String sql, String... params) throws SQLException {
        System.out.println(sql);
        PreparedStatement preparedStatement = connectionFactory.getJDBCConnection().prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        int rows = preparedStatement.executeUpdate();
        preparedStatement.close();
        return rows;
    }


    public void closeQuery(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.getStatement().close();
            resultSet.close();
        }
    }

    @Override
    public void close() {
        try {
            closeQuery(resultSet);
        } catch (SQLException e) {
            System.err.println("The result set can't be closed due to " + e.getMessage());
        }
    }
}
