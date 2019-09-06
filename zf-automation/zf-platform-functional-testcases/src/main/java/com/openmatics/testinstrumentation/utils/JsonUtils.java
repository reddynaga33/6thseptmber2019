package com.openmatics.testinstrumentation.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JodaModule())
            .registerModule(new JavaTimeModule())
            .configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    public static JSONObject getJsonItem(JSONArray array, String field, String value) throws Exception {
        for(Object item : array){
            JSONObject json = (JSONObject)item;
            if (!json.isNull(field) && json.getString(field).matches(value)) return json;
        }
        return null;
    }

    public static String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T fromJson(String valueAsString, JavaType javaType) throws IOException {
        return objectMapper.readValue(valueAsString, javaType);
    }

    public static <T> T fromJson(String valueAsString, TypeReference typeReference) throws IOException {
        return objectMapper.readValue(valueAsString, typeReference);
    }

    public static <T> T fromJson(String valueAsString, Class<T> tClass) throws IOException {
        return objectMapper.readValue(valueAsString, tClass);
    }

}
