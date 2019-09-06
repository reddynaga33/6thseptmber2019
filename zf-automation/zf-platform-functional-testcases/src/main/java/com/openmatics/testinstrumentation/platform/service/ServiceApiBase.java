package com.openmatics.testinstrumentation.platform.service;

public class ServiceApiBase {

    protected IServiceConfiguration serviceProperty;

    public ServiceApiBase(IServiceConfiguration serviceProperty){
        this.serviceProperty = serviceProperty;
    }

    public String getEnvKey() {return serviceProperty.getEnvConf().getEnvKey();}

    public IServiceConfiguration getServiceProperty() {
        return serviceProperty;
    }
}


