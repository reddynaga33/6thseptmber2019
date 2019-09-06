package com.openmatics.testinstrumentation.utils.net;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

/**
 * Hold information about request of API
 */
public class HttpRequest {

    private String content;
    private HttpUriRequest request;
    private StringEntity entity;

    /**
     * This constructor use for method without content - GET, DELETE
     *
     * @param request
     */
    public HttpRequest(HttpUriRequest request){

        this.request = request;
        this.content = null;
    }

    /**
     * This constructor use for method with content - POST, PUT, PATCH
     *
     * @param request
     * @param content
     */
    public HttpRequest(HttpUriRequest request, String content){

        this.request = request;
        this.content = content;
    }

    /**
     * Returns of request content for a method POST, PUT, PATCH otherwise is null
     * @return String
     */
    public String getContent(){
        return content;
    }

    /**
     * Returns HttpUriRequest which can be cast HttpPost, HttpPut, HttpPatch in depending on the called method (POST,PUT,PATCH) for access to next members
     * @return HttpUriRequest
     */
    public HttpUriRequest getRequest(){
        return request;
    }
}
