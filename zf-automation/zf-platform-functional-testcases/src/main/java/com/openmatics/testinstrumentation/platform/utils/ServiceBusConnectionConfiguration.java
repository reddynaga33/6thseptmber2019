package com.openmatics.testinstrumentation.platform.utils;

import org.json.JSONObject;

public class ServiceBusConnectionConfiguration {
    private final String clientId;
    private final String tenant;
    private final String secret;
    private final String subscriptionId;
    private final String resourceGroupName;
    private final String namespace;
    private final String connectionString;

    public ServiceBusConnectionConfiguration(JSONObject json) {

        clientId = json.has("clientId") ? json.getString("clientId") : null;
        subscriptionId = json.has("subscriptionId") ? json.getString("subscriptionId") : null;
        resourceGroupName = json.has("resourceGroupName") ? json.getString("resourceGroupName") : null;
        namespace = json.has("namespace") ? json.getString("namespace") : null;
        connectionString = json.has("connectionString") ? json.getString("connectionString") : null;
        tenant = json.has("tenant") ? json.getString("tenant") : null;
        secret = json.has("secret") ? json.getString("secret") : null;
    }


    public String getClientId() {
        return clientId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getResourceGroupName() {
        return resourceGroupName;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public String getTenant() {
        return tenant;
    }

    public String getSecret() {
        return secret;
    }
}
