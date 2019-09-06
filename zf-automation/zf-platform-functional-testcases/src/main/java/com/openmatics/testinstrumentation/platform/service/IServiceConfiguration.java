package com.openmatics.testinstrumentation.platform.service;

import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import org.json.JSONObject;

public interface IServiceConfiguration {

    //enum ServiceType { global, clients}

    IEnvironmentConfiguration getEnvConf();

    String getServiceKey();

    String getApplicationClientId();

    String getApplicationClientSecret();

    String getApplicationIdUri();

    String getTenant();

    JSONObject getValues();


}
