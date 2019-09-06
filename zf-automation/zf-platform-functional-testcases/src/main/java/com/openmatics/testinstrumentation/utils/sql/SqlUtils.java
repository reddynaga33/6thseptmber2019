package com.openmatics.testinstrumentation.utils.sql;

import java.time.Instant;

public class SqlUtils {

    public static String getTimeString(Instant value) {
        return value.toString().replace("T"," ").replace("Z","");
    }

    public static String getClientDbName(String clientId){
        return String.format("client-%s-db", clientId);
    }

}
