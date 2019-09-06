package com.openmatics.testinstrumentation.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Read resouces from txt a json files a provide its content as json, json array or lines list.
 * Use path to resource example:
 * this means {project base}/src/test/resources/integration/client/ClientPropertiesJsonTemplate.json
 */
public class ResourceLoader {

    public static JSONArray loadAsJsonArray(String PathInResource) throws Exception {
        return new JSONArray(LoadAsString(PathInResource));
    }

    public static JSONObject loadAsJsonArrayGetItemByField(String PathInResource, String field, String fieldValue) throws Exception {
        for(Object item : (new JSONArray(LoadAsString(PathInResource)))){
            JSONObject json = (JSONObject)item;
            if (json.getString(field).equals(fieldValue)) return json;
        }
        return null;
    }

    public static JSONArray loadAsJsonArray(String PathInResource, Map<String, String> values) throws Exception {
        return new JSONArray(LoadAsString(PathInResource, values));
    }

    public static JSONObject loadAsJsonArrayGetItemByField(String PathInResource, Map<String, String> values, String field, String fieldValue) throws Exception {
        for(Object item : (new JSONArray(LoadAsString(PathInResource, values)))){
            JSONObject json = (JSONObject)item;
            if (json.getString(field).equals(fieldValue)) return json;
        }
        return null;
    }


    public static JSONObject LoadAsJson(String PathInResource) throws Exception {
        return new JSONObject(LoadAsString(PathInResource));
    }

    public static JSONObject LoadAsJson(String PathInResource, Map<String, String> values) throws Exception {
        return new JSONObject(LoadAsString(PathInResource, values));
    }


    public static String LoadAsString(String PathInResource) throws Exception {
        StringBuilder string = new StringBuilder();
        BufferedReader bs = getReader(PathInResource);
        for (String line; (line = bs.readLine()) != null;) {
            string.append(line);
        }
        return string.toString();
    }

    public static String LoadAsString(String PathInResource, Map<String, String> values) throws Exception {
        StringBuilder string = new StringBuilder();
        BufferedReader bs = getReader(PathInResource);
        for (String line; (line = bs.readLine()) != null;) {
            string.append(line);
        }
        String result = string.toString();
        for(Map.Entry<String, String> entry : values.entrySet()){
            result = result.replace(getReplaceKey(entry.getKey()),entry.getValue());
        }
        return result;
    }

    public static List<String> LoadAsLines(String PathInResource, Map<String, String> values)throws Exception {
        List<String> lines = new ArrayList<String>();
        BufferedReader bs = getReader(PathInResource);
        for (String line; (line = bs.readLine()) != null;) {
            for(Map.Entry<String, String> entry : values.entrySet()){
                line = line.replace(getReplaceKey(entry.getKey()),entry.getValue());
            }
            lines.add(line);
        }
        return lines;
    }

    public static List<String> LoadAsLines(String PathInResource)throws Exception {
        List<String> lines = new ArrayList<String>();
        BufferedReader bs = getReader(PathInResource);
        for (String line; (line = bs.readLine()) != null;) {
            lines.add(line);
        }
        return lines;
    }

    public static List<String> LoadAsLinesWithOutComment(String PathInResource)throws Exception {
        List<String> lines = new ArrayList<String>();
        BufferedReader bs = getReader(PathInResource);
        for (String line; (line = bs.readLine()) != null;) {
            if (!line.startsWith("#")){
                lines.add(line);
            }
        }
        return lines;
    }

    public static List<String> LoadAsLinesWithOutComment(String PathInResource, Map<String, String> values)throws Exception {
        List<String> lines = new ArrayList<String>();
        BufferedReader bs = getReader(PathInResource);
        for (String line; (line = bs.readLine()) != null;) {
            if (!line.startsWith("#")){
                for(Map.Entry<String, String> entry : values.entrySet()){
                    line = line.replace(getReplaceKey(entry.getKey()),entry.getValue());
                }
                lines.add(line);
            }
        }
        return lines;
    }



    private static String getReplaceKey(String value){
        return String.format("${%s}", value);
    }

    private static BufferedReader getReader(String pathInResource) {
        System.out.println(String.format("[tc] Get reader of source %s", pathInResource));
        InputStream inputStream = ResourceLoader.class.getClassLoader().getResourceAsStream(pathInResource);
        if (inputStream == null) {
            throw new RuntimeException(String.format("Did not get input stream of source %s.", pathInResource));
        }
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        return new BufferedReader(streamReader);
    }
}
