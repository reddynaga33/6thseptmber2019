package com.openmatics.testinstrumentation.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuidUtils {

    public static final String GUID_REGEX_PATTERN = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}";

    public static boolean containsGuid(String expression){
        if (expression != null)
        {
            String stringPattern = String.format(".*(%s).*", GUID_REGEX_PATTERN);
            Pattern pattern = Pattern.compile(stringPattern);
            Matcher matcher = pattern.matcher(expression);
            return matcher.matches();
        }
        return false;
    }

    public static boolean isGuid(String expression){
        if (expression != null)
        {
            Pattern pattern = Pattern.compile(GUID_REGEX_PATTERN);
            Matcher matcher = pattern.matcher(expression);
            return matcher.matches();
        }
        return false;
    }

}
