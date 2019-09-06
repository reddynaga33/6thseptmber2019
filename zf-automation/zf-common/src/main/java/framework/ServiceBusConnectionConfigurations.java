package framework;

import net.minidev.json.JSONObject;

public class ServiceBusConnectionConfigurations {
    private final String clientId;
    private final String tenant;
    private final String secret;
    private final String subscriptionId;
    private final String resourceGroupName;
    private final String namespace;
    private final String connectionString;

    public ServiceBusConnectionConfigurations(JSONObject json) {

        clientId = json.containsKey("clientId") ? json.getAsString("clientId") : null;
        subscriptionId = json.containsKey("subscriptionId") ? json.getAsString("subscriptionId") : null;
        resourceGroupName = json.containsKey("resourceGroupName") ? json.getAsString("resourceGroupName") : null;
        namespace = json.containsKey("namespace") ? json.getAsString("namespace") : null;
        connectionString = json.containsKey("connectionString") ? json.getAsString("connectionString") : null;
        tenant = json.containsKey("tenant") ? json.getAsString("tenant") : null;
        secret = json.containsKey("secret") ? json.getAsString("secret") : null;
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
