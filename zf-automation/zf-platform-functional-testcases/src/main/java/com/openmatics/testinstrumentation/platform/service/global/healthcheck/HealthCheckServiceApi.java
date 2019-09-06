package com.openmatics.testinstrumentation.platform.service.global.healthcheck;

import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.utils.net.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HealthCheckServiceApi implements IHealthCheckServiceApi {

    private IEnvironmentConfiguration envConf;

    public HealthCheckServiceApi(IEnvironmentConfiguration envConf) {
        this.envConf =envConf;
    }

    @Override
    public HttpResponse getHealthCheck(String clientId, String artifactId) throws Exception {

        java.net.URL source = new java.net.URL(envConf.getUrl());
        source = new java.net.URL(source, String.format("%s/%s/health/check", clientId, artifactId));
        HttpUriRequest request = new HttpGet(source.toURI());
        System.out.println("Will be send request:");
        System.out.println(request.getMethod() + " " + request.getURI().toString());
        HttpResponse response;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
            HttpRequest httpRequest = new HttpRequest(request);
            response = new HttpResponse(httpClient.execute(request), httpRequest);
        }
        return response;
    }

}
