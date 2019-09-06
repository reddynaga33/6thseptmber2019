package com.openmatics.testinstrumentation.platform;

import com.openmatics.testinstrumentation.platform.service.configuration.DeploymentConfiguration;
import com.openmatics.testinstrumentation.platform.utils.ServiceBusConnectionConfiguration;
import org.json.JSONObject;

public interface IEnvironmentConfiguration {

    String getAADTenant();

    String getUrl();

    String getDbName();

    String getDbHost();

    String getDbUrl();

    String getDbUrl(String clientId);

    String getDbUser();

    String getDbPassword();

    String getDbConnectionString();

    String getDbConnectionString(String clientId);

    String getDbServerConnectionString();

    String getIotHubHostName();

    String getEnvKey();

    String getOpenshiftHostName();

    String getOpenshiftSaToken();

    ServiceBusConnectionConfiguration getServiceBusProperty();

    DeploymentConfiguration getDeploymentConfiguration();

    JSONObject getValues();
}
