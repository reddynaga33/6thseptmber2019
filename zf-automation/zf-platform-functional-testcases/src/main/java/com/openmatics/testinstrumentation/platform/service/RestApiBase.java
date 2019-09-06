package com.openmatics.testinstrumentation.platform.service;

import com.google.common.base.Strings;
import com.openmatics.testinstrumentation.utils.net.HttpRequest;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.naming.ServiceUnavailableException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class RestApiBase {

    private BearerTokenProvider token;
    public static String EMPTY_CONTENT = "{}";
    private String endPoint = null;
    private java.net.URL url;
    public IServiceConfiguration serviceProperty;
    private String clientKey;

    public RestApiBase(URL url, IServiceConfiguration property) {
        this.serviceProperty = property;
        this.url = url;
    }


    public RestApiBase(String clientKey, IServiceConfiguration property) {
        this.serviceProperty = property;
        try {
            URL envUrl = new URL(property.getEnvConf().getUrl());
            this.url = new URL(envUrl, String.format("%s/%s", clientKey, property.getServiceKey()));
        } catch (MalformedURLException e) {
            System.err.println("Can't create URL due to " + e.getMessage());
        }
    }

    public RestApiBase(String clientKey, IServiceConfiguration property, String host) {
        this.clientKey = clientKey;
        this.serviceProperty = property;
        URL envUrl;
        try {
            if (host != null) envUrl = new URL(host);
            else envUrl = new URL(property.getEnvConf().getUrl());
            this.url = new URL(envUrl, String.format("%s/%s", clientKey, property.getServiceKey()));
        } catch (MalformedURLException e) {
            System.err.println("Can't create URL due to " + e.getMessage());
        }
    }

    public BearerTokenProvider getAuthorizationToken() {
        if (token == null) {
            token = new BearerTokenProvider(serviceProperty);
        }
        return token;
    }

    public HttpResponse getGetResponse(String source) throws IOException, ServiceUnavailableException {
        HttpUriRequest request = new HttpGet(getCallUrl(source));
        return callResponse(new HttpRequest(request));
    }

    public HttpResponse getPostResponse(String source, String content) throws IOException, ServiceUnavailableException {
        HttpPost post = new HttpPost(getCallUrl(source));
        System.out.println("Content for send:");
        System.out.println(content);
        post.setEntity(new StringEntity(content, "UTF-8"));
        post.setHeader("Content-type", "application/json; charset=UTF-8");
        return callResponse(new HttpRequest(post, content));
    }

    public HttpResponse getPutResponse(String source, String content) throws IOException, ServiceUnavailableException {
        HttpPut put = new HttpPut(getCallUrl(source));
        System.out.println("Request content:");
        System.out.println(content);
        put.setEntity(new StringEntity(content, "UTF-8"));
        put.setHeader("Content-type", "application/json; charset=UTF-8");
        return callResponse(new HttpRequest(put, content));
    }

    public HttpResponse getPatchResponse(String source, String content) throws IOException, ServiceUnavailableException {
        HttpPatch patch = new HttpPatch(getCallUrl(source));
        System.out.println("Request content:");
        System.out.println(content);
        patch.setEntity(new StringEntity(content, "UTF-8"));
        patch.setHeader("Content-type", "application/json; charset=UTF-8");
        return callResponse(new HttpRequest(patch, content));
    }

    public HttpResponse getDeleteResponse(String source) throws IOException, ServiceUnavailableException {
        HttpUriRequest request = new HttpDelete(getCallUrl(source));
        return callResponse(new HttpRequest(request));
    }


    private HttpResponse callResponse(HttpRequest httpRequest) throws IOException, ServiceUnavailableException {
        BearerTokenProvider token = getAuthorizationToken();
        HttpUriRequest request = httpRequest.getRequest();
        token.getToken();
        System.out.println("Will be send request:");
        System.out.println(request.getMethod() + " " + request.getURI().toString());
        request.setHeader("Authorization", "Bearer " + getAuthorizationToken().getToken().getAccessToken());
        System.out.println("Token: " + getAuthorizationToken().getToken().getAccessToken());
        HttpResponse response;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
            response = new HttpResponse(httpClient.execute(request), httpRequest);
        }
        return response;
    }

    private String getCallUrl(String source){
        String urlString;
        if(Strings.isNullOrEmpty(source))
        {
            urlString = this.url.toString();
        }
        else
        {
            urlString =  url.toString() + "/" + source;
        }
        return urlString;
    }


}
