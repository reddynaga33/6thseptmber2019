package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.configuration;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.ISmsmcConfigurationModifyApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.ISmsmcConfigurationReadApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.configuration.model.PropertyType;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.saga.SagaUtils;
import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.utils.exception.ObjectNotExistsException;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.apache.http.HttpStatus;

import javax.naming.ServiceUnavailableException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 29.03.2019.
 */
public class SmsmcConfigurationRestApi extends RestApiBase implements ISmsmcConfigurationReadApi, ISmsmcConfigurationModifyApi {
    private static final String PARAM = "?";
    private static final String AND = "&";

    private static final String BASE = "config/clients/%s";
    private static final String HIERARCHICAL = "hierarchical";
    private static final String SERVICE_DESCRIPTOR_ID_TEMPLATE = "serviceDescriptorId=%s";
    private static final String RECONFIGURE_TEMPLATE = "reconfigure=%s";

    public SmsmcConfigurationRestApi(IServiceConfiguration configuration) {
        super("global", configuration);
    }

    @Override
    public String saveClientConfiguration(String clientId, Map<String, String> configuration, boolean reconfigure) {
        String path = getUrlWithParam(null, clientId, reconfigure, false);
        return processSave(path, configuration);
    }

    @Override
    public String saveClientUsesServiceConfiguration(String serviceDescriptorId, String clientId, Map<String, String> configuration, boolean reconfigure) {
        String path = getUrlWithParam(serviceDescriptorId, clientId, reconfigure, false);
        return processSave(path, configuration);
    }

    @Override
    public String deleteClientConfiguration(String clientId, boolean reconfigure) {
        String path = getUrlWithParam(null, clientId, reconfigure, false);
        try {
            HttpResponse deleteResponse = getDeleteResponse(path);
            return SagaUtils.getSagaStatus(deleteResponse);
        } catch (IOException | ServiceUnavailableException e) {
            throw new RuntimeException(String.format("The client configuration '%s' can't be deleted.", clientId), e);
        }
    }

    @Override
    public String deleteClientUsesServiceConfiguration(String serviceDescriptorId, String clientId, boolean reconfigure) {
        String path = getUrlWithParam(serviceDescriptorId, clientId, reconfigure, false);
        try {
            HttpResponse deleteResponse = getDeleteResponse(path);
            return SagaUtils.getSagaStatus(deleteResponse);
        } catch (IOException | ServiceUnavailableException e) {
            throw new RuntimeException(String.format("The deleteClientUsesConfiguration can't be deleted. ServiceDescriptor: %s, clientId: %s", serviceDescriptorId, clientId), e);
        }
    }

    @Override
    public Map<String, String> getConfiguration(String serviceDescriptorId, String clientId) throws ObjectNotExistsException {
        String path = getUrlWithParam(serviceDescriptorId, clientId, null, false);
        HttpResponse getResponse;
        try {
            getResponse = getGetResponse(path);
            switch (getResponse.getStatus()) {
                case HttpStatus.SC_OK:
                    JavaType mapType = TypeFactory.defaultInstance().constructMapType(Map.class, String.class, String.class);
                    Map<String, String> config = JsonUtils.fromJson(getResponse.getContentAsString(), mapType);
                    return config;
                case HttpStatus.SC_UNAUTHORIZED:
                    throw new NotAuthorizedException("Bad authorization");
                case HttpStatus.SC_NOT_FOUND:
                    throw new ObjectNotExistsException(String.format("The configuration model not found. ClientId: %s, ServiceDescriptorID: %s, Status: %s", clientId, serviceDescriptorId, getResponse.getStatus()));
                default:
                    throw new WebApplicationException("Unknown exception");
            }
        } catch (IOException | ServiceUnavailableException e) {
            throw new RuntimeException(String.format("The client configuration can't be load. ClientId: %s, ServiceDescriptorID: %s", clientId, serviceDescriptorId), e);
        }
    }

    @Override
    public Map<PropertyType, Map<String, String>> getHierarchicalConfiguration(String serviceDescriptorId, String clientId) throws ObjectNotExistsException {
        String path = getUrlWithParam(serviceDescriptorId, clientId, null, true);
        HttpResponse getResponse;
        try {
            getResponse = getGetResponse(path);
            switch (getResponse.getStatus()) {
                case HttpStatus.SC_OK:
                    JavaType javaType = TypeFactory.defaultInstance().constructParametricType(Map.class, PropertyType.class, Map.class);
                    Map<PropertyType, Map<String, String>> config = JsonUtils.fromJson(getResponse.getContentAsString(), javaType);
                    return config;
                case HttpStatus.SC_UNAUTHORIZED:
                    throw new NotAuthorizedException("Bad authorization");
                case HttpStatus.SC_NOT_FOUND:
                    throw new ObjectNotExistsException(String.format("The configuration model not found. ClientId: %s, ServiceDescriptorID: %s, Status: %s", clientId, serviceDescriptorId, getResponse.getStatus()));
                default:
                    throw new WebApplicationException("Unknown exception");
            }
        } catch (IOException | ServiceUnavailableException e) {
            throw new RuntimeException(String.format("The client configuration can't be load. ClientId: %s, ServiceDescriptorID: %s", clientId, serviceDescriptorId), e);
        }
    }

    private String processSave(String path, Map<String, String> properties) {
        HttpResponse putResponse;
        try {
            putResponse = getPutResponse(path, JsonUtils.toJson(properties));
        } catch (IOException | ServiceUnavailableException e) {
            System.err.println("The configuration can't be saved due to " + e.getMessage());
            throw new RuntimeException("The configuration can't be saved", e);
        }
        return SagaUtils.getSagaStatus(putResponse);
    }

    private String getUrlWithParam(String serviceDescriptorId, String clientId, Boolean reconfigure, boolean hierarchical) {
        List<String> params = new ArrayList<>();
        if (clientId == null) {
            throw new RuntimeException("The client id can't be null in the REST configuration API");
        }
        if (serviceDescriptorId != null) {
            params.add(String.format(SERVICE_DESCRIPTOR_ID_TEMPLATE, serviceDescriptorId));
        }
        if (reconfigure != null) {
            params.add(String.format(RECONFIGURE_TEMPLATE, reconfigure));
        }
        String baseEndpoint = String.format(BASE, clientId);
        StringBuilder stringBuilder = new StringBuilder(baseEndpoint);
        if (hierarchical) {
            stringBuilder.append("/").append(HIERARCHICAL);
        }
        String delimiter = PARAM;

        for (String param : params) {
            stringBuilder.append(delimiter).append(param);
            delimiter = AND;
        }

        return stringBuilder.toString();
    }
}
