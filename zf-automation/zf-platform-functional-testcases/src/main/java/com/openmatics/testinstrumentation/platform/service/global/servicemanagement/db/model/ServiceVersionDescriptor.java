package com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Zdeněk Záhlava (zdenek.zahlava@zf.com) on 17.04.2019
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ServiceVersionDescriptor {
    private String id;
    private String versionName;
    private String serviceDescriptorId;

    public String getId() {
        return id;
    }

    public ServiceVersionDescriptor setId(String id) {
        this.id = id;
        return this;
    }

    public String getVersionName() {
        return versionName;
    }

    public ServiceVersionDescriptor setVersionName(String versionName) {
        this.versionName = versionName;
        return this;
    }

    public String getServiceDescriptorId() {
        return serviceDescriptorId;
    }

    public ServiceVersionDescriptor setServiceDescriptorId(String serviceDescriptorId) {
        this.serviceDescriptorId = serviceDescriptorId;
        return this;
    }

    @Override
    public String toString() {
        return "ServiceVersionDescriptor{" +
                "id='" + id + '\'' +
                ", versionName='" + versionName + '\'' +
                ", serviceDescriptorId='" + serviceDescriptorId + '\'' +
                '}';
    }
}
