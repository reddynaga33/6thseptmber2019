package com.openmatics.testinstrumentation.platform.utils;

public class Payload {

    public static String getEventServiceStarted() {
        String getServiceStartedTemplate = "{\n" +
                "\"Payload\": {\n" +
                "\"operation\": \"CREATE\",\n" +
                "\"status\": \"null\",\n" +
                "\"Payload\": {\n" +
                "\"id\": \"null\",\n" +
                "\"serviceDescriptorId\": \"null\",\n" +
                "\"clientId\": \"19309c32-1769-42ba-9829-d18d5e9e072d\",\n" +
                "\"reconfigure\": \"true\",\n" +
                "\"properties\": {\n" +
                "\"test\": \"nikhil_test\",\n" +
                "\"test1\": \"nikhil_test12344\",\n" +
                          "}\n" +
                "}\n" +
                "}\n" +
                "},\n" +
                
                "}";
        
       
        return getServiceStartedTemplate;

    }

}
