package com.openmatics.testinstrumentation.utils.net;

import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpResponseUtils {

    public static int getStatus(HttpResponse response) {
        return response.getStatusLine().getStatusCode();
    }

    public static String getContentAsString(HttpResponse response) throws IOException {
        BufferedReader reader = null;
        if (response.getEntity() != null && response.getEntity().getContent() != null ){
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        }
        if (reader == null) return null;
        java.lang.StringBuilder result = new java.lang.StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    public static JSONObject getContentAsJsonObject(HttpResponse response) throws Exception{
        String content = getContentAsString(response);
        if (content == null) return null;
        return new JSONObject(content);
    }

    public static JSONArray getContentAsJsonArray(HttpResponse response) throws Exception{
        String content = getContentAsString(response);
        if (content == null) return null;
        return new JSONArray(content);
    }
}
