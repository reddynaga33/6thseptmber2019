package com.openmatics.testinstrumentation.platform.service.global.clientmanagement;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IClientManagementServiceClientApi {

    HttpResponse getClients()throws Exception;

    HttpResponse getClientById(String Id)throws Exception;

    HttpResponse getClientByIdExtended(String Id) throws Exception;

    HttpResponse createClient(String content)throws Exception;

    HttpResponse updateClient(String content)throws Exception;

    HttpResponse deleteClient(String Id) throws Exception;

}
