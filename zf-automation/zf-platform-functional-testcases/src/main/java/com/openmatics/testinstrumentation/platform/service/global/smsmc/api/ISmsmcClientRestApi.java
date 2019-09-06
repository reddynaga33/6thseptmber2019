package com.openmatics.testinstrumentation.platform.service.global.smsmc.api;

import com.openmatics.testinstrumentation.platform.entity.Client;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

import java.util.List;

public interface ISmsmcClientRestApi {

    @Deprecated
    HttpResponse getClients() throws Exception;

    @Deprecated
    HttpResponse getClient(String clientId) throws Exception;

    List<Client> getAllClients();

}
