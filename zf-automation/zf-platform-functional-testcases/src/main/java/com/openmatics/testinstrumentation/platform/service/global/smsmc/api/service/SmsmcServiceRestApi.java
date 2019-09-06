package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.ISmsmcServiceRestApi;
import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.utils.exception.ObjectNotExistsException;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.apache.http.HttpStatus;

import javax.naming.ServiceUnavailableException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SmsmcServiceRestApi extends RestApiBase implements ISmsmcServiceRestApi {

    private String SOURCE = "services";

    public SmsmcServiceRestApi(IServiceConfiguration property) throws Exception {
        super("global", property);
    }

    @Override
    public List<ServiceDescriptor> getServicesAll() {
        try {
            HttpResponse getResponse = super.getGetResponse(SOURCE);
            List<ServiceDescriptor> list = new ArrayList<>();
            if (getResponse.getStatus() == HttpStatus.SC_OK) {
                JavaType javaType = TypeFactory.defaultInstance().constructCollectionLikeType(ArrayList.class, ServiceDescriptor.class);
                list = JsonUtils.fromJson(getResponse.getContentAsString(), javaType);
            }
            return list;
        } catch (IOException | ServiceUnavailableException e) {
            System.err.println("The service descriptor can't be found due to " + e.getMessage());
            throw new RuntimeException("The service descriptor can't be found.", e);
        }
    }

    @Override
    public ServiceDescriptor getServiceOne(String serviceDescriptorId) throws ObjectNotExistsException {
        try {
            HttpResponse getResponse = super.getGetResponse(SOURCE + "/" + serviceDescriptorId);
            if (getResponse.getStatus() == HttpStatus.SC_OK) {
                ServiceDescriptor serviceDescriptor = JsonUtils.fromJson(getResponse.getContentAsString(), ServiceDescriptor.class);
                return serviceDescriptor;
            }
            throw new ObjectNotExistsException(String.format("The service descriptor by id '%s' not found", serviceDescriptorId));
        } catch (IOException | ServiceUnavailableException e) {
            System.err.println("The service descriptor can't be found due to " + e.getMessage());
            throw new RuntimeException("The service descriptor can't be found.", e);
        }
    }
}
