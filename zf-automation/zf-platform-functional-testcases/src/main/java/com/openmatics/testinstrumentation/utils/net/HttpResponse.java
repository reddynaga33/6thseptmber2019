package com.openmatics.testinstrumentation.utils.net;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class HttpResponse {

    private org.apache.http.HttpResponse response;
    private HttpRequest request;
    private Integer status;
    private String content;

    public HttpResponse(org.apache.http.HttpResponse response, HttpRequest request) throws IOException {
        this.response = response;
        this.request = request;
        this.content = null;
        this.request = null;
        getStatus();
        getContentAsString();
    }

    public org.apache.http.HttpResponse getResponse(){ return response; }

    public int getStatus(){
        if(status == null) status = HttpResponseUtils.getStatus(response);
        System.out.println("Http status: " + status);
        return status;
    }

    public String getContentAsString() throws IOException {
        if(content == null) {
            content = HttpResponseUtils.getContentAsString(response);
        }
        System.out.println("Content of response:");
        System.out.println(content);
        return content;
    }

    public JSONObject getContentAsJson() {
        return new JSONObject(content);
    }

    public JSONArray getContentAsJsonArray()throws Exception {
        return new JSONArray(content);
    }

    public HttpRequest getRequest() {
        return request;
    }
}
