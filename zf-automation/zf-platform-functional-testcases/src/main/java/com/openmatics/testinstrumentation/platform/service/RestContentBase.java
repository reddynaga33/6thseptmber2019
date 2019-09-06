package com.openmatics.testinstrumentation.platform.service;

import com.google.common.base.Strings;
import java.lang.reflect.Field;

public class RestContentBase {

    public String getJsonFromFields() throws Exception {
        String result = null;
        for (Field f : this.getClass().getDeclaredFields()) {
            if (Strings.isNullOrEmpty(result)){
                result = "{";
            }
            else {
                result += ",";
            }
            result += String.format("\"%s\":\"%s\"", f.getName(), f.get(this));
        }
        return result + "}";
    }

}
