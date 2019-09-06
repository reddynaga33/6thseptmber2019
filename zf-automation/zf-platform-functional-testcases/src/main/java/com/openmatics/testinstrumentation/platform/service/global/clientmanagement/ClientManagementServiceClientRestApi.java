package com.openmatics.testinstrumentation.platform.service.global.clientmanagement;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
//import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;


public class ClientManagementServiceClientRestApi extends RestApiBase implements IClientManagementServiceClientApi {

    public static String CLIENTS_SOURCE = "clients";


    public ClientManagementServiceClientRestApi(IServiceConfiguration property) throws java.net.MalformedURLException {
        super("global", property);
    }

    @Override
    public HttpResponse getClients() throws Exception {
        return super.getGetResponse(CLIENTS_SOURCE);
    }

    @Override
    public HttpResponse getClientById(String Id) throws Exception {
        return super.getGetResponse(CLIENTS_SOURCE + "/" + Id);
    }

    @Override
    public HttpResponse getClientByIdExtended(String Id) throws Exception {
        return super.getGetResponse(String.format("%s/%s?extendedInfo=true", CLIENTS_SOURCE, Id));
    }

    @Override
    public HttpResponse createClient(String content) throws Exception {
        return super.getPostResponse(CLIENTS_SOURCE, content);
    }

    @Override
    public HttpResponse updateClient(String content) throws Exception {
        return super.getPutResponse(CLIENTS_SOURCE, content);
    }

    @Override
    public HttpResponse deleteClient(String Id) throws Exception {
        return super.getDeleteResponse(CLIENTS_SOURCE + "/" + Id);
    }

}
