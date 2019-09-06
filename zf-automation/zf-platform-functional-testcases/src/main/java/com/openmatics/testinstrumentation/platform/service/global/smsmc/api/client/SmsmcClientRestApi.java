package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.client;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.openmatics.testinstrumentation.platform.entity.Client;
import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.ISmsmcClientRestApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.ISmsmcCusApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.saga.SagaUtils;
import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.apache.http.HttpStatus;

import javax.naming.ServiceUnavailableException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SmsmcClientRestApi extends RestApiBase implements ISmsmcClientRestApi, ISmsmcCusApi {

    public static final String SERVICE_ENDPOINT = "%s/%s/service/%s";
    public static final String EMPTY_BODY = "{}";
    private String SOURCE = "clients";


    public SmsmcClientRestApi(IServiceConfiguration property) {
        super("global", property);
    }

    @Override
    public HttpResponse getClients() throws Exception {
        return super.getGetResponse(SOURCE);
    }

    @Override
    public HttpResponse getClient(String clientId) throws Exception {
        return super.getGetResponse(SOURCE + "/" + clientId);
    }

    @Override
    public List<Client> getAllClients() {
        try {
            HttpResponse response = super.getGetResponse(SOURCE);
            switch (response.getStatus()) {
                case HttpStatus.SC_OK:
                    JavaType javaType = TypeFactory.defaultInstance().constructParametricType(ArrayList.class, Client.class);
                    String contentAsString = response.getContentAsString();
                    List<Client> clients = JsonUtils.fromJson(contentAsString, javaType);
                    return clients;
                case HttpStatus.SC_UNAUTHORIZED:
                    throw new NotAuthorizedException("Bad authorization");
                case HttpStatus.SC_NOT_FOUND:
                    throw new NotFoundException("The get all clients is failed");
                default:
                    throw new WebApplicationException("Unknown exception");
            }
        } catch (IOException | ServiceUnavailableException e) {
            throw new RuntimeException("The get all clients is failed", e);
        }
    }

    @Override
    public String assignServiceToClient(String serviceDescriptorId, String clientId) {
        String source = String.format(SERVICE_ENDPOINT, SOURCE, clientId, serviceDescriptorId);
        try {
            HttpResponse putResponse = this.getPutResponse(source, EMPTY_BODY);
            return SagaUtils.getSagaStatus(putResponse);
        } catch (IOException | ServiceUnavailableException e) {
            throw new RuntimeException(String.format("The client uses service can't be assigned. Client: '%s', ServiceDescriptor: '%s'", clientId, serviceDescriptorId), e);
        }
    }

    @Override
    public String unassignServiceToClient(String serviceDescriptorId, String clientId) {
        String source = String.format(SERVICE_ENDPOINT, SOURCE, clientId, serviceDescriptorId);
        try {
            HttpResponse response = this.getDeleteResponse(source);
            return SagaUtils.getSagaStatus(response);
        } catch (IOException | ServiceUnavailableException e) {
            throw new RuntimeException(String.format("The client uses service can't be unassigned. Client: '%s', ServiceDescriptor: '%s'", clientId, serviceDescriptorId), e);
        }
    }

}
