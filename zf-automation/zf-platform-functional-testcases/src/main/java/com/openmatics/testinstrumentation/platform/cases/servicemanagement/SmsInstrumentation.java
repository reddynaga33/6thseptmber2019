package com.openmatics.testinstrumentation.platform.cases.servicemanagement;

import com.openmatics.testinstrumentation.utils.azure.ServiceBusDestinationsHandler;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class SmsInstrumentation {
    public static final int ATTEMPT = 10;

    private final DbConnectionFactory connectionFactory;

    public SmsInstrumentation(DbConnectionFactory connectionFactory) throws Exception {
        this.connectionFactory = connectionFactory;
    }

    public String getFailMessage(String message, String destination, String subscription){
        return String.format("Message about subscription %s in topic %s: %s",
                subscription, destination, message);
    }

    public boolean isExistsSTSubscriptionInDb(String destinationName, String subscription) throws Exception
    {
        String query = DestinationContext.getSqlIsSubscription(destinationName, subscription);
        int number;
        try {
            DbQueryService sql = new DbQueryService(connectionFactory);
            ResultSet rs = sql.executeQuery(query);
            rs.next();
            number = rs.getInt("number");
            sql.closeQuery(rs);
        } catch (SQLException e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
        return number != 0;
    }

    public synchronized void waitForSubscription(String topicName, String subscription, ServiceBusDestinationsHandler sbHandler, long timeout) {
        boolean exists;
        for (int i = 0; i < ATTEMPT; i++) {
            exists = sbHandler.isSubscriptionExists(topicName, subscription);
            if (exists) {
                return;
            }
            try {
                TimeUnit.SECONDS.timedWait(this, timeout);
            } catch (InterruptedException e) {
                System.err.println("Thread is Interrupting during waiting");
            }
        }
        throw new RuntimeException(String.format("Test can not get created syntetic subscription. Topic: %s, Subscription: %s", topicName, subscription));
    }

    public String getOpenshiftDeploymentName(String artifactId, String clientId) {
        return String.format("%s-%s", artifactId, clientId);
    }
}
