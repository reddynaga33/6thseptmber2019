package com.openmatics.testinstrumentation.platform;

import com.openmatics.testinstrumentation.platform.service.configuration.DeploymentConfiguration;
import com.openmatics.testinstrumentation.platform.utils.ServiceBusConnectionConfiguration;
import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.utils.ResourceLoader;
import org.json.JSONObject;

import java.io.IOException;

public class EnvironmentConfiguration implements IEnvironmentConfiguration {


    private static final String VALUES_RESOURCE_PATH_TEMPLATE = "environment/%s/config.json";
    private JSONObject values;
    private String envKey;
    private ServiceBusConnectionConfiguration serviceBusProperty;
    private DeploymentConfiguration deploymentConfiguration;


    public EnvironmentConfiguration(String envKey) throws Exception {
        this.values = loadValues(envKey.toLowerCase());
        this.envKey = envKey.toLowerCase();
    }

    @Override
    public String getEnvKey() {
        return envKey;
    }

    @Override
    public String getUrl() {
        return values.getString("url");
    }

    @Override
    public String getAADTenant() {
        return values.getString("tenant");
    }

    @Override
    public String getDbHost(){
        return values.getJSONObject("db").getString("host");
    }

    @Override
    public String getDbName() {
        return values.getJSONObject("db").getString("name");
    }

    @Override
    public String getDbUrl() {
        return "jdbc:sqlserver://" + getDbHost() + ".database.windows.net:1433;database=" + getDbName() + ";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    }

    @Override
    public String getDbUrl(String clientId) {
        return "jdbc:sqlserver://" + getDbHost() + ".database.windows.net:1433;database=client-" + clientId + "-db;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    }

    @Override
    public String getDbUser() {
        return values.getJSONObject("db").getString("user");
    }

    @Override
    public String getDbPassword() {
        return values.getJSONObject("db").getString("password");
    }

    @Override
    public String getDbConnectionString() {
        return String.format("%suser=%s;password=%s;", getDbUrl(), getDbUser(), getDbPassword());
    }

    @Override
    public String getDbConnectionString(String clientId) {
        return String.format("%suser=%s;password=%s;", getDbUrl(clientId), getDbUser(), getDbPassword());
    }

    @Override
    public String getDbServerConnectionString() {
        String serverString = "jdbc:sqlserver://" + getDbHost() + ".database.windows.net:1433;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        return String.format("%suser=%s;password=%s;", serverString, getDbUser(), getDbPassword());
    }

    @Override
    public String getIotHubHostName() {
        return values.getJSONObject("iotHub").getString("hostName");
    }

    @Override
    public String getOpenshiftHostName() {
        return values.getJSONObject("openshift").getString("hostName");
    }

    @Override
    public String getOpenshiftSaToken() {
        return values.getJSONObject("openshift").getString("saToken");
    }

    @Override
    public ServiceBusConnectionConfiguration getServiceBusProperty() {
        if (serviceBusProperty == null) {
            if (!values.has("serviceBus")) return null;
            serviceBusProperty = new ServiceBusConnectionConfiguration(values.getJSONObject("serviceBus"));
        }
        return serviceBusProperty;
    }

    @Override
    public DeploymentConfiguration getDeploymentConfiguration() {
        try {
            if (deploymentConfiguration == null) {
                if (values.has("deployment")) {
                    deploymentConfiguration = JsonUtils.fromJson(values.getJSONObject("deployment").toString(), DeploymentConfiguration.class);
                }
            }
        } catch (IOException e) {
            System.err.println("The Deployment configuration can't be deserialized");
        }
        return deploymentConfiguration;
    }

    @Override
    public JSONObject getValues() {
        return values;
    }

    private JSONObject loadValues(String envKey) {
        String path = String.format(VALUES_RESOURCE_PATH_TEMPLATE, envKey);
        try {
            return ResourceLoader.LoadAsJson(path);
        }
        catch (Exception e){
           String message = "Environment configuration don´t possible load from path: " + path;
           System.err.println(message + " " +  e.toString());
           throw new RuntimeException(message, e);
        }
    }

}
