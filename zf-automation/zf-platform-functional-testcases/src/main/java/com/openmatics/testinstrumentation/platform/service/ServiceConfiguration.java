package com.openmatics.testinstrumentation.platform.service;

import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import org.json.JSONObject;

public class ServiceConfiguration implements IServiceConfiguration {

    private String serviceKey;
    private IEnvironmentConfiguration envConf;
    private JSONObject values;

    public ServiceConfiguration(String serviceKey, IEnvironmentConfiguration envConf) throws Exception {
        this.envConf = envConf;
        this.serviceKey = serviceKey;
        this.values = envConf.getValues().getJSONObject("services").getJSONObject(serviceKey);
    }


    @Override
    public IEnvironmentConfiguration getEnvConf(){ return envConf; }

    @Override
    public String getServiceKey(){
        return serviceKey;
    }

    @Override
    public String getApplicationClientId(){
        return values.getString("aadId");
    }

    @Override
    public String getApplicationClientSecret(){
        return values.getString("aadSecret");
    }

    @Override
    public String getApplicationIdUri(){
        return values.getString("servicePath");
    }

    @Override
    public String getTenant()
    {
        if (values.has("tenant")) return values.getString("tenant");
        return null;
    }

    @Override
    public JSONObject getValues(){
        return values;
    }
}
